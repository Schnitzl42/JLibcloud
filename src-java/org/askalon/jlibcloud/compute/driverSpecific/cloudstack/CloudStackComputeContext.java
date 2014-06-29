/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/cloudstack.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/cloudstack.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/cloudstack.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.cloudstack;

import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;

import java.util.List;

import java.util.Map;

import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;


public interface CloudStackComputeContext extends ComputeContext {

	/**
	 * Starts/Resumes a stopped virtual machine
	 * 			
	 * @param node
	 * 			(:type node: :class:`CloudStackNode`)
	 * 
	 * @return rtype ``str``
	**/
	public String exStart(CloudStackNode node);


	/**
	 * Stops/Suspends a running virtual machine
	 * @param node Node to stop.
	 * 			(:type node: :class:`CloudStackNode`)
	 * @return ``str``
	**/
	public String exStop(CloudStackNode node);


	/**
	 * Fetch a list of all available disk offerings.
	 * @return `CloudStackDiskOffering`
	**/
	public List<CloudStackDiskOffering> exListDiskOfferings();


	/**
	 * List the available networks
	 * @return `CloudStackNetwork`
	**/
	public List<CloudStackNetwork> exListNetworks();


	/**
	 * Lists all Public IP Addresses.
	 * @return `CloudStackAddress`
	**/
	public List<CloudStackAddress> exListPublicIps();


	/**
	 * Allocate a public IP.
	 * @param location Zone
	 * 			(:type location: :class:`NodeLocation`)
	 * @return `CloudStackAddress`
	**/
	public CloudStackAddress exAllocatePublicIp(NodeLocation location);

	/**
	 * Allocate a public IP.
	 * @return `CloudStackAddress`
	**/
	public CloudStackAddress exAllocatePublicIp();

	/**
	 * Release a public IP.
	 * @param address CloudStackAddress which should be used
	 * 			(:type address: :class:`CloudStackAddress`)
	 * @return ``bool``
	**/
	public boolean exReleasePublicIp(CloudStackAddress address);


	/**
	 * Lists all Port Forwarding Rules
	 * @return `CloudStackPortForwardingRule`
	**/
	public List<CloudStackPortForwardingRule> exListPortForwardingRules();


	/**
	 * Creates a Port Forwarding Rule, used for Source NAT
	 * @param node The virtual machine
	 * 			(:type node: :class:`CloudStackNode`)
	 * @param address IP address of the Source NAT
	 * 			(:type address: :class:`CloudStackAddress`)
	 * @param private_port Port of the virtual machine
	 * 			(:type private_port: ``int``)
	 * @param public_port Public port on the Source NAT address
	 * 			(:type public_port: ``int``)
	 * @param protocol Protocol of the rule
	 * 			(:type protocol: ``str``)
	 * @return `CloudStackPortForwardingRule`
	**/
	public CloudStackPortForwardingRule exCreatePortForwardingRule(CloudStackNode node, CloudStackAddress address, int private_port, int public_port, String protocol, 
			int public_end_port, int private_end_port, boolean openfirewall);

	/**
	 * Creates a Port Forwarding Rule, used for Source NAT
	 * @param node The virtual machine
	 * 			(:type node: :class:`CloudStackNode`)
	 * @param address IP address of the Source NAT
	 * 			(:type address: :class:`CloudStackAddress`)
	 * @param private_port Port of the virtual machine
	 * 			(:type private_port: ``int``)
	 * @param public_port Public port on the Source NAT address
	 * 			(:type public_port: ``int``)
	 * @param protocol Protocol of the rule
	 * 			(:type protocol: ``str``)
	 * @return `CloudStackPortForwardingRule`
	**/
	public CloudStackPortForwardingRule exCreatePortForwardingRule(CloudStackNode node, CloudStackAddress address, int private_port, int public_port, String protocol);

	/**
	 * Remove a Port forwarding rule.
	 * @param node Node used in the rule
	 * 			(:type node: :class:`CloudStackNode`)
	 * @param rule Forwarding rule which should be used
	 * 			(:type rule: :class:`CloudStackPortForwardingRule`)
	 * @return ``bool``
	**/
	public boolean exDeletePortForwardingRule(CloudStackNode node, CloudStackPortForwardingRule rule);


	/**
	 * "Add a NAT/firewall forwarding rule.
	 * @param node Node which should be used
	 * 			(:type node: :class:`CloudStackNode`)
	 * @param address CloudStackAddress which should be used
	 * 			(:type address: :class:`CloudStackAddress`)
	 * @param protocol Protocol which should be used (TCP or UDP)
	 * 			(:type protocol: ``str``)
	 * @param start_port Start port which should be used
	 * 			(:type start_port: ``int``)
	 * @param end_port End port which should be used
	 * 			(:type end_port: ``int``)
	 * @return `CloudStackForwardingRule`
	**/
	public CloudStackIPForwardingRule exCreateIpForwardingRule(CloudStackNode node, CloudStackAddress address, String protocol, int start_port, int end_port);

