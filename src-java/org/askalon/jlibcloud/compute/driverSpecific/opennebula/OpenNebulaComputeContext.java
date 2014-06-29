/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/opennebula.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/opennebula.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/opennebula.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.opennebula;

import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import java.util.List;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;


public interface OpenNebulaComputeContext extends ComputeContext {

	/**
	 * List virtual networks on a provider.
	 * @param location Location from which to request a list of virtual
	 * 			networks. (optional)
	 * 			(:type location: :class:`NodeLocation`)
	 * 			compute node.
	 * @return List of virtual networks available to be connected to a
	 * 			(:rtype: ``list`` of :class:`OpenNebulaNetwork`)
	**/
	public List<OpenNebulaNetwork> exListNetworks(NodeLocation location);


	/**
	 * List virtual networks on a provider.
	 * @return List of virtual networks available to be connected to a
	 * 			(:rtype: ``list`` of :class:`OpenNebulaNetwork`)
	**/
	public List<OpenNebulaNetwork> exListNetworks();

	/**
	 * Build action representation and instruct node to commit action.
	 * Build action representation from the compute node ID, and the
	 * action which should be carried out on that compute node. Then
	 * instruct the node to carry out that action.
	 * @param node Compute node instance.
	 * 			(:type node: :class:`Node`)
	 * @param action Action to be carried out on the compute node.
	 * 			(:type action: ``str``)
	 * 			returned.
	 * @return False if an HTTP Bad Request is received, else, True is
	 * 			(:rtype: ``bool``)
	**/
	public boolean exNodeAction(Node node, String action);



}