/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/ibm_sce.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/ibm_sce.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/ibm_sce.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.ibm_sce;


/**
 * A reserved IP address that can be attached to an instance.
 * Properties: id, ip, state, options(location, type, created_time, state,
 * hostname, instance_ids, vlan, owner,
 * mode, offering_id)
**/
public interface Address {

	public String getId();

	public String getIp();

	public String getState();

	public String getOptions();

}