package org.askalon.jlibcloud.compute.examples.ec2_rackspace;

import java.util.LinkedList;
import java.util.List;

import org.askalon.jlibcloud.compute.core.ComputeContextBuilder;
import org.askalon.jlibcloud.compute.core.Providers;
import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;

/**
 * Sample program to launch instances from different providers in the same
 * fashion.
 */
public class MultipleProviders {

	public static void main(String[] args) {

		// Instantiate EC2 Context
		ComputeContext ec2Context = ComputeContextBuilder
				.newBuilder(Providers.EC2.name)
				.region(Providers.EC2.regions[0])
				.credentialsFromFile("/home/marcus/BAK/secrets.py")
				.nodePropertiesFrom("/home/marcus/BAK/ec2.properties").build();
		System.out.println("connected to EC2");

		// Instantiate Rackspace Context
		ComputeContext rackspContext = ComputeContextBuilder
				.newBuilder(Providers.RACKSPACE.name).region("lon")
				.credentialsFromFile("/home/marcus/BAK/secrets.py")
				.nodePropertiesFrom("/home/marcus/BAK/rackspace.properties")
				.build();
		System.out.println("connected to Rackspace");

		List<ComputeContext> contexts = new LinkedList<ComputeContext>();
		contexts.add(rackspContext);
		contexts.add(ec2Context);

		// List for all new Nodes
		List<Node> nodes = new LinkedList<Node>();

		// Launch Instances configured with the properties files
		for (ComputeContext cc : contexts) {
			System.out.println("-----------");
			//create
			System.out.println("creating node...");
			List<? extends Node> newNode = cc.createNode(cc
					.getTemplateBuilder().build());
			//wait
			System.out.println("waiting...");
			boolean noTimeout = cc.waitUntilRunning((List<Node>) newNode);
			if (noTimeout) {
				System.out.println("Node is running!");
			} else {
				System.err.println("Timeout exceeded!");
			}
			//update node status and add to node list
			nodes.addAll(cc.listNodesMatching((List<Node>) newNode));
		}
		
		// List Nodes
		for (Node node : nodes) {
			System.out.println("-----------");
			System.out.println("Id:\t\t"+node.getID());
			System.out.println("State:\t\t"+node.getState());
			System.out.println("PublicIP:\t"+node.getPublicIP());
			System.out.println(node.getExtra());	
		}
	}
}
