# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/opennebula.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/opennebula.py
#
# Apache Libcloud is licensed under the Apache 2.0 license.
# For more information, please see LICENSE and NOTICE file or:
# http://www.apache.org/licenses/LICENSE-2.0
from javaimpl.compute.ComputeContextImpl import ComputeContextImpl
from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception, jlist_str_to_pylist
from javaimpl.compute.utils import jlist_obj_to_pylist, get_property, get_property_list
from javaimpl.compute.utils import jmap_to_pymap, jlist_map_to_pylist_map
from org.askalon.jlibcloud.compute.driverSpecific.opennebula import OpenNebula_2_0_NodeTemplateImpl
from org.askalon.jlibcloud.compute.driverSpecific.opennebula import OpenNebulaNodeTemplateImpl
from javaimpl.compute.base.NodeLocationImpl import NodeLocationImpl
from javaimpl.compute.base.NodeImpl import NodeImpl
from javaimpl.compute.base.NodeSizeImpl import NodeSizeImpl
from org.askalon.jlibcloud.compute.driverSpecific.opennebula import OpenNebulaComputeContext

class OpenNebulaComputeContextImpl(ComputeContextImpl, OpenNebulaComputeContext):
	def __init__(self, builder):
		ComputeContextImpl.__init__(self, builder)

	def createNode(self, node_temp):
		"""
        Create a new OpenNebula node.

        @inherits: :class:`NodeDriver.create_node`

        :keyword networks: List of virtual networks to which this node should
                           connect. (optional)
        :type    networks: :class:`OpenNebulaNetwork` or
            ``list`` of :class:`OpenNebulaNetwork`
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_opennebula_template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_opennebula_template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_opennebula_template(self, node_temp, kwargs):
		networks = node_temp.getNetworks()
		if networks:
			kwargs['networks'] = jlist_obj_to_pylist(networks)
		return kwargs

	def listSizes(self, location=None):
		try:
			if location:
				location = location.obj
			return wrap_listing(self.conn.list_sizes(location), OpenNebulaNodeSizeImpl)
		except Exception, ex:
			raise wrap_exception(ex)
		
	def getTemplateBuilder(self):
		return OpenNebulaNodeTemplateImpl.newBuilder()

	def exListNetworks(self, location=None):
		"""
        List virtual networks on a provider.
        
        :param location: Location from which to request a list of virtual
                         networks. (optional)
        :type  location: :class:`NodeLocation`
        
        :return: List of virtual networks available to be connected to a
                 compute node.
        :rtype:  ``list`` of :class:`OpenNebulaNetwork`
        """
		try:
			if location:
				location = location.obj
			if not location:
				location = None
			return wrap_listing(self.conn.ex_list_networks(location), OpenNebulaNetworkImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exNodeAction(self, node, action):
		"""
        Build action representation and instruct node to commit action.

        Build action representation from the compute node ID, and the
        action which should be carried out on that compute node. Then
        instruct the node to carry out that action.

        :param node: Compute node instance.
        :type  node: :class:`Node`

        :param action: Action to be carried out on the compute node.
        :type  action: ``str``

        :return: False if an HTTP Bad Request is received, else, True is
                 returned.
        :rtype:  ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_node_action(node, action)
		except Exception, ex:
			raise wrap_exception(ex)

from org.askalon.jlibcloud.compute.driverSpecific.opennebula import OpenNebula_2_0_ComputeContext

