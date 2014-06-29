package org.askalon.jlibcloud.compute.driverSpecific.ec2;

import java.util.List;
import java.util.Map;

import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;

public interface BaseEC2NodeTemplate extends NodeTemplate{

	/**
	 * @return ex_keyname The name of the key pair
	 * 			(:type ex_keyname: ``str``)
	**/
	public String getExKeyname();

	/**
	 * @return ex_userdata User data
	 * 			(:type ex_userdata: ``str``)
	**/
	public String getExUserdata();

	/**
	 * @return ex_security_groups A list of names of security groups to
	 * 			assign to the node.
	 * 			(:type ex_security_groups: ``list``)
	**/
	public String[] getExSecurityGroups();

	/**
	 * @return ex_metadata Key/Value metadata to associate with a node
	 * 			(:type ex_metadata: ``dict``)
	**/
	public Map<String,Arg> getExMetadata();

	/**
	 * @return ex_mincount Minimum number of instances to launch
	 * 			(:type ex_mincount: ``int``)
	**/
	public Integer getExMincount();

	/**
	 * @return ex_maxcount Maximum number of instances to launch
	 * 			(:type ex_maxcount: ``int``)
	**/
	public Integer getExMaxcount();

	/**
	 * @return ex_clienttoken Unique identifier to ensure idempotency
	 * 			(:type ex_clienttoken: ``str``)
	**/
	public String getExClienttoken();

	/**
	 * @return ex_blockdevicemappings ``list`` of ``dict`` block device
	 * 			mappings.
	 * 			(:type ex_blockdevicemappings: ``list`` of ``dict``)
	**/
	public List<Map<String,Arg>> getExBlockdevicemappings();

	/**
	 * @return ex_iamprofile Name or ARN of IAM profile
	 * 			(:type ex_iamprofile: ``str``)
	**/
	public String getExIamprofile();

}