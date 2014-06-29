/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/opennebula.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/opennebula.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/opennebula.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.opennebula;

import java.util.Map;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;

/**
 * Provide a common interface for handling networks of all types.
 * 
 * Network objects are analogous to physical switches connecting two or
 * more physical nodes together. The Network object provides the interface in
 * libcloud through which we can manipulate networks in different cloud
 * providers in the same way. Network objects don't actually do much directly
 * themselves, instead the network driver handles the connection to the
 * network.
 * 
 * You don't normally create a network object yourself; instead you use
 * a driver and then have that create the network for you.
 * 
 * >>> from libcloud.compute.drivers.dummy import DummyNodeDriver
 * >>> driver = DummyNodeDriver()
 * >>> network = driver.create_network()
 * >>> network = driver.list_networks()[0]
 * >>> network.name
 * 'dummy-1'
**/
public interface OpenNebulaNetwork {

	public String getId();

	public String getName();

	public String getAddress();

	public NodeSize getSize();

	public Map<String,String> getExtra();

	/**
	 * Unique hash for this network.
	 * The hash is a function of an SHA1 hash of the network's ID and
	 * its driver which means that it should be unique between all
	 * networks. In some subclasses (e.g. GoGrid) there is no ID
	 * available so the public IP address is used. This means that,
	 * unlike a properly done system UUID, the same UUID may mean a
	 * different system install at a different time
	 * >>> from libcloud.network.drivers.dummy import DummyNetworkDriver
	 * >>> driver = DummyNetworkDriver()
	 * >>> network = driver.create_network()
	 * >>> network.get_uuid()
	 * 'd3748461511d8b9b0e0bfa0d4d3383a619a2bb9f'
	 * Note, for example, that this example will always produce the
	 * same UUID!
	 * @return Unique identifier for this instance.
	**/
	public String getUuid();


}