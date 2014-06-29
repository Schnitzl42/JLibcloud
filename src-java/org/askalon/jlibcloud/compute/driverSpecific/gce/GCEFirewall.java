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

public interface GCEFirewall {

	public String getId();

	public String getName();

	public String getNetwork();

	public String getAllowed();

	public String getSourceRanges();

	public String getSourceTags();

	public Map<String,String> getExtra();

	/**
	 * Destroy this firewall.
	 * @return True if successful
	 * 			(:rtype: ``bool``)
	**/
	public boolean destroy();


	/**
	 * Commit updated firewall values.
	 * @return Updated Firewall object
	 * 			(:rtype: :class:`GCEFirewall`)
	**/
	public GCEFirewall update();


}