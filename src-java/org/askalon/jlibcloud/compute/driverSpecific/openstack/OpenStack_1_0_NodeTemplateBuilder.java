package org.askalon.jlibcloud.compute.driverSpecific.openstack;

import java.util.Map;

import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;

public interface OpenStack_1_0_NodeTemplateBuilder extends NodeTemplateBuilder{

	/**
	 * @param ex_metadata Key/Value metadata to associate with a node
	 * 			(:type ex_metadata: ``dict``)
	**/
	public OpenStack_1_0_NodeTemplateBuilder exMetadata(Map<String,Arg> ex_metadata);

	/**
	 * @param ex_files File Path => File contents to create on
	 * 			the node
	 * 			(:type ex_files: ``dict``)
	**/
	public OpenStack_1_0_NodeTemplateBuilder exFiles(Map<String,Arg> ex_files);

	/**
	 * @param ex_shared_ip_group_id The server is launched into
	 * 			that shared IP group
	**/
	public OpenStack_1_0_NodeTemplateBuilder exSharedIpGroupId(String ex_shared_ip_group_id);

}