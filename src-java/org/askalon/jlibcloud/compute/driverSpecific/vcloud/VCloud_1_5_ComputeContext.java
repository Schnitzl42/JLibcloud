/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/vcloud.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/vcloud.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/vcloud.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.vcloud;


import java.util.List;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;
import java.util.Map;


public interface VCloud_1_5_ComputeContext extends VCloudComputeContext {

	/**
	 * Searches for node across specified vDCs. This is more effective than
	 * querying all nodes to get a single instance.
	 * @param node_name The name of the node to search for
	 * 			(:type node_name: ``str``)
	 * @param vdcs None, vDC or a list of vDCs to search in. If None all vDCs
	 * 			will be searched.
	 * 			(:type vdcs: :class:`Vdc`)
	 * @return node instance or None if not found
	 * 			(:rtype: :class:`Node` or ``None``)
	**/
	public Node exFindNode(String node_name, Vdc vdcs);


	/**
	 * Searches for node across specified vDCs. This is more effective than
	 * querying all nodes to get a single instance.
	 * @param node_name The name of the node to search for
	 * 			(:type node_name: ``str``)
	 * @return node instance or None if not found
	 * 			(:rtype: :class:`Node` or ``None``)
	**/
	public Node exFindNode(String node_name);

	/**
	 * Deploys existing node. Equal to vApp "start" operation.
	 * @param node The node to be deployed
	 * 			(:type node: :class:`Node`)
	 * @return `Node`
	**/
	public Node exDeployNode(Node node);



	/**
	 * Undeploys existing node. Equal to vApp "stop" operation.
	 * @param node The node to be deployed
	 * 			(:type node: :class:`Node`)
	 * @return `Node`
	**/
	public Node exUndeployNode(Node node);



	/**
	 * Powers on all VMs under specified node. VMs need to be This operation
	 * is allowed only when the vApp/VM is powered on.
	 * @param node The node to be powered off
	 * 			(:type node: :class:`Node`)
	 * @return `Node`
	**/
	public Node exPowerOffNode(Node node);



	/**
	 * Powers on all VMs under specified node. This operation is allowed
	 * only when the vApp/VM is powered off or suspended.
	 * @param node The node to be powered on
	 * 			(:type node: :class:`Node`)
	 * @return `Node`
	**/
	public Node exPowerOnNode(Node node);



	/**
	 * Shutdowns all VMs under specified node. This operation is allowed only
	 * when the vApp/VM is powered on.
	 * @param node The node to be shut down
	 * 			(:type node: :class:`Node`)
	 * @return `Node`
	**/
	public Node exShutdownNode(Node node);



	/**
	 * Suspends all VMs under specified node. This operation is allowed only
	 * when the vApp/VM is powered on.
	 * @param node The node to be suspended
	 * 			(:type node: :class:`Node`)
	 * @return `Node`
	**/
	public Node exSuspendNode(Node node);



	/**
	 * Returns the control access settings for specified node.
	 * @param node node to get the control access for
	 * 			(:type node: :class:`Node`)
	 * @return `ControlAccess`
	**/
	public ControlAccess exGetControlAccess(Node node);



	/**
	 * Sets control access for the specified node.
	 * @param node node
	 * 			(:type node: :class:`Node`)
	 * @param control_access control access settings
	 * 			(:type control_access: :class:`ControlAccess`)
	 * <p><p>return ``None``
	**/
	public void exSetControlAccess(Node node, ControlAccess control_access);



	/**
	 * @param node node
	 * 			(:type node: :class:`Node`)
	 * @return dictionary mapping metadata keys to metadata values
	 * 			(:rtype: dictionary mapping ``str`` to ``str``)
	**/
	public Map<String,String> exGetMetadata(Node node);



	/**
	 * @param node node
	 * 			(:type node: :class:`Node`)
	 * @param key metadata key to be set
	 * 			(:type key: ``str``)
	 * @param value metadata value to be set
	 * 			(:type value: ``str``)
	 * <p><p>return ``None``
	**/
	public void exSetMetadataEntry(Node node, String key, String value);



