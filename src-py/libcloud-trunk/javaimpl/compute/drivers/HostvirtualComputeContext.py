# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/hostvirtual.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/hostvirtual.py
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
from org.askalon.jlibcloud.compute.driverSpecific.hostvirtual import HostVirtualComputeContext

from libcloud.compute.base import NodeAuthSSHKey, NodeAuthPassword

class HostVirtualComputeContextImpl(ComputeContextImpl, HostVirtualComputeContext):
	def __init__(self, builder):
		ComputeContext.__init__(self, builder)

	def createNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_hostvirtual_template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_hostvirtual_template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_hostvirtual_template(self, node_temp, kwargs):
		return kwargs

	def exGetNode(self, node_id):
		"""
        Get a single node.

        :param      node_id: id of the node that we need the node object for
        :type       node_id: ``str``

        :rtype: :class:`Node`
        """
		try:
			return NodeImpl(self.conn.ex_get_node(node_id))
		except Exception, ex:
			raise wrap_exception(ex)

	def exStopNode(self, node):
		"""
        Stop a node.

        :param      node: Node which should be used
        :type       node: :class:`Node`

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
        Start a node.

        :param      node: Node which should be used
        :type       node: :class:`Node`

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_start_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exProvisionNode(self, node, image, auth, isSshKey, location):
		"""
        Provision a server on a VR package and get it booted

        :keyword node: node which should be used
        :type    node: :class:`Node`

        :keyword image: The distribution to deploy on your server (mandatory)
        :type    image: :class:`NodeImage`

        :keyword auth: an SSH key or root password (mandatory)
        :type    auth: :class:`NodeAuthSSHKey` or :class:`NodeAuthPassword`

        :keyword location: which datacenter to create the server in
        :type    location: :class:`NodeLocation`

        :return: Node representing the newly built server
        :rtype: :class:`Node`
        """
		try:
			kwargs = {}
			if node:
				kwargs['node'] = node
			if image:
				kwargs['image'] = image
			if auth:
				if isSshKey:
					kwargs['auth'] = NodeAuthSSHKey(auth)
				else:
					kwargs['auth'] = NodeAuthPassword(auth)
			if location:
				kwargs['location'] = location
			return NodeImpl(self.conn.ex_provision_node(**kwargs))
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeleteNode(self, node):
		"""
        Delete a node.

        :param      node: Node which should be used
        :type       node: :class:`Node`

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_delete_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

