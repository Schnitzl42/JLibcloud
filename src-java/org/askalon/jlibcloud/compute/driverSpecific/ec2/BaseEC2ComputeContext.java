/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/ec2.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/ec2.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/ec2.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.ec2;

import java.util.List;
import java.util.Map;

import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;


public interface BaseEC2ComputeContext extends ComputeContext {

	/**
	 * Copy an Amazon Machine Image from the specified source region
	 * to the current region.
	 * @param source_region The region where the image resides
	 * 			(:type source_region: ``str``)
	 * @param image Instance of class NodeImage
	 * 			(:type image: :class:`NodeImage`)
	 * @param name The name of the new image
	 * 			(:type name: ``str``)
	 * @param description The description of the new image
	 * 			(:type description: ``str``)
	 * @return Instance of class ``NodeImage``
	 * 			(:rtype: :class:`NodeImage`)
	**/
	public NodeImage exCopyImage(String source_region, NodeImage image, String name, String description);

	/**
	 * Copy an Amazon Machine Image from the specified source region
	 * to the current region.
	 * @param source_region The region where the image resides
	 * 			(:type source_region: ``str``)
	 * @param image Instance of class NodeImage
	 * 			(:type image: :class:`NodeImage`)
	 * @return Instance of class ``NodeImage``
	 * 			(:rtype: :class:`NodeImage`)
	**/
	public NodeImage exCopyImage(String source_region, NodeImage image);

	/**
	 * Create an Amazon Machine Image based off of an EBS-backed instance.
	 * An example block device mapping dictionary is included:
	 * mapping = [{'VirtualName': None,
	 * 'Ebs': {'VolumeSize': 10,
	 * 'VolumeType': 'standard',
	 * 'DeleteOnTermination': 'true'},
	 * 'DeviceName': '/dev/sda1'}]
	 * @param node Instance of ``Node``
	 * 			(:type node: :class: `Node`)
	 * @param name The name for the new image
	 * 			(:type name: ``str``)
	 * @param block_device_mapping A dictionary of the disk layout
	 * 			An example of this dict is included
	 * 			below.
	 * 			(:type block_device_mapping: ``list`` of ``dict``)
	 * @param reboot Whether or not to shutdown the instance before
	 * 			creation. By default Amazon sets this to false
	 * 			to ensure a clean image.
	 * 			(:type reboot: ``bool``)
	 * @param description An optional description for the new image
	 * 			(:type description: ``str``)
	 * @return Instance of class ``NodeImage``
	 * 			(:rtype: :class:`NodeImage`)
	**/
	public NodeImage exCreateImageFromNode(Node node, String name, List<Map<String,Arg>> block_device_mapping, boolean reboot, String description);

	/**
	 * Create an Amazon Machine Image based off of an EBS-backed instance.
	 * An example block device mapping dictionary is included:
	 * mapping = [{'VirtualName': None,
	 * 'Ebs': {'VolumeSize': 10,
	 * 'VolumeType': 'standard',
	 * 'DeleteOnTermination': 'true'},
	 * 'DeviceName': '/dev/sda1'}]
	 * @param node Instance of ``Node``
	 * 			(:type node: :class: `Node`)
	 * @param name The name for the new image
	 * 			(:type name: ``str``)
	 * @param block_device_mapping A dictionary of the disk layout
	 * 			An example of this dict is included
	 * 			below.
	 * 			(:type block_device_mapping: ``list`` of ``dict``)
	 * @return Instance of class ``NodeImage``
	 * 			(:rtype: :class:`NodeImage`)
	**/
	public NodeImage exCreateImageFromNode(Node node, String name, List<Map<String,Arg>> block_device_mapping);

	public void exDestroyImage(NodeImage image);


	/**
	 * Registers an Amazon Machine Image based off of an EBS-backed instance.
	 * Can also be used to create images from snapshots. More information
	 * can be found at http://goo.gl/hqZq0a.
	 * @param name The name for the AMI being registered
	 * 			(:type name: ``str``)
	 * @param description The description of the AMI (optional)
	 * 			(:type description: ``str``)
	 * @param architecture The architecture of the AMI (i386/x86_64)
	 * 			(optional)
	 * 			(:type architecture: ``str``)
	 * @param image_location The location of the AMI within Amazon S3
	 * 			Required if registering an instance
	 * 			store-backed AMI
	 * 			(:type image_location: ``str``)
	 * @param root_device_name The device name for the root device
	 * 			Required if registering a EBS-backed AMI
	 * 			(:type root_device_name: ``str``)
	 * @param block_device_mapping A dictionary of the disk layout
	 * 			(optional)
	 * 			(:type block_device_mapping: ``dict``)
	 * @param kernel_id Kernel id for AMI (optional)
	 * 			(:type kernel_id: ``str``)
	 * @param ramdisk_id RAM disk for AMI (optional)
	 * 			(:type ramdisk_id: ``str``)
	 * @return `NodeImage`
	**/
	public NodeImage exRegisterImage(String name, String description, String architecture, String image_location, String root_device_name, 
			Map<String,Arg> block_device_mapping, String kernel_id, String ramdisk_id);

