package org.askalon.jlibcloud.compute.examples;

import java.util.List;
import java.util.Map;

import org.askalon.jlibcloud.compute.types.NodeState;
import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;

public class BasePrintInfo {

	private BasePrintInfo() {
	}

	/**
	 * prints sizes, 10 images, locations, nodes and the node-state-map
	 * for the given compute context.
	 * 
	 * @param cc the ComputeContext
	 * @param destroy
	 * 			destroys all nodes if true
	 */
	public static void printBaseInfo(ComputeContext cc, boolean destroy) {

		System.out.println("SIZES:");
		for (NodeSize ns : cc.listSizes()) {
			System.out.println("uuid=" + ns.getUUID() + "id=" + ns.getId()
					+ " name=" + ns.getName() + " ram=" + ns.getRamMB()
					+ " disk=" + ns.getDiskSizeGB() + " bandwith="
					+ ns.getBandwidth() + " price=" + ns.getHourPriceDollar()
					+ " driver=" + ns.getName() + "\n");

			System.out.println("====================================");
		}

		System.out.println("\nIMAGES:");
		int i = 0;
		for (NodeImage ni : cc.listImages()) {

			if (i > 10) {
				break;
			}

			i++;
			// if (ni.getName().toLowerCase().contains("ubuntu")) {
			System.out.println("id=" + ni.getId() + " name=" + ni.getName());
			System.out.println(ni.toString());
			System.out.println(ni.getExtra());
			System.out.println("==========================");
			// }
		}

		System.out.println("\nLOCATIONS:");
		List<? extends NodeLocation> locations = cc.listLocations();
		for (int j = 0; j < locations.size(); j++) {
			NodeLocation nl = locations.get(j);
			System.out.println("id=" + nl.getId() + " name=" + nl.getName()
					+ " country=" + nl.getCountry());
		}
		System.out.println("====================================");

		System.out.println("\nNODES:");
		List<? extends Node> nodes = cc.listNodes();
		for (Node n : nodes) {
			System.out.println("full node: " + n);
			NodeSize ns = n.getSize();
			System.out
					.println("Node: " + n.getName() + " with uuid: "
							+ n.getUUID() + 
							" and id: " + n.getID() + " has the public ip: "
							+ n.getPublicIP() + " and size: " + ns.getId() + ns
							+ ns.getHourPriceDollar());
			System.out.println("nodeImage: " + n.getImage());
			System.out.println("ssh: user@" + n.getPublicIP());
			System.out.println("node_state: "+n.getState());
			System.out.println("==============");
			if (destroy) {
				n.destroy();
			}
		}
		

		System.out.println("\nNODE_STATE_MAP:");
		Map<String, NodeState> state = cc.getNodeStateMap();
		System.out.println(state);
	}

}
