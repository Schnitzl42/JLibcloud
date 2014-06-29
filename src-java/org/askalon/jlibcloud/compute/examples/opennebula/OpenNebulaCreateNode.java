package org.askalon.jlibcloud.compute.examples.opennebula;

import java.util.List;

import org.askalon.jlibcloud.compute.core.ComputeContextBuilder;
import org.askalon.jlibcloud.compute.core.Providers;
import org.askalon.jlibcloud.compute.driverSpecific.opennebula.OpenNebula_3_0_ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;

public class OpenNebulaCreateNode {

	public static void main(String[] args) {
		ComputeContext cc = ComputeContextBuilder
				.newBuilder(Providers.OPENNEBULA.name)
				.credentials("user", "﻿password", false, "host", 4567).build();

		OpenNebula_3_0_ComputeContext oncc = (OpenNebula_3_0_ComputeContext) cc;

		NodeTemplate onnt = cc.getTemplateBuilder()
				.imageId("imgId").sizeId("sizeId").nodeName("name").build();

		System.out.println("creating node...");
		cc.createNode(onnt);

		System.out.println("node created! waiting....");
		boolean wait = cc.waitUntilRunning((List<Node>) cc.listNodes());
		if (wait) {
			System.out.println("node is runing!");
		} else {
			System.err.println("Timeout!");
		}

		List<Node> nodes = (List<Node>) cc.listNodes();
		for (Node n : nodes) {
			System.out.println(n);
		}
	}
}
