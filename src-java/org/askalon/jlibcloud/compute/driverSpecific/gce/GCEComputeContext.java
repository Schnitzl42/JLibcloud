/**
 * Parts or the whole documentation of this class
 * are copied from the respective module:
 * libcloud/compute/drivers/gce.py
 * @see <a href="https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/gce.py">https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/gce.py</a>
 * 
 * Apache Libcloud is licensed under the Apache 2.0 license.
 * For more information, please see LICENSE and NOTICE file or
 * see: <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 **/
package org.askalon.jlibcloud.compute.driverSpecific.gce;

import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.StorageVolume;
import java.util.List;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;
import java.util.Map;


public interface GCEComputeContext extends ComputeContext {

	/**
	 * Return a list of static addreses for a region or all.
	 * @param region 
	 * 			'us-central1'. If None, will return addresses from
	 * 			region of self.zone. If 'all', will return all
	 * 			addresses.
	 * 			(:type region: ``str`` or ``None``)
	 * @return A list of static address objects.
	 * 			(:rtype: ``list`` of :class:`GCEAddress`)
	**/
	public List<GCEAddress> exListAddresses(String region);


	/**
	 * Return a list of static addreses for a region or all.
	 * @return A list of static address objects.
	 * 			(:rtype: ``list`` of :class:`GCEAddress`)
	**/
	public List<GCEAddress> exListAddresses();

	/**
	 * Return the list of health checks.
	 * @return A list of health check objects.
	 * 			(:rtype: ``list`` of :class:`GCEHealthCheck`)
	**/
	public List<GCEHealthCheck> exListHealthchecks();



	/**
	 * Return the list of firewalls.
	 * @return A list of firewall objects.
	 * 			(:rtype: ``list`` of :class:`GCEFirewall`)
	**/
	public List<GCEFirewall> exListFirewalls();



	/**
	 * Return the list of forwarding rules for a region or all.
	 * @param region The region to return forwarding rules from. For
	 * 			example: 'us-central1'. If None, will return
	 * 			forwarding rules from the region of self.region
	 * 			(which is based on self.zone). If 'all', will
	 * 			return all forwarding rules.
	 * 			(:type region: ``str`` or :class:`GCERegion` or ``None``)
	 * @return A list of forwarding rule objects.
	 * 			(:rtype: ``list`` of :class:`GCEForwardingRule`)
	**/
	public List<GCEForwardingRule> exListForwardingRules(String region);


	/**
	 * Return the list of forwarding rules for a region or all.
	 * @return A list of forwarding rule objects.
	 * 			(:rtype: ``list`` of :class:`GCEForwardingRule`)
	**/
	public List<GCEForwardingRule> exListForwardingRules();

	/**
	 * Return the list of networks.
	 * @return A list of network objects.
	 * 			(:rtype: ``list`` of :class:`GCENetwork`)
	**/
	public List<GCENetwork> exListNetworks();



	/**
	 * Return the list of regions.
	 * @return A list of region objects.
	 * 			(:rtype: ``list`` of :class:`GCERegion`)
	**/
	public List<GCERegion> exListRegions();



	/**
	 * Return the list of disk snapshots in the project.
	 * @return A list of snapshot objects
	 * 			(:rtype: ``list`` of :class:`GCESnapshot`)
	**/
	public List<GCESnapshot> exListSnapshots();



	/**
	 * Return the list of target pools.
	 * @return A list of target pool objects
	 * 			(:rtype: ``list`` of :class:`GCETargetPool`)
	**/
	public List<GCETargetPool> exListTargetpools(String region);


	/**
	 * Return the list of target pools.
	 * @return A list of target pool objects
	 * 			(:rtype: ``list`` of :class:`GCETargetPool`)
	**/
	public List<GCETargetPool> exListTargetpools();

	/**
	 * Return the list of zones.
	 * @return A list of zone objects.
	 * 			(:rtype: ``list`` of :class:`GCEZone`)
	**/
	public List<GCEZone> exListZones();



	/**
	 * Create a static address in a region.
	 * @param name Name of static address
	 * 			(:type name: ``str``)
	 * @param region Name of region for the address (e.g. 'us-central1')
	 * 			(:type region: ``str`` or :class:`GCERegion`)
	 * @return Static Address object
	 * 			(:rtype: :class:`GCEAddress`)
	**/
	public GCEAddress exCreateAddress(String name, String region);


