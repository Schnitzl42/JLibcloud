package org.askalon.jlibcloud.compute.driverSpecific.vcl;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;

public interface VCLNodeTemplate extends NodeTemplate{

	/**
	 * @return image image is the id from list_image
	 * 			(:type image: ``str``)
	**/
	public String getImageId();

	/**
	 * @return start start time as unix timestamp
	 * 			(:type start: ``str``)
	**/
	public String getStart();

	/**
	 * @return length length of time in minutes
	 * 			(:type length: ``str``)
	**/
	public String getLength();

}