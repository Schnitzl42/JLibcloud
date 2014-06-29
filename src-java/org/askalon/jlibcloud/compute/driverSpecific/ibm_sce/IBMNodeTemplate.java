package org.askalon.jlibcloud.compute.driverSpecific.ibm_sce;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.types.Arg;
import java.util.Map;

public interface IBMNodeTemplate extends NodeTemplate{


	/**
	 * @return ex_configurationData Image-specific configuration
	 * 			parameters. Configuration parameters are defined in the parameters
	 * 			.xml file. The URL to this file is defined in the NodeImage at
	 * 			extra[parametersURL].
	 * 			Note: This argument must be specified when launching a Windows
	 * 			instance. It must contain 'UserName' and 'Password' keys.
	 * 			(:type ex_configurationData: ``dict``)
	**/
	public Map<String,Arg> getExConfigurationData();

}