/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/ec2.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/ec2.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/ec2.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.ec2;

import java.util.Map;

/**
 * Represents information about a VPC (Virtual Private Cloud) subnet
 * 
 * Note: This class is EC2 specific.
**/
public interface EC2NetworkSubnet {

	public String getId();

	public String getName();

	public String getState();

	public Map<String,String> getExtra();

}