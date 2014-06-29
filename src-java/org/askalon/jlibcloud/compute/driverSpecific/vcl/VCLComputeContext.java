/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/vcl.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/vcl.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/vcl.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.vcl;

import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;


public interface VCLComputeContext extends ComputeContext {

	/**
	 * Update the remote ip accessing the node.
	 * @param node the reservation node to update
	 * 			(:type node: :class:`Node`)
	 * @param ipaddr the ipaddr used to access the node
	 * 			(:type ipaddr: ``str``)
	 * @return node with updated information
	 * 			(:rtype: :class:`Node`)
	**/
	public Node exUpdateNodeAccess(Node node, String ipaddr);



	/**
	 * Time in minutes to extend the requested node's reservation time
	 * @param node the reservation node to update
	 * 			(:type node: :class:`Node`)
	 * @param minutes the number of mintes to update
	 * 			(:type minutes: ``str``)
	 * @return true on success, throws error on failure
	 * 			(:rtype: ``bool``)
	**/
	public boolean exExtendRequestTime(Node node, String minutes);



	/**
	 * Get the ending time of the node reservation.
	 * @param node the reservation node to update
	 * 			(:type node: :class:`Node`)
	 * @return unix timestamp
	 * 			(:rtype: ``int``)
	**/
	public int exGetRequestEndTime(Node node);



}