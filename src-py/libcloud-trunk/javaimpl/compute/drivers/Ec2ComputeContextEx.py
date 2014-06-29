'''
Created on 21.12.2013

@author: root
'''

from org.askalon.jlibcloud.compute.driverSpecific.ec2Ex import EC2ComputeContext
from org.askalon.jlibcloud.compute.driverSpecific.ec2Ex import EC2NodeTemplateImpl


from javaimpl.compute.utils import wrap_listing, get_property, get_property_list
from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception
from javaimpl.compute.utils import get_property, get_property_list, jmap_to_pymap, jlist_map_to_pylist_map
from javaimpl.compute.base.NodeImpl import NodeImpl

import time
from libcloud.utils.xml import fixxpath, findtext, findattr, findall
from libcloud.compute.drivers.ec2 import NAMESPACE
    
from javaimpl.compute.drivers.Ec2ComputeContext import BaseEC2ComputeContextImpl

class EC2ComputeContextImpl(BaseEC2ComputeContextImpl, EC2ComputeContext):
    '''
    classdocs
    '''
    def __init__(self, builder):
        '''
        Constructor
        '''
        BaseEC2ComputeContextImpl.__init__(self, builder)
          
    def createNode(self, node_temp):
        try:
            kwargs = self._eval_template(node_temp)
            kwargs = self._parse_baseec2_template(node_temp, kwargs)
            #normal or spot create
            spot_price = node_temp.getExSpotPrice()
            if spot_price:
                kwargs['ex_price'] = spot_price
                if node_temp.getExSpotInstances():
                    kwargs['ex_instancecount'] = node_temp.getExSpotInstances()
                return wrap_listing(self.create_spot_node(**kwargs), NodeImpl)
            else:
                return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
        except Exception, ex:
            raise wrap_exception(ex)
    
    #--------------------------------------------#
    # provider specific methods                  #
    #--------------------------------------------#
    def create_spot_node(self, **kwargs):
        """Create a new EC2 spot node

        Reference: http://bit.ly/8ZyPSy [docs.amazonwebservices.com]

        @inherits: L{NodeDriver.create_node}

        @keyword    ex_price: Maximum hourly price for any Spot Instance launched to fulfill the request
        @type       ex_price: C{str}
        
        @keyword    ex_instancecount: number of instances to launch
        @type       ex_instancecount: C{int}

        @keyword    ex_security_groups: Name of security group
        @type       ex_security_groups: C{str}

        @keyword    ex_keyname: The name of the key pair
        @type       ex_keyname: C{str}

        @keyword    ex_userdata: User data
        @type       ex_userdata: C{str}

        @keyword    ex_clienttoken: Unique identifier to ensure idempotency
        @type       ex_clienttoken: C{str}

        @keyword    ex_blockdevicemappings: C{list} of C{dict} block device
                    mappings. Example:
                    [{'DeviceName': '/dev/sdb', 'VirtualName': 'ephemeral0'}]
        @type       ex_blockdevicemappings: C{list} of C{dict}
        """
        image = kwargs["image"]
        size = kwargs["size"]
        price = kwargs["ex_price"]
        params = {
            'Action': 'RequestSpotInstances',
            'SpotPrice' : price,
            'LaunchSpecification.ImageId': image.id,
            'InstanceCount': kwargs.get('ex_instancecount', '1'),
            'LaunchSpecification.InstanceType': size.id
        }

        if 'ex_security_groups' in kwargs:
            if not isinstance(kwargs['ex_security_groups'], list):
                kwargs['ex_security_groups'] = [kwargs['ex_security_groups']]
            for sig in range(len(kwargs['ex_security_groups'])):
                params['LaunchSpecification.SecurityGroup.%d' % (sig + 1,)] =\
                    kwargs['ex_security_groups'][sig]

        if 'location' in kwargs:
            availability_zone = getattr(kwargs['location'],
                                        'availability_zone', None)
            if availability_zone:
                if availability_zone.region_name != self.region_name:
                    raise AttributeError('Invalid availability zone: %s'
                                         % (availability_zone.name))
                params['LaunchSpecification.Placement.AvailabilityZone'] = availability_zone.name

        if 'ex_keyname' in kwargs:
            params['LaunchSpecification.KeyName'] = kwargs['ex_keyname']

        if 'ex_userdata' in kwargs:
            params['LaunchSpecification.UserData'] = base64.b64encode(b(kwargs['ex_userdata']))\
                .decode('utf-8')

        #if 'ex_clienttoken' in kwargs:
        #   params['ClientToken'] = kwargs['ex_clienttoken']

        if 'ex_blockdevicemappings' in kwargs:
            for index, mapping in enumerate(kwargs['ex_blockdevicemappings']):
                params['LaunchSpecification.BlockDeviceMapping.%d.DeviceName' % (index + 1)] = \
                    mapping['DeviceName']
                params['LaunchSpecification.BlockDeviceMapping.%d.VirtualName' % (index + 1)] = \
                    mapping['VirtualName']

        #object - the RequestSpotInstancesResponse
        object = self.conn.connection.request(self.conn.path, params=params).object
        #TODO: assign name tag to running spot nodes
        nodes = self.conn._to_nodes(object, 'spotInstanceRequestSet/item')
       
        for node in nodes:
            tags = {'Name': kwargs['name']}

            try:
                self.ex_create_tags(resource=node, tags=tags)
            except Exception:
                continue

            node.name = kwargs['name']
            node.extra.update({'tags': tags})

        if len(nodes) == 1:
            return nodes[0]
        else:
            return nodes
            
    def waitUntilRunning(self, nodes, waitPeriodSeconds=5, timeoutSeconds=600):
        '''waits until the given nodes are accesible via ssh.
            invokes first ComputeContextImpl.waitUntilRunning()
        '''        
        from javaimpl.compute.ComputeContextImpl import ComputeContextImpl
        from org.askalon.jlibcloud.compute.types import NodeState as JNodeState
        if not waitPeriodSeconds:
            waitPeriodSeconds = 5
        if not timeoutSeconds:
            timeoutSeconds = 600
        start = time.time()
        end = start + timeoutSeconds
        ret = ComputeContextImpl.waitUntilRunning(self, nodes, waitPeriodSeconds, timeoutSeconds)
        if nodes:  
            ec2_list = []
            #build ec2 node list
            for node in nodes:
                #only wait for remaining ec2 nodes if this is called with other node types
                if "Amazon" in node.node.driver.name:
                    if node.getState() == JNodeState.PENDING or node.getState() == JNodeState.REBOOTING or node.getState() == JNodeState.RUNNING:
                        ec2_list.append(node.node)
            #wait for ec2 nodes
            while time.time() < end:
                ids = self._list_inaccessible_nodes(ec2_list)
                if ids:
                    time.sleep(waitPeriodSeconds)
                    continue
                else:
                    return True
        if time.time() <= end:
            return True
        else:
            return False
    
    def exWaitForPendingSpotNodes(self, waitPeriodSeconds=15, timeoutSeconds=600):
        if waitPeriodSeconds == None : waitPeriodSeconds = 15
        if timeoutSeconds == None: timeoutSeconds = 600
        return self._ex_wait_for_pending_spot_nodes(waitPeriodSeconds, timeoutSeconds)
        
    
    def getTemplateBuilder(self): 
        return EC2NodeTemplateImpl.newBuilder()
    
    #--------------------------------------------#
    # internal helper methods                    #
    #--------------------------------------------#
    def _recv_wait_set(self):
        params = {'Action': 'DescribeSpotInstanceRequests'}
        object = self.conn.connection.request(self.conn.path, params=params).object
        wait_ids = []
        for elem in object.findall(fixxpath(xpath='spotInstanceRequestSet/item', namespace=NAMESPACE)):
            inst_id = findtext(element=elem, xpath='spotInstanceRequestId',namespace=NAMESPACE)
            status = findtext(element=elem, xpath='status/code',namespace=NAMESPACE)
            if status == 'pending-evaluation' or status == 'pending-fulfillment':
                wait_ids.append(inst_id)
        return wait_ids
    
    def _ex_wait_for_pending_spot_nodes(self, wait_period, timeout):
        ''':keyword    spot_ids: A list SpotRequestIds
        :type       spot_ids:   ``list``
        pending-evaluation -> pending-fulfillment -> fulfilled
                        -> bad-parameters
        receives current spot instanceRequests. if a instance is in state pending-xxx
        the method blocks until no instance is in the state pending-xxx
        TODO: add name tag
        '''
        import time
        start = time.time()
        end = start + timeout
        
        while time.time() < end:
            if self._recv_wait_set():
                time.sleep(wait_period)
            else:
                return True
        return False
        
    def _list_inaccessible_nodes(self, node_list):
        '''returns node ids which aren't accessible via ssh yet'''
        params = {'Action' : 'DescribeInstanceStatus'}
        for index in range(len(node_list)):
            params['InstanceId.%d' % (index + 1,)] =\
                node_list[index].id
                    
        object = self.conn.connection.request(self.conn.path, params=params).object
        bad_ids = []
        for elem in object.findall(fixxpath(xpath='instanceStatusSet/item', namespace=NAMESPACE)):
                inst_id = findtext(element=elem, xpath='instanceId',namespace=NAMESPACE)
                sys_stat = findtext(element=elem, xpath='systemStatus/status',namespace=NAMESPACE)
                inst_stat = findtext(element=elem, xpath='instanceStatus/status',namespace=NAMESPACE)
                if sys_stat != 'ok' and inst_stat != 'ok':
                    bad_ids.append(inst_id)
        return bad_ids