	/**
	 * Create a static address in a region.
	 * @param name Name of static address
	 * 			(:type name: ``str``)
	 * @return Static Address object
	 * 			(:rtype: :class:`GCEAddress`)
	**/
	public GCEAddress exCreateAddress(String name);

	/**
	 * Create an Http Health Check.
	 * @param name Name of health check
	 * 			(:type name: ``str``)
	 * @param host Hostname of health check requst. Defaults to empty and
	 * 			public IP is used instead.
	 * 			(:type host: ``str``)
	 * @param path The request path for the check. Defaults to /.
	 * 			(:type path: ``str``)
	 * @param port The TCP port number for the check. Defaults to 80.
	 * 			(:type port: ``int``)
	 * @param interval How often (in seconds) to check. Defaults to 5.
	 * 			(:type interval: ``int``)
	 * @param timeout How long to wait before failing. Defaults to 5.
	 * 			(:type timeout: ``int``)
	 * @param unhealthy_threshold How many failures before marking
	 * 			unhealthy. Defaults to 2.
	 * 			(:type unhealthy_threshold: ``int``)
	 * @param healthy_threshold How many successes before marking as
	 * 			healthy. Defaults to 2.
	 * 			(:type healthy_threshold: ``int``)
	 * @return Health Check object
	 * 			(:rtype: :class:`GCEHealthCheck`)
	**/
	public GCEHealthCheck exCreateHealthcheck(String name, String host, String path, int port, int interval, 
			int timeout, int unhealthy_threshold, int healthy_threshold);


	/**
	 * Create an Http Health Check.
	 * @param name Name of health check
	 * 			(:type name: ``str``)
	 * @return Health Check object
	 * 			(:rtype: :class:`GCEHealthCheck`)
	**/
	public GCEHealthCheck exCreateHealthcheck(String name);

	/**
	 * Create a firewall on a network.
	 * Firewall rules should be supplied in the "allowed" field. This is a
	 * list of dictionaries formated like so ("ports" is optional)::
	 * [{"IPProtocol": "<protocol string or number>",
	 * "ports": "<port_numbers or ranges>"}]
	 * For example, to allow tcp on port 8080 and udp on all ports, 'allowed'
	 * would be::
	 * [{"IPProtocol": "tcp",
	 * "ports": ["8080"]},
	 * {"IPProtocol": "udp"}]
	 * See `Firewall Reference <https://developers.google.com/compute/docs/
	 * reference/latest/firewalls/insert>`_ for more information.
	 * @param name Name of the firewall to be created
	 * 			(:type name: ``str``)
	 * @param allowed List of dictionaries with rules
	 * 			(:type allowed: ``list`` of ``dict``)
	 * @param network The network that the firewall applies to.
	 * 			(:type network: ``str`` or :class:`GCENetwork`)
	 * @param source_ranges A list of IP ranges in CIDR format that the
	 * 			firewall should apply to. Defaults to
	 * 			['0.0.0.0/0']
	 * 			(:type source_ranges: ``list`` of ``str``)
	 * @param source_tags A list of instance tags which the rules apply
	 * 			(:type source_tags: ``list`` of ``str``)
	 * @return Firewall object
	 * 			(:rtype: :class:`GCEFirewall`)
	**/
	public GCEFirewall exCreateFirewall(String name, List<Map<String,Arg>> allowed, String network, List<String> source_ranges, List<String> source_tags);


	/**
	 * Create a firewall on a network.
	 * Firewall rules should be supplied in the "allowed" field. This is a
	 * list of dictionaries formated like so ("ports" is optional)::
	 * [{"IPProtocol": "<protocol string or number>",
	 * "ports": "<port_numbers or ranges>"}]
	 * For example, to allow tcp on port 8080 and udp on all ports, 'allowed'
	 * would be::
	 * [{"IPProtocol": "tcp",
	 * "ports": ["8080"]},
	 * {"IPProtocol": "udp"}]
	 * See `Firewall Reference <https://developers.google.com/compute/docs/
	 * reference/latest/firewalls/insert>`_ for more information.
	 * @param name Name of the firewall to be created
	 * 			(:type name: ``str``)
	 * @param allowed List of dictionaries with rules
	 * 			(:type allowed: ``list`` of ``dict``)
	 * @return Firewall object
	 * 			(:rtype: :class:`GCEFirewall`)
	**/
	public GCEFirewall exCreateFirewall(String name, List<Map<String,Arg>> allowed);

