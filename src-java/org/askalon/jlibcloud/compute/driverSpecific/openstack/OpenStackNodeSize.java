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

import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;

/**
 * NodeSize class for the OpenStack.org driver.
 * 
 * Following the example of OpenNebula.org driver
 * and following guidelines:
 * https://issues.apache.org/jira/browse/LIBCLOUD-119
**/
public interface OpenStackNodeSize extends NodeSize {

	public int getVcpus();

}