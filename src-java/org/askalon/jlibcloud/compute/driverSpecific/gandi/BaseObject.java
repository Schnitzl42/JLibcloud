/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/gandi.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/gandi.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/gandi.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.gandi;


public interface BaseObject {

	public String getId();

	public String getState();

	/**
	 * The hash is a function of an SHA1 hash of prefix, the object's ID and
	 * its driver which means that it should be unique between all
	 * interfaces.
	 * TODO : to review
	 * >>> from libcloud.compute.drivers.dummy import DummyNodeDriver
	 * >>> driver = DummyNodeDriver(0)
	 * >>> vif = driver.create_interface()
	 * >>> vif.get_uuid()
	 * 'd3748461511d8b9b0e0bfa0d4d3383a619a2bb9f'
	 * Note, for example, that this example will always produce the
	 * same UUID!
	 * @return ``str``
	**/
	public String getUuid();


}