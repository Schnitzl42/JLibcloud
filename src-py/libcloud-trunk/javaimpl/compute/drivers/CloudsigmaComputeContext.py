# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/cloudsigma.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/cloudsigma.py
#
# Apache Libcloud is licensed under the Apache 2.0 license.
# For more information, please see LICENSE and NOTICE file or:
# http://www.apache.org/licenses/LICENSE-2.0
from javaimpl.compute.ComputeContextImpl import ComputeContextImpl
from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception, jlist_str_to_pylist
from javaimpl.compute.utils import jlist_obj_to_pylist, get_property, get_property_list
from javaimpl.compute.utils import jmap_to_pymap, jlist_map_to_pylist_map
from org.askalon.jlibcloud.compute.driverSpecific.cloudsigma import CloudSigma_2_0_NodeTemplateImpl
from javaimpl.compute.base.NodeImageImpl import NodeImageImpl
from org.askalon.jlibcloud.compute.driverSpecific.cloudsigma import CloudSigma_1_0_NodeTemplateImpl
from javaimpl.compute.base.libcloudcomputebaseNodeImageImpl import libcloudcomputebaseNodeImageImpl
from javaimpl.compute.base.libcloudcomputebaseNodeImpl import libcloudcomputebaseNodeImpl
from javaimpl.compute.base.NodeImpl import NodeImpl
from javaimpl.compute.base.NodeSizeImpl import NodeSizeImpl
from org.askalon.jlibcloud.compute.driverSpecific.cloudsigma import CloudSigma_1_0_ComputeContext

