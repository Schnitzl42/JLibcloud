package org.askalon.jlibcloud.compute.examples.opennebula;

import java.util.List;

import org.askalon.jlibcloud.compute.core.ComputeContextBuilder;
import org.askalon.jlibcloud.compute.core.Providers;
import org.askalon.jlibcloud.compute.driverSpecific.opennebula.OpenNebulaNetwork;
import org.askalon.jlibcloud.compute.driverSpecific.opennebula.OpenNebulaNodeSize;
import org.askalon.jlibcloud.compute.driverSpecific.opennebula.OpenNebula_3_0_ComputeContext;
import org.askalon.jlibcloud.compute.examples.BasePrintInfo;
import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.KeyPair;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;

public class OpenNebulaPrintInfo {

	public static void main(String[] args) {
		ComputeContext cc = ComputeContextBuilder
				.newBuilder(Providers.OPENNEBULA.name)
				.credentials("xx", "﻿xx", false, "127.0.0.1", 4567).build();

		OpenNebula_3_0_ComputeContext oncc = (OpenNebula_3_0_ComputeContext) cc;
		
		System.out.println("images");
		List<? extends NodeImage> images = oncc.listImages();
		
		for (NodeImage image : images) {
			System.out.println(image);
		}
		
		BasePrintInfo.printBaseInfo(cc, false);

		// TODO: check return type of getCpu + getVcpu (py: type(arg))
		/*System.out.println("OpenNebula SIZES:");
		List<OpenNebulaNodeSize> sizes = (List<OpenNebulaNodeSize>) cc.listSizes();
		for (OpenNebulaNodeSize ns : sizes) {
			System.out.println(ns);
			System.out.println(ns.getCpu());
			System.out.println(ns.getVcpu());
		}
		System.out.println("====================================");

		System.out.println("KEYPAIRS:");
		List<KeyPair> kps = oncc.listKeyPairs();
		for (KeyPair kp : kps) {
			System.out.println(kp);
		}
		System.out.println("====================================");

		// provider specific
		System.out.println("NETWORKS:");
		List<OpenNebulaNetwork> nws = oncc.exListNetworks();
		for (OpenNebulaNetwork nw : nws) {
			System.out.println(nw);
		}
		System.out.println("====================================");
		*/
	}
}
