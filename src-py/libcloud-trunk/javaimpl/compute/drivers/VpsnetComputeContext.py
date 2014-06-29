# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/vpsnet.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/vpsnet.py
#
# Apache Libcloud is licensed under the Apache 2.0 license.
# For more information, please see LICENSE and NOTICE file or:
# http://www.apache.org/licenses/LICENSE-2.0
from javaimpl.compute.ComputeContextImpl import ComputeContextImpl
from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception, jlist_str_to_pylist
from javaimpl.compute.utils import jlist_obj_to_pylist, get_property, get_property_list
from javaimpl.compute.utils import jmap_to_pymap, jlist_map_to_pylist_map
from org.askalon.jlibcloud.compute.driverSpecific.vpsnet import VPSNetNodeTemplateImpl
from javaimpl.compute.base.NodeImpl import NodeImpl
from org.askalon.jlibcloud.compute.driverSpecific.vpsnet import VPSNetComputeContext

class VPSNetComputeContextImpl(ComputeContextImpl, VPSNetComputeContext):
	def __init__(self, builder):
		ComputeContext.__init__(self, builder)

	def createNode(self, node_temp):
		"""Create a new VPS.net node

        @inherits: :class:`NodeDriver.create_node`

        :keyword    ex_backups_enabled: Enable automatic backups
        :type       ex_backups_enabled: ``bool``

        :keyword    ex_fqdn:   Fully Qualified domain of the node
        :type       ex_fqdn:   ``str``
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_vpsnet_template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_vpsnet_template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_vpsnet_template(self, node_temp, kwargs):
		name = node_temp.getName()
		kwargs = get_property(self, name, 'name',
					 kwargs,lambda x : x)
		ex_backups_enabled = node_temp.getExBackupsEnabled()
		l = lambda x : True if x == 'true' or x == True else False
		kwargs = get_property(self, ex_backups_enabled, 'ex_backups_enabled',
					 kwargs,l)
		ex_fqdn = node_temp.getExFqdn()
		kwargs = get_property(self, ex_fqdn, 'ex_fqdn',
					 kwargs,lambda x : x)
		return kwargs

	def getTemplateBuilder(self):
		return VPSNetNodeTemplateImpl.newBuilder()