	/**
	 * Registers an Amazon Machine Image based off of an EBS-backed instance.
	 * Can also be used to create images from snapshots. More information
	 * can be found at http://goo.gl/hqZq0a.
	 * @param name The name for the AMI being registered
	 * 			(:type name: ``str``)
	 * @return `NodeImage`
	**/
	public NodeImage exRegisterImage(String name);

	/**
	 * Return a list of :class:`EC2Network` objects for the
	 * current region.
	 * @return `EC2Network`
	**/
	public List<EC2Network> exListNetworks();


	/**
	 * Create a network/VPC
	 * @param cidr_block The CIDR block assigned to the network
	 * 			(:type cidr_block: ``str``)
	 * @param name An optional name for the network
	 * 			(:type name: ``str``)
	 * @param instance_tenancy The allowed tenancy of instances launched
	 * 			into the VPC.
	 * 			Valid values: default/dedicated
	 * 			(:type instance_tenancy: ``str``)
	 * @return Dictionary of network properties
	 * 			(:rtype: ``dict``)
	**/
	public Map<String,String> exCreateNetwork(String cidr_block, String name, String instance_tenancy);

	/**
	 * Create a network/VPC
	 * @param cidr_block The CIDR block assigned to the network
	 * 			(:type cidr_block: ``str``)
	 * @return Dictionary of network properties
	 * 			(:rtype: ``dict``)
	**/
	public Map<String,String> exCreateNetwork(String cidr_block);

	/**
	 * Deletes a network/VPC.
	 * @param vpc VPC to delete.
	 * 			(:type vpc: :class:`.EC2Network`)
	 * @return ``bool``
	**/
	public boolean exDeleteNetwork(EC2Network vpc);


	/**
	 * Return a list of :class:`EC2NetworkSubnet` objects for the
	 * current region.
	 * @return `EC2NetworkSubnet`
	**/
	public List<EC2NetworkSubnet> exListSubnets();


	/**
	 * Create a network subnet within a VPC
	 * @param vpc_id The ID of the VPC that the subnet should be
	 * 			associated with
	 * 			(:type vpc_id: ``str``)
	 * @param cidr_block The CIDR block assigned to the subnet
	 * 			(:type cidr_block: ``str``)
	 * @param availability_zone The availability zone where the subnet
	 * 			should reside
	 * 			(:type availability_zone: ``str``)
	 * @param name An optional name for the network
	 * 			(:type name: ``str``)
	 * @return `EC2NetworkSubnet`
	**/
	public EC2NetworkSubnet exCreateSubnet(String vpc_id, String cidr_block, String availability_zone, String name);

	/**
	 * Create a network subnet within a VPC
	 * @param vpc_id The ID of the VPC that the subnet should be
	 * 			associated with
	 * 			(:type vpc_id: ``str``)
	 * @param cidr_block The CIDR block assigned to the subnet
	 * 			(:type cidr_block: ``str``)
	 * @param availability_zone The availability zone where the subnet
	 * 			should reside
	 * 			(:type availability_zone: ``str``)
	 * @return `EC2NetworkSubnet`
	**/
	public EC2NetworkSubnet exCreateSubnet(String vpc_id, String cidr_block, String availability_zone);

	/**
	 * Deletes a VPC subnet.
	 * @param subnet The subnet to delete
	 * 			(:type subnet: :class:`.EC2NetworkSubnet`)
	 * @return ``bool``
	**/
	public boolean exDeleteSubnet(EC2NetworkSubnet subnet);


	/**
	 * List existing Security Groups.
	 * note: This is a non-standard extension API, and only works for EC2.
	 * @return ``list`` of ``str``
	**/
	public List<String> exListSecurityGroups();


	/**
	 * Creates a new Security Group in EC2-Classic or a targetted VPC.
	 * @param name The name of the security group to Create.
	 * 			This must be unique.
	 * 			(:type name: ``str``)
	 * @param description Optional identifier for VPC networks
	 * 			(:type description: ``str``)
	 * @return ``dict``
	**/
	public Map<String,String> exCreateSecurityGroup(String name, String description, String vpc_id);

