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


/**
 * Represents a CloudSigma firewall policy rule.
**/
public interface CloudSigmaFirewallPolicyRule {

	public String getAction();

	public String getDirection();

	public String getIpProto();

	public String getSrcIp();

	public String getSrcPort();

	public String getDstIp();

	public String getComment();

	public String getDstPort();

}