/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/abiquo.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/abiquo.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/abiquo.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.abiquo;

import java.util.List;

import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;


public interface AbiquoComputeContext extends ComputeContext {

	/**
	 * Runs a node
	 * Here there is a bit difference between Abiquo states and libcloud
	 * states, so this method is created to have better compatibility. In
	 * libcloud, if the node is not running, then it does not exist (avoiding
	 * UNKNOWN and temporal states). In Abiquo, you can define a node, and
	 * then deploy it.
	 * If the node is in :class:`NodeState.TERMINATED` libcloud's state and in
	 * 'NOT_DEPLOYED' Abiquo state, there is a way to run and recover it
	 * for libcloud using this method. There is no way to reach this state
	 * if you are using only libcloud, but you may have used another Abiquo
	 * client and now you want to recover your node to be used by libcloud.
	 * @param node The node to run
	 * 			(:type node: :class:`Node`)
	 * @return The node itself, but with the new state
	 * 			(:rtype: :class:`Node`)
	**/
	public Node exRunNode(Node node);


	/**
	 * Populate the cache.
	 * For each connection, it is good to store some objects that will be
	 * useful for further requests, such as the 'user' and the 'enterprise'
	 * objects.
	 * Executes the 'login' resource after setting the connection parameters
	 * and, if the execution is successful, it sets the 'user' object into
	 * cache. After that, it also requests for the 'enterprise' and
	 * 'locations' data.
	 * List of locations should remain the same for a single libcloud
	 * connection. However, this method is public and you are able to
	 * refresh the list of locations any time.
	**/
	public void exPopulateCache();


	/**
	 * Create an empty group.
	 * You can specify the location as well.
	 * @param name name of the group (required)
	 * 			(:type name: ``str``)
	 * @param location location were to create the group
	 * 			(:type location: :class:`NodeLocation`)
	 * @return the created group
	 * 			(:rtype: :class:`NodeGroup`)
	**/
	public NodeGroup exCreateGroup(String name, NodeLocation location);

	/**
	 * Create an empty group.
	 * You can specify the location as well.
	 * @param name name of the group (required)
	 * 			(:type name: ``str``)
	 * @return the created group
	 * 			(:rtype: :class:`NodeGroup`)
	**/
	public NodeGroup exCreateGroup(String name);

	/**
	 * Destroy a group.
	 * Be careful! Destroying a group means destroying all the :class:`Node`s
	 * there and the group itself!
	 * If there is currently any action over any :class:`Node` of the
	 * :class:`NodeGroup`, then the method will raise an exception.
	 * @param group The group (required)
	 * 			(:type group: :class:`NodeGroup`)
	 * @return If the group was destroyed successfully
	 * 			(:rtype: ``bool``)
	**/
	public boolean exDestroyGroup(NodeGroup group);


	/**
	 * List all groups.
	 * @param location filter the groups by location (optional)
	 * 			(:type location: a :class:`NodeLocation` instance.)
	 * @return `NodeGroup`
	**/
	public List<NodeGroup> exListGroups(NodeLocation location);

	/**
	 * List all groups.
	 * @return `NodeGroup`
	**/
	public List<NodeGroup> exListGroups();

}