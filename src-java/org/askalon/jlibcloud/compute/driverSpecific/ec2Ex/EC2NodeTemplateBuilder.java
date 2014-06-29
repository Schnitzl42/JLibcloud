package org.askalon.jlibcloud.compute.driverSpecific.ec2Ex;

import java.util.List;
import java.util.Map;

import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;

public interface EC2NodeTemplateBuilder extends NodeTemplateBuilder {

	/**
	 * 
	 * @param spotPrice
	 *            if a spot price is set, a spot instance request will be sent
	 */
	public EC2NodeTemplateBuilder exSpotPrice(float spotPrice);

	/**
	 * 
	 * @param spotInstances
	 *            the number of spot instances to create
	 */
	public EC2NodeTemplateBuilder exSpotInstances(int spotInstances);
	
	/**
	 * @param exKeyname The name of the key pair
	 * 			(:type exKeyname: ``str``)
	**/
	public EC2NodeTemplateBuilder exKeyname(String exKeyname);

	/**
	 * @param exUserdata User data
	 * 			(:type exUserdata: ``str``)
	**/
	public EC2NodeTemplateBuilder exUserdata(String exUserdata);

	/**
	 * @param exSecurityGroups A list of names of security groups to
	 * 			assign to the node.
	 * 			(:type exSecurityGroups: ``list``)
	**/
	public EC2NodeTemplateBuilder exSecurityGroups(String... exSecurityGroups);

	/**
	 * @param exMetadata Key/Value metadata to associate with a node
	 * 			(:type exMetadata: ``dict``)
	**/
	public EC2NodeTemplateBuilder exMetadata(Map<String,Arg> exMetadata);

	/**
	 * @param exMincount Minimum number of instances to launch
	 * 			(:type exMincount: ``int``)
	**/
	public EC2NodeTemplateBuilder exMincount(int exMincount);

	/**
	 * @param exMaxcount Maximum number of instances to launch
	 * 			(:type exMaxcount: ``int``)
	**/
	public EC2NodeTemplateBuilder exMaxcount(int exMaxcount);

	/**
	 * @param exClienttoken Unique identifier to ensure idempotency
	 * 			(:type exClienttoken: ``str``)
	**/
	public EC2NodeTemplateBuilder exClienttoken(String exClienttoken);

	/**
	 * @param exBlockdevicemappings ``list`` of ``dict`` block device
	 * 			mappings.
	 * 			(:type exBlockdevicemappings: ``list`` of ``dict``)
	**/
	public EC2NodeTemplateBuilder exBlockdevicemappings(List<Map<String,Arg>> exBlockdevicemappings);

	/**
	 * @param exIamprofile Name or ARN of IAM profile
	 * 			(:type exIamprofile: ``str``)
	**/
	public EC2NodeTemplateBuilder exIamprofile(String exIamprofile);
}