	/**
	 * Creates a new Security Group in EC2-Classic or a targetted VPC.
	 * @param name The name of the security group to Create.
	 * 			This must be unique.
	 * 			(:type name: ``str``)
	 * @param description Optional identifier for VPC networks
	 * 			(:type description: ``str``)
	 * @return ``dict``
	**/
	public Map<String,String> exCreateSecurityGroup(String name, String description);

	/**
	 * Deletes a new Security Group using the group id.
	 * @param group_id The ID of the security group
	 * 			(:type group_id: ``str``)
	 * @return ``bool``
	**/
	public boolean exDeleteSecurityGroupById(String group_id);


	/**
	 * Deletes a new Security Group using the group name.
	 * @param group_name The name of the security group
	 * 			(:type group_name: ``str``)
	 * @return ``bool``
	**/
	public boolean exDeleteSecurityGroupByName(String group_name);


	/**
	 * Wrapper method which calls ex_delete_security_group_by_name.
	 * @param name The name of the security group
	 * 			(:type name: ``str``)
	 * @return ``bool``
	**/
	public boolean exDeleteSecurityGroup(String name);


	/**
	 * Edit a Security Group to allow specific traffic.
	 * note: This is a non-standard extension API, and only works for EC2.
	 * @param name The name of the security group to edit
	 * 			(:type name: ``str``)
	 * @param from_port The beginning of the port range to open
	 * 			(:type from_port: ``str``)
	 * @param to_port The end of the port range to open
	 * 			(:type to_port: ``str``)
	 * @param cidr_ip The ip to allow traffic for.
	 * 			(:type cidr_ip: ``str``)
	 * @param protocol tcp/udp/icmp
	 * 			(:type protocol: ``str``)
	 * @return ``bool``
	**/
	public boolean exAuthorizeSecurityGroup(String name, String from_port, String to_port, String cidr_ip, String protocol);

	/**
	 * Edit a Security Group to allow specific traffic.
	 * note: This is a non-standard extension API, and only works for EC2.
	 * @param name The name of the security group to edit
	 * 			(:type name: ``str``)
	 * @param from_port The beginning of the port range to open
	 * 			(:type from_port: ``str``)
	 * @param to_port The end of the port range to open
	 * 			(:type to_port: ``str``)
	 * @param cidr_ip The ip to allow traffic for.
	 * 			(:type cidr_ip: ``str``)
	 * @return ``bool``
	**/
	public boolean exAuthorizeSecurityGroup(String name, String from_port, String to_port, String cidr_ip);

	/**
	 * Edit a Security Group to allow specific ingress traffic using
	 * CIDR blocks or either a group ID, group name or user ID (account).
	 * @param id The id of the security group to edit
	 * 			(:type id: ``str``)
	 * @param from_port The beginning of the port range to open
	 * 			(:type from_port: ``int``)
	 * @param to_port The end of the port range to open
	 * 			(:type to_port: ``int``)
	 * @param cidr_ips The list of ip ranges to allow traffic for.
	 * 			(:type cidr_ips: ``list``)
	 * @param group_pairs Source user/group pairs to allow traffic for.
	 * 			More info can be found at http://goo.gl/stBHJF
	 * 			
	 * 			EC2 Classic Example: To allow access from any system
	 * 			associated with the default group on account 1234567890
	 * 			
	 * 			[{'group_name': 'default', 'user_id': '1234567890'}]
	 * 			
	 * 			VPC Example: Allow access from any system associated with
	 * 			security group sg-47ad482e on your own account
	 * 			
	 * 			[{'group_id': ' sg-47ad482e'}]
	 * 			(:type group_pairs: ``list`` of ``dict``)
	 * @param protocol tcp/udp/icmp
	 * 			(:type protocol: ``str``)
	 * @return ``bool``
	**/
	public boolean exAuthorizeSecurityGroupIngress(String id, int from_port, int to_port, List<String> cidr_ips, List<Map<String,Arg>> group_pairs, 
			String protocol);

	/**
	 * Edit a Security Group to allow specific ingress traffic using
	 * CIDR blocks or either a group ID, group name or user ID (account).
	 * @param id The id of the security group to edit
	 * 			(:type id: ``str``)
	 * @param from_port The beginning of the port range to open
	 * 			(:type from_port: ``int``)
	 * @param to_port The end of the port range to open
	 * 			(:type to_port: ``int``)
	 * @return ``bool``
	**/
	public boolean exAuthorizeSecurityGroupIngress(String id, int from_port, int to_port);

