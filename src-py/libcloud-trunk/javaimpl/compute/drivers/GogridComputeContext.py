# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/gogrid.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/gogrid.py
#
# Apache Libcloud is licensed under the Apache 2.0 license.
# For more information, please see LICENSE and NOTICE file or:
# http://www.apache.org/licenses/LICENSE-2.0
from javaimpl.compute.ComputeContextImpl import ComputeContextImpl
from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception, jlist_str_to_pylist
from javaimpl.compute.utils import jlist_obj_to_pylist, get_property, get_property_list
from javaimpl.compute.utils import jmap_to_pymap, jlist_map_to_pylist_map
from org.askalon.jlibcloud.compute.driverSpecific.gogrid import GoGridNodeTemplateImpl
from javaimpl.compute.base.NodeImageImpl import NodeImageImpl
from javaimpl.compute.base.NodeLocationImpl import NodeLocationImpl
from javaimpl.compute.base.NodeImpl import NodeImpl
from javaimpl.compute.base.NodeSizeImpl import NodeSizeImpl
from org.askalon.jlibcloud.compute.driverSpecific.gogrid import GoGridComputeContext

class GoGridComputeContextImpl(BaseGoGridDriver,ComputeContextImpl, GoGridComputeContext):
	def __init__(self, builder):
		BaseGoGridDriver,ComputeContext.__init__(self, builder)

	def createNode(self, node_temp):
		"""Create a new GoGird node

        @inherits: :class:`NodeDriver.create_node`

        :keyword    ex_description: Description of a Node
        :type       ex_description: ``str``

        :keyword    ex_ip: Public IP address to use for a Node. If not
                    specified, first available IP address will be picked
        :type       ex_ip: ``str``

        :rtype: :class:`GoGridNode`
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_gogrid_template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_gogrid_template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_gogrid_template(self, node_temp, kwargs):
		ex_description = node_temp.getExDescription()
		kwargs = get_property(self, ex_description, 'ex_description',
					 kwargs,lambda x : x)
		ex_ip = node_temp.getExIp()
		kwargs = get_property(self, ex_ip, 'ex_ip',
					 kwargs,lambda x : x)
		return kwargs

	def listNodes(self):
		try:
			return wrap_listing(self.conn.list_nodes(), GoGridNodeImpl)
		except Exception, ex:
		   raise wrap_exception(ex)
		  
	def listNodesMatching(self, nodes):
		try:
			return self._listNodesMatching(nodes, CloudFramesNodeImpl)
		except Exception, ex:
			wrap_exception(ex)
		  
	def getTemplateBuilder(self):
		return GoGridNodeTemplateImpl.newBuilder()

	def exCreateNodeNowait(self, name, size, image, ex_description, 
					ex_ip):
		"""Don't block until GoGrid allocates id for a node
        but return right away with id == None.

        The existance of this method is explained by the fact
        that GoGrid assigns id to a node only few minutes after
        creation.


        :keyword    name:   String with a name for this new node (required)
        :type       name:   ``str``

        :keyword    size:   The size of resources allocated to this node .
                            (required)
        :type       size:   :class:`NodeSize`

        :keyword    image:  OS Image to boot on node. (required)
        :type       image:  :class:`NodeImage`

        :keyword    ex_description: Description of a Node
        :type       ex_description: ``str``

        :keyword    ex_ip: Public IP address to use for a Node. If not
            specified, first available IP address will be picked
        :type       ex_ip: ``str``

        :rtype: :class:`GoGridNode`
        """
		try:
			kwargs = {}
			if name:
				kwargs['name'] = name
			if size:
				kwargs['size'] = size
			if image:
				kwargs['image'] = image
			if ex_description:
				kwargs['ex_description'] = ex_description
			if ex_ip:
				kwargs['ex_ip'] = ex_ip
			return GoGridNodeImpl(self.conn.ex_create_node_nowait(**kwargs))
		except Exception, ex:
			raise wrap_exception(ex)

	def exSaveImage(self, node, name):
		"""Create an image for node.

        Please refer to GoGrid documentation to get info
        how prepare a node for image creation:

        http://wiki.gogrid.com/wiki/index.php/MyGSI

        :keyword    node: node to use as a base for image
        :type       node: :class:`GoGridNode`

        :keyword    name: name for new image
        :type       name: ``str``

        :rtype: :class:`NodeImage`
        """
		try:
			if node:
				node = node.obj
			return NodeImageImpl(self.conn.ex_save_image(node, name))
		except Exception, ex:
			raise wrap_exception(ex)

	def exEditNode(self, node, size, ex_description):
		"""Change attributes of a node.

        :keyword    node: node to be edited (required)
        :type       node: :class:`GoGridNode`

        :keyword    size: new size of a node (required)
        :type       size: :class:`NodeSize`

        :keyword    ex_description: new description of a node
        :type       ex_description: ``str``

        :rtype: :class:`Node`
        """
		try:
			kwargs = {}
			if node:
				kwargs['node'] = node
			if size:
				kwargs['size'] = size
			if ex_description:
				kwargs['ex_description'] = ex_description
			return NodeImpl(self.conn.ex_edit_node(**kwargs))
		except Exception, ex:
			raise wrap_exception(ex)

	def exEditImage(self, image, public_, ex_description, name):
		"""Edit metadata of a server image.

        :keyword    image: image to be edited (required)
        :type       image: :class:`NodeImage`

        :keyword    public: should be the image public (required)
        :type       public: ``bool``

        :keyword    ex_description: description of the image (optional)
        :type       ex_description: ``str``

        :keyword    name: name of the image
        :type       name: ``str``

        :rtype: :class:`NodeImage`
        """
		try:
			kwargs = {}
			if image:
				kwargs['image'] = image
			if public_:
				kwargs['public'] = public_
			if ex_description:
				kwargs['ex_description'] = ex_description
			if name:
				kwargs['name'] = name
			return NodeImageImpl(self.conn.ex_edit_image(**kwargs))
		except Exception, ex:
			raise wrap_exception(ex)

	def exListIps(self, public_, assigned, location):
		"""Return list of IP addresses assigned to
        the account.

        :keyword    public: set to True to list only
                    public IPs or False to list only
                    private IPs. Set to None or not specify
                    at all not to filter by type
        :type       public: ``bool``

        :keyword    assigned: set to True to list only addresses
                    assigned to servers, False to list unassigned
                    addresses and set to None or don't set at all
                    not no filter by state
        :type       assigned: ``bool``

        :keyword    location: filter IP addresses by location
        :type       location: :class:`NodeLocation`

        :rtype: ``list`` of :class:`GoGridIpAddress`
        """
		try:
			kwargs = {}
			if public_:
				kwargs['public'] = public_
			if assigned:
				kwargs['assigned'] = assigned
			if location:
				kwargs['location'] = location
			return wrap_listing(self.conn.ex_list_ips(**kwargs), GoGridIpAddressImpl)
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.gogrid import GoGridNode

class GoGridNodeImpl(NodeImpl, GoGridNode):
	
	def __init__(self, obj):
		NodeImpl.__init__(self, obj)

	def getUuid(self):
		try:
			return self.obj.get_uuid()
		except Exception, ex:
			raise wrap_exception(ex)
		
		
from org.askalon.jlibcloud.compute.driverSpecific.gogrid import GoGridIpAddress

class GoGridIpAddressImpl(GoGridIpAddress):
	'''
    IP Address
	'''

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'ip'):
			self.ipp = none_check(obj.ip, ' ')
		else:
			self.ipp = ' '
		if hasattr(obj, 'public'):
			self.publicp = none_check(obj.public, ' ')
		else:
			self.publicp = ' '
		if hasattr(obj, 'state'):
			self.statep = none_check(obj.state, ' ')
		else:
			self.statep = ' '
		if hasattr(obj, 'subnet'):
			self.subnetp = none_check(obj.subnet, ' ')
		else:
			self.subnetp = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getIp(self):
		return self.ipp

	def getPublic(self):
		return self.publicp

	def getState(self):
		return self.statep

	def getSubnet(self):
		return self.subnetp

	def toString(self):
		return self.reprp



