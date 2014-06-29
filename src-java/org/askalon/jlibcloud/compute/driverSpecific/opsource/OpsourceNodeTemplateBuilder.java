package org.askalon.jlibcloud.compute.driverSpecific.opsource;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;

public interface OpsourceNodeTemplateBuilder extends NodeTemplateBuilder{

	/**
	 * @param name String with a name for this new node (required)
	 * 			(:type name: ``str``)
	**/
	public OpsourceNodeTemplateBuilder name(String name);

	/**
	 * @param image OS Image to boot on node. (required)
	 * 			(:type image: :class:`NodeImage`)
	**/
	public OpsourceNodeTemplateBuilder image(NodeImage image);

	/**
	 * @param ex_description description for this node (required)
	 * 			(:type ex_description: ``str``)
	**/
	public OpsourceNodeTemplateBuilder exDescription(String ex_description);

	/**
	 * @param ex_network Network to create the node within (required)
	 * 			(:type ex_network: :class:`OpsourceNetwork`)
	**/
	public OpsourceNodeTemplateBuilder exNetwork(OpsourceNetwork ex_network);

	/**
	 * @param ex_isStarted Start server after creation? default
	 * 			true (required)
	 * 			(:type ex_isStarted: ``bool``)
	**/
	public OpsourceNodeTemplateBuilder exIsStarted(boolean ex_isStarted);

}