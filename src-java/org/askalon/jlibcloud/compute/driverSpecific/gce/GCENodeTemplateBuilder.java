package org.askalon.jlibcloud.compute.driverSpecific.gce;

import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.StorageVolume;
import java.util.List;
import java.util.Map;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;

public interface GCENodeTemplateBuilder extends NodeTemplateBuilder{

	/**
	 * @param name The name of the node to create.
	 * 			(:type name: ``str``)
	**/
	public GCENodeTemplateBuilder name(String name);

	/**
	 * @param size The machine type to use.
	 * 			(:type size: ``str`` or :class:`GCENodeSize`)
	**/
	public GCENodeTemplateBuilder size(NodeSize size);

	/**
	 * @param image The image to use to create the node (or, if attaching
	 * 			a persistent disk, the image used to create the disk)
	 * 			(:type image: ``str`` or :class:`NodeImage`)
	**/
	public GCENodeTemplateBuilder image(NodeImage image);

	/**
	 * @param location The location (zone) to create the node in.
	 * 			(:type location: ``str`` or :class:`NodeLocation` or :class:`GCEZone` or ``None``)
	**/
	public GCENodeTemplateBuilder location(NodeLocation location);

	/**
	 * @param ex_network The network to associate with the node.
	 * 			(:type ex_network: ``str`` or :class:`GCENetwork`)
	**/
	public GCENodeTemplateBuilder exNetwork(String ex_network);

	/**
	 * @param ex_tags A list of tags to assiciate with the node.
	 * 			(:type ex_tags: ``list`` of ``str`` or ``None``)
	**/
	public GCENodeTemplateBuilder exTags(List<String> ex_tags);

	/**
	 * @param ex_metadata Metadata dictionary for instance.
	 * 			(:type ex_metadata: ``dict`` or ``None``)
	**/
	public GCENodeTemplateBuilder exMetadata(Map<String,Arg> ex_metadata);

	/**
	 * @param ex_boot_disk The boot disk to attach to the instance.
	 * 			(:type ex_boot_disk: :class:`StorageVolume` or ``str``)
	**/
	public GCENodeTemplateBuilder exBootDisk(StorageVolume ex_boot_disk);

	/**
	 * @param use_existing_disk If True and if an existing disk with the
	 * 			same name/location is found, use that
	 * 			disk instead of creating a new one.
	 * 			(:type use_existing_disk: ``bool``)
	**/
	public GCENodeTemplateBuilder useExistingDisk(boolean use_existing_disk);

	/**
	 * @param external_ip The external IP address to use. If 'ephemeral'
	 * 			(default), a new non-static address will be
	 * 			used. If 'None', then no external address will
	 * 			be used. To use an existing static IP address,
	 * 			a GCEAddress object should be passed in.
	 * 			(:type external_ip: :class:`GCEAddress` or ``str`` or None)
	**/
	public GCENodeTemplateBuilder externalIp(GCEAddress external_ip);

}