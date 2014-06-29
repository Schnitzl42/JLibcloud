/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/openstack.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/openstack.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/openstack.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.openstack;

import java.util.List;
import java.util.Map;

import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.StorageVolume;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.VolumeSnapshot;


public interface OpenStack_1_1_ComputeContext extends OpenStackComputeContext {

	/**
	 * Changes the administrator password for a specified server.
	 * @param node Node to rebuild.
	 * 			(:type node: :class:`Node`)
	 * @param password The administrator password.
	 * 			(:type password: ``str``)
	 * @return ``bool``
	**/
	public boolean exSetPassword(Node node, String password);


	/**
	 * Rebuild a Node.
	 * :class:`OpenStackSecurityGroup`
	 * @param node Node to rebuild.
	 * 			(:type node: :class:`Node`)
	 * @param image New image to use.
	 * 			(:type image: :class:`NodeImage`)
	 * @param ex_metadata Key/Value metadata to associate with a node
	 * 			(:type ex_metadata: ``dict``)
	 * @param ex_files File Path => File contents to create on
	 * 			the no de
	 * 			(:type ex_files: ``dict``)
	 * @param ex_keyname Name of existing public key to inject into
	 * 			instance
	 * 			(:type ex_keyname: ``str``)
	 * @param ex_userdata String containing user data
	 * 			see
	 * 			https://help.ubuntu.com/community/CloudInit
	 * 			(:type ex_userdata: ``str``)
	 * @param ex_security_groups List of security groups to assign to
	 * 			the node
	 * 			(:type ex_security_groups: ``list`` of :class:`OpenStackSecurityGroup`)
	 * @param ex_disk_config Name of the disk configuration.
	 * 			Can be either ``AUTO`` or ``MANUAL``.
	 * 			(:type ex_disk_config: ``str``)
	 * @return ``bool``
	**/
	public boolean exRebuild(Node node, NodeImage image, Map<String,Arg> ex_metadata, Map<String,Arg> ex_files, String ex_keyname, 
			String ex_userdata, List<OpenStackSecurityGroup> ex_security_groups, String ex_disk_config);


	/**
	 * Change a node size.
	 * @param node Node to resize.
	 * @param size New size to use.
	 * 			
	 * @return ``bool``
	**/
	public boolean exResize(String node, String size);


	/**
	 * Confirms a pending resize action.
	 * @param node Node to resize.
	 * 			(:type node: :class:`Node`)
	 * @return ``bool``
	**/
	public boolean exConfirmResize(Node node);


	/**
	 * Cancels and reverts a pending resize action.
	 * @param node Node to resize.
	 * 			(:type node: :class:`Node`)
	 * @return ``bool``
	**/
	public boolean exRevertResize(Node node);


	/**
	 * Creates a new image.
	 * @param node Node
	 * 			(:type node: :class:`Node`)
	 * @param name The name for the new image.
	 * 			(:type name: ``str``)
	 * @param metadata Key and value pairs for metadata.
	 * 			(:type metadata: ``dict``)
	 * @return `NodeImage`
	**/
	public NodeImage exSaveImage(Node node, String name, Map<String,Arg> metadata);

	/**
	 * Creates a new image.
	 * @param node Node
	 * 			(:type node: :class:`Node`)
	 * @param name The name for the new image.
	 * 			(:type name: ``str``)
	 * @return `NodeImage`
	**/
	public NodeImage exSaveImage(Node node, String name);

	/**
	 * Sets the Node's name.
	 * @param node Node
	 * 			(:type node: :class:`Node`)
	 * @param name The name of the server.
	 * 			(:type name: ``str``)
	 * @return `Node`
	**/
	public Node exSetServerName(Node node, String name);


	/**
	 * Get a Node's metadata.
	 * @param node Node
	 * 			(:type node: :class:`Node`)
	 * @return Key/Value metadata associated with node.
	 * 			(:rtype: ``dict``)
	**/
	public Map<String,String> exGetMetadata(Node node);


	/**
	 * Sets the Node's metadata.
	 * @param node Node
	 * 			(:type node: :class:`Node`)
	 * @param metadata Key/Value metadata to associate with a node
	 * 			(:type metadata: ``dict``)
	 * @return ``dict``
	**/
	public Map<String,String> exSetMetadata(Node node, Map<String,Arg> metadata);


	/**
	 * Update the Node's editable attributes. The OpenStack API currently
	 * supports editing name and IPv4/IPv6 access addresses.
	 * The driver currently only supports updating the node name.
	 * @param node Node
	 * 			(:type node: :class:`Node`)
	 * @param name New name for the server
	 * 			(:type name: ``str``)
	 * @return `Node`
	**/
	public Node exUpdateNode(Node node, String name);


	/**
	 * Get a list of Networks that are available.
	 * @return `OpenStackNetwork`
	**/
	public List<OpenStackNetwork> exListNetworks();

