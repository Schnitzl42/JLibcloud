package org.askalon.jlibcloud.compute.driverSpecific.cloudframes;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import java.util.Map;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;

public interface CloudFramesNodeTemplate extends NodeTemplate{

	/**
	 * @return image The template to be cloned (required)
	 * 			(:type image: ``list`` of :class:`NodeImage`)
	**/
	public NodeImage getImage();

	/**
	 * @return name The name for the new node (required)
	 * 			(:type name: ``str``)
	**/
	public String getName();

	/**
	 * @return size The size of the new node (required)
	 * 			(:type size: ``list`` of :class:`NodeSize`)
	**/
	public NodeSize getSize();

	/**
	 * @return location The location to create the new node
	 * 			(:type location: ``list`` of :class:`NodeLocation`)
	**/
	public NodeLocation getLocation();

	/**
	 * @return default_gateway The default gateway to be used
	 * 			(:type default_gateway: ``str``)
	**/
	public String getDefaultGateway();

	/**
	 * @return extra Additional requirements (extra disks fi.)
	 * 			(:type extra: ``dict``)
	**/
	public Map<String,Arg> getExtra();

}