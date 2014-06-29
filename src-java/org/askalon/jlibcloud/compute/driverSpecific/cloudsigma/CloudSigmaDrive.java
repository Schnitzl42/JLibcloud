/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/cloudsigma.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/cloudsigma.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/cloudsigma.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.cloudsigma;

import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;

/**
 * Represents a CloudSigma drive.
**/
public interface CloudSigmaDrive extends NodeImage {

	public String getId();

	public String getName();

	public int getSize();

	public String getMedia();

	public String getStatus();

}