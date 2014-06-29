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

import java.util.List;

/**
 * Floating IP Pool info.
**/
public interface OpenStack_1_1_FloatingIpPool {

	public String getName();

	public String getConnection();

	/**
	 * List floating IPs in the pool
	 * @return `OpenStack_1_1_FloatingIpAddress`
	**/
	public List<OpenStack_1_1_FloatingIpAddress> listFloatingIps();
	/**
	 * Get specified floating IP from the pool
	 * @param ip floating IP to remove
	 * 			(:type ip: ``str``)
	 * @return `OpenStack_1_1_FloatingIpAddress`
	**/
	public OpenStack_1_1_FloatingIpAddress getFloatingIp(String ip);
	/**
	 * Create new floating IP in the pool
	 * @return `OpenStack_1_1_FloatingIpAddress`
	**/
	public OpenStack_1_1_FloatingIpAddress createFloatingIp();
	/**
	 * Delete specified floating IP from the pool
	 * @param ip floating IP to remove
	 * 			(:type ip::class:`OpenStack_1_1_FloatingIpAddress`)
	 * @return ``bool``
	**/
	public boolean deleteFloatingIp(OpenStack_1_1_FloatingIpAddress ip);
}