package org.askalon.jlibcloud.compute.driverSpecific.openstack;

import java.util.Map;

import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;

public interface OpenStack_1_0_NodeTemplate extends NodeTemplate{

	/**
	 * @return ex_metadata Key/Value metadata to associate with a node
	 * 			(:type ex_metadata: ``dict``)
	**/
	public Map<String,Arg> getExMetadata();

	/**
	 * @return ex_files File Path => File contents to create on
	 * 			the node
	 * 			(:type ex_files: ``dict``)
	**/
	public Map<String,Arg> getExFiles();

	/**
	 * @return ex_shared_ip_group_id The server is launched into
	 * 			that shared IP group
	**/
	public String getExSharedIpGroupId();

}