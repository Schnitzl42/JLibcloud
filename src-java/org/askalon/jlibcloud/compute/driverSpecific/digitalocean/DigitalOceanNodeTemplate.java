package org.askalon.jlibcloud.compute.driverSpecific.digitalocean;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;

public interface DigitalOceanNodeTemplate extends NodeTemplate{


	/**
	 * @return ex_ssh_key_ids A list of ssh key ids which will be added
	 * 			to the server. (optional)
	 * 			(:type ex_ssh_key_ids: ``list`` of ``str``)
	**/
	public String[] getExSshKeyIds();

}