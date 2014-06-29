/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/gogrid.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/gogrid.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/gogrid.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.gogrid;

import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;

import java.util.List;

import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;


public interface GoGridComputeContext extends ComputeContext {

	/**
	 * but return right away with id == None.
	 * The existance of this method is explained by the fact
	 * that GoGrid assigns id to a node only few minutes after
	 * creation.
	 * @param name String with a name for this new node (required)
	 * 			(:type name: ``str``)
	 * @param size The size of resources allocated to this node .
	 * 			(required)
	 * 			(:type size: :class:`NodeSize`)
	 * @param image OS Image to boot on node. (required)
	 * 			(:type image: :class:`NodeImage`)
	 * @param ex_description Description of a Node
	 * 			(:type ex_description: ``str``)
	 * @param ex_ip Public IP address to use for a Node. If not
	 * 			specified, first available IP address will be picked
	 * 			(:type ex_ip: ``str``)
	 * @return `GoGridNode`
	**/
	public GoGridNode exCreateNodeNowait(String name, NodeSize size, NodeImage image, String ex_description, String ex_ip);



	/**
	 * Please refer to GoGrid documentation to get info
	 * how prepare a node for image creation:
	 * http://wiki.gogrid.com/wiki/index.php/MyGSI
	 * @param node node to use as a base for image
	 * 			(:type node: :class:`GoGridNode`)
	 * @param name name for new image
	 * 			(:type name: ``str``)
	 * @return `NodeImage`
	**/
	public NodeImage exSaveImage(GoGridNode node, String name);



	/**
	 * @param node node to be edited (required)
	 * 			(:type node: :class:`GoGridNode`)
	 * @param size new size of a node (required)
	 * 			(:type size: :class:`NodeSize`)
	 * @param ex_description new description of a node
	 * 			(:type ex_description: ``str``)
	 * @return `Node`
	**/
	public Node exEditNode(GoGridNode node, NodeSize size, String ex_description);



	/**
	 * @param image image to be edited (required)
	 * 			(:type image: :class:`NodeImage`)
	 * @param public_ should be the image public (required)
	 * 			(:type public: ``bool``)
	 * @param ex_description description of the image (optional)
	 * 			(:type ex_description: ``str``)
	 * @param name name of the image
	 * 			(:type name: ``str``)
	 * @return `NodeImage`
	**/
	public NodeImage exEditImage(NodeImage image, boolean public_, String ex_description, String name);



	/**
	 * the account.
	 * @param public_ set to True to list only
	 * 			public IPs or False to list only
	 * 			private IPs. Set to None or not specify
	 * 			at all not to filter by type
	 * 			(:type public: ``bool``)
	 * @param assigned set to True to list only addresses
	 * 			assigned to servers, False to list unassigned
	 * 			addresses and set to None or don't set at all
	 * 			not no filter by state
	 * 			(:type assigned: ``bool``)
	 * @param location filter IP addresses by location
	 * 			(:type location: :class:`NodeLocation`)
	 * @return `GoGridIpAddress`
	**/
	public List<GoGridIpAddress> exListIps(boolean public_, boolean assigned, NodeLocation location);



}