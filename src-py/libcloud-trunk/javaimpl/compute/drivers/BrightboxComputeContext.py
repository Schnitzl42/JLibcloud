# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/brightbox.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/brightbox.py
#
# Apache Libcloud is licensed under the Apache 2.0 license.
# For more information, please see LICENSE and NOTICE file or:
# http://www.apache.org/licenses/LICENSE-2.0
from javaimpl.compute.ComputeContextImpl import ComputeContextImpl
from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception, jlist_str_to_pylist
from javaimpl.compute.utils import jlist_obj_to_pylist, get_property, get_property_list
from javaimpl.compute.utils import jmap_to_pymap, jlist_map_to_pylist_map
from javaimpl.compute.base.NodeImpl import NodeImpl
from org.askalon.jlibcloud.compute.driverSpecific.brightbox import BrightboxNodeTemplateImpl
from org.askalon.jlibcloud.compute.driverSpecific.brightbox import BrightboxComputeContext

class BrightboxComputeContextImpl(ComputeContextImpl, BrightboxComputeContext):
	def __init__(self, builder):
		ComputeContextImpl.__init__(self, builder)

	def createNode(self, node_temp):
		"""Create a new Brightbox node

        Reference: https://api.gb1.brightbox.com/1.0/#server_create_server

        @inherits: :class:`NodeDriver.create_node`

        :keyword    ex_userdata: User data
        :type       ex_userdata: ``str``

        :keyword    ex_servergroup: Name or list of server group ids to
                                    add server to
        :type       ex_servergroup: ``str`` or ``list`` of ``str``
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_brightbox_template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_brightbox_template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_brightbox_template(self, node_temp, kwargs):
		ex_userdata = node_temp.getExUserdata()
		kwargs = get_property(self, ex_userdata, 'ex_userdata',
					 kwargs,lambda x : x)
		ex_servergroup = node_temp.getExServergroup()
		kwargs = get_property(self, ex_servergroup, 'ex_servergroup',
					 kwargs,lambda x : x)
		return kwargs

	def getTemplateBuilder(self):
		return BrightboxNodeTemplateImpl.newBuilder()

	def exListCloudIps(self):
		"""
        List Cloud IPs

        @note: This is an API extension for use on Brightbox

        :rtype: ``list`` of ``dict``
        """
		try:
			return self.conn.ex_list_cloud_ips()
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateCloudIp(self, reverse_dns=None):
		"""
        Requests a new cloud IP address for the account

        @note: This is an API extension for use on Brightbox

        :param      reverse_dns: Reverse DNS hostname
        :type       reverse_dns: ``str``

        :rtype: ``dict``
        """
		try:
			if not reverse_dns:
				reverse_dns = None
			return self.conn.ex_create_cloud_ip(reverse_dns)
		except Exception, ex:
			raise wrap_exception(ex)

	def exUpdateCloudIp(self, cloud_ip_id, reverse_dns):
		"""
        Update some details of the cloud IP address

        @note: This is an API extension for use on Brightbox

        :param  cloud_ip_id: The id of the cloud ip.
        :type   cloud_ip_id: ``str``

        :param      reverse_dns: Reverse DNS hostname
        :type       reverse_dns: ``str``

        :rtype: ``dict``
        """
		try:
			return self.conn.ex_update_cloud_ip(cloud_ip_id, reverse_dns)
		except Exception, ex:
			raise wrap_exception(ex)

	def exMapCloudIp(self, cloud_ip_id, interface_id):
		"""
        Maps (or points) a cloud IP address at a server's interface
        or a load balancer to allow them to respond to public requests

        @note: This is an API extension for use on Brightbox

        :param  cloud_ip_id: The id of the cloud ip.
        :type   cloud_ip_id: ``str``

        :param  interface_id: The Interface ID or LoadBalancer ID to
                              which this Cloud IP should be mapped to
        :type   interface_id: ``str``

        :return: True if the mapping was successful.
        :rtype: ``bool``
        """
		try:
			return self.conn.ex_map_cloud_ip(cloud_ip_id, interface_id)
		except Exception, ex:
			raise wrap_exception(ex)

	def exUnmapCloudIp(self, cloud_ip_id):
		"""
        Unmaps a cloud IP address from its current destination making
        it available to remap. This remains in the account's pool
        of addresses

        @note: This is an API extension for use on Brightbox

        :param  cloud_ip_id: The id of the cloud ip.
        :type   cloud_ip_id: ``str``

        :return: True if the unmap was successful.
        :rtype: ``bool``
        """
		try:
			return self.conn.ex_unmap_cloud_ip(cloud_ip_id)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDestroyCloudIp(self, cloud_ip_id):
		"""
        Release the cloud IP address from the account's ownership

        @note: This is an API extension for use on Brightbox

        :param  cloud_ip_id: The id of the cloud ip.
        :type   cloud_ip_id: ``str``

        :return: True if the unmap was successful.
        :rtype: ``bool``
        """
		try:
			return self.conn.ex_destroy_cloud_ip(cloud_ip_id)
		except Exception, ex:
			raise wrap_exception(ex)

