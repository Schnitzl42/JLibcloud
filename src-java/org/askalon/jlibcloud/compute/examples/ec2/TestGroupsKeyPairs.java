package org.askalon.jlibcloud.compute.examples.ec2;


import java.util.List;
import java.util.Map;

import org.askalon.jlibcloud.compute.driverSpecific.ec2Ex.EC2ComputeContext;
import org.askalon.jlibcloud.compute.types.NodeState;
import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.KeyPair;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.core.ComputeContextBuilder;
import org.askalon.jlibcloud.compute.core.Providers;

public class TestGroupsKeyPairs {

	public static void main(String[] args) throws Exception {

		
		ComputeContext cc = ComputeContextBuilder
				.newBuilder(Providers.EC2.name)
				.credentialsFromFile("/home/marcus/BAK/secrets.py").build();

		
		 List<Node> nodes = (List<Node>) cc.listNodes(); 
		 for(Node n : nodes){
			 System.out.println(n); 
			 NodeSize ns = n.getSize();
			 System.out.println("Node: " + n.getName() + " with uuid: "
					+ n.getUUID() + " has the public ip: " + n.getPublicIP()
					+ " and size: " + ns.getId()+ns+ns.getHourPriceDollar());
			 System.out.println("nodeImage: "+n.getImage());
			System.out.println("ssh: ec2-user@" + n.getPublicIP());
			System.out.println(n.getState());
			System.out.println("==============");
			n.destroy();
		 }
		 Map<String, NodeState> state = cc.getNodeStateMap();
		 System.out.println(state);
		 
		
		EC2ComputeContext ec2cc = (EC2ComputeContext) cc;
		String newKeyPairName = "NewUniqueKeyPair";
		String newGroupName = "NewUniqueGroup";
		//delete if allready existing
		List<KeyPair> kps = cc.listKeyPairs();
		for(KeyPair kp : kps){
			if(kp.getName().equals(newKeyPairName)){
				cc.deleteKeyPair(cc.getKeyPair(newKeyPairName));
			}
		}
		List<String> sgs = ec2cc.exListSecurityGroups();
		for(String sg : sgs){
			if(sg.equals(newGroupName)){
				ec2cc.exDeleteSecurityGroup(newGroupName);
			}
		}
		//create 
		cc.createKeyPair(newKeyPairName);
		ec2cc.exCreateSecurityGroup(newGroupName, "description");
		ec2cc.exAuthorizeSecurityGroup(newGroupName, "22", "22", "0.0.0.0/0");
		System.out.println("GROUPS 1 NEW:");
		
		for(String key :ec2cc.exListSecurityGroups()){
			System.out.println(key);
		} 
		 System.out.println("===========");
		 System.out.println("KEYPAIRS 1 NEW:");
		 for(KeyPair kp : cc.listKeyPairs()){
				System.out.println(kp);
			}
		 System.out.println("===========");
		 //delete
		 ec2cc.exDeleteSecurityGroup(newGroupName);
		 cc.deleteKeyPair(cc.getKeyPair(newKeyPairName));
		 System.out.println("GROUPS 1 DELETED:");
		 for(String key :ec2cc.exListSecurityGroups()){
				System.out.println(key);
		} 
		System.out.println("===========");
		System.out.println("KEYPAIRS 1 DELETED:");
		 for(KeyPair kp : cc.listKeyPairs()){
				System.out.println(kp);
			}
		 System.out.println("===========");
	}
}
