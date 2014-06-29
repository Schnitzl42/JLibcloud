package org.askalon.jlibcloud.compute.examples.openstack;
import java.util.LinkedList;
import java.util.List;

import org.askalon.jlibcloud.compute.driverSpecific.openstack.OpenStackSecurityGroup;
import org.askalon.jlibcloud.compute.driverSpecific.openstack.OpenStack_1_1_ComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.openstack.OpenStack_1_1_NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;
import org.askalon.jlibcloud.compute.core.ComputeContextBuilder;
import org.askalon.jlibcloud.compute.core.Providers;

public class OpenStackCreateNode {

	public static void main(String[] args) {
		ComputeContext cc = ComputeContextBuilder
				.newBuilder(Providers.OPENSTACK.name)
				.credentials("user", "﻿password", false, "host", 9696,
						"base-url", "auth-url", "2.0_password", "default")
				.build();

		OpenStack_1_1_ComputeContext oscc = (OpenStack_1_1_ComputeContext) cc;

		// create a new security group
		OpenStackSecurityGroup ossg = oscc.exCreateSecurityGroup("newGroup",
				"for testing purpose");
		List<OpenStackSecurityGroup> groups = new LinkedList<OpenStackSecurityGroup>();
		groups.add(ossg);

		// create new key pair
		String keyPairName = "newKeyPair";
		oscc.createKeyPair(keyPairName);

		NodeTemplate onnt = cc.getTemplateBuilder().keyPair(keyPairName)
				.imageId("imgId").nodeName("testNode").sizeId("sizeId")
				.as(OpenStack_1_1_NodeTemplateBuilder.class)
				.exSecurityGroups(groups).build();

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
