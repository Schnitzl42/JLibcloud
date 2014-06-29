/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/hostvirtual.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/hostvirtual.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/hostvirtual.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.hostvirtual;

import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;


public interface HostVirtualComputeContext extends ComputeContext {

	/**
	 * Get a single node.
	 * @param node_id id of the node that we need the node object for
	 * 			(:type node_id: ``str``)
	 * @return `Node`
	**/
	public Node exGetNode(String node_id);



	/**
	 * Stop a node.
	 * @param node Node which should be used
	 * 			(:type node: :class:`Node`)
	 * @return ``bool``
	**/
	public boolean exStopNode(Node node);



	/**
	 * Start a node.
	 * @param node Node which should be used
	 * 			(:type node: :class:`Node`)
	 * @return ``bool``
	**/
	public boolean exStartNode(Node node);



	/**
	 * Provision a server on a VR package and get it booted
	 * @param node node which should be used
	 * 			(:type node: :class:`Node`)
	 * @param image The distribution to deploy on your server (mandatory)
	 * 			(:type image: :class:`NodeImage`)
	 * @param auth an SSH key or root password (mandatory)
	 * 			(:type auth: :class:`NodeAuthSSHKey` or :class:`NodeAuthPassword`)
	 * @param location which datacenter to create the server in
	 * 			(:type location: :class:`NodeLocation`)
	 * @return Node representing the newly built server
	 * 			(:rtype: :class:`Node`)
	**/
	public Node exProvisionNode(Node node, NodeImage image, String auth, boolean isSshKey, NodeLocation location);



	/**
	 * Delete a node.
	 * @param node Node which should be used
	 * 			(:type node: :class:`Node`)
	 * @return ``bool``
	**/
	public boolean exDeleteNode(Node node);



}