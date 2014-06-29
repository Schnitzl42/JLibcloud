package org.askalon.jlibcloud.compute.driverSpecific.nephoscale;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;

public interface NephoscaleNodeTemplate extends NodeTemplate{

	/**
	**/
	public String getName();

	/**
	**/
	public NodeSize getSize();

	/**
	**/
	public NodeImage getImage();

	/**
	**/
	public String getServerKey();

	/**
	**/
	public String getConsoleKey();

	/**
	**/
	public String getZone();

}