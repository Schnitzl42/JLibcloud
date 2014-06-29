/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/cloudframes.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/cloudframes.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/cloudframes.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.cloudframes;

import java.util.List;

import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;


public interface CloudFramesNode extends Node {

	public List<CloudFramesSnapshot> listSnapshots();
	public CloudFramesSnapshot snapshot(String label, String description);
	public CloudFramesSnapshot snapshot();
	public boolean rollback(String snapshot);
}