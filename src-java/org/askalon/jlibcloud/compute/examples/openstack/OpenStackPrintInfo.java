package org.askalon.jlibcloud.compute.examples.openstack;
import java.util.List;
import java.util.Map;

import org.askalon.jlibcloud.compute.driverSpecific.openstack.OpenStackKeyPair;
import org.askalon.jlibcloud.compute.driverSpecific.openstack.OpenStackNetwork;
import org.askalon.jlibcloud.compute.driverSpecific.openstack.OpenStackNodeSize;
import org.askalon.jlibcloud.compute.driverSpecific.openstack.OpenStackSecurityGroup;
import org.askalon.jlibcloud.compute.driverSpecific.openstack.OpenStack_1_1_ComputeContext;
import org.askalon.jlibcloud.compute.examples.BasePrintInfo;
import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.core.ComputeContextBuilder;
import org.askalon.jlibcloud.compute.core.Providers;

public class OpenStackPrintInfo {
	public static void main(String[] args) {
		ComputeContext cc = ComputeContextBuilder
				.newBuilder(Providers.OPENSTACK.name)
				.credentials("user", "﻿password", false, "host", 9696,
						"base-url", "auth-url", "2.0_password", "default")
				.build();

		OpenStack_1_1_ComputeContext oscc = (OpenStack_1_1_ComputeContext) cc;


		BasePrintInfo.printBaseInfo(cc, false);
		
		System.out.println("KEYPAIRS:");
		List<OpenStackKeyPair> kps = oscc.exListKeypairs();
		for (OpenStackKeyPair kp : kps) {
			System.out.println(kp);
		}
		System.out.println("====================================");

		System.out.println("OPENSTACK_SIZES:");
		// TODO: check return type of getCpu + getVcpu (py: type(arg))
		for (OpenStackNodeSize ns : (List<OpenStackNodeSize>) cc
				.listSizes()) {
			System.out.println(ns);
		}
		System.out.println("====================================");
		
		// provider specific
		System.out.println("NETWORKS:");
		List<OpenStackNetwork> nws = oscc.exListNetworks();
		for (OpenStackNetwork nw : nws) {
			System.out.println(nw);
		}
		System.out.println("====================================");

		System.out.println("SECURITY GROUPS:");
		List<OpenStackSecurityGroup> ossgs = oscc.exListSecurityGroups();
		for (OpenStackSecurityGroup ossg : ossgs) {
			System.out.println(ossg);
		}
		System.out.println("====================================");
	}
}
