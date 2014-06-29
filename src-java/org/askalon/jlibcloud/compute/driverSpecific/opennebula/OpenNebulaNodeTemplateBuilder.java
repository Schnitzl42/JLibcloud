package org.askalon.jlibcloud.compute.driverSpecific.opennebula;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;
import java.util.List;

public interface OpenNebulaNodeTemplateBuilder extends NodeTemplateBuilder{

	/**
	 * @param networks List of virtual networks to which this node should
	 * 			connect. (optional)
	 * 			(:type networks: :class:`OpenNebulaNetwork` or ``list`` of :class:`OpenNebulaNetwork`)
	**/
	public OpenNebulaNodeTemplateBuilder networks(List<OpenNebulaNetwork> networks);

}