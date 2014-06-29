package org.askalon.jlibcloud.compute.driverSpecific.vcloud;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;

public interface VCloud_1_5_NodeTemplate extends NodeTemplate{

	/**
	 * @return image OS Image to boot on node. (required). Can be a
	 * 			NodeImage or existing Node that will be cloned.
	 * 			(:type image: :class:`NodeImage` or :class:`Node`)
	**/
	public NodeImage getImage();

	/**
	 * @return ex_network Organisation's network name for attaching vApp
	 * 			VMs to.
	 * 			(:type ex_network: ``str``)
	**/
	public String getExNetwork();

	/**
	 * @return ex_vdc Name of organisation's virtual data center where
	 * 			vApp VMs will be deployed.
	 * 			(:type ex_vdc: ``str``)
	**/
	public String getExVdc();

	/**
	 * @return ex_vm_names list of names to be used as a VM and computer
	 * 			name. The name must be max. 15 characters
	 * 			long and follow the host name requirements.
	 * 			(:type ex_vm_names: ``list`` of ``str``)
	**/
	public String[] getExVmNames();

	/**
	 * @return ex_vm_cpu number of virtual CPUs/cores to allocate for
	 * 			each vApp VM.
	 * 			(:type ex_vm_cpu: ``int``)
	**/
	public Integer getExVmCpu();

	/**
	 * @return ex_vm_memory amount of memory in MB to allocate for each
	 * 			vApp VM.
	 * 			(:type ex_vm_memory: ``int``)
	**/
	public Integer getExVmMemory();

	/**
	 * @return ex_vm_script full path to file containing guest
	 * 			customisation script for each vApp VM.
	 * 			Useful for creating users & pushing out
	 * 			public SSH keys etc.
	 * 			(:type ex_vm_script: ``str``)
	**/
	public String getExVmScript();

	/**
	 * @return ex_vm_network Override default vApp VM network name.
	 * 			Useful for when you've imported an OVF
	 * 			originating from outside of the vCloud.
	 * 			(:type ex_vm_network: ``str``)
	**/
	public String getExVmNetwork();

	/**
	 * @return ex_vm_fence Fence mode for connecting the vApp VM network
	 * 			(ex_vm_network) to the parent
	 * 			organisation network (ex_network).
	 * 			(:type ex_vm_fence: ``str``)
	**/
	public String getExVmFence();

	/**
	 * @return ex_vm_ipmode IP address allocation mode for all vApp VM
	 * 			network connections.
	 * 			(:type ex_vm_ipmode: ``str``)
	**/
	public String getExVmIpmode();

	/**
	 * @return ex_deploy set to False if the node shouldn't be deployed
	 * 			(started) after creation
	 * 			(:type ex_deploy: ``bool``)
	**/
	public Boolean getExDeploy();

	/**
	 * @return ex_clone_timeout timeout in seconds for clone/instantiate
	 * 			VM operation.
	 * 			Cloning might be a time consuming
	 * 			operation especially when linked clones
	 * 			are disabled or VMs are created on
	 * 			different datastores.
	 * 			Overrides the default task completion
	 * 			value.
	 * 			(:type ex_clone_timeout: ``int``)
	**/
	public Integer getExCloneTimeout();

}