package org.askalon.jlibcloud.compute.examples.rackspace;

import java.util.List;

import org.askalon.jlibcloud.compute.core.ComputeContextBuilder;
import org.askalon.jlibcloud.compute.core.Providers;
import org.askalon.jlibcloud.compute.driverSpecific.openstack.OpenStackNodeSize;
import org.askalon.jlibcloud.compute.examples.BasePrintInfo;
import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;

public class PrintInfo {

	public static void main(String[] args) throws Exception {

		ComputeContext cc = ComputeContextBuilder
				.newBuilder(Providers.RACKSPACE.name).region("lon")
				.credentialsFromFile("/home/marcus/BAK/secrets.py").build();

		//OpenStack_1_1_ComputeContext rscc = (OpenStack_1_1_ComputeContext) cc;

		BasePrintInfo.printBaseInfo(cc, false);
		
		System.out.println("\nOPENSTACK_SIZES:");
		for (OpenStackNodeSize ns : (List<OpenStackNodeSize>) cc.listSizes()) {
			System.out.println(ns);
			System.out.println(ns.getVcpus());
			System.out.println("====================================");
		}
	}
}
