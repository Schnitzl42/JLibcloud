# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/openstack.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/openstack.py
#
# Apache Libcloud is licensed under the Apache 2.0 license.
# For more information, please see LICENSE and NOTICE file or:
# http://www.apache.org/licenses/LICENSE-2.0
from javaimpl.compute.ComputeContextImpl import ComputeContextImpl
from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception, jlist_str_to_pylist
from javaimpl.compute.utils import jlist_obj_to_pylist, get_property, get_property_list
from javaimpl.compute.utils import jmap_to_pymap, jlist_map_to_pylist_map
from javaimpl.compute.base.NodeImageImpl import NodeImageImpl
from javaimpl.compute.base.NodeImpl import NodeImpl
from javaimpl.compute.base.StorageVolumeImpl import StorageVolumeImpl
from org.askalon.jlibcloud.compute.driverSpecific.openstack import OpenStack_1_1_NodeTemplateImpl
from javaimpl.compute.base.VolumeSnapshotImpl import VolumeSnapshotImpl
from org.askalon.jlibcloud.compute.driverSpecific.openstack import OpenStack_1_0_NodeTemplateImpl
from javaimpl.compute.base.NodeSizeImpl import NodeSizeImpl
from org.askalon.jlibcloud.compute.driverSpecific.openstack import OpenStackComputeContext

class OpenStackComputeContextImpl(ComputeContextImpl, OpenStackComputeContext):
	def __init__(self, builder):
		ComputeContextImpl.__init__(self, builder)

	def createNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def listSizes(self, location=None):
		try:
			if location:
				location = location.obj
			return wrap_listing(self.conn.list_sizes(location), OpenStackNodeSizeImpl)
		except Exception, ex:
			raise wrap_exception(ex)
		
	def exGetVolume(self, volumeId):
		try:
			return StorageVolumeImpl(self.conn.ex_get_volume(volumeId))
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetNodeDetails(self, node_id):
		"""
        Lists details of the specified server.

        :param       node_id: ID of the node which should be used
        :type        node_id: ``str``

        :rtype: :class:`Node`
        """
		try:
			return NodeImpl(self.conn.ex_get_node_details(node_id))
		except Exception, ex:
			raise wrap_exception(ex)

	def exSoftRebootNode(self, node):
		"""
        Soft reboots the specified server

        :param      node:  node
        :type       node: :class:`Node`

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_soft_reboot_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exHardRebootNode(self, node):
		"""
        Hard reboots the specified server

        :param      node:  node
        :type       node: :class:`Node`

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_hard_reboot_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

from org.askalon.jlibcloud.compute.driverSpecific.openstack import OpenStack_1_0_ComputeContext

