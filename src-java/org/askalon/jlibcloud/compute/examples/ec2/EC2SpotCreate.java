package org.askalon.jlibcloud.compute.examples.ec2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.askalon.jlibcloud.compute.driverSpecific.ec2Ex.EC2ComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.ec2Ex.EC2NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.KeyPair;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.core.ComputeContextBuilder;
import org.askalon.jlibcloud.compute.core.Providers;

public class EC2SpotCreate {

	public static void main(String[] args) {

		String identity = "";
		String authentication = "﻿";
		String privateSSHKeyPath = "/home/marcus/.ssh/id_rsa_test_ec2.pem";
		String newKeyPairName = "NewUniqueKeyPair";
		String newGroupName = "NewUniqueGroup";

		ComputeContext cc = null;
		EC2ComputeContext ec2cc = null;

		try {
			cc = ComputeContextBuilder.newBuilder(Providers.EC2.name)
					.credentials(identity, authentication).build();
			ec2cc = (EC2ComputeContext) cc;

			System.out.println("creating new KEYPAIR...");
			// this is the only time ec2 sends the private key!!!
			// cc.getKeyPair(newKeyPairName) would return a KeyPair
			// with only name and fingerprint
			KeyPair kp = cc.createKeyPair(newKeyPairName);
			// so write the key immediatley to a file
			FileWriter fw;
			try {
				fw = new FileWriter(new File(privateSSHKeyPath));
				fw.write(kp.getPrivateKey());
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("private key written to: " + privateSSHKeyPath);

			System.out.println("creating new GROUP...");
			ec2cc.exCreateSecurityGroup(newGroupName, "my new group");
			ec2cc.exAuthorizeSecurityGroup(newGroupName, "22", "22",
					"0.0.0.0/0");

			System.out.println("creating new TEMPLATE...");
			// build a new node template
			NodeTemplate nt = cc.getTemplateBuilder().nodeName("test-node")
					.sizeId("t1.micro").imageId("ami-4f9fee26")
					.keyPair(newKeyPairName).as(EC2NodeTemplateBuilder.class)
					.exSecurityGroups(newGroupName).exSpotPrice(0.04f)
					.exSpotInstances(1).
					build();

			// create the node
			cc.createNode(nt);
		
			System.out.println("node created... waiting...");
			ec2cc.exWaitForPendingSpotNodes();
			cc.waitUntilRunning((List<Node>) cc.listNodes());
			System.out.println("waited.....");

			System.out.println("\nCURRENT NODES:");
			List<Node> nodes = (List<Node>) cc.listNodes();
			print(nodes);
			String input = "";
			Scanner scan = new Scanner(System.in);
			while (!input.equals("q")) {
				System.out.println("================================");
				System.out
						.println("Enter 'q' to quit, or 'p' to print node info");
				input = scan.next();
				if (input.equals("p")) {
					System.out.println(cc.getNodeStateMap());
					printNodes(nodes);
					nodes = (List<Node>) cc.listNodes();
				}
			}
			System.out.println("cleaning up.....");
			for (Node n : nodes) {
				n.destroy();

			}

			// wait for node to shutdown
			Thread.sleep(30 * 1000);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cc.deleteKeyPair(cc.getKeyPair(newKeyPairName));
			ec2cc.exDeleteSecurityGroup(newGroupName);
			System.out.println("cleaned up...");
			System.out.println("\nCURRENT KEYPAIRS:");
			List<KeyPair> kps = cc.listKeyPairs();
			print(kps);
			// ec2 specific information
			System.out.println("\nCURRENT GROUPS:");
			List<String> gps = ec2cc.exListSecurityGroups();
			print(gps);
		}
	}

	public static void printNodes(List<Node> nodes) {
		for (int i = 0; i < nodes.size(); i++) {
			Node n = nodes.get(i);
			NodeSize ns = n.getSize();
			System.out.println("state: " + n.getState());
			System.out.println("Node: " + n.getName() + " with uuid: "
					+ n.getUUID() + " and id: " + n.getID()
					+ " has the public ip: " + n.getPublicIP() + " and size: "
					+ ns.getId());
			System.out.println("ssh: ssh -i <pathToPrivateKey> ec2-user@"
					+ n.getPublicIP());
			System.out.println(n);
			System.out.println("-------------------------");
		}
	}

	public static <T> void print(List<T> list) {
		Iterator<T> it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println("======================");
	}
}
