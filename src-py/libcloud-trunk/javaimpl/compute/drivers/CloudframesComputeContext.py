# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/cloudframes.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/cloudframes.py
#
# Apache Libcloud is licensed under the Apache 2.0 license.
# For more information, please see LICENSE and NOTICE file or:
# http://www.apache.org/licenses/LICENSE-2.0
from javaimpl.compute.ComputeContextImpl import ComputeContextImpl
from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception, jlist_str_to_pylist
from javaimpl.compute.utils import jlist_obj_to_pylist, get_property, get_property_list
from javaimpl.compute.utils import jmap_to_pymap, jlist_map_to_pylist_map
from org.askalon.jlibcloud.compute.driverSpecific.cloudframes import CloudFramesNodeTemplateImpl
from javaimpl.compute.base.NodeImageImpl import NodeImageImpl
from javaimpl.compute.base.NodeLocationImpl import NodeLocationImpl
from javaimpl.compute.base.NodeImpl import NodeImpl
from javaimpl.compute.base.NodeSizeImpl import NodeSizeImpl
from org.askalon.jlibcloud.compute.driverSpecific.cloudframes import CloudFramesComputeContext

class CloudFramesComputeContextImpl(ComputeContextImpl, CloudFramesComputeContext):
	def __init__(self, builder):
		ComputeContextImpl.__init__(self, builder)

	def createNode(self, node_temp):
		"""
        Creates a new node, by cloning the template provided.

        If no location object is passed, a random location will be used.


        :param   image:           The template to be cloned (required)
        :type    image:           ``list`` of :class:`NodeImage`

        :param   name:            The name for the new node (required)
        :type    name:            ``str``

        :param   size:            The size of the new node (required)
        :type    size:            ``list`` of :class:`NodeSize`

        :param   location:        The location to create the new node
        :type    location:        ``list`` of :class:`NodeLocation`

        :param   default_gateway: The default gateway to be used
        :type    default_gateway: ``str``

        :param   extra:           Additional requirements (extra disks fi.)
        :type    extra:           ``dict``


        :returns: ``list`` of :class:`Node` -- The newly created Node object

        :raises: CloudFramesException
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_cloudframes_template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_cloudframes_template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_cloudframes_template(self, node_temp, kwargs):
		image = node_temp.getImage()
		if image:
			kwargs['image'] = jlist_obj_to_pylist(image)
		name = node_temp.getName()
		kwargs = get_property(self, name, 'name',
					 kwargs,lambda x : x)
		size = node_temp.getSize()
		if size:
			kwargs['size'] = jlist_obj_to_pylist(size)
		location = node_temp.getLocation()
		if location:
			kwargs['location'] = jlist_obj_to_pylist(location)
		default_gateway = node_temp.getDefaultGateway()
		kwargs = get_property(self, default_gateway, 'default_gateway',
					 kwargs,lambda x : x)
		extra = node_temp.getExtra()
		if extra:
			kwargs['extra'] = jmap_to_pymap(extra)
		return kwargs

	def listNodes(self):
		try:
			return wrap_listing(self.conn.list_nodes(), CloudFramesNodeImpl)
		except Exception, ex:
			wrap_exception(ex)
			
	def listNodesMatching(self, nodes):
		try:
			return self._listNodesMatching(nodes, CloudFramesNodeImpl)
		except Exception, ex:
			wrap_exception(ex)
			
	def listSizes(self, location=None):
		try:
			if location:
				location = location.obj
			return wrap_listing(self.conn.list_sizes(location), CloudFramesNodeSizeImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def getTemplateBuilder(self):
		return CloudFramesNodeTemplateImpl.newBuilder()

	def exSnapshotNode(self, node, label='', description=''):
		try:
			if not description:
				description = ''
			if not label:
				label = ''
			if node:
				node = node.obj
			return CloudFramesSnapshotImpl(self.conn.ex_snapshot_node(node, label, description))
		except Exception, ex:
			raise wrap_exception(ex)

	def exRollbackNode(self, node, snapshot):
		try:
			if node:
				node = node.obj
			if snapshot:
				snapshot = snapshot.obj
			return self.conn.ex_rollback_node(node, snapshot)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListSnapshots(self, node):
		try:
			if node:
				node = node.obj
			return wrap_listing(self.conn.ex_list_snapshots(node), CloudFramesSnapshotImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDestroySnapshot(self, node, snapshot):
		try:
			if node:
				node = node.obj
				if snapshot:
					snapshot = snapshot.obj
			return self.conn.ex_destroy_snapshot(node, snapshot)
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.cloudframes import CloudFramesNodeSize

class CloudFramesNodeSizeImpl(NodeSizeImpl, CloudFramesNodeSize):

	def __init__(self, obj):
		NodeSizeImpl.__init__(self, obj)
		if hasattr(obj, 'vcpus'):
			self.vcpusp = none_check(obj.vcpus, -1)
		else:
			self.vcpusp = -1
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getVcpus(self):
		return self.vcpusp

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.cloudframes import CloudFramesNode

class CloudFramesNodeImpl(NodeImpl, CloudFramesNode):

	def __init__(self, obj):
		NodeImpl.__init__(self, obj)
		
	def listSnapshots(self):
		try:
			return wrap_listing(self.obj.list_snapshots(), CloudFramesSnapshotImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def snapshot(label='', description=''):
		try:
			if not description:
				description = ''
			if not label:
				label = ''
			return CloudFramesSnapshotImpl(self.obj.snapshot(label, description))
		except Exception, ex:
			raise wrap_exception(ex)

	def rollback(self, snapshot):
		try:
			return self.obj.rollback(snapshot)
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.cloudframes import CloudFramesSnapshot

class CloudFramesSnapshotImpl(CloudFramesSnapshot):

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'timestamp'):
			self.timestampp = none_check(obj.timestamp, ' ')
		else:
			self.timestampp = ' '
		if hasattr(obj, 'label'):
			self.labelp = none_check(obj.label, ' ')
		else:
			self.labelp = ' '
		if hasattr(obj, 'description'):
			self.descriptionp = none_check(obj.description, ' ')
		else:
			self.descriptionp = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getTimestamp(self):
		return self.timestampp

	def getLabel(self):
		return self.labelp

	def getDescription(self):
		return self.descriptionp

	def toString(self):
		return self.reprp

	def destroy(self):
		try:
			return self.obj.destroy()
		except Exception, ex:
			raise wrap_exception(ex)

