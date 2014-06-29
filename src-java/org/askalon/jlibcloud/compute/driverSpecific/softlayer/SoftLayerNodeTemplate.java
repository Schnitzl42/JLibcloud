package org.askalon.jlibcloud.compute.driverSpecific.softlayer;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;

public interface SoftLayerNodeTemplate extends NodeTemplate{

	/**
	 * @return ex_domain e.g. libcloud.org
	 * 			(:type ex_domain: ``str``)
	**/
	public String getExDomain();

	/**
	 * @return ex_cpus e.g. 2
	 * 			(:type ex_cpus: ``int``)
	**/
	public Integer getExCpus();

	/**
	 * @return ex_disk e.g. 100
	 * 			(:type ex_disk: ``int``)
	**/
	public Integer getExDisk();

	/**
	 * @return ex_ram e.g. 2048
	 * 			(:type ex_ram: ``int``)
	**/
	public Integer getExRam();

	/**
	 * @return ex_bandwidth e.g. 100
	 * 			(:type ex_bandwidth: ``int``)
	**/
	public Integer getExBandwidth();

	/**
	 * @return ex_local_disk e.g. True
	 * 			(:type ex_local_disk: ``bool``)
	**/
	public Boolean getExLocalDisk();

	/**
	 * @return ex_datacenter e.g. Dal05
	 * 			(:type ex_datacenter: ``str``)
	**/
	public String getExDatacenter();

	/**
	 * @return ex_os e.g. UBUNTU_LATEST
	 * 			(:type ex_os: ``str``)
	**/
	public String getExOs();

}