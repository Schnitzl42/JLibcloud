'''
Created on 11.12.2013

@author: root
'''
from java.lang import Integer, Float, Boolean, String

from org.askalon.jlibcloud.compute.wrapperInterfaces import ComputeContext
from org.askalon.jlibcloud.compute.wrapperInterfacesImpl import NodeTemplateImpl
from org.askalon.jlibcloud.compute.wrapperInterfaces.deployment import FileDeployment, ScriptDeployment
from org.askalon.jlibcloud.compute.wrapperInterfaces.deployment import ScriptFileDeployment, SshKeyDeployment
from org.askalon.jlibcloud.compute.types import Arg

from libcloud.compute.types import Provider, NodeState
from libcloud.compute.providers import get_driver
from libcloud.compute.base import NodeAuthSSHKey
from libcloud.compute.base import NodeImage, NodeSize, NodeLocation
from libcloud.compute.deployment import SSHKeyDeployment, FileDeployment, ScriptDeployment
from libcloud.compute.deployment import ScriptFileDeployment, MultiStepDeployment

from utils import wrap_listing, wrap_exception, none_check, get_property, get_property_list
from javaimpl.compute.base.KeyPairImpl import KeyPairImpl
from javaimpl.compute.base.NodeImpl import NodeImpl
from javaimpl.compute.base.NodeImageImpl import NodeImageImpl
from javaimpl.compute.base.NodeSizeImpl import NodeSizeImpl
from javaimpl.compute.base.NodeLocationImpl import NodeLocationImpl
from javaimpl.compute.base.VolumeSnapshotImpl import VolumeSnapshotImpl
from javaimpl.compute.base.StorageVolumeImpl import StorageVolumeImpl

import codecs

