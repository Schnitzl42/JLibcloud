/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/joyent.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/joyent.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/joyent.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.joyent;

import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;


public interface JoyentComputeContext extends ComputeContext {

	/**
	 * Stop node
	 * @param node The node to be stopped
	 * 			(:type node: :class:`Node`)
	 * @return ``bool``
	**/
	public boolean exStopNode(Node node);



	/**
	 * Start node
	 * @param node The node to be stopped
	 * 			(:type node: :class:`Node`)
	 * @return ``bool``
	**/
	public boolean exStartNode(Node node);



}