	public List<OpenStackNetwork> exListNetworks(String tenant_id);
	
	/**
	 * Create a new Network
	 * @param name Name of network which should be used
	 * 			(:type name: ``str``)
	 * @param cidr cidr of network which should be used
	 * 			(:type cidr: ``str``)
	 * @return `OpenStackNetwork`
	**/
	public OpenStackNetwork exCreateNetwork(String name, String cidr);


	/**
	 * Get a list of NodeNetorks that are available.
	 * @param network Network which should be used
	 * 			(:type network: :class:`OpenStackNetwork`)
	 * @return ``bool``
	**/
	public boolean exDeleteNetwork(OpenStackNetwork network);


	/**
	 * Get console output
	 * @param node node
	 * 			(:type node: :class:`Node`)
	 * @param length Optional number of lines to fetch from the
	 * 			console log
	 * 			(:type length: ``int``)
	 * @return Dictionary with the output
	 * 			(:rtype: ``dict``)
	**/
	public Map<String,String> exGetConsoleOutput(Node node, int length);

	/**
	 * Get console output
	 * @param node node
	 * 			(:type node: :class:`Node`)
	 * @return Dictionary with the output
	 * 			(:rtype: ``dict``)
	**/
	public Map<String,String> exGetConsoleOutput(Node node);

	public String exListSnapshots();


	/**
	 * Create a snapshot based off of a volume.
	 * @param volume volume
	 * 			(:type volume: :class:`StorageVolume`)
	 * @param name New name for the volume snapshot
	 * 			(:type name: ``str``)
	 * @param description Description of the snapshot (optional)
	 * 			(:type description: ``str``)
	 * @param force Whether to force creation (optional)
	 * 			(:type force: ``bool``)
	 * @return `VolumeSnapshot`
	**/
	public VolumeSnapshot exCreateSnapshot(StorageVolume volume, String name, String description, boolean force);

	/**
	 * Create a snapshot based off of a volume.
	 * @param volume volume
	 * 			(:type volume: :class:`StorageVolume`)
	 * @param name New name for the volume snapshot
	 * 			(:type name: ``str``)
	 * @return `VolumeSnapshot`
	**/
	public VolumeSnapshot exCreateSnapshot(StorageVolume volume, String name);

	/**
	 * Delete a VolumeSnapshot
	 * @param snapshot snapshot
	 * 			(:type snapshot: :class:`VolumeSnapshot`)
	 * @return ``bool``
	**/
	public boolean exDeleteSnapshot(VolumeSnapshot snapshot);


	/**
	 * Get a list of Security Groups that are available.
	 * @return `OpenStackSecurityGroup`
	**/
	public List<OpenStackSecurityGroup> exListSecurityGroups();


	/**
	 * Get Security Groups of the specified server.
	 * @return `OpenStackSecurityGroup`
	**/
	public List<OpenStackSecurityGroup> exGetNodeSecurityGroups(Node node);


	/**
	 * Create a new Security Group
	 * @param name Name of the new Security Group
	 * 			(:type name: ``str``)
	 * @param description Description of the new Security Group
	 * 			(:type description: ``str``)
	 * @return `OpenStackSecurityGroup`
	**/
	public OpenStackSecurityGroup exCreateSecurityGroup(String name, String description);


	/**
	 * Delete a Security Group.
	 * @param security_group Security Group should be deleted
	 * 			(:type security_group: :class:`OpenStackSecurityGroup`)
	 * @return ``bool``
	**/
	public boolean exDeleteSecurityGroup(OpenStackSecurityGroup security_group);


	/**
	 * Create a new Rule in a Security Group
	 * @param security_group Security Group in which to add the rule
	 * 			(:type security_group: :class:`OpenStackSecurityGroup`)
	 * @param ip_protocol Protocol to which this rule applies
	 * 			Examples: tcp, udp, ...
	 * 			(:type ip_protocol: ``str``)
	 * @param from_port First port of the port range
	 * 			(:type from_port: ``int``)
	 * @param to_port Last port of the port range
	 * 			(:type to_port: ``int``)
	 * @param cidr CIDR notation of the source IP range for this rule
	 * 			(:type cidr: ``str``)
	 * @param source_security_group Existing Security Group to use as the
	 * 			source (instead of CIDR)
	 * 			(:type source_security_group: L{OpenStackSecurityGroup)
	 * @return `OpenStackSecurityGroupRule`
	**/
	public OpenStackSecurityGroupRule exCreateSecurityGroupRule(OpenStackSecurityGroup security_group, String ip_protocol, int from_port, int to_port, String cidr, 
			OpenStackSecurityGroup source_security_group);

