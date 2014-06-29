/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/elasticstack.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/elasticstack.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/elasticstack.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.elasticstack;

import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;
import java.util.Map;


public interface ElasticStackBaseComputeContext extends ComputeContext {

	/**
	 * Changes the configuration of the running server
	 * @param node Node which should be used
	 * 			(:type node: :class:`Node`)
	 * @param kwargs keyword arguments
	 * 			(:type kwargs: ``dict``)
	 * @return ``bool``
	**/
	public boolean exSetNodeConfiguration(Node node, Map<String, Arg> kwargs);



	/**
	 * Sends the ACPI power-down event
	 * @param node Node which should be used
	 * 			(:type node: :class:`Node`)
	 * @return ``bool``
	**/
	public boolean exShutdownNode(Node node);



	/**
	 * Deletes a drive
	 * @param drive_uuid Drive uuid which should be used
	 * 			(:type drive_uuid: ``str``)
	 * @return ``bool``
	**/
	public boolean exDestroyDrive(String drive_uuid);



}