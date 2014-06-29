package org.askalon.jlibcloud.compute.driverSpecific.brightbox;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;

public interface BrightboxNodeTemplate extends NodeTemplate{

	/**
	 * @return ex_userdata User data
	 * 			(:type ex_userdata: ``str``)
	**/
	public String getExUserdata();

	/**
	 * @return ex_servergroup Name or list of server group ids to
	 * 			add server to
	**/
	public String getExServergroup();

}