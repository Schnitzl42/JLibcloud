package org.askalon.jlibcloud.compute.driverSpecific.brightbox;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;

public interface BrightboxNodeTemplateBuilder extends NodeTemplateBuilder{

	/**
	 * @param ex_userdata User data
	 * 			(:type ex_userdata: ``str``)
	**/
	public BrightboxNodeTemplateBuilder exUserdata(String ex_userdata);

	/**
	 * @param ex_servergroup Name or list of server group ids to
	 * 			add server to
	**/
	public BrightboxNodeTemplateBuilder exServergroup(String ex_servergroup);

}