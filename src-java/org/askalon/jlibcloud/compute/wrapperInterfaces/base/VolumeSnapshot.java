package org.askalon.jlibcloud.compute.wrapperInterfaces.base;

import java.util.Map;

public interface VolumeSnapshot {

	public String getId();
	public int getSize();
	public Map<String, String> getExtra();
	public boolean destroy();
	public String toString();
}
