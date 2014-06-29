package org.askalon.jlibcloud.compute.driverSpecific.voxel;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;

public interface VoxelNodeTemplate extends NodeTemplate{

	/**
	 * @return name the name to assign the node (mandatory)
	 * 			(:type name: ``str``)
	**/
	public String getName();

	/**
	 * @return image distribution to deploy
	 * 			(:type image: :class:`NodeImage`)
	**/
	public NodeImage getImage();

	/**
	 * @return size the plan size to create (mandatory)
	 * 			Requires size.disk (GB) to be set manually
	 * 			(:type size: :class:`NodeSize`)
	**/
	public NodeSize getSize();

	/**
	 * @return location which datacenter to create the node in
	 * 			(:type location: :class:`NodeLocation`)
	**/
	public NodeLocation getLocation();

	/**
	 * @return ex_privateip Backend IP address to assign to node;
	 * 			must be chosen from the customer's
	 * 			private VLAN assignment.
	 * 			(:type ex_privateip: ``str``)
	**/
	public String getExPrivateip();

	/**
	 * @return ex_publicip Public-facing IP address to assign to node;
	 * 			must be chosen from the customer's
	 * 			public VLAN assignment.
	 * 			(:type ex_publicip: ``str``)
	**/
	public String getExPublicip();

	/**
	 * @return ex_rootpass Password for root access; generated if unset.
	 * 			(:type ex_rootpass: ``str``)
	**/
	public String getExRootpass();

	/**
	 * @return ex_consolepass Password for remote console;
	 * 			generated if unset.
	 * 			(:type ex_consolepass: ``str``)
	**/
	public String getExConsolepass();

	/**
	 * @return ex_sshuser Username for SSH access
	 * 			(:type ex_sshuser: ``str``)
	**/
	public String getExSshuser();

	/**
	 * @return ex_sshpass Password for SSH access; generated if unset.
	 * 			(:type ex_sshpass: ``str``)
	**/
	public String getExSshpass();

	/**
	 * @return ex_voxel_access Allow access Voxel administrative access.
	 * 			Defaults to False.
	 * 			(:type ex_voxel_access: ``bool``)
	**/
	public Boolean getExVoxelAccess();

}