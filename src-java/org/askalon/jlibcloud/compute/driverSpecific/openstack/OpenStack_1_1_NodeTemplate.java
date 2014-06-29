package org.askalon.jlibcloud.compute.driverSpecific.openstack;

import java.util.List;
import java.util.Map;

import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;

public interface OpenStack_1_1_NodeTemplate extends NodeTemplate{
	
	/**
	 * @return ex_keyname The name of the key pair
	 * 			(:type ex_keyname: ``str``)
	**/
	public String getExKeyname();

	/**
	 * @return ex_userdata String containing user data
	 * 			see
	 * 			https://help.ubuntu.com/community/CloudInit
	 * 			(:type ex_userdata: ``str``)
	**/
	public String getExUserdata();

	/**
	 * @return ex_security_groups List of security groups to assign to
	 * 			the node
	 * 			(:type ex_security_groups: ``list`` of :class:`OpenStackSecurityGroup`)
	**/
	public List<OpenStackSecurityGroup> getExSecurityGroups();

	/**
	 * @return ex_metadata Key/Value metadata to associate with a node
	 * 			(:type ex_metadata: ``dict``)
	**/
	public Map<String,Arg> getExMetadata();

	/**
	 * @return ex_files File Path => File contents to create on
	 * 			the no de
	 * 			(:type ex_files: ``dict``)
	**/
	public Map<String,Arg> getExFiles();

	/**
	 * @return networks The server is launched into a set of Networks.
	 * 			(:type networks: :class:`OpenStackNetwork`)
	**/
	public OpenStackNetwork getNetworks();

	/**
	 * @return ex_disk_config Name of the disk configuration.
	 * 			Can be either ``AUTO`` or ``MANUAL``.
	**/
	public String getExDiskConfig();
	
	/**
	 * @return ex_admin_pass root password for the node
	 * 			(:type ex_admin_pass: ``str``)
	**/
	public String getExAdminPass();

}