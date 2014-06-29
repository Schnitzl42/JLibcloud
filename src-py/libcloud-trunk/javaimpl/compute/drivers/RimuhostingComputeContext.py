# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/rimuhosting.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/rimuhosting.py
#
# Apache Libcloud is licensed under the Apache 2.0 license.
# For more information, please see LICENSE and NOTICE file or:
# http://www.apache.org/licenses/LICENSE-2.0
from javaimpl.compute.ComputeContextImpl import ComputeContextImpl
from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception, jlist_str_to_pylist
from javaimpl.compute.utils import jlist_obj_to_pylist, get_property, get_property_list
from javaimpl.compute.utils import jmap_to_pymap, jlist_map_to_pylist_map
from org.askalon.jlibcloud.compute.driverSpecific.rimuhosting import RimuHostingNodeTemplateImpl
from javaimpl.compute.base.NodeImpl import NodeImpl
from org.askalon.jlibcloud.compute.driverSpecific.rimuhosting import RimuHostingComputeContext

class RimuHostingComputeContextImpl(ComputeContextImpl, RimuHostingComputeContext):
	def __init__(self, builder):
		ComputeContext.__init__(self, builder)

	def createNode(self, node_temp):
		"""Creates a RimuHosting instance

        @inherits: :class:`NodeDriver.create_node`

        :keyword    name: Must be a FQDN. e.g example.com.
        :type       name: ``str``

        :keyword    ex_billing_oid: If not set,
                                    a billing method is automatically picked.
        :type       ex_billing_oid: ``str``

        :keyword    ex_host_server_oid: The host server to set the VPS up on.
        :type       ex_host_server_oid: ``str``

        :keyword    ex_vps_order_oid_to_clone: Clone another VPS to use as
                                                the image for the new VPS.
        :type       ex_vps_order_oid_to_clone: ``str``

        :keyword    ex_num_ips: Number of IPs to allocate. Defaults to 1.
        :type       ex_num_ips: ``int``

        :keyword    ex_extra_ip_reason: Reason for needing the extra IPs.
        :type       ex_extra_ip_reason: ``str``

        :keyword    ex_memory_mb: Memory to allocate to the VPS.
        :type       ex_memory_mb: ``int``

        :keyword    ex_disk_space_mb: Diskspace to allocate to the VPS.
            Defaults to 4096 (4GB).
        :type       ex_disk_space_mb: ``int``

        :keyword    ex_disk_space_2_mb: Secondary disk size allocation.
                                        Disabled by default.
        :type       ex_disk_space_2_mb: ``int``

        :keyword    ex_control_panel: Control panel to install on the VPS.
        :type       ex_control_panel: ``str``
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_rimuhosting_template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_rimuhosting_template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_rimuhosting_template(self, node_temp, kwargs):
		name = node_temp.getName()
		kwargs = get_property(self, name, 'name',
					 kwargs,lambda x : x)
		ex_billing_oid = node_temp.getExBillingOid()
		kwargs = get_property(self, ex_billing_oid, 'ex_billing_oid',
					 kwargs,lambda x : x)
		ex_host_server_oid = node_temp.getExHostServerOid()
		kwargs = get_property(self, ex_host_server_oid, 'ex_host_server_oid',
					 kwargs,lambda x : x)
		ex_vps_order_oid_to_clone = node_temp.getExVpsOrderOidToClone()
		kwargs = get_property(self, ex_vps_order_oid_to_clone, 'ex_vps_order_oid_to_clone',
					 kwargs,lambda x : x)
		ex_num_ips = node_temp.getExNumIps()
		kwargs = get_property(self, ex_num_ips, 'ex_num_ips',
					 kwargs,lambda x : int(x))
		ex_extra_ip_reason = node_temp.getExExtraIpReason()
		kwargs = get_property(self, ex_extra_ip_reason, 'ex_extra_ip_reason',
					 kwargs,lambda x : x)
		ex_memory_mb = node_temp.getExMemoryMb()
		kwargs = get_property(self, ex_memory_mb, 'ex_memory_mb',
					 kwargs,lambda x : int(x))
		ex_disk_space_mb = node_temp.getExDiskSpaceMb()
		kwargs = get_property(self, ex_disk_space_mb, 'ex_disk_space_mb',
					 kwargs,lambda x : int(x))
		ex_disk_space_2_mb = node_temp.getExDiskSpace2Mb()
		kwargs = get_property(self, ex_disk_space_2_mb, 'ex_disk_space_2_mb',
					 kwargs,lambda x : int(x))
		ex_control_panel = node_temp.getExControlPanel()
		kwargs = get_property(self, ex_control_panel, 'ex_control_panel',
					 kwargs,lambda x : x)
		return kwargs

	def getTemplateBuilder(self):
		return RimuHostingNodeTemplateImpl.newBuilder()