	/**
	 * Edit a Security Group to allow specific egress traffic using
	 * CIDR blocks or either a group ID, group name or user ID (account).
	 * This call is not supported for EC2 classic and only works for VPC
	 * groups.
	 * @param id The id of the security group to edit
	 * 			(:type id: ``str``)
	 * @param from_port The beginning of the port range to open
	 * 			(:type from_port: ``int``)
	 * @param to_port The end of the port range to open
	 * 			(:type to_port: ``int``)
	 * @param cidr_ips The list of ip ranges to allow traffic for.
	 * 			(:type cidr_ips: ``list``)
	 * @param group_pairs Source user/group pairs to allow traffic for.
	 * 			More info can be found at http://goo.gl/stBHJF
	 * 			
	 * 			EC2 Classic Example: To allow access from any system
	 * 			associated with the default group on account 1234567890
	 * 			
	 * 			[{'group_name': 'default', 'user_id': '1234567890'}]
	 * 			
	 * 			VPC Example: Allow access from any system associated with
	 * 			security group sg-47ad482e on your own account
	 * 			
	 * 			[{'group_id': ' sg-47ad482e'}]
	 * 			(:type group_pairs: ``list`` of ``dict``)
	 * @param protocol tcp/udp/icmp
	 * 			(:type protocol: ``str``)
	 * @return ``bool``
	**/
	public boolean exAuthorizeSecurityGroupEgress(String id, int from_port, int to_port, List<String> cidr_ips, List<Map<String,Arg>> group_pairs, 
			String protocol);

	/**
	 * Edit a Security Group to allow specific egress traffic using
	 * CIDR blocks or either a group ID, group name or user ID (account).
	 * This call is not supported for EC2 classic and only works for VPC
	 * groups.
	 * @param id The id of the security group to edit
	 * 			(:type id: ``str``)
	 * @param from_port The beginning of the port range to open
	 * 			(:type from_port: ``int``)
	 * @param to_port The end of the port range to open
	 * 			(:type to_port: ``int``)
	 * @return ``bool``
	**/
	public boolean exAuthorizeSecurityGroupEgress(String id, int from_port, int to_port);

	/**
	 * Edit a Security Group to revoke specific ingress traffic using
	 * CIDR blocks or either a group ID, group name or user ID (account).
	 * @param id The id of the security group to edit
	 * 			(:type id: ``str``)
	 * @param from_port The beginning of the port range to open
	 * 			(:type from_port: ``int``)
	 * @param to_port The end of the port range to open
	 * 			(:type to_port: ``int``)
	 * @param cidr_ips The list of ip ranges to allow traffic for.
	 * 			(:type cidr_ips: ``list``)
	 * @param group_pairs Source user/group pairs to allow traffic for.
	 * 			More info can be found at http://goo.gl/stBHJF
	 * 			
	 * 			EC2 Classic Example: To allow access from any system
	 * 			associated with the default group on account 1234567890
	 * 			
	 * 			[{'group_name': 'default', 'user_id': '1234567890'}]
	 * 			
	 * 			VPC Example: Allow access from any system associated with
	 * 			security group sg-47ad482e on your own account
	 * 			
	 * 			[{'group_id': ' sg-47ad482e'}]
	 * 			(:type group_pairs: ``list`` of ``dict``)
	 * @param protocol tcp/udp/icmp
	 * 			(:type protocol: ``str``)
	 * @return ``bool``
	**/
	public boolean exRevokeSecurityGroupIngress(String id, int from_port, int to_port, List<String> cidr_ips, List<Map<String,Arg>> group_pairs, 
			String protocol);

	/**
	 * Edit a Security Group to revoke specific ingress traffic using
	 * CIDR blocks or either a group ID, group name or user ID (account).
	 * @param id The id of the security group to edit
	 * 			(:type id: ``str``)
	 * @param from_port The beginning of the port range to open
	 * 			(:type from_port: ``int``)
	 * @param to_port The end of the port range to open
	 * 			(:type to_port: ``int``)
	 * @return ``bool``
	**/
	public boolean exRevokeSecurityGroupIngress(String id, int from_port, int to_port);

