package org.askalon.jlibcloud.compute.core;

/**
 * this class stores the name of every provider.
 * the provider names and regions are equal to the
 * names in libcloud/compute/types.
 *
 */
public final class Providers {

	public static final Container DUMMY = new Container("dummy",
			new String[] {});

	public static final Container EC2 = new Container("ec2_us_east",
			new String[] { "us-east-1", "us-west-1", "us-west-2", "eu-west-1",
					"ap-southeast-1", "ap-northeast-1", " sa-east-1",
					"ap-southeast-2" });

	public static final Container RACKSPACE_FIRST_GEN = new Container(
			"rackspace_first_gen", new String[] { "us", "uk" });

	public static final Container RACKSPACE = new Container("rackspace",
			new String[] { "dfw", "ord", "iad", "lon", "syd", "hkg" });

	public static final Container GCE = new Container("gce", new String[] {});

	public static final Container GOGRID = new Container("gogrid",
			new String[] {});
	
	public static final Container EXOSCALE = new Container("exoscale",
			new String[] {});

	public static final Container VPSNET = new Container("vpsnet",
			new String[] {});

	public static final Container LINODE = new Container("linode",
			new String[] {});
	public static final Container VCLOUD = new Container("vcloud",
			new String[] {});
	public static final Container RIMUHOSTING = new Container("rimuhosting",
			new String[] {});
	public static final Container VOXEL = new Container("voxel",
			new String[] {});
	public static final Container SOFTLAYER = new Container("softlayer",
			new String[] {});
	public static final Container EUCALYPTUS = new Container("eucalyptus",
			new String[] {});
	public static final Container ECP = new Container("ecp", new String[] {});
	public static final Container IBM = new Container("ibm", new String[] {});
	public static final Container OPENNEBULA = new Container("opennebula",
			new String[] {});
	public static final Container DREAMHOST = new Container("dreamhost",
			new String[] {});
	public static final Container ELASTICHOSTS = new Container("elastichosts",
			new String[] { "sat-p", "lon-p", "lon-b", "lax-p", "sjc-c",
					"tor-p", "syd-y", "cn-1" });
	public static final Container BRIGHTBOX = new Container("brightbox",
			new String[] {});
	public static final Container CLOUDSIGMA = new Container("cloudsigma",
			new String[] {});
	public static final Container NIMBUS = new Container("nimbus",
			new String[] {});
	public static final Container BLUEBOX = new Container("bluebox",
			new String[] {});
	public static final Container GANDI = new Container("gandi",
			new String[] {});
	public static final Container IKOULA = new Container("ikoula",
			new String[] {});
	public static final Container OPSOURCE = new Container("opsource",
			new String[] {});
	public static final Container OPENSTACK = new Container("openstack",
			new String[] {});
	public static final Container SKALICLOUD = new Container("skalicloud",
			new String[] {});
	public static final Container SERVERLOVE = new Container("serverlove",
			new String[] {});
	public static final Container NINEFOLD = new Container("ninefold",
			new String[] {});
	public static final Container TERREMARK = new Container("terremark",
			new String[] {});
	public static final Container CLOUDSTACK = new Container("cloudstack",
			new String[] {});
	public static final Container CLOUDSIGMA_US = new Container(
			"cloudsigma_us", new String[] {});
	public static final Container LIBVIRT = new Container("libvirt",
			new String[] {});
	public static final Container JOYENT = new Container("joyent",
			new String[] {});
	public static final Container VCL = new Container("vcl", new String[] {});
	public static final Container KTUCLOUD = new Container("ktucloud",
			new String[] {});
	public static final Container GRIDSPOT = new Container("gridspot",
			new String[] {});
	public static final Container HOSTVIRTUAL = new Container("hostvirtual",
			new String[] {});
	public static final Container ABIQUO = new Container("abiquo",
			new String[] {});
	public static final Container DIGITAL_OCEAN = new Container("digitalocean",
			new String[] {});
	public static final Container NEPHOSCALE = new Container("nephoscale",
			new String[] {});
	public static final Container CLOUDFRAMES = new Container("cloudframes",
			new String[] {"lon","dfw"}); //which is default?

}

