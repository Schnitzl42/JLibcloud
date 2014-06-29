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

import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;


public interface OpenNebula_3_0_ComputeContext extends OpenNebula_2_0_ComputeContext {

	/**
	 * Build action representation and instruct node to commit action.
	 * Build action representation from the compute node ID, the disk image
	 * which will be saved, and the name under which the image will be saved
	 * upon shutting down the compute node.
	 * @param node Compute node instance.
	 * 			(:type node: :class:`Node`)
	 * @param name Name under which the image should be saved after shutting
	 * 			down the compute node.
	 * 			(:type name: ``str``)
	 * 			returned.
	 * @return False if an HTTP Bad Request is received, else, True is
	 * 			(:rtype: ``bool``)
	**/
	public boolean exNodeSetSaveName(Node node, String name);



}