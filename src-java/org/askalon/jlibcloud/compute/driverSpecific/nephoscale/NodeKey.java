/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/nephoscale.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/nephoscale.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/nephoscale.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.nephoscale;


public interface NodeKey {

	public String getId();

	public String getName();

	public String getKeyGroup();

	public String getPassword();

	public String getPublicKey();

}