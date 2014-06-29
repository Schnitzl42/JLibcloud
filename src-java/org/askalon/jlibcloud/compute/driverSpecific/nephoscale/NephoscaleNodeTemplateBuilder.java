package org.askalon.jlibcloud.compute.driverSpecific.nephoscale;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;

public interface NephoscaleNodeTemplateBuilder extends NodeTemplateBuilder{

	/**
	**/
	public NephoscaleNodeTemplateBuilder name(String name);

	/**
	**/
	public NephoscaleNodeTemplateBuilder size(NodeSize size);

	/**
	**/
	public NephoscaleNodeTemplateBuilder image(NodeImage image);

	/**
	**/
	public NephoscaleNodeTemplateBuilder serverKey(String server_key);

	/**
	**/
	public NephoscaleNodeTemplateBuilder consoleKey(String console_key);

	/**
	 * @param zone a location id
	**/
	public NephoscaleNodeTemplateBuilder zone(String zone);

}