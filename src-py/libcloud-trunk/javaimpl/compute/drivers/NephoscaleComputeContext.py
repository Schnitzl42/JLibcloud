# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/nephoscale.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/nephoscale.py
#
# Apache Libcloud is licensed under the Apache 2.0 license.
# For more information, please see LICENSE and NOTICE file or:
# http://www.apache.org/licenses/LICENSE-2.0
from javaimpl.compute.ComputeContextImpl import ComputeContextImpl
from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception, jlist_str_to_pylist
from javaimpl.compute.utils import jlist_obj_to_pylist, get_property, get_property_list
from javaimpl.compute.utils import jmap_to_pymap, jlist_map_to_pylist_map
from org.askalon.jlibcloud.compute.driverSpecific.nephoscale import NephoscaleNodeTemplateImpl
from javaimpl.compute.base.NodeImpl import NodeImpl
from org.askalon.jlibcloud.compute.driverSpecific.nephoscale import NephoscaleComputeContext

class NephoscaleComputeContextImpl(ComputeContextImpl, NephoscaleComputeContext):
	def __init__(self, builder):
		ComputeContext.__init__(self, builder)

	def createNode(self, node_temp):
		"""Creates the node, and sets the ssh key, console key
        NephoScale will respond with a 200-200 response after sending a valid
        request. If nowait=True is specified in the args, we then ask a few
        times until the server is created and assigned a public IP address,
        so that deploy_node can be run

        >>> from libcloud.compute.providers import get_driver
        >>> driver = get_driver('nephoscale')
        >>> conn = driver('nepho_user','nepho_password')
        >>> conn.list_nodes()
        >>> name = 'staging-server'
        >>> size = conn.list_sizes()[0]
        <NodeSize: id=27, ...name=CS025 - 0.25GB, 10GB, ...>
        >>> image = conn.list_images()[9]
        <NodeImage: id=49, name=Linux Ubuntu Server 10.04 LTS 64-bit, ...>
        >>> server_keys = conn.ex_list_keypairs(key_group=1)[0]
        <NodeKey: id=71211, name=markos>
        >>> server_key = conn.ex_list_keypairs(key_group=1)[0].id
        70867
        >>> console_keys = conn.ex_list_keypairs(key_group=4)[0]
        <NodeKey: id=71213, name=mistio28434>
        >>> console_key = conn.ex_list_keypairs(key_group=4)[0].id
        70907
        >>> node = conn.create_node(name=name, size=size, image=image, \
                console_key=console_key, server_key=server_key)

        We can also create an ssh key, plus a console key and
        deploy node with them
        >>> server_key = conn.ex_create_keypair(name, public_key='123')
        71211
        >>> console_key = conn.ex_create_keypair(name, key_group=4)
        71213

        We can increase the number of connect attempts to wait until
        the node is created, so that deploy_node has ip address to
        deploy the script
        We can also specify the location
        >>> location = conn.list_locations()[0]
        >>> node = conn.create_node(name=name,
            ...                     size=size,
            ...                     image=image,
            ...                     console_key=console_key,
            ...                     server_key=server_key,
            ...                     connect_attempts=10,
            ...                     nowait=True,
            ...                     zone=location.id)
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_nephoscale_template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_nephoscale_template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_nephoscale_template(self, node_temp, kwargs):
		name = node_temp.getName()
		kwargs = get_property(self, name, 'name',
					 kwargs,lambda x : x)
		size = node_temp.getSize()
		kwargs = get_property(self, size, 'size',
					 kwargs,lambda x : x.obj)
		image = node_temp.getImage()
		kwargs = get_property(self, image, 'image',
					 kwargs,lambda x : x.obj)
		server_key = node_temp.getServerKey()
		kwargs = get_property(self, server_key, 'server_key',
					 kwargs,lambda x : x)
		console_key = node_temp.getConsoleKey()
		kwargs = get_property(self, console_key, 'console_key',
					 kwargs,lambda x : x)
		zone = node_temp.getZone()
		kwargs = get_property(self, zone, 'zone',
					 kwargs,lambda x : x)
		return kwargs

	def getTemplateBuilder(self):
		return NephoscaleNodeTemplateImpl.newBuilder()

	def exStartNode(self, node):
		"""start a stopped node"""
		try:
			if node:
				node = node.obj
			return self.conn.ex_start_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exStopNode(self, node):
		"""stop a running node"""
		try:
			if node:
				node = node.obj
			return self.conn.ex_stop_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListKeypairs(self, ssh=False, password=False, key_group=None):
		"""
        List available console and server keys
        There are two types of keys for NephoScale, ssh and password keys.
        If run without arguments, lists all keys. Otherwise list only
        ssh keys, or only password keys.
        Password keys with key_group 4 are console keys. When a server
        is created, it has two keys, one password or ssh key, and
        one password console key.

        :keyword ssh: if specified, show ssh keys only (optional)
        :type    ssh: ``bool``

        :keyword password: if specified, show password keys only (optional)
        :type    password: ``bool``

        :keyword key_group: if specified, show keys with this key_group only
                            eg key_group=4 for console password keys (optional)
        :type    key_group: ``int``

        :rtype: ``list`` of :class:`NodeKey`
        """
		try:
			if not key_group:
				key_group = None
			if not password:
				password = False
			if not ssh:
				ssh = False
			return wrap_listing(self.conn.ex_list_keypairs(ssh, password, key_group), NodeKeyImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateKeypair(self, name, public_key=None, password=None, key_group=None):
		"""Creates a key, ssh or password, for server or console
           The group for the key (key_group) is 1 for Server and 4 for Console
           Returns the id of the created key
        """
		try:
			if not key_group:
				key_group = None
			if not password:
				password = None
			if not public_key:
				public_key = None
			return self.conn.ex_create_keypair(name, public_key, password, key_group)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeleteKeypair(self, key_id, ssh=False):
		"""Delete an ssh key or password given it's id
        """
		try:
			if not ssh:
				ssh = False
			return self.conn.ex_delete_keypair(key_id, ssh)
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.nephoscale import NodeKey

class NodeKeyImpl(NodeKey):

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
		if hasattr(obj, 'key_group'):
			self.key_groupp = none_check(obj.key_group, ' ')
		else:
			self.key_groupp = ' '
		if hasattr(obj, 'password'):
			self.passwordp = none_check(obj.password, ' ')
		else:
			self.passwordp = ' '
		if hasattr(obj, 'public_key'):
			self.public_keyp = none_check(obj.public_key, ' ')
		else:
			self.public_keyp = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getName(self):
		return self.namep

	def getKeyGroup(self):
		return self.key_groupp

	def getPassword(self):
		return self.passwordp

	def getPublicKey(self):
		return self.public_keyp

	def toString(self):
		return self.reprp

