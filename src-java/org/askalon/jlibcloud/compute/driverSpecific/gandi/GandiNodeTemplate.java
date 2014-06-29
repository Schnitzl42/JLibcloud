package org.askalon.jlibcloud.compute.driverSpecific.gandi;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;

public interface GandiNodeTemplate extends NodeTemplate{

	/**
	 * @return name String with a name for this new node (required)
	 * 			(:type name: ``str``)
	**/
	public String getName();

	/**
	 * @return image OS Image to boot on node. (required)
	 * 			(:type image: :class:`NodeImage`)
	**/
	public NodeImage getImage();

	/**
	 * @return location Which data center to create a node in. If empty,
	 * 			undefined behavior will be selected. (optional)
	 * 			(:type location: :class:`NodeLocation`)
	**/
	public NodeLocation getLocation();

	/**
	 * @return size The size of resources allocated to this node.
	 * 			(required)
	 * 			(:type size: :class:`NodeSize`)
	**/
	public NodeSize getSize();

	/**
	 * @return login user name to create for login on machine (required)
	 * 			(:type login: ``str``)
	**/
	public String getLogin();

	/**
	 * @return password password for user that'll be created (required)
	 * 			(:type password: ``str``)
	**/
	public String getPassword();

	/**
	 * @return inet_family version of ip to use, default 4 (optional)
	 * 			(:type inet_family: ``int``)
	**/
	public Integer getInetFamily();

}