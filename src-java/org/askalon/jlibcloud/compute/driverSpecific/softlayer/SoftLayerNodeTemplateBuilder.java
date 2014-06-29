package org.askalon.jlibcloud.compute.driverSpecific.softlayer;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;

public interface SoftLayerNodeTemplateBuilder extends NodeTemplateBuilder{

	/**
	 * @param ex_domain e.g. libcloud.org
	 * 			(:type ex_domain: ``str``)
	**/
	public SoftLayerNodeTemplateBuilder exDomain(String ex_domain);

	/**
	 * @param ex_cpus e.g. 2
	 * 			(:type ex_cpus: ``int``)
	**/
	public SoftLayerNodeTemplateBuilder exCpus(int ex_cpus);

	/**
	 * @param ex_disk e.g. 100
	 * 			(:type ex_disk: ``int``)
	**/
	public SoftLayerNodeTemplateBuilder exDisk(int ex_disk);

	/**
	 * @param ex_ram e.g. 2048
	 * 			(:type ex_ram: ``int``)
	**/
	public SoftLayerNodeTemplateBuilder exRam(int ex_ram);

	/**
	 * @param ex_bandwidth e.g. 100
	 * 			(:type ex_bandwidth: ``int``)
	**/
	public SoftLayerNodeTemplateBuilder exBandwidth(int ex_bandwidth);

	/**
	 * @param ex_local_disk e.g. True
	 * 			(:type ex_local_disk: ``bool``)
	**/
	public SoftLayerNodeTemplateBuilder exLocalDisk(boolean ex_local_disk);

	/**
	 * @param ex_datacenter e.g. Dal05
	 * 			(:type ex_datacenter: ``str``)
	**/
	public SoftLayerNodeTemplateBuilder exDatacenter(String ex_datacenter);

	/**
	 * @param ex_os e.g. UBUNTU_LATEST
	 * 			(:type ex_os: ``str``)
	**/
	public SoftLayerNodeTemplateBuilder exOs(String ex_os);

}