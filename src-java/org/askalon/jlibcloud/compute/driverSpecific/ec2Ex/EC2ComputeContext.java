package org.askalon.jlibcloud.compute.driverSpecific.ec2Ex;

import org.askalon.jlibcloud.compute.driverSpecific.ec2.BaseEC2ComputeContext;


public interface EC2ComputeContext extends BaseEC2ComputeContext {
	
	public boolean exWaitForPendingSpotNodes();
	
	public boolean  exWaitForPendingSpotNodes(Integer waitPeriodSeconds, Integer timeoutSeconds);
	
}
