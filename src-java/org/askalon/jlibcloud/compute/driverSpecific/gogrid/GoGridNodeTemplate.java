package org.askalon.jlibcloud.compute.driverSpecific.gogrid;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;

public interface GoGridNodeTemplate extends NodeTemplate{

	/**
	 * @return ex_description Description of a Node
	 * 			(:type ex_description: ``str``)
	**/
	public String getExDescription();

	/**
	 * @return ex_ip Public IP address to use for a Node. If not
	 * 			specified, first available IP address will be picked
	 * 			(:type ex_ip: ``str``)
	**/
	public String getExIp();

}