	/**
	 * Create a forwarding rule.
	 * @param name Name of forwarding rule to be created
	 * 			(:type name: ``str``)
	 * @param targetpool `GCETargetPool`
	 * 			
	 * @param region Region to create the forwarding rule in. Defaults to
	 * 			self.region
	 * 			(:type region: ``str`` or :class:`GCERegion`)
	 * @param protocol Should be 'tcp' or 'udp'
	 * 			(:type protocol: ``str``)
	 * @param port_range Optional single port number or range separated
	 * 			by a dash. Examples: '80', '5000-5999'.
	 * 			(:type port_range: ``str``)
	 * @param address Optional static address for forwarding rule. Must be
	 * 			in same region.
	 * 			(:type address: ``str`` or :class:`GCEAddress`)
	 * @return Forwarding Rule object
	 * 			(:rtype: :class:`GCEForwardingRule`)
	**/
	public GCEForwardingRule exCreateForwardingRule(String name, String targetpool, String region, String protocol, String port_range, 
			String address);


	/**
	 * Create a forwarding rule.
	 * @param name Name of forwarding rule to be created
	 * 			(:type name: ``str``)
	 * @param targetpool `GCETargetPool`
	 * 			
	 * @return Forwarding Rule object
	 * 			(:rtype: :class:`GCEForwardingRule`)
	**/
	public GCEForwardingRule exCreateForwardingRule(String name, String targetpool);

	/**
	 * Create a network.
	 * @param name Name of network to be created
	 * 			(:type name: ``str``)
	 * @param cidr Address range of network in CIDR format.
	 * 			(:type cidr: ``str``)
	 * @return Network object
	 * 			(:rtype: :class:`GCENetwork`)
	**/
	public GCENetwork exCreateNetwork(String name, String cidr);



	/**
	 * Create multiple nodes and return a list of Node objects.
	 * Nodes will be named with the base name and a number. For example, if
	 * the base name is 'libcloud' and you create 3 nodes, they will be
	 * named::
	 * libcloud-000
	 * libcloud-001
	 * libcloud-002
	 * :class:`GCEZone` or ``None``
	 * @param base_name The base name of the nodes to create.
	 * 			(:type base_name: ``str``)
	 * @param size The machine type to use.
	 * 			(:type size: ``str`` or :class:`GCENodeSize`)
	 * @param image The image to use to create the nodes.
	 * 			(:type image: ``str`` or :class:`NodeImage`)
	 * @param number The number of nodes to create.
	 * 			(:type number: ``int``)
	 * @param location The location (zone) to create the nodes in.
	 * 			(:type location: ``str`` or :class:`NodeLocation` or :class:`GCEZone` or ``None``)
	 * @param ex_network The network to associate with the nodes.
	 * 			(:type ex_network: ``str`` or :class:`GCENetwork`)
	 * @param ex_tags A list of tags to assiciate with the nodes.
	 * 			(:type ex_tags: ``list`` of ``str`` or ``None``)
	 * @param ex_metadata Metadata dictionary for instances.
	 * 			(:type ex_metadata: ``dict`` or ``None``)
	 * @param ignore_errors If True, don't raise Exceptions if one or
	 * 			more nodes fails.
	 * 			(:type ignore_errors: ``bool``)
	 * @param use_existing_disk If True and if an existing disk with the
	 * 			same name/location is found, use that
	 * 			disk instead of creating a new one.
	 * 			(:type use_existing_disk: ``bool``)
	 * @param poll_interval Number of seconds between status checks.
	 * 			(:type poll_interval: ``int``)
	 * @param external_ip The external IP address to use. If 'ephemeral'
	 * 			(default), a new non-static address will be
	 * 			used. If 'None', then no external address will
	 * 			be used. (Static addresses are not supported for
	 * 			multiple node creation.)
	 * 			(:type external_ip: ``str`` or None)
	 * @param timeout The number of seconds to wait for all nodes to be
	 * 			created before timing out.
	 * 			(:type timeout: ``int``)
	 * @return A list of Node objects for the new nodes.
	 * 			(:rtype: ``list`` of :class:`Node`)
	**/
	public List<Node> exCreateMultipleNodes(String base_name, String size, String image, int number, String location, 
			String ex_network, List<String> ex_tags, Map<String,Arg> ex_metadata, boolean ignore_errors, 
			boolean use_existing_disk, int poll_interval, String external_ip, int timeout);