	/**
	 * "Add a NAT/firewall forwarding rule.
	 * @param node Node which should be used
	 * 			(:type node: :class:`CloudStackNode`)
	 * @param address CloudStackAddress which should be used
	 * 			(:type address: :class:`CloudStackAddress`)
	 * @param protocol Protocol which should be used (TCP or UDP)
	 * 			(:type protocol: ``str``)
	 * @param start_port Start port which should be used
	 * 			(:type start_port: ``int``)
	 * @return `CloudStackForwardingRule`
	**/
	public CloudStackIPForwardingRule exCreateIpForwardingRule(CloudStackNode node, CloudStackAddress address, String protocol, int start_port);

	/**
	 * Remove a NAT/firewall forwarding rule.
	 * @param node Node which should be used
	 * 			(:type node: :class:`CloudStackNode`)
	 * @param rule Forwarding rule which should be used
	 * 			(:type rule: :class:`CloudStackForwardingRule`)
	 * @return ``bool``
	**/
	public boolean exDeleteIpForwardingRule(CloudStackNode node, CloudStackIPForwardingRule rule);


	/**
	 * List Registered SSH Key Pairs
	 * @param projectid list objects by project
	 * 			(:type projectid: ``str``)
	 * @param page The page to list the keypairs from
	 * 			(:type page: ``int``)
	 * @param keyword List by keyword
	 * 			(:type keyword: ``str``)
	 * @param listall If set to false, list only resources
	 * 			belonging to the command's caller;
	 * 			if set to true - list resources that
	 * 			the caller is authorized to see.
	 * 			Default value is false
	 * 			
	 * 			(:type listall: ``bool``)
	 * @param pagesize The number of results per page
	 * 			(:type pagesize: ``int``)
	 * @param account List resources by account.
	 * 			Must be used with the domainId parameter
	 * 			(:type account: ``str``)
	 * @param isrecursive Defaults to false, but if true,
	 * 			lists all resources from
	 * 			the parent specified by the
	 * 			domainId till leaves.
	 * 			(:type isrecursive: ``bool``)
	 * @param fingerprint A public key fingerprint to look for
	 * 			(:type fingerprint: ``str``)
	 * @param name A key pair name to look for
	 * 			(:type name: ``str``)
	 * @param domainid List only resources belonging to
	 * 			the domain specified
	 * 			(:type domainid: ``str``)
	 * @return A list of keypair dictionaries
	 * 			(:rtype: ``list`` of ``dict``)
	**/
	@Deprecated
	public List<Map<String,String>> exListKeypairs(String projectid, int page, String keyword, boolean listall, int pagesize, 
			String account, boolean isrecursive, String fingerprint, String name, 
			String domainid);


	/**
	 * Creates a SSH KeyPair, returns fingerprint and private key
	 * @param name Name of the keypair (required)
	 * 			(:type name: ``str``)
	 * @param projectid An optional project for the ssh key
	 * 			(:type projectid: ``str``)
	 * @param domainid An optional domainId for the ssh key.
	 * 			If the account parameter is used,
	 * 			domainId must also be used.
	 * 			(:type domainid: ``str``)
	 * @param account An optional account for the ssh key.
	 * 			Must be used with domainId.
	 * 			(:type account: ``str``)
	 * @return A keypair dictionary
	 * 			(:rtype: ``dict``)
	**/
	@Deprecated
	public Map<String,String> exCreateKeypair(String name, String projectid, String domainid, String account);


	/**
	 * Imports a new public key where the public key is passed in as a string
	 * @param name The name of the public key to import.
	 * 			(:type name: ``str``)
	 * @param key_material The contents of a public key file.
	 * 			(:type key_material: ``str``)
	 * @return ``dict``
	**/
	@Deprecated
	public Map<String,String> exImportKeypairFromString(String name, String key_material);


	/**
	 * Imports a new public key where the public key is passed via a filename
	 * @param name The name of the public key to import.
	 * 			(:type name: ``str``)
	 * @param keyfile The filename with path of the public key to import.
	 * 			(:type keyfile: ``str``)
	 * @return ``dict``
	**/
	@Deprecated
	public Map<String,String> exImportKeypair(String name, String keyfile);


	/**
	 * Deletes an existing SSH KeyPair
	 * @param keypair Name of the keypair (required)
	 * 			(:type keypair: ``str``)
	 * @param projectid The project associated with keypair
	 * 			(:type projectid: ``str``)
	 * @param domainid The domain ID associated with the keypair
	 * 			(:type domainid: ``str``)
	 * @param account The account associated with the keypair.
	 * 			Must be used with the domainId parameter.
	 * 			(:type account: ``str``)
	 * @return True of False based on success of Keypair deletion
	 * 			(:rtype: ``bool``)
	**/
	@Deprecated
	public boolean exDeleteKeypair(String keypair, String projectid, String domainid, String account);


