# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/softlayer.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/softlayer.py
#
# Apache Libcloud is licensed under the Apache 2.0 license.
# For more information, please see LICENSE and NOTICE file or:
# http://www.apache.org/licenses/LICENSE-2.0
from javaimpl.compute.ComputeContextImpl import ComputeContextImpl
from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception, jlist_str_to_pylist
from javaimpl.compute.utils import jlist_obj_to_pylist, get_property, get_property_list
from javaimpl.compute.utils import jmap_to_pymap, jlist_map_to_pylist_map
from org.askalon.jlibcloud.compute.driverSpecific.softlayer import SoftLayerNodeTemplateImpl
from javaimpl.compute.base.NodeImpl import NodeImpl
from org.askalon.jlibcloud.compute.driverSpecific.softlayer import SoftLayerComputeContext

class SoftLayerComputeContextImpl(ComputeContextImpl, SoftLayerComputeContext):
	def __init__(self, builder):
		ComputeContext.__init__(self, builder)

	def createNode(self, node_temp):
		"""Create a new SoftLayer node

        @inherits: :class:`NodeDriver.create_node`

        :keyword    ex_domain: e.g. libcloud.org
        :type       ex_domain: ``str``
        :keyword    ex_cpus: e.g. 2
        :type       ex_cpus: ``int``
        :keyword    ex_disk: e.g. 100
        :type       ex_disk: ``int``
        :keyword    ex_ram: e.g. 2048
        :type       ex_ram: ``int``
        :keyword    ex_bandwidth: e.g. 100
        :type       ex_bandwidth: ``int``
        :keyword    ex_local_disk: e.g. True
        :type       ex_local_disk: ``bool``
        :keyword    ex_datacenter: e.g. Dal05
        :type       ex_datacenter: ``str``
        :keyword    ex_os: e.g. UBUNTU_LATEST
        :type       ex_os: ``str``
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_softlayer_template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_softlayer_template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_softlayer_template(self, node_temp, kwargs):
		ex_domain = node_temp.getExDomain()
		kwargs = get_property(self, ex_domain, 'ex_domain',
					 kwargs,lambda x : x)
		ex_cpus = node_temp.getExCpus()
		kwargs = get_property(self, ex_cpus, 'ex_cpus',
					 kwargs,lambda x : int(x))
		ex_disk = node_temp.getExDisk()
		kwargs = get_property(self, ex_disk, 'ex_disk',
					 kwargs,lambda x : int(x))
		ex_ram = node_temp.getExRam()
		kwargs = get_property(self, ex_ram, 'ex_ram',
					 kwargs,lambda x : int(x))
		ex_bandwidth = node_temp.getExBandwidth()
		kwargs = get_property(self, ex_bandwidth, 'ex_bandwidth',
					 kwargs,lambda x : int(x))
		ex_local_disk = node_temp.getExLocalDisk()
		l = lambda x : True if x == 'true' or x == True else False
		kwargs = get_property(self, ex_local_disk, 'ex_local_disk',
					 kwargs,l)
		ex_datacenter = node_temp.getExDatacenter()
		kwargs = get_property(self, ex_datacenter, 'ex_datacenter',
					 kwargs,lambda x : x)
		ex_os = node_temp.getExOs()
		kwargs = get_property(self, ex_os, 'ex_os',
					 kwargs,lambda x : x)
		return kwargs

	def getTemplateBuilder(self):
		return SoftLayerNodeTemplateImpl.newBuilder()

	def exStopNode(self, node):
		try:
			if node:
				node = node.obj
			return self.conn.ex_stop_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exStartNode(self, node):
		try:
			if node:
				node = node.obj
			return self.conn.ex_start_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

