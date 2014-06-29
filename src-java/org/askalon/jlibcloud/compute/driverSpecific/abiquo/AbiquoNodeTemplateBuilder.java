package org.askalon.jlibcloud.compute.driverSpecific.abiquo;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;

public interface AbiquoNodeTemplateBuilder extends NodeTemplateBuilder{

	/**
	 * @param name The name for this new node (required)
	 * 			(:type name: ``str``)
	**/
	public AbiquoNodeTemplateBuilder name(String name);

	/**
	 * @param size The size of resources allocated to this node.
	 * 			(:type size: :class:`NodeSize`)
	**/
	public AbiquoNodeTemplateBuilder size(NodeSize size);

	/**
	 * @param image OS Image to boot on node. (required)
	 * 			(:type image: :class:`NodeImage`)
	**/
	public AbiquoNodeTemplateBuilder image(NodeImage image);

	/**
	 * @param location Which data center to create a node in. If empty,
	 * 			undefined behavoir will be selected. (optional)
	 * 			(:type location: :class:`NodeLocation`)
	**/
	public AbiquoNodeTemplateBuilder location(NodeLocation location);

	/**
	 * @param group_name Which group this node belongs to. If empty,
	 * 			it will be created into 'libcloud' group. If
	 * 			it does not found any group in the target
	 * 			location (random location if you have not set
	 * 			the parameter), then it will create a new
	 * 			group with this name.
	 * 			(:type group_name: c{str})
	**/
	public AbiquoNodeTemplateBuilder groupName(String group_name);

}