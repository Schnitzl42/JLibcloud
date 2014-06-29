package org.askalon.jlibcloud.compute.driverSpecific.vcloud;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;

public interface VCloudNodeTemplateBuilder extends NodeTemplateBuilder{

	/**
	 * @param ex_network link to a "Network" e.g.,
	 * 			``https://services.vcloudexpress...``
	 * 			(:type ex_network: ``str``)
	**/
	public VCloudNodeTemplateBuilder exNetwork(String ex_network);

	/**
	 * @param ex_vdc Name of organisation's virtual data
	 * 			center where vApp VMs will be deployed.
	 * 			(:type ex_vdc: ``str``)
	**/
	public VCloudNodeTemplateBuilder exVdc(String ex_vdc);

	/**
	 * @param ex_cpus number of virtual cpus (limit depends on provider)
	**/
	public VCloudNodeTemplateBuilder exCpus(int ex_cpus);

	/**
	 * @param ex_group ``str``
	**/
	public VCloudNodeTemplateBuilder exGroup(String ex_group);

}