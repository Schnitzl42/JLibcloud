package org.askalon.jlibcloud.compute.examples.ec2;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.askalon.jlibcloud.compute.core.ComputeContextBuilder;
import org.askalon.jlibcloud.compute.core.Providers;
import org.askalon.jlibcloud.compute.driverSpecific.ec2.ElasticIP;
import org.askalon.jlibcloud.compute.driverSpecific.ec2.ExEC2AvailabilityZone;
import org.askalon.jlibcloud.compute.driverSpecific.ec2Ex.EC2ComputeContext;
import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.types.ArgImpl;
import org.askalon.jlibcloud.compute.types.NestedMap;
import org.askalon.jlibcloud.compute.types.NestedMapImpl;
import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.StorageVolume;

public class SomeTestStuff {

	public static void main(String[] args) throws URISyntaxException {

		ComputeContext cc = ComputeContextBuilder
				.newBuilder(Providers.EC2.name).credentialsFromFile("std")
				.nodePropertiesFrom("/home/marcus/BAK/std.properties").build();
		EC2ComputeContext ec2cc = (EC2ComputeContext) cc;

		// StorageVolume sv0 = cc.createVolume(2, "testVolume");
		// System.out.println(sv0);

		System.out.println("STORAGE VOLUMES:");
		List<StorageVolume> vols = cc.listVolumes();
		for (StorageVolume sv : vols) {
			System.out.println(sv.getSizeGB());
			System.out.println(sv);
		}

		System.out.println("AVAILABILITY ZONES:");
		List<ExEC2AvailabilityZone> avzones = ec2cc.exListAvailabilityZones();
		for (ExEC2AvailabilityZone az : avzones) {
			System.out.println(az);
		}

		System.out.println("ALL EALSTIC IPS:");
		List<ElasticIP> eips = ec2cc.exDescribeAllAddresses();
		for (ElasticIP ip : eips) {
			System.out.println(ip);
		}

		/*
		 * System.out.println("Creating Snapshot of first volume.."); if
		 * (vols.size() >= 1) { StorageVolume vol = vols.get(0); VolumeSnapshot
		 * vos = cc.createVolumeSnapshot(vol, "testSnapshot");
		 * System.out.println(vos.getID()); System.out.println(vos.toString());
		 * 
		 * System.out.println("VOLUME SNAPSHOTS:"); // there seems to be a
		 * conceptual mistake with volumes and // snapshots // because
		 * listVolumeSnapshots() needs a snapshot(id) but gets a // Volume(id)
		 * List<VolumeSnapshot> vss = cc.listVolumeSnapshots(vol); if (vss !=
		 * null) { for (VolumeSnapshot vs : vss) { System.out.println(vs); } } }
		 */

		List<Node> lns = (List<Node>) cc.listNodes();
		Node n = lns.get(0);

		Map<String, Arg> tags = new HashMap<String, Arg>();
		tags.put("attribute1", new ArgImpl("Tag1Value"));
		tags.put("attribute2", new ArgImpl("Tag2Value"));

		
		 System.out.println("Creating tag..."); 
		 //ec2cc.exCreateTags(n, tags);
		 

		System.out.println("TAGS:");
		Map<String, String> dtags = ec2cc.exDescribeTags(n);
		System.out.println(dtags);

		System.out.println("creating ami from node...");
		/*
		 * [{'VirtualName': None, 'Ebs': {'VolumeSize': 10, 'VolumeType':
		 * 'standard', 'DeleteOnTermination': 'true'}, 'DeviceName':
		 * '/dev/sda1'}]
		 */
		NestedMap<String, Arg> bdm = new NestedMapImpl<String, Arg>();
		bdm.put("VirtualName", new ArgImpl("testVName"));
		bdm.put("DeviceName", new ArgImpl("/dev/sda1"));
		
		Map<String,Arg> ebs = new HashMap<String, Arg>();
		ebs.put("VolumeSize", new ArgImpl(8));
		ebs.put("VolumeType", new ArgImpl("standard"));
		ebs.put("DeleteOnTermination", new ArgImpl("true"));
		
		//bdm.put("VolumeSize", new ArgImpl(8));
		
		bdm.put("Ebs", ebs);
		List<Map<String, Arg>> bdms = new LinkedList<Map<String, Arg>>();
		bdms.add(bdm);

		//NodeImage ni = ec2cc.exCreateImageFromNode(n, "testImage", bdms);
		//System.out.println(ni);
		
		System.out.println("creating groups+authorize it...");
		ec2cc.exCreateSecurityGroup("test", "test groups");
		ec2cc.exAuthorizeSecurityGroup("test", "22", "22", null);
	}
}
