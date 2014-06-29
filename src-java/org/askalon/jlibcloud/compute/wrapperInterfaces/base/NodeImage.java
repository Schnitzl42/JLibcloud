package org.askalon.jlibcloud.compute.wrapperInterfaces.base;

import java.util.Map;

/**
 * A NodeImage represents an operating system for a {@link org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node}.
 * NodeImages are returned by a {@link org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext}
 *
 */
public interface NodeImage {

	public String getUUID();
	public String getId();
	public String getName();
	public Map<String, String> getExtra();
	public String toString(); 
}
