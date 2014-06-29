/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/libvirt_driver.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/libvirt_driver.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/libvirt_driver.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.libvirt_driver;

import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;
import java.util.Map;


public interface LibvirtComputeContext extends ComputeContext {

	/**
	 * Start a stopped node.
	 * @param node Node which should be used
	 * 			(:type node: :class:`Node`)
	 * @return ``bool``
	**/
	public boolean exStartNode(Node node);



	/**
	 * Shutdown a running node.
	 * Note: Usually this will result in sending an ACPI event to the node.
	 * @param node Node which should be used
	 * 			(:type node: :class:`Node`)
	 * @return ``bool``
	**/
	public boolean exShutdownNode(Node node);



	/**
	 * Suspend a running node.
	 * @param node Node which should be used
	 * 			(:type node: :class:`Node`)
	 * @return ``bool``
	**/
	public boolean exSuspendNode(Node node);



	/**
	 * Resume a suspended node.
	 * @param node Node which should be used
	 * 			(:type node: :class:`Node`)
	 * @return ``bool``
	**/
	public boolean exResumeNode(Node node);



	/**
	 * Take a screenshot of a monitoring of a running instance.
	 * @param node Node to take the screenshot of.
	 * 			(:type node: :class:`libcloud.compute.base.Node`)
	 * @param directory Path where the screenshot will be saved.
	 * 			(:type directory: ``str``)
	 * @param screen ID of the monitor to take the screenshot of.
	 * 			(:type screen: ``int``)
	 * @return Full path where the screenshot has been saved.
	 * 			(:rtype: ``str``)
	**/
	public String exTakeNodeScreenshot(Node node, String directory, int screen);


	/**
	 * Take a screenshot of a monitoring of a running instance.
	 * @param node Node to take the screenshot of.
	 * 			(:type node: :class:`libcloud.compute.base.Node`)
	 * @param directory Path where the screenshot will be saved.
	 * 			(:type directory: ``str``)
	 * @return Full path where the screenshot has been saved.
	 * 			(:rtype: ``str``)
	**/
	public String exTakeNodeScreenshot(Node node, String directory);

	/**
	 * Return a system hostname on which the hypervisor is running.
	**/
	public String exGetHypervisorHostname();



	/**
	 * Retrieve hypervisor system information.
	 * @return ``dict``
	**/
	public Map<String,String> exGetHypervisorSysinfo();



}