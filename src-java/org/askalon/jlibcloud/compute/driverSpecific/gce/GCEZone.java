/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/gce.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/gce.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/gce.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.gce;

import java.util.Map;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;

public interface GCEZone extends NodeLocation {

	public String getStatus();

	public String getMaintenanceWindows();

	public String getDeprecated();

	public Map<String,String> getExtra();

	/**
	 * Returns the time until the next Maintenance Window as a
	 * datetime.timedelta object.
	**/
	public String timeUntilMw();


	/**
	 * Returns the duration of the next Maintenance Window as a
	 * datetime.timedelta object.
	**/
	public String nextMwDuration();


}