	/**
	 * Lists Security Groups
	 * @param domainid List only resources belonging to the domain specified
	 * 			(:type domainid: ``str``)
	 * @param account List resources by account. Must be used with
	 * 			the domainId parameter.
	 * 			(:type account: ``str``)
	 * @param listall If set to false, list only resources belonging to
	 * 			the command's caller; if set to true
	 * 			list resources that the caller is
	 * 			authorized to see.
	 * 			Default value is false
	 * 			(:type listall: ``bool``)
	 * @param pagesize Number of entries per page
	 * 			(:type pagesize: ``int``)
	 * @param keyword List by keyword
	 * 			(:type keyword: ``str``)
	 * @param tags List resources by tags (key/value pairs)
	 * 			(:type tags: ``dict``)
	 * @param id list the security group by the id provided
	 * 			(:type id: ``str``)
	 * @param securitygroupname lists security groups by name
	 * 			(:type securitygroupname: ``str``)
	 * @param virtualmachineid lists security groups by virtual machine id
	 * 			(:type virtualmachineid: ``str``)
	 * @param projectid list objects by project
	 * 			(:type projectid: ``str``)
	 * @param isrecursive (boolean) defaults to false, but if true,
	 * 			lists all resources from the parent
	 * 			specified by the domainId till leaves.
	 * 			(:type isrecursive: ``bool``)
	 * @param page (integer)
	 * 			(:type page: ``int``)
	 * @return rtype ``list``
	**/
	public List<String> exListSecurityGroups(String domainid, String account, boolean listall, int pagesize, String keyword, 
			Map<String,Arg> tags, String id, String securitygroupname, String virtualmachineid, 
			String projectid, boolean isrecursive, int page);


	public List<String> exListSecurityGroups();
	
	/**
	 * Creates a new Security Group
	 * @param name name of the security group (required)
	 * 			(:type name: ``str``)
	 * @param account An optional account for the security group.
	 * 			Must be used with domainId.
	 * 			(:type account: ``str``)
	 * @param domainid An optional domainId for the security group.
	 * 			If the account parameter is used,
	 * 			domainId must also be used.
	 * 			(:type domainid: ``str``)
	 * @param description The description of the security group
	 * 			(:type description: ``str``)
	 * @param projectid Deploy vm for the project
	 * 			(:type projectid: ``str``)
	 * @return ``dict``
	**/
	public Map<String,String> exCreateSecurityGroup(String name, String account, String domainid, String description, String projectid);


	/**
	 * Deletes a given Security Group
	 * @param name The ID of the security group.
	 * 			Mutually exclusive with id parameter
	 * 			(:type name: ``str``)
	 * @return ``bool``
	**/
	public boolean exDeleteSecurityGroup(String name);


	/**
	 * Creates a new Security Group Ingress rule
	 * @param securitygroupname The name of the security group.
	 * 			Mutually exclusive with
	 * 			securityGroupName parameter
	 * 			(:type securitygroupname: ``str``)
	 * @param protocol TCP is default. UDP is the other supported protocol
	 * 			(:type protocol: ``str``)
	 * @param cidrlist The cidr list associated
	 * 			(:type cidrlist: ``list``)
	 * @param startport Start port for this ingress rule
	 * 			(:type startport: ``int``)
	 * @param endport end port for this ingress rule
	 * 			(:type endport: ``int``)
	 *
	 * @return ``list``
	**/
	public List<String> exAuthorizeSecurityGroupIngress(String securitygroupname, String protocol, List<String> cidrlist, int startport, int endport);

			
			
	/**
	 * Creates a new Security Group Ingress rule
	 * @param securitygroupname The name of the security group.
	 * 			Mutually exclusive with
	 * 			securityGroupName parameter
	 * 			(:type securitygroupname: ``str``)
	 * @param protocol TCP is default. UDP is the other supported protocol
	 * 			(:type protocol: ``str``)
	 * @param cidrlist The cidr list associated
	 * 			(:type cidrlist: ``list``)
	 * @param startport Start port for this ingress rule
	 * 			(:type startport: ``int``)
	 *
	 * @return ``list``
	**/
	public List<String> exAuthorizeSecurityGroupIngress(String securitygroupname, String protocol, List<String> cidrlist, int startport);

	/**
	 * Registers an existing ISO by URL.
	 * @param name Name which should be used
	 * 			(:type name: ``str``)
	 * @param url Url should be used
	 * 			(:type url: ``str``)
	 * @param location Location which should be used
	 * 			(:type location: :class:`NodeLocation`)
	 * @return ``str``
	**/
	public String exRegisterIso(String name, String url, NodeLocation location);

	/**
	 * Registers an existing ISO by URL.
	 * @param name Name which should be used
	 * 			(:type name: ``str``)
	 * @param url Url should be used
	 * 			(:type url: ``str``)
	 * @return ``str``
	**/
	public String exRegisterIso(String name, String url);

	/**
	 * Extra call to get account's resource limits, such as
	 * the amount of instances, volumes, snapshots and networks.
	 * CloudStack uses integers as the resource type so we will convert
	 * them to a more human readable string using the resource map
	 * @return dict
	 * 			(:rtype: ``dict``)
	**/
	public Map<String,String> exLimits();


}