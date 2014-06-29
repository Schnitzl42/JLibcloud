# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/gce.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/gce.py
#
# Apache Libcloud is licensed under the Apache 2.0 license.
# For more information, please see LICENSE and NOTICE file or:
# http://www.apache.org/licenses/LICENSE-2.0
from javaimpl.compute.ComputeContextImpl import ComputeContextImpl
from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception, jlist_str_to_pylist
from javaimpl.compute.utils import jlist_obj_to_pylist, get_property, get_property_list
from javaimpl.compute.utils import jmap_to_pymap, jlist_map_to_pylist_map
from javaimpl.compute.base.NodeImageImpl import NodeImageImpl
from javaimpl.compute.base.NodeLocationImpl import NodeLocationImpl
from org.askalon.jlibcloud.compute.driverSpecific.gce import GCENodeTemplateImpl
from javaimpl.compute.base.NodeImpl import NodeImpl
from javaimpl.compute.base.StorageVolumeImpl import StorageVolumeImpl
from javaimpl.compute.base.VolumeSnapshotImpl import VolumeSnapshotImpl
from javaimpl.compute.base.NodeSizeImpl import NodeSizeImpl
from org.askalon.jlibcloud.compute.driverSpecific.gce import GCEComputeContext

class GCEComputeContextImpl(ComputeContextImpl, GCEComputeContext):
	def __init__(self, builder):
		ComputeContext.__init__(self, builder)

	def createNode(self, node_temp):
		"""
        Create a new node and return a node object for the node.

        :param  name: The name of the node to create.
        :type   name: ``str``

        :param  size: The machine type to use.
        :type   size: ``str`` or :class:`GCENodeSize`

        :param  image: The image to use to create the node (or, if attaching
                       a persistent disk, the image used to create the disk)
        :type   image: ``str`` or :class:`NodeImage`

        :keyword  location: The location (zone) to create the node in.
        :type     location: ``str`` or :class:`NodeLocation` or
                            :class:`GCEZone` or ``None``

        :keyword  ex_network: The network to associate with the node.
        :type     ex_network: ``str`` or :class:`GCENetwork`

        :keyword  ex_tags: A list of tags to assiciate with the node.
        :type     ex_tags: ``list`` of ``str`` or ``None``

        :keyword  ex_metadata: Metadata dictionary for instance.
        :type     ex_metadata: ``dict`` or ``None``

        :keyword  ex_boot_disk: The boot disk to attach to the instance.
        :type     ex_boot_disk: :class:`StorageVolume` or ``str``

        :keyword  use_existing_disk: If True and if an existing disk with the
                                     same name/location is found, use that
                                     disk instead of creating a new one.
        :type     use_existing_disk: ``bool``

        :keyword  external_ip: The external IP address to use.  If 'ephemeral'
                               (default), a new non-static address will be
                               used.  If 'None', then no external address will
                               be used.  To use an existing static IP address,
                               a GCEAddress object should be passed in.
        :type     external_ip: :class:`GCEAddress` or ``str`` or None

        :return:  A Node object for the new node.
        :rtype:   :class:`Node`
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_gce_template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_gce_template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_gce_template(self, node_temp, kwargs):
		name = node_temp.getName()
		kwargs = get_property(self, name, 'name',
					 kwargs,lambda x : x)
		size = node_temp.getSize()
		kwargs = get_property(self, size, 'size',
					 kwargs,lambda x : x)
		image = node_temp.getImage()
		kwargs = get_property(self, image, 'image',
					 kwargs,lambda x : x)
		location = node_temp.getLocation()
		kwargs = get_property(self, location, 'location',
					 kwargs,lambda x : x)
		ex_network = node_temp.getExNetwork()
		kwargs = get_property(self, ex_network, 'ex_network',
					 kwargs,lambda x : x)
		ex_tags = node_temp.getExTags()
		if ex_tags:
			kwargs['ex_tags'] = jlist_obj_to_pylist(ex_tags)
		ex_metadata = node_temp.getExMetadata()
		if ex_metadata:
			kwargs['ex_metadata'] = jmap_to_pymap(ex_metadata)
		ex_boot_disk = node_temp.getExBootDisk()
		kwargs = get_property(self, ex_boot_disk, 'ex_boot_disk',
					 kwargs,lambda x : x.obj)
		use_existing_disk = node_temp.getUseExistingDisk()
		l = lambda x : True if x == 'true' or x == True else False
		kwargs = get_property(self, use_existing_disk, 'use_existing_disk',
					 kwargs,l)
		external_ip = node_temp.getExternalIp()
		kwargs = get_property(self, external_ip, 'external_ip',
					 kwargs,lambda x : x.obj)
		return kwargs

	def getTemplateBuilder(self):
		return GCENodeTemplateImpl.newBuilder()

	def exListAddresses(self, region=None):
		"""
        Return a list of static addreses for a region or all.

        :keyword  region: The region to return addresses from. For example:
                          'us-central1'.  If None, will return addresses from
                          region of self.zone.  If 'all', will return all
                          addresses.
        :type     region: ``str`` or ``None``

        :return: A list of static address objects.
        :rtype: ``list`` of :class:`GCEAddress`
        """
		try:
			if not region:
				region = None
			return wrap_listing(self.conn.ex_list_addresses(region), GCEAddressImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListHealthchecks(self):
		"""
        Return the list of health checks.

        :return: A list of health check objects.
        :rtype: ``list`` of :class:`GCEHealthCheck`
        """
		try:
			return wrap_listing(self.conn.ex_list_healthchecks(), GCEHealthCheckImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListFirewalls(self):
		"""
        Return the list of firewalls.

        :return: A list of firewall objects.
        :rtype: ``list`` of :class:`GCEFirewall`
        """
		try:
			return wrap_listing(self.conn.ex_list_firewalls(), GCEFirewallImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListForwardingRules(self, region=None):
		"""
        Return the list of forwarding rules for a region or all.

        :keyword  region: The region to return forwarding rules from.  For
                          example: 'us-central1'.  If None, will return
                          forwarding rules from the region of self.region
                          (which is based on self.zone).  If 'all', will
                          return all forwarding rules.
        :type     region: ``str`` or :class:`GCERegion` or ``None``

        :return: A list of forwarding rule objects.
        :rtype: ``list`` of :class:`GCEForwardingRule`
        """
		try:
			if not region:
				region = None
			return wrap_listing(self.conn.ex_list_forwarding_rules(region), GCEForwardingRuleImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListNetworks(self):
		"""
        Return the list of networks.

        :return: A list of network objects.
        :rtype: ``list`` of :class:`GCENetwork`
        """
		try:
			return wrap_listing(self.conn.ex_list_networks(), GCENetworkImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListRegions(self):
		"""
        Return the list of regions.

        :return: A list of region objects.
        :rtype: ``list`` of :class:`GCERegion`
        """
		try:
			return wrap_listing(self.conn.ex_list_regions(), GCERegionImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListSnapshots(self):
		"""
        Return the list of disk snapshots in the project.

        :return:  A list of snapshot objects
        :rtype:   ``list`` of :class:`GCESnapshot`
        """
		try:
			return wrap_listing(self.conn.ex_list_snapshots(), GCESnapshotImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListTargetpools(self, region=None):
		"""
        Return the list of target pools.

        :return:  A list of target pool objects
        :rtype:   ``list`` of :class:`GCETargetPool`
        """
		try:
			if not region:
				region = None
			return wrap_listing(self.conn.ex_list_targetpools(region), GCETargetPoolImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListZones(self):
		"""
        Return the list of zones.

        :return: A list of zone objects.
        :rtype: ``list`` of :class:`GCEZone`
        """
		try:
			return wrap_listing(self.conn.ex_list_zones(), GCEZoneImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateAddress(self, name, region=None):
		"""
        Create a static address in a region.

        :param  name: Name of static address
        :type   name: ``str``

        :keyword  region: Name of region for the address (e.g. 'us-central1')
        :type     region: ``str`` or :class:`GCERegion`

        :return:  Static Address object
        :rtype:   :class:`GCEAddress`
        """
		try:
			if not region:
				region = None
			return GCEAddressImpl(self.conn.ex_create_address(name, region))
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateHealthcheck(self, name, host=None, path=None, port=None, interval=None, 
					timeout=None, unhealthy_threshold=None, healthy_threshold=None):
		"""
        Create an Http Health Check.

        :param  name: Name of health check
        :type   name: ``str``

        :keyword  host: Hostname of health check requst.  Defaults to empty and
                        public IP is used instead.
        :type     host: ``str``

        :keyword  path: The request path for the check.  Defaults to /.
        :type     path: ``str``

        :keyword  port: The TCP port number for the check.  Defaults to 80.
        :type     port: ``int``

        :keyword  interval: How often (in seconds) to check.  Defaults to 5.
        :type     interval: ``int``

        :keyword  timeout: How long to wait before failing. Defaults to 5.
        :type     timeout: ``int``

        :keyword  unhealthy_threshold: How many failures before marking
                                       unhealthy.  Defaults to 2.
        :type     unhealthy_threshold: ``int``

        :keyword  healthy_threshold: How many successes before marking as
                                     healthy.  Defaults to 2.
        :type     healthy_threshold: ``int``

        :return:  Health Check object
        :rtype:   :class:`GCEHealthCheck`
        """
		try:
			if not healthy_threshold:
				healthy_threshold = None
			if not unhealthy_threshold:
				unhealthy_threshold = None
			if not timeout:
				timeout = None
			if not interval:
				interval = None
			if not port:
				port = None
			if not path:
				path = None
			if not host:
				host = None
			return GCEHealthCheckImpl(self.conn.ex_create_healthcheck(name, host, path, port, interval, timeout, unhealthy_threshold, healthy_threshold))
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateFirewall(self, name, allowed, network='default', source_ranges=None, source_tags=None):
		"""
        Create a firewall on a network.

        Firewall rules should be supplied in the "allowed" field.  This is a
        list of dictionaries formated like so ("ports" is optional)::

            [{"IPProtocol": "<protocol string or number>",
              "ports": "<port_numbers or ranges>"}]

        For example, to allow tcp on port 8080 and udp on all ports, 'allowed'
        would be::

            [{"IPProtocol": "tcp",
              "ports": ["8080"]},
             {"IPProtocol": "udp"}]

        See `Firewall Reference <https://developers.google.com/compute/docs/
        reference/latest/firewalls/insert>`_ for more information.

        :param  name: Name of the firewall to be created
        :type   name: ``str``

        :param  allowed: List of dictionaries with rules
        :type   allowed: ``list`` of ``dict``

        :keyword  network: The network that the firewall applies to.
        :type     network: ``str`` or :class:`GCENetwork`

        :keyword  source_ranges: A list of IP ranges in CIDR format that the
                                 firewall should apply to. Defaults to
                                 ['0.0.0.0/0']
        :type     source_ranges: ``list`` of ``str``

        :keyword  source_tags: A list of instance tags which the rules apply
        :type     source_tags: ``list`` of ``str``

        :return:  Firewall object
        :rtype:   :class:`GCEFirewall`
        """
		try:
			if not source_tags:
				source_tags = None
			if not source_ranges:
				source_ranges = None
			if not network:
				network = 'default'
			allowed = jlist_map_to_pylist_map(allowed)
			source_ranges = jlist_str_to_pylist(source_ranges)
			source_tags = jlist_str_to_pylist(source_tags)
			return GCEFirewallImpl(self.conn.ex_create_firewall(name, allowed, network, source_ranges, source_tags))
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateForwardingRule(self, name, targetpool, region=None, protocol='tcp', port_range=None, 
					address=None):
		"""
        Create a forwarding rule.

        :param  name: Name of forwarding rule to be created
        :type   name: ``str``

        :param  targetpool: Target pool to apply the rule to
        :param  targetpool: ``str`` or :class:`GCETargetPool`

        :keyword  region: Region to create the forwarding rule in.  Defaults to
                          self.region
        :type     region: ``str`` or :class:`GCERegion`

        :keyword  protocol: Should be 'tcp' or 'udp'
        :type     protocol: ``str``

        :keyword  port_range: Optional single port number or range separated
                              by a dash.  Examples: '80', '5000-5999'.
        :type     port_range: ``str``

        :keyword  address: Optional static address for forwarding rule. Must be
                           in same region.
        :type     address: ``str`` or :class:`GCEAddress`

        :return:  Forwarding Rule object
        :rtype:   :class:`GCEForwardingRule`
        """
		try:
			if not address:
				address = None
			if not port_range:
				port_range = None
			if not protocol:
				protocol = 'tcp'
			if not region:
				region = None
			return GCEForwardingRuleImpl(self.conn.ex_create_forwarding_rule(name, targetpool, region, protocol, port_range, address))
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateNetwork(self, name, cidr):
		"""
        Create a network.

        :param  name: Name of network to be created
        :type   name: ``str``

        :param  cidr: Address range of network in CIDR format.
        :type  cidr: ``str``

        :return:  Network object
        :rtype:   :class:`GCENetwork`
        """
		try:
			return GCENetworkImpl(self.conn.ex_create_network(name, cidr))
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateMultipleNodes(self, base_name, size, image, number, location=None, 
					ex_network='default', ex_tags=None, ex_metadata=None, ignore_errors=True, 
					use_existing_disk=True, poll_interval=2, external_ip='ephemeral', timeout=DEFAULT_TASK_COMPLETION_TIMEOUT):
		"""
        Create multiple nodes and return a list of Node objects.

        Nodes will be named with the base name and a number.  For example, if
        the base name is 'libcloud' and you create 3 nodes, they will be
        named::

            libcloud-000
            libcloud-001
            libcloud-002

        :param  base_name: The base name of the nodes to create.
        :type   base_name: ``str``

        :param  size: The machine type to use.
        :type   size: ``str`` or :class:`GCENodeSize`

        :param  image: The image to use to create the nodes.
        :type   image: ``str`` or :class:`NodeImage`

        :param  number: The number of nodes to create.
        :type   number: ``int``

        :keyword  location: The location (zone) to create the nodes in.
        :type     location: ``str`` or :class:`NodeLocation` or
                            :class:`GCEZone` or ``None``

        :keyword  ex_network: The network to associate with the nodes.
        :type     ex_network: ``str`` or :class:`GCENetwork`

        :keyword  ex_tags: A list of tags to assiciate with the nodes.
        :type     ex_tags: ``list`` of ``str`` or ``None``

        :keyword  ex_metadata: Metadata dictionary for instances.
        :type     ex_metadata: ``dict`` or ``None``

        :keyword  ignore_errors: If True, don't raise Exceptions if one or
                                 more nodes fails.
        :type     ignore_errors: ``bool``

        :keyword  use_existing_disk: If True and if an existing disk with the
                                     same name/location is found, use that
                                     disk instead of creating a new one.
        :type     use_existing_disk: ``bool``

        :keyword  poll_interval: Number of seconds between status checks.
        :type     poll_interval: ``int``

        :keyword  external_ip: The external IP address to use.  If 'ephemeral'
                               (default), a new non-static address will be
                               used. If 'None', then no external address will
                               be used. (Static addresses are not supported for
                               multiple node creation.)
        :type     external_ip: ``str`` or None

        :keyword  timeout: The number of seconds to wait for all nodes to be
                           created before timing out.
        :type     timeout: ``int``

        :return:  A list of Node objects for the new nodes.
        :rtype:   ``list`` of :class:`Node`
        """
		try:
			if not timeout:
				timeout = DEFAULT_TASK_COMPLETION_TIMEOUT
			if not external_ip:
				external_ip = 'ephemeral'
			if not poll_interval:
				poll_interval = 2
			if not use_existing_disk:
				use_existing_disk = True
			if not ignore_errors:
				ignore_errors = True
			if not ex_metadata:
				ex_metadata = None
			if not ex_tags:
				ex_tags = None
			if not ex_network:
				ex_network = 'default'
			if not location:
				location = None
			ex_tags = jlist_obj_to_pylist(ex_tags)
			ex_metadata = jmap_to_pymap(ex_metadata)
			return wrap_listing(self.conn.ex_create_multiple_nodes(base_name, size, image, number, location, ex_network, ex_tags, ex_metadata, ignore_errors, use_existing_disk, poll_interval, external_ip, timeout), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateTargetpool(self, name, region=None, healthchecks=None, nodes=None):
		"""
        Create a target pool.

        :param  name: Name of target pool
        :type   name: ``str``

        :keyword  region: Region to create the target pool in. Defaults to
                          self.region
        :type     region: ``str`` or :class:`GCERegion` or ``None``

        :keyword  healthchecks: Optional list of health checks to attach
        :type     healthchecks: ``list`` of ``str`` or :class:`GCEHealthCheck`

        :keyword  nodes:  Optional list of nodes to attach to the pool
        :type     nodes:  ``list`` of ``str`` or :class:`Node`

        :return:  Target Pool object
        :rtype:   :class:`GCETargetPool`
        """
		try:
			if not nodes:
				nodes = None
			if not healthchecks:
				healthchecks = None
			if not region:
				region = None
			healthchecks = jlist_obj_to_pylist(healthchecks)
			nodes = jlist_obj_to_pylist(nodes)
			return GCETargetPoolImpl(self.conn.ex_create_targetpool(name, region, healthchecks, nodes))
		except Exception, ex:
			raise wrap_exception(ex)

	def exUpdateHealthcheck(self, healthcheck):
		"""
        Update a health check with new values.

        To update, change the attributes of the health check object and pass
        the updated object to the method.

        :param  healthcheck: A healthcheck object with updated values.
        :type   healthcheck: :class:`GCEHealthCheck`

        :return:  An object representing the new state of the health check.
        :rtype:   :class:`GCEHealthCheck`
        """
		try:
			if healthcheck:
				healthcheck = healthcheck.obj
			return GCEHealthCheckImpl(self.conn.ex_update_healthcheck(healthcheck))
		except Exception, ex:
			raise wrap_exception(ex)

	def exUpdateFirewall(self, firewall):
		"""
        Update a firewall with new values.

        To update, change the attributes of the firewall object and pass the
        updated object to the method.

        :param  firewall: A firewall object with updated values.
        :type   firewall: :class:`GCEFirewall`

        :return:  An object representing the new state of the firewall.
        :rtype:   :class:`GCEFirewall`
        """
		try:
			if firewall:
				firewall = firewall.obj
			return GCEFirewallImpl(self.conn.ex_update_firewall(firewall))
		except Exception, ex:
			raise wrap_exception(ex)

	def exTargetpoolAddNode(self, targetpool, node):
		"""
        Add a node to a target pool.

        :param  targetpool: The targetpool to add node to
        :type   targetpool: ``str`` or :class:`GCETargetPool`

        :param  node: The node to add
        :type   node: ``str`` or :class:`Node`

        :returns: True if successful
        :rtype:   ``bool``
        """
		try:
			return self.conn.ex_targetpool_add_node(targetpool, node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exTargetpoolAddHealthcheck(self, targetpool, healthcheck):
		"""
        Add a health check to a target pool.

        :param  targetpool: The targetpool to add health check to
        :type   targetpool: ``str`` or :class:`GCETargetPool`

        :param  healthcheck: The healthcheck to add
        :type   healthcheck: ``str`` or :class:`GCEHealthCheck`

        :returns: True if successful
        :rtype:   ``bool``
        """
		try:
			return self.conn.ex_targetpool_add_healthcheck(targetpool, healthcheck)
		except Exception, ex:
			raise wrap_exception(ex)

	def exTargetpoolRemoveNode(self, targetpool, node):
		"""
        Remove a node from a target pool.

        :param  targetpool: The targetpool to remove node from
        :type   targetpool: ``str`` or :class:`GCETargetPool`

        :param  node: The node to remove
        :type   node: ``str`` or :class:`Node`

        :returns: True if successful
        :rtype:   ``bool``
        """
		try:
			return self.conn.ex_targetpool_remove_node(targetpool, node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exTargetpoolRemoveHealthcheck(self, targetpool, healthcheck):
		"""
        Remove a health check from a target pool.

        :param  targetpool: The targetpool to remove health check from
        :type   targetpool: ``str`` or :class:`GCETargetPool`

        :param  healthcheck: The healthcheck to remove
        :type   healthcheck: ``str`` or :class:`GCEHealthCheck`

        :returns: True if successful
        :rtype:   ``bool``
        """
		try:
			return self.conn.ex_targetpool_remove_healthcheck(targetpool, healthcheck)
		except Exception, ex:
			raise wrap_exception(ex)

	def exSetNodeTags(self, node, tags):
		"""
        Set the tags on a Node instance.

        Note that this updates the node object directly.

        :param  node: Node object
        :type   node: :class:`Node`

        :param  tags: List of tags to apply to the object
        :type   tags: ``list`` of ``str``

        :return:  True if successful
        :rtype:   ``bool``
        """
		try:
			if node:
				node = node.obj
			tags = jlist_str_to_pylist(tags)
			return self.conn.ex_set_node_tags(node, tags)
		except Exception, ex:
			raise wrap_exception(ex)

	def exSetNodeScheduling(self, node, on_host_maintenance=None, automatic_restart=None):
		"""Set the maintenance behavior for the node.

        See `Scheduling <https://developers.google.com/compute/
        docs/instances#onhostmaintenance>`_ documentation for more info.

        :param  node: Node object
        :type   node: :class:`Node`

        :keyword  on_host_maintenance: Defines whether node should be
                                       terminated or migrated when host machine
                                       goes down. Acceptable values are:
                                       'MIGRATE' or 'TERMINATE' (If not
                                       supplied, value will be reset to GCE
                                       default value for the instance type.)
        :type     on_host_maintenance: ``str``

        :keyword  automatic_restart: Defines whether the instance should be
                                     automatically restarted when it is
                                     terminated by Compute Engine. (If not
                                     supplied, value will be set to the GCE
                                     default value for the instance type.)
        :type     automatic_restart: ``bool``

        :return:  True if successful.
        :rtype:   ``bool``
        """
		try:
			if not automatic_restart:
				automatic_restart = None
			if not on_host_maintenance:
				on_host_maintenance = None
			if node:
				node = node.obj
			return self.conn.ex_set_node_scheduling(node, on_host_maintenance, automatic_restart)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDestroyAddress(self, address):
		"""
        Destroy a static address.

        :param  address: Address object to destroy
        :type   address: :class:`GCEAddress`

        :return:  True if successful
        :rtype:   ``bool``
        """
		try:
			if address:
				address = address.obj
			return self.conn.ex_destroy_address(address)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDestroyHealthcheck(self, healthcheck):
		"""
        Destroy a healthcheck.

        :param  healthcheck: Health check object to destroy
        :type   healthcheck: :class:`GCEHealthCheck`

        :return:  True if successful
        :rtype:   ``bool``
        """
		try:
			if healthcheck:
				healthcheck = healthcheck.obj
			return self.conn.ex_destroy_healthcheck(healthcheck)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDestroyFirewall(self, firewall):
		"""
        Destroy a firewall.

        :param  firewall: Firewall object to destroy
        :type   firewall: :class:`GCEFirewall`

        :return:  True if successful
        :rtype:   ``bool``
        """
		try:
			if firewall:
				firewall = firewall.obj
			return self.conn.ex_destroy_firewall(firewall)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDestroyForwardingRule(self, forwarding_rule):
		"""
        Destroy a forwarding rule.

        :param  forwarding_rule: Forwarding Rule object to destroy
        :type   forwarding_rule: :class:`GCEForwardingRule`

        :return:  True if successful
        :rtype:   ``bool``
        """
		try:
			if forwarding_rule:
				forwarding_rule = forwarding_rule.obj
			return self.conn.ex_destroy_forwarding_rule(forwarding_rule)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDestroyNetwork(self, network):
		"""
        Destroy a network.

        :param  network: Network object to destroy
        :type   network: :class:`GCENetwork`

        :return:  True if successful
        :rtype:   ``bool``
        """
		try:
			if network:
				network = network.obj
			return self.conn.ex_destroy_network(network)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDestroyMultipleNodes(self, node_list, ignore_errors=True, destroy_boot_disk=False, poll_interval=2, timeout=DEFAULT_TASK_COMPLETION_TIMEOUT):
		"""
        Destroy multiple nodes at once.

        :param  node_list: List of nodes to destroy
        :type   node_list: ``list`` of :class:`Node`

        :keyword  ignore_errors: If true, don't raise an exception if one or
                                 more nodes fails to be destroyed.
        :type     ignore_errors: ``bool``

        :keyword  destroy_boot_disk: If true, also destroy the nodes' boot
                                     disks.
        :type     destroy_boot_disk: ``bool``

        :keyword  poll_interval: Number of seconds between status checks.
        :type     poll_interval: ``int``

        :keyword  timeout: Number of seconds to wait for all nodes to be
                           destroyed.
        :type     timeout: ``int``

        :return:  A list of boolean values.  One for each node.  True means
                  that the node was successfully destroyed.
        :rtype:   ``list`` of ``bool``
        """
		try:
			if not timeout:
				timeout = DEFAULT_TASK_COMPLETION_TIMEOUT
			if not poll_interval:
				poll_interval = 2
			if not destroy_boot_disk:
				destroy_boot_disk = False
			if not ignore_errors:
				ignore_errors = True
			node_list = jlist_obj_to_pylist(node_list)
			return self.conn.ex_destroy_multiple_nodes(node_list, ignore_errors, destroy_boot_disk, poll_interval, timeout)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDestroyTargetpool(self, targetpool):
		"""
        Destroy a target pool.

        :param  targetpool: TargetPool object to destroy
        :type   targetpool: :class:`GCETargetPool`

        :return:  True if successful
        :rtype:   ``bool``
        """
		try:
			if targetpool:
				targetpool = targetpool.obj
			return self.conn.ex_destroy_targetpool(targetpool)
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetAddress(self, name, region=None):
		"""
        Return an Address object based on an address name and optional region.

        :param  name: The name of the address
        :type   name: ``str``

        :keyword  region: The region to search for the address in (set to
                          'all' to search all regions)
        :type     region: ``str`` :class:`GCERegion` or ``None``

        :return:  An Address object for the address
        :rtype:   :class:`GCEAddress`
        """
		try:
			if not region:
				region = None
			return GCEAddressImpl(self.conn.ex_get_address(name, region))
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetHealthcheck(self, name):
		"""
        Return a HealthCheck object based on the healthcheck name.

        :param  name: The name of the healthcheck
        :type   name: ``str``

        :return:  A GCEHealthCheck object
        :rtype:   :class:`GCEHealthCheck`
        """
		try:
			return GCEHealthCheckImpl(self.conn.ex_get_healthcheck(name))
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetFirewall(self, name):
		"""
        Return a Firewall object based on the firewall name.

        :param  name: The name of the firewall
        :type   name: ``str``

        :return:  A GCEFirewall object
        :rtype:   :class:`GCEFirewall`
        """
		try:
			return GCEFirewallImpl(self.conn.ex_get_firewall(name))
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetForwardingRule(self, name, region=None):
		"""
        Return a Forwarding Rule object based on the forwarding rule name.

        :param  name: The name of the forwarding rule
        :type   name: ``str``

        :keyword  region: The region to search for the rule in (set to 'all'
                          to search all regions).
        :type     region: ``str`` or ``None``

        :return:  A GCEForwardingRule object
        :rtype:   :class:`GCEForwardingRule`
        """
		try:
			if not region:
				region = None
			return GCEForwardingRuleImpl(self.conn.ex_get_forwarding_rule(name, region))
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetImage(self, partial_name):
		"""
        Return an NodeImage object based on the name or link provided.

        :param  partial_name: The name, partial name, or full path of a GCE
                              image.
        :type   partial_name: ``str``

        :return:  NodeImage object based on provided information or None if an
                  image with that name is not found.
        :rtype:   :class:`NodeImage` or ``None``
        """
		try:
			return NodeImageImpl(self.conn.ex_get_image(partial_name))
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetNetwork(self, name):
		"""
        Return a Network object based on a network name.

        :param  name: The name of the network
        :type   name: ``str``

        :return:  A Network object for the network
        :rtype:   :class:`GCENetwork`
        """
		try:
			return GCENetworkImpl(self.conn.ex_get_network(name))
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetNode(self, name, zone=None):
		"""
        Return a Node object based on a node name and optional zone.

        :param  name: The name of the node
        :type   name: ``str``

        :keyword  zone: The zone to search for the node in.  If set to 'all',
                        search all zones for the instance.
        :type     zone: ``str`` or :class:`GCEZone` or
                        :class:`NodeLocation` or ``None``

        :return:  A Node object for the node
        :rtype:   :class:`Node`
        """
		try:
			if not zone:
				zone = None
			return NodeImpl(self.conn.ex_get_node(name, zone))
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetProject(self):
		"""
        Return a Project object with project-wide information.

        :return:  A GCEProject object
        :rtype:   :class:`GCEProject`
        """
		try:
			return GCEProjectImpl(self.conn.ex_get_project())
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetSize(self, name, zone=None):
		"""
        Return a size object based on a machine type name and zone.

        :param  name: The name of the node
        :type   name: ``str``

        :keyword  zone: The zone to search for the machine type in
        :type     zone: ``str`` or :class:`GCEZone` or
                        :class:`NodeLocation` or ``None``

        :return:  A GCENodeSize object for the machine type
        :rtype:   :class:`GCENodeSize`
        """
		try:
			if not zone:
				zone = None
			return GCENodeSizeImpl(self.conn.ex_get_size(name, zone))
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetSnapshot(self, name):
		"""
        Return a Snapshot object based on snapshot name.

        :param  name: The name of the snapshot
        :type   name: ``str``

        :return:  A GCESnapshot object for the snapshot
        :rtype:   :class:`GCESnapshot`
        """
		try:
			return GCESnapshotImpl(self.conn.ex_get_snapshot(name))
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetVolume(self, name, zone=None):
		"""
        Return a Volume object based on a volume name and optional zone.

        :param  name: The name of the volume
        :type   name: ``str``

        :keyword  zone: The zone to search for the volume in (set to 'all' to
                        search all zones)
        :type     zone: ``str`` or :class:`GCEZone` or :class:`NodeLocation`
                        or ``None``

        :return:  A StorageVolume object for the volume
        :rtype:   :class:`StorageVolume`
        """
		try:
			if not zone:
				zone = None
			return StorageVolumeImpl(self.conn.ex_get_volume(name, zone))
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetRegion(self, name):
		"""
        Return a Region object based on the region name.

        :param  name: The name of the region.
        :type   name: ``str``

        :return:  A GCERegion object for the region
        :rtype:   :class:`GCERegion`
        """
		try:
			return GCERegionImpl(self.conn.ex_get_region(name))
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetTargetpool(self, name, region=None):
		"""
        Return a TargetPool object based on a name and optional region.

        :param  name: The name of the target pool
        :type   name: ``str``

        :keyword  region: The region to search for the target pool in (set to
                          'all' to search all regions).
        :type     region: ``str`` or :class:`GCERegion` or ``None``

        :return:  A TargetPool object for the pool
        :rtype:   :class:`GCETargetPool`
        """
		try:
			if not region:
				region = None
			return GCETargetPoolImpl(self.conn.ex_get_targetpool(name, region))
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetZone(self, name):
		"""
        Return a Zone object based on the zone name.

        :param  name: The name of the zone.
        :type   name: ``str``

        :return:  A GCEZone object for the zone or None if not found
        :rtype:   :class:`GCEZone` or ``None``
        """
		try:
			return GCEZoneImpl(self.conn.ex_get_zone(name))
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.gce import GCEAddress

class GCEAddressImpl(GCEAddress):

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
		if hasattr(obj, 'address'):
			self.addressp = none_check(obj.address, ' ')
		else:
			self.addressp = ' '
		if hasattr(obj, 'region'):
			self.regionp = none_check(obj.region, ' ')
		else:
			self.regionp = ' '
		if hasattr(obj, 'extra'):
			self.extrap = obj.extra, {}
		else:
			self.extrap = {}
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getName(self):
		return self.namep

	def getAddress(self):
		return self.addressp

	def getRegion(self):
		return self.regionp

	def getExtra(self):
		return self.extrap

	def toString(self):
		return self.reprp

	def destroy(self):
		"""
        Destroy this address.

        :return: True if successful
        :rtype:  ``bool``
        """
		try:
			return self.obj.destroy()
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.gce import GCEFailedDisk

class GCEFailedDiskImpl(GCEFailedDisk):

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
		if hasattr(obj, 'error'):
			self.errorp = none_check(obj.error, ' ')
		else:
			self.errorp = ' '
		if hasattr(obj, 'code'):
			self.codep = none_check(obj.code, ' ')
		else:
			self.codep = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getName(self):
		return self.namep

	def getError(self):
		return self.errorp

	def getCode(self):
		return self.codep

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.gce import GCEFailedNode

class GCEFailedNodeImpl(GCEFailedNode):

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
		if hasattr(obj, 'error'):
			self.errorp = none_check(obj.error, ' ')
		else:
			self.errorp = ' '
		if hasattr(obj, 'code'):
			self.codep = none_check(obj.code, ' ')
		else:
			self.codep = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getName(self):
		return self.namep

	def getError(self):
		return self.errorp

	def getCode(self):
		return self.codep

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.gce import GCEHealthCheck

class GCEHealthCheckImpl(GCEHealthCheck):

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
		if hasattr(obj, 'path'):
			self.pathp = none_check(obj.path, ' ')
		else:
			self.pathp = ' '
		if hasattr(obj, 'port'):
			self.portp = none_check(obj.port, -1)
		else:
			self.portp = -1
		if hasattr(obj, 'interval'):
			self.intervalp = none_check(obj.interval, ' ')
		else:
			self.intervalp = ' '
		if hasattr(obj, 'timeout'):
			self.timeoutp = none_check(obj.timeout, ' ')
		else:
			self.timeoutp = ' '
		if hasattr(obj, 'unhealthy_threshold'):
			self.unhealthy_thresholdp = none_check(obj.unhealthy_threshold, ' ')
		else:
			self.unhealthy_thresholdp = ' '
		if hasattr(obj, 'healthy_threshold'):
			self.healthy_thresholdp = none_check(obj.healthy_threshold, ' ')
		else:
			self.healthy_thresholdp = ' '
		if hasattr(obj, 'extra'):
			self.extrap = obj.extra, {}
		else:
			self.extrap = {}
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getName(self):
		return self.namep

	def getPath(self):
		return self.pathp

	def getPort(self):
		return self.portp

	def getInterval(self):
		return self.intervalp

	def getTimeout(self):
		return self.timeoutp

	def getUnhealthyThreshold(self):
		return self.unhealthy_thresholdp

	def getHealthyThreshold(self):
		return self.healthy_thresholdp

	def getExtra(self):
		return self.extrap

	def toString(self):
		return self.reprp

	def destroy(self):
		"""
        Destroy this Health Check.

        :return:  True if successful
        :rtype:   ``bool``
        """
		try:
			return self.obj.destroy()
		except Exception, ex:
			raise wrap_exception(ex)

	def update(self):
		"""
        Commit updated healthcheck values.

        :return:  Updated Healthcheck object
        :rtype:   :class:`GCEHealthcheck`
        """
		try:
			return GCEHealthcheckImpl(self.obj.update())
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.gce import GCEFirewall

class GCEFirewallImpl(GCEFirewall):

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
		if hasattr(obj, 'network'):
			self.networkp = none_check(obj.network, ' ')
		else:
			self.networkp = ' '
		if hasattr(obj, 'allowed'):
			self.allowedp = none_check(obj.allowed, ' ')
		else:
			self.allowedp = ' '
		if hasattr(obj, 'source_ranges'):
			self.source_rangesp = none_check(obj.source_ranges, ' ')
		else:
			self.source_rangesp = ' '
		if hasattr(obj, 'source_tags'):
			self.source_tagsp = none_check(obj.source_tags, ' ')
		else:
			self.source_tagsp = ' '
		if hasattr(obj, 'extra'):
			self.extrap = obj.extra, {}
		else:
			self.extrap = {}
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getName(self):
		return self.namep

	def getNetwork(self):
		return self.networkp

	def getAllowed(self):
		return self.allowedp

	def getSourceRanges(self):
		return self.source_rangesp

	def getSourceTags(self):
		return self.source_tagsp

	def getExtra(self):
		return self.extrap

	def toString(self):
		return self.reprp

	def destroy(self):
		"""
        Destroy this firewall.

        :return: True if successful
        :rtype:  ``bool``
        """
		try:
			return self.obj.destroy()
		except Exception, ex:
			raise wrap_exception(ex)

	def update(self):
		"""
        Commit updated firewall values.

        :return:  Updated Firewall object
        :rtype:   :class:`GCEFirewall`
        """
		try:
			return GCEFirewallImpl(self.obj.update())
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.gce import GCEForwardingRule

class GCEForwardingRuleImpl(GCEForwardingRule):

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
		if hasattr(obj, 'region'):
			self.regionp = none_check(obj.region, ' ')
		else:
			self.regionp = ' '
		if hasattr(obj, 'address'):
			self.addressp = none_check(obj.address, ' ')
		else:
			self.addressp = ' '
		if hasattr(obj, 'protocol'):
			self.protocolp = none_check(obj.protocol, ' ')
		else:
			self.protocolp = ' '
		if hasattr(obj, 'targetpool'):
			self.targetpoolp = none_check(obj.targetpool, ' ')
		else:
			self.targetpoolp = ' '
		if hasattr(obj, 'extra'):
			self.extrap = obj.extra, {}
		else:
			self.extrap = {}
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getName(self):
		return self.namep

	def getRegion(self):
		return self.regionp

	def getAddress(self):
		return self.addressp

	def getProtocol(self):
		return self.protocolp

	def getTargetpool(self):
		return self.targetpoolp

	def getExtra(self):
		return self.extrap

	def toString(self):
		return self.reprp

	def destroy(self):
		"""
        Destroy this Forwarding Rule

        :return:  True if successful
        :rtype:   ``bool``
        """
		try:
			return self.obj.destroy()
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.gce import GCENetwork

class GCENetworkImpl(GCENetwork):

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
		if hasattr(obj, 'cidr'):
			self.cidrp = none_check(obj.cidr, ' ')
		else:
			self.cidrp = ' '
		if hasattr(obj, 'extra'):
			self.extrap = obj.extra, {}
		else:
			self.extrap = {}
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getName(self):
		return self.namep

	def getCidr(self):
		return self.cidrp

	def getExtra(self):
		return self.extrap

	def toString(self):
		return self.reprp

	def destroy(self):
		"""
        Destroy this newtwork

        :return: True if successful
        :rtype:  ``bool``
        """
		try:
			return self.obj.destroy()
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.gce import GCENodeSize

class GCENodeSizeImpl(NodeSizeImpl, GCENodeSize):

	def __init__(self, obj):
		NodeSizeImpl.__init__(self, obj)
		if hasattr(obj, 'extra'):
			self.extrap = obj.extra, {}
		else:
			self.extrap = {}
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getExtra(self):
		return self.extrap

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.gce import GCEProject

class GCEProjectImpl(GCEProject):

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
		if hasattr(obj, 'metadata'):
			self.metadatap = none_check(obj.metadata, ' ')
		else:
			self.metadatap = ' '
		if hasattr(obj, 'quotas'):
			self.quotasp = none_check(obj.quotas, ' ')
		else:
			self.quotasp = ' '
		if hasattr(obj, 'extra'):
			self.extrap = obj.extra, {}
		else:
			self.extrap = {}
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getName(self):
		return self.namep

	def getMetadata(self):
		return self.metadatap

	def getQuotas(self):
		return self.quotasp

	def getExtra(self):
		return self.extrap

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.gce import GCERegion

class GCERegionImpl(GCERegion):

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
		if hasattr(obj, 'status'):
			self.statusp = none_check(obj.status, ' ')
		else:
			self.statusp = ' '
		if hasattr(obj, 'zones'):
			self.zonesp = none_check(obj.zones, ' ')
		else:
			self.zonesp = ' '
		if hasattr(obj, 'quotas'):
			self.quotasp = none_check(obj.quotas, ' ')
		else:
			self.quotasp = ' '
		if hasattr(obj, 'deprecated'):
			self.deprecatedp = none_check(obj.deprecated, ' ')
		else:
			self.deprecatedp = ' '
		if hasattr(obj, 'extra'):
			self.extrap = obj.extra, {}
		else:
			self.extrap = {}
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getName(self):
		return self.namep

	def getStatus(self):
		return self.statusp

	def getZones(self):
		return self.zonesp

	def getQuotas(self):
		return self.quotasp

	def getDeprecated(self):
		return self.deprecatedp

	def getExtra(self):
		return self.extrap

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.gce import GCESnapshot

class GCESnapshotImpl(VolumeSnapshotImpl, GCESnapshot):

	def __init__(self, obj):
		VolumeSnapshotImpl.__init__(self, obj)
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
		if hasattr(obj, 'status'):
			self.statusp = none_check(obj.status, ' ')
		else:
			self.statusp = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getName(self):
		return self.namep

	def getStatus(self):
		return self.statusp

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.gce import GCETargetPool

class GCETargetPoolImpl(GCETargetPool):

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
		if hasattr(obj, 'region'):
			self.regionp = none_check(obj.region, ' ')
		else:
			self.regionp = ' '
		if hasattr(obj, 'healthchecks'):
			self.healthchecksp = none_check(obj.healthchecks, ' ')
		else:
			self.healthchecksp = ' '
		if hasattr(obj, 'nodes'):
			self.nodesp = wrap_listing(obj.nodes, NodeImpl)
		else:
			self.nodesp = wrap_listing(None, NodeImpl)
		if hasattr(obj, 'extra'):
			self.extrap = obj.extra, {}
		else:
			self.extrap = {}
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getName(self):
		return self.namep

	def getRegion(self):
		return self.regionp

	def getHealthchecks(self):
		return self.healthchecksp

	def getNodes(self):
		return self.nodesp

	def getExtra(self):
		return self.extrap

	def toString(self):
		return self.reprp

	def addNode(self, node):
		"""
        Add a node to this target pool.

        :param  node: Node to add
        :type   node: ``str`` or :class:`Node`

        :return:  True if successful
        :rtype:   ``bool``
        """
		try:
			return self.obj.add_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def removeNode(self, node):
		"""
        Remove a node from this target pool.

        :param  node: Node to remove
        :type   node: ``str`` or :class:`Node`

        :return:  True if successful
        :rtype:   ``bool``
        """
		try:
			return self.obj.remove_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def addHealthcheck(self, healthcheck):
		"""
        Add a healthcheck to this target pool.

        :param  healthcheck: Healthcheck to add
        :type   healthcheck: ``str`` or :class:`GCEHealthCheck`

        :return:  True if successful
        :rtype:   ``bool``
        """
		try:
			return self.obj.add_healthcheck(healthcheck)
		except Exception, ex:
			raise wrap_exception(ex)

	def removeHealthcheck(self, healthcheck):
		"""
        Remove a healthcheck from this target pool.

        :param  healthcheck: Healthcheck to remove
        :type   healthcheck: ``str`` or :class:`GCEHealthCheck`

        :return:  True if successful
        :rtype:   ``bool``
        """
		try:
			return self.obj.remove_healthcheck(healthcheck)
		except Exception, ex:
			raise wrap_exception(ex)

	def destroy(self):
		"""
        Destroy this Target Pool

        :return:  True if successful
        :rtype:   ``bool``
        """
		try:
			return self.obj.destroy()
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.gce import GCEZone

class GCEZoneImpl(NodeLocationImpl, GCEZone):

	def __init__(self, obj):
		NodeLocationImpl.__init__(self, obj)
		if hasattr(obj, 'status'):
			self.statusp = none_check(obj.status, ' ')
		else:
			self.statusp = ' '
		if hasattr(obj, 'maintenance_windows'):
			self.maintenance_windowsp = none_check(obj.maintenance_windows, ' ')
		else:
			self.maintenance_windowsp = ' '
		if hasattr(obj, 'deprecated'):
			self.deprecatedp = none_check(obj.deprecated, ' ')
		else:
			self.deprecatedp = ' '
		if hasattr(obj, 'extra'):
			self.extrap = obj.extra, {}
		else:
			self.extrap = {}
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getStatus(self):
		return self.statusp

	def getMaintenanceWindows(self):
		return self.maintenance_windowsp

	def getDeprecated(self):
		return self.deprecatedp

	def getExtra(self):
		return self.extrap

	def toString(self):
		return self.reprp

	def timeUntilMw(self):
		"""
        Returns the time until the next Maintenance Window as a
        datetime.timedelta object.
        """
		try:
			return self.obj.time_until_mw()
		except Exception, ex:
			raise wrap_exception(ex)

	def nextMwDuration(self):
		"""
        Returns the duration of the next Maintenance Window as a
        datetime.timedelta object.
        """
		try:
			return self.obj.next_mw_duration()
		except Exception, ex:
			raise wrap_exception(ex)

