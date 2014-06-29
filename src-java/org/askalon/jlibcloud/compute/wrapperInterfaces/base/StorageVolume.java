package org.askalon.jlibcloud.compute.wrapperInterfaces.base;

import java.util.List;
import java.util.Map;

/**
 * Represents a volume for a node.
 * A volume is attached to a {@link org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node}
 * to provide storage.
 *
 */
public interface StorageVolume {

	public String getUUID();
	public String getId();
	public String getName();
	public int getSizeGB();
	public Map<String,String> getExtra();
	public boolean attach(Node node);
	public boolean attach(Node node, String device);
	public boolean detach();
	public boolean destroy();
	public VolumeSnapshot createSnapshot(String name);
	public List<VolumeSnapshot> listSnapshots();
	public String toString();
}
