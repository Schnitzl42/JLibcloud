/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/digitalocean.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/digitalocean.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/digitalocean.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.digitalocean;

import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import java.util.List;


public interface DigitalOceanComputeContext extends ComputeContext {

	/**
	 * List all the available SSH keys.
	 * @return Available SSH keys.
	 * 			(:rtype: ``list`` of :class:`SSHKey`)
	**/
	public List<SSHKey> exListSshKeys();


	/**
	 * Create a new SSH key.
	 * @param name Valid public key string (required)
	**/
	public SSHKey exCreateSshKey(String name, String ssh_key_pub);


	/**
	 * Delete an existing SSH key.
	 * @param key_id SSH key id (required)
	**/
	public boolean exDestroySshKey(String key_id);


}