	/**
	 * Queries vCloud for specified type. See
	 * http://www.vmware.com/pdf/vcd_15_api_guide.pdf for details. Each
	 * element of the returned list is a dictionary with all attributes from
	 * the record.
	 * @param type type to query (r.g. user, group, vApp etc.)
	 * 			(:type type: ``str``)
	 * @param filter filter expression (see documentation for syntax)
	 * 			(:type filter: ``str``)
	 * @param page page number
	 * 			(:type page: ``int``)
	 * @param page_size page size
	 * 			(:type page_size: ``int``)
	 * @param sort_asc sort in ascending order by specified field
	 * 			(:type sort_asc: ``str``)
	 * @param sort_desc sort in descending order by specified field
	 * 			(:type sort_desc: ``str``)
	 * @return ``list`` of dict
	**/
	public List<Map<String,String>> exQuery(String type, String filter, int page, int page_size, String sort_asc, 
			String sort_desc);


	/**
	 * Queries vCloud for specified type. See
	 * http://www.vmware.com/pdf/vcd_15_api_guide.pdf for details. Each
	 * element of the returned list is a dictionary with all attributes from
	 * the record.
	 * @param type type to query (r.g. user, group, vApp etc.)
	 * 			(:type type: ``str``)
	 * @return ``list`` of dict
	**/
	public List<Map<String,String>> exQuery(String type);

	/**
	 * Sets the number of virtual CPUs for the specified VM or VMs under
	 * the vApp. If the vapp_or_vm_id param represents a link to an vApp
	 * all VMs that are attached to this vApp will be modified.
	 * Please ensure that hot-adding a virtual CPU is enabled for the
	 * powered on virtual machines. Otherwise use this method on undeployed
	 * vApp.
	 * @param vapp_or_vm_id vApp or VM ID that will be modified. If
	 * 			a vApp ID is used here all attached VMs
	 * 			will be modified
	 * 			(:type vapp_or_vm_id: ``str``)
	 * @param vm_cpu number of virtual CPUs/cores to allocate for
	 * 			specified VMs
	 * 			(:type vm_cpu: ``int``)
	 * <p><p>return ``None``
	**/
	public void exSetVmCpu(String vapp_or_vm_id, int vm_cpu);



	/**
	 * Sets the virtual memory in MB to allocate for the specified VM or
	 * VMs under the vApp. If the vapp_or_vm_id param represents a link
	 * to an vApp all VMs that are attached to this vApp will be modified.
	 * Please ensure that hot-change of virtual memory is enabled for the
	 * powered on virtual machines. Otherwise use this method on undeployed
	 * vApp.
	 * @param vapp_or_vm_id vApp or VM ID that will be modified. If
	 * 			a vApp ID is used here all attached VMs
	 * 			will be modified
	 * 			(:type vapp_or_vm_id: ``str``)
	 * @param vm_memory virtual memory in MB to allocate for the
	 * 			specified VM or VMs
	 * 			(:type vm_memory: ``int``)
	 * <p><p>return ``None``
	**/
	public void exSetVmMemory(String vapp_or_vm_id, int vm_memory);



	/**
	 * Adds a virtual disk to the specified VM or VMs under the vApp. If the
	 * vapp_or_vm_id param represents a link to an vApp all VMs that are
	 * attached to this vApp will be modified.
	 * @param vapp_or_vm_id vApp or VM ID that will be modified. If a
	 * 			vApp ID is used here all attached VMs
	 * 			will be modified
	 * 			(:type vapp_or_vm_id: ``str``)
	 * @param vm_disk_size the disk capacity in GB that will be added
	 * 			to the specified VM or VMs
	 * 			(:type vm_disk_size: ``int``)
	 * <p><p>return ``None``
	**/
	public void exAddVmDisk(String vapp_or_vm_id, int vm_disk_size);



}