class OpenNebula_2_0_ComputeContextImpl(OpenNebulaComputeContextImpl, OpenNebula_2_0_ComputeContext):
	def __init__(self, builder):
		OpenNebulaComputeContextImpl.__init__(self, builder)

	def createNode(self, node_temp):
		"""
        Create a new OpenNebula node.

        @inherits: :class:`NodeDriver.create_node`

        :keyword networks: List of virtual networks to which this node should
                           connect. (optional)
        :type    networks: :class:`OpenNebulaNetwork` or ``list``
                           of :class:`OpenNebulaNetwork`

        :keyword context: Custom (key, value) pairs to be injected into
                          compute node XML description. (optional)
        :type    context: ``dict``

        :return: Instance of a newly created node.
        :rtype:  :class:`Node`
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_opennebula_2_0__template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_opennebula_2_0__template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_opennebula_2_0__template(self, node_temp, kwargs):
		networks = node_temp.getNetworks()
		if networks:
			kwargs['networks'] = jlist_obj_to_pylist(networks)
		context = node_temp.getContext()
		if context:
			kwargs['context'] = jmap_to_pymap(context)
		return kwargs

	def getTemplateBuilder(self):
		return OpenNebula_2_0_NodeTemplateImpl.newBuilder()

from org.askalon.jlibcloud.compute.driverSpecific.opennebula import OpenNebula_3_0_ComputeContext

class OpenNebula_3_0_ComputeContextImpl(OpenNebula_2_0_ComputeContextImpl, OpenNebula_3_0_ComputeContext):
	def __init__(self, builder):
		OpenNebula_2_0_ComputeContextImpl.__init__(self, builder)


	def exNodeSetSaveName(self, node, name):
		"""
        Build action representation and instruct node to commit action.

        Build action representation from the compute node ID, the disk image
        which will be saved, and the name under which the image will be saved
        upon shutting down the compute node.

        :param node: Compute node instance.
        :type  node: :class:`Node`

        :param name: Name under which the image should be saved after shutting
                     down the compute node.
        :type  name: ``str``

        :return: False if an HTTP Bad Request is received, else, True is
                 returned.
        :rtype:  ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_node_set_save_name(node, name)
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.opennebula import OpenNebulaNodeSize

class OpenNebulaNodeSizeImpl(NodeSizeImpl, OpenNebulaNodeSize):
	'''
    NodeSize class for the OpenNebula.org driver.
	'''

	def __init__(self, obj):
		NodeSizeImpl.__init__(self, obj)
		if hasattr(obj, 'cpu'):
			self.cpup = none_check(obj.cpu, ' ')
		else:
			self.cpup = ' '
		if hasattr(obj, 'vcpu'):
			self.vcpup = none_check(obj.vcpu, ' ')
		else:
			self.vcpup = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getCpu(self):
		return self.cpup

	def getVcpu(self):
		return self.vcpup

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.opennebula import OpenNebulaNetwork

class OpenNebulaNetworkImpl(OpenNebulaNetwork):
	'''
    Provide a common interface for handling networks of all types.

    Network objects are analogous to physical switches connecting two or
    more physical nodes together. The Network object provides the interface in
    libcloud through which we can manipulate networks in different cloud
    providers in the same way. Network objects don't actually do much directly
    themselves, instead the network driver handles the connection to the
    network.

    You don't normally create a network object yourself; instead you use
    a driver and then have that create the network for you.

    >>> from libcloud.compute.drivers.dummy import DummyNodeDriver
    >>> driver = DummyNodeDriver()
    >>> network = driver.create_network()
    >>> network = driver.list_networks()[0]
    >>> network.name
    'dummy-1'
	'''

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
		if hasattr(obj, 'address'):
			self.addressp = none_check(obj.address, ' ')
		else:
			self.addressp = ' '
		if hasattr(obj, 'size'):
			self.sizep = NodeSizeImpl(obj.size)
		else:
			self.sizep = NodeSizeImpl(None)
		if hasattr(obj, 'uuid'):
			self.uuidp = none_check(obj.uuid, ' ')
		else:
			self.uuidp = ' '
		if hasattr(obj, 'extra'):
			self.extrap = obj.extra, {}
		else:
			self.extrap = {}
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getName(self):
		return self.namep

	def getAddress(self):
		return self.addressp

	def getSize(self):
		return self.sizep

	def getExtra(self):
		return self.extrap

	def toString(self):
		return self.reprp

	def getUuid(self):
		"""
        Unique hash for this network.

        The hash is a function of an SHA1 hash of the network's ID and
        its driver which means that it should be unique between all
        networks. In some subclasses (e.g. GoGrid) there is no ID
        available so the public IP address is used. This means that,
        unlike a properly done system UUID, the same UUID may mean a
        different system install at a different time

        >>> from libcloud.network.drivers.dummy import DummyNetworkDriver
        >>> driver = DummyNetworkDriver()
        >>> network = driver.create_network()
        >>> network.get_uuid()
        'd3748461511d8b9b0e0bfa0d4d3383a619a2bb9f'

        Note, for example, that this example will always produce the
        same UUID!

        :rtype:  ``str``
        :return: Unique identifier for this instance.
        """
		try:
			return self.obj.get_uuid()
		except Exception, ex:
			raise wrap_exception(ex)

