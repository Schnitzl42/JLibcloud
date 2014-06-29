package org.askalon.jlibcloud.compute.driverSpecific.abiquo;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;

public interface AbiquoNodeTemplate extends NodeTemplate{

	/**
	 * @return name The name for this new node (required)
	 * 			(:type name: ``str``)
	**/
	public String getName();

	/**
	 * @return size The size of resources allocated to this node.
	 * 			(:type size: :class:`NodeSize`)
	**/
	public NodeSize getSize();

	/**
	 * @return image OS Image to boot on node. (required)
	 * 			(:type image: :class:`NodeImage`)
	**/
	public NodeImage getImage();

	/**
	 * @return location Which data center to create a node in. If empty,
	 * 			undefined behavoir will be selected. (optional)
	 * 			(:type location: :class:`NodeLocation`)
	**/
	public NodeLocation getLocation();

	/**
	 * @return group_name Which group this node belongs to. If empty,
	 * 			it will be created into 'libcloud' group. If
	 * 			it does not found any group in the target
	 * 			location (random location if you have not set
	 * 			the parameter), then it will create a new
	 * 			group with this name.
	 * 			(:type group_name: c{str})
	**/
	public String getGroupName();

}