	/**
	 * Create a new Rule in a Security Group
	 * @param security_group Security Group in which to add the rule
	 * 			(:type security_group: :class:`OpenStackSecurityGroup`)
	 * @param ip_protocol Protocol to which this rule applies
	 * 			Examples: tcp, udp, ...
	 * 			(:type ip_protocol: ``str``)
	 * @param from_port First port of the port range
	 * 			(:type from_port: ``int``)
	 * @param to_port Last port of the port range
	 * 			(:type to_port: ``int``)
	 * @return `OpenStackSecurityGroupRule`
	**/
	public OpenStackSecurityGroupRule exCreateSecurityGroupRule(OpenStackSecurityGroup security_group, String ip_protocol, int from_port, int to_port);

	/**
	 * Delete a Rule from a Security Group.
	 * @param rule Rule should be deleted
	 * 			(:type rule: :class:`OpenStackSecurityGroupRule`)
	 * @return ``bool``
	**/
	public boolean exDeleteSecurityGroupRule(OpenStackSecurityGroupRule rule);


	/**
	 * Get a list of KeyPairs that are available.
	 * @return `OpenStackKeyPair`
	**/
	public List<OpenStackKeyPair> exListKeypairs();


	/**
	 * Create a new KeyPair
	 * @param name Name of the new KeyPair
	 * 			(:type name: ``str``)
	 * @return `OpenStackKeyPair`
	**/
	public OpenStackKeyPair exCreateKeypair(String name);


	/**
	 * Import a KeyPair from a file
	 * @param name Name of the new KeyPair
	 * 			(:type name: ``str``)
	 * @param keyfile Path to the public key file (in OpenSSH format)
	 * 			(:type keyfile: ``str``)
	 * @return `OpenStackKeyPair`
	**/
	public OpenStackKeyPair exImportKeypair(String name, String keyfile);


	/**
	 * Import a KeyPair from a string
	 * @param name Name of the new KeyPair
	 * 			(:type name: ``str``)
	 * @param key_material Public key (in OpenSSH format)
	 * 			(:type key_material: ``str``)
	 * @return `OpenStackKeyPair`
	**/
	public OpenStackKeyPair exImportKeypairFromString(String name, String key_material);


	/**
	 * Delete a KeyPair.
	 * @param keypair KeyPair to delete
	 * 			(:type keypair: :class:`OpenStackKeyPair`)
	 * @return ``bool``
	**/
	public boolean exDeleteKeypair(OpenStackKeyPair keypair);


	/**
	 * Get a NodeSize
	 * @param size_id ID of the size which should be used
	 * 			(:type size_id: ``str``)
	 * @return `NodeSize`
	**/
	public NodeSize exGetSize(String size_id);


	/**
	 * Get a NodeImage
	 * @param image_id ID of the image which should be used
	 * 			(:type image_id: ``str``)
	 * @return `NodeImage`
	**/
	public NodeImage exGetImage(String image_id);


	/**
	 * Delete a NodeImage
	 * @param image image witch should be used
	 * 			(:type image: :class:`NodeImage`)
	 * @return ``bool``
	**/
	public boolean exDeleteImage(NodeImage image);


	/**
	 * Rescue a node
	 * @param node node
	 * 			(:type node: :class:`Node`)
	 * @param password password
	 * 			(:type password: ``str``)
	 * @return `Node`
	**/
	public Node exRescue(Node node, String password);

	/**
	 * Rescue a node
	 * @param node node
	 * 			(:type node: :class:`Node`)
	 * @return `Node`
	**/
	public Node exRescue(Node node);

	/**
	 * Unrescue a node
	 * @param node node
	 * 			(:type node: :class:`Node`)
	 * @return ``bool``
	**/
	public boolean exUnrescue(Node node);


	/**
	 * List available floating IP pools
	 * @return `OpenStack_1_1_FloatingIpPool`
	**/
	public List<OpenStack_1_1_FloatingIpPool> exListFloatingIpPools();


	/**
	 * Attach the floating IP to the node
	 * @param node node
	 * 			(:type node: :class:`Node`)
	 * @param ip floating IP to attach
	 * 			(:type ip: ``str`` or :class:`OpenStack_1_1_FloatingIpAddress`)
	 * @return ``bool``
	**/
	public boolean exAttachFloatingIpToNode(Node node, String ip);


	/**
	 * Detach the floating IP from the node
	 * @param node node
	 * 			(:type node: :class:`Node`)
	 * @param ip floating IP to remove
	 * 			(:type ip: ``str`` or :class:`OpenStack_1_1_FloatingIpAddress`)
	 * @return ``bool``
	**/
	public boolean exDetachFloatingIpFromNode(Node node, String ip);


	/**
	 * Return the metadata associated with the node.
	 * associating tag names with tag values.
	 * @param node Node instance
	 * 			(:type node: :class:`Node`)
	 * @return A dictionary or other mapping of strings to strings,
	**/
	public Map<String,String> exGetMetadataForNode(Node node);


	public String exPauseNode(Node node);


	public String exUnpauseNode(Node node);


	public String exSuspendNode(Node node);


	public String exResumeNode(Node node);


}