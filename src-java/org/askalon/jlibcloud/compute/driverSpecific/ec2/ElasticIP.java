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
 * Represents information about an elastic IP adddress
 * 
 * :param ip: The elastic IP address
 * :type ip: ``str``
 * 
 * :param domain: The domain that the IP resides in (EC2-Classic/VPC).
 * EC2 classic is represented with standard and VPC
 * is represented with vpc.
 * :type domain: ``str``
 * 
 * :param instance_id: The identifier of the instance which currently
 * has the IP associated.
 * :type instance_id: ``str``
 * 
 * Note: This class is used to support both EC2 and VPC IPs.
 * For VPC specific attributes are stored in the extra
 * dict to make promotion to the base API easier.
**/
public interface ElasticIP {

	public String getIp();

	public String getDomain();

	public String getInstanceId();

	public Map<String,String> getExtra();

}