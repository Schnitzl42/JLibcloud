/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/vcloud.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/vcloud.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/vcloud.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.vcloud;


/**
 * Represents CPU, Memory or Storage capacity of vDC.
**/
public interface Capacity {

	public String getLimit();

	public String getUsed();

	public String getUnits();

}