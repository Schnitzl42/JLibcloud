package org.askalon.jlibcloud.compute.driverSpecific.cloudsigma;

import java.util.Map;

import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;

public interface CloudSigma_2_0_NodeTemplateBuilder extends NodeTemplateBuilder{

	/**
	**/
	public CloudSigma_2_0_NodeTemplateBuilder name(String name);

	/**
	 * @param ex_metadata Key / value pairs to associate with the
	 * 			created node. (optional)
	 * 			(:type ex_metadata: ``dict``)
	**/
	public CloudSigma_2_0_NodeTemplateBuilder exMetadata(Map<String,Arg> ex_metadata);

	/**
	 * @param ex_vnc_password Password to use for VNC access. If not
	 * 			provided, random password is generated.
	 * 			(:type ex_vnc_password: ``str``)
	**/
	public CloudSigma_2_0_NodeTemplateBuilder exVncPassword(String ex_vnc_password);

	/**
	 * @param ex_avoid A list of server UUIDs to avoid when starting this
	 * 			node. (optional)
	 * 			(:type ex_avoid: ``list``)
	**/
	public CloudSigma_2_0_NodeTemplateBuilder exAvoid(String... ex_avoid);

	/**
	 * @param ex_vlan Optional UUID of a VLAN network to use. If specified,
	 * 			server will have two nics assigned - 1 with a public ip
	 * 			and 1 with the provided VLAN.
	**/
	public CloudSigma_2_0_NodeTemplateBuilder exVlan(String ex_vlan);

}