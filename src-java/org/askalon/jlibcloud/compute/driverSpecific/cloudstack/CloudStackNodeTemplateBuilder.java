package org.askalon.jlibcloud.compute.driverSpecific.cloudstack;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;
import java.util.List;

public interface CloudStackNodeTemplateBuilder extends NodeTemplateBuilder{

	/**
	 * @param networks Optional list of networks to launch the server
	 * 			into.
	 * 			(:type networks: ``list`` of :class:`.CloudStackNetwork`)
	**/
	public CloudStackNodeTemplateBuilder networks(List<CloudStackNetwork> networks);

	/**
	 * @param ex_keyname Name of existing keypair
	 * 			(:type ex_keyname: ``str``)
	**/
	public CloudStackNodeTemplateBuilder exKeyname(String ex_keyname);

	/**
	 * @param ex_userdata String containing user data
	 * 			(:type ex_userdata: ``str``)
	**/
	public CloudStackNodeTemplateBuilder exUserdata(String ex_userdata);

	/**
	 * @param ex_security_groups List of security groups to assign to
	 * 			the node
	 * 			(:type ex_security_groups: ``list`` of ``str``)
	**/
	public CloudStackNodeTemplateBuilder exSecurityGroups(String... ex_security_groups);

}