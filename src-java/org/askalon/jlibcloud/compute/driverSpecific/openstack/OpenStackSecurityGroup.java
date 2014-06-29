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
import java.util.Map;

/**
 * A Security Group.
**/
public interface OpenStackSecurityGroup {

	public String getId();

	public String getTenantId();

	public String getName();

	public String getDescription();

	public List<OpenStackSecurityGroupRule> getRules();

	public Map<String,String> getExtra();

}