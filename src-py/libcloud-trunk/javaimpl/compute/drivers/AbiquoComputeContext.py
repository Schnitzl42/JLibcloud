# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/abiquo.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/abiquo.py
#
# Apache Libcloud is licensed under the Apache 2.0 license.
# For more information, please see LICENSE and NOTICE file or:
# http://www.apache.org/licenses/LICENSE-2.0
from javaimpl.compute.ComputeContextImpl import ComputeContextImpl
from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception, jlist_str_to_pylist
from javaimpl.compute.utils import jlist_obj_to_pylist, get_property, get_property_list
from javaimpl.compute.utils import jmap_to_pymap, jlist_map_to_pylist_map
from org.askalon.jlibcloud.compute.driverSpecific.abiquo import AbiquoNodeTemplateImpl
from javaimpl.compute.base.NodeImageImpl import NodeImageImpl
from javaimpl.compute.base.NodeLocationImpl import NodeLocationImpl
from javaimpl.compute.base.NodeImpl import NodeImpl
from javaimpl.compute.base.NodeSizeImpl import NodeSizeImpl
from org.askalon.jlibcloud.compute.driverSpecific.abiquo import AbiquoComputeContext

class AbiquoComputeContextImpl(ComputeContextImpl, AbiquoComputeContext):
	def __init__(self, builder):
		ComputeContextImpl.__init__(self, builder)

	def createNode(self, node_temp):
		"""
        Create a new node instance in Abiquo

        All the :class:`Node`s need to be defined inside a VirtualAppliance
        (called :class:`NodeGroup` here). If there is no group name defined,
        'libcloud' name will be used instead.

        This method wraps these Abiquo actions:

            1. Create a group if it does not exist.
            2. Register a new node in the group.
            3. Deploy the node and boot it.
            4. Retrieves it again to get schedule-time attributes (such as ips
               and vnc ports).

        The rest of the driver methods has been created in a way that, if any
        of these actions fail, the user can not reach an inconsistent state

        :keyword    name:   The name for this new node (required)
        :type       name:   ``str``

        :keyword    size:   The size of resources allocated to this node.
        :type       size:   :class:`NodeSize`

        :keyword    image:  OS Image to boot on node. (required)
        :type       image:  :class:`NodeImage`

        :keyword    location: Which data center to create a node in. If empty,
                              undefined behavoir will be selected. (optional)
        :type       location: :class:`NodeLocation`

        :keyword   group_name:  Which group this node belongs to. If empty,
                                 it will be created into 'libcloud' group. If
                                 it does not found any group in the target
                                 location (random location if you have not set
                                 the parameter), then it will create a new
                                 group with this name.
        :type     group_name:  c{str}

        :return:               The newly created node.
        :rtype:                :class:`Node`
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_abiquo_template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_abiquo_template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_abiquo_template(self, node_temp, kwargs):
		name = node_temp.getName()
		kwargs = get_property(self, name, 'name',
					 kwargs,lambda x : x)
		group_name = node_temp.getGroupName()
		kwargs = get_property(self, group_name, 'group_name',
					 kwargs,lambda x : x)
		return kwargs

	def getTemplateBuilder(self):
		return AbiquoNodeTemplateImpl.newBuilder()

	def exRunNode(self, node):
		"""
        Runs a node

        Here there is a bit difference between Abiquo states and libcloud
        states, so this method is created to have better compatibility. In
        libcloud, if the node is not running, then it does not exist (avoiding
        UNKNOWN and temporal states). In Abiquo, you can define a node, and
        then deploy it.

        If the node is in :class:`NodeState.TERMINATED` libcloud's state and in
        'NOT_DEPLOYED' Abiquo state, there is a way to run and recover it
        for libcloud using this method. There is no way to reach this state
        if you are using only libcloud, but you may have used another Abiquo
        client and now you want to recover your node to be used by libcloud.

        :param node: The node to run
        :type node: :class:`Node`

        :return: The node itself, but with the new state
        :rtype: :class:`Node`
        """
		try:
			if node:
				node = node.obj
			return NodeImpl(self.conn.ex_run_node(node))
		except Exception, ex:
			raise wrap_exception(ex)

	def exPopulateCache(self):
		"""
        Populate the cache.

        For each connection, it is good to store some objects that will be
        useful for further requests, such as the 'user' and the 'enterprise'
        objects.

        Executes the 'login' resource after setting the connection parameters
        and, if the execution is successful, it sets the 'user' object into
        cache. After that, it also requests for the 'enterprise' and
        'locations' data.

        List of locations should remain the same for a single libcloud
        connection. However, this method is public and you are able to
        refresh the list of locations any time.
        """
		try:
			self.conn.ex_populate_cache()
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateGroup(self, name, location=None):
		"""
        Create an empty group.

        You can specify the location as well.

        :param     name:     name of the group (required)
        :type      name:     ``str``

        :param     location: location were to create the group
        :type      location: :class:`NodeLocation`

        :returns:            the created group
        :rtype:              :class:`NodeGroup`
        """
		try:
			if location:
				location = location.obj
			if not location:
				location = None
			return NodeGroupImpl(self.conn.ex_create_group(name, location))
		except Exception, ex:
			raise wrap_exception(ex)

	def exDestroyGroup(self, group):
		"""
        Destroy a group.

        Be careful! Destroying a group means destroying all the :class:`Node`s
        there and the group itself!

        If there is currently any action over any :class:`Node` of the
        :class:`NodeGroup`, then the method will raise an exception.

        :param     group: The group (required)
        :type      group: :class:`NodeGroup`

        :return:         If the group was destroyed successfully
        :rtype:          ``bool``
        """
		try:
			if group:
				group = group.obj
			return self.conn.ex_destroy_group(group)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListGroups(self, location=None):
		"""
        List all groups.

        :param location: filter the groups by location (optional)
        :type  location: a :class:`NodeLocation` instance.

        :return:         the list of :class:`NodeGroup`
        """
		try:
			if location:
				location = location.obj
			if not location:
				location = None
			return wrap_listing(self.conn.ex_list_groups(location), NodeGroupImpl)
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.abiquo import NodeGroup

class NodeGroupImpl(NodeGroup):
	'''
    Group of virtual machines that can be managed together

    All :class:`Node`s in Abiquo must be defined inside a Virtual Appliance.
    We offer a way to handle virtual appliances (called NodeGroup to
    maintain some kind of name conventions here) inside the
    :class:`AbiquoNodeDriver` without breaking compatibility of the rest of
    libcloud API.

    If the user does not want to handle groups, all the virtual machines
    will be created inside a group named 'libcloud'
	'''

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
		if hasattr(obj, 'nodes'):
			self.nodesp = wrap_listing(obj.nodes, NodeImpl)
		else:
			self.nodesp = wrap_listing(None, NodeImpl)
		if hasattr(obj, 'uri'):
			self.urip = none_check(obj.uri, ' ')
		else:
			self.urip = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getName(self):
		return self.namep

	def getNodes(self):
		return self.nodesp

	def getUri(self):
		return self.urip

	def toString(self):
		return self.reprp

	def destroy(self):
		"""
        Destroys the group delegating the execution to
        :class:`AbiquoNodeDriver`.
        """
		try:
			return self.obj.destroy()
		except Exception, ex:
			raise wrap_exception(ex)

