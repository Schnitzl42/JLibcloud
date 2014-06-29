# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/joyent.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/joyent.py
#
# Apache Libcloud is licensed under the Apache 2.0 license.
# For more information, please see LICENSE and NOTICE file or:
# http://www.apache.org/licenses/LICENSE-2.0
from javaimpl.compute.ComputeContextImpl import ComputeContextImpl
from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception, jlist_str_to_pylist
from javaimpl.compute.utils import jlist_obj_to_pylist, get_property, get_property_list
from javaimpl.compute.utils import jmap_to_pymap, jlist_map_to_pylist_map
from javaimpl.compute.base.NodeImpl import NodeImpl
from org.askalon.jlibcloud.compute.driverSpecific.joyent import JoyentComputeContext

class JoyentComputeContextImpl(ComputeContextImpl, JoyentComputeContext):
	def __init__(self, builder):
		ComputeContext.__init__(self, builder)

	def createNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_joyent_template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_joyent_template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_joyent_template(self, node_temp, kwargs):
		return kwargs

	def exStopNode(self, node):
		"""
        Stop node

        :param  node: The node to be stopped
        :type   node: :class:`Node`

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_stop_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exStartNode(self, node):
		"""
        Start node

        :param  node: The node to be stopped
        :type   node: :class:`Node`

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_start_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

