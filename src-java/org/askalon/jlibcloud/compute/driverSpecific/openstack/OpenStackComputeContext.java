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

import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.StorageVolume;


public interface OpenStackComputeContext extends ComputeContext {

	public StorageVolume exGetVolume(String volumeId);


	/**
	 * Lists details of the specified server.
	 * @param node_id ID of the node which should be used
	 * 			(:type node_id: ``str``)
	 * @return `Node`
	**/
	public Node exGetNodeDetails(String node_id);


	/**
	 * Soft reboots the specified server
	 * @param node node
	 * 			(:type node: :class:`Node`)
	 * @return ``bool``
	**/
	public boolean exSoftRebootNode(Node node);


	/**
	 * Hard reboots the specified server
	 * @param node node
	 * 			(:type node: :class:`Node`)
	 * @return ``bool``
	**/
	public boolean exHardRebootNode(Node node);


}