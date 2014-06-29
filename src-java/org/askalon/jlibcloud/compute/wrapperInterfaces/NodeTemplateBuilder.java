package org.askalon.jlibcloud.compute.wrapperInterfaces;

import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;

public interface NodeTemplateBuilder {

	/**
	 * 
	 * @param name
	 */
	public NodeTemplateBuilder nodeName(String name);
	
	/**
	 * 
	 * @param size_id
	 */
	public NodeTemplateBuilder sizeId(String size_id);
	
	/**
	 * 
	 * @param size
	 */
	public NodeTemplateBuilder size(NodeSize size);
	
	/**
	 * 
	 * @param image_id
	 */
	public NodeTemplateBuilder imageId(String image_id);
	
	/**
	 * 
	 * @param image
	 */
	public NodeTemplateBuilder image(NodeImage image);
	
	/**
	 * 
	 * @param location_id
	 */
	public NodeTemplateBuilder locationId(String location_id);
	
	/**
	 * 
	 * @param location
	 */
	public NodeTemplateBuilder location(NodeLocation location);
	
	/**
	 * !No .properties file support for this param!
	 * @param password
	 */
	public NodeTemplateBuilder authPassword(String password);
	
	/**
	 * !No .properties file support for this param!
	 * @param sshKey
	 */
	public NodeTemplateBuilder authSshKey(String sshKey);
	
	/**
	 * 
	 * @param ex_keyname
	 */
	public NodeTemplateBuilder keyPair(String ex_keyname);
	
	//DEPLOYMENT METHODS
	//deployment isn't yet supported
	
	/**
	 * 
	 * @param ssh_username
	 */
	//public NodeTemplateBuilder sshUsername(String ssh_username);
	
	/**
	 * 
	 * @param ssh_alternate_usernames
	 */
	//public NodeTemplateBuilder sshAlternateUsernames(List<String> ssh_alternate_usernames);
	
	/**
	 * 
	 * @param ssh_port
	 */
	//public NodeTemplateBuilder sshPort(int ssh_port);
	
	/**
	 * 
	 * @param ssh_timeout
	 */
	//public NodeTemplateBuilder sshTimeout(float ssh_timeout);
	
	/**
	 * 
	 * @param ssh_key - a path to a valid ssh key
	 */
	//public NodeTemplateBuilder sshKey(String ssh_key);
	
	/**
	 * 
	 * @param timeout
	 */
	//public NodeTemplateBuilder timeout(int timeout);
	
	/**
	 * 
	 * @param maxTries
	 */
	//public NodeTemplateBuilder maxTries(int maxTries);
	
	/**
	 * 
	 * @param sshInterface
	 */
	//public NodeTemplateBuilder sshInterface(String sshInterface);
	
	/**
	 * to deploy multiple deploy steps call nt.deploy(step1).deploy(step2)
	 * @param deploy
	 */
	//public NodeTemplateBuilder deploy(Deployment deploy);
	
	/**
	 * cast this node template builder to another supported builder class.
	 * @param klass
	 */
	public <T extends NodeTemplateBuilder> T as(Class<T> klass);
	
	/**
	 * build the template
	 * @return nodeTemplate
	 */
	public NodeTemplate build();
}
