package org.askalon.jlibcloud.compute.driverSpecific.vcl;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;

public interface VCLNodeTemplateBuilder extends NodeTemplateBuilder{

	/**
	 * @param image image is the id from list_image
	 * 			(:type image: ``str``)
	**/
	public VCLNodeTemplateBuilder image(String image);

	/**
	 * @param start start time as unix timestamp
	 * 			(:type start: ``str``)
	**/
	public VCLNodeTemplateBuilder start(String start);

	/**
	 * @param length length of time in minutes
	 * 			(:type length: ``str``)
	**/
	public VCLNodeTemplateBuilder length(String length);

}