	/**
	 * Edit a Security Group to revoke specific egress traffic using
	 * CIDR blocks or either a group ID, group name or user ID (account).
	 * This call is not supported for EC2 classic and only works for
	 * VPC groups.
	 * @param id The id of the security group to edit
	 * 			(:type id: ``str``)
	 * @param from_port The beginning of the port range to open
	 * 			(:type from_port: ``int``)
	 * @param to_port The end of the port range to open
	 * 			(:type to_port: ``int``)
	 * @param cidr_ips The list of ip ranges to allow traffic for.
	 * 			(:type cidr_ips: ``list``)
	 * @param group_pairs Source user/group pairs to allow traffic for.
	 * 			More info can be found at http://goo.gl/stBHJF
	 * 			
	 * 			EC2 Classic Example: To allow access from any system
	 * 			associated with the default group on account 1234567890
	 * 			
	 * 			[{'group_name': 'default', 'user_id': '1234567890'}]
	 * 			
	 * 			VPC Example: Allow access from any system associated with
	 * 			security group sg-47ad482e on your own account
	 * 			
	 * 			[{'group_id': ' sg-47ad482e'}]
	 * 			(:type group_pairs: ``list`` of ``dict``)
	 * @param protocol tcp/udp/icmp
	 * 			(:type protocol: ``str``)
	 * @return ``bool``
	**/
	public boolean exRevokeSecurityGroupEgress(String id, int from_port, int to_port, List<String> cidr_ips, List<Map<String,Arg>> group_pairs, 
			String protocol);

	/**
	 * Edit a Security Group to revoke specific egress traffic using
	 * CIDR blocks or either a group ID, group name or user ID (account).
	 * This call is not supported for EC2 classic and only works for
	 * VPC groups.
	 * @param id The id of the security group to edit
	 * 			(:type id: ``str``)
	 * @param from_port The beginning of the port range to open
	 * 			(:type from_port: ``int``)
	 * @param to_port The end of the port range to open
	 * 			(:type to_port: ``int``)
	 * @return ``bool``
	**/
	public boolean exRevokeSecurityGroupEgress(String id, int from_port, int to_port);

	/**
	 * Edit a Security Group to allow all traffic.
	 * note: This is a non-standard extension API, and only works for EC2.
	 * @param name The name of the security group to edit
	 * 			(:type name: ``str``)
	 * @return ``list`` of ``str``
	**/
	public List<String> exAuthorizeSecurityGroupPermissive(String name);


	/**
	 * Return a list of :class:`ExEC2AvailabilityZone` objects for the
	 * current region.
	 * Note: This is an extension method and is only available for EC2
	 * driver.
	 * @param only_available If true, return only availability zones
	 * 			with state 'available'
	 * 			(:type only_available: ``str``)
	 * @return `ExEC2AvailabilityZone`
	**/
	public List<ExEC2AvailabilityZone> exListAvailabilityZones(String only_available);

	/**
	 * Return a list of :class:`ExEC2AvailabilityZone` objects for the
	 * current region.
	 * Note: This is an extension method and is only available for EC2
	 * driver.
	 * @return `ExEC2AvailabilityZone`
	**/
	public List<ExEC2AvailabilityZone> exListAvailabilityZones();

	/**
	 * Return a dictionary of tags for a resource (Node or StorageVolume).
	 * @param resource resource which should be used
	 * 			(:type resource: :class:`Node` or :class:`StorageVolume`)
	 * @return dict Node tags
	 * 			(:rtype: ``dict``)
	**/
	public Map<String,String> exDescribeTags(Node resource);


	/**
	 * Create tags for a resource (Node or StorageVolume).
	 * @param resource Resource to be tagged
	 * 			(:type resource: :class:`Node` or :class:`StorageVolume`)
	 * @param tags A dictionary or other mapping of strings to strings,
	 * 			associating tag names with tag values.
	 * 			(:type tags: ``dict``)
	 * @return ``bool``
	**/
	public boolean exCreateTags(Node resource, Map<String,Arg> tags);


	/**
	 * Delete tags from a resource.
	 * @param resource Resource to be tagged
	 * 			(:type resource: :class:`Node` or :class:`StorageVolume`)
	 * @param tags A dictionary or other mapping of strings to strings,
	 * 			specifying the tag names and tag values to be deleted.
	 * 			(:type tags: ``dict``)
	 * @return ``bool``
	**/
	public boolean exDeleteTags(Node resource, Map<String,Arg> tags);


	/**
	 * Return the metadata associated with the node.
	 * associating tag names with tag values.
	 * @param node Node instance
	 * 			(:type node: :class:`Node`)
	 * @return A dictionary or other mapping of strings to strings,
	 * 			(:rtype tags: ``dict``)
	**/
	public Map<String,String> exGetMetadataForNode(Node node);


	/**
	 * Allocate a new Elastic IP address for EC2 classic or VPC
	 * @param domain The domain to allocate the new address in
	 * 			(standard/vpc)
	 * 			(:type domain: ``str``)
	 * @return Instance of ElasticIP
	 * 			(:rtype: :class:`ElasticIP`)
	**/
	public ElasticIP exAllocateAddress(String domain);

