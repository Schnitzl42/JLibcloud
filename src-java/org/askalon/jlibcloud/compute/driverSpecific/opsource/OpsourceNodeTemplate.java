package org.askalon.jlibcloud.compute.driverSpecific.opsource;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;

public interface OpsourceNodeTemplate extends NodeTemplate{

	/**
	 * @return name String with a name for this new node (required)
	 * 			(:type name: ``str``)
	**/
	public String getName();

	/**
	 * @return image OS Image to boot on node. (required)
	 * 			(:type image: :class:`NodeImage`)
	**/
	public NodeImage getImage();


	/**
	 * @return ex_description description for this node (required)
	 * 			(:type ex_description: ``str``)
	**/
	public String getExDescription();

	/**
	 * @return ex_network Network to create the node within (required)
	 * 			(:type ex_network: :class:`OpsourceNetwork`)
	**/
	public OpsourceNetwork getExNetwork();

	/**
	 * @return ex_isStarted Start server after creation? default
	 * 			true (required)
	 * 			(:type ex_isStarted: ``bool``)
	**/
	public Boolean getExIsStarted();

}