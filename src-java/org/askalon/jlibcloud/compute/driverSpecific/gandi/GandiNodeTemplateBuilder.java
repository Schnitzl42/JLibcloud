package org.askalon.jlibcloud.compute.driverSpecific.gandi;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;

public interface GandiNodeTemplateBuilder extends NodeTemplateBuilder{

	/**
	 * @param name String with a name for this new node (required)
	 * 			(:type name: ``str``)
	**/
	public GandiNodeTemplateBuilder name(String name);

	/**
	 * @param image OS Image to boot on node. (required)
	 * 			(:type image: :class:`NodeImage`)
	**/
	public GandiNodeTemplateBuilder image(NodeImage image);

	/**
	 * @param location Which data center to create a node in. If empty,
	 * 			undefined behavior will be selected. (optional)
	 * 			(:type location: :class:`NodeLocation`)
	**/
	public GandiNodeTemplateBuilder location(NodeLocation location);

	/**
	 * @param size The size of resources allocated to this node.
	 * 			(required)
	 * 			(:type size: :class:`NodeSize`)
	**/
	public GandiNodeTemplateBuilder size(NodeSize size);

	/**
	 * @param login user name to create for login on machine (required)
	 * 			(:type login: ``str``)
	**/
	public GandiNodeTemplateBuilder login(String login);

	/**
	 * @param password password for user that'll be created (required)
	 * 			(:type password: ``str``)
	**/
	public GandiNodeTemplateBuilder password(String password);

	/**
	 * @param inet_family version of ip to use, default 4 (optional)
	 * 			(:type inet_family: ``int``)
	**/
	public GandiNodeTemplateBuilder inetFamily(int inet_family);

}