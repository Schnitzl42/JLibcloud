package org.askalon.jlibcloud.compute.driverSpecific.openstack;

import java.util.List;
import java.util.Map;

import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;

public interface OpenStack_1_1_NodeTemplateBuilder extends NodeTemplateBuilder{
	
	/**
	 * @param ex_keyname The name of the key pair
	 * 			(:type ex_keyname: ``str``)
	**/
	public OpenStack_1_1_NodeTemplateBuilder exKeyname(String ex_keyname);

	/**
	 * @param ex_userdata String containing user data
	 * 			see
	 * 			https://help.ubuntu.com/community/CloudInit
	 * 			(:type ex_userdata: ``str``)
	**/
	public OpenStack_1_1_NodeTemplateBuilder exUserdata(String ex_userdata);

	/**
	 * @param ex_security_groups List of security groups to assign to
	 * 			the node
	 * 			(:type ex_security_groups: ``list`` of :class:`OpenStackSecurityGroup`)
	**/
	public OpenStack_1_1_NodeTemplateBuilder exSecurityGroups(List<OpenStackSecurityGroup> ex_security_groups);

	/**
	 * @param ex_metadata Key/Value metadata to associate with a node
	 * 			(:type ex_metadata: ``dict``)
	**/
	public OpenStack_1_1_NodeTemplateBuilder exMetadata(Map<String,Arg> ex_metadata);

	/**
	 * @param ex_files File Path => File contents to create on
	 * 			the no de
	 * 			(:type ex_files: ``dict``)
	**/
	public OpenStack_1_1_NodeTemplateBuilder exFiles(Map<String,Arg> ex_files);

	/**
	 * @param networks The server is launched into a set of Networks.
	 * 			(:type networks: :class:`OpenStackNetwork`)
	**/
	public OpenStack_1_1_NodeTemplateBuilder networks(OpenStackNetwork networks);

	/**
	 * @param ex_disk_config Name of the disk configuration.
	 * 			Can be either ``AUTO`` or ``MANUAL``.
	**/
	public OpenStack_1_1_NodeTemplateBuilder exDiskConfig(String ex_disk_config);
	
	/**
	 * @param ex_admin_pass root password for the node
	 * 			(:type ex_admin_pass: ``str``)
	**/
	public OpenStack_1_1_NodeTemplateBuilder exAdminPass(String ex_admin_pass);


}