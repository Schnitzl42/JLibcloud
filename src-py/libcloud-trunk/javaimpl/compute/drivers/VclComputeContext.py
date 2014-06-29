# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/vcl.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/vcl.py
#
# Apache Libcloud is licensed under the Apache 2.0 license.
# For more information, please see LICENSE and NOTICE file or:
# http://www.apache.org/licenses/LICENSE-2.0
from javaimpl.compute.ComputeContextImpl import ComputeContextImpl
from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception, jlist_str_to_pylist
from javaimpl.compute.utils import jlist_obj_to_pylist, get_property, get_property_list
from javaimpl.compute.utils import jmap_to_pymap, jlist_map_to_pylist_map
from javaimpl.compute.base.NodeImpl import NodeImpl
from org.askalon.jlibcloud.compute.driverSpecific.vcl import VCLNodeTemplateImpl
from org.askalon.jlibcloud.compute.driverSpecific.vcl import VCLComputeContext

class VCLComputeContextImpl(ComputeContextImpl, VCLComputeContext):
	def __init__(self, builder):
		ComputeContext.__init__(self, builder)

	def createNode(self, node_temp):
		"""Create a new VCL reservation
        size and name ignored, image is the id from list_image

        @inherits: :class:`NodeDriver.create_node`

        :keyword    image: image is the id from list_image
        :type       image: ``str``

        :keyword    start: start time as unix timestamp
        :type       start: ``str``

        :keyword    length: length of time in minutes
        :type       length: ``str``
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_vcl_template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_vcl_template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_vcl_template(self, node_temp, kwargs):
		image = node_temp.getImageId()
		kwargs = get_property(self, image, 'image',
					 kwargs,lambda x : x)
		start = node_temp.getStart()
		kwargs = get_property(self, start, 'start',
					 kwargs,lambda x : x)
		length = node_temp.getLength()
		kwargs = get_property(self, length, 'length',
					 kwargs,lambda x : x)
		return kwargs

	def getTemplateBuilder(self):
		return VCLNodeTemplateImpl.newBuilder()

	def exUpdateNodeAccess(self, node, ipaddr):
		"""
        Update the remote ip accessing the node.

        :param node: the reservation node to update
        :type  node: :class:`Node`

        :param ipaddr: the ipaddr used to access the node
        :type  ipaddr: ``str``

        :return: node with updated information
        :rtype: :class:`Node`
        """
		try:
			if node:
				node = node.obj
			return NodeImpl(self.conn.ex_update_node_access(node, ipaddr))
		except Exception, ex:
			raise wrap_exception(ex)

	def exExtendRequestTime(self, node, minutes):
		"""
        Time in minutes to extend the requested node's reservation time

        :param node: the reservation node to update
        :type  node: :class:`Node`

        :param minutes: the number of mintes to update
        :type  minutes: ``str``

        :return: true on success, throws error on failure
        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_extend_request_time(node, minutes)
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetRequestEndTime(self, node):
		"""
        Get the ending time of the node reservation.

        :param node: the reservation node to update
        :type  node: :class:`Node`

        :return: unix timestamp
        :rtype: ``int``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_get_request_end_time(node)
		except Exception, ex:
			raise wrap_exception(ex)

