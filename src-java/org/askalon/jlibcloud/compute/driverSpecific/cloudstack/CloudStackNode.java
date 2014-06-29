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

import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;

/**
 * Subclass of Node so we can expose our extension methods.
**/
public interface CloudStackNode extends Node {

	/**
	 * Allocate a public IP and bind it to this node.
	**/
	public CloudStackAddress exAllocatePublicIp();

	/**
	 * Release a public IP that this node holds.
	**/
	public boolean exReleasePublicIp(CloudStackAddress address);

	/**
	 * Add a NAT/firewall forwarding rule for a port or ports.
	**/
	public CloudStackIPForwardingRule exCreateIpForwardingRule(CloudStackAddress address, String protocol, int start_port, int end_port);

	/**
	 * Add a NAT/firewall forwarding rule for a port or ports.
	**/
	public CloudStackIPForwardingRule exCreateIpForwardingRule(CloudStackAddress address, String protocol, int start_port);
	/**
	 * Add a port forwarding rule for port or ports.
	**/
	public CloudStackPortForwardingRule exCreatePortForwardingRule(CloudStackAddress address, int private_port, int public_port, String protocol, String public_end_port, 
			String private_end_port, String openfirewall);


	/**
	 * Add a port forwarding rule for port or ports.
	**/
	public CloudStackPortForwardingRule exCreatePortForwardingRule(CloudStackAddress address, String private_port, String public_port, String protocol);
	/**
	 * Delete a port forwarding rule.
	**/
	public boolean exDeleteIpForwardingRule(CloudStackIPForwardingRule rule);

	/**
	 * Delete a NAT/firewall rule.
	**/
	public boolean exDeletePortForwardingRule(CloudStackPortForwardingRule rule);

	/**
	 * Starts a stopped virtual machine.
	**/
	public String exStart();

	/**
	 * Stops a running virtual machine.
	**/
	public String exStop();

}