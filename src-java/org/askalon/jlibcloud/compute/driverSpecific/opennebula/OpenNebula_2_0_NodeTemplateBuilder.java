package org.askalon.jlibcloud.compute.driverSpecific.opennebula;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.types.Arg;
import java.util.List;
import java.util.Map;

public interface OpenNebula_2_0_NodeTemplateBuilder extends NodeTemplateBuilder{

	/**
	 * @param networks List of virtual networks to which this node should
	 * 			connect. (optional)
	 * 			(:type networks: :class:`OpenNebulaNetwork` or ``list`` of :class:`OpenNebulaNetwork`)
	**/
	public OpenNebula_2_0_NodeTemplateBuilder networks(List<OpenNebulaNetwork> networks);

	/**
	 * @param context Custom (key, value) pairs to be injected into
	 * 			compute node XML description. (optional)
	 * 			(:type context: ``dict``)
	**/
	public OpenNebula_2_0_NodeTemplateBuilder context(Map<String,Arg> context);

}