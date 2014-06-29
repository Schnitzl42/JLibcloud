package org.askalon.jlibcloud.compute.wrapperInterfacesImpl.deploymentImpl;

import org.askalon.jlibcloud.compute.wrapperInterfaces.deployment.FileDeployment;

public class FileDeploymentImpl implements FileDeployment {

	private String source;
	private String target;
	
	public FileDeploymentImpl(String source, String target) {
		this.source = source;
		this.target = target;
	}

	@Override
	public String getSource() {
		return this.source;
	}

	@Override
	public String getTarget() {
		return this.target;
	}

}
