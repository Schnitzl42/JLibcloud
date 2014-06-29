package org.askalon.jlibcloud.compute.examples.ec2;

import java.util.List;
import java.util.Map;

import org.askalon.jlibcloud.compute.core.ComputeContextBuilder;
import org.askalon.jlibcloud.compute.core.Providers;
import org.askalon.jlibcloud.compute.driverSpecific.ec2.ExEC2AvailabilityZone;
import org.askalon.jlibcloud.compute.driverSpecific.ec2Ex.EC2ComputeContext;
import org.askalon.jlibcloud.compute.examples.BasePrintInfo;
import org.askalon.jlibcloud.compute.types.NodeState;
import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;

public class PrintInfo {

	public static void main(String[] args) throws Exception {

		ComputeContext cc = ComputeContextBuilder
				.newBuilder(Providers.EC2.name)
				.credentialsFromFile("/home/marcus/BAK/secrets.py").build();

		EC2ComputeContext ec2cc = (EC2ComputeContext) cc;

		System.out.println(cc.listNodesMatching((List<Node>) cc.listNodes()));

		/*
		 * BasePrintInfo.printBaseInfo(cc, false);
		 * 
		 * System.out.println("\nAVAILABILITY_ZONES:");
		 * List<ExEC2AvailabilityZone> zones = ec2cc.exListAvailabilityZones();
		 * for(ExEC2AvailabilityZone zone : zones){ System.out.println(zone); }
		 */
	}
}
