package org.askalon.jlibcloud.compute.driverSpecific.rimuhosting;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;

public interface RimuHostingNodeTemplate extends NodeTemplate{

	/**
	 * @return name Must be a FQDN. e.g example.com.
	 * 			(:type name: ``str``)
	**/
	public String getName();

	/**
	 * @return ex_billing_oid If not set,
	 * 			a billing method is automatically picked.
	 * 			(:type ex_billing_oid: ``str``)
	**/
	public String getExBillingOid();

	/**
	 * @return ex_host_server_oid The host server to set the VPS up on.
	 * 			(:type ex_host_server_oid: ``str``)
	**/
	public String getExHostServerOid();

	/**
	 * @return ex_vps_order_oid_to_clone Clone another VPS to use as
	 * 			the image for the new VPS.
	 * 			(:type ex_vps_order_oid_to_clone: ``str``)
	**/
	public String getExVpsOrderOidToClone();

	/**
	 * @return ex_num_ips Number of IPs to allocate. Defaults to 1.
	 * 			(:type ex_num_ips: ``int``)
	**/
	public Integer getExNumIps();

	/**
	 * @return ex_extra_ip_reason Reason for needing the extra IPs.
	 * 			(:type ex_extra_ip_reason: ``str``)
	**/
	public String getExExtraIpReason();

	/**
	 * @return ex_memory_mb Memory to allocate to the VPS.
	 * 			(:type ex_memory_mb: ``int``)
	**/
	public Integer getExMemoryMb();

	/**
	 * @return ex_disk_space_mb Diskspace to allocate to the VPS.
	 * 			Defaults to 4096 (4GB).
	 * 			(:type ex_disk_space_mb: ``int``)
	**/
	public Integer getExDiskSpaceMb();

	/**
	 * @return ex_disk_space_2_mb Secondary disk size allocation.
	 * 			Disabled by default.
	 * 			(:type ex_disk_space_2_mb: ``int``)
	**/
	public Integer getExDiskSpace2Mb();

	/**
	 * @return ex_control_panel Control panel to install on the VPS.
	 * 			(:type ex_control_panel: ``str``)
	**/
	public String getExControlPanel();

}