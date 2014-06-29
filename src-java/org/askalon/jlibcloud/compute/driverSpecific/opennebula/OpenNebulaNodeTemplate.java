package org.askalon.jlibcloud.compute.driverSpecific.opennebula;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import java.util.List;

public interface OpenNebulaNodeTemplate extends NodeTemplate{

	/**
	 * @return networks List of virtual networks to which this node should
	 * 			connect. (optional)
	 * 			(:type networks: :class:`OpenNebulaNetwork` or ``list`` of :class:`OpenNebulaNetwork`)
	**/
	public List<OpenNebulaNetwork> getNetworks();

}