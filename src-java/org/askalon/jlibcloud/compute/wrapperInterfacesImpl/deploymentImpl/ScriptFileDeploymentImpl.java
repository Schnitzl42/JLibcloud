package org.askalon.jlibcloud.compute.wrapperInterfacesImpl.deploymentImpl;

import java.util.LinkedList;
import java.util.List;

import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.types.ArgImpl;
import org.askalon.jlibcloud.compute.wrapperInterfaces.deployment.ScriptFileDeployment;

public class ScriptFileDeploymentImpl implements ScriptFileDeployment {

	private String scriptPath = null;
	private List<Arg> args = null;
	private String name = null;
	private Boolean delete = null;
	
	public ScriptFileDeploymentImpl(String scriptContent){
		this.scriptPath = scriptContent;
	}
	
	public ScriptFileDeploymentImpl(String scriptContent, List<Arg> args){
		this.scriptPath = scriptContent;
		this.args = args;
	}
	
	public ScriptFileDeploymentImpl(String scriptContent, List<Arg> args, String name, Boolean delete) {
		this.scriptPath = scriptContent;
		this.args = args;
		this.name = name;
		this.delete = delete;
	}

	@Override
	public String getScriptPath() {
		return this.scriptPath;
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
