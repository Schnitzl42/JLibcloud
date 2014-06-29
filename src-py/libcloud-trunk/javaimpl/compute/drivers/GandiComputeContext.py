# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/gandi.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/gandi.py
#
# Apache Libcloud is licensed under the Apache 2.0 license.
# For more information, please see LICENSE and NOTICE file or:
# http://www.apache.org/licenses/LICENSE-2.0
from javaimpl.compute.ComputeContextImpl import ComputeContextImpl
from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception, jlist_str_to_pylist
from javaimpl.compute.utils import jlist_obj_to_pylist, get_property, get_property_list
from javaimpl.compute.utils import jmap_to_pymap, jlist_map_to_pylist_map
from javaimpl.compute.base.NodeImageImpl import NodeImageImpl
from javaimpl.compute.base.NodeLocationImpl import NodeLocationImpl
from javaimpl.compute.base.NodeImpl import NodeImpl
from org.askalon.jlibcloud.compute.driverSpecific.gandi import GandiNodeTemplateImpl
from javaimpl.compute.base.NodeSizeImpl import NodeSizeImpl
from org.askalon.jlibcloud.compute.driverSpecific.gandi import GandiComputeContext

class GandiComputeContextImpl(BaseGandiDriver,ComputeContextImpl, GandiComputeContext):
	def __init__(self, builder):
		BaseGandiDriver,ComputeContext.__init__(self, builder)

	def createNode(self, node_temp):
		"""
        Create a new Gandi node

        :keyword    name:   String with a name for this new node (required)
        :type       name:   ``str``

        :keyword    image:  OS Image to boot on node. (required)
        :type       image:  :class:`NodeImage`

        :keyword    location: Which data center to create a node in. If empty,
                              undefined behavior will be selected. (optional)
        :type       location: :class:`NodeLocation`

        :keyword    size:   The size of resources allocated to this node.
                            (required)
        :type       size:   :class:`NodeSize`

        :keyword    login: user name to create for login on machine (required)
        :type       login: ``str``

        :keyword    password: password for user that'll be created (required)
        :type       password: ``str``

        :keyword    inet_family: version of ip to use, default 4 (optional)
        :type       inet_family: ``int``

        :rtype: :class:`Node`
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_gandi_template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_gandi_template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_gandi_template(self, node_temp, kwargs):
		name = node_temp.getName()
		kwargs = get_property(self, name, 'name',
					 kwargs,lambda x : x)
		image = node_temp.getImage()
		kwargs = get_property(self, image, 'image',
					 kwargs,lambda x : x.obj)
		location = node_temp.getLocation()
		kwargs = get_property(self, location, 'location',
					 kwargs,lambda x : x.obj)
		size = node_temp.getSize()
		kwargs = get_property(self, size, 'size',
					 kwargs,lambda x : x.obj)
		login = node_temp.getLogin()
		kwargs = get_property(self, login, 'login',
					 kwargs,lambda x : x)
		password = node_temp.getPassword()
		kwargs = get_property(self, password, 'password',
					 kwargs,lambda x : x)
		inet_family = node_temp.getInetFamily()
		kwargs = get_property(self, inet_family, 'inet_family',
					 kwargs,lambda x : int(x))
		return kwargs

	def getTemplateBuilder(self):
		return GandiNodeTemplateImpl.newBuilder()

	def exListInterfaces(self):
		"""
        Specific method to list network interfaces

        :rtype: ``list`` of :class:`GandiNetworkInterface`
        """
		try:
			return wrap_listing(self.conn.ex_list_interfaces(), NetworkInterfaceImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListDisks(self):
		"""
        Specific method to list all disk

        :rtype: ``list`` of :class:`GandiDisk`
        """
		try:
			return wrap_listing(self.conn.ex_list_disks(), DiskImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exNodeAttachDisk(self, node, disk):
		"""
        Specific method to attach a disk to a node

        :param      node: Node which should be used
        :type       node: :class:`Node`

        :param      disk: Disk which should be used
        :type       disk: :class:`GandiDisk`

        :rtype: ``bool``
        """
		try:
			if disk:
				disk = disk.obj
			if node:
				node = node.obj
			return self.conn.ex_node_attach_disk(node, disk)
		except Exception, ex:
			raise wrap_exception(ex)

	def exNodeDetachDisk(self, node, disk):
		"""
        Specific method to detach a disk from a node

        :param      node: Node which should be used
        :type       node: :class:`Node`

        :param      disk: Disk which should be used
        :type       disk: :class:`GandiDisk`

        :rtype: ``bool``
        """
		try:
			if disk:
				disk = disk.obj
			if node:
				node = node.obj
			return self.conn.ex_node_detach_disk(node, disk)
		except Exception, ex:
			raise wrap_exception(ex)

	def exNodeAttachInterface(self, node, iface):
		"""
        Specific method to attach an interface to a node

        :param      node: Node which should be used
        :type       node: :class:`Node`


        :param      iface: Network interface which should be used
        :type       iface: :class:`GandiNetworkInterface`

        :rtype: ``bool``
        """
		try:
			if iface:
				iface = iface.obj
			if node:
				node = node.obj
			return self.conn.ex_node_attach_interface(node, iface)
		except Exception, ex:
			raise wrap_exception(ex)

	def exNodeDetachInterface(self, node, iface):
		"""
        Specific method to detach an interface from a node

        :param      node: Node which should be used
        :type       node: :class:`Node`


        :param      iface: Network interface which should be used
        :type       iface: :class:`GandiNetworkInterface`

        :rtype: ``bool``
        """
		try:
			if iface:
				iface = iface.obj
			if node:
				node = node.obj
			return self.conn.ex_node_detach_interface(node, iface)
		except Exception, ex:
			raise wrap_exception(ex)

	def exSnapshotDisk(self, disk, name=None):
		"""
        Specific method to make a snapshot of a disk

        :param      disk: Disk which should be used
        :type       disk: :class:`GandiDisk`

        :param      name: Name which should be used
        :type       name: ``str``

        :rtype: ``bool``
        """
		try:
			if not name:
				name = None
			if disk:
				disk = disk.obj
			return self.conn.ex_snapshot_disk(disk, name)
		except Exception, ex:
			raise wrap_exception(ex)

	def exUpdateDisk(self, disk, new_size=None, new_name=None):
		"""Specific method to update size or name of a disk
        WARNING: if a server is attached it'll be rebooted

        :param      disk: Disk which should be used
        :type       disk: :class:`GandiDisk`

        :param      new_size: New size
        :type       new_size: ``int``

        :param      new_name: New name
        :type       new_name: ``str``

        :rtype: ``bool``
        """
		try:
			if not new_name:
				new_name = None
			if not new_size:
				new_size = None
			if disk:
				disk = disk.obj
			return self.conn.ex_update_disk(disk, new_size, new_name)
		except Exception, ex:
			raise wrap_exception(ex)

from org.askalon.jlibcloud.compute.driverSpecific.gandi import BaseObject

class BaseObjectImpl(BaseObject):

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'state'):
			self.statep = none_check(obj.state, ' ')
		else:
			self.statep = ' '
		if hasattr(obj, 'uuid'):
			self.uuidp = none_check(obj.uuid, ' ')
		else:
			self.uuidp = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getState(self):
		return self.statep

	def toString(self):
		return self.reprp

	def getUuid(self):
		"""Unique hash for this object

        :return: ``str``

        The hash is a function of an SHA1 hash of prefix, the object's ID and
        its driver which means that it should be unique between all
        interfaces.
        TODO : to review
        >>> from libcloud.compute.drivers.dummy import DummyNodeDriver
        >>> driver = DummyNodeDriver(0)
        >>> vif = driver.create_interface()
        >>> vif.get_uuid()
        'd3748461511d8b9b0e0bfa0d4d3383a619a2bb9f'

        Note, for example, that this example will always produce the
        same UUID!
        """
		try:
			return self.obj.get_uuid()
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.gandi import IPAddress

class IPAddressImpl(BaseObjectImpl, IPAddress):
	'''
    Provide a common interface for ip addresses
	'''

	def __init__(self, obj):
		BaseObjectImpl.__init__(self, obj)
		if hasattr(obj, 'inet'):
			self.inetp = none_check(obj.inet, ' ')
		else:
			self.inetp = ' '
		if hasattr(obj, 'version'):
			self.versionp = none_check(obj.version, ' ')
		else:
			self.versionp = ' '
		if hasattr(obj, 'extra'):
			self.extrap = obj.extra, {}
		else:
			self.extrap = {}
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getInet(self):
		return self.inetp

	def getVersion(self):
		return self.versionp

	def getExtra(self):
		return self.extrap

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.gandi import NetworkInterface

class NetworkInterfaceImpl(BaseObjectImpl, NetworkInterface):
	'''
    Provide a common interface for network interfaces
	'''

	def __init__(self, obj):
		BaseObjectImpl.__init__(self, obj)
		if hasattr(obj, 'mac'):
			self.macp = none_check(obj.mac, ' ')
		else:
			self.macp = ' '
		if hasattr(obj, 'ips'):
			self.ipsp = none_check(obj.ips, ' ')
		else:
			self.ipsp = ' '
		if hasattr(obj, 'node_id'):
			self.node_idp = none_check(obj.ips, ' ')
		else:
			self.node_idp = ' '
		if hasattr(obj, 'extra'):
			self.extrap = obj.extra, {}
		else:
			self.extrap = {}
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getMac(self):
		return self.macp

	def getIps(self):
		return self.ipsp

	def getNodeId(self):
		return self.node_idp

	def getExtra(self):
		return self.extrap

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.gandi import Disk

class DiskImpl(Disk):
	'''
    Gandi disk component
	'''

	def __init__(self, obj):
		BaseObjectImpl.__init__(self, obj)
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
		if hasattr(obj, 'size'):
			self.sizep = NodeSizeImpl(obj.size)
		else:
			self.sizep = NodeSizeImpl(None)
		if hasattr(obj, 'extra'):
			self.extrap = obj.extra, {}
		else:
			self.extrap = {}
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getName(self):
		return self.namep

	def getSize(self):
		return self.sizep

	def getExtra(self):
		return self.extrap

	def toString(self):
		return self.reprp

