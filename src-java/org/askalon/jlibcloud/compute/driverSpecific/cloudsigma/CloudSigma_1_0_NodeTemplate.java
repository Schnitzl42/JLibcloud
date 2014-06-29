package org.askalon.jlibcloud.compute.driverSpecific.cloudsigma;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;

public interface CloudSigma_1_0_NodeTemplate extends NodeTemplate{

	/**
	 * @return name String with a name for this new node (required)
	 * 			(:type name: ``str``)
	**/
	public String getName();

	/**
	 * @return smp Number of virtual processors or None to calculate
	 * 			based on the cpu speed
	 * 			(:type smp: ``int``)
	**/
	public Integer getSmp();

	/**
	 * @return nic_model e1000, rtl8139 or virtio (is not specified,
	 * 			e1000 is used)
	 * 			(:type nic_model: ``str``)
	**/
	public String getNicModel();

	/**
	 * @return vnc_password If not set, VNC access is disabled.
	 * 			(:type vnc_password: ``bool``)
	**/
	public Boolean getVncPassword();

	/**
	 * @return drive_type Drive type (ssd|hdd). Defaults to hdd.
	**/
	public String getDriveType();

}