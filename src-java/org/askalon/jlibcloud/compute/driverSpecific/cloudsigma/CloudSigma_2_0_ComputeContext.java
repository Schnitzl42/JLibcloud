/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/cloudsigma.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/cloudsigma.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/cloudsigma.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.cloudsigma;

import java.util.List;
import java.util.Map;

import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;


public interface CloudSigma_2_0_ComputeContext extends ComputeContext {

	/**
	 * Edit a node.
	 * @param node Node to edit.
	 * 			(:type node: :class:`libcloud.compute.base.Node`)
	 * @param params Node parameters to update.
	 * 			(:type params: ``dict``)
	 * @return return Edited node.
	 * 			(:rtype: :class:`libcloud.compute.base.Node`)
	**/
	public Node exEditNode(Node node, Map<String,Arg> params);


	/**
	 * Start a node.
	 * @param node Node to start.
	 * 			(:type node: :class:`libcloud.compute.base.Node`)
	 * @param ex_avoid A list of other server uuids to avoid when
	 * 			starting this node. If provided, node will
	 * 			attempt to be started on a different
	 * 			physical infrastructure from other servers
	 * 			specified using this argument. (optional)
	**/
	public boolean exStartNode(Node node, String ex_avoid);

	/**
	 * Start a node.
	 * @param node Node to start.
	 * 			(:type node: :class:`libcloud.compute.base.Node`)
	 * 	public String exStartNode(Node node);

	/**
	 * Stop a node.
	**/
	public boolean exStopNode(Node node);


	/**
	 * Clone the provided node.
	 * @param name Optional name for the cloned node.
	 * 			(:type name: ``str``)
	 * @param random_vnc_password If True, a new random VNC password will be
	 * 			generated for the cloned node. Otherwise
	 * 			password from the cloned node will be
	 * 			reused.
	 * 			(:type random_vnc_password: ``bool``)
	 * @return Cloned node.
	 * 			(:rtype: :class:`libcloud.compute.base.Node`)
	**/
	public Node exCloneNode(Node node, String name, boolean random_vnc_password);

	/**
	 * Clone the provided node.
	 * @return Cloned node.
	 * 			(:rtype: :class:`libcloud.compute.base.Node`)
	**/
	public Node exCloneNode(Node node);

	/**
	 * Open a VNC tunnel to the provided node and return the VNC url.
	 * @param node Node to open the VNC tunnel to.
	 * 			(:type node: :class:`libcloud.compute.base.Node`)
	 * @return URL of the opened VNC tunnel.
	 * 			(:rtype: ``str``)
	**/
	public String exOpenVncTunnel(Node node);


	/**
	 * Close a VNC server to the provided node.
	 * @param node Node to close the VNC tunnel to.
	 * 			(:type node: :class:`libcloud.compute.base.Node`)
	 * @return ``True`` on success, ``False`` otherwise.
	 * 			(:rtype: ``bool``)
	**/
	public boolean exCloseVncTunnel(Node node);


	/**
	 * Return a list of all the available library drives (pre-installed and
	 * installation CDs).
	 * @return `.CloudSigmaDrive` objects
	**/
	public List<CloudSigmaDrive> exListLibraryDrives();


	/**
	 * Return a list of all the available user's drives.
	 * @return `.CloudSigmaDrive` objects
	**/
	public List<CloudSigmaDrive> exListUserDrives();


	/**
	 * Create a new drive.
	 * @param name Drive name.
	 * 			(:type name: ``str``)
	 * @param size Drive size in bytes.
	 * 			(:type size: ``int``)
	 * @param media Drive media type (cdrom, disk).
	 * 			(:type media: ``str``)
	 * @param ex_avoid A list of other drive uuids to avoid when
	 * 			creating this drive. If provided, drive will
	 * 			attempt to be created on a different
	 * 			physical infrastructure from other drives
	 * 			specified using this argument. (optional)
	 * 			(:type ex_avoid: ``list``)
	 * @return Created drive object.
	 * 			(:rtype: :class:`.CloudSigmaDrive`)
	**/
	public CloudSigmaDrive exCreateDrive(String name, int size, String media, List<String> ex_avoid);

	/**
	 * Create a new drive.
	 * @param name Drive name.
	 * 			(:type name: ``str``)
	 * @param size Drive size in bytes.
	 * 			(:type size: ``int``)
	 * @return Created drive object.
	 * 			(:rtype: :class:`.CloudSigmaDrive`)
	**/
	public CloudSigmaDrive exCreateDrive(String name, int size);

	/**
	 * Clone a library or a standard drive.
	 * :class:`.CloudSigmaDrive`
	 * @param drive Drive to clone.
	 * 			(:type drive: :class:`libcloud.compute.base.NodeImage` or :class:`.CloudSigmaDrive`)
	 * @param name Optional name for the cloned drive.
	 * 			(:type name: ``str``)
	 * @param ex_avoid A list of other drive uuids to avoid when
	 * 			creating this drive. If provided, drive will
	 * 			attempt to be created on a different
	 * 			physical infrastructure from other drives
	 * 			specified using this argument. (optional)
	 * 			(:type ex_avoid: ``list``)
	 * @return New cloned drive.
	 * 			(:rtype: :class:`.CloudSigmaDrive`)
	**/
	public CloudSigmaDrive exCloneDrive(NodeImage drive, String name, List<String> ex_avoid);

