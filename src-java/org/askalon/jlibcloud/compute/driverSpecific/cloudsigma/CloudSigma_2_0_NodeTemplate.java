package org.askalon.jlibcloud.compute.driverSpecific.cloudsigma;

import java.util.Map;

import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;

public interface CloudSigma_2_0_NodeTemplate extends NodeTemplate{

	/**
	**/
	public String getName();


	/**
	 * @return ex_metadata Key / value pairs to associate with the
	 * 			created node. (optional)
	 * 			(:type ex_metadata: ``dict``)
	**/
	public Map<String,Arg> getExMetadata();

	/**
	 * @return ex_vnc_password Password to use for VNC access. If not
	 * 			provided, random password is generated.
	 * 			(:type ex_vnc_password: ``str``)
	**/
	public String getExVncPassword();

	/**
	 * @return ex_avoid A list of server UUIDs to avoid when starting this
	 * 			node. (optional)
	 * 			(:type ex_avoid: ``list``)
	**/
	public String[] getExAvoid();

	/**
	 * @return ex_vlan Optional UUID of a VLAN network to use. If specified,
	 * 			server will have two nics assigned - 1 with a public ip
	 * 			and 1 with the provided VLAN.
	**/
	public String getExVlan();

}