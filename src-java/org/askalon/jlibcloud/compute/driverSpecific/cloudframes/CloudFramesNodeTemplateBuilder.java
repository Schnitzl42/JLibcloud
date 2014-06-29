package org.askalon.jlibcloud.compute.driverSpecific.cloudframes;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;

import java.util.Map;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;

public interface CloudFramesNodeTemplateBuilder extends NodeTemplateBuilder{

	/**
	 * @param image The template to be cloned (required)
	 * 			(:type image: ``list`` of :class:`NodeImage`)
	**/
	public CloudFramesNodeTemplateBuilder image(NodeImage image);

	/**
	 * @param name The name for the new node (required)
	 * 			(:type name: ``str``)
	**/
	public CloudFramesNodeTemplateBuilder name(String name);

	/**
	 * @param size The size of the new node (required)
	 * 			(:type size: ``list`` of :class:`NodeSize`)
	**/
	public CloudFramesNodeTemplateBuilder size(NodeSize size);

	/**
	 * @param location The location to create the new node
	 * 			(:type location: ``list`` of :class:`NodeLocation`)
	**/
	public CloudFramesNodeTemplateBuilder location(NodeLocation location);

	/**
	 * @param default_gateway The default gateway to be used
	 * 			(:type default_gateway: ``str``)
	**/
	public CloudFramesNodeTemplateBuilder defaultGateway(String default_gateway);

	/**
	 * @param extra Additional requirements (extra disks fi.)
	 * 			(:type extra: ``dict``)
	**/
	public CloudFramesNodeTemplateBuilder extra(Map<String,Arg> extra);

}