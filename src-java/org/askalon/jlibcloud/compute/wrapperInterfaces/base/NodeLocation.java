package org.askalon.jlibcloud.compute.wrapperInterfaces.base;

/**
 * Represents a geographical location
 * where {@link org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node}s
 * can reside.
 *
 */
public interface NodeLocation {

	public String getId();
	public String getName();
	public String getCountry();
	public String toString();
}
