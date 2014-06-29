/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/cloudstack.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/cloudstack.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/cloudstack.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.cloudstack;

/**
 * A NAT/firewall forwarding rule.
**/
public interface CloudStackIPForwardingRule {

	public CloudStackNode getNode();

	public String getId();

	public CloudStackAddress getAddress();

	public String getProtocol();

	public int getStartPort();

	public int getEndPort();

	public boolean delete();

}