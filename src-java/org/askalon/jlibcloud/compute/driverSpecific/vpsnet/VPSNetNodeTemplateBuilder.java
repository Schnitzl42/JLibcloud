package org.askalon.jlibcloud.compute.driverSpecific.vpsnet;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;

public interface VPSNetNodeTemplateBuilder extends NodeTemplateBuilder{

	/**
	**/
	public VPSNetNodeTemplateBuilder name(String name);

	/**
	**/
	public VPSNetNodeTemplateBuilder image(NodeImage image);

	/**
	**/
	public VPSNetNodeTemplateBuilder size(NodeSize size);

	/**
	 * @param ex_backups_enabled Enable automatic backups
	 * 			(:type ex_backups_enabled: ``bool``)
	**/
	public VPSNetNodeTemplateBuilder exBackupsEnabled(boolean ex_backups_enabled);

	/**
	 * @param ex_fqdn Fully Qualified domain of the node
	 * 			(:type ex_fqdn: ``str``)
	**/
	public VPSNetNodeTemplateBuilder exFqdn(String ex_fqdn);

}