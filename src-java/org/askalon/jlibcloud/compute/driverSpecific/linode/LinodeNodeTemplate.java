package org.askalon.jlibcloud.compute.driverSpecific.linode;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;

public interface LinodeNodeTemplate extends NodeTemplate{

	/**
	 * @return name the name to assign the Linode (mandatory)
	 * 			(:type name: ``str``)
	**/
	public String getName();

	/**
	 * @return image which distribution to deploy on the Linode (mandatory)
	 * 			(:type image: :class:`NodeImage`)
	**/
	public NodeImage getImage();

	/**
	 * @return size the plan size to create (mandatory)
	 * 			(:type size: :class:`NodeSize`)
	**/
	public NodeSize getSize();


	/**
	 * @return location which datacenter to create the Linode in
	 * 			(:type location: :class:`NodeLocation`)
	**/
	public NodeLocation getLocation();

	/**
	 * @return ex_swap size of the swap partition in MB (128)
	 * 			(:type ex_swap: ``int``)
	**/
	public Integer getExSwap();

	/**
	 * @return ex_rsize size of the root partition in MB (plan size - swap).
	 * 			(:type ex_rsize: ``int``)
	**/
	public Integer getExRsize();

	/**
	 * @return ex_kernel a kernel ID from avail.kernels (Latest 2.6 Stable).
	 * 			(:type ex_kernel: ``str``)
	**/
	public String getExKernel();

	/**
	 * @return ex_payment one of 1, 12, or 24; subscription length (1)
	 * 			(:type ex_payment: ``int``)
	**/
	public Integer getExPayment();

	/**
	 * @return ex_comment a small comment for the configuration (libcloud)
	 * 			(:type ex_comment: ``str``)
	**/
	public String getExComment();

	/**
	 * @return ex_private whether or not to request a private IP (False)
	 * 			(:type ex_private: ``bool``)
	**/
	public Boolean getExPrivate();

	/**
	 * @return lconfig what to call the configuration (generated)
	 * 			(:type lconfig: ``str``)
	**/
	public String getLconfig();

	/**
	 * @return lroot what to call the root image (generated)
	 * 			(:type lroot: ``str``)
	**/
	public String getLroot();

	/**
	 * @return lswap what to call the swap space (generated)
	 * 			(:type lswap: ``str``)
	**/
	public String getLswap();

}