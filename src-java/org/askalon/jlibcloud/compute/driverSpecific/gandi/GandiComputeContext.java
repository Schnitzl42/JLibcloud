/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/gandi.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/gandi.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/gandi.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.gandi;

import java.util.List;

import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;


public interface GandiComputeContext extends ComputeContext {

	/**
	 * Specific method to list network interfaces
	 * @return `GandiNetworkInterface`
	**/
	public List<NetworkInterface> exListInterfaces();



	/**
	 * Specific method to list all disk
	 * @return `GandiDisk`
	**/
	public List<Disk> exListDisks();



	/**
	 * Specific method to attach a disk to a node
	 * @param node Node which should be used
	 * 			(:type node: :class:`Node`)
	 * @param disk Disk which should be used
	 * 			(:type disk: :class:`GandiDisk`)
	 * @return ``bool``
	**/
	public boolean exNodeAttachDisk(Node node, Disk disk);



	/**
	 * Specific method to detach a disk from a node
	 * @param node Node which should be used
	 * 			(:type node: :class:`Node`)
	 * @param disk Disk which should be used
	 * 			(:type disk: :class:`GandiDisk`)
	 * @return ``bool``
	**/
	public boolean exNodeDetachDisk(Node node, Disk disk);



	/**
	 * Specific method to attach an interface to a node
	 * @param node Node which should be used
	 * 			(:type node: :class:`Node`)
	 * @param iface Network interface which should be used
	 * 			(:type iface: :class:`GandiNetworkInterface`)
	 * @return ``bool``
	**/
	public boolean exNodeAttachInterface(Node node, NetworkInterface iface);



	/**
	 * Specific method to detach an interface from a node
	 * @param node Node which should be used
	 * 			(:type node: :class:`Node`)
	 * @param iface Network interface which should be used
	 * 			(:type iface: :class:`GandiNetworkInterface`)
	 * @return ``bool``
	**/
	public boolean exNodeDetachInterface(Node node, NetworkInterface iface);



	/**
	 * Specific method to make a snapshot of a disk
	 * @param disk Disk which should be used
	 * 			(:type disk: :class:`GandiDisk`)
	 * @param name Name which should be used
	 * 			(:type name: ``str``)
	 * @return ``bool``
	**/
	public boolean exSnapshotDisk(Disk disk, String name);


	/**
	 * Specific method to make a snapshot of a disk
	 * @param disk Disk which should be used
	 * 			(:type disk: :class:`GandiDisk`)
	 * @return ``bool``
	**/
	public boolean exSnapshotDisk(Disk disk);

	/**
	 * WARNING: if a server is attached it'll be rebooted
	 * @param disk Disk which should be used
	 * 			(:type disk: :class:`GandiDisk`)
	 * @param new_size New size
	 * 			(:type new_size: ``int``)
	 * @param new_name New name
	 * 			(:type new_name: ``str``)
	 * @return ``bool``
	**/
	public boolean exUpdateDisk(Disk disk, int new_size, String new_name);


	/**
	 * WARNING: if a server is attached it'll be rebooted
	 * @param disk Disk which should be used
	 * 			(:type disk: :class:`GandiDisk`)
	 * @return ``bool``
	**/
	public boolean exUpdateDisk(Disk disk);

}