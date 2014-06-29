/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/abiquo.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/abiquo.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/abiquo.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.abiquo;

import java.util.List;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;

/**
 * Group of virtual machines that can be managed together
 * 
 * All :class:`Node`s in Abiquo must be defined inside a Virtual Appliance.
 * We offer a way to handle virtual appliances (called NodeGroup to
 * maintain some kind of name conventions here) inside the
 * :class:`AbiquoNodeDriver` without breaking compatibility of the rest of
 * libcloud API.
 * 
 * If the user does not want to handle groups, all the virtual machines
 * will be created inside a group named 'libcloud'
**/
public interface NodeGroup {

	public String getName();

	public List<Node> getNodes();

	public String getUri();

	/**
	 * Destroys the group delegating the execution to
	 * :class:`AbiquoNodeDriver`.
	**/
	public boolean destroy();
}