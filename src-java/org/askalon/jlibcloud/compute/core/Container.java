package org.askalon.jlibcloud.compute.core;

/**
 * Container class for the <tt>Providers.java</tt>
 * to store a provider name and the provider regions
 *
 */
public final class Container {
	public final String name;
	public final String[] regions;

	public Container(String name, String[] regions) {
		this.name = name;
		this.regions = regions;
	}	
}
