package org.askalon.jlibcloud.compute.examples.brightbox;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.askalon.jlibcloud.compute.core.ComputeContextBuilder;
import org.askalon.jlibcloud.compute.core.Providers;
import org.askalon.jlibcloud.compute.driverSpecific.brightbox.BrightboxComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;

public class MinimalisticCreate {

	public static void main(String[] args) {

		ComputeContext cc = ComputeContextBuilder
				.newBuilder(Providers.BRIGHTBOX.name)
				.credentialsFromFile("/home/marcus/BAK/secrets.py").build();

		BrightboxComputeContext bbcc = (BrightboxComputeContext) cc;

		NodeTemplate nt = cc.getTemplateBuilder()
				// Ubuntu Lucid 10.04 LTS server image, nano size
				.nodeName("test-node1").imageId("img-fqqt0")
				.size(cc.listSizes().get(0)).build();

		List<? extends Node> nodeList = cc.createNode(nt);
		System.out.println(nodeList);
		
		System.out.println("node created! waiting...");
		@SuppressWarnings("unchecked")
		boolean wait = cc.waitUntilRunning((List<Node>) nodeList);
		if (wait) {
			System.out.println("node is runing!");
		} else {
			System.err.println("Timeout!");
		}

		// map ip to node
		// an ip is needed to connect to the node
		// creat ip before

		List<Map<String, String>> cips = bbcc.exListCloudIps();

		String cloudIp = "";
		for (Map<String, String> map : cips) {
			// use ? because "account" entry contains a map
			for (Entry<String, ?> entry : map.entrySet()) {
				System.out.println(entry.getKey() + " : " + entry.getValue());
				if (entry.getKey().equals("id")) {
					cloudIp = (String) entry.getValue();
				}
			}
		}
		System.out.println("========");
		System.out.println(cloudIp);

		bbcc.exMapCloudIp(cloudIp, nodeList.get(0).getID());

		
	}
}
