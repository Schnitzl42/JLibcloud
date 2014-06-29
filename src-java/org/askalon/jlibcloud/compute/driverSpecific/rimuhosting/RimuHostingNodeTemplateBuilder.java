package org.askalon.jlibcloud.compute.driverSpecific.rimuhosting;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;

public interface RimuHostingNodeTemplateBuilder extends NodeTemplateBuilder{

	/**
	 * @param name Must be a FQDN. e.g example.com.
	 * 			(:type name: ``str``)
	**/
	public RimuHostingNodeTemplateBuilder name(String name);

	/**
	 * @param ex_billing_oid If not set,
	 * 			a billing method is automatically picked.
	 * 			(:type ex_billing_oid: ``str``)
	**/
	public RimuHostingNodeTemplateBuilder exBillingOid(String ex_billing_oid);

	/**
	 * @param ex_host_server_oid The host server to set the VPS up on.
	 * 			(:type ex_host_server_oid: ``str``)
	**/
	public RimuHostingNodeTemplateBuilder exHostServerOid(String ex_host_server_oid);

	/**
	 * @param ex_vps_order_oid_to_clone Clone another VPS to use as
	 * 			the image for the new VPS.
	 * 			(:type ex_vps_order_oid_to_clone: ``str``)
	**/
	public RimuHostingNodeTemplateBuilder exVpsOrderOidToClone(String ex_vps_order_oid_to_clone);

	/**
	 * @param ex_num_ips Number of IPs to allocate. Defaults to 1.
	 * 			(:type ex_num_ips: ``int``)
	**/
	public RimuHostingNodeTemplateBuilder exNumIps(int ex_num_ips);

	/**
	 * @param ex_extra_ip_reason Reason for needing the extra IPs.
	 * 			(:type ex_extra_ip_reason: ``str``)
	**/
	public RimuHostingNodeTemplateBuilder exExtraIpReason(String ex_extra_ip_reason);

	/**
	 * @param ex_memory_mb Memory to allocate to the VPS.
	 * 			(:type ex_memory_mb: ``int``)
	**/
	public RimuHostingNodeTemplateBuilder exMemoryMb(int ex_memory_mb);

	/**
	 * @param ex_disk_space_mb Diskspace to allocate to the VPS.
	 * 			Defaults to 4096 (4GB).
	 * 			(:type ex_disk_space_mb: ``int``)
	**/
	public RimuHostingNodeTemplateBuilder exDiskSpaceMb(int ex_disk_space_mb);

	/**
	 * @param ex_disk_space_2_mb Secondary disk size allocation.
	 * 			Disabled by default.
	 * 			(:type ex_disk_space_2_mb: ``int``)
	**/
	public RimuHostingNodeTemplateBuilder exDiskSpace2Mb(int ex_disk_space_2_mb);

	/**
	 * @param ex_control_panel Control panel to install on the VPS.
	 * 			(:type ex_control_panel: ``str``)
	**/
	public RimuHostingNodeTemplateBuilder exControlPanel(String ex_control_panel);

}