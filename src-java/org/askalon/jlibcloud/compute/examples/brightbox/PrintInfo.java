package org.askalon.jlibcloud.compute.examples.brightbox;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.askalon.jlibcloud.compute.core.ComputeContextBuilder;
import org.askalon.jlibcloud.compute.core.Providers;
import org.askalon.jlibcloud.compute.driverSpecific.brightbox.BrightboxComputeContext;
import org.askalon.jlibcloud.compute.examples.BasePrintInfo;
import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;

public class PrintInfo {

	public static void main(String[] args) throws Exception {

		ComputeContext cc = ComputeContextBuilder
				.newBuilder(Providers.BRIGHTBOX.name)
				.credentialsFromFile("/home/marcus/BAK/secrets.py").build();

		BrightboxComputeContext bbcc = (BrightboxComputeContext) cc;

		
		List<Map<String,String>> dips = bbcc.exListCloudIps();
		
		String cloudIp = "";
		for(Map<String,String> map : dips){
			//use ? because "account" entry contains a map
			for(Entry<String, ?> entry : map.entrySet()){
				System.out.println(entry.getKey() + " : "+ entry.getValue());
				if(entry.getKey().equals("id")){
					cloudIp = (String) entry.getValue();
				}
			}
		}
		
		String nodeId = "";
		for(Node n : cc.listNodes()){
			if(n.getState().equals(0)){
				nodeId = n.getID();
			}
		}
		System.out.println(nodeId);
		System.out.println(cloudIp);
		bbcc.exMapCloudIp(cloudIp, "lba-vh0d9");
		
		
	
		BasePrintInfo.printBaseInfo(cc, false);

		System.out.println("\nCLOUD_IPS:");
		List<Map<String,String>> cips = bbcc.exListCloudIps();
		
		for(Map<String,String> map : cips){
			//use ? because "account" entry contains a map
			for(Entry<String, ?> entry : map.entrySet()){
				System.out.println(entry.getKey() + " : "+ entry.getValue());
			}
		}
	}
}
