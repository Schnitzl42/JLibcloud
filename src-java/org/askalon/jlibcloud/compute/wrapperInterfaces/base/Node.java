package org.askalon.jlibcloud.compute.wrapperInterfaces.base;

import java.util.Map;

import org.askalon.jlibcloud.compute.types.NodeState;

/**
 * Nodes represent servers.
 * Nodes are not instantiated directly.
 * They are created by a {@link org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext}
 *
 */
public interface Node {

	public String getUUID();
	public String getID();
	public String getName();
	public NodeState getState();
	public String getPublicIP();
	public String getPrivateIP();
	//public String getSSHCredentials();
	public NodeSize getSize();
	public NodeImage getImage(); //TODO: null-bug empty image should be ""
	public Map<String,String> getExtra();
	public boolean reboot();
	public void destroy();
	public String toString();
}