	/**
	 * Create multiple nodes and return a list of Node objects.
	 * Nodes will be named with the base name and a number. For example, if
	 * the base name is 'libcloud' and you create 3 nodes, they will be
	 * named::
	 * libcloud-000
	 * libcloud-001
	 * libcloud-002
	 * :class:`GCEZone` or ``None``
	 * @param base_name The base name of the nodes to create.
	 * 			(:type base_name: ``str``)
	 * @param size The machine type to use.
	 * 			(:type size: ``str`` or :class:`GCENodeSize`)
	 * @param image The image to use to create the nodes.
	 * 			(:type image: ``str`` or :class:`NodeImage`)
	 * @param number The number of nodes to create.
	 * 			(:type number: ``int``)
	 * @return A list of Node objects for the new nodes.
	 * 			(:rtype: ``list`` of :class:`Node`)
	**/
	public List<Node> exCreateMultipleNodes(String base_name, String size, String image, int number);

	/**
	 * Create a target pool.
	 * @param name Name of target pool
	 * 			(:type name: ``str``)
	 * @param region Region to create the target pool in. Defaults to
	 * 			self.region
	 * 			(:type region: ``str`` or :class:`GCERegion` or ``None``)
	 * @param healthchecks Optional list of health checks to attach
	 * 			(:type healthchecks: ``list`` of ``str`` or :class:`GCEHealthCheck`)
	 * @param nodes Optional list of nodes to attach to the pool
	 * 			(:type nodes: ``list`` of ``str`` or :class:`Node`)
	 * @return Target Pool object
	 * 			(:rtype: :class:`GCETargetPool`)
	**/
	public GCETargetPool exCreateTargetpool(String name, String region, List<GCEHealthCheck> healthchecks, List<Node> nodes);


	/**
	 * Create a target pool.
	 * @param name Name of target pool
	 * 			(:type name: ``str``)
	 * @return Target Pool object
	 * 			(:rtype: :class:`GCETargetPool`)
	**/
	public GCETargetPool exCreateTargetpool(String name);

	/**
	 * Update a health check with new values.
	 * To update, change the attributes of the health check object and pass
	 * the updated object to the method.
	 * @param healthcheck A healthcheck object with updated values.
	 * 			(:type healthcheck: :class:`GCEHealthCheck`)
	 * @return An object representing the new state of the health check.
	 * 			(:rtype: :class:`GCEHealthCheck`)
	**/
	public GCEHealthCheck exUpdateHealthcheck(GCEHealthCheck healthcheck);



	/**
	 * Update a firewall with new values.
	 * To update, change the attributes of the firewall object and pass the
	 * updated object to the method.
	 * @param firewall A firewall object with updated values.
	 * 			(:type firewall: :class:`GCEFirewall`)
	 * @return An object representing the new state of the firewall.
	 * 			(:rtype: :class:`GCEFirewall`)
	**/
	public GCEFirewall exUpdateFirewall(GCEFirewall firewall);



	/**
	 * Add a node to a target pool.
	 * @param targetpool The targetpool to add node to
	 * 			(:type targetpool: ``str`` or :class:`GCETargetPool`)
	 * @param node The node to add
	 * 			(:type node: ``str`` or :class:`Node`)
	 * @return True if successful
	 * 			(:rtype: ``bool``)
	**/
	public boolean exTargetpoolAddNode(String targetpool, String node);



	/**
	 * Add a health check to a target pool.
	 * @param targetpool The targetpool to add health check to
	 * 			(:type targetpool: ``str`` or :class:`GCETargetPool`)
	 * @param healthcheck The healthcheck to add
	 * 			(:type healthcheck: ``str`` or :class:`GCEHealthCheck`)
	 * @return True if successful
	 * 			(:rtype: ``bool``)
	**/
	public boolean exTargetpoolAddHealthcheck(String targetpool, String healthcheck);



	/**
	 * Remove a node from a target pool.
	 * @param targetpool The targetpool to remove node from
	 * 			(:type targetpool: ``str`` or :class:`GCETargetPool`)
	 * @param node The node to remove
	 * 			(:type node: ``str`` or :class:`Node`)
	 * @return True if successful
	 * 			(:rtype: ``bool``)
	**/
	public boolean exTargetpoolRemoveNode(String targetpool, String node);



