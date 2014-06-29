package org.askalon.jlibcloud.compute.driverSpecific.gogrid;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;

public interface GoGridNodeTemplateBuilder extends NodeTemplateBuilder{

	/**
	 * @param ex_description Description of a Node
	 * 			(:type ex_description: ``str``)
	**/
	public GoGridNodeTemplateBuilder exDescription(String ex_description);

	/**
	 * @param ex_ip Public IP address to use for a Node. If not
	 * 			specified, first available IP address will be picked
	 * 			(:type ex_ip: ``str``)
	**/
	public GoGridNodeTemplateBuilder exIp(String ex_ip);

}