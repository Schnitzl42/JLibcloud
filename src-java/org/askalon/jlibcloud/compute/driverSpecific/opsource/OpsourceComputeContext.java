/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/opsource.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/opsource.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/opsource.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.opsource;

import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import java.util.List;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;


public interface OpsourceComputeContext extends ComputeContext {

	/**
	 * Powers on an existing deployed server
	 * @param node Node which should be used
	 * 			(:type node: :class:`Node`)
	 * @return ``bool``
	**/
	public boolean exStartNode(Node node);



	/**
	 * This function will attempt to "gracefully" stop a server by
	 * initiating a shutdown sequence within the guest operating system.
	 * A successful response on this function means the system has
	 * successfully passed the request into the operating system.
	 * @param node Node which should be used
	 * 			(:type node: :class:`Node`)
	 * @return ``bool``
	**/
	public boolean exShutdownGraceful(Node node);



	/**
	 * This function will abruptly power-off a server. Unlike
	 * ex_shutdown_graceful, success ensures the node will stop but some OS
	 * and application configurations may be adversely affected by the
	 * equivalent of pulling the power plug out of the machine.
	 * @param node Node which should be used
	 * 			(:type node: :class:`Node`)
	 * @return ``bool``
	**/
	public boolean exPowerOff(Node node);



	/**
	 * List networks deployed across all data center locations for your
	 * organization. The response includes the location of each network.
	 * @return a list of OpsourceNetwork objects
	 * 			(:rtype: ``list`` of :class:`OpsourceNetwork`)
	**/
	public List<OpsourceNetwork> exListNetworks();



	/**
	 * Get location by ID.
	 * @param id ID of the node location which should be used
	 * 			(:type id: ``str``)
	 * @return `NodeLocation`
	**/
	public NodeLocation exGetLocationById(String id);



}