	/**
	 * Remove a health check from a target pool.
	 * @param targetpool The targetpool to remove health check from
	 * 			(:type targetpool: ``str`` or :class:`GCETargetPool`)
	 * @param healthcheck The healthcheck to remove
	 * 			(:type healthcheck: ``str`` or :class:`GCEHealthCheck`)
	 * @return True if successful
	 * 			(:rtype: ``bool``)
	**/
	public boolean exTargetpoolRemoveHealthcheck(String targetpool, String healthcheck);



	/**
	 * Set the tags on a Node instance.
	 * Note that this updates the node object directly.
	 * @param node Node object
	 * 			(:type node: :class:`Node`)
	 * @param tags List of tags to apply to the object
	 * 			(:type tags: ``list`` of ``str``)
	 * @return True if successful
	 * 			(:rtype: ``bool``)
	**/
	public boolean exSetNodeTags(Node node, List<String> tags);



	/**
	 * See `Scheduling <https://developers.google.com/compute/
	 * docs/instances#onhostmaintenance>`_ documentation for more info.
	 * @param node Node object
	 * 			(:type node: :class:`Node`)
	 * @param on_host_maintenance Defines whether node should be
	 * 			terminated or migrated when host machine
	 * 			goes down. Acceptable values are:
	 * 			'MIGRATE' or 'TERMINATE' (If not
	 * 			supplied, value will be reset to GCE
	 * 			default value for the instance type.)
	 * 			(:type on_host_maintenance: ``str``)
	 * @param automatic_restart Defines whether the instance should be
	 * 			automatically restarted when it is
	 * 			terminated by Compute Engine. (If not
	 * 			supplied, value will be set to the GCE
	 * 			default value for the instance type.)
	 * 			(:type automatic_restart: ``bool``)
	 * @return True if successful.
	 * 			(:rtype: ``bool``)
	**/
	public boolean exSetNodeScheduling(Node node, String on_host_maintenance, boolean automatic_restart);


	/**
	 * See `Scheduling <https://developers.google.com/compute/
	 * docs/instances#onhostmaintenance>`_ documentation for more info.
	 * @param node Node object
	 * 			(:type node: :class:`Node`)
	 * @return True if successful.
	 * 			(:rtype: ``bool``)
	**/
	public boolean exSetNodeScheduling(Node node);

	/**
	 * Destroy a static address.
	 * @param address Address object to destroy
	 * 			(:type address: :class:`GCEAddress`)
	 * @return True if successful
	 * 			(:rtype: ``bool``)
	**/
	public boolean exDestroyAddress(GCEAddress address);



	/**
	 * Destroy a healthcheck.
	 * @param healthcheck Health check object to destroy
	 * 			(:type healthcheck: :class:`GCEHealthCheck`)
	 * @return True if successful
	 * 			(:rtype: ``bool``)
	**/
	public boolean exDestroyHealthcheck(GCEHealthCheck healthcheck);



	/**
	 * Destroy a firewall.
	 * @param firewall Firewall object to destroy
	 * 			(:type firewall: :class:`GCEFirewall`)
	 * @return True if successful
	 * 			(:rtype: ``bool``)
	**/
	public boolean exDestroyFirewall(GCEFirewall firewall);



	/**
	 * Destroy a forwarding rule.
	 * @param forwarding_rule Forwarding Rule object to destroy
	 * 			(:type forwarding_rule: :class:`GCEForwardingRule`)
	 * @return True if successful
	 * 			(:rtype: ``bool``)
	**/
	public boolean exDestroyForwardingRule(GCEForwardingRule forwarding_rule);



	/**
	 * Destroy a network.
	 * @param network Network object to destroy
	 * 			(:type network: :class:`GCENetwork`)
	 * @return True if successful
	 * 			(:rtype: ``bool``)
	**/
	public boolean exDestroyNetwork(GCENetwork network);