	/**
	 * Clone a library or a standard drive.
	 * :class:`.CloudSigmaDrive`
	 * @param drive Drive to clone.
	 * 			(:type drive: :class:`libcloud.compute.base.NodeImage` or :class:`.CloudSigmaDrive`)
	 * @return New cloned drive.
	 * 			(:rtype: :class:`.CloudSigmaDrive`)
	**/
	public CloudSigmaDrive exCloneDrive(NodeImage drive);

	/**
	 * Resize a drive.
	 * @param drive Drive to resize.
	 * 			
	 * @param size New drive size in bytes.
	 * 			(:type size: ``int``)
	 * @return Drive object which is being resized.
	 * 			(:rtype: :class:`.CloudSigmaDrive`)
	**/
	public CloudSigmaDrive exResizeDrive(String drive, int size);


	/**
	 * Attach a drive to the provided node.
	**/
	public void exAttachDrive(Node node);


	/**
	 * Retrieve information about a single drive.
	 * @param drive_id ID of the drive to retrieve.
	 * 			(:type drive_id: ``str``)
	 * @return Drive object.
	 * 			(:rtype: :class:`.CloudSigmaDrive`)
	**/
	public CloudSigmaDrive exGetDrive(String drive_id);


	/**
	 * List firewall policies.
	 * @return `.CloudSigmaFirewallPolicy`
	**/
	public List<CloudSigmaFirewallPolicy> exListFirewallPolicies();


	/**
	 * Create a firewall policy.
	 * @param name Policy name.
	 * 			(:type name: ``str``)
	 * @param rules List of firewall policy rules to associate with this
	 * 			policy. (optional)
	 * 			(:type rules: ``list`` of ``dict``)
	 * @return Created firewall policy object.
	 * 			(:rtype: :class:`.CloudSigmaFirewallPolicy`)
	**/
	public CloudSigmaFirewallPolicy exCreateFirewallPolicy(String name, List<Map<String,Arg>> rules);

	/**
	 * Create a firewall policy.
	 * @param name Policy name.
	 * 			(:type name: ``str``)
	 * @return Created firewall policy object.
	 * 			(:rtype: :class:`.CloudSigmaFirewallPolicy`)
	**/
	public CloudSigmaFirewallPolicy exCreateFirewallPolicy(String name);

	/**
	 * Attach firewall policy to a public NIC interface on the server.
	 * @param policy Firewall policy to attach.
	 * 			(:type policy: :class:`.CloudSigmaFirewallPolicy`)
	 * @param node Node to attach policy to.
	 * 			(:type node: :class:`libcloud.compute.base.Node`)
	 * @param nic_mac Optional MAC address of the NIC to add the policy to.
	 * 			If not specified, first public interface is used
	 * 			instead.
	 * 			(:type nic_mac: ``str``)
	 * @return Node object to which the policy was attached to.
	 * 			(:rtype: :class:`libcloud.compute.base.Node`)
	**/
	public Node exAttachFirewallPolicy(CloudSigmaFirewallPolicy policy, Node node, String nic_mac);

	/**
	 * Attach firewall policy to a public NIC interface on the server.
	 * @param policy Firewall policy to attach.
	 * 			(:type policy: :class:`.CloudSigmaFirewallPolicy`)
	 * @param node Node to attach policy to.
	 * 			(:type node: :class:`libcloud.compute.base.Node`)
	 * @return Node object to which the policy was attached to.
	 * 			(:rtype: :class:`libcloud.compute.base.Node`)
	**/
	public Node exAttachFirewallPolicy(CloudSigmaFirewallPolicy policy, Node node);

	/**
	 * Delete a firewall policy.
	 * @param policy Policy to delete to.
	 * 			(:type policy: :class:`.CloudSigmaFirewallPolicy`)
	 * @return ``True`` on success, ``False`` otherwise.
	 * 			(:rtype: ``bool``)
	**/
	public boolean exDeleteFirewallPolicy(CloudSigmaFirewallPolicy policy);


	/**
	 * Return which running servers share the same physical compute host.
	 * host. Servers which share the same host will be stored under
	 * the same list index.
	 * @return A list of server UUIDs which share the same physical compute
	 * 			(:rtype: ``list`` of ``list``)
	**/
	public List<List<String>> exListServersAvailabilityGroups();


	/**
	 * Return which drives share the same physical storage host.
	 * host. Drives which share the same host will be stored under
	 * the same list index.
	 * @return A list of drive UUIDs which share the same physical storage
	 * 			(:rtype: ``list`` of ``list``)
	**/
	public List<List<String>> exListDrivesAvailabilityGroups();


	/**
	 * List all the available tags.
	 * @return `.CloudSigmaTag` objects
	**/
	public List<CloudSigmaTag> exListTags();


	/**
	 * Retrieve a single tag.
	 * @param id ID of the tag to retrieve.
	 * 			(:type id: ``str``)
	 * @return `.CloudSigmaTag` objects
	**/
	public List<CloudSigmaTag> exGetTag(String tag_id, String id);