class CloudSigma_1_0_ComputeContextImpl(ComputeContextImpl, CloudSigma_1_0_ComputeContext):
	def __init__(self, builder):
		ComputeContextImpl.__init__(self, builder)

	def createNode(self, node_temp):
		"""
        Creates a CloudSigma instance

        @inherits: :class:`NodeDriver.create_node`

        :keyword    name: String with a name for this new node (required)
        :type       name: ``str``

        :keyword    smp: Number of virtual processors or None to calculate
        based on the cpu speed
        :type       smp: ``int``

        :keyword    nic_model: e1000, rtl8139 or virtio (is not specified,
        e1000 is used)
        :type       nic_model: ``str``

        :keyword    vnc_password: If not set, VNC access is disabled.
        :type       vnc_password: ``bool``

        :keyword    drive_type: Drive type (ssd|hdd). Defaults to hdd.
        :type       drive_type: ``str``
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_cloudsigma_1_0__template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_cloudsigma_1_0__template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_cloudsigma_1_0__template(self, node_temp, kwargs):
		name = node_temp.getName()
		kwargs = get_property(self, name, 'name',
					 kwargs,lambda x : x)
		smp = node_temp.getSmp()
		kwargs = get_property(self, smp, 'smp',
					 kwargs,lambda x : int(x))
		nic_model = node_temp.getNicModel()
		kwargs = get_property(self, nic_model, 'nic_model',
					 kwargs,lambda x : x)
		vnc_password = node_temp.getVncPassword()
		l = lambda x : True if x == 'true' or x == True else False
		kwargs = get_property(self, vnc_password, 'vnc_password',
					 kwargs,l)
		drive_type = node_temp.getDriveType()
		kwargs = get_property(self, drive_type, 'drive_type',
					 kwargs,lambda x : x)
		return kwargs
	
	def listSizes(self, location=None):
		try:
			if location:
				location = location.obj
			return wrap_listing(self.conn.list_sizes(location), CloudSigmaNodeSizeImpl)
		except Exception, ex:
			raise wrap_exception(ex)
			
	def listImages(self, location=None):
		try:
			if location:
				location = location.location
			return wrap_listing(self.conn.list_images(), CloudSigmaDriveImpl)
		except Exception, ex:
			wrap_exception(ex)
			
	def getTemplateBuilder(self):
		return CloudSigma_1_0_NodeTemplateImpl.newBuilder()

	def exDestroyNodeAndDrives(self, node):
		"""
        Destroy a node and all the drives associated with it.

        :param      node: Node which should be used
        :type       node: :class:`libcloud.compute.base.Node`

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_destroy_node_and_drives(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exStaticIpList(self):
		"""
        Return a list of available static IP addresses.

        :rtype: ``list`` of ``str``
        """
		try:
			return self.conn.ex_static_ip_list()
		except Exception, ex:
			raise wrap_exception(ex)

	def exDrivesList(self):
		"""
        Return a list of all the available drives.

        :rtype: ``list`` of ``dict``
        """
		try:
			return self.conn.ex_drives_list()
		except Exception, ex:
			raise wrap_exception(ex)

	def exStaticIpCreate(self):
		"""
        Create a new static IP address.p

        :rtype: ``list`` of ``dict``
        """
		try:
			return self.conn.ex_static_ip_create()
		except Exception, ex:
			raise wrap_exception(ex)

	def exStaticIpDestroy(self, ip_address):
		"""
        Destroy a static IP address.

        :param      ip_address: IP address which should be used
        :type       ip_address: ``str``

        :rtype: ``bool``
        """
		try:
			return self.conn.ex_static_ip_destroy(ip_address)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDriveDestroy(self, drive_uuid):
		"""
        Destroy a drive with a specified uuid.
        If the drive is currently mounted an exception is thrown.

        :param      drive_uuid: Drive uuid which should be used
        :type       drive_uuid: ``str``

        :rtype: ``bool``
        """
		try:
			return self.conn.ex_drive_destroy(drive_uuid)
		except Exception, ex:
			raise wrap_exception(ex)

	def exSetNodeConfiguration(self, node, kwargs):
		"""
        Update a node configuration.
        Changing most of the parameters requires node to be stopped.

        :param      node: Node which should be used
        :type       node: :class:`libcloud.compute.base.Node`

        :param      kwargs: keyword arguments
        :type       kwargs: ``dict``

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			kwargs = jmap_to_pymap(kwargs)
			return self.conn.ex_set_node_configuration(node, **kwargs)
		except Exception, ex:
			raise wrap_exception(ex)

	def exStartNode(self, node):
		"""
        Start a node.

        :param      node: Node which should be used
        :type       node: :class:`libcloud.compute.base.Node`

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_start_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exStopNode(self, node):
		"""
        Stop (shutdown) a node.

        :param      node: Node which should be used
        :type       node: :class:`libcloud.compute.base.Node`

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_stop_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exShutdownNode(self, node):
		"""
        Stop (shutdown) a node.

        @inherits: :class:`CloudSigmaBaseNodeDriver.ex_stop_node`
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_shutdown_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDestroyDrive(self, drive_uuid):
		"""
        Destroy a drive.

        :param      drive_uuid: Drive uuid which should be used
        :type       drive_uuid: ``str``

        :rtype: ``bool``
        """
		try:
			return self.conn.ex_destroy_drive(drive_uuid)
		except Exception, ex:
			raise wrap_exception(ex)

from org.askalon.jlibcloud.compute.driverSpecific.cloudsigma import CloudSigma_2_0_ComputeContext

class CloudSigma_2_0_ComputeContextImpl(ComputeContextImpl, CloudSigma_2_0_ComputeContext):
	def __init__(self, builder):
		ComputeContextImpl.__init__(self, builder)

	def createNode(self, node_temp):
		"""
        Create a new server.

        Server creation consists multiple steps depending on the type of the
        image used.

        1. Installation CD:

            1. Create a server and attach installation cd
            2. Start a server

        2. Pre-installed image:

            1. Clone provided library drive so we can use it
            2. Resize cloned drive to the desired size
            3. Create a server and attach cloned drive
            4. Start a server

        :param ex_metadata: Key / value pairs to associate with the
                            created node. (optional)
        :type ex_metadata: ``dict``

        :param ex_vnc_password: Password to use for VNC access. If not
                                provided, random password is generated.
        :type ex_vnc_password: ``str``

        :param ex_avoid: A list of server UUIDs to avoid when starting this
                         node. (optional)
        :type ex_avoid: ``list``

        :param ex_vlan: Optional UUID of a VLAN network to use. If specified,
                        server will have two nics assigned - 1 with a public ip
                        and 1 with the provided VLAN.
        :type ex_vlan: ``str``
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_cloudsigma_2_0__template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_cloudsigma_2_0__template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_cloudsigma_2_0__template(self, node_temp, kwargs):
		name = node_temp.getName()
		kwargs = get_property(self, name, 'name',
					 kwargs,lambda x : x)
		size = node_temp.getSize()
		kwargs = get_property(self, size, 'size',
					 kwargs,lambda x : x.obj)
		image = node_temp.getImage()
		kwargs = get_property(self, image, 'image',
					 kwargs,lambda x : x.obj)
		ex_metadata = node_temp.getExMetadata()
		if ex_metadata:
			kwargs['ex_metadata'] = jmap_to_pymap(ex_metadata)
		ex_vnc_password = node_temp.getExVncPassword()
		kwargs = get_property(self, ex_vnc_password, 'ex_vnc_password',
					 kwargs,lambda x : x)
		ex_avoid = node_temp.getExAvoid()
		kwargs = get_property_list(self, ex_avoid, 'ex_avoid',
					 kwargs,lambda x : jlist_str_to_pylist(x))
		ex_vlan = node_temp.getExVlan()
		kwargs = get_property(self, ex_vlan, 'ex_vlan',
					 kwargs,lambda x : x)
		return kwargs
	
	def listSizes(self, location=None):
		try:
			if location:
				location = location.obj
			return wrap_listing(self.conn.list_sizes(location), CloudSigmaNodeSizeImpl)
		except Exception, ex:
			raise wrap_exception(ex)
			
	def listImages(self, location=None):
		try:
			if location:
				location = location.location
			return wrap_listing(self.conn.list_images(), CloudSigmaDriveImpl)
		except Exception, ex:
			wrap_exception(ex)
		
	def getTemplateBuilder(self):
		return CloudSigma_2_0_NodeTemplateImpl.newBuilder()

	def exEditNode(self, node, params):
		"""
        Edit a node.

        :param node: Node to edit.
        :type node: :class:`libcloud.compute.base.Node`

        :param params: Node parameters to update.
        :type params: ``dict``

        :return Edited node.
        :rtype: :class:`libcloud.compute.base.Node`
        """
		try:
			if node:
				node = node.obj
			params = jmap_to_pymap(params)
			return NodeImpl(self.conn.ex_edit_node(node, params))
		except Exception, ex:
			raise wrap_exception(ex)

	def exStartNode(self, node, ex_avoid=None):
		"""
        Start a node.

        :param node: Node to start.
        :type node: :class:`libcloud.compute.base.Node`

        :param ex_avoid: A list of other server uuids to avoid when
                         starting this node. If provided, node will
                         attempt to be started on a different
                         physical infrastructure from other servers
                         specified using this argument. (optional)
        :type ex_avoid: ``list``
        """
		try:
			if not ex_avoid:
				ex_avoid = None
			if node:
				node = node.obj
			return self.conn.ex_start_node(node, ex_avoid)
		except Exception, ex:
			raise wrap_exception(ex)

	def exStopNode(self, node):
		"""
        Stop a node.
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_stop_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCloneNode(self, node, name=None, random_vnc_password=None):
		"""
        Clone the provided node.

        :param name: Optional name for the cloned node.
        :type name: ``str``
        :param random_vnc_password: If True, a new random VNC password will be
                                    generated for the cloned node. Otherwise
                                    password from the cloned node will be
                                    reused.
        :type random_vnc_password: ``bool``

        :return: Cloned node.
        :rtype: :class:`libcloud.compute.base.Node`
        """
		try:
			if not random_vnc_password:
				random_vnc_password = None
			if not name:
				name = None
			if node:
				node = node.obj
			return NodeImpl(self.conn.ex_clone_node(node, name, random_vnc_password))
		except Exception, ex:
			raise wrap_exception(ex)

	def exOpenVncTunnel(self, node):
		"""
        Open a VNC tunnel to the provided node and return the VNC url.

        :param node: Node to open the VNC tunnel to.
        :type node: :class:`libcloud.compute.base.Node`

        :return: URL of the opened VNC tunnel.
        :rtype: ``str``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_open_vnc_tunnel(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCloseVncTunnel(self, node):
		"""
        Close a VNC server to the provided node.

        :param node: Node to close the VNC tunnel to.
        :type node: :class:`libcloud.compute.base.Node`

        :return: ``True`` on success, ``False`` otherwise.
        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_close_vnc_tunnel(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListLibraryDrives(self):
		"""
        Return a list of all the available library drives (pre-installed and
        installation CDs).

        :rtype: ``list`` of :class:`.CloudSigmaDrive` objects
        """
		try:
			return wrap_listing(self.conn.ex_list_library_drives(), CloudSigmaDriveImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListUserDrives(self):
		"""
        Return a list of all the available user's drives.

        :rtype: ``list`` of :class:`.CloudSigmaDrive` objects
        """
		try:
			return wrap_listing(self.conn.ex_list_user_drives(), CloudSigmaDriveImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateDrive(self, name, size, media='disk', ex_avoid=None):
		"""
        Create a new drive.

        :param name: Drive name.
        :type name: ``str``

        :param size: Drive size in bytes.
        :type size: ``int``

        :param media: Drive media type (cdrom, disk).
        :type media: ``str``

        :param ex_avoid: A list of other drive uuids to avoid when
                         creating this drive. If provided, drive will
                         attempt to be created on a different
                         physical infrastructure from other drives
                         specified using this argument. (optional)
        :type ex_avoid: ``list``

        :return: Created drive object.
        :rtype: :class:`.CloudSigmaDrive`
        """
		try:
			if not ex_avoid:
				ex_avoid = None
			if not media:
				media = 'disk'
			ex_avoid = jlist_str_to_pylist(ex_avoid)
			return CloudSigmaDriveImpl(self.conn.ex_create_drive(name, size, media, ex_avoid))
		except Exception, ex:
			raise wrap_exception(ex)

	def exCloneDrive(self, drive, name=None, ex_avoid=None):
		"""
        Clone a library or a standard drive.

        :param drive: Drive to clone.
        :type drive: :class:`libcloud.compute.base.NodeImage` or
                     :class:`.CloudSigmaDrive`

        :param name: Optional name for the cloned drive.
        :type name: ``str``

        :param ex_avoid: A list of other drive uuids to avoid when
                         creating this drive. If provided, drive will
                         attempt to be created on a different
                         physical infrastructure from other drives
                         specified using this argument. (optional)
        :type ex_avoid: ``list``

        :return: New cloned drive.
        :rtype: :class:`.CloudSigmaDrive`
        """
		try:
			if not ex_avoid:
				ex_avoid = None
			if not name:
				name = None
			if drive:
				drive = drive.obj
			ex_avoid = jlist_str_to_pylist(ex_avoid)
			return CloudSigmaDriveImpl(self.conn.ex_clone_drive(drive, name, ex_avoid))
		except Exception, ex:
			raise wrap_exception(ex)

	def exResizeDrive(self, drive, size):
		"""
        Resize a drive.

        :param drive: Drive to resize.

        :param size: New drive size in bytes.
        :type size: ``int``

        :return: Drive object which is being resized.
        :rtype: :class:`.CloudSigmaDrive`
        """
		try:
			return CloudSigmaDriveImpl(self.conn.ex_resize_drive(drive, size))
		except Exception, ex:
			raise wrap_exception(ex)

	def exAttachDrive(self, node):
		"""
        Attach a drive to the provided node.
        """
		try:
			if node:
				node = node.obj
			self.conn.ex_attach_drive(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetDrive(self, drive_id):
		"""
        Retrieve information about a single drive.

        :param drive_id: ID of the drive to retrieve.
        :type drive_id: ``str``

        :return: Drive object.
        :rtype: :class:`.CloudSigmaDrive`
        """
		try:
			return CloudSigmaDriveImpl(self.conn.ex_get_drive(drive_id))
		except Exception, ex:
			raise wrap_exception(ex)

	def exListFirewallPolicies(self):
		"""
        List firewall policies.

        :rtype: ``list`` of :class:`.CloudSigmaFirewallPolicy`
        """
		try:
			return wrap_listing(self.conn.ex_list_firewall_policies(), CloudSigmaFirewallPolicyImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateFirewallPolicy(self, name, rules=None):
		"""
        Create a firewall policy.

        :param name: Policy name.
        :type name: ``str``

        :param rules: List of firewall policy rules to associate with this
                      policy. (optional)
        :type rules: ``list`` of ``dict``

        :return: Created firewall policy object.
        :rtype: :class:`.CloudSigmaFirewallPolicy`
        """
		try:
			if not rules:
				rules = None
			rules = jlist_map_to_pylist_map(rules)
			return CloudSigmaFirewallPolicyImpl(self.conn.ex_create_firewall_policy(name, rules))
		except Exception, ex:
			raise wrap_exception(ex)

	def exAttachFirewallPolicy(self, policy, node, nic_mac=None):
		"""
        Attach firewall policy to a public NIC interface on the server.

        :param policy: Firewall policy to attach.
        :type policy: :class:`.CloudSigmaFirewallPolicy`

        :param node: Node to attach policy to.
        :type node: :class:`libcloud.compute.base.Node`

        :param nic_mac: Optional MAC address of the NIC to add the policy to.
                        If not specified, first public interface is used
                        instead.
        :type nic_mac: ``str``

        :return: Node object to which the policy was attached to.
        :rtype: :class:`libcloud.compute.base.Node`
        """
		try:
			if not nic_mac:
				nic_mac = None
			if node:
				node = node.obj
			if policy:
				policy = policy.obj
			return NodeImpl(self.conn.ex_attach_firewall_policy(policy, node, nic_mac))
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeleteFirewallPolicy(self, policy):
		"""
        Delete a firewall policy.

        :param policy: Policy to delete to.
        :type policy: :class:`.CloudSigmaFirewallPolicy`

        :return: ``True`` on success, ``False`` otherwise.
        :rtype: ``bool``
        """
		try:
			if policy:
				policy = policy.obj
			return self.conn.ex_delete_firewall_policy(policy)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListServersAvailabilityGroups(self):
		"""
        Return which running servers share the same physical compute host.

        :return: A list of server UUIDs which share the same physical compute
                 host. Servers which share the same host will be stored under
                 the same list index.
        :rtype: ``list`` of ``list``
        """
		try:
			return self.conn.ex_list_servers_availability_groups()
		except Exception, ex:
			raise wrap_exception(ex)

	def exListDrivesAvailabilityGroups(self):
		"""
        Return which drives share the same physical storage host.

        :return: A list of drive UUIDs which share the same physical storage
                 host. Drives which share the same host will be stored under
                 the same list index.
        :rtype: ``list`` of ``list``
        """
		try:
			return self.conn.ex_list_drives_availability_groups()
		except Exception, ex:
			raise wrap_exception(ex)

	def exListTags(self):
		"""
        List all the available tags.

        :rtype: ``list`` of :class:`.CloudSigmaTag` objects
        """
		try:
			return wrap_listing(self.conn.ex_list_tags(), CloudSigmaTagImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetTag(self, tag_id):
		"""
        Retrieve a single tag.

        :param tag_id: ID of the tag to retrieve.
        :type idtag_id: ``str``

        :rtype: ``list`` of :class:`.CloudSigmaTag` objects
        """
		try:
			return wrap_listing(self.conn.ex_get_tag(tag_id), CloudSigmaTagImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateTag(self, name, resource_uuids=None):
		"""
        Create a tag.

        :param name: Tag name.
        :type name: ``str``

        :param resource_uuids: Optional list of resource UUIDs to assign this
                               tag go.
        :type resource_uuids: ``list`` of ``str``

        :return: Created tag object.
        :rtype: :class:`.CloudSigmaTag`
        """
		try:
			if not resource_uuids:
				resource_uuids = None
			resource_uuids = jlist_str_to_pylist(resource_uuids)
			return CloudSigmaTagImpl(self.conn.ex_create_tag(name, resource_uuids))
		except Exception, ex:
			raise wrap_exception(ex)

	def exTagResource(self, resource, tag):
		"""
        Associate tag with the provided resource.

        :param resource: Resource to associate a tag with.
        :type resource: :class:`libcloud.compute.base.Node` or
                        :class:`.CloudSigmaDrive`

        :param tag: Tag to associate with the resources.
        :type tag: :class:`.CloudSigmaTag`

        :return: Updated tag object.
        :rtype: :class:`.CloudSigmaTag`
        """
		try:
			if tag:
				tag = tag.obj
			if resource:
				resource = resource.obj
			return CloudSigmaTagImpl(self.conn.ex_tag_resource(resource, tag))
		except Exception, ex:
			raise wrap_exception(ex)

	def exTagResources(self, resources, tag):
		"""
        Associate tag with the provided resources.

        :param resources: Resources to associate a tag with.
        :type resources: ``list`` of :class:`libcloud.compute.base.Node` or
                        :class:`.CloudSigmaDrive`

        :param tag: Tag to associate with the resources.
        :type tag: :class:`.CloudSigmaTag`

        :return: Updated tag object.
        :rtype: :class:`.CloudSigmaTag`
        """
		try:
			if tag:
				tag = tag.obj
			resources = jlist_obj_to_pylist(resources)
			return CloudSigmaTagImpl(self.conn.ex_tag_resources(resources, tag))
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeleteTag(self, tag):
		"""
        Delete a tag.

        :param tag: Tag to delete.
        :type tag: :class:`.CloudSigmaTag`

        :return: ``True`` on success, ``False`` otherwise.
        :rtype: ``bool``
        """
		try:
			if tag:
				tag = tag.obj
			return self.conn.ex_delete_tag(tag)
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetBalance(self):
		"""
        Retrueve account balance information.

        :return: Dictionary with two items ("balance" and "currency").
        :rtype: ``dict``
        """
		try:
			return self.conn.ex_get_balance()
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetPricing(self):
		"""
        Retrive pricing information that are applicable to the cloud.

        :return: Dictionary with pricing information.
        :rtype: ``dict``
        """
		try:
			return self.conn.ex_get_pricing()
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetUsage(self):
		"""
        Retrieve account current usage information.

        :return: Dictionary with two items ("balance" and "usage").
        :rtype: ``dict``
        """
		try:
			return self.conn.ex_get_usage()
		except Exception, ex:
			raise wrap_exception(ex)

	def exListSubscriptions(self, status='all', resources=None):
		"""
        List subscriptions for this account.

        :param status: Only return subscriptions with the provided status
                       (optional).
        :type status: ``str``
        :param resources: Only return subscriptions for the provided resources
                          (optional).
        :type resources: ``list``

        :rtype: ``list``
        """
		try:
			if not resources:
				resources = None
			if not status:
				status = 'all'
			resources = jlist_str_to_pylist(resources)
			return self.conn.ex_list_subscriptions(status, resources)
		except Exception, ex:
			raise wrap_exception(ex)

	def exToggleSubscriptionAutoRenew(self, subscription):
		"""
        Toggle subscription auto renew status.

        :param subscription: Subscription to toggle the auto renew flag for.
        :type subscription: :class:`.CloudSigmaSubscription`

        :return: ``True`` on success, ``False`` otherwise.
        :rtype: ``bool``
        """
		try:
			if subscription:
				subscription = subscription.obj
			return self.conn.ex_toggle_subscription_auto_renew(subscription)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateSubscription(self,amount, period, resource, auto_renew=False):
		"""
        Create a new subscription.

        :param amount: Subscription amount. For example, in dssd case this
                       would be disk size in gigabytes.
        :type amount: ``int``

        :param period: Subscription period. For example: 30 days, 1 week, 1
                                            month, ...
        :type period: ``str``

        :param resource: Resource the purchase the subscription for.
        :type resource: ``str``

        :param auto_renew: True to automatically renew the subscription.
        :type auto_renew: ``bool``
        """
		try:
			if not auto_renew:
				auto_renew = False
			return CloudSigmaSubscriptionImpl(self.conn.ex_create_subscription(amount, period, resource, auto_renew))
		except Exception, ex:
			raise wrap_exception(ex)

	def exListCapabilities(self):
		"""
        Retrieve all the basic and sensible limits of the API.

        :rtype: ``dict``
        """
		try:
			return self.conn.ex_list_capabilities()
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.cloudsigma import CloudSigmaNodeSize

class CloudSigmaNodeSizeImpl(NodeSizeImpl, CloudSigmaNodeSize):

	def __init__(self, obj):
		NodeSizeImpl.__init__(self, obj)
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
		if hasattr(obj, 'cpu'):
			self.cpup = none_check(obj.cpu, ' ')
		else:
			self.cpup = ' '
		if hasattr(obj, 'ram'):
			self.ramp = none_check(obj.ram, -1)
		else:
			self.ramp = -1
		if hasattr(obj, 'disk'):
			self.diskp = none_check(obj.disk, -1)
		else:
			self.diskp = -1
		if hasattr(obj, 'bandwidth'):
			self.bandwidthp = none_check(obj.bandwidth, -1)
		else:
			self.bandwidthp = -1
		if hasattr(obj, 'price'):
			self.pricep = none_check(obj.price, -1.0)
		else:
			self.pricep = -1.0
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getName(self):
		return self.namep

	def getCpu(self):
		return self.cpup

	def getRam(self):
		return self.ramp

	def getDisk(self):
		return self.diskp

	def getBandwidth(self):
		return self.bandwidthp

	def getPrice(self):
		return self.pricep

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.cloudsigma import CloudSigmaError

class CloudSigmaErrorImpl(CloudSigmaError):
	'''
    Represents CloudSigma API error.
	'''

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'http_code'):
			self.http_codep = none_check(obj.http_code, -1)
		else:
			self.http_codep = -1
		if hasattr(obj, 'error_type'):
			self.error_typep = none_check(obj.error_type, ' ')
		else:
			self.error_typep = ' '
		if hasattr(obj, 'error_msg'):
			self.error_msgp = none_check(obj.error_msg, ' ')
		else:
			self.error_msgp = ' '
		if hasattr(obj, 'error_point'):
			self.error_pointp = none_check(obj.error_point, ' ')
		else:
			self.error_pointp = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getHttpCode(self):
		return self.http_codep

	def getErrorType(self):
		return self.error_typep

	def getErrorMsg(self):
		return self.error_msgp

	def getErrorPoint(self):
		return self.error_pointp

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.cloudsigma import CloudSigmaSubscription

class CloudSigmaSubscriptionImpl(CloudSigmaSubscription):
	'''
    Represents CloudSigma subscription.
	'''

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'resource'):
			self.resourcep = none_check(obj.resource, ' ')
		else:
			self.resourcep = ' '
		if hasattr(obj, 'period'):
			self.periodp = none_check(obj.period, ' ')
		else:
			self.periodp = ' '
		if hasattr(obj, 'status'):
			self.statusp = none_check(obj.status, ' ')
		else:
			self.statusp = ' '
		if hasattr(obj, 'price'):
			self.pricep = none_check(obj.price, -1.0)
		else:
			self.pricep = -1.0
		if hasattr(obj, 'start_time'):
			self.start_timep = none_check(obj.start_time, ' ')
		else:
			self.start_timep = ' '
		if hasattr(obj, 'end_time'):
			self.end_timep = none_check(obj.end_time, ' ')
		else:
			self.end_timep = ' '
		if hasattr(obj, 'auto_renew'):
			self.auto_renewp = none_check(obj.auto_renew, ' ')
		else:
			self.auto_renewp = ' '
		if hasattr(obj, 'subscribed_object'):
			self.subscribed_objectp = none_check(obj.subscribed_object, ' ')
		else:
			self.subscribed_objectp = ' '
		if hasattr(obj, 'amount'):
			self.amountp = none_check(obj.amount, ' ')
		else:
			self.amountp = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getResource(self):
		return self.resourcep

	def getPeriod(self):
		return self.periodp

	def getStatus(self):
		return self.statusp

	def getPrice(self):
		return self.pricep

	def getStartTime(self):
		return self.start_timep

	def getEndTime(self):
		return self.end_timep

	def getAutoRenew(self):
		return self.auto_renewp

	def getSubscribedObject(self):
		return self.subscribed_objectp

	def getAmount(self):
		return self.amountp

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.cloudsigma import CloudSigmaTag

class CloudSigmaTagImpl(CloudSigmaTag):
	'''
    Represents a CloudSigma tag object.
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
		if hasattr(obj, 'resource'):
			self.resourcep = none_check(obj.resource, ' ')
		else:
			self.resourcep = ' '
		if hasattr(obj, 'resources'):
			self.resourcesp = none_check(obj.resources, ' ')
		else:
			self.resourcesp = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getName(self):
		return self.namep

	def getResource(self):
		return self.resourcep

	def getResources(self):
		return self.resourcesp

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.cloudsigma import CloudSigmaDrive

class CloudSigmaDriveImpl(NodeImageImpl, CloudSigmaDrive):
	'''
    Represents a CloudSigma drive.
	'''

	def __init__(self, obj):
		NodeImageImpl.__init__(self, obj)
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
		if hasattr(obj, 'size'):
			self.sizep = none_check(obj.size, -1)
		else:
			self.sizep = -1
		if hasattr(obj, 'media'):
			self.mediap = none_check(obj.media, ' ')
		else:
			self.mediap = ' '
		if hasattr(obj, 'status'):
			self.statusp = none_check(obj.status, ' ')
		else:
			self.statusp = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getName(self):
		return self.namep

	def getSize(self):
		return self.sizep

	def getMedia(self):
		return self.mediap

	def getStatus(self):
		return self.statusp

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.cloudsigma import CloudSigmaFirewallPolicy

class CloudSigmaFirewallPolicyImpl(CloudSigmaFirewallPolicy):
	'''
    Represents a CloudSigma firewall policy.
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
		if hasattr(obj, 'rules'):
			self.rulesp = none_check(obj.rules, ' ')
		else:
			self.rulesp = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getName(self):
		return self.namep

	def getRules(self):
		return self.rulesp

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.cloudsigma import CloudSigmaFirewallPolicyRule

class CloudSigmaFirewallPolicyRuleImpl(CloudSigmaFirewallPolicyRule):
	'''
    Represents a CloudSigma firewall policy rule.
	'''

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'action'):
			self.actionp = none_check(obj.action, ' ')
		else:
			self.actionp = ' '
		if hasattr(obj, 'direction'):
			self.directionp = none_check(obj.direction, ' ')
		else:
			self.directionp = ' '
		if hasattr(obj, 'ip_proto'):
			self.ip_protop = none_check(obj.ip_proto, ' ')
		else:
			self.ip_protop = ' '
		if hasattr(obj, 'src_ip'):
			self.src_ipp = none_check(obj.src_ip, ' ')
		else:
			self.src_ipp = ' '
		if hasattr(obj, 'src_port'):
			self.src_portp = none_check(obj.src_port, ' ')
		else:
			self.src_portp = ' '
		if hasattr(obj, 'dst_ip'):
			self.dst_ipp = none_check(obj.dst_ip, ' ')
		else:
			self.dst_ipp = ' '
		if hasattr(obj, 'comment'):
			self.commentp = none_check(obj.comment, ' ')
		else:
			self.commentp = ' '
		if hasattr(obj, 'dst_port'):
			self.dst_portp = none_check(obj.dst_port, ' ')
		else:
			self.dst_portp = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getAction(self):
		return self.actionp

	def getDirection(self):
		return self.directionp

	def getIpProto(self):
		return self.ip_protop

	def getSrcIp(self):
		return self.src_ipp

	def getSrcPort(self):
		return self.src_portp

	def getDstIp(self):
		return self.dst_ipp

	def getComment(self):
		return self.commentp

	def getDstPort(self):
		return self.dst_portp

	def toString(self):
		return self.reprp

