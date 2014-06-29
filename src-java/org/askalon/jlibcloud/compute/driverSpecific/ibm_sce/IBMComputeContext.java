/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/ibm_sce.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/ibm_sce.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/ibm_sce.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.ibm_sce;

import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.StorageVolume;
import java.util.List;


public interface IBMComputeContext extends ComputeContext {

	/**
	 * Destroys an image.
	 * @param image Image to be destroyed
	 * 			(:type image: :class:`NodeImage`)
	 * @return ``bool``
	**/
	public boolean exDestroyImage(NodeImage image);



	/**
	 * List the storage center offerings
	 * @return `VolumeOffering`
	**/
	public List<VolumeOffering> exListStorageOfferings();



	/**
	 * Allocate a new reserved IP address
	 * @param location_id Target data center
	 * 			(:type location_id: ``str``)
	 * @param offering_id Offering ID for address to create
	 * 			(:type offering_id: ``str``)
	 * @param vlan_id ID of target VLAN
	 * 			(:type vlan_id: ``str``)
	 * @return `Address` object
	 * 			(:rtype: :class:`Address`)
	**/
	public Address exAllocateAddress(String location_id, String offering_id, String vlan_id);


	/**
	 * Allocate a new reserved IP address
	 * @param location_id Target data center
	 * 			(:type location_id: ``str``)
	 * @param offering_id Offering ID for address to create
	 * 			(:type offering_id: ``str``)
	 * @return `Address` object
	 * 			(:rtype: :class:`Address`)
	**/
	public Address exAllocateAddress(String location_id, String offering_id);

	/**
	 * List the reserved IP addresses
	 * @param resource_id If this is supplied only a single address will
	 * 			be returned (optional)
	 * 			(:type resource_id: ``str``)
	 * @return `Address`
	**/
	public List<Address> exListAddresses(String resource_id);


	/**
	 * List the reserved IP addresses
	 * @return `Address`
	**/
	public List<Address> exListAddresses();

	/**
	 * Copies a node image to a storage volume
	 * @param image source image to copy
	 * 			(:type image: :class:`NodeImage`)
	 * @param volume Target storage volume to copy to
	 * 			(:type volume: :class:`StorageVolume`)
	 * @return ``bool`` The success of the operation
	 * 			(:rtype: ``bool``)
	**/
	public boolean exCopyTo(NodeImage image, StorageVolume volume);



	/**
	 * Delete a reserved IP address
	 * @param resource_id The address to delete (required)
	 * 			(:type resource_id: ``str``)
	 * @return ``bool``
	**/
	public boolean exDeleteAddress(String resource_id);



	/**
	 * Block until storage volume state changes to the given value
	 * @param volume Storage volume.
	 * 			(:type volume: :class:`StorageVolume`)
	 * @param state The target state to wait for
	 * 			(:type state: ``int``)
	 * @param wait_period How many seconds to between each loop
	 * 			iteration (default is 3)
	 * 			(:type wait_period: ``int``)
	 * @param timeout How many seconds to wait before timing out
	 * 			(default is 1200)
	 * 			(:type timeout: ``int``)
	 * @return `StorageVolume`
	**/
	public StorageVolume exWaitStorageState(StorageVolume volume, int state, int wait_period, int timeout);


	/**
	 * Block until storage volume state changes to the given value
	 * @param volume Storage volume.
	 * 			(:type volume: :class:`StorageVolume`)
	 * @return `StorageVolume`
	**/
	public StorageVolume exWaitStorageState(StorageVolume volume);

}