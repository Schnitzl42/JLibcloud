/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/nephoscale.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/nephoscale.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/nephoscale.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.nephoscale;

import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import java.util.List;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;


public interface NephoscaleComputeContext extends ComputeContext {

	/**
	start a stopped node
	**/
	public String exStartNode(Node node);



	/**
	stop a running node
	**/
	public String exStopNode(Node node);



	/**
	 * List available console and server keys
	 * There are two types of keys for NephoScale, ssh and password keys.
	 * If run without arguments, lists all keys. Otherwise list only
	 * ssh keys, or only password keys.
	 * Password keys with key_group 4 are console keys. When a server
	 * is created, it has two keys, one password or ssh key, and
	 * one password console key.
	 * @param ssh if specified, show ssh keys only (optional)
	 * 			(:type ssh: ``bool``)
	 * @param password if specified, show password keys only (optional)
	 * 			(:type password: ``bool``)
	 * @param key_group if specified, show keys with this key_group only
	 * 			eg key_group=4 for console password keys (optional)
	 * 			(:type key_group: ``int``)
	 * @return `NodeKey`
	**/
	public List<NodeKey> exListKeypairs(boolean ssh, boolean password, int key_group);


	/**
	 * List available console and server keys
	 * There are two types of keys for NephoScale, ssh and password keys.
	 * If run without arguments, lists all keys. Otherwise list only
	 * ssh keys, or only password keys.
	 * Password keys with key_group 4 are console keys. When a server
	 * is created, it has two keys, one password or ssh key, and
	 * one password console key.
	 * @return `NodeKey`
	**/
	public List<NodeKey> exListKeypairs();

	/**
	 * The group for the key (key_group) is 1 for Server and 4 for Console
	 * Returns the id of the created key
	**/
	public String exCreateKeypair(String name, String public_key, String password, String key_group);


	/**
	 * The group for the key (key_group) is 1 for Server and 4 for Console
	 * Returns the id of the created key
	**/
	public String exCreateKeypair(String name);

	/**
	**/
	public String exDeleteKeypair(String key_id, boolean ssh);


	/**
	**/
	public String exDeleteKeypair(String key_id);

}