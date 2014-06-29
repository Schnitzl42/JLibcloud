package org.askalon.jlibcloud.compute.examples.ec2;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.print.DocFlavor.URL;

import org.askalon.jlibcloud.compute.driverSpecific.ec2Ex.EC2NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;
import org.askalon.jlibcloud.compute.wrapperInterfacesImpl.deploymentImpl.FileDeploymentImpl;
import org.askalon.jlibcloud.compute.core.ComputeContextBuilder;
import org.askalon.jlibcloud.compute.core.Providers;

public class EC2MinimalisticCreate {

	public static void main(String[] args) throws URISyntaxException {


		ComputeContext cc = ComputeContextBuilder
				.newBuilder(Providers.EC2.name)
				.credentialsFromFile("/home/marcus/BAK/secrets.py")
				//.nodePropertiesFrom(
				//		"examples/ec2/std.properties")
				.build();
		
		cc.setNodeProperties("/home/marcus/workspace/JLibcloud0_1_1/src-java/org/askalon/jlibcloud/compute/examples/ec2/std.properties");

		System.out.println("creating node...");
		NodeTemplate nt = cc.getTemplateBuilder().build();
		List<? extends Node> nodeList = cc.createNode(nt);

		
		System.out.println("node created! waiting...");
		boolean wait = cc.waitUntilRunning((List<Node>) nodeList);
		if (wait) {
			System.out.println("node is runing!");
		} else {
			System.err.println("Timeout!");
		}
		
		for(Node n : cc.listNodes()){
			if(n.getID().equals(nodeList.iterator().next().getID())){
				System.out.println(n.getPublicIP());
				System.out.println(n);
			}
		}
	}
}