	/**
	 * Create a tag.
	 * @param name Tag name.
	 * 			(:type name: ``str``)
	 * @param resource_uuids Optional list of resource UUIDs to assign this
	 * 			tag go.
	 * 			(:type resource_uuids: ``list`` of ``str``)
	 * @return Created tag object.
	 * 			(:rtype: :class:`.CloudSigmaTag`)
	**/
	public CloudSigmaTag exCreateTag(String name, List<String> resource_uuids);

	/**
	 * Create a tag.
	 * @param name Tag name.
	 * 			(:type name: ``str``)
	 * @return Created tag object.
	 * 			(:rtype: :class:`.CloudSigmaTag`)
	**/
	public CloudSigmaTag exCreateTag(String name);

	/**
	 * Associate tag with the provided resource.
	 * :class:`.CloudSigmaDrive`
	 * @param resource Resource to associate a tag with.
	 * 			(:type resource: :class:`libcloud.compute.base.Node` or :class:`.CloudSigmaDrive`)
	 * @param tag Tag to associate with the resources.
	 * 			(:type tag: :class:`.CloudSigmaTag`)
	 * @return Updated tag object.
	 * 			(:rtype: :class:`.CloudSigmaTag`)
	**/
	public CloudSigmaTag exTagResource(Node resource, CloudSigmaTag tag);


	/**
	 * Associate tag with the provided resources.
	 * :class:`.CloudSigmaDrive`
	 * @param tag Tag to associate with the resources.
	 * 			(:type tag: :class:`.CloudSigmaTag`)
	 * @param resources Resources to associate a tag with.
	 * 			(:type resource: ``list`` of :class:`libcloud.compute.base.Node` or :class:`.CloudSigmaDrive`)
	 * @return Updated tag object.
	 * 			(:rtype: :class:`.CloudSigmaTag`)
	**/
	public CloudSigmaTag exTagResources(List<Node> resources, CloudSigmaTag tag);


	/**
	 * Delete a tag.
	 * @param tag Tag to delete.
	 * 			(:type tag: :class:`.CloudSigmaTag`)
	 * @return ``True`` on success, ``False`` otherwise.
	 * 			(:rtype: ``bool``)
	**/
	public boolean exDeleteTag(CloudSigmaTag tag);


	/**
	 * Retrueve account balance information.
	 * @return Dictionary with two items ("balance" and "currency").
	 * 			(:rtype: ``dict``)
	**/
	public Map<String,String> exGetBalance();


	/**
	 * Retrive pricing information that are applicable to the cloud.
	 * @return Dictionary with pricing information.
	 * 			(:rtype: ``dict``)
	**/
	public Map<String,String> exGetPricing();


	/**
	 * Retrieve account current usage information.
	 * @return Dictionary with two items ("balance" and "usage").
	 * 			(:rtype: ``dict``)
	**/
	public Map<String,String> exGetUsage();


	/**
	 * List subscriptions for this account.
	 * @param status Only return subscriptions with the provided status
	 * 			(optional).
	 * 			(:type status: ``str``)
	 * @param resources Only return subscriptions for the provided resources
	 * 			(optional).
	 * 			(:type resources: ``list``)
	 * @return ``list``
	**/
	public List<String> exListSubscriptions(String status, List<String> resources);

	/**
	 * List subscriptions for this account.
	 * @return ``list``
	**/
	public List<String> exListSubscriptions();

	/**
	 * Toggle subscription auto renew status.
	 * @param subscription Subscription to toggle the auto renew flag for.
	 * 			(:type subscription: :class:`.CloudSigmaSubscription`)
	 * @return ``True`` on success, ``False`` otherwise.
	 * 			(:rtype: ``bool``)
	**/
	public boolean exToggleSubscriptionAutoRenew(CloudSigmaSubscription subscription);


	/**
	 * Create a new subscription.
	 * @param amount Subscription amount. For example, in dssd case this
	 * 			would be disk size in gigabytes.
	 * 			(:type amount: ``int``)
	 * @param period 30 days, 1 week, 1
	 * 			month, ...
	 * 			(:type period: ``str``)
	 * @param resource Resource the purchase the subscription for.
	 * 			(:type resource: ``str``)
	 * @param auto_renew True to automatically renew the subscription.
	**/
	public CloudSigmaSubscription exCreateSubscription(int amount, String period, String resource, String auto_renew);

	/**
	 * Create a new subscription.
	 * @param amount Subscription amount. For example, in dssd case this
	 * 			would be disk size in gigabytes.
	 * 			(:type amount: ``int``)
	 * @param period 30 days, 1 week, 1
	 * 			month, ...
	 * 			(:type period: ``str``)
	 * @param resource Resource the purchase the subscription for.
	 * 			(:type resource: ``str``)
	 **/
	public CloudSigmaSubscription exCreateSubscription(int amount, String period, String resource);

	/**
	 * Retrieve all the basic and sensible limits of the API.
	 * @return ``dict``
	**/
	public Map<String,String> exListCapabilities();


}