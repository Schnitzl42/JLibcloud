package org.askalon.jlibcloud.compute.wrapperInterfaces.deployment;

import java.util.List;

import org.askalon.jlibcloud.compute.types.Arg;


public interface ScriptFileDeployment extends Deployment {

	/**
	 * add an int arg to the list of arguments of the script
	 * 
	 * @param arg
	 */
	public void arg(int arg);

	public void arg(float arg);

	public void arg(String arg);

	public String getScriptPath();

	public List<Arg> getArgs();

	public String getName();

	public Boolean getDelete();
}
