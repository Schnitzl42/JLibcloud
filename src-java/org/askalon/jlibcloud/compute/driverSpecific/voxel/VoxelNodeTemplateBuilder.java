package org.askalon.jlibcloud.compute.driverSpecific.voxel;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;

public interface VoxelNodeTemplateBuilder extends NodeTemplateBuilder{

	/**
	 * @param name the name to assign the node (mandatory)
	 * 			(:type name: ``str``)
	**/
	public VoxelNodeTemplateBuilder name(String name);

	/**
	 * @param image distribution to deploy
	 * 			(:type image: :class:`NodeImage`)
	**/
	public VoxelNodeTemplateBuilder image(NodeImage image);

	/**
	 * @param size the plan size to create (mandatory)
	 * 			Requires size.disk (GB) to be set manually
	 * 			(:type size: :class:`NodeSize`)
	**/
	public VoxelNodeTemplateBuilder size(NodeSize size);

	/**
	 * @param location which datacenter to create the node in
	 * 			(:type location: :class:`NodeLocation`)
	**/
	public VoxelNodeTemplateBuilder location(NodeLocation location);

	/**
	 * @param ex_privateip Backend IP address to assign to node;
	 * 			must be chosen from the customer's
	 * 			private VLAN assignment.
	 * 			(:type ex_privateip: ``str``)
	**/
	public VoxelNodeTemplateBuilder exPrivateip(String ex_privateip);

	/**
	 * @param ex_publicip Public-facing IP address to assign to node;
	 * 			must be chosen from the customer's
	 * 			public VLAN assignment.
	 * 			(:type ex_publicip: ``str``)
	**/
	public VoxelNodeTemplateBuilder exPublicip(String ex_publicip);

	/**
	 * @param ex_rootpass Password for root access; generated if unset.
	 * 			(:type ex_rootpass: ``str``)
	**/
	public VoxelNodeTemplateBuilder exRootpass(String ex_rootpass);

	/**
	 * @param ex_consolepass Password for remote console;
	 * 			generated if unset.
	 * 			(:type ex_consolepass: ``str``)
	**/
	public VoxelNodeTemplateBuilder exConsolepass(String ex_consolepass);

	/**
	 * @param ex_sshuser Username for SSH access
	 * 			(:type ex_sshuser: ``str``)
	**/
	public VoxelNodeTemplateBuilder exSshuser(String ex_sshuser);

	/**
	 * @param ex_sshpass Password for SSH access; generated if unset.
	 * 			(:type ex_sshpass: ``str``)
	**/
	public VoxelNodeTemplateBuilder exSshpass(String ex_sshpass);

	/**
	 * @param ex_voxel_access Allow access Voxel administrative access.
	 * 			Defaults to False.
	 * 			(:type ex_voxel_access: ``bool``)
	**/
	public VoxelNodeTemplateBuilder exVoxelAccess(boolean ex_voxel_access);

}