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

import java.util.List;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;
import java.util.Map;

public interface GCETargetPool {

	public String getId();

	public String getName();

	public String getRegion();

	public String getHealthchecks();

	public List<Node> getNodes();

	public Map<String,String> getExtra();

	/**
	 * Add a node to this target pool.
	 * @param node Node to add
	 * 			(:type node: ``str`` or :class:`Node`)
	 * @return True if successful
	 * 			(:rtype: ``bool``)
	**/
	public boolean addNode(String node);


	/**
	 * Remove a node from this target pool.
	 * @param node Node to remove
	 * 			(:type node: ``str`` or :class:`Node`)
	 * @return True if successful
	 * 			(:rtype: ``bool``)
	**/
	public boolean removeNode(String node);


	/**
	 * Add a healthcheck to this target pool.
	 * @param healthcheck Healthcheck to add
	 * 			(:type healthcheck: ``str`` or :class:`GCEHealthCheck`)
	 * @return True if successful
	 * 			(:rtype: ``bool``)
	**/
	public boolean addHealthcheck(String healthcheck);


	/**
	 * Remove a healthcheck from this target pool.
	 * @param healthcheck Healthcheck to remove
	 * 			(:type healthcheck: ``str`` or :class:`GCEHealthCheck`)
	 * @return True if successful
	 * 			(:rtype: ``bool``)
	**/
	public boolean removeHealthcheck(String healthcheck);


	/**
	 * Destroy this Target Pool
	 * @return True if successful
	 * 			(:rtype: ``bool``)
	**/
	public boolean destroy();


}