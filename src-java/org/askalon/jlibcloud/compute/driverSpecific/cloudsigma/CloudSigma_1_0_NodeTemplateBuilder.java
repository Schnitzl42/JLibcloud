package org.askalon.jlibcloud.compute.driverSpecific.cloudsigma;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;

public interface CloudSigma_1_0_NodeTemplateBuilder extends NodeTemplateBuilder{

	/**
	 * @param name String with a name for this new node (required)
	 * 			(:type name: ``str``)
	**/
	public CloudSigma_1_0_NodeTemplateBuilder name(String name);

	/**
	 * @param smp Number of virtual processors or None to calculate
	 * 			based on the cpu speed
	 * 			(:type smp: ``int``)
	**/
	public CloudSigma_1_0_NodeTemplateBuilder smp(int smp);

	/**
	 * @param nic_model e1000, rtl8139 or virtio (is not specified,
	 * 			e1000 is used)
	 * 			(:type nic_model: ``str``)
	**/
	public CloudSigma_1_0_NodeTemplateBuilder nicModel(String nic_model);

	/**
	 * @param vnc_password If not set, VNC access is disabled.
	 * 			(:type vnc_password: ``bool``)
	**/
	public CloudSigma_1_0_NodeTemplateBuilder vncPassword(boolean vnc_password);

	/**
	 * @param drive_type Drive type (ssd|hdd). Defaults to hdd.
	**/
	public CloudSigma_1_0_NodeTemplateBuilder driveType(String drive_type);

}