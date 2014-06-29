package org.askalon.jlibcloud.compute.examples.ec2_rackspace;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.askalon.jlibcloud.compute.core.ComputeContextBuilder;
import org.askalon.jlibcloud.compute.core.Providers;
import org.askalon.jlibcloud.compute.driverSpecific.ec2Ex.EC2ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.KeyPair;

public class NodePropertiesSetup {

	public static void main(String[] args) {

		// Instantiate EC2 Context
		ComputeContext ec2Context = ComputeContextBuilder
				.newBuilder(Providers.EC2.name)
				.region(Providers.EC2.regions[0])
				.credentialsFromFile("/home/marcus/BAK/secrets.py").build();

		// Instantiate Rackspace Context
		ComputeContext rackspContext = ComputeContextBuilder
				.newBuilder(Providers.RACKSPACE.name).region("lon")
				.credentialsFromFile("/home/marcus/BAK/secrets.py").build();

		// Create and Store KeyPair for EC2
		KeyPair kp0 = ec2Context.createKeyPair("FinalDemoKey");
		FileWriter fw;
		try {
			fw = new FileWriter(new File(
					"/home/marcus/.ssh/id_rsa_ec2_final.pem"));
			fw.write(kp0.getPrivateKey());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Create Security Group
		EC2ComputeContext ec2cc = (EC2ComputeContext) ec2Context;
		ec2cc.exCreateSecurityGroup("final-demo-group",
				"group for the final presentation");
		ec2cc.exAuthorizeSecurityGroup("final-demo-group", "22", "22",
				"0.0.0.0/0");

		// Create and Store KeyPair for Rackspace
		KeyPair kp1 = rackspContext.createKeyPair("FinalDemoKey");
		try {
			fw = new FileWriter(new File(
					"/home/marcus/.ssh/id_rsa_rackspace_final.pem"));
			fw.write(kp1.getPrivateKey());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
