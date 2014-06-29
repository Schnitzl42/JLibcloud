package org.askalon.jlibcloud.compute.driverSpecific.vpsnet;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;

public interface VPSNetNodeTemplate extends NodeTemplate{

	/**
	**/
	public String getName();

	/**
	**/
	public NodeImage getImage();

	/**
	**/
	public NodeSize getSize();

	/**
	 * @return ex_backups_enabled Enable automatic backups
	 * 			(:type ex_backups_enabled: ``bool``)
	**/
	public Boolean getExBackupsEnabled();

	/**
	 * @return ex_fqdn Fully Qualified domain of the node
	 * 			(:type ex_fqdn: ``str``)
	**/
	public String getExFqdn();

}