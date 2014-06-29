package org.askalon.jlibcloud.compute.driverSpecific.elasticstack;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;

public interface ElasticStackBaseNodeTemplateBuilder extends NodeTemplateBuilder{

	/**
	 * @param name String with a name for this new node (required)
	 * 			(:type name: ``str``)
	**/
	public ElasticStackBaseNodeTemplateBuilder name(String name);

	/**
	 * @param smp Number of virtual processors or None to calculate
	 * 			based on the cpu speed
	 * 			(:type smp: ``int``)
	**/
	public ElasticStackBaseNodeTemplateBuilder smp(int smp);

	/**
	 * @param nic_model e1000, rtl8139 or virtio
	 * 			(if not specified, e1000 is used)
	 * 			(:type nic_model: ``str``)
	**/
	public ElasticStackBaseNodeTemplateBuilder nicModel(String nic_model);

	/**
	 * @param vnc_password If set, the same password is also used for
	 * 			SSH access with user toor,
	 * 			otherwise VNC access is disabled and
	 * 			no SSH login is possible.
	 * 			(:type vnc_password: ``str``)
	**/
	public ElasticStackBaseNodeTemplateBuilder vncPassword(String vnc_password);

}