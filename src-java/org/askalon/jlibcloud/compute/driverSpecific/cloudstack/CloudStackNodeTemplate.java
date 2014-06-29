package org.askalon.jlibcloud.compute.driverSpecific.cloudstack;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import java.util.List;

public interface CloudStackNodeTemplate extends NodeTemplate{

	/**
	 * @return networks Optional list of networks to launch the server
	 * 			into.
	 * 			(:type networks: ``list`` of :class:`.CloudStackNetwork`)
	**/
	public List<CloudStackNetwork> getNetworks();

	/**
	 * @return ex_keyname Name of existing keypair
	 * 			(:type ex_keyname: ``str``)
	**/
	public String getExKeyname();

	/**
	 * @return ex_userdata String containing user data
	 * 			(:type ex_userdata: ``str``)
	**/
	public String getExUserdata();

	/**
	 * @return ex_security_groups List of security groups to assign to
	 * 			the node
	 * 			(:type ex_security_groups: ``list`` of ``str``)
	**/
	public String[] getExSecurityGroups();

}