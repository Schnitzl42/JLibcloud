# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/elasticstack.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/elasticstack.py
#
# Apache Libcloud is licensed under the Apache 2.0 license.
# For more information, please see LICENSE and NOTICE file or:
# http://www.apache.org/licenses/LICENSE-2.0
from javaimpl.compute.ComputeContextImpl import ComputeContextImpl
from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception, jlist_str_to_pylist
from javaimpl.compute.utils import jlist_obj_to_pylist, get_property, get_property_list
from javaimpl.compute.utils import jmap_to_pymap, jlist_map_to_pylist_map
from org.askalon.jlibcloud.compute.driverSpecific.elasticstack import ElasticStackBaseNodeTemplateImpl
from javaimpl.compute.base.NodeImpl import NodeImpl
from javaimpl.compute.base.NodeSizeImpl import NodeSizeImpl
from org.askalon.jlibcloud.compute.driverSpecific.elasticstack import ElasticStackBaseComputeContext

class ElasticStackBaseComputeContextImpl(ComputeContextImpl, ElasticStackBaseComputeContext):
	def __init__(self, builder):
		ComputeContext.__init__(self, builder)

	def createNode(self, node_temp):
		"""Creates a ElasticStack instance

        @inherits: :class:`NodeDriver.create_node`

        :keyword    name: String with a name for this new node (required)
        :type       name: ``str``

        :keyword    smp: Number of virtual processors or None to calculate
                         based on the cpu speed
        :type       smp: ``int``

        :keyword    nic_model: e1000, rtl8139 or virtio
                               (if not specified, e1000 is used)
        :type       nic_model: ``str``

        :keyword    vnc_password: If set, the same password is also used for
                                  SSH access with user toor,
                                  otherwise VNC access is disabled and
                                  no SSH login is possible.
        :type       vnc_password: ``str``
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_elasticstackbase_template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_elasticstackbase_template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_elasticstackbase_template(self, node_temp, kwargs):
		name = node_temp.getName()
		kwargs = get_property(self, name, 'name',
					 kwargs,lambda x : x)
		smp = node_temp.getSmp()
		kwargs = get_property(self, smp, 'smp',
					 kwargs,lambda x : int(x))
		nic_model = node_temp.getNicModel()
		kwargs = get_property(self, nic_model, 'nic_model',
					 kwargs,lambda x : x)
		vnc_password = node_temp.getVncPassword()
		kwargs = get_property(self, vnc_password, 'vnc_password',
					 kwargs,lambda x : x)
		return kwargs

	def listSizes(self, location=None):
		try:
			if location:
				location = location.obj
			return wrap_listing(self.conn.list_sizes(location), ElasticStackNodeSizeImpl)
		except Exception, ex:
			raise wrap_exception(ex)
		
	def getTemplateBuilder(self):
		return ElasticStackBaseNodeTemplateImpl.newBuilder()

	def exSetNodeConfiguration(self, node, kwargs):
		"""
        Changes the configuration of the running server

        :param      node: Node which should be used
        :type       node: :class:`Node`

        :param      kwargs: keyword arguments
        :type       kwargs: ``dict``

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			kwargs = jmap_to_pymap(kwargs)
			return self.conn.ex_set_node_configuration(node, **kwargs)
		except Exception, ex:
			raise wrap_exception(ex)

	def exShutdownNode(self, node):
		"""
        Sends the ACPI power-down event

        :param      node: Node which should be used
        :type       node: :class:`Node`

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_shutdown_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDestroyDrive(self, drive_uuid):
		"""
        Deletes a drive

        :param      drive_uuid: Drive uuid which should be used
        :type       drive_uuid: ``str``

        :rtype: ``bool``
        """
		try:
			return self.conn.ex_destroy_drive(drive_uuid)
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.elasticstack import ElasticStackNodeSize

class ElasticStackNodeSizeImpl(NodeSizeImpl, ElasticStackNodeSize):

	def __init__(self, obj):
		NodeSizeImpl.__init__(self, obj)
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
		if hasattr(obj, 'cpu'):
			self.cpup = none_check(obj.cpu, ' ')
		else:
			self.cpup = ' '
		if hasattr(obj, 'ram'):
			self.ramp = none_check(obj.ram, -1)
		else:
			self.ramp = -1
		if hasattr(obj, 'disk'):
			self.diskp = none_check(obj.disk, -1)
		else:
			self.diskp = -1
		if hasattr(obj, 'bandwidth'):
			self.bandwidthp = none_check(obj.bandwidth, -1)
		else:
			self.bandwidthp = -1
		if hasattr(obj, 'price'):
			self.pricep = none_check(obj.price, -1.0)
		else:
			self.pricep = -1.0
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getName(self):
		return self.namep

	def getCpu(self):
		return self.cpup

	def getRam(self):
		return self.ramp

	def getDisk(self):
		return self.diskp

	def getBandwidth(self):
		return self.bandwidthp

	def getPrice(self):
		return self.pricep

	def toString(self):
		return self.reprp

