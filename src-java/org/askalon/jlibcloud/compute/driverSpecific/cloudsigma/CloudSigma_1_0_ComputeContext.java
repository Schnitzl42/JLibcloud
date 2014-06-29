/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/cloudsigma.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/cloudsigma.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/cloudsigma.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.cloudsigma;

import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;

import java.util.List;

import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;

import java.util.Map;


public interface CloudSigma_1_0_ComputeContext extends ComputeContext {

	/**
	 * Destroy a node and all the drives associated with it.
	 * @param node Node which should be used
	 * 			(:type node: :class:`libcloud.compute.base.Node`)
	 * @return ``bool``
	**/
	public boolean exDestroyNodeAndDrives(Node node);


	/**
	 * Return a list of available static IP addresses.
	 * @return ``list`` of ``str``
	**/
	public List<String> exStaticIpList();


	/**
	 * Return a list of all the available drives.
	 * @return ``list`` of ``dict``
	**/
	public List<Map<String,String>> exDrivesList();


	/**
	 * Create a new static IP address.p
	 * @return ``list`` of ``dict``
	**/
	public List<Map<String,String>> exStaticIpCreate();


	/**
	 * Destroy a static IP address.
	 * @param ip_address IP address which should be used
	 * 			(:type ip_address: ``str``)
	 * @return ``bool``
	**/
	public boolean exStaticIpDestroy(String ip_address);


	/**
	 * Destroy a drive with a specified uuid.
	 * If the drive is currently mounted an exception is thrown.
	 * @param drive_uuid Drive uuid which should be used
	 * 			(:type drive_uuid: ``str``)
	 * @return ``bool``
	**/
	public boolean exDriveDestroy(String drive_uuid);


	/**
	 * Update a node configuration.
	 * Changing most of the parameters requires node to be stopped.
	 * @param node Node which should be used
	 * 			(:type node: :class:`libcloud.compute.base.Node`)
	 * @param kwargs keyword arguments
	 * 			(:type kwargs: ``dict``)
	 * @return ``bool``
	**/
	public boolean exSetNodeConfiguration(Node node, Map<String, Arg> kwargs);


	/**
	 * Start a node.
	 * @param node Node which should be used
	 * 			(:type node: :class:`libcloud.compute.base.Node`)
	 * @return ``bool``
	**/
	public boolean exStartNode(Node node);


	/**
	 * Stop (shutdown) a node.
	 * @param node Node which should be used
	 * 			(:type node: :class:`libcloud.compute.base.Node`)
	 * @return ``bool``
	**/
	public boolean exStopNode(Node node);


	/**
	 * Stop (shutdown) a node.
	 * inherits: :class:`CloudSigmaBaseNodeDriver.ex_stop_node`
	**/
	public String exShutdownNode(Node node);


	/**
	 * Destroy a drive.
	 * @param drive_uuid Drive uuid which should be used
	 * 			(:type drive_uuid: ``str``)
	 * @return ``bool``
	**/
	public boolean exDestroyDrive(String drive_uuid);


}