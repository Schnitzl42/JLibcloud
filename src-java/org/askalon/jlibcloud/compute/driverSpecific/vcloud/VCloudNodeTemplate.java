package org.askalon.jlibcloud.compute.driverSpecific.vcloud;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;

public interface VCloudNodeTemplate extends NodeTemplate{

	/**
	 * @return ex_network link to a "Network" e.g.,
	 * 			``https://services.vcloudexpress...``
	 * 			(:type ex_network: ``str``)
	**/
	public String getExNetwork();

	/**
	 * @return ex_vdc Name of organisation's virtual data
	 * 			center where vApp VMs will be deployed.
	 * 			(:type ex_vdc: ``str``)
	**/
	public String getExVdc();

	/**
	 * @return ex_cpus number of virtual cpus (limit depends on provider)
	**/
	public Integer getExCpus();

	/**
	 * @return ex_group ``str``
	**/
	public String getExGroup();

}