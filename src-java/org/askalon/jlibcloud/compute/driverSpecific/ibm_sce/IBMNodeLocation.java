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

import java.util.Map;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;

/**
 * Extends the base LibCloud NodeLocation to contain additional attributes
**/
public interface IBMNodeLocation extends NodeLocation {

	public String getId();

	public String getName();

	public String getCountry();

	public Map<String,String> getExtra();

}