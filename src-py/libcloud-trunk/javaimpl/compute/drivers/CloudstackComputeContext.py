# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/cloudstack.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/cloudstack.py
#
# Apache Libcloud is licensed under the Apache 2.0 license.
# For more information, please see LICENSE and NOTICE file or:
# http://www.apache.org/licenses/LICENSE-2.0
from javaimpl.compute.ComputeContextImpl import ComputeContextImpl
from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception, jlist_str_to_pylist
from javaimpl.compute.utils import jlist_obj_to_pylist, get_property, get_property_list
from javaimpl.compute.utils import jmap_to_pymap, jlist_map_to_pylist_map
from javaimpl.compute.base.NodeLocationImpl import NodeLocationImpl
from javaimpl.compute.base.NodeImpl import NodeImpl
from org.askalon.jlibcloud.compute.driverSpecific.cloudstack import CloudStackNodeTemplateImpl
from org.askalon.jlibcloud.compute.driverSpecific.cloudstack import CloudStackComputeContext

class CloudStackComputeContextImpl(ComputeContextImpl, CloudStackComputeContext):
	def __init__(self, builder):
		ComputeContext.__init__(self, builder)

	def createNode(self, node_temp):
		"""
        Create a new node

        @inherits: :class:`NodeDriver.create_node`

        :keyword    networks: Optional list of networks to launch the server
                              into.
        :type       networks: ``list`` of :class:`.CloudStackNetwork`

        :keyword    ex_keyname:  Name of existing keypair
        :type       ex_keyname:  ``str``

        :keyword    ex_userdata: String containing user data
        :type       ex_userdata: ``str``

        :keyword    ex_security_groups: List of security groups to assign to
                                        the node
        :type       ex_security_groups: ``list`` of ``str``

        :rtype:     :class:`.CloudStackNode`
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_cloudstack_template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_cloudstack_template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_cloudstack_template(self, node_temp, kwargs):
		networks = node_temp.getNetworks()
		if networks:
			kwargs['networks'] = jlist_obj_to_pylist(networks)
		ex_keyname = node_temp.getExKeyname()
		kwargs = get_property(self, ex_keyname, 'ex_keyname',
					 kwargs,lambda x : x)
		ex_userdata = node_temp.getExUserdata()
		kwargs = get_property(self, ex_userdata, 'ex_userdata',
					 kwargs,lambda x : x)
		ex_security_groups = node_temp.getExSecurityGroups()
		kwargs = get_property_list(self, ex_security_groups, 'ex_security_groups',
					 kwargs,lambda x : jlist_str_to_pylist(x))
		return kwargs

	def listNodes(self):
		try:
			return wrap_listing(self.conn.list_nodes(), CloudStackNodeImpl)
		except Exception, ex:
		   raise wrap_exception(ex)

	def getTemplateBuilder(self):
		return CloudStackNodeTemplateImpl.newBuilder()

	def exStart(self, node):
		"""
        Starts/Resumes a stopped virtual machine

        :type node: :class:`CloudStackNode`

        :param id: The ID of the virtual machine (required)
        :type  id: ``str``

        :param hostid: destination Host ID to deploy the VM to
                       parameter available for root admin only
        :type  hostid: ``str``

        :rtype ``str``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_start(node, **kwargs)
		except Exception, ex:
			raise wrap_exception(ex)

	def exStop(self, node):
		"""
        Stops/Suspends a running virtual machine

        :param node: Node to stop.
        :type node: :class:`CloudStackNode`

        :rtype: ``str``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_stop(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListDiskOfferings(self):
		"""
        Fetch a list of all available disk offerings.

        :rtype: ``list`` of :class:`CloudStackDiskOffering`
        """
		try:
			return wrap_listing(self.conn.ex_list_disk_offerings(), CloudStackDiskOfferingImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListNetworks(self):
		"""
        List the available networks

        :rtype ``list`` of :class:`CloudStackNetwork`
        """
		try:
			return wrap_listing(self.conn.ex_list_networks(), CloudStackNetworkImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListPublicIps(self):
		"""
        Lists all Public IP Addresses.

        :rtype: ``list`` of :class:`CloudStackAddress`
        """
		try:
			return wrap_listing(self.conn.ex_list_public_ips(), CloudStackAddressImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exAllocatePublicIp(self, location=None):
		"""
        Allocate a public IP.

        :param location: Zone
        :type  location: :class:`NodeLocation`

        :rtype: :class:`CloudStackAddress`
        """
		try:
			if location:
				location = location.obj
			if not location:
				location = None
			return CloudStackAddressImpl(self.conn.ex_allocate_public_ip(location))
		except Exception, ex:
			raise wrap_exception(ex)

	def exReleasePublicIp(self, address):
		"""
        Release a public IP.

        :param address: CloudStackAddress which should be used
        :type  address: :class:`CloudStackAddress`

        :rtype: ``bool``
        """
		try:
			if address:
				address = address.obj
			return self.conn.ex_release_public_ip(address)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListPortForwardingRules(self):
		"""
        Lists all Port Forwarding Rules

        :rtype: ``list`` of :class:`CloudStackPortForwardingRule`
        """
		try:
			return wrap_listing(self.conn.ex_list_port_forwarding_rules(), CloudStackPortForwardingRuleImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreatePortForwardingRule(self, node, address, private_port, public_port, protocol, 
					public_end_port=None, private_end_port=None, openfirewall=True):
		"""
        Creates a Port Forwarding Rule, used for Source NAT

        :param  address: IP address of the Source NAT
        :type   address: :class:`CloudStackAddress`

        :param  private_port: Port of the virtual machine
        :type   private_port: ``int``

        :param  protocol: Protocol of the rule
        :type   protocol: ``str``

        :param  public_port: Public port on the Source NAT address
        :type   public_port: ``int``

        :param  node: The virtual machine
        :type   node: :class:`CloudStackNode`

        :rtype: :class:`CloudStackPortForwardingRule`
        """
		try:
			if not openfirewall:
				openfirewall = True
			if not private_end_port:
				private_end_port = None
			if not public_end_port:
				public_end_port = None
			if address:
				address = address.obj
			if node:
				node = node.obj
			return CloudStackPortForwardingRuleImpl(self.conn.ex_create_port_forwarding_rule(node, address, private_port, public_port, protocol, public_end_port, private_end_port, openfirewall))
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeletePortForwardingRule(self, node, rule):
		"""
        Remove a Port forwarding rule.

        :param node: Node used in the rule
        :type  node: :class:`CloudStackNode`

        :param rule: Forwarding rule which should be used
        :type  rule: :class:`CloudStackPortForwardingRule`

        :rtype: ``bool``
        """
		try:
			if rule:
				rule = rule.obj
			if node:
				node = node.obj
			return self.conn.ex_delete_port_forwarding_rule(node, rule)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateIpForwardingRule(self, node, address, protocol, start_port, end_port=None):
		"""
        "Add a NAT/firewall forwarding rule.

        :param      node: Node which should be used
        :type       node: :class:`CloudStackNode`

        :param      address: CloudStackAddress which should be used
        :type       address: :class:`CloudStackAddress`

        :param      protocol: Protocol which should be used (TCP or UDP)
        :type       protocol: ``str``

        :param      start_port: Start port which should be used
        :type       start_port: ``int``

        :param      end_port: End port which should be used
        :type       end_port: ``int``

        :rtype:     :class:`CloudStackForwardingRule`
        """
		try:
			if not end_port:
				end_port = None
			if address:
				address = address.obj
			if node:
				node = node.obj
			return CloudStackIPForwardingRuleImpl(self.conn.ex_create_ip_forwarding_rule(node, address, protocol, start_port, end_port))
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeleteIpForwardingRule(self, node, rule):
		"""
        Remove a NAT/firewall forwarding rule.

        :param node: Node which should be used
        :type  node: :class:`CloudStackNode`

        :param rule: Forwarding rule which should be used
        :type  rule: :class:`CloudStackForwardingRule`

        :rtype: ``bool``
        """
		try:
			if rule:
				rule = rule.obj
			if node:
				node = node.obj
			return self.conn.ex_delete_ip_forwarding_rule(node, rule)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListKeypairs(self, projectid, page, keyword, listall, 
					pagesize, account, isrecursive, fingerprint, 
					name, domainid):
		"""
        List Registered SSH Key Pairs

        :param     projectid: list objects by project
        :type      projectid: ``str``

        :param     page: The page to list the keypairs from
        :type      page: ``int``

        :param     keyword: List by keyword
        :type      keyword: ``str``

        :param     listall: If set to false, list only resources
                            belonging to the command's caller;
                            if set to true - list resources that
                            the caller is authorized to see.
                            Default value is false

        :type      listall: ``bool``

        :param     pagesize: The number of results per page
        :type      pagesize: ``int``

        :param     account: List resources by account.
                            Must be used with the domainId parameter
        :type      account: ``str``

        :param     isrecursive: Defaults to false, but if true,
                                lists all resources from
                                the parent specified by the
                                domainId till leaves.
        :type      isrecursive: ``bool``

        :param     fingerprint: A public key fingerprint to look for
        :type      fingerprint: ``str``

        :param     name: A key pair name to look for
        :type      name: ``str``

        :param     domainid: List only resources belonging to
                                     the domain specified
        :type      domainid: ``str``

        :return:   A list of keypair dictionaries
        :rtype:   ``list`` of ``dict``
        """
		try:
			kwargs = {}
			if projectid:
				kwargs['projectid'] = projectid
			if page:
				kwargs['page'] = page
			if keyword:
				kwargs['keyword'] = keyword
			if listall:
				kwargs['listall'] = listall
			if pagesize:
				kwargs['pagesize'] = pagesize
			if account:
				kwargs['account'] = account
			if isrecursive:
				kwargs['isrecursive'] = isrecursive
			if fingerprint:
				kwargs['fingerprint'] = fingerprint
			if name:
				kwargs['name'] = name
			if domainid:
				kwargs['domainid'] = domainid
			return self.conn.ex_list_keypairs(**kwargs)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateKeypair(self, name, projectid, domainid, account):
		"""
        Creates a SSH KeyPair, returns fingerprint and private key

        :param     name: Name of the keypair (required)
        :type      name: ``str``

        :param     projectid: An optional project for the ssh key
        :type      projectid: ``str``

        :param     domainid: An optional domainId for the ssh key.
                             If the account parameter is used,
                             domainId must also be used.
        :type      domainid: ``str``

        :param     account: An optional account for the ssh key.
                            Must be used with domainId.
        :type      account: ``str``

        :return:   A keypair dictionary
        :rtype:    ``dict``
        """
		try:
			kwargs = {}
			if projectid:
				kwargs['projectid'] = projectid
			if domainid:
				kwargs['domainid'] = domainid
			if account:
				kwargs['account'] = account
			return self.conn.ex_create_keypair(name, **kwargs)
		except Exception, ex:
			raise wrap_exception(ex)

	def exImportKeypairFromString(self, name, key_material):
		"""
        Imports a new public key where the public key is passed in as a string

        :param     name: The name of the public key to import.
        :type      name: ``str``

        :param     key_material: The contents of a public key file.
        :type      key_material: ``str``

        :rtype: ``dict``
        """
		try:
			return self.conn.ex_import_keypair_from_string(name, key_material)
		except Exception, ex:
			raise wrap_exception(ex)

	def exImportKeypair(self, name, keyfile):
		"""
        Imports a new public key where the public key is passed via a filename

        :param     name: The name of the public key to import.
        :type      name: ``str``

        :param     keyfile: The filename with path of the public key to import.
        :type      keyfile: ``str``

        :rtype: ``dict``
        """
		try:
			return self.conn.ex_import_keypair(name, keyfile)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeleteKeypair(self, keypair, projectid, domainid, account):
		"""
        Deletes an existing SSH KeyPair

        :param     keypair: Name of the keypair (required)
        :type      keypair: ``str``

        :param     projectid: The project associated with keypair
        :type      projectid: ``str``

        :param     domainid: The domain ID associated with the keypair
        :type      domainid: ``str``

        :param     account: The account associated with the keypair.
                             Must be used with the domainId parameter.
        :type      account: ``str``

        :return:   True of False based on success of Keypair deletion
        :rtype:    ``bool``
        """
		try:
			kwargs = {}
			if projectid:
				kwargs['projectid'] = projectid
			if domainid:
				kwargs['domainid'] = domainid
			if account:
				kwargs['account'] = account
			return self.conn.ex_delete_keypair(keypair, **kwargs)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListSecurityGroups(self, domainid, account, listall, pagesize, 
					keyword, tags, id, securitygroupname, 
					virtualmachineid, projectid, isrecursive, page):
		"""
        Lists Security Groups

        :param domainid: List only resources belonging to the domain specified
        :type  domainid: ``str``

        :param account: List resources by account. Must be used with
                                                   the domainId parameter.
        :type  account: ``str``

        :param listall: If set to false, list only resources belonging to
                                         the command's caller; if set to true
                                         list resources that the caller is
                                         authorized to see.
                                         Default value is false
        :type  listall: ``bool``

        :param pagesize: Number of entries per page
        :type  pagesize: ``int``

        :param keyword: List by keyword
        :type  keyword: ``str``

        :param tags: List resources by tags (key/value pairs)
        :type  tags: ``dict``

        :param id: list the security group by the id provided
        :type  id: ``str``

        :param securitygroupname: lists security groups by name
        :type  securitygroupname: ``str``

        :param virtualmachineid: lists security groups by virtual machine id
        :type  virtualmachineid: ``str``

        :param projectid: list objects by project
        :type  projectid: ``str``

        :param isrecursive: (boolean) defaults to false, but if true,
                                      lists all resources from the parent
                                      specified by the domainId till leaves.
        :type  isrecursive: ``bool``

        :param page: (integer)
        :type  page: ``int``

        :rtype ``list``
        """
		try:
			kwargs = {}
			if domainid:
				kwargs['domainid'] = domainid
			if account:
				kwargs['account'] = account
			if listall:
				kwargs['listall'] = listall
			if pagesize:
				kwargs['pagesize'] = pagesize
			if keyword:
				kwargs['keyword'] = keyword
			tags = jmap_to_pymap(tags)
			if tags:
				kwargs['tags'] = tags
			if id:
				kwargs['id'] = id
			if securitygroupname:
				kwargs['securitygroupname'] = securitygroupname
			if virtualmachineid:
				kwargs['virtualmachineid'] = virtualmachineid
			if projectid:
				kwargs['projectid'] = projectid
			if isrecursive:
				kwargs['isrecursive'] = isrecursive
			if page:
				kwargs['page'] = page
			return self.conn.ex_list_security_groups(**kwargs)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateSecurityGroup(self, name, account, domainid, description, 
					projectid):
		"""
        Creates a new Security Group

        :param name: name of the security group (required)
        :type  name: ``str``

        :param account: An optional account for the security group.
                        Must be used with domainId.
        :type  account: ``str``

        :param domainid: An optional domainId for the security group.
                         If the account parameter is used,
                         domainId must also be used.
        :type  domainid: ``str``

        :param description: The description of the security group
        :type  description: ``str``

        :param projectid: Deploy vm for the project
        :type  projectid: ``str``

        :rtype: ``dict``
        """
		try:
			kwargs = {}
			if account:
				kwargs['account'] = account
			if domainid:
				kwargs['domainid'] = domainid
			if description:
				kwargs['description'] = description
			if projectid:
				kwargs['projectid'] = projectid
			return self.conn.ex_create_security_group(name, **kwargs)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeleteSecurityGroup(self, name):
		"""
        Deletes a given Security Group

        :param domainid: The domain ID of account owning
                         the security group
        :type  domainid: ``str``

        :param id: The ID of the security group.
                   Mutually exclusive with name parameter
        :type  id: ``str``

        :param name: The ID of the security group.
                     Mutually exclusive with id parameter
        :type name: ``str``

        :param account: The account of the security group.
                        Must be specified with domain ID
        :type  account: ``str``

        :param projectid:  The project of the security group
        :type  projectid:  ``str``

        :rtype: ``bool``
        """
		try:
			return self.conn.ex_delete_security_group(name)
		except Exception, ex:
			raise wrap_exception(ex)

	def exAuthorizeSecurityGroupIngress(self, securitygroupname, protocol, cidrlist, startport, endport=None):
		"""
        Creates a new Security Group Ingress rule

        :param domainid: An optional domainId for the security group.
                         If the account parameter is used,
                         domainId must also be used.
        :type domainid: ``str``

        :param startport: Start port for this ingress rule
        :type  startport: ``int``

        :param securitygroupid: The ID of the security group.
                                Mutually exclusive with securityGroupName
                                parameter
        :type  securitygroupid: ``str``

        :param cidrlist: The cidr list associated
        :type  cidrlist: ``list``

        :param usersecuritygrouplist: user to security group mapping
        :type  usersecuritygrouplist: ``dict``

        :param securitygroupname: The name of the security group.
                                  Mutually exclusive with
                                  securityGroupName parameter
        :type  securitygroupname: ``str``

        :param account: An optional account for the security group.
                        Must be used with domainId.
        :type  account: ``str``

        :param icmpcode: Error code for this icmp message
        :type  icmpcode: ``int``

        :param protocol: TCP is default. UDP is the other supported protocol
        :type  protocol: ``str``

        :param icmptype: type of the icmp message being sent
        :type  icmptype: ``int``

        :param projectid: An optional project of the security group
        :type  projectid: ``str``

        :param endport: end port for this ingress rule
        :type  endport: ``int``

        :rtype: ``list``
        """
		try:
			if not endport:
				endport = None
			cidrlist = jlist_str_to_pylist(cidrlist)
			return self.conn.ex_authorize_security_group_ingress(securitygroupname, protocol, cidrlist, startport, endport)
		except Exception, ex:
			raise wrap_exception(ex)

	def exRegisterIso(self, name, url, location=None):
		"""
        Registers an existing ISO by URL.

        :param      name: Name which should be used
        :type       name: ``str``

        :param      url: Url should be used
        :type       url: ``str``

        :param      location: Location which should be used
        :type       location: :class:`NodeLocation`

        :rtype: ``str``
        """
		try:
			if location:
				location = location.obj
			if not location:
				location = None
			return self.conn.ex_register_iso(name, url, location)
		except Exception, ex:
			raise wrap_exception(ex)

	def exLimits(self):
		"""
        Extra call to get account's resource limits, such as
        the amount of instances, volumes, snapshots and networks.

        CloudStack uses integers as the resource type so we will convert
        them to a more human readable string using the resource map

        :return: dict
        :rtype: ``dict``
        """
		try:
			return self.conn.ex_limits()
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.cloudstack import CloudStackNode

class CloudStackNodeImpl(NodeImpl, CloudStackNode):
	'''
    Subclass of Node so we can expose our extension methods.
	'''
	def __init__(self, node):
		NodeImpl.__init__(self, node)

	def exAllocatePublicIp(self):
		"""
        Allocate a public IP and bind it to this node.
        """
		try:
			return CloudStackAddressImpl(self.obj.ex_allocate_public_ip())
		except Exception, ex:
			raise wrap_exception(ex)

	def exReleasePublicIp(self, address):
		"""
        Release a public IP that this node holds.
        """
		try:
			if adress:
				adress = adress.obj
			return self.obj.ex_release_public_ip(address)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateIpForwardingRule(self, address, protocol, start_port, end_port=None):
		"""
        Add a NAT/firewall forwarding rule for a port or ports.
        """
		try:
			if not end_port:
				end_port = None
			if address:
				address = address.obj
			return CloudStackIPForwardingRuleImpl(self.obj.ex_create_ip_forwarding_rule(address, protocol, start_port, end_port))
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreatePortForwardingRule(self, address, private_port, public_port, protocol, public_end_port=None, 
					private_end_port=None, openfirewall=True):
		"""
        Add a port forwarding rule for port or ports.
        """
		try:
			if not openfirewall:
				openfirewall = True
			if not private_end_port:
				private_end_port = None
			if not public_end_port:
				public_end_port = None
			if address:
				address = address.obj
			return CloudStackPortForwardingRuleImpl(self.obj.ex_create_port_forwarding_rule(address, private_port, public_port, protocol, public_end_port, private_end_port, openfirewall))
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeleteIpForwardingRule(self, rule):
		"""
        Delete a port forwarding rule.
        """
		try:
			if rule:
				rule = rule.obj
			return self.obj.ex_delete_ip_forwarding_rule(rule)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeletePortForwardingRule(self, rule):
		"""
        Delete a NAT/firewall rule.
        """
		try:
			if rule:
				rule = rule.obj
			return self.obj.ex_delete_port_forwarding_rule(rule)
		except Exception, ex:
			raise wrap_exception(ex)

	def exStart(self):
		"""
        Starts a stopped virtual machine.
        """
		try:
			return self.obj.ex_start()
		except Exception, ex:
			raise wrap_exception(ex)

	def exStop(self):
		"""
        Stops a running virtual machine.
        """
		try:
			return self.obj.ex_stop()
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.cloudstack import CloudStackAddress

class CloudStackAddressImpl(CloudStackAddress):
	'''
    A public IP address.
	'''

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'address'):
			self.addressp = none_check(obj.address, ' ')
		else:
			self.addressp = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getAddress(self):
		return self.addressp

	def toString(self):
		return self.reprp

	def release(self):
		try:
			return self.obj.release()
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.cloudstack import CloudStackIPForwardingRule

class CloudStackIPForwardingRuleImpl(CloudStackIPForwardingRule):
	'''
    A NAT/firewall forwarding rule.
	'''

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'node'):
			self.nodep = CloudStackNodeImpl(obj.node)
		else:
			self.nodep = CloudStackNodeImpl(None)
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'address'):
			self.addressp = CloudStackAddressImpl(obj.address)
		else:
			self.addressp = ' '
		if hasattr(obj, 'protocol'):
			self.protocolp = CloudStackAddressImpl(None)
		else:
			self.protocolp = ' '
		if hasattr(obj, 'start_port'):
			self.start_portp = none_check(obj.start_port, -1)
		else:
			self.start_portp = -1
		if hasattr(obj, 'end_port'):
			self.end_portp = none_check(obj.end_port, -1)
		else:
			self.end_portp = -1
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getNode(self):
		return self.nodep

	def getId(self):
		return self.idp

	def getAddress(self):
		return self.addressp

	def getProtocol(self):
		return self.protocolp

	def getStartPort(self):
		return self.start_portp

	def getEndPort(self):
		return self.end_portp

	def toString(self):
		return self.reprp

	def delete(self):
		try:
			return self.obj.delete()
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.cloudstack import CloudStackPortForwardingRule

class CloudStackPortForwardingRuleImpl(CloudStackPortForwardingRule):
	'''
    A Port forwarding rule for Source NAT.
	'''

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'node'):
			self.nodep = CloudStackNodeImpl(obj.node)
		else:
			self.nodep = CloudStackNodeImpl(None)
		if hasattr(obj, 'rule_id'):
			self.rule_idp = none_check(obj.rule_id, -1)
		else:
			self.rule_idp = -1
		if hasattr(obj, 'address'):
			self.addressp = CloudStackAddressImpl(obj.address)
		else:
			self.addressp = CloudStackAddressImpl(None)
		if hasattr(obj, 'protocol'):
			self.protocolp = none_check(obj.protocol, ' ')
		else:
			self.protocolp = ' '
		if hasattr(obj, 'public_port'):
			self.public_portp = none_check(obj.public_port, -1)
		else:
			self.public_portp = -1
		if hasattr(obj, 'private_port'):
			self.private_portp = none_check(obj.private_port, -1)
		else:
			self.private_portp = -1
		if hasattr(obj, 'public_end_port'):
			self.public_end_portp = none_check(obj.public_end_port, -1)
		else:
			self.public_end_portp = -1
		if hasattr(obj, 'private_end_port'):
			self.private_end_portp = none_check(obj.private_end_port, -1)
		else:
			self.private_end_portp = -1
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getNode(self):
		return self.nodep

	def getRuleId(self):
		return self.rule_idp

	def getAddress(self):
		return self.addressp

	def getProtocol(self):
		return self.protocolp

	def getPublicPort(self):
		return self.public_portp

	def getPrivatePort(self):
		return self.private_portp

	def getPublicEndPort(self):
		return self.public_end_portp

	def getPrivateEndPort(self):
		return self.private_end_portp

	def getId(self):
		return self.idp

	def toString(self):
		return self.reprp

	def delete(self):
		try:
			return self.obj.delete()
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.cloudstack import CloudStackDiskOffering

class CloudStackDiskOfferingImpl(CloudStackDiskOffering):
	'''
    A disk offering within CloudStack.
	'''

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
		if hasattr(obj, 'size'):
			self.sizep = NodeSizeImpl(obj.size)
		else:
			self.sizep = NodeSizeImpl(None)
		if hasattr(obj, 'customizable'):
			self.customizablep = none_check(obj.customizable, ' ')
		else:
			self.customizablep = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getName(self):
		return self.namep

	def getSize(self):
		return self.sizep

	def getCustomizable(self):
		return self.customizablep

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.cloudstack import CloudStackNetwork

class CloudStackNetworkImpl(CloudStackNetwork):
	'''
    Class representing a CloudStack Network.
	'''

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'displaytext'):
			self.displaytextp = none_check(obj.displaytext, ' ')
		else:
			self.displaytextp = ' '
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
		if hasattr(obj, 'networkofferingid'):
			self.networkofferingidp = none_check(obj.networkofferingid, ' ')
		else:
			self.networkofferingidp = ' '
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'zoneid'):
			self.zoneidp = none_check(obj.zoneid, ' ')
		else:
			self.zoneidp = ' '
		if hasattr(obj, 'extra'):
			self.extrap = obj.extra, {}
		else:
			self.extrap = {}
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getDisplaytext(self):
		return self.displaytextp

	def getName(self):
		return self.namep

	def getNetworkofferingid(self):
		return self.networkofferingidp

	def getId(self):
		return self.idp

	def getZoneid(self):
		return self.zoneidp

	def getExtra(self):
		return self.extrap

	def toString(self):
		return self.reprp

