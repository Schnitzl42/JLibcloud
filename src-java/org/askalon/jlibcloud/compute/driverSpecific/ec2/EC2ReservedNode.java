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

import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;

/**
 * Class which stores information about EC2 reserved instances/nodes
 * Inherits from Node and passes in None for name and private/public IPs
 * 
 * Note: This class is EC2 specific.
**/
public interface EC2ReservedNode extends Node {

}