	/**
	 * Allocate a new Elastic IP address for EC2 classic or VPC
	 * @return Instance of ElasticIP
	 * 			(:rtype: :class:`ElasticIP`)
	**/
	public ElasticIP exAllocateAddress();

	/**
	 * Release an Elastic IP address using the IP (EC2-Classic) or
	 * using the allocation ID (VPC)
	 * @param elastic_ip Elastic IP instance
	 * 			(:type elastic_ip: :class:`ElasticIP`)
	 * @param domain The domain where the IP resides (vpc only)
	 * 			(:type domain: ``str``)
	 * @return True on success, False otherwise.
	 * 			(:rtype: ``bool``)
	**/
	public boolean exReleaseAddress(ElasticIP elastic_ip, String domain);

	/**
	 * Release an Elastic IP address using the IP (EC2-Classic) or
	 * using the allocation ID (VPC)
	 * @param elastic_ip Elastic IP instance
	 * 			(:type elastic_ip: :class:`ElasticIP`)
	 * @return True on success, False otherwise.
	 * 			(:rtype: ``bool``)
	**/
	public boolean exReleaseAddress(ElasticIP elastic_ip);

	/**
	 * Return all the Elastic IP addresses for this account
	 * optionally, return only addresses associated with nodes
	 * @param only_associated If true, return only those addresses
	 * 			that are associated with an instance.
	 * 			(:type only_associated: ``bool``)
	 * @return List of ElasticIP instances.
	 * 			(:rtype: ``list`` of :class:`ElasticIP`)
	**/
	public List<ElasticIP> exDescribeAllAddresses(boolean only_associated);

	/**
	 * Return all the Elastic IP addresses for this account
	 * optionally, return only addresses associated with nodes
	 * @return List of ElasticIP instances.
	 * 			(:rtype: ``list`` of :class:`ElasticIP`)
	**/
	public List<ElasticIP> exDescribeAllAddresses();

	/**
	 * Associate an Elastic IP address with a particular node.
	 * @param node Node instance
	 * 			(:type node: :class:`Node`)
	 * @param elastic_ip Elastic IP instance
	 * 			(:type elastic_ip: :class:`ElasticIP`)
	 * @param domain The domain where the IP resides (vpc only)
	 * 			(:type domain: ``str``)
	 * 			required for VPC disassociation. EC2/standard
	 * 			addresses return None
	 * @return A string representation of the association ID which is
	 * 			(:rtype: ``None`` or ``str``)
	**/
	public String exAssociateAddressWithNode(Node node, ElasticIP elastic_ip, String domain);

	/**
	 * Associate an Elastic IP address with a particular node.
	 * @param node Node instance
	 * 			(:type node: :class:`Node`)
	 * @param elastic_ip Elastic IP instance
	 * 			(:type elastic_ip: :class:`ElasticIP`)
	 * @return A string representation of the association ID which is
	 * 			(:rtype: ``None`` or ``str``)
	**/
	public String exAssociateAddressWithNode(Node node, ElasticIP elastic_ip);

	/**
	 * Note: This method has been deprecated in favor of
	 * the ex_associate_address_with_node method.
	**/
	public String exAssociateAddresses(Node node, ElasticIP elastic_ip, String domain);

	/**
	 * Note: This method has been deprecated in favor of
	 * the ex_associate_address_with_node method.
	**/
	public String exAssociateAddresses(Node node, ElasticIP elastic_ip);

	/**
	 * Disassociate an Elastic IP address using the IP (EC2-Classic)
	 * or the association ID (VPC)
	 * @param elastic_ip ElasticIP instance
	 * 			(:type elastic_ip: :class:`ElasticIP`)
	 * @param domain The domain where the IP resides (vpc only)
	 * 			(:type domain: ``str``)
	 * @return True on success, False otherwise.
	 * 			(:rtype: ``bool``)
	**/
	public boolean exDisassociateAddress(ElasticIP elastic_ip, String domain);

	/**
	 * Disassociate an Elastic IP address using the IP (EC2-Classic)
	 * or the association ID (VPC)
	 * @param elastic_ip ElasticIP instance
	 * 			(:type elastic_ip: :class:`ElasticIP`)
	 * @return True on success, False otherwise.
	 * 			(:rtype: ``bool``)
	**/
	public boolean exDisassociateAddress(ElasticIP elastic_ip);

	/**
	 * Return Elastic IP addresses for all the nodes in the provided list.
	 * @param nodes `Node` instances
	 * 			(:type nodes: ``list`` of :class:`Node`)
	 * 			list with the Elastic IP addresses associated with
	 * 			this node.
	 * @return Dictionary where a key is a node ID and the value is a
	 * 			(:rtype: ``dict``)
	**/
	public Map<String,String> exDescribeAddresses(List<Node> nodes);


