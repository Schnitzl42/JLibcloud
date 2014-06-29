# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/ec2.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/ec2.py
#
# Apache Libcloud is licensed under the Apache 2.0 license.
# For more information, please see LICENSE and NOTICE file or:
# http://www.apache.org/licenses/LICENSE-2.0
from javaimpl.compute.ComputeContextImpl import ComputeContextImpl
from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception, jlist_str_to_pylist
from javaimpl.compute.utils import jlist_obj_to_pylist, get_property, get_property_list
from javaimpl.compute.utils import jmap_to_pymap, jlist_map_to_pylist_map
from javaimpl.compute.base.NodeLocationImpl import NodeLocationImpl
from javaimpl.compute.base.NodeImageImpl import NodeImageImpl
from javaimpl.compute.base.NodeImpl import NodeImpl
from org.askalon.jlibcloud.compute.driverSpecific.ec2 import BaseEC2NodeTemplateImpl
from javaimpl.compute.base.NodeSizeImpl import NodeSizeImpl
from org.askalon.jlibcloud.compute.driverSpecific.ec2 import BaseEC2ComputeContext

class BaseEC2ComputeContextImpl(ComputeContextImpl, BaseEC2ComputeContext):
	def __init__(self, builder):
		ComputeContextImpl.__init__(self, builder)

	def createNode(self, node_temp):
		"""
        Create a new EC2 node.

        Reference: http://bit.ly/8ZyPSy [docs.amazonwebservices.com]

        @inherits: :class:`NodeDriver.create_node`

        :keyword    ex_keyname: The name of the key pair
        :type       ex_keyname: ``str``

        :keyword    ex_userdata: User data
        :type       ex_userdata: ``str``

        :keyword    ex_security_groups: A list of names of security groups to
                                        assign to the node.
        :type       ex_security_groups:   ``list``

        :keyword    ex_metadata: Key/Value metadata to associate with a node
        :type       ex_metadata: ``dict``

        :keyword    ex_mincount: Minimum number of instances to launch
        :type       ex_mincount: ``int``

        :keyword    ex_maxcount: Maximum number of instances to launch
        :type       ex_maxcount: ``int``

        :keyword    ex_clienttoken: Unique identifier to ensure idempotency
        :type       ex_clienttoken: ``str``

        :keyword    ex_blockdevicemappings: ``list`` of ``dict`` block device
                    mappings.
        :type       ex_blockdevicemappings: ``list`` of ``dict``

        :keyword    ex_iamprofile: Name or ARN of IAM profile
        :type       ex_iamprofile: ``str``
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_baseec2_template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_baseec2_template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_baseec2_template(self, node_temp, kwargs):
		ex_keyname = node_temp.getExKeyname()
		kwargs = get_property(self, ex_keyname, 'ex_keyname',
					 kwargs,lambda x : x)
		ex_userdata = node_temp.getExUserdata()
		kwargs = get_property(self, ex_userdata, 'ex_userdata',
					 kwargs,lambda x : x)
		ex_security_groups = node_temp.getExSecurityGroups()
		kwargs = get_property_list(self, ex_security_groups, 'ex_security_groups',
					 kwargs,lambda x : jlist_str_to_pylist(x))
		ex_metadata = node_temp.getExMetadata()
		if ex_metadata:
			kwargs['ex_metadata'] = jmap_to_pymap(ex_metadata)
		ex_mincount = node_temp.getExMincount()
		kwargs = get_property(self, ex_mincount, 'ex_mincount',
					 kwargs,lambda x : int(x))
		ex_maxcount = node_temp.getExMaxcount()
		kwargs = get_property(self, ex_maxcount, 'ex_maxcount',
					 kwargs,lambda x : int(x))
		ex_clienttoken = node_temp.getExClienttoken()
		kwargs = get_property(self, ex_clienttoken, 'ex_clienttoken',
					 kwargs,lambda x : x)
		ex_blockdevicemappings = node_temp.getExBlockdevicemappings()
		if ex_blockdevicemappings:
			kwargs['ex_blockdevicemappings'] = jlist_map_to_pylist_map(ex_blockdevicemappings)
		ex_iamprofile = node_temp.getExIamprofile()
		kwargs = get_property(self, ex_iamprofile, 'ex_iamprofile',
					 kwargs,lambda x : x)
		return kwargs

	def getTemplateBuilder(self):
		return BaseEC2NodeTemplateImpl.newBuilder()
	
	def listLocations(self):
		try:
			return wrap_listing(self.conn.list_locations(), EC2NodeLocationImpl)
		except Exception, ex:
			wrap_exception(ex)

	def exCopyImage(self, source_region, image, name=None, description=None):
		"""
        Copy an Amazon Machine Image from the specified source region
        to the current region.

        :param      source_region: The region where the image resides
        :type       source_region: ``str``

        :param      image: Instance of class NodeImage
        :type       image: :class:`NodeImage`

        :param      name: The name of the new image
        :type       name: ``str``

        :param      description: The description of the new image
        :type       description: ``str``

        :return:    Instance of class ``NodeImage``
        :rtype:     :class:`NodeImage`
        """
		try:
			if not description:
				description = None
			if not name:
				name = None
			if image:
				image = image.obj
			return NodeImageImpl(self.conn.ex_copy_image(source_region ,image ,name ,description))
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateImageFromNode(self, node, name, block_device_mapping,
                                  reboot=False, description=None):
		"""
        Create an Amazon Machine Image based off of an EBS-backed instance.

        :param      node: Instance of ``Node``
        :type       node: :class: `Node`

        :param      name: The name for the new image
        :type       name: ``str``

        :param      block_device_mapping: A dictionary of the disk layout
                                          An example of this dict is included
                                          below.
        :type       block_device_mapping: ``list`` of ``dict``

        :param      reboot: Whether or not to shutdown the instance before
                               creation. By default Amazon sets this to false
                               to ensure a clean image.
        :type       reboot: ``bool``

        :param      description: An optional description for the new image
        :type       description: ``str``

        An example block device mapping dictionary is included:

        mapping = [{'VirtualName': None,
                    'Ebs': {'VolumeSize': 10,
                            'VolumeType': 'standard',
                            'DeleteOnTermination': 'true'},
                            'DeviceName': '/dev/sda1'}]

        :return:    Instance of class ``NodeImage``
        :rtype:     :class:`NodeImage`
        """
		try:
			if not description:
				description = None
			if not reboot:
				reboot = False
			if node:
				node = node.obj
			block_device_mapping = jlist_map_to_pylist_map(block_device_mapping)
			return NodeImageImpl(self.conn.ex_create_image_from_node(node ,name ,block_device_mapping ,reboot ,description))
		except Exception, ex:
			raise wrap_exception(ex)

	def exDestroyImage(self, image):
		try:
			if image:
				image = image.obj
			self.conn.ex_destroy_image(image)
		except Exception, ex:
			raise wrap_exception(ex)

	def exRegisterImage(self, name, description=None, architecture=None,
                          image_location=None, root_device_name=None,
                          block_device_mapping=None, kernel_id=None,
                          ramdisk_id=None):
		"""
        Registers an Amazon Machine Image based off of an EBS-backed instance.
        Can also be used to create images from snapshots. More information
        can be found at http://goo.gl/hqZq0a.

        :param      name:  The name for the AMI being registered
        :type       name: ``str``

        :param      description: The description of the AMI (optional)
        :type       description: ``str``

        :param      architecture: The architecture of the AMI (i386/x86_64)
                                  (optional)
        :type       architecture: ``str``

        :param      image_location: The location of the AMI within Amazon S3
                                    Required if registering an instance
                                    store-backed AMI
        :type       image_location: ``str``

        :param      root_device_name: The device name for the root device
                                      Required if registering a EBS-backed AMI
        :type       root_device_name: ``str``

        :param      block_device_mapping: A dictionary of the disk layout
                                          (optional)
        :type       block_device_mapping: ``dict``

        :param      kernel_id: Kernel id for AMI (optional)
        :type       kernel_id: ``str``

        :param      ramdisk_id: RAM disk for AMI (optional)
        :type       ramdisk_id: ``str``

        :rtype:     :class:`NodeImage`
        """
		try:
			if not ramdisk_id:
				ramdisk_id = None
			if not kernel_id:
				kernel_id = None
			if not block_device_mapping:
				block_device_mapping = None
			if not root_device_name:
				root_device_name = None
			if not image_location:
				image_location = None
			if not architecture:
				architecture = None
			if not description:
				description = None
			block_device_mapping = jmap_to_pymap(block_device_mapping)
			return NodeImageImpl(self.conn.ex_register_image(name ,description ,architecture ,image_location ,root_device_name ,block_device_mapping ,kernel_id ,ramdisk_id))
		except Exception, ex:
			raise wrap_exception(ex)

	def exListNetworks(self):
		"""
        Return a list of :class:`EC2Network` objects for the
        current region.

        :rtype:     ``list`` of :class:`EC2Network`
        """
		try:
			return wrap_listing(self.conn.ex_list_networks(), EC2NetworkImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateNetwork(self, cidr_block, name=None,
                          instance_tenancy='default'):
		"""
        Create a network/VPC

        :param      cidr_block: The CIDR block assigned to the network
        :type       cidr_block: ``str``

        :param      name: An optional name for the network
        :type       name: ``str``

        :param      instance_tenancy: The allowed tenancy of instances launched
                                      into the VPC.
                                      Valid values: default/dedicated
        :type       instance_tenancy: ``str``

        :return:    Dictionary of network properties
        :rtype:     ``dict``
        """
		try:
			if not instance_tenancy:
				instance_tenancy = 'default'
			if not name:
				name = None
			return self.conn.ex_create_network(cidr_block ,name ,instance_tenancy)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeleteNetwork(self, vpc):
		"""
        Deletes a network/VPC.

        :param      vpc: VPC to delete.
        :type       vpc: :class:`.EC2Network`

        :rtype:     ``bool``
        """
		try:
			if vpc:
				vpc = vpc.obj
			return self.conn.ex_delete_network(vpc)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListSubnets(self):
		"""
        Return a list of :class:`EC2NetworkSubnet` objects for the
        current region.

        :rtype:     ``list`` of :class:`EC2NetworkSubnet`
        """
		try:
			return wrap_listing(self.conn.ex_list_subnets(), EC2NetworkSubnetImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateSubnet(self, vpc_id, cidr_block,
                         availability_zone, name=None):
		"""
        Create a network subnet within a VPC

        :param      vpc_id: The ID of the VPC that the subnet should be
                            associated with
        :type       vpc_id: ``str``

        :param      cidr_block: The CIDR block assigned to the subnet
        :type       cidr_block: ``str``

        :param      availability_zone: The availability zone where the subnet
                                       should reside
        :type       availability_zone: ``str``

        :param      name: An optional name for the network
        :type       name: ``str``

        :rtype:     :class: `EC2NetworkSubnet`
        """
		try:
			if not name:
				name = None
			return EC2NetworkSubnetImpl(self.conn.ex_create_subnet(vpc_id ,cidr_block ,availability_zone ,name))
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeleteSubnet(self, subnet):
		"""
        Deletes a VPC subnet.

        :param      subnet: The subnet to delete
        :type       subnet: :class:`.EC2NetworkSubnet`

        :rtype:     ``bool``
        """
		try:
			if subnet:
				subnet = subnet.obj
			return self.conn.ex_delete_subnet(subnet)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListSecurityGroups(self):
		"""
        List existing Security Groups.

        @note: This is a non-standard extension API, and only works for EC2.

        :rtype: ``list`` of ``str``
        """
		try:
			return self.conn.ex_list_security_groups()
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateSecurityGroup(self, name, description, vpc_id=None):
		"""
        Creates a new Security Group in EC2-Classic or a targetted VPC.

        :param      name:        The name of the security group to Create.
                                 This must be unique.
        :type       name:        ``str``

        :param      description: Human readable description of a Security
                                 Group.
        :type       description: ``str``

        :param      description: Optional identifier for VPC networks
        :type       description: ``str``

        :rtype: ``dict``
        """
		try:
			if not vpc_id:
				vpc_id = None
			return self.conn.ex_create_security_group(name ,description ,vpc_id)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeleteSecurityGroupById(self, group_id):
		"""
        Deletes a new Security Group using the group id.

        :param      group_id: The ID of the security group
        :type       group_id: ``str``

        :rtype: ``bool``
        """
		try:
			return self.conn.ex_delete_security_group_by_id(group_id)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeleteSecurityGroupByName(self, group_name):
		"""
        Deletes a new Security Group using the group name.

        :param      group_name: The name of the security group
        :type       group_name: ``str``

        :rtype: ``bool``
        """
		try:
			return self.conn.ex_delete_security_group_by_name(group_name)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeleteSecurityGroup(self, name):
		"""
        Wrapper method which calls ex_delete_security_group_by_name.

        :param      name: The name of the security group
        :type       name: ``str``

        :rtype: ``bool``
        """
		try:
			return self.conn.ex_delete_security_group(name)
		except Exception, ex:
			raise wrap_exception(ex)

	def exAuthorizeSecurityGroup(self, name, from_port, to_port, cidr_ip,
                                    protocol='tcp'):
		"""
        Edit a Security Group to allow specific traffic.

        @note: This is a non-standard extension API, and only works for EC2.

        :param      name: The name of the security group to edit
        :type       name: ``str``

        :param      from_port: The beginning of the port range to open
        :type       from_port: ``str``

        :param      to_port: The end of the port range to open
        :type       to_port: ``str``

        :param      cidr_ip: The ip to allow traffic for.
        :type       cidr_ip: ``str``

        :param      protocol: tcp/udp/icmp
        :type       protocol: ``str``

        :rtype: ``bool``
        """
		try:
			if not protocol:
				protocol = 'tcp'
			return self.conn.ex_authorize_security_group(name ,from_port ,to_port ,cidr_ip ,protocol)
		except Exception, ex:
			raise wrap_exception(ex)

	def exAuthorizeSecurityGroupIngress(self, id, from_port, to_port,
                                            cidr_ips=None, group_pairs=None,
                                            protocol='tcp'):
		"""
        Edit a Security Group to allow specific ingress traffic using
        CIDR blocks or either a group ID, group name or user ID (account).

        :param      id: The id of the security group to edit
        :type       id: ``str``

        :param      from_port: The beginning of the port range to open
        :type       from_port: ``int``

        :param      to_port: The end of the port range to open
        :type       to_port: ``int``

        :param      cidr_ips: The list of ip ranges to allow traffic for.
        :type       cidr_ips: ``list``

        :param      group_pairs: Source user/group pairs to allow traffic for.
                    More info can be found at http://goo.gl/stBHJF

                    EC2 Classic Example: To allow access from any system
                    associated with the default group on account 1234567890

                    [{'group_name': 'default', 'user_id': '1234567890'}]

                    VPC Example: Allow access from any system associated with
                    security group sg-47ad482e on your own account

                    [{'group_id': ' sg-47ad482e'}]
        :type       group_pairs: ``list`` of ``dict``

        :param      protocol: tcp/udp/icmp
        :type       protocol: ``str``

        :rtype: ``bool``
        """
		try:
			if not protocol:
				protocol = 'tcp'
			if not group_pairs:
				group_pairs = None
			if not cidr_ips:
				cidr_ips = None
			cidr_ips = jlist_str_to_pylist(cidr_ips)
			group_pairs = jlist_map_to_pylist_map(group_pairs)
			return self.conn.ex_authorize_security_group_ingress(id ,from_port ,to_port ,cidr_ips ,group_pairs ,protocol)
		except Exception, ex:
			raise wrap_exception(ex)

	def exAuthorizeSecurityGroupEgress(self, id, from_port, to_port,
                                           cidr_ips=None, group_pairs=None,
                                           protocol='tcp'):
		"""
        Edit a Security Group to allow specific egress traffic using
        CIDR blocks or either a group ID, group name or user ID (account).
        This call is not supported for EC2 classic and only works for VPC
        groups.

        :param      id: The id of the security group to edit
        :type       id: ``str``

        :param      from_port: The beginning of the port range to open
        :type       from_port: ``int``

        :param      to_port: The end of the port range to open
        :type       to_port: ``int``

        :param      cidr_ips: The list of ip ranges to allow traffic for.
        :type       cidr_ips: ``list``

        :param      group_pairs: Source user/group pairs to allow traffic for.
                    More info can be found at http://goo.gl/stBHJF

                    EC2 Classic Example: To allow access from any system
                    associated with the default group on account 1234567890

                    [{'group_name': 'default', 'user_id': '1234567890'}]

                    VPC Example: Allow access from any system associated with
                    security group sg-47ad482e on your own account

                    [{'group_id': ' sg-47ad482e'}]
        :type       group_pairs: ``list`` of ``dict``

        :param      protocol: tcp/udp/icmp
        :type       protocol: ``str``

        :rtype: ``bool``
        """
		try:
			if not protocol:
				protocol = 'tcp'
			if not group_pairs:
				group_pairs = None
			if not cidr_ips:
				cidr_ips = None
			cidr_ips = jlist_str_to_pylist(cidr_ips)
			group_pairs = jlist_map_to_pylist_map(group_pairs)
			return self.conn.ex_authorize_security_group_egress(id ,from_port ,to_port ,cidr_ips ,group_pairs ,protocol)
		except Exception, ex:
			raise wrap_exception(ex)

	def exRevokeSecurityGroupIngress(self, id, from_port, to_port,
                                         cidr_ips=None, group_pairs=None,
                                         protocol='tcp'):
		"""
        Edit a Security Group to revoke specific ingress traffic using
        CIDR blocks or either a group ID, group name or user ID (account).

        :param      id: The id of the security group to edit
        :type       id: ``str``

        :param      from_port: The beginning of the port range to open
        :type       from_port: ``int``

        :param      to_port: The end of the port range to open
        :type       to_port: ``int``

        :param      cidr_ips: The list of ip ranges to allow traffic for.
        :type       cidr_ips: ``list``

        :param      group_pairs: Source user/group pairs to allow traffic for.
                    More info can be found at http://goo.gl/stBHJF

                    EC2 Classic Example: To allow access from any system
                    associated with the default group on account 1234567890

                    [{'group_name': 'default', 'user_id': '1234567890'}]

                    VPC Example: Allow access from any system associated with
                    security group sg-47ad482e on your own account

                    [{'group_id': ' sg-47ad482e'}]
        :type       group_pairs: ``list`` of ``dict``

        :param      protocol: tcp/udp/icmp
        :type       protocol: ``str``

        :rtype: ``bool``
        """
		try:
			if not protocol:
				protocol = 'tcp'
			if not group_pairs:
				group_pairs = None
			if not cidr_ips:
				cidr_ips = None
			cidr_ips = jlist_str_to_pylist(cidr_ips)
			group_pairs = jlist_map_to_pylist_map(group_pairs)
			return self.conn.ex_revoke_security_group_ingress(id ,from_port ,to_port ,cidr_ips ,group_pairs ,protocol)
		except Exception, ex:
			raise wrap_exception(ex)

	def exRevokeSecurityGroupEgress(self, id, from_port, to_port,
                                        cidr_ips=None, group_pairs=None,
                                        protocol='tcp'):
		"""
        Edit a Security Group to revoke specific egress traffic using
        CIDR blocks or either a group ID, group name or user ID (account).
        This call is not supported for EC2 classic and only works for
        VPC groups.

        :param      id: The id of the security group to edit
        :type       id: ``str``

        :param      from_port: The beginning of the port range to open
        :type       from_port: ``int``

        :param      to_port: The end of the port range to open
        :type       to_port: ``int``

        :param      cidr_ips: The list of ip ranges to allow traffic for.
        :type       cidr_ips: ``list``

        :param      group_pairs: Source user/group pairs to allow traffic for.
                    More info can be found at http://goo.gl/stBHJF

                    EC2 Classic Example: To allow access from any system
                    associated with the default group on account 1234567890

                    [{'group_name': 'default', 'user_id': '1234567890'}]

                    VPC Example: Allow access from any system associated with
                    security group sg-47ad482e on your own account

                    [{'group_id': ' sg-47ad482e'}]
        :type       group_pairs: ``list`` of ``dict``

        :param      protocol: tcp/udp/icmp
        :type       protocol: ``str``

        :rtype: ``bool``
        """
		try:
			if not protocol:
				protocol = 'tcp'
			if not group_pairs:
				group_pairs = None
			if not cidr_ips:
				cidr_ips = None
			cidr_ips = jlist_str_to_pylist(cidr_ips)
			group_pairs = jlist_map_to_pylist_map(group_pairs)
			return self.conn.ex_revoke_security_group_egress(id ,from_port ,to_port ,cidr_ips ,group_pairs ,protocol)
		except Exception, ex:
			raise wrap_exception(ex)

	def exAuthorizeSecurityGroupPermissive(self, name):
		"""
        Edit a Security Group to allow all traffic.

        @note: This is a non-standard extension API, and only works for EC2.

        :param      name: The name of the security group to edit
        :type       name: ``str``

        :rtype: ``list`` of ``str``
        """
		try:
			return self.conn.ex_authorize_security_group_permissive(name)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListAvailabilityZones(self, only_available=True):
		"""
        Return a list of :class:`ExEC2AvailabilityZone` objects for the
        current region.

        Note: This is an extension method and is only available for EC2
        driver.

        :keyword  only_available: If true, return only availability zones
                                  with state 'available'
        :type     only_available: ``str``

        :rtype: ``list`` of :class:`ExEC2AvailabilityZone`
        """
		try:
			if not only_available:
				only_available = True
			return wrap_listing(self.conn.ex_list_availability_zones(only_available), ExEC2AvailabilityZoneImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDescribeTags(self, resource):
		"""
        Return a dictionary of tags for a resource (Node or StorageVolume).

        :param  resource: resource which should be used
        :type   resource: :class:`Node` or :class:`StorageVolume`

        :return: dict Node tags
        :rtype: ``dict``
        """
		try:
			if resource:
				resource = resource.obj
			return self.conn.ex_describe_tags(resource)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateTags(self, resource, tags):
		"""
        Create tags for a resource (Node or StorageVolume).

        :param resource: Resource to be tagged
        :type resource: :class:`Node` or :class:`StorageVolume`

        :param tags: A dictionary or other mapping of strings to strings,
                     associating tag names with tag values.
        :type tags: ``dict``

        :rtype: ``bool``
        """
		try:
			if resource:
				resource = resource.obj
			tags = jmap_to_pymap(tags)
			return self.conn.ex_create_tags(resource ,tags)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeleteTags(self, resource, tags):
		"""
        Delete tags from a resource.

        :param resource: Resource to be tagged
        :type resource: :class:`Node` or :class:`StorageVolume`

        :param tags: A dictionary or other mapping of strings to strings,
                     specifying the tag names and tag values to be deleted.
        :type tags: ``dict``

        :rtype: ``bool``
        """
		try:
			if resource:
				resource = resource.obj
			tags = jmap_to_pymap(tags)
			return self.conn.ex_delete_tags(resource ,tags)
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetMetadataForNode(self, node):
		"""
        Return the metadata associated with the node.

        :param      node: Node instance
        :type       node: :class:`Node`

        :return: A dictionary or other mapping of strings to strings,
                 associating tag names with tag values.
        :rtype tags: ``dict``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_get_metadata_for_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exAllocateAddress(self, domain='standard'):
		"""
        Allocate a new Elastic IP address for EC2 classic or VPC

        :param      domain: The domain to allocate the new address in
                            (standard/vpc)
        :type       domain: ``str``

        :return:    Instance of ElasticIP
        :rtype:     :class:`ElasticIP`
        """
		try:
			if not domain:
				domain = 'standard'
			return ElasticIPImpl(self.conn.ex_allocate_address(domain))
		except Exception, ex:
			raise wrap_exception(ex)

	def exReleaseAddress(self, elastic_ip, domain=None):
		"""
        Release an Elastic IP address using the IP (EC2-Classic) or
        using the allocation ID (VPC)

        :param      elastic_ip: Elastic IP instance
        :type       elastic_ip: :class:`ElasticIP`

        :param      domain: The domain where the IP resides (vpc only)
        :type       domain: ``str``

        :return:    True on success, False otherwise.
        :rtype:     ``bool``
        """
		try:
			if not domain:
				domain = None
			if elastic_ip:
				elastic_ip = elastic_ip.obj
			return self.conn.ex_release_address(elastic_ip ,domain)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDescribeAllAddresses(self, only_associated=False):
		"""
        Return all the Elastic IP addresses for this account
        optionally, return only addresses associated with nodes

        :param    only_associated: If true, return only those addresses
                                   that are associated with an instance.
        :type     only_associated: ``bool``

        :return:  List of ElasticIP instances.
        :rtype:   ``list`` of :class:`ElasticIP`
        """
		try:
			if not only_associated:
				only_associated = False
			return wrap_listing(self.conn.ex_describe_all_addresses(only_associated), ElasticIPImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exAssociateAddressWithNode(self, node, elastic_ip, domain=None):
		"""
        Associate an Elastic IP address with a particular node.

        :param      node: Node instance
        :type       node: :class:`Node`

        :param      elastic_ip: Elastic IP instance
        :type       elastic_ip: :class:`ElasticIP`

        :param      domain: The domain where the IP resides (vpc only)
        :type       domain: ``str``

        :return:    A string representation of the association ID which is
                    required for VPC disassociation. EC2/standard
                    addresses return None
        :rtype:     ``None`` or ``str``
        """
		try:
			if not domain:
				domain = None
			if elastic_ip:
				elastic_ip = elastic_ip.obj
			if node:
				node = node.obj
			return self.conn.ex_associate_address_with_node(node ,elastic_ip ,domain)
		except Exception, ex:
			raise wrap_exception(ex)

	def exAssociateAddresses(self, node, elastic_ip, domain=None):
		"""
        Note: This method has been deprecated in favor of
        the ex_associate_address_with_node method.
        """
		try:
			if not domain:
				domain = None
			if node:
				node = node.obj
			return self.conn.ex_associate_addresses(node ,elastic_ip ,domain)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDisassociateAddress(self, elastic_ip, domain=None):
		"""
        Disassociate an Elastic IP address using the IP (EC2-Classic)
        or the association ID (VPC)

        :param      elastic_ip: ElasticIP instance
        :type       elastic_ip: :class:`ElasticIP`

        :param      domain: The domain where the IP resides (vpc only)
        :type       domain: ``str``

        :return:    True on success, False otherwise.
        :rtype:     ``bool``
        """
		try:
			if not domain:
				domain = None
			if elastic_ip:
				elastic_ip = elastic_ip.obj
			return self.conn.ex_disassociate_address(elastic_ip ,domain)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDescribeAddresses(self, nodes):
		"""
        Return Elastic IP addresses for all the nodes in the provided list.

        :param      nodes: List of :class:`Node` instances
        :type       nodes: ``list`` of :class:`Node`

        :return:    Dictionary where a key is a node ID and the value is a
                    list with the Elastic IP addresses associated with
                    this node.
        :rtype:     ``dict``
        """
		try:
			nodes = jlist_obj_to_pylist(nodes)
			return self.conn.ex_describe_addresses(nodes)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDescribeAddressesForNode(self, node):
		"""
        Return a list of Elastic IP addresses associated with this node.

        :param      node: Node instance
        :type       node: :class:`Node`

        :return: list Elastic IP addresses attached to this node.
        :rtype: ``list`` of ``str``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_describe_addresses_for_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListNetworkInterfaces(self):
		"""
        Return all network interfaces

        :return:    List of EC2NetworkInterface instances
        :rtype:     ``list`` of :class `EC2NetworkInterface`
        """
		try:
			return wrap_listing(self.conn.ex_list_network_interfaces(), EC2NetworkInterfaceImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateNetworkInterface(self, subnet, name=None,
                                    description=None,
                                    private_ip_address=None):
		"""
        Create a network interface within a VPC subnet.

        :param      subnet: EC2NetworkSubnet instance
        :type       subnet: :class:`EC2NetworkSubnet`

        :param      name:  Optional name of the interface
        :type       name:  ``str``

        :param      description:  Optional description of the network interface
        :type       description:  ``str``

        :param      private_ip_address: Optional address to assign as the
                                        primary private IP address of the
                                        interface. If one is not provided then
                                        Amazon will automatically auto-assign
                                        an available IP. EC2 allows assignment
                                        of multiple IPs, but this will be
                                        the primary.
        :type       private_ip_address: ``str``

        :return:    EC2NetworkInterface instance
        :rtype:     :class `EC2NetworkInterface`
        """
		try:
			if not private_ip_address:
				private_ip_address = None
			if not description:
				description = None
			if not name:
				name = None
			if subnet:
				subnet = subnet.obj
			return EC2NetworkInterfaceImpl(self.conn.ex_create_network_interface(subnet ,name ,description ,private_ip_address))
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeleteNetworkInterface(self, network_interface):
		"""
        Deletes a network interface.

        :param      network_interface: EC2NetworkInterface instance
        :type       network_interface: :class:`EC2NetworkInterface`

        :rtype:     ``bool``
        """
		try:
			if network_interface:
				network_interface = network_interface.obj
			return self.conn.ex_delete_network_interface(network_interface)
		except Exception, ex:
			raise wrap_exception(ex)

	def exAttachNetworkInterfaceToNode(self, network_interface,
                                            node, device_index):
		"""
        Attatch a network interface to an instance.

        :param      network_interface: EC2NetworkInterface instance
        :type       network_interface: :class:`EC2NetworkInterface`

        :param      node: Node instance
        :type       node: :class:`Node`

        :param      device_index: The interface device index
        :type       device_index: ``int``

        :return:    String representation of the attachment id.
                    This is required to detach the interface.
        :rtype:     ``str``
        """
		try:
			if node:
				node = node.obj
			if network_interface:
				network_interface = network_interface.obj
			return self.conn.ex_attach_network_interface_to_node(network_interface ,node ,device_index)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDetachNetworkInterface(self, attachment_id, force=False):
		"""
        Detatch a network interface from an instance.

        :param      attachment_id: The attachment ID associated with the
                                   interface
        :type       attachment_id: ``str``

        :param      force: Forces the detachment.
        :type       force: ``bool``

        :return:    ``True`` on successful detachment, ``False`` otherwise.
        :rtype:     ``bool``
        """
		try:
			if not force:
				force = False
			return self.conn.ex_detach_network_interface(attachment_id ,force)
		except Exception, ex:
			raise wrap_exception(ex)

	def exModifyInstanceAttribute(self, node, attributes):
		"""
        Modify node attributes.
        A list of valid attributes can be found at http://goo.gl/gxcj8

        :param      node: Node instance
        :type       node: :class:`Node`

        :param      attributes: Dictionary with node attributes
        :type       attributes: ``dict``

        :return: True on success, False otherwise.
        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			attributes = jmap_to_pymap(attributes)
			return self.conn.ex_modify_instance_attribute(node ,attributes)
		except Exception, ex:
			raise wrap_exception(ex)

	def exModifyImageAttribute(self, image, attributes):
		"""
        Modify image attributes.

        :param      image: Node instance
        :type       image: :class:`NodeImage`

        :param      attributes: Dictionary with node attributes
        :type       attributes: ``dict``

        :return: True on success, False otherwise.
        :rtype: ``bool``
        """
		try:
			if image:
				image = image.obj
			attributes = jmap_to_pymap(attributes)
			return self.conn.ex_modify_image_attribute(image ,attributes)
		except Exception, ex:
			raise wrap_exception(ex)

	def exChangeNodeSize(self, node, new_size):
		"""
        Change the node size.
        Note: Node must be turned of before changing the size.

        :param      node: Node instance
        :type       node: :class:`Node`

        :param      new_size: NodeSize intance
        :type       new_size: :class:`NodeSize`

        :return: True on success, False otherwise.
        :rtype: ``bool``
        """
		try:
			if new_size:
				new_size = new_size.obj
			if node:
				node = node.obj
			return self.conn.ex_change_node_size(node ,new_size)
		except Exception, ex:
			raise wrap_exception(ex)

	def exStartNode(self, node):
		"""
        Start the node by passing in the node object, does not work with
        instance store backed instances

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

	def exStopNode(self, node):
		"""
        Stop the node by passing in the node object, does not work with
        instance store backed instances

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

	def exGetConsoleOutput(self, node):
		"""
        Get console output for the node.

        :param      node: Node which should be used
        :type       node: :class:`Node`

        :return:    Dictionary with the following keys:
                    - instance_id (``str``)
                    - timestamp (``datetime.datetime``) - ts of the last output
                    - output (``str``) - console output
        :rtype:     ``dict``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_get_console_output(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListReservedNodes(self):
		"""
        List all reserved instances/nodes which can be purchased from Amazon
        for one or three year terms. Reservations are made at a region level
        and reduce the hourly charge for instances.

        More information can be found at http://goo.gl/ulXCC7.

        :rtype: ``list`` of :class:`.EC2ReservedNode`
        """
		try:
			return wrap_listing(self.conn.ex_list_reserved_nodes(), EC2ReservedNodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetLimits(self):
		"""
        Retrieve account resource limits.

        :rtype: ``dict``
        """
		try:
			return self.conn.ex_get_limits()
		except Exception, ex:
			raise wrap_exception(ex)

	def exListKeypairs(self):
		"""
        Lists all the keypair names and fingerprints.

        :rtype: ``list`` of ``dict``
        """
		try:
			return self.conn.ex_list_keypairs()
		except Exception, ex:
			raise wrap_exception(ex)

	def exDescribeAllKeypairs(self):
		"""
        Return names for all the available key pairs.

        @note: This is a non-standard extension API, and only works for EC2.

        :rtype: ``list`` of ``str``
        """
		try:
			return self.conn.ex_describe_all_keypairs()
		except Exception, ex:
			raise wrap_exception(ex)

	def exDescribeKeypairs(self, name):
		"""
        Here for backward compatibility.
        """
		try:
			return self.conn.ex_describe_keypairs(name)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDescribeKeypair(self, name):
		"""
        Describes a keypair by name.

        @note: This is a non-standard extension API, and only works for EC2.

        :param      name: The name of the keypair to describe.
        :type       name: ``str``

        :rtype: ``dict``
        """
		try:
			return self.conn.ex_describe_keypair(name)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateKeypair(self, name):
		"""
        Creates a new keypair

        @note: This is a non-standard extension API, and only works for EC2.

        :param      name: The name of the keypair to Create. This must be
            unique, otherwise an InvalidKeyPair.Duplicate exception is raised.
        :type       name: ``str``

        :rtype: ``dict``
        """
		try:
			return self.conn.ex_create_keypair(name)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeleteKeypair(self, keypair):
		"""
        Delete a key pair by name.

        @note: This is a non-standard extension API, and only works with EC2.

        :param      keypair: The name of the keypair to delete.
        :type       keypair: ``str``

        :rtype: ``bool``
        """
		try:
			return self.conn.ex_delete_keypair(keypair)
		except Exception, ex:
			raise wrap_exception(ex)

	def exImportKeypairFromString(self, name, key_material):
		"""
        imports a new public key where the public key is passed in as a string

        @note: This is a non-standard extension API, and only works for EC2.

        :param      name: The name of the public key to import. This must be
         unique, otherwise an InvalidKeyPair.Duplicate exception is raised.
        :type       name: ``str``

        :param     key_material: The contents of a public key file.
        :type      key_material: ``str``

        :rtype: ``dict``
        """
		try:
			return self.conn.ex_import_keypair_from_string(name ,key_material)
		except Exception, ex:
			raise wrap_exception(ex)

	def exImportKeypair(self, name, keyfile):
		"""
        imports a new public key where the public key is passed via a filename

        @note: This is a non-standard extension API, and only works for EC2.

        :param      name: The name of the public key to import. This must be
         unique, otherwise an InvalidKeyPair.Duplicate exception is raised.
        :type       name: ``str``

        :param     keyfile: The filename with path of the public key to import.
        :type      keyfile: ``str``

        :rtype: ``dict``
        """
		try:
			return self.conn.ex_import_keypair(name ,keyfile)
		except Exception, ex:
			raise wrap_exception(ex)

	def exFindOrImportKeypairByKeyMaterial(self, pubkey):
		"""
        Given a public key, look it up in the EC2 KeyPair database. If it
        exists, return any information we have about it. Otherwise, create it.

        Keys that are created are named based on their comment and fingerprint.

        :rtype: ``dict``
        """
		try:
			return self.conn.ex_find_or_import_keypair_by_key_material(pubkey)
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.ec2 import EC2NodeLocation

class EC2NodeLocationImpl(NodeLocationImpl, EC2NodeLocation):

	def __init__(self, obj):
		NodeLocationImpl.__init__(self, obj)
		if hasattr(obj, 'availability_zone'):
			self.availability_zonep = ExEC2AvailabilityZoneImpl(obj.availability_zone)
		else:
			self.availability_zonep = ExEC2AvailabilityZoneImpl(None)
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getAvailabilityZone(self):
		return self.availability_zonep

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.ec2 import ExEC2AvailabilityZone

class ExEC2AvailabilityZoneImpl(ExEC2AvailabilityZone):
	'''
    Extension class which stores information about an EC2 availability zone.

    Note: This class is EC2 specific.
	'''

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
		if hasattr(obj, 'zone_state'):
			self.zone_statep = none_check(obj.zone_state, ' ')
		else:
			self.zone_statep = ' '
		if hasattr(obj, 'region_name'):
			self.region_namep = none_check(obj.region_name, ' ')
		else:
			self.region_namep = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getName(self):
		return self.namep

	def getZoneState(self):
		return self.zone_statep

	def getRegionName(self):
		return self.region_namep

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.ec2 import EC2ReservedNode

class EC2ReservedNodeImpl(NodeImpl, EC2ReservedNode):
	'''
    Class which stores information about EC2 reserved instances/nodes
    Inherits from Node and passes in None for name and private/public IPs

    Note: This class is EC2 specific.
	'''

	def __init__(self, obj):
		NodeImpl.__init__(self, obj)
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.ec2 import EC2Network

class EC2NetworkImpl(EC2Network):
	'''
    Represents information about a VPC (Virtual Private Cloud) network

    Note: This class is EC2 specific.
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
		if hasattr(obj, 'cidr_block'):
			self.cidr_blockp = none_check(obj.cidr_block, ' ')
		else:
			self.cidr_blockp = ' '
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

	def getCidrBlock(self):
		return self.cidr_blockp

	def getExtra(self):
		return self.extrap

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.ec2 import EC2NetworkSubnet

class EC2NetworkSubnetImpl(EC2NetworkSubnet):
	'''
    Represents information about a VPC (Virtual Private Cloud) subnet

    Note: This class is EC2 specific.
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
		if hasattr(obj, 'state'):
			self.statep = none_check(obj.state, ' ')
		else:
			self.statep = ' '
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

	def getState(self):
		return self.statep

	def getExtra(self):
		return self.extrap

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.ec2 import EC2NetworkInterface

class EC2NetworkInterfaceImpl(EC2NetworkInterface):
	'''
    Represents information about a VPC network interface

    Note: This class is EC2 specific. The state parameter denotes the current
    status of the interface. Valid values for state are attaching, attached,
    detaching and detached.
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
		if hasattr(obj, 'state'):
			self.statep = none_check(obj.state, ' ')
		else:
			self.statep = ' '
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

	def getState(self):
		return self.statep

	def getExtra(self):
		return self.extrap

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.ec2 import ElasticIP

class ElasticIPImpl(ElasticIP):
	'''
    Represents information about an elastic IP adddress

    :param      ip: The elastic IP address
    :type       ip: ``str``

    :param      domain: The domain that the IP resides in (EC2-Classic/VPC).
                        EC2 classic is represented with standard and VPC
                        is represented with vpc.
    :type       domain: ``str``

    :param      instance_id: The identifier of the instance which currently
                             has the IP associated.
    :type       instance_id: ``str``

    Note: This class is used to support both EC2 and VPC IPs.
          For VPC specific attributes are stored in the extra
          dict to make promotion to the base API easier.
	'''

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'ip'):
			self.ipp = none_check(obj.ip, ' ')
		else:
			self.ipp = ' '
		if hasattr(obj, 'domain'):
			self.domainp = none_check(obj.domain, ' ')
		else:
			self.domainp = ' '
		if hasattr(obj, 'instance_id'):
			self.instance_idp = none_check(obj.instance_id, ' ')
		else:
			self.instance_idp = ' '
		if hasattr(obj, 'extra'):
			self.extrap = obj.extra, {}
		else:
			self.extrap = {}
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getIp(self):
		return self.ipp

	def getDomain(self):
		return self.domainp

	def getInstanceId(self):
		return self.instance_idp

	def getExtra(self):
		return self.extrap

	def toString(self):
		return self.reprp

