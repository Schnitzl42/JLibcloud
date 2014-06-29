package org.askalon.jlibcloud.compute.driverSpecific.ec2Ex;

import org.askalon.jlibcloud.compute.driverSpecific.ec2.BaseEC2NodeTemplate;

public interface EC2NodeTemplate extends BaseEC2NodeTemplate {

	/**
	 * 
	 * @return the spot price for this instance
	 */
	public Float getExSpotPrice();

	/**
	 * 
	 * @return spotInstances the number of spot instances to create
	 */
	public Integer getExSpotInstances();

}