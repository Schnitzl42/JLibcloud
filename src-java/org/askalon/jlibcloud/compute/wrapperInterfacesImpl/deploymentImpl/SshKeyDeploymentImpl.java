package org.askalon.jlibcloud.compute.wrapperInterfacesImpl.deploymentImpl;

import org.askalon.jlibcloud.compute.wrapperInterfaces.deployment.SshKeyDeployment;

public class SshKeyDeploymentImpl implements SshKeyDeployment {

	private String key; 
	
	/**
	 * 
	 * 
	 * @param key
	 * key: ``str`` or :class:`File` object
	 * key: Contents of the public key write or a file object which
                      can be read.
	 */
	public SshKeyDeploymentImpl(String key) {
		this.key = key;
	}

	@Override
	public String getKey() {
		return key;
	}

}
