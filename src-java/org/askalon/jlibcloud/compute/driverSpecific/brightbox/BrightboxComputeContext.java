/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/brightbox.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/brightbox.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/brightbox.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.brightbox;

import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import java.util.List;
import java.util.Map;


public interface BrightboxComputeContext extends ComputeContext {

	/**
	 * List Cloud IPs
	 * note: This is an API extension for use on Brightbox
	 * @return ``list`` of ``dict``
	**/
	public List<Map<String,String>> exListCloudIps();


	/**
	 * Requests a new cloud IP address for the account
	 * note: This is an API extension for use on Brightbox
	 * @param reverse_dns Reverse DNS hostname
	 * 			(:type reverse_dns: ``str``)
	 * @return ``dict``
	**/
	public Map<String,String> exCreateCloudIp(String reverse_dns);

	/**
	 * Requests a new cloud IP address for the account
	 * note: This is an API extension for use on Brightbox
	 * @return ``dict``
	**/
	public Map<String,String> exCreateCloudIp();

	/**
	 * Update some details of the cloud IP address
	 * note: This is an API extension for use on Brightbox
	 * @param cloud_ip_id The id of the cloud ip.
	 * 			(:type cloud_ip_id: ``str``)
	 * @param reverse_dns Reverse DNS hostname
	 * 			(:type reverse_dns: ``str``)
	 * @return ``dict``
	**/
	public Map<String,String> exUpdateCloudIp(String cloud_ip_id, String reverse_dns);


	/**
	 * Maps (or points) a cloud IP address at a server's interface
	 * or a load balancer to allow them to respond to public requests
	 * note: This is an API extension for use on Brightbox
	 * @param cloud_ip_id The id of the cloud ip.
	 * 			(:type cloud_ip_id: ``str``)
	 * @param interface_id The Interface ID or LoadBalancer ID to
	 * 			which this Cloud IP should be mapped to
	 * 			(:type interface_id: ``str``)
	 * @return True if the mapping was successful.
	 * 			(:rtype: ``bool``)
	**/
	public boolean exMapCloudIp(String cloud_ip_id, String interface_id);


	/**
	 * Unmaps a cloud IP address from its current destination making
	 * it available to remap. This remains in the account's pool
	 * of addresses
	 * note: This is an API extension for use on Brightbox
	 * @param cloud_ip_id The id of the cloud ip.
	 * 			(:type cloud_ip_id: ``str``)
	 * @return True if the unmap was successful.
	 * 			(:rtype: ``bool``)
	**/
	public boolean exUnmapCloudIp(String cloud_ip_id);


	/**
	 * Release the cloud IP address from the account's ownership
	 * note: This is an API extension for use on Brightbox
	 * @param cloud_ip_id The id of the cloud ip.
	 * 			(:type cloud_ip_id: ``str``)
	 * @return True if the unmap was successful.
	 * 			(:rtype: ``bool``)
	**/
	public boolean exDestroyCloudIp(String cloud_ip_id);


}