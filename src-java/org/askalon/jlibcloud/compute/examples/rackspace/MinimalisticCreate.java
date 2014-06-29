package org.askalon.jlibcloud.compute.examples.rackspace;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.askalon.jlibcloud.compute.driverSpecific.openstack.OpenStackKeyPair;
import org.askalon.jlibcloud.compute.driverSpecific.openstack.OpenStack_1_1_NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.KeyPair;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;
import org.askalon.jlibcloud.compute.wrapperInterfaces.deployment.ScriptDeployment;
import org.askalon.jlibcloud.compute.wrapperInterfacesImpl.deploymentImpl.FileDeploymentImpl;
import org.askalon.jlibcloud.compute.wrapperInterfacesImpl.deploymentImpl.ScriptDeploymentImpl;
import org.askalon.jlibcloud.compute.wrapperInterfacesImpl.deploymentImpl.SshKeyDeploymentImpl;
import org.askalon.jlibcloud.compute.core.ComputeContextBuilder;
import org.askalon.jlibcloud.compute.core.Providers;

public class MinimalisticCreate {

	public static void main(String[] args) {
		
		String privateSSHKeyPath = "/home/marcus/.ssh/id_rsa_test_rackspace.pem";
		String publicSSHKeyPath = "/home/marcus/.ssh/id_rsa_test_rackspace.pub";
		String keyName = "RackspaceTestKey";
		ComputeContext cc = ComputeContextBuilder
				.newBuilder(Providers.RACKSPACE.name).region("lon")
				.credentialsFromFile("/home/marcus/BAK/secrets.py").build();

		//cc.setNodeProperties("/home/marcus/BAK/rck.properties");
		//TH5K99GNpGHe
		
		//System.out.println("creating node...");
		/*
		 * NodeTemplate nt = cc .getTemplateBuilder() .deploy(new
		 * FileDeploymentImpl( "/home/marcus/BAK/helloWorld.txt",
		 * "/home/ec2-user/")) .deploy(new FileDeploymentImpl(
		 * "/home/marcus/BAK/helloWorld.txt", "/usr/local/lib")) .build();
		 */

																		//set the ssh password for deploy
		NodeTemplate nt = cc.getTemplateBuilder().nodeName("testNode2").keyPair("RackspaceTestKey")
				//Ubuntu 10.04 LTS (Lucid Lynx)
				.imageId("aab63bcf-89aa-440f-b0c7-c7a1c611914b").size(cc.listSizes().get(0))
				.as(OpenStack_1_1_NodeTemplateBuilder.class).exAdminPass("1234!")//.vncPassword(false)
				
				.build();
		

		/*List<KeyPair> lkps = cc.listKeyPairs();
		for(KeyPair kp : lkps){
			System.out.println(kp.getName());
		}*/
		
		/*KeyPair kp = cc.createKeyPair(keyName);
		System.out.println("new: "+kp.getName());
		//save the key
		FileWriter fw;
		try {
			fw = new FileWriter(new File(privateSSHKeyPath));
			fw.write(kp.getPrivateKey());
			fw.close();
			fw = new FileWriter(new File(publicSSHKeyPath));
			fw.write(kp.getPublicKey());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		List<Node> nodeList = (List<Node>) cc.createNode(nt);
		System.out.println(nodeList);

		System.out.println("node created! waiting...");
		boolean wait = cc.waitUntilRunning(nodeList);
		if (wait) {
			System.out.println("node is runing!");
		} else {
			System.err.println("Timeout!");
		}
	}
}
