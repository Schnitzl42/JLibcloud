/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/vcloud.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/vcloud.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/vcloud.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.vcloud;

import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import java.util.List;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;


public interface VCloudComputeContext extends ComputeContext {

	/**
	 * List all nodes across all vDCs. Using 'vdcs' you can specify which vDCs
	 * should be queried.
	 * @param vdcs None, vDC or a list of vDCs to query. If None all vDCs
	 * 			will be queried.
	 * 			(:type vdcs: :class:`Vdc`)
	 * @return `Node`
	**/
	public List<Node> exListNodes(Vdc vdcs);


	/**
	 * List all nodes across all vDCs. Using 'vdcs' you can specify which vDCs
	 * should be queried.
	 * @return `Node`
	**/
	public List<Node> exListNodes();

}