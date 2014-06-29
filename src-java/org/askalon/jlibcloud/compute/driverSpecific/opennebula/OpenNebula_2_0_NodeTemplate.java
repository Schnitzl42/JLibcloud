package org.askalon.jlibcloud.compute.driverSpecific.opennebula;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.types.Arg;
import java.util.List;
import java.util.Map;

public interface OpenNebula_2_0_NodeTemplate extends NodeTemplate{

	/**
	 * @return networks List of virtual networks to which this node should
	 * 			connect. (optional)
	 * 			(:type networks: :class:`OpenNebulaNetwork` or ``list`` of :class:`OpenNebulaNetwork`)
	**/
	public List<OpenNebulaNetwork> getNetworks();

	/**
	 * @return context Custom (key, value) pairs to be injected into
	 * 			compute node XML description. (optional)
	 * 			(:type context: ``dict``)
	**/
	public Map<String,Arg> getContext();

}