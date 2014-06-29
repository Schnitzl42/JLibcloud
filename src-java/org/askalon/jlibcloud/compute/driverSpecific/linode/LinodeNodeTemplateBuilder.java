package org.askalon.jlibcloud.compute.driverSpecific.linode;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;

public interface LinodeNodeTemplateBuilder extends NodeTemplateBuilder{

	/**
	 * @param name the name to assign the Linode (mandatory)
	 * 			(:type name: ``str``)
	**/
	public LinodeNodeTemplateBuilder name(String name);

	/**
	 * @param image which distribution to deploy on the Linode (mandatory)
	 * 			(:type image: :class:`NodeImage`)
	**/
	public LinodeNodeTemplateBuilder image(NodeImage image);

	/**
	 * @param size the plan size to create (mandatory)
	 * 			(:type size: :class:`NodeSize`)
	**/
	public LinodeNodeTemplateBuilder size(NodeSize size);

	/**
	 * @param location which datacenter to create the Linode in
	 * 			(:type location: :class:`NodeLocation`)
	**/
	public LinodeNodeTemplateBuilder location(NodeLocation location);

	/**
	 * @param ex_swap size of the swap partition in MB (128)
	 * 			(:type ex_swap: ``int``)
	**/
	public LinodeNodeTemplateBuilder exSwap(int ex_swap);

	/**
	 * @param ex_rsize size of the root partition in MB (plan size - swap).
	 * 			(:type ex_rsize: ``int``)
	**/
	public LinodeNodeTemplateBuilder exRsize(int ex_rsize);

	/**
	 * @param ex_kernel a kernel ID from avail.kernels (Latest 2.6 Stable).
	 * 			(:type ex_kernel: ``str``)
	**/
	public LinodeNodeTemplateBuilder exKernel(String ex_kernel);

	/**
	 * @param ex_payment one of 1, 12, or 24; subscription length (1)
	 * 			(:type ex_payment: ``int``)
	**/
	public LinodeNodeTemplateBuilder exPayment(int ex_payment);

	/**
	 * @param ex_comment a small comment for the configuration (libcloud)
	 * 			(:type ex_comment: ``str``)
	**/
	public LinodeNodeTemplateBuilder exComment(String ex_comment);

	/**
	 * @param ex_private whether or not to request a private IP (False)
	 * 			(:type ex_private: ``bool``)
	**/
	public LinodeNodeTemplateBuilder exPrivate(boolean ex_private);

	/**
	 * @param lconfig what to call the configuration (generated)
	 * 			(:type lconfig: ``str``)
	**/
	public LinodeNodeTemplateBuilder lconfig(String lconfig);

	/**
	 * @param lroot what to call the root image (generated)
	 * 			(:type lroot: ``str``)
	**/
	public LinodeNodeTemplateBuilder lroot(String lroot);

	/**
	 * @param lswap what to call the swap space (generated)
	 * 			(:type lswap: ``str``)
	**/
	public LinodeNodeTemplateBuilder lswap(String lswap);

}