package org.askalon.jlibcloud.compute.driverSpecific.dreamhost;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;

public interface DreamhostNodeTemplate extends NodeTemplate{

	/**
	 * @return ex_movedata Copy all your existing users to this new PS
	 * 			(:type ex_movedata: ``str``)
	**/
	public String getExMovedata();

}