package org.askalon.jlibcloud.compute.wrapperInterfaces;

import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;

/**
 * @author root
 *
 */
public interface NodeTemplate {

	/**
	 * @return driverName the tempates driver name
	 */
	public String getTemplateType();
	/**
	 * @return name
	 */
	public String getNodeName();
	/**
	 * @return size_id
	 */
	public String getSizeId();
	
	/**
	 * 
	 * @return NodeSize
	 */
	public NodeSize getSize();
	/**
	 * @return image_id
	 */
	public String getImageId();
	/**
	 * @return NodeImage
	 */
	public NodeImage getImage();
	
	/**
	 * 
	 * @return location_id
	 */
	public String getLocationId();
	
	/**
	 * 
	 * @return NodeLocation
	 */
	public NodeLocation getLocation();
	
	/**
	 * !No .properties file support for this param!
	 * @return auth_password
	 */
	public String getAuthPassword();
	
	/**
	 * !No .properties file support for this param!
	 * @return auth_sshKey
	 */
	public String getAuthSshKey();
	
	/**
	 * 
	 * @return ex_keyname
	 */
	public String getKeyPair();
	
	//deployment isn't yet supported
	//deployment getters
	
	
	/**
	 * 
	 * @return ssh_username
	 */
	//public String getSshUsername();
	
	/**
	 * 
	 * @return ssh_alternate_usernames
	 */
	//public List<String> getSshAlternateUsernames();
	
	/**
	 * 
	 * @return ssh_port
	 */
	//public Integer getSshPort();

	/**
	 * 
	 * @return ssh_timeout
	 */
	//public Float getSshTimeout();
	
	/**
	 * 
	 * @return ssh_key a path to a ssh key or null
	 */
	//public String getSshKey();
	
	/**
	 * 
	 * @returntimeout
	 */
	//public Integer getTimeout();
	
	/**
	 * 
	 * @return max_tries
	 */
	//public Integer getMaxTries();
	
	/**
	 * 
	 * @return ssh_interface
	 */
	//public String getSshInterface();
	
	/**
	 * 
	 * @return deployments a list of deployments
	 */
	//public List<Deployment> getDeploy();
	
}