	/**
	 * Return a list of Elastic IP addresses associated with this node.
	 * @param node Node instance
	 * 			(:type node: :class:`Node`)
	 * @return list Elastic IP addresses attached to this node.
	 * 			(:rtype: ``list`` of ``str``)
	**/
	public List<String> exDescribeAddressesForNode(Node node);


	/**
	 * Return all network interfaces
	 * @return List of EC2NetworkInterface instances
	 * 			(:rtype: ``list`` of :class `EC2NetworkInterface`)
	**/
	public List<EC2NetworkInterface> exListNetworkInterfaces();


	/**
	 * Create a network interface within a VPC subnet.
	 * @param subnet EC2NetworkSubnet instance
	 * 			(:type subnet: :class:`EC2NetworkSubnet`)
	 * @param name Optional name of the interface
	 * 			(:type name: ``str``)
	 * @param description Optional description of the network interface
	 * 			(:type description: ``str``)
	 * @param private_ip_address Optional address to assign as the
	 * 			primary private IP address of the
	 * 			interface. If one is not provided then
	 * 			Amazon will automatically auto-assign
	 * 			an available IP. EC2 allows assignment
	 * 			of multiple IPs, but this will be
	 * 			the primary.
	 * 			(:type private_ip_address: ``str``)
	 * @return EC2NetworkInterface instance
	 * 			(:rtype: :class `EC2NetworkInterface`)
	**/
	public EC2NetworkInterface exCreateNetworkInterface(EC2NetworkSubnet subnet, String name, String description, String private_ip_address);

	/**
	 * Create a network interface within a VPC subnet.
	 * @param subnet EC2NetworkSubnet instance
	 * 			(:type subnet: :class:`EC2NetworkSubnet`)
	 * @return EC2NetworkInterface instance
	 * 			(:rtype: :class `EC2NetworkInterface`)
	**/
	public EC2NetworkInterface exCreateNetworkInterface(EC2NetworkSubnet subnet);

	/**
	 * Deletes a network interface.
	 * @param network_interface EC2NetworkInterface instance
	 * 			(:type network_interface: :class:`EC2NetworkInterface`)
	 * @return ``bool``
	**/
	public boolean exDeleteNetworkInterface(EC2NetworkInterface network_interface);


	/**
	 * Attatch a network interface to an instance.
	 * @param network_interface EC2NetworkInterface instance
	 * 			(:type network_interface: :class:`EC2NetworkInterface`)
	 * @param node Node instance
	 * 			(:type node: :class:`Node`)
	 * @param device_index The interface device index
	 * 			(:type device_index: ``int``)
	 * 			This is required to detach the interface.
	 * @return String representation of the attachment id.
	 * 			(:rtype: ``str``)
	**/
	public String exAttachNetworkInterfaceToNode(EC2NetworkInterface network_interface, Node node, int device_index);


	/**
	 * Detatch a network interface from an instance.
	 * @param attachment_id The attachment ID associated with the
	 * 			interface
	 * 			(:type attachment_id: ``str``)
	 * @param force Forces the detachment.
	 * 			(:type force: ``bool``)
	 * @return ``True`` on successful detachment, ``False`` otherwise.
	 * 			(:rtype: ``bool``)
	**/
	public boolean exDetachNetworkInterface(String attachment_id, boolean force);

	/**
	 * Detatch a network interface from an instance.
	 * @param attachment_id The attachment ID associated with the
	 * 			interface
	 * 			(:type attachment_id: ``str``)
	 * @return ``True`` on successful detachment, ``False`` otherwise.
	 * 			(:rtype: ``bool``)
	**/
	public boolean exDetachNetworkInterface(String attachment_id);

	/**
	 * Modify node attributes.
	 * A list of valid attributes can be found at http://goo.gl/gxcj8
	 * @param node Node instance
	 * 			(:type node: :class:`Node`)
	 * @param attributes Dictionary with node attributes
	 * 			(:type attributes: ``dict``)
	 * @return True on success, False otherwise.
	 * 			(:rtype: ``bool``)
	**/
	public boolean exModifyInstanceAttribute(Node node, Map<String,Arg> attributes);


	/**
	 * Modify image attributes.
	 * @param image Node instance
	 * 			(:type image: :class:`NodeImage`)
	 * @param attributes Dictionary with node attributes
	 * 			(:type attributes: ``dict``)
	 * @return True on success, False otherwise.
	 * 			(:rtype: ``bool``)
	**/
	public boolean exModifyImageAttribute(NodeImage image, Map<String,Arg> attributes);