class OpenStack_1_0_ComputeContextImpl(OpenStackComputeContextImpl, OpenStack_1_0_ComputeContext):
	def __init__(self, builder):
		OpenStackComputeContextImpl.__init__(self, builder)

	def createNode(self, node_temp):
		"""
        Create a new node

        @inherits: :class:`NodeDriver.create_node`

        :keyword    ex_metadata: Key/Value metadata to associate with a node
        :type       ex_metadata: ``dict``

        :keyword    ex_files:   File Path => File contents to create on
                                the node
        :type       ex_files:   ``dict``

        :keyword    ex_shared_ip_group_id: The server is launched into
            that shared IP group
        :type       ex_shared_ip_group_id: ``str``
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_openstack_1_0__template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_openstack_1_0__template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_openstack_1_0__template(self, node_temp, kwargs):
		ex_metadata = node_temp.getExMetadata()
		if ex_metadata:
			kwargs['ex_metadata'] = jmap_to_pymap(ex_metadata)
		ex_files = node_temp.getExFiles()
		if ex_files:
			kwargs['ex_files'] = jmap_to_pymap(ex_files)
		ex_shared_ip_group_id = node_temp.getExSharedIpGroupId()
		kwargs = get_property(self, ex_shared_ip_group_id, 'ex_shared_ip_group_id',
					 kwargs,lambda x : x)
		return kwargs

	def getTemplateBuilder(self):
		return OpenStack_1_0_NodeTemplateImpl.newBuilder()

	def exSetPassword(self, node, password):
		"""
        Sets the Node's root password.

        This will reboot the instance to complete the operation.

        :class:`Node.extra['password']` will be set to the new value if the
        operation was successful.

        :param      node: node to set password
        :type       node: :class:`Node`

        :param      password: new password.
        :type       password: ``str``

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_set_password(node, password)
		except Exception, ex:
			raise wrap_exception(ex)

	def exSetServerName(self, node, name):
		"""
        Sets the Node's name.

        This will reboot the instance to complete the operation.

        :param      node: node to set name
        :type       node: :class:`Node`

        :param      name: new name
        :type       name: ``str``

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_set_server_name(node, name)
		except Exception, ex:
			raise wrap_exception(ex)

	def exResize(self, node, size):
		"""
        Change an existing server flavor / scale the server up or down.

        :param      node: node to resize.
        :type       node: :class:`Node`

        :param      size: new size.
        :type       size: :class:`NodeSize`

        :rtype: ``bool``
        """
		try:
			if size:
				size = size.obj
			if node:
				node = node.obj
			return self.conn.ex_resize(node, size)
		except Exception, ex:
			raise wrap_exception(ex)

	def exConfirmResize(self, node):
		"""
        Confirm a resize request which is currently in progress. If a resize
        request is not explicitly confirmed or reverted it's automatically
        confirmed after 24 hours.

        For more info refer to the API documentation: http://goo.gl/zjFI1

        :param      node: node for which the resize request will be confirmed.
        :type       node: :class:`Node`

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_confirm_resize(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exRevertResize(self, node):
		"""
        Revert a resize request which is currently in progress.
        All resizes are automatically confirmed after 24 hours if they have
        not already been confirmed explicitly or reverted.

        For more info refer to the API documentation: http://goo.gl/AizBu

        :param      node: node for which the resize request will be reverted.
        :type       node: :class:`Node`

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_revert_resize(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exRebuild(self, node_id, image_id):
		"""
        Rebuilds the specified server.

        :param       node_id: ID of the node which should be used
        :type        node_id: ``str``

        :param       image_id: ID of the image which should be used
        :type        image_id: ``str``

        :rtype: ``bool``
        """
		try:
			return self.conn.ex_rebuild(node_id, image_id)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateIpGroup(self, group_name, node_id=None):
		"""
        Creates a shared IP group.

        :param       group_name:  group name which should be used
        :type        group_name: ``str``

        :param       node_id: ID of the node which should be used
        :type        node_id: ``str``

        :rtype: ``bool``
        """
		try:
			if not node_id:
				node_id = None
			return self.conn.ex_create_ip_group(group_name, node_id)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListIpGroups(self, details=False):
		"""
        Lists IDs and names for shared IP groups.
        If details lists all details for shared IP groups.

        :param       details: True if details is required
        :type        details: ``bool``

        :rtype: ``list`` of :class:`OpenStack_1_0_SharedIpGroup`
        """
		try:
			if not details:
				details = False
			return wrap_listing(self.conn.ex_list_ip_groups(details), OpenStack_1_0_SharedIpGroupImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeleteIpGroup(self, group_id):
		"""
        Deletes the specified shared IP group.

        :param       group_id:  group id which should be used
        :type        group_id: ``str``

        :rtype: ``bool``
        """
		try:
			return self.conn.ex_delete_ip_group(group_id)
		except Exception, ex:
			raise wrap_exception(ex)

	def exShareIp(self, group_id, node_id, ip, configure_node=True):
		"""
        Shares an IP address to the specified server.

        :param       group_id:  group id which should be used
        :type        group_id: ``str``

        :param       node_id: ID of the node which should be used
        :type        node_id: ``str``

        :param       ip: ip which should be used
        :type        ip: ``str``

        :param       configure_node: configure node
        :type        configure_node: ``bool``

        :rtype: ``bool``
        """
		try:
			if not configure_node:
				configure_node = True
			return self.conn.ex_share_ip(group_id, node_id, ip, configure_node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exUnshareIp(self, node_id, ip):
		"""
        Removes a shared IP address from the specified server.

        :param       node_id: ID of the node which should be used
        :type        node_id: ``str``

        :param       ip: ip which should be used
        :type        ip: ``str``

        :rtype: ``bool``
        """
		try:
			return self.conn.ex_unshare_ip(node_id, ip)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListIpAddresses(self, node_id):
		"""
        List all server addresses.

        :param       node_id: ID of the node which should be used
        :type        node_id: ``str``

        :rtype: :class:`OpenStack_1_0_NodeIpAddresses`
        """
		try:
			return wrap_listing(self.conn.ex_list_ip_addresses(node_id), OpenStack_1_0_NodeIpAddressesImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exLimits(self):
		"""
        Extra call to get account's limits, such as
        rates (for example amount of POST requests per day)
        and absolute limits like total amount of available
        RAM to be used by servers.

        :return: dict with keys 'rate' and 'absolute'
        :rtype: ``dict``
        """
		try:
			return self.conn.ex_limits()
		except Exception, ex:
			raise wrap_exception(ex)

	def exSaveImage(self, node, name):
		"""Create an image for node.

        :param      node: node to use as a base for image
        :type       node: :class:`Node`

        :param      name: name for new image
        :type       name: ``str``

        :rtype: :class:`NodeImage`
        """
		try:
			if node:
				node = node.obj
			return NodeImageImpl(self.conn.ex_save_image(node, name))
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeleteImage(self, image):
		"""Delete an image for node.

        :param      image: the image to be deleted
        :type       image: :class:`NodeImage`

        :rtype: ``bool``
        """
		try:
			if image:
				image = image.obj
			return self.conn.ex_delete_image(image)
		except Exception, ex:
			raise wrap_exception(ex)

from org.askalon.jlibcloud.compute.driverSpecific.openstack import OpenStack_1_1_ComputeContext

class OpenStack_1_1_ComputeContextImpl(OpenStackComputeContextImpl, OpenStack_1_1_ComputeContext):
	def __init__(self, builder):
		OpenStackComputeContextImpl.__init__(self, builder)

	def createNode(self, node_temp):
		"""Create a new node

        @inherits:  :class:`NodeDriver.create_node`

        :keyword    ex_keyname:  The name of the key pair
        :type       ex_keyname:  ``str``

        :keyword    ex_userdata: String containing user data
                                 see
                                 https://help.ubuntu.com/community/CloudInit
        :type       ex_userdata: ``str``

        :keyword    ex_security_groups: List of security groups to assign to
                                        the node
        :type       ex_security_groups: ``list`` of
                                       :class:`OpenStackSecurityGroup`

        :keyword    ex_metadata: Key/Value metadata to associate with a node
        :type       ex_metadata: ``dict``

        :keyword    ex_files:   File Path => File contents to create on
                                the no  de
        :type       ex_files:   ``dict``


        :keyword    networks: The server is launched into a set of Networks.
        :type       networks: :class:`OpenStackNetwork`

        :keyword    ex_disk_config: Name of the disk configuration.
                                    Can be either ``AUTO`` or ``MANUAL``.
        :type       ex_disk_config: ``str``
        
        :keyword    ex_admin_pass: root password for the node
        :type       ex_admin_pass: ``str``
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_openstack_1_1__template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_openstack_1_1__template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_openstack_1_1__template(self, node_temp, kwargs):
		ex_keyname = node_temp.getExKeyname()
		kwargs = get_property(self, ex_keyname, 'ex_keyname',
					 kwargs,lambda x : x)
		ex_userdata = node_temp.getExUserdata()
		kwargs = get_property(self, ex_userdata, 'ex_userdata',
					 kwargs,lambda x : x)
		ex_security_groups = node_temp.getExSecurityGroups()
		if ex_security_groups:
			kwargs['ex_security_groups'] = jlist_obj_to_pylist(ex_security_groups)
		ex_metadata = node_temp.getExMetadata()
		if ex_metadata:
			kwargs['ex_metadata'] = jmap_to_pymap(ex_metadata)
		ex_files = node_temp.getExFiles()
		if ex_files:
			kwargs['ex_files'] = jmap_to_pymap(ex_files)
		networks = node_temp.getNetworks()
		kwargs = get_property(self, networks, 'networks',
					 kwargs,lambda x : x.obj)
		ex_disk_config = node_temp.getExDiskConfig()
		kwargs = get_property(self, ex_disk_config, 'ex_disk_config',
					 kwargs,lambda x : x)
		ex_admin_pass = node_temp.getExAdminPass()
		kwargs = get_property(self, ex_admin_pass, 'ex_admin_pass',
					 kwargs,lambda x : x)
		return kwargs

	def getTemplateBuilder(self):
		return OpenStack_1_1_NodeTemplateImpl.newBuilder()

	def exSetPassword(self, node, password):
		"""
        Changes the administrator password for a specified server.

        :param      node: Node to rebuild.
        :type       node: :class:`Node`

        :param      password: The administrator password.
        :type       password: ``str``

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_set_password(node, password)
		except Exception, ex:
			raise wrap_exception(ex)

	def exRebuild(self, node, image, ex_metadata, ex_files, 
					ex_keyname, ex_userdata, ex_security_groups, ex_disk_config):
		"""
        Rebuild a Node.

        :param      node: Node to rebuild.
        :type       node: :class:`Node`

        :param      image: New image to use.
        :type       image: :class:`NodeImage`

        :keyword    ex_metadata: Key/Value metadata to associate with a node
        :type       ex_metadata: ``dict``

        :keyword    ex_files:   File Path => File contents to create on
                                the no  de
        :type       ex_files:   ``dict``

        :keyword    ex_keyname:  Name of existing public key to inject into
                                 instance
        :type       ex_keyname:  ``str``

        :keyword    ex_userdata: String containing user data
                                 see
                                 https://help.ubuntu.com/community/CloudInit
        :type       ex_userdata: ``str``

        :keyword    ex_security_groups: List of security groups to assign to
                                        the node
        :type       ex_security_groups: ``list`` of
                                       :class:`OpenStackSecurityGroup`

        :keyword    ex_disk_config: Name of the disk configuration.
                                    Can be either ``AUTO`` or ``MANUAL``.
        :type       ex_disk_config: ``str``

        :rtype: ``bool``
        """
		try:
			if image:
				image = image.obj
			if node:
				node = node.obj
			kwargs = {}
			ex_metadata = jmap_to_pymap(ex_metadata)
			if ex_metadata:
				kwargs['ex_metadata'] = ex_metadata
			ex_files = jmap_to_pymap(ex_files)
			if ex_files:
				kwargs['ex_files'] = ex_files
			if ex_keyname:
				kwargs['ex_keyname'] = ex_keyname
			if ex_userdata:
				kwargs['ex_userdata'] = ex_userdata
			ex_security_groups = jlist_obj_to_pylist(ex_security_groups)
			if ex_security_groups:
				kwargs['ex_security_groups'] = ex_security_groups
			if ex_disk_config:
				kwargs['ex_disk_config'] = ex_disk_config
			return self.conn.ex_rebuild(node, image, **kwargs)
		except Exception, ex:
			raise wrap_exception(ex)

	def exResize(self, node, size):
		"""
        Change a node size.

        :param      node: Node to resize.
        :type       node: :class:`Node`

        :type       size: :class:`NodeSize`
        :param      size: New size to use.

        :rtype: ``bool``
        """
		try:
			return self.conn.ex_resize(node, size)
		except Exception, ex:
			raise wrap_exception(ex)

	def exConfirmResize(self, node):
		"""
        Confirms a pending resize action.

        :param      node: Node to resize.
        :type       node: :class:`Node`

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_confirm_resize(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exRevertResize(self, node):
		"""
        Cancels and reverts a pending resize action.

        :param      node: Node to resize.
        :type       node: :class:`Node`

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_revert_resize(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exSaveImage(self, node, name, metadata=None):
		"""
        Creates a new image.

        :param      node: Node
        :type       node: :class:`Node`

        :param      name: The name for the new image.
        :type       name: ``str``

        :param      metadata: Key and value pairs for metadata.
        :type       metadata: ``dict``

        :rtype: :class:`NodeImage`
        """
		try:
			if not metadata:
				metadata = None
			if node:
				node = node.obj
			metadata = jmap_to_pymap(metadata)
			return NodeImageImpl(self.conn.ex_save_image(node, name, metadata))
		except Exception, ex:
			raise wrap_exception(ex)

	def exSetServerName(self, node, name):
		"""
        Sets the Node's name.

        :param      node: Node
        :type       node: :class:`Node`

        :param      name: The name of the server.
        :type       name: ``str``

        :rtype: :class:`Node`
        """
		try:
			if node:
				node = node.obj
			return NodeImpl(self.conn.ex_set_server_name(node, name))
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetMetadata(self, node):
		"""
        Get a Node's metadata.

        :param      node: Node
        :type       node: :class:`Node`

        :return: Key/Value metadata associated with node.
        :rtype: ``dict``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_get_metadata(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exSetMetadata(self, node, metadata):
		"""
        Sets the Node's metadata.

        :param      node: Node
        :type       node: :class:`Node`

        :param      metadata: Key/Value metadata to associate with a node
        :type       metadata: ``dict``

        :rtype: ``dict``
        """
		try:
			if node:
				node = node.obj
			metadata = jmap_to_pymap(metadata)
			return self.conn.ex_set_metadata(node, metadata)
		except Exception, ex:
			raise wrap_exception(ex)

	def exUpdateNode(self, node, name):
		"""
        Update the Node's editable attributes.  The OpenStack API currently
        supports editing name and IPv4/IPv6 access addresses.

        The driver currently only supports updating the node name.

        :param      node: Node
        :type       node: :class:`Node`

        :keyword    name:   New name for the server
        :type       name:   ``str``

        :rtype: :class:`Node`
        """
		try:
			if node:
				node = node.obj
			kwargs = {}
			if name:
				kwargs['name'] = name
			return NodeImpl(self.conn.ex_update_node(node, **kwargs))
		except Exception, ex:
			raise wrap_exception(ex)

	def exListNetworks(self, tenant_id=None):
		"""
        Get a list of Networks that are available.

        :rtype: ``list`` of :class:`OpenStackNetwork`
        """
		try:
			return wrap_listing(self.conn.ex_list_networks(tenant_id), OpenStackNetworkImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateNetwork(self, name, cidr):
		"""
        Create a new Network

        :param name: Name of network which should be used
        :type name: ``str``

        :param cidr: cidr of network which should be used
        :type cidr: ``str``

        :rtype: :class:`OpenStackNetwork`
        """
		try:
			return OpenStackNetworkImpl(self.conn.ex_create_network(name, cidr))
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeleteNetwork(self, network):
		"""
        Get a list of NodeNetorks that are available.

        :param network: Network which should be used
        :type network: :class:`OpenStackNetwork`

        :rtype: ``bool``
        """
		try:
			if network:
				network = network.obj
			return self.conn.ex_delete_network(network)
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetConsoleOutput(self, node, length=None):
		"""
        Get console output

        :param      node: node
        :type       node: :class:`Node`

        :param      length: Optional number of lines to fetch from the
                            console log
        :type       length: ``int``

        :return: Dictionary with the output
        :rtype: ``dict``
        """
		try:
			if not length:
				length = None
			if node:
				node = node.obj
			return self.conn.ex_get_console_output(node, length)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListSnapshots():
		try:
			return self.conn.ex_list_snapshots()
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateSnapshot(self, volume, name, description=None, force=False):
		"""
        Create a snapshot based off of a volume.

        :param      volume: volume
        :type       volume: :class:`StorageVolume`

        :keyword    name: New name for the volume snapshot
        :type       name: ``str``

        :keyword    description: Description of the snapshot (optional)
        :type       description: ``str``

        :keyword    force: Whether to force creation (optional)
        :type       force: ``bool``

        :rtype:     :class:`VolumeSnapshot`
        """
		try:
			if not force:
				force = False
			if not description:
				description = None
			if volume:
				volume = volume.obj
			return VolumeSnapshotImpl(self.conn.ex_create_snapshot(volume, name, description, force))
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeleteSnapshot(self, snapshot):
		"""
        Delete a VolumeSnapshot

        :param      snapshot: snapshot
        :type       snapshot: :class:`VolumeSnapshot`

        :rtype:     ``bool``
        """
		try:
			if snapshot:
				snapshot = snapshot.obj
			return self.conn.ex_delete_snapshot(snapshot)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListSecurityGroups(self):
		"""
        Get a list of Security Groups that are available.

        :rtype: ``list`` of :class:`OpenStackSecurityGroup`
        """
		try:
			return wrap_listing(self.conn.ex_list_security_groups(), OpenStackSecurityGroupImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetNodeSecurityGroups(self, node):
		"""
        Get Security Groups of the specified server.

        :rtype: ``list`` of :class:`OpenStackSecurityGroup`
        """
		try:
			if node:
				node = node.obj
			return wrap_listing(self.conn.ex_get_node_security_groups(node), OpenStackSecurityGroupImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateSecurityGroup(self, name, description):
		"""
        Create a new Security Group

        :param name: Name of the new Security Group
        :type  name: ``str``

        :param description: Description of the new Security Group
        :type  description: ``str``

        :rtype: :class:`OpenStackSecurityGroup`
        """
		try:
			return OpenStackSecurityGroupImpl(self.conn.ex_create_security_group(name, description))
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeleteSecurityGroup(self, security_group):
		"""
        Delete a Security Group.

        :param security_group: Security Group should be deleted
        :type  security_group: :class:`OpenStackSecurityGroup`

        :rtype: ``bool``
        """
		try:
			if security_group:
				security_group = security_group.obj
			return self.conn.ex_delete_security_group(security_group)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateSecurityGroupRule(self, security_group, ip_protocol, from_port, to_port, cidr=None, 
					source_security_group=None):
		"""
        Create a new Rule in a Security Group

        :param security_group: Security Group in which to add the rule
        :type  security_group: :class:`OpenStackSecurityGroup`

        :param ip_protocol: Protocol to which this rule applies
                            Examples: tcp, udp, ...
        :type  ip_protocol: ``str``

        :param from_port: First port of the port range
        :type  from_port: ``int``

        :param to_port: Last port of the port range
        :type  to_port: ``int``

        :param cidr: CIDR notation of the source IP range for this rule
        :type  cidr: ``str``

        :param source_security_group: Existing Security Group to use as the
                                      source (instead of CIDR)
        :type  source_security_group: L{OpenStackSecurityGroup

        :rtype: :class:`OpenStackSecurityGroupRule`
        """
		try:
			if source_security_group:
				source_security_group = source_security_group.obj
			if not source_security_group:
				source_security_group = None
			if not cidr:
				cidr = None
			if security_group:
				security_group = security_group.obj
			return OpenStackSecurityGroupRuleImpl(self.conn.ex_create_security_group_rule(security_group, ip_protocol, from_port, to_port, cidr, source_security_group))
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeleteSecurityGroupRule(self, rule):
		"""
        Delete a Rule from a Security Group.

        :param rule: Rule should be deleted
        :type  rule: :class:`OpenStackSecurityGroupRule`

        :rtype: ``bool``
        """
		try:
			if rule:
				rule = rule.obj
			return self.conn.ex_delete_security_group_rule(rule)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListKeypairs(self):
		"""
        Get a list of KeyPairs that are available.

        :rtype: ``list`` of :class:`OpenStackKeyPair`
        """
		try:
			return wrap_listing(self.conn.ex_list_keypairs(), OpenStackKeyPairImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateKeypair(self, name):
		"""
        Create a new KeyPair

        :param name: Name of the new KeyPair
        :type  name: ``str``

        :rtype: :class:`OpenStackKeyPair`
        """
		try:
			return OpenStackKeyPairImpl(self.conn.ex_create_keypair(name))
		except Exception, ex:
			raise wrap_exception(ex)

	def exImportKeypair(self, name, keyfile):
		"""
        Import a KeyPair from a file

        :param name: Name of the new KeyPair
        :type  name: ``str``

        :param keyfile: Path to the public key file (in OpenSSH format)
        :type  keyfile: ``str``

        :rtype: :class:`OpenStackKeyPair`
        """
		try:
			return OpenStackKeyPairImpl(self.conn.ex_import_keypair(name, keyfile))
		except Exception, ex:
			raise wrap_exception(ex)

	def exImportKeypairFromString(self, name, key_material):
		"""
        Import a KeyPair from a string

        :param name: Name of the new KeyPair
        :type  name: ``str``

        :param key_material: Public key (in OpenSSH format)
        :type  key_material: ``str``

        :rtype: :class:`OpenStackKeyPair`
        """
		try:
			return OpenStackKeyPairImpl(self.conn.ex_import_keypair_from_string(name, key_material))
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeleteKeypair(self, keypair):
		"""
        Delete a KeyPair.

        :param keypair: KeyPair to delete
        :type  keypair: :class:`OpenStackKeyPair`

        :rtype: ``bool``
        """
		try:
			if keypair:
				keypair = keypair.obj
			return self.conn.ex_delete_keypair(keypair)
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetSize(self, size_id):
		"""
        Get a NodeSize

        :param      size_id: ID of the size which should be used
        :type       size_id: ``str``

        :rtype: :class:`NodeSize`
        """
		try:
			return NodeSizeImpl(self.conn.ex_get_size(size_id))
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetImage(self, image_id):
		"""
        Get a NodeImage

        :param      image_id: ID of the image which should be used
        :type       image_id: ``str``

        :rtype: :class:`NodeImage`
        """
		try:
			return NodeImageImpl(self.conn.ex_get_image(image_id))
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeleteImage(self, image):
		"""
        Delete a NodeImage

        :param      image: image witch should be used
        :type       image: :class:`NodeImage`

        :rtype: ``bool``
        """
		try:
			if image:
				image = image.obj
			return self.conn.ex_delete_image(image)
		except Exception, ex:
			raise wrap_exception(ex)

	def exRescue(self, node, password=None):
		"""
        Rescue a node

        :param      node: node
        :type       node: :class:`Node`

        :param      password: password
        :type       password: ``str``

        :rtype: :class:`Node`
        """
		try:
			if not password:
				password = None
			if node:
				node = node.obj
			return NodeImpl(self.conn.ex_rescue(node, password))
		except Exception, ex:
			raise wrap_exception(ex)

	def exUnrescue(self, node):
		"""
        Unrescue a node

        :param      node: node
        :type       node: :class:`Node`

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_unrescue(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListFloatingIpPools(self):
		"""
        List available floating IP pools

        :rtype: ``list`` of :class:`OpenStack_1_1_FloatingIpPool`
        """
		try:
			return wrap_listing(self.conn.ex_list_floating_ip_pools(), OpenStack_1_1_FloatingIpPoolImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exAttachFloatingIpToNode(self, node, ip):
		"""
        Attach the floating IP to the node

        :param      node: node
        :type       node: :class:`Node`

        :param      ip: floating IP to attach
        :type       ip: ``str`` or :class:`OpenStack_1_1_FloatingIpAddress`

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_attach_floating_ip_to_node(node, ip)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDetachFloatingIpFromNode(self, node, ip):
		"""
        Detach the floating IP from the node

        :param      node: node
        :type       node: :class:`Node`

        :param      ip: floating IP to remove
        :type       ip: ``str`` or :class:`OpenStack_1_1_FloatingIpAddress`

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_detach_floating_ip_from_node(node, ip)
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetMetadataForNode(self, node):
		"""
        Return the metadata associated with the node.

        :param      node: Node instance
        :type       node: :class:`Node`

        :return: A dictionary or other mapping of strings to strings,
                 associating tag names with tag values.
        :type tags: ``dict``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_get_metadata_for_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exPauseNode(self, node):
		try:
			if node:
				node = node.obj
			return self.conn.ex_pause_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exUnpauseNode(self, node):
		try:
			if node:
				node = node.obj
			return self.conn.ex_unpause_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exSuspendNode(self, node):
		try:
			if node:
				node = node.obj
			return self.conn.ex_suspend_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exResumeNode(self, node):
		try:
			if node:
				node = node.obj
			return self.conn.ex_resume_node(node)
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.openstack import OpenStackNodeSize

class OpenStackNodeSizeImpl(NodeSizeImpl, OpenStackNodeSize):
	'''
    NodeSize class for the OpenStack.org driver.

    Following the example of OpenNebula.org driver
    and following guidelines:
    https://issues.apache.org/jira/browse/LIBCLOUD-119
	'''

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


from org.askalon.jlibcloud.compute.driverSpecific.openstack import OpenStack_1_0_SharedIpGroup

class OpenStack_1_0_SharedIpGroupImpl(OpenStack_1_0_SharedIpGroup):
	'''
    Shared IP group info.
	'''

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
		if hasattr(obj, 'servers'):
			self.serversp = none_check(obj.servers, ' ')
		else:
			self.serversp = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getName(self):
		return self.namep

	def getServers(self):
		return self.serversp

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.openstack import OpenStack_1_0_NodeIpAddresses

class OpenStack_1_0_NodeIpAddressesImpl(OpenStack_1_0_NodeIpAddresses):
	'''
    List of public and private IP addresses of a Node.
	'''

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'public_addresses'):
			self.public_addressesp = none_check(obj.public_addresses, ' ')
		else:
			self.public_addressesp = ' '
		if hasattr(obj, 'private_addresses'):
			self.private_addressesp = none_check(obj.private_addresses, ' ')
		else:
			self.private_addressesp = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getPublicAddresses(self):
		return self.public_addressesp

	def getPrivateAddresses(self):
		return self.private_addressesp

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.openstack import OpenStackNetwork

class OpenStackNetworkImpl(OpenStackNetwork):
	'''
    A Virtual Network.
	'''

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
		if hasattr(obj, 'cidr'):
			self.cidrp = none_check(obj.cidr, ' ')
		else:
			self.cidrp = ' '
		if hasattr(obj, 'extra'):
			self.extrap = obj.extra, {}
		else:
			self.extrap = {}
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getName(self):
		return self.namep

	def getCidr(self):
		return self.cidrp

	def getExtra(self):
		return self.extrap

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.openstack import OpenStackSecurityGroup

class OpenStackSecurityGroupImpl(OpenStackSecurityGroup):
	'''
    A Security Group.
	'''

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'tenant_id'):
			self.tenant_idp = none_check(obj.tenant_id, ' ')
		else:
			self.tenant_idp = ' '
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
		if hasattr(obj, 'description'):
			self.descriptionp = none_check(obj.description, ' ')
		else:
			self.descriptionp = ' '
		if hasattr(obj, 'rules'):
			self.rulesp = wrap_listing(obj.rules, OpenStackSecurityGroupRuleImpl)
		else:
			self.rulesp = [OpenStackSecurityGroupRuleImpl(None)]
		if hasattr(obj, 'extra'):
			self.extrap = obj.extra, {}
		else:
			self.extrap = {}
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getTenantId(self):
		return self.tenant_idp

	def getName(self):
		return self.namep

	def getDescription(self):
		return self.descriptionp

	def getRules(self):
		return self.rulesp

	def getExtra(self):
		return self.extrap

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.openstack import OpenStackSecurityGroupRule

class OpenStackSecurityGroupRuleImpl(OpenStackSecurityGroupRule):
	'''
    A Rule of a Security Group.
	'''

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'parent_group_id'):
			self.parent_group_idp = none_check(obj.parent_group_id, ' ')
		else:
			self.parent_group_idp = ' '
		if hasattr(obj, 'ip_protocol'):
			self.ip_protocolp = none_check(obj.ip_protocol, ' ')
		else:
			self.ip_protocolp = ' '
		if hasattr(obj, 'from_port'):
			self.from_portp = none_check(obj.from_port, -1)
		else:
			self.from_portp = -1
		if hasattr(obj, 'to_port'):
			self.to_portp = none_check(obj.to_port, -1)
		else:
			self.to_portp = -1
		if hasattr(obj, 'ip_range'):
			self.ip_rangep = none_check(obj.ip_range, ' ')
		else:
			self.ip_rangep = ' '
		if hasattr(obj, 'group'):
			self.groupp = none_check(obj.group, ' ')
		else:
			self.groupp = ' '
		if hasattr(obj, 'tenant_id'):
			self.tenant_idp = none_check(obj.tenant_id, ' ')
		else:
			self.tenant_idp = ' '
		if hasattr(obj, 'extra'):
			self.extrap = obj.extra, {}
		else:
			self.extrap = {}
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getParentGroupId(self):
		return self.parent_group_idp

	def getIpProtocol(self):
		return self.ip_protocolp

	def getFromPort(self):
		return self.from_portp

	def getToPort(self):
		return self.to_portp

	def getIpRange(self):
		return self.ip_rangep

	def getGroup(self):
		return self.groupp

	def getTenantId(self):
		return self.tenant_idp

	def getExtra(self):
		return self.extrap

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.openstack import OpenStackKeyPair
from javaimpl.compute.base.KeyPairImpl import KeyPairImpl

class OpenStackKeyPairImpl(KeyPairImpl, OpenStackKeyPair):
	'''
    A KeyPair.
	'''

	def __init__(self, obj):
		KeyPairImpl.__init__(self, obj)
		

from org.askalon.jlibcloud.compute.driverSpecific.openstack import OpenStack_1_1_FloatingIpPool

class OpenStack_1_1_FloatingIpPoolImpl(OpenStack_1_1_FloatingIpPool):
	'''
    Floating IP Pool info.
	'''

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
		if hasattr(obj, 'connection'):
			self.connectionp = none_check(obj.connection, ' ')
		else:
			self.connectionp = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getName(self):
		return self.namep

	def getConnection(self):
		return self.connectionp

	def toString(self):
		return self.reprp

	def listFloatingIps(self):
		"""
        List floating IPs in the pool

        :rtype: ``list`` of :class:`OpenStack_1_1_FloatingIpAddress`
        """
		try:
			return wrap_listing(self.conn.list_floating_ips(), OpenStack_1_1_FloatingIpAddressImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def getFloatingIp(self, ip):
		"""
        Get specified floating IP from the pool

        :param      ip: floating IP to remove
        :type       ip: ``str``

        :rtype: :class:`OpenStack_1_1_FloatingIpAddress`
        """
		try:
			return OpenStack_1_1_FloatingIpAddressImpl(self.conn.get_floating_ip(ip))
		except Exception, ex:
			raise wrap_exception(ex)

	def createFloatingIp(self):
		"""
        Create new floating IP in the pool

        :rtype: :class:`OpenStack_1_1_FloatingIpAddress`
        """
		try:
			return OpenStack_1_1_FloatingIpAddressImpl(self.conn.create_floating_ip())
		except Exception, ex:
			raise wrap_exception(ex)

	def deleteFloatingIp(self, ip):
		"""
        Delete specified floating IP from the pool

        :param      ip: floating IP to remove
        :type       ip::class:`OpenStack_1_1_FloatingIpAddress`

        :rtype: ``bool``
        """
		try:
			if ip:
				ip = ip.obj
			return self.conn.delete_floating_ip(ip)
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.openstack import OpenStack_1_1_FloatingIpAddress

class OpenStack_1_1_FloatingIpAddressImpl(OpenStack_1_1_FloatingIpAddress):
	'''
    Floating IP info.
	'''

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'ip_address'):
			self.ip_addressp = none_check(obj.ip_address, ' ')
		else:
			self.ip_addressp = ' '
		if hasattr(obj, 'pool'):
			self.poolp = none_check(obj.pool, ' ')
		else:
			self.poolp = ' '
		if hasattr(obj, 'node_id'):
			self.node_idp = NodeImpl(obj.node_id)
		else:
			self.node_idp = NodeImpl(None)
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getIpAddress(self):
		return self.ip_addressp

	def getPool(self):
		return self.poolp

	def getNodeId(self):
		return self.node_idp

	def toString(self):
		return self.reprp

	def delete(self):
		"""
        Delete this floating IP

        :rtype: ``bool``
        """
		try:
			return self.conn.delete()
		except Exception, ex:
			raise wrap_exception(ex)

