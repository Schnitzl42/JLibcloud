package org.askalon.jlibcloud.compute.wrapperInterfaces;

import java.util.List;
import java.util.Map;

import org.askalon.jlibcloud.compute.types.NodeState;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.KeyPair;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.StorageVolume;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.VolumeSnapshot;

/**
 * Represents a node driver. Offers functions
 * to interact with a provider. A ComputeContext
 * is instanciated using {@link org.askalon.jlibcloud.compute.core.ComputeContextBuilder}
 */
public interface ComputeContext {

	/*---------------------------------------------
	 methods which directly operate on nodes
	 ----------------------------------------------*/
	/**
	 * create a node for this driver
	 * 
	 * @param nodeTemplate
	 *            a nodeTemplate for this driver
	 * @return a list with the newly created node(s), these nodes do not have an
	 *  IP adress assigned and should be refetched when they are in the state running
	 */
	public List<? extends Node> createNode(NodeTemplate nodeTemplate);

	/**
	 * !This method isn't yet supported and threfore marked as @Deprecated!
	 * <p>
	 * <p>
	 * deploys a node. Deploying allows to use the deploy() method of the
	 * nodeTemplate and the deployment classes from:
	 * {@link org.askalon.jlibcloud.compute.wrapperInterfaces.deployment}
	 * <p>
	 * !not every driver supports the deploy method and will throw an exception
	 * if not!
	 * 
	 * @param nodeTemplate
	 * @return a list with the newly created node(s)
	 */
	@Deprecated
	public List<? extends Node> deployNode(NodeTemplate nodeTemplate);

	/**
	 * destroys a node
	 * 
	 * @param node
	 *            the node to destroy
	 */
	public void destroyNode(Node node);

	/**
	 * reboots a node
	 * 
	 * @param node
	 * @return true if successful
	 */
	public boolean rebootNode(Node node);

	/**
	 * blocking waits for a list of nodes to get in the running state. nodes in
	 * the state shutdown are excluded. the polling rate is set to 5 seconds and
	 * the method returns after 600 seconds
	 * 
	 * @param nodes
	 * @return true if the node starts befor timeout
	 */
	public boolean waitUntilRunning(List<Node> nodes);

	/**
	 * blocking waits for a list of nodes to get in the running state. nodes in
	 * the state shutdown are excluded.
	 * 
	 * @param nodes
	 * @param waitPeriodSeconds
	 *            the polling intervall for checking the node status
	 * @param timeoutSeconds
	 *            the time after the method should return
	 * @return true if all nodes are in state running before timeoutSeconds
	 *         exceeds else false
	 */
	public boolean waitUntilRunning(List<Node> nodes,
			Integer waitPeriodSeconds, Integer timeoutSeconds);

	/*---------------------------------------------
	 methods for listing information
	 ----------------------------------------------*/
	// cloudframes offers node functions, which are also callable via the
	// computeContext
	// gogrid node
	/**
	 * list all nodes for this driver
	 * 
	 * @return a list of nodes
	 */
	public List<? extends Node> listNodes();

	/**
	 * Use this method to get the current status of a node. If a node was just
	 * created and has no IP adress, this method can be used to refetch the
	 * node, to update the IP field.
	 * 
	 * @param nodes
	 *            a list of nodes
	 * @return the updated list of nodes
	 */
	public List<? extends Node> listNodesMatching(List<Node> nodes);

	// cloud sigma
	/**
	 * list all images for this driver (this can be very time consuming)
	 * 
	 * @return a list of NodeImages
	 */
	public List<? extends NodeImage> listImages();

	/**
	 * only list the images for a specific location
	 * 
	 * @param location
	 *            the location to list images from
	 * @return a list of NodeImages
	 */
	public List<? extends NodeImage> listImages(NodeLocation location);

	// cloudframes vcpusArg
	// cloudsigma node size + getCpu
	// elastichosts/stacks + getCpu
	// opennebula/vcpus+cpus
	// openstack/vcpus
	/**
	 * list all known node sizes
	 * 
	 * @return a list of NodeSize
	 */
	public List<? extends NodeSize> listSizes();

	/**
	 * list all known node sizes
	 * 
	 * @param location
	 *            the location to list sizes from
	 * @return a list of NodeSize
	 */
	public List<? extends NodeSize> listSizes(NodeLocation location);

	// gceZone
	// ec2 availability zone
	/**
	 * list all known locations
	 * 
	 * @return a list of NodeLocation
	 */
	public List<? extends NodeLocation> listLocations();

	/**
	 * list all volumes
	 * 
	 * @return list of StorageVolumes
	 */
	public List<StorageVolume> listVolumes();

	// gceSnapshot has own method
	/**
	 * list all volume snapshots
	 * 
	 * @param volume
	 * @return a list of VolumeSnapshots
	 */
	public List<VolumeSnapshot> listVolumeSnapshots(StorageVolume volume);

	/**
	 * list all keypairs
	 * 
	 * @return list of KeyPairs
	 */
	public List<KeyPair> listKeyPairs();

	/**
	 * 
	 * @return a map with the state name to state code mapping
	 */
	public Map<String, NodeState> getNodeStateMap();

	/*---------------------------------------------
	 Volume specific methods
	 ----------------------------------------------*/
	/**
	 * create a storage volume with the specified name and size
	 * 
	 * @param sizeGB
	 *            the size in gigabytes
	 * @param name
	 *            the name of the volume
	 * @return the newly created volume
	 */
	public StorageVolume createVolume(int sizeGB, String name);

	/**
	 * create a storage volume with the specified name and size
	 * 
	 * @param sizeGB
	 *            the size in gigabytes
	 * @param name
	 *            the name of the volume
	 * @param snapshotName
	 *            the snapshot name to create the volume from
	 * @return the newly created volume
	 */
	public StorageVolume createVolume(int sizeGB, String name,
			String snapshotName);

	/**
	 * create a storage volume with the specified name and size
	 * 
	 * @param sizeGB
	 *            the size in gigabytes
	 * @param name
	 *            the name of the volume
	 * @param location
	 *            the datacenter to create the volume in
	 * @return the newly created volume
	 */
	public StorageVolume createVolume(int sizeGB, String name,
			NodeLocation location);

	/**
	 * create a storage volume with the specified name and size
	 * 
	 * @param sizeGB
	 *            the size in gigabytes
	 * @param name
	 *            the name of the volume
	 * @param location
	 *            the datacenter to create the volume in
	 * @param snapshotName
	 *            the snapshot name to create the volume from
	 * @return the newly created volume
	 */
	public StorageVolume createVolume(int sizeGB, String name,
			NodeLocation location, String snapshotName);

	/**
	 * attach a storage volume to a node
	 * 
	 * @param node
	 *            the node to attach te volume to
	 * @param volume
	 *            the volume to attach to the node
	 * @return true if successful
	 */
	public boolean attachVolume(Node node, StorageVolume volume);

	/**
	 * 
	 * @param node
	 * @param volume
	 * @param device
	 *            Where the device is exposed, e.g. '/dev/sdb'
	 * @return true if successful
	 */
	public boolean attachVolume(Node node, StorageVolume volume, String device);

	/**
	 * detach a storage volume from the node it is currently attached
	 * 
	 * @param volume
	 *            the volume to detach
	 * @return true if successful
	 */
	public boolean detachVolume(StorageVolume volume);

	/**
	 * delete the specified volume
	 * 
	 * @param volume
	 *            the volume to delte
	 * @return true if successful
	 */
	public boolean destroyVolume(StorageVolume volume);

	/**
	 * create a snapshot of a volume
	 * 
	 * @param volume
	 *            the volume to create the snapshot of
	 * @param name
	 *            the name of the snapshot
	 * @return the newly created snapshot
	 */
	public VolumeSnapshot createVolumeSnapshot(StorageVolume volume, String name);

	/**
	 * delete a volume snapshot
	 * 
	 * @param snapshot
	 *            the snapshot to delete
	 * @return true if successful
	 */
	public boolean destroyVolumeSnapshot(VolumeSnapshot snapshot);

	/*---------------------------------------------
	 SSH key pair specific methods 
	 ----------------------------------------------*/
	/**
	 * retrieve information about a specific keypair
	 * 
	 * @param name
	 *            the name of the keypair
	 * @return the keypair information in a KeyPair object
	 */
	public KeyPair getKeyPair(String name);

	/**
	 * create a new key pair
	 * 
	 * @param name
	 *            the key pair name
	 * @return the newly created key pair
	 */
	public KeyPair createKeyPair(String name);

	// import means export to the driver, so in the drivers view import
	/**
	 * push a key to the provider
	 * 
	 * @param name
	 *            the name of the key
	 * @param keyMaterial
	 *            the key material
	 * @return the key pair that was added to the provider
	 */
	public KeyPair importKeyPairFromString(String name, String keyMaterial);

	/**
	 * push a key to the provider
	 * 
	 * @param name
	 *            the name of the key
	 * @param keyPath
	 *            a filepath to the key
	 * @return the key pair that was added to the provider
	 */
	public KeyPair importKeyPairFromFile(String name, String keyPath);

	/**
	 * delete an existing key pair from the provider
	 * 
	 * @param keypair
	 *            the keypair to delete
	 */
	public void deleteKeyPair(KeyPair keypair);

	/*---------------------------------------------
	 non libcloud methods
	 ----------------------------------------------*/
	/**
	 * Loads a given properties file for the
	 * {@link org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext}
	 * from the given filepath.
	 * 
	 * The
	 * {@link org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext
	 * ComputeContext} uses the properties when creating a node, if the required
	 * fields in the
	 * {@link org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate} are
	 * uninitialized.
	 * 
	 * <p>
	 * For valid attribute names use the Javadoc name from
	 * <tt>NodeTemplate.class</tt> or the <tt>NodeTemplateBuilder.class</tt>.
	 * Allowed fields for the standard
	 * {@link org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext}
	 * are:
	 * 
	 * <pre>
	 * {@code
	 * name=nodeName
	 * image_id=someImage
	 * size_id=someSize
	 * location_id=location
	 * ex_keyname=someKey
	 * }
	 * </pre>
	 * 
	 * In general for the <tt> ComputeContext</tt> and derived classes alls
	 * <tt>String, int, boolean</tt> and <tt>float</tt> values can be read from
	 * a .properties file. <tt>List\<String\></tt> or <tt>String[]</tt> values
	 * can also be read. Therfore add an an index from [0,14] to the attribute
	 * name. for example:
	 * 
	 * <pre>
	 * {@code
	 * ex_security_groups0=group1
	 * ex_security_groups1=group2
	 * ex_security_groups2=group3
	 * }
	 * </pre>
	 * 
	 * @param filepath
	 *            path to the <i> .properties </i> file
	 */
	public void setNodeProperties(String filepath);

	/**
	 * 
	 * @return the template builder for this driver
	 */
	public NodeTemplateBuilder getTemplateBuilder();

	/**
	 * 
	 * @return the name of the provider
	 */
	public String getProviderName();

}
