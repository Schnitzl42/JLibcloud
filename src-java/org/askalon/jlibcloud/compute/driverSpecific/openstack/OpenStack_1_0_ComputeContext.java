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

import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;

import java.util.Map;


public interface OpenStack_1_0_ComputeContext extends OpenStackComputeContext {

	/**
	 * Sets the Node's root password.
	 * This will reboot the instance to complete the operation.
	 * :class:`Node.extra['password']` will be set to the new value if the
	 * operation was successful.
	 * @param node node to set password
	 * 			(:type node: :class:`Node`)
	 * @param password new password.
	 * 			(:type password: ``str``)
	 * @return ``bool``
	**/
	public boolean exSetPassword(Node node, String password);


	/**
	 * Sets the Node's name.
	 * This will reboot the instance to complete the operation.
	 * @param node node to set name
	 * 			(:type node: :class:`Node`)
	 * @param name new name
	 * 			(:type name: ``str``)
	 * @return ``bool``
	**/
	public boolean exSetServerName(Node node, String name);


	/**
	 * Change an existing server flavor / scale the server up or down.
	 * @param node node to resize.
	 * 			(:type node: :class:`Node`)
	 * @param size new size.
	 * 			(:type size: :class:`NodeSize`)
	 * @return ``bool``
	**/
	public boolean exResize(Node node, NodeSize size);


	/**
	 * Confirm a resize request which is currently in progress. If a resize
	 * request is not explicitly confirmed or reverted it's automatically
	 * confirmed after 24 hours.
	 * For more info refer to the API documentation: http://goo.gl/zjFI1
	 * @param node node for which the resize request will be confirmed.
	 * 			(:type node: :class:`Node`)
	 * @return ``bool``
	**/
	public boolean exConfirmResize(Node node);


	/**
	 * Revert a resize request which is currently in progress.
	 * All resizes are automatically confirmed after 24 hours if they have
	 * not already been confirmed explicitly or reverted.
	 * For more info refer to the API documentation: http://goo.gl/AizBu
	 * @param node node for which the resize request will be reverted.
	 * 			(:type node: :class:`Node`)
	 * @return ``bool``
	**/
	public boolean exRevertResize(Node node);


	/**
	 * Rebuilds the specified server.
	 * @param node_id ID of the node which should be used
	 * 			(:type node_id: ``str``)
	 * @param image_id ID of the image which should be used
	 * 			(:type image_id: ``str``)
	 * @return ``bool``
	**/
	public boolean exRebuild(String node_id, String image_id);


	/**
	 * Creates a shared IP group.
	 * @param group_name group name which should be used
	 * 			(:type group_name: ``str``)
	 * @param node_id ID of the node which should be used
	 * 			(:type node_id: ``str``)
	 * @return ``bool``
	**/
	public boolean exCreateIpGroup(String group_name, String node_id);

	/**
	 * Creates a shared IP group.
	 * @param group_name group name which should be used
	 * 			(:type group_name: ``str``)
	 * @return ``bool``
	**/
	public boolean exCreateIpGroup(String group_name);

	/**
	 * Lists IDs and names for shared IP groups.
	 * If details lists all details for shared IP groups.
	 * @param details True if details is required
	 * 			(:type details: ``bool``)
	 * @return `OpenStack_1_0_SharedIpGroup`
	**/
	public List<OpenStack_1_0_SharedIpGroup> exListIpGroups(boolean details);

	/**
	 * Lists IDs and names for shared IP groups.
	 * If details lists all details for shared IP groups.
	 * @return `OpenStack_1_0_SharedIpGroup`
	**/
	public List<OpenStack_1_0_SharedIpGroup> exListIpGroups();

	/**
	 * Deletes the specified shared IP group.
	 * @param group_id group id which should be used
	 * 			(:type group_id: ``str``)
	 * @return ``bool``
	**/
	public boolean exDeleteIpGroup(String group_id);


	/**
	 * Shares an IP address to the specified server.
	 * @param group_id group id which should be used
	 * 			(:type group_id: ``str``)
	 * @param node_id ID of the node which should be used
	 * 			(:type node_id: ``str``)
	 * @param ip ip which should be used
	 * 			(:type ip: ``str``)
	 * @param configure_node configure node
	 * 			(:type configure_node: ``bool``)
	 * @return ``bool``
	**/
	public boolean exShareIp(String group_id, String node_id, String ip, boolean configure_node);

	/**
	 * Shares an IP address to the specified server.
	 * @param group_id group id which should be used
	 * 			(:type group_id: ``str``)
	 * @param node_id ID of the node which should be used
	 * 			(:type node_id: ``str``)
	 * @param ip ip which should be used
	 * 			(:type ip: ``str``)
	 * @return ``bool``
	**/
	public boolean exShareIp(String group_id, String node_id, String ip);

	/**
	 * Removes a shared IP address from the specified server.
	 * @param node_id ID of the node which should be used
	 * 			(:type node_id: ``str``)
	 * @param ip ip which should be used
	 * 			(:type ip: ``str``)
	 * @return ``bool``
	**/
	public boolean exUnshareIp(String node_id, String ip);


	/**
	 * List all server addresses.
	 * @param node_id ID of the node which should be used
	 * 			(:type node_id: ``str``)
	 * @return `OpenStack_1_0_NodeIpAddresses`
	**/
	public OpenStack_1_0_NodeIpAddresses exListIpAddresses(String node_id);


	/**
	 * Extra call to get account's limits, such as
	 * rates (for example amount of POST requests per day)
	 * and absolute limits like total amount of available
	 * RAM to be used by servers.
	 * @return dict with keys 'rate' and 'absolute'
	 * 			(:rtype: ``dict``)
	**/
	public Map<String,String> exLimits();


	/**
	 * @param node node to use as a base for image
	 * 			(:type node: :class:`Node`)
	 * @param name name for new image
	 * 			(:type name: ``str``)
	 * @return `NodeImage`
	**/
	public NodeImage exSaveImage(Node node, String name);


	/**
	 * @param image the image to be deleted
	 * 			(:type image: :class:`NodeImage`)
	 * @return ``bool``
	**/
	public boolean exDeleteImage(NodeImage image);


}