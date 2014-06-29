/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/gandi.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/gandi.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/gandi.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.gandi;

import java.util.Map;

/**
 * Provide a common interface for ip addresses
**/
public interface IPAddress extends BaseObject{

	public String getInet();

	public String getVersion();

	public Map<String,String> getExtra();

}