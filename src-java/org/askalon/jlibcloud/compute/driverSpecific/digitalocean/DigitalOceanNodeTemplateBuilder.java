package org.askalon.jlibcloud.compute.driverSpecific.digitalocean;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;

public interface DigitalOceanNodeTemplateBuilder extends NodeTemplateBuilder{

	/**
	 * @param ex_ssh_key_ids A list of ssh key ids which will be added
	 * 			to the server. (optional)
	 * 			(:type ex_ssh_key_ids: ``list`` of ``str``)
	**/
	public DigitalOceanNodeTemplateBuilder exSshKeyIds(String... ex_ssh_key_ids);

}