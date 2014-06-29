/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/opsource.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/opsource.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/opsource.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.opsource;


/**
 * Opsource API pending operation status class
 * action, requestTime, username, numberOfSteps, updateTime,
 * step.name, step.number, step.percentComplete, failureReason,
**/
public interface OpsourceStatus {

	public String getAction();

	public String getRequestTime();

	public String getUserName();

	public String getNumberOfSteps();

	public String getUpdateTime();

	public String getStepName();

	public String getStepNumber();

	public String getStepPercentComplete();

	public String getFailureReason();

}