	/**
	 * Destroy multiple nodes at once.
	 * that the node was successfully destroyed.
	 * @param node_list List of nodes to destroy
	 * 			(:type node_list: ``list`` of :class:`Node`)
	 * @param ignore_errors If true, don't raise an exception if one or
	 * 			more nodes fails to be destroyed.
	 * 			(:type ignore_errors: ``bool``)
	 * @param destroy_boot_disk If true, also destroy the nodes' boot
	 * 			disks.
	 * 			(:type destroy_boot_disk: ``bool``)
	 * @param poll_interval Number of seconds between status checks.
	 * 			(:type poll_interval: ``int``)
	 * @param timeout Number of seconds to wait for all nodes to be
	 * 			destroyed.
	 * 			(:type timeout: ``int``)
	 * @return A list of boolean values.  One for each node.  True means
	 * 			(:rtype: ``list`` of ``bool``)
	**/
	public List<Boolean> exDestroyMultipleNodes(List<Node> node_list, boolean ignore_errors, boolean destroy_boot_disk, int poll_interval, int timeout);


	/**
	 * Destroy multiple nodes at once.
	 * that the node was successfully destroyed.
	 * @param node_list List of nodes to destroy
	 * 			(:type node_list: ``list`` of :class:`Node`)
	 * @return A list of boolean values.  One for each node.  True means
	 * 			(:rtype: ``list`` of ``bool``)
	**/
	public List<Boolean> exDestroyMultipleNodes(List<Node> node_list);

	/**
	 * Destroy a target pool.
	 * @param targetpool TargetPool object to destroy
	 * 			(:type targetpool: :class:`GCETargetPool`)
	 * @return True if successful
	 * 			(:rtype: ``bool``)
	**/
	public boolean exDestroyTargetpool(GCETargetPool targetpool);



	/**
	 * Return an Address object based on an address name and optional region.
	 * @param name The name of the address
	 * 			(:type name: ``str``)
	 * @param region The region to search for the address in (set to
	 * 			'all' to search all regions)
	 * 			(:type region: ``str`` :class:`GCERegion` or ``None``)
	 * @return An Address object for the address
	 * 			(:rtype: :class:`GCEAddress`)
	**/
	public GCEAddress exGetAddress(String name, String region);


	/**
	 * Return an Address object based on an address name and optional region.
	 * @param name The name of the address
	 * 			(:type name: ``str``)
	 * @return An Address object for the address
	 * 			(:rtype: :class:`GCEAddress`)
	**/
	public GCEAddress exGetAddress(String name);

	/**
	 * Return a HealthCheck object based on the healthcheck name.
	 * @param name The name of the healthcheck
	 * 			(:type name: ``str``)
	 * @return A GCEHealthCheck object
	 * 			(:rtype: :class:`GCEHealthCheck`)
	**/
	public GCEHealthCheck exGetHealthcheck(String name);



	/**
	 * Return a Firewall object based on the firewall name.
	 * @param name The name of the firewall
	 * 			(:type name: ``str``)
	 * @return A GCEFirewall object
	 * 			(:rtype: :class:`GCEFirewall`)
	**/
	public GCEFirewall exGetFirewall(String name);



	/**
	 * Return a Forwarding Rule object based on the forwarding rule name.
	 * @param name The name of the forwarding rule
	 * 			(:type name: ``str``)
	 * @param region The region to search for the rule in (set to 'all'
	 * 			to search all regions).
	 * 			(:type region: ``str`` or ``None``)
	 * @return A GCEForwardingRule object
	 * 			(:rtype: :class:`GCEForwardingRule`)
	**/
	public GCEForwardingRule exGetForwardingRule(String name, String region);


	/**
	 * Return a Forwarding Rule object based on the forwarding rule name.
	 * @param name The name of the forwarding rule
	 * 			(:type name: ``str``)
	 * @return A GCEForwardingRule object
	 * 			(:rtype: :class:`GCEForwardingRule`)
	**/
	public GCEForwardingRule exGetForwardingRule(String name);

	/**
	 * Return an NodeImage object based on the name or link provided.
	 * @param partial_name The name, partial name, or full path of a GCE
	 * 			image.
	 * 			(:type partial_name: ``str``)
	 * 			image with that name is not found.
	 * @return NodeImage object based on provided information or None if an
	 * 			(:rtype: :class:`NodeImage` or ``None``)
	**/
	public NodeImage exGetImage(String partial_name);



	/**
	 * Return a Network object based on a network name.
	 * @param name The name of the network
	 * 			(:type name: ``str``)
	 * @return A Network object for the network
	 * 			(:rtype: :class:`GCENetwork`)
	**/
	public GCENetwork exGetNetwork(String name);