	/**
	 * Change the node size.
	 * Note: Node must be turned of before changing the size.
	 * @param node Node instance
	 * 			(:type node: :class:`Node`)
	 * @param new_size NodeSize intance
	 * 			(:type new_size: :class:`NodeSize`)
	 * @return True on success, False otherwise.
	 * 			(:rtype: ``bool``)
	**/
	public boolean exChangeNodeSize(Node node, NodeSize new_size);


	/**
	 * Start the node by passing in the node object, does not work with
	 * instance store backed instances
	 * @param node Node which should be used
	 * 			(:type node: :class:`Node`)
	 * @return ``bool``
	**/
	public boolean exStartNode(Node node);


	/**
	 * Stop the node by passing in the node object, does not work with
	 * instance store backed instances
	 * @param node Node which should be used
	 * 			(:type node: :class:`Node`)
	 * @return ``bool``
	**/
	public boolean exStopNode(Node node);


	/**
	 * Get console output for the node.
	 * @param node Node which should be used
	 * 			(:type node: :class:`Node`)
	 * 			- instance_id (``str``)
	 * 			- timestamp (``datetime.datetime``) - ts of the last output
	 * 			- output (``str``) - console output
	 * @return 
	 * 			(:rtype: ``dict``)
	**/
	public Map<String,String> exGetConsoleOutput(Node node);


	/**
	 * List all reserved instances/nodes which can be purchased from Amazon
	 * for one or three year terms. Reservations are made at a region level
	 * and reduce the hourly charge for instances.
	 * More information can be found at http://goo.gl/ulXCC7.
	 * @return `.EC2ReservedNode`
	**/
	public List<EC2ReservedNode> exListReservedNodes();


	/**
	 * Retrieve account resource limits.
	 * @return ``dict``
	**/
	public Map<String,String> exGetLimits();


	/**
	 * Lists all the keypair names and fingerprints.
	 * @return ``list`` of ``dict``
	**/
	public List<Map<String,String>> exListKeypairs();


	/**
	 * Return names for all the available key pairs.
	 * note: This is a non-standard extension API, and only works for EC2.
	 * @return ``list`` of ``str``
	**/
	public List<String> exDescribeAllKeypairs();


	/**
	 * Here for backward compatibility.
	**/
	public String exDescribeKeypairs(String name);


	/**
	 * Describes a keypair by name.
	 * note: This is a non-standard extension API, and only works for EC2.
	 * @param name The name of the keypair to describe.
	 * 			(:type name: ``str``)
	 * @return ``dict``
	**/
	public Map<String,String> exDescribeKeypair(String name);


	/**
	 * Creates a new keypair
	 * note: This is a non-standard extension API, and only works for EC2.
	 * @param name The name of the keypair to Create. This must be
	 * 			unique, otherwise an InvalidKeyPair.Duplicate exception is raised.
	 * 			(:type name: ``str``)
	 * @return ``dict``
	**/
	public Map<String,String> exCreateKeypair(String name);


	/**
	 * Delete a key pair by name.
	 * note: This is a non-standard extension API, and only works with EC2.
	 * @param keypair The name of the keypair to delete.
	 * 			(:type keypair: ``str``)
	 * @return ``bool``
	**/
	public boolean exDeleteKeypair(String keypair);


	/**
	 * imports a new public key where the public key is passed in as a string
	 * note: This is a non-standard extension API, and only works for EC2.
	 * @param name The name of the public key to import. This must be
	 * 			unique, otherwise an InvalidKeyPair.Duplicate exception is raised.
	 * 			(:type name: ``str``)
	 * @param key_material The contents of a public key file.
	 * 			(:type key_material: ``str``)
	 * @return ``dict``
	**/
	public Map<String,String> exImportKeypairFromString(String name, String key_material);


	/**
	 * imports a new public key where the public key is passed via a filename
	 * note: This is a non-standard extension API, and only works for EC2.
	 * @param name The name of the public key to import. This must be
	 * 			unique, otherwise an InvalidKeyPair.Duplicate exception is raised.
	 * 			(:type name: ``str``)
	 * @param keyfile The filename with path of the public key to import.
	 * 			(:type keyfile: ``str``)
	 * @return ``dict``
	**/
	public Map<String,String> exImportKeypair(String name, String keyfile);


	/**
	 * Given a public key, look it up in the EC2 KeyPair database. If it
	 * exists, return any information we have about it. Otherwise, create it.
	 * Keys that are created are named based on their comment and fingerprint.
	 * @return ``dict``
	**/
	public Map<String,String> exFindOrImportKeypairByKeyMaterial(String pubkey);


}