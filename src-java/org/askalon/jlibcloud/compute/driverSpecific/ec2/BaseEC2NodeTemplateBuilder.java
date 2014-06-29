package org.askalon.jlibcloud.compute.driverSpecific.ec2;

import java.util.List;
import java.util.Map;

import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;

public interface BaseEC2NodeTemplateBuilder extends NodeTemplateBuilder{

	/**
	 * @param ex_keyname The name of the key pair
	 * 			(:type exKeyname: ``str``)
	**/
	public BaseEC2NodeTemplateBuilder exKeyname(String ex_keyname);

	/**
	 * @param ex_userdata User data
	 * 			(:type exUserdata: ``str``)
	**/
	public BaseEC2NodeTemplateBuilder exUserdata(String ex_userdata);

	/**
	 * @param ex_security_groups A list of names of security groups to
	 * 			assign to the node.
	 * 			(:type exSecurityGroups: ``list``)
	**/
	public BaseEC2NodeTemplateBuilder exSecurityGroups(String... ex_security_groups);

	/**
	 * @param ex_metadata Key/Value metadata to associate with a node
	 * 			(:type exMetadata: ``dict``)
	**/
	public BaseEC2NodeTemplateBuilder exMetadata(Map<String,Arg> ex_metadata);

	/**
	 * @param ex_mincount Minimum number of instances to launch
	 * 			(:type exMincount: ``int``)
	**/
	public BaseEC2NodeTemplateBuilder exMincount(int ex_mincount);

	/**
	 * @param ex_maxcount Maximum number of instances to launch
	 * 			(:type exMaxcount: ``int``)
	**/
	public BaseEC2NodeTemplateBuilder exMaxcount(int ex_maxcount);

	/**
	 * @param ex_clienttoken Unique identifier to ensure idempotency
	 * 			(:type exClienttoken: ``str``)
	**/
	public BaseEC2NodeTemplateBuilder exClienttoken(String ex_clienttoken);

	/**
	 * @param ex_blockdevicemappings ``list`` of ``dict`` block device
	 * 			mappings.
	 * 			(:type exBlockdevicemappings: ``list`` of ``dict``)
	**/
	public BaseEC2NodeTemplateBuilder exBlockdevicemappings(List<Map<String,Arg>> ex_blockdevicemappings);

	/**
	 * @param ex_iamprofile Name or ARN of IAM profile
	 * 			(:type exIamprofile: ``str``)
	**/
	public BaseEC2NodeTemplateBuilder exIamprofile(String ex_iamprofile);

}