	/**
	 * Return a Node object based on a node name and optional zone.
	 * :class:`NodeLocation` or ``None``
	 * @param name The name of the node
	 * 			(:type name: ``str``)
	 * @param zone The zone to search for the node in. If set to 'all',
	 * 			search all zones for the instance.
	 * 			(:type zone: ``str`` or :class:`GCEZone` or :class:`NodeLocation` or ``None``)
	 * @return A Node object for the node
	 * 			(:rtype: :class:`Node`)
	**/
	public Node exGetNode(String name, String zone);


	/**
	 * Return a Node object based on a node name and optional zone.
	 * :class:`NodeLocation` or ``None``
	 * @param name The name of the node
	 * 			(:type name: ``str``)
	 * @return A Node object for the node
	 * 			(:rtype: :class:`Node`)
	**/
	public Node exGetNode(String name);

	/**
	 * Return a Project object with project-wide information.
	 * @return A GCEProject object
	 * 			(:rtype: :class:`GCEProject`)
	**/
	public GCEProject exGetProject();



	/**
	 * Return a size object based on a machine type name and zone.
	 * :class:`NodeLocation` or ``None``
	 * @param name The name of the node
	 * 			(:type name: ``str``)
	 * @param zone The zone to search for the machine type in
	 * 			(:type zone: ``str`` or :class:`GCEZone` or :class:`NodeLocation` or ``None``)
	 * @return A GCENodeSize object for the machine type
	 * 			(:rtype: :class:`GCENodeSize`)
	**/
	public GCENodeSize exGetSize(String name, String zone);


	/**
	 * Return a size object based on a machine type name and zone.
	 * :class:`NodeLocation` or ``None``
	 * @param name The name of the node
	 * 			(:type name: ``str``)
	 * @return A GCENodeSize object for the machine type
	 * 			(:rtype: :class:`GCENodeSize`)
	**/
	public GCENodeSize exGetSize(String name);

	/**
	 * Return a Snapshot object based on snapshot name.
	 * @param name The name of the snapshot
	 * 			(:type name: ``str``)
	 * @return A GCESnapshot object for the snapshot
	 * 			(:rtype: :class:`GCESnapshot`)
	**/
	public GCESnapshot exGetSnapshot(String name);



	/**
	 * Return a Volume object based on a volume name and optional zone.
	 * or ``None``
	 * @param name The name of the volume
	 * 			(:type name: ``str``)
	 * @param zone The zone to search for the volume in (set to 'all' to
	 * 			search all zones)
	 * 			(:type zone: ``str`` or :class:`GCEZone` or :class:`NodeLocation` or ``None``)
	 * @return A StorageVolume object for the volume
	 * 			(:rtype: :class:`StorageVolume`)
	**/
	public StorageVolume exGetVolume(String name, String zone);


	/**
	 * Return a Volume object based on a volume name and optional zone.
	 * or ``None``
	 * @param name The name of the volume
	 * 			(:type name: ``str``)
	 * @return A StorageVolume object for the volume
	 * 			(:rtype: :class:`StorageVolume`)
	**/
	public StorageVolume exGetVolume(String name);

	/**
	 * Return a Region object based on the region name.
	 * @param name The name of the region.
	 * 			(:type name: ``str``)
	 * @return A GCERegion object for the region
	 * 			(:rtype: :class:`GCERegion`)
	**/
	public GCERegion exGetRegion(String name);



	/**
	 * Return a TargetPool object based on a name and optional region.
	 * @param name The name of the target pool
	 * 			(:type name: ``str``)
	 * @param region The region to search for the target pool in (set to
	 * 			'all' to search all regions).
	 * 			(:type region: ``str`` or :class:`GCERegion` or ``None``)
	 * @return A TargetPool object for the pool
	 * 			(:rtype: :class:`GCETargetPool`)
	**/
	public GCETargetPool exGetTargetpool(String name, String region);


	/**
	 * Return a TargetPool object based on a name and optional region.
	 * @param name The name of the target pool
	 * 			(:type name: ``str``)
	 * @return A TargetPool object for the pool
	 * 			(:rtype: :class:`GCETargetPool`)
	**/
	public GCETargetPool exGetTargetpool(String name);

	/**
	 * Return a Zone object based on the zone name.
	 * @param name The name of the zone.
	 * 			(:type name: ``str``)
	 * @return A GCEZone object for the zone or None if not found
	 * 			(:rtype: :class:`GCEZone` or ``None``)
	**/
	public GCEZone exGetZone(String name);



}