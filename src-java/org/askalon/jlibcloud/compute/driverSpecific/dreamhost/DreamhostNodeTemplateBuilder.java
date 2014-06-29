package org.askalon.jlibcloud.compute.driverSpecific.dreamhost;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;

public interface DreamhostNodeTemplateBuilder extends NodeTemplateBuilder{

	/**
	 * @param ex_movedata Copy all your existing users to this new PS
	 * 			(:type ex_movedata: ``str``)
	**/
	public DreamhostNodeTemplateBuilder exMovedata(String ex_movedata);

}