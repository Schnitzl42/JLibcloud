package org.askalon.jlibcloud.compute.wrapperInterfaces.base;

/**
 * A NodeSize represents the hardware configuration
 * of a {@link org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node}. NodeSizes are returned by
 * a {@link org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext}
 *
 */
public interface NodeSize {

	public String getUUID();
	public String getId();
	public String getName();
	public int getRamMB();
	public int getDiskSizeGB();
	public int getBandwidth();
	public float getHourPriceDollar();
	public String toString();
	
}
