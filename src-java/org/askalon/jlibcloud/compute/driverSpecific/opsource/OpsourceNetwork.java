/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/opsource.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/opsource.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/opsource.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.opsource;

import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;

/**
 * Opsource network with location.
**/
public interface OpsourceNetwork {

	public String getId();

	public String getName();

	public String getDescription();

	public NodeLocation getLocation();

	public String getPrivateNet();

	public String getMulticast();

	public String getStatus();

}