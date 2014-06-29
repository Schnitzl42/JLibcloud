package org.askalon.jlibcloud.compute.driverSpecific.ibm_sce;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.types.Arg;
import java.util.Map;

public interface IBMNodeTemplateBuilder extends NodeTemplateBuilder{


	/**
	 * @param ex_configurationData Image-specific configuration
	 * 			parameters. Configuration parameters are defined in the parameters
	 * 			.xml file. The URL to this file is defined in the NodeImage at
	 * 			extra[parametersURL].
	 * 			Note: This argument must be specified when launching a Windows
	 * 			instance. It must contain 'UserName' and 'Password' keys.
	 * 			(:type ex_configurationData: ``dict``)
	**/
	public IBMNodeTemplateBuilder exConfigurationData(Map<String,Arg> ex_configurationData);

}