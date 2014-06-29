package org.askalon.jlibcloud.compute.driverSpecific.gce;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.StorageVolume;
import java.util.List;
import java.util.Map;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;

public interface GCENodeTemplate extends NodeTemplate{

	/**
	 * @return name The name of the node to create.
	 * 			(:type name: ``str``)
	**/
	public String getName();

	/**
	 * @return size The machine type to use.
	 * 			(:type size: ``str`` or :class:`GCENodeSize`)
	**/
	public NodeSize getSize();

	/**
	 * @return image The image to use to create the node (or, if attaching
	 * 			a persistent disk, the image used to create the disk)
	 * 			(:type image: ``str`` or :class:`NodeImage`)
	**/
	public NodeImage getImage();

	/**
	 * @return location The location (zone) to create the node in.
	 * 			(:type location: ``str`` or :class:`NodeLocation` or :class:`GCEZone` or ``None``)
	**/
	public NodeLocation getLocation();

	/**
	 * @return ex_network The network to associate with the node.
	 * 			(:type ex_network: ``str`` or :class:`GCENetwork`)
	**/
	public String getExNetwork();

	/**
	 * @return ex_tags A list of tags to assiciate with the node.
	 * 			(:type ex_tags: ``list`` of ``str`` or ``None``)
	**/
	public List<String> getExTags();

	/**
	 * @return ex_metadata Metadata dictionary for instance.
	 * 			(:type ex_metadata: ``dict`` or ``None``)
	**/
	public Map<String,Arg> getExMetadata();

	/**
	 * @return ex_boot_disk The boot disk to attach to the instance.
	 * 			(:type ex_boot_disk: :class:`StorageVolume` or ``str``)
	**/
	public StorageVolume getExBootDisk();

	/**
	 * @return use_existing_disk If True and if an existing disk with the
	 * 			same name/location is found, use that
	 * 			disk instead of creating a new one.
	 * 			(:type use_existing_disk: ``bool``)
	**/
	public Boolean getUseExistingDisk();

	/**
	 * @return external_ip The external IP address to use. If 'ephemeral'
	 * 			(default), a new non-static address will be
	 * 			used. If 'None', then no external address will
	 * 			be used. To use an existing static IP address,
	 * 			a GCEAddress object should be passed in.
	 * 			(:type external_ip: :class:`GCEAddress` or ``str`` or None)
	**/
	public GCEAddress getExternalIp();

}