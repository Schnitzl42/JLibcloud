package org.askalon.jlibcloud.compute.wrapperInterfacesImpl.deploymentImpl;

import java.util.LinkedList;
import java.util.List;

import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.types.ArgImpl;
import org.askalon.jlibcloud.compute.wrapperInterfaces.deployment.ScriptDeployment;

public class ScriptDeploymentImpl implements ScriptDeployment {

	private String scriptContent = null;
	private List<Arg> args = null;
	private String name = null;
	private Boolean delete = null;
	
	public ScriptDeploymentImpl(String scriptContent){
		this.scriptContent = scriptContent;
	}
	
	public ScriptDeploymentImpl(String scriptContent, List<Arg> args){
		this.scriptContent = scriptContent;
		this.args = args;
	}
	
	public ScriptDeploymentImpl(String scriptContent, List<Arg> args, String name, Boolean delete) {
		this.scriptContent = scriptContent;
		this.args = args;
		this.name = name;
		this.delete = delete;
	}

	
	@Override
	public String getScriptContent() {
		return this.scriptContent;
	}

	@Override
	public List<Arg> getArgs() {
		return this.args;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Boolean getDelete() {
		return this.delete;
	}

	@Override
	public void arg(int arg) {
		if(this.args==null){
			this.args = new LinkedList<Arg>();
		}
		this.args.add(new ArgImpl(arg));
	}

	@Override
	public void arg(float arg) {
		if(this.args==null){
			this.args = new LinkedList<Arg>();
		}
		this.args.add(new ArgImpl(arg));
	}

	@Override
	public void arg(String arg) {
		if(this.args==null){
			this.args = new LinkedList<Arg>();
		}
		this.args.add(new ArgImpl(arg));
	}

}