class ComputeContextImpl(ComputeContext):
    def __init__(self, builder):
        try:
            self.provider = builder.getProvider()
            self.driver = get_driver(self.provider)
            self.prop = builder.getProperties()

            args = ()
            kwargs = {}
            #load credentials from file
            if builder.getFilePath():
                argss = self._load_creds(builder.getFilePath())
                args = argss[0]
                kwargs = argss[1]
            else:
                #key, secret=None, secure=True, host=None, port=None,
                #api_version=None
                key = builder.getIdentity()
                secret = builder.getAuthentication()
                #remove byte order mark
                if secret.startswith(u'\ufeff'):
                    secret = secret.replace(u'\ufeff', '')
                host = builder.getHost()
                port = builder.getPort()
                secure = builder.isSecure()
                api_version = builder.getApiVersion()
                endpoint = builder.getEndpoint()
                #set args
                #id, auth, endpoint /abiquo
                if endpoint:
                    args = (key, secret,
                            endpoint)
                #if (identity, authentification) was called 
                elif secret:
                    args = (key, secret)
                #if no secret is required
                else:
                    args = (key)
                #set kwargs
                if builder.getExForceAuthUrl():
                    kwargs['ex_force_auth_url'] = builder.getExForceAuthUrl()
                if builder.getExForceBaseUrl():
                    kwargs['ex_force_base_url'] = builder.getExForceBaseUrl()
                if builder.getExForceAuthVersion():
                    kwargs['ex_force_auth_version'] = builder.getExForceAuthVersion()
                if builder.getExForceAuthToken():
                    kwargs['ex_force_auth_token'] = builder.getExForceAuthToken()
                if builder.getExTenantName():
                    kwargs['ex_tenant_name'] = builder.getExTenantName()
                if builder.getExForceServiceType():
                    kwargs['ex_force_service_type'] = builder.getExForceServiceType() 
                if builder.getExForceServiceName():
                    kwargs['ex_force_service_name'] = builder.getExForceServiceName() 
                if builder.getExForceServiceRegion():
                    kwargs['ex_force_service_region'] = builder.getExForceServiceRegion()  
                if host:
                    kwargs['host'] = host
                if port:
                    kwargs['port'] = port
                if api_version:
                    kwargs['api_version'] = api_version
                if secure != None:
                    kwargs['secure'] = secure
            if builder.getRegion():
                kwargs['region'] = builder.getRegion()
            self.conn = self.driver(*args, **kwargs)
        except Exception, ex:
            wrap_exception(ex)
    
    
    #--------------------------------------------#
    # methods which directly operate on nodes    #
    #--------------------------------------------#
    def createNode(self, node_temp):
        try:
            kwargs = self._eval_template(node_temp)
            return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
        except Exception, ex:
            wrap_exception(ex)
        
    def deployNode(self, node_temp):
        try:
            kwargs = self._eval_template(node_temp)
            kwargs = self._eval_deploy_template(node_temp, kwargs)
            return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
        except Exception, ex:
            wrap_exception(ex)
        
    def destroyNode(self, node):
        try:
            return node.destroy()
        except Exception, ex:
            wrap_exception(ex)
    
    def rebootNode(self, node):
        try:
            return node.reboot()
        except Exception, ex:
            wrap_exception(ex)
    
    def waitUntilRunning(self, nodes, waitPeriodSeconds=5, timeoutSeconds=600):
        from org.askalon.jlibcloud.compute.types import NodeState as JNodeState
        if not waitPeriodSeconds:
            waitPeriodSeconds = 5
        if not timeoutSeconds:
            timeoutSeconds = 600
        ssh_interface='public_ips'
        force_ipv4=True
        try:
            node_list = []
            if nodes:
                for node in nodes:
                    if node.getState() == JNodeState.PENDING or node.getState() == JNodeState.REBOOTING:
                        node_list.append(node.node)
            self.conn.wait_until_running(node_list, wait_period=waitPeriodSeconds, timeout=timeoutSeconds, 
                                         ssh_interface=ssh_interface, force_ipv4=force_ipv4)
            return True
        except Exception:
            return False
   
    
    #--------------------------------------------#
    # methods for listing information            #
    #--------------------------------------------#
    def listNodes(self):
        try:
            return wrap_listing(self.conn.list_nodes(), NodeImpl)
        except Exception, ex:
           raise wrap_exception(ex)
       
    def listNodesMatching(self, nodes):
        try:
            return self._listNodesMatching(nodes, NodeImpl)
        except Exception, ex:
           raise wrap_exception(ex)
    
    def _listNodesMatching(self, nodes, node_class):
        all_nodes = self.conn.list_nodes()
        target_nodes = []
        for node in nodes:
            for curr_node in all_nodes:
                if node.getID() == curr_node.id:
                    target_nodes.append(node_class(curr_node))
        return target_nodes
            
    def listImages(self, location=None):
        try:
            if location:
                location = location.obj
            return wrap_listing(self.conn.list_images(location), NodeImageImpl)
        except Exception, ex:
            raise wrap_exception(ex)
    
    def listSizes(self, location=None):
        try:
            if location:
                return wrap_listing(self.conn.list_sizes(location.obj), NodeSizeImpl)
            return wrap_listing(self.conn.list_sizes(), NodeSizeImpl)
        except Exception, ex:
            raise wrap_exception(ex)

    def listLocations(self):
        try:
            return wrap_listing(self.conn.list_locations(), NodeLocationImpl)
        except Exception, ex:
            wrap_exception(ex)
            
    def listVolumes(self):
        try:
            return wrap_listing(self.conn.list_volumes(), StorageVolumeImpl)
        except Exception, ex:
            wrap_exception(ex)
            
    def listVolumeSnapshots(self, volume):
        try:
            return wrap_listing(self.conn.list_volume_snapshots(volume.obj), VolumeSnapshotImpl)
        except Exception, ex:
            wrap_exception(ex)
            
    def listKeyPairs(self):
        try:
            return wrap_listing(self.conn.list_key_pairs(), KeyPairImpl)
        except Exception, ex:
            wrap_exception(ex)
            
    def getNodeStateMap(self):
        from org.askalon.jlibcloud.compute.types import NodeState;
        try:
            state_map = self.driver.NODE_STATE_MAP
            for k in state_map.iterkeys():
                if state_map[k] == 0:
                    state_map[k] = NodeState.RUNNING
                elif state_map[k] == 1:
                    state_map[k] = NodeState.REBOOTING
                elif state_map[k] == 2:
                    state_map[k] = NodeState.TERMINATED
                elif state_map[k] == 3:
                    state_map[k] = NodeState.PENDING
                elif state_map[k] == 4:
                    state_map[k] = NodeState.UNKNOWN
                elif state_map[k] == 5:
                    state_map[k] = NodeState.STOPPED
            return state_map
        except Exception, ex:
            wrap_exception(ex)
            
    #--------------------------------------------#
    # Volume specific methods                    #
    #--------------------------------------------#
    def createVolume(self, size, name, location=None, snapshot=None):
        try:
            if location:
                location = location.location
            if snapshot:
                snapshot = snapshot.snapshot
            return StorageVolumeImpl(self.conn.create_volume(size, name, location, snapshot))
        except Exception, ex:
            wrap_exception(ex)
            
    def attachVolume(self, node, volume, device=None):
        try:
            return self.conn.attach_volume(node.node, volume.volume, device)
        except Exception, ex:
            wrap_exception(ex)
            
    def detachVolume(self, volume):
        try:
            return self.conn.detach_volume(volume.volume)
        except Exception, ex:
            wrap_exception(ex)
            
    def destroyVolume(self, volume):
        try:
            return self.conn.destroy_volume(volume.volume)
        except Exception, ex:
            wrap_exception(ex)
            
    def createVolumeSnapshot(self, volume, name):
        try:
            return VolumeSnapshotImpl(self.conn.create_volume_snapshot(volume.obj, name))
        except Exception, ex:
            wrap_exception(ex)
            
    def destroyVolumeSnapshot(self, snapshot):
        try:
            return self.conn.destroy_volume_snapshot(snapshot.snapshot)
        except Exception, ex:
            wrap_exception(ex)
            
    #--------------------------------------------#
    #SSH key pair specific methods (should be in official 0.14 release)
    #--------------------------------------------#
    def getKeyPair(self, name):
        try:
            return KeyPairImpl(self.conn.get_key_pair(name))
        except Exception, ex:
            wrap_exception(ex)
            
    def createKeyPair(self, name):
        try:
            return KeyPairImpl(self.conn.create_key_pair(name))
        except Exception, ex:
            wrap_exception(ex)
               
    def importKeyPairFromString(self, name, key_material):
        try:
            return KeyPairImpl(self.conn.import_key_pair_from_string(name, key_material))
        except Exception, ex:
            wrap_exception(ex)
            
    def importKeyPairFromFile(self, name, key_path):
        try:
            return KeyPairImpl(self.conn.import_key_pair_from_file(name, key_path))
        except Exception, ex:
            wrap_exception(ex)
            
    def deleteKeyPair(self, key_pair):
        try:
            self.conn.delete_key_pair(key_pair.key_pair)
        except Exception, ex:
            wrap_exception(ex)
            
    #--------------------------------------------#
    # non libcloud methods                       #
    #--------------------------------------------#
    def setNodeProperties(self, filepath):
        try:
            from org.askalon.jlibcloud.compute.core.Utils import loadPropertiesFrom
            self.prop = loadPropertiesFrom(filepath)
        except Exception, ex:
            wrap_exception(ex)
    
    def getTemplateBuilder(self): 
        return NodeTemplateImpl.newBuilder();
    
    def getProviderName(self):
        return self.provider
    
    #--------------------------------------------#
    # internal helper methods                    #
    #--------------------------------------------#
    def _eval_template(self, node_temp):
        kwargs = {}
        size_id = node_temp.getSizeId()
        size = node_temp.getSize()
        image_id = node_temp.getImageId()
        image = node_temp.getImage()
        #check if None attributes can be loaded from self.prop
        if size_id == None and self.prop != None:
            size_id = self.prop.getProperty('size_id')
        if image_id == None and self.prop != None:
            image_id = self.prop.getProperty('image_id')
        #if size or image are still None throw an Exception    
        if (size_id == None and 
            size == None) or (image_id == None and image == None):
            raise AttributeError('size or image not set!')
        #assign values
        name = node_temp.getNodeName()
        kwargs = get_property(self, name, 'name'
                              , kwargs, lambda x : x)
        if size_id:
            kwargs['size'] = NodeSize(id=size_id, name='', ram=-1, disk=-1, bandwidth=-1,
                                      price=1.0, driver=self.conn, extra={})
        else:
            kwargs['size'] = size.size
        if image_id:
            kwargs['image'] =  NodeImage(id=image_id, name='', driver=self.conn, extra={})
        else:
            kwargs['image'] = image.image
        
        location = node_temp.getLocationId()
        if location == None and self.prop != None:
            location= self.prop.getProperty('location_id')
        if location:
            kwargs['location'] = NodeLocation(id=location, name='', country='', driver=self.conn)
        elif node_temp.getLocation():
            kwargs['location'] = node_temp.getLocation().location
        else:
            pass
        #check keyName
        ex_keyname = node_temp.getKeyPair()
        kwargs = get_property(self, ex_keyname, 'ex_keyname'
                              , kwargs, lambda x : x)
        #inspect 'authentication'
        if node_temp.getAuthPassword():
            from libcloud.compute.base import NodeAuthPassword
            kwargs['auth'] = NodeAuthPassword(node_temp.getAuthPassword())
        elif node_temp.getAuthSshKey():
            from libcloud.compute.base import NodeAuthSSHKey
            kwargs['auth'] = NodeAuthSSHKey(node_temp.getAuthSshKey()) 
        return kwargs    
        

    def _parse_arg_list(self, args):
        #parse args
        arg_lst = []
        for arg in args:
            if arg.isInt():
                arg_lst.append(arg.getInt())
            elif arg.isFloat():
                arg_lst.append(arg.getFloat())
            elif arg.isString():
                arg_lst.append(arg.getString())
        return arg_lst

    def _eval_deploy_template(self, node_temp, kwargs):
        #deploy_node params
        ssh_username = node_temp.getSshUsername()
        kwargs = get_property(self, ssh_username, 'ssh_username'
                              , kwargs, lambda x : x)
        ssh_alternate_usernames = node_temp.getSshAlternateUsernames()
        kwargs = get_property_list(self, ssh_alternate_usernames, 'ssh_alternate_usernames'
                              , kwargs, lambda x : x)
        ssh_port = node_temp.getSshPort()
        kwargs = get_property(self, ssh_port, 'ssh_port'
                              , kwargs, lambda x : int(x))
        ssh_timeout = node_temp.getSshTimeout()
        kwargs = get_property(self, ssh_timeout, 'ssh_timeout'
                              , kwargs, lambda x : float(x))
        ssh_key = node_temp.getSshKey()
        kwargs = get_property(self, ssh_key, 'ssh_key'
                              , kwargs, lambda x : x)
        timeout = node_temp.getTimeout()
        kwargs = get_property(self, timeout, 'timeout'
                              , kwargs, lambda x : int(x))
        max_tries = node_temp.getMaxTries()
        kwargs = get_property(self, max_tries, 'max_tries'
                              , kwargs, lambda x : int(x))
        ssh_interface = node_temp.getSshInterface()
        kwargs = get_property(self, ssh_interface, 'ssh_interface'
                              , kwargs, lambda x : x)
        #get the deployment classes
        deployments = node_temp.getDeploy()
        if deployments:
            msd = MultiStepDeployment()
            for deploy in deployments:
                if isinstance(deploy, SshKeyDeployment):
                    msd.add(SSHKeyDeployment(str(deploy.getKey())))
                elif isinstance(deploy, FileDeployment):
                    msd.add(FileDeployment(deploy.getSource(), deploy.getTarget()))
                elif isinstance(deploy, ScriptDeployment):
                    args = deploy.getArgs()
                    arg_lst = self._parse_arg_list(args)
                    msd.add(ScriptDeployment(deploy.getScriptContent(), arg_lst, 
                                             deploy.getName(), deploy.getDelete()))
                elif isinstance(deploy, ScriptFileDeployment):
                    args = deploy.getArgs()
                    arg_lst = self._parse_arg_list(args)
                    msd.add(ScriptFileDeployment(deploy.getScriptPath(), arg_lst,
                                                  deploy.getName(), deploy.getDelete()))
            kwargs['deploy'] = msd
        return kwargs
        
    def _load_creds(self, file_path):
        if file_path == 'std':
            try:
                import secrets
            except ImportError:
                raise ImportError("Couldn't import secrets.py from within the jar")
        else:
            import os.path
            path = os.path.normpath(os.path.abspath(file_path))
            if os.path.isfile(path):
                try:
                    #sys.path.append(os.path.dirname(path))
                    #import secrets
                    import imp
                    secrets = imp.load_source('secrets', path)
                except ImportError:
                    raise ImportError("Couldn't import secrets.py from %s" % (path))
            else:
                raise ImportError("%s - is no valid path" % (path))
        provider_name = self.provider.upper()
        args = getattr(secrets, provider_name + '_PARAMS', ())
        kwargs = getattr(secrets, provider_name + '_KEYWORD_PARAMS', {})
        return (args, kwargs)
        #else:
        #    raise ImportError("Invalid filepath: %s to secrets.py" % (path))
        
 