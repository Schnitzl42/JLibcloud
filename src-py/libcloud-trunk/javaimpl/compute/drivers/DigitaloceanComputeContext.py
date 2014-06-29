# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/digitalocean.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/digitalocean.py
#
# Apache Libcloud is licensed under the Apache 2.0 license.
# For more information, please see LICENSE and NOTICE file or:
# http://www.apache.org/licenses/LICENSE-2.0
from javaimpl.compute.ComputeContextImpl import ComputeContextImpl
from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception, jlist_str_to_pylist
from javaimpl.compute.utils import jlist_obj_to_pylist, get_property, get_property_list
from javaimpl.compute.utils import jmap_to_pymap, jlist_map_to_pylist_map
from org.askalon.jlibcloud.compute.driverSpecific.digitalocean import DigitalOceanNodeTemplateImpl
from javaimpl.compute.base.NodeImpl import NodeImpl
from org.askalon.jlibcloud.compute.driverSpecific.digitalocean import DigitalOceanComputeContext

class DigitalOceanComputeContextImpl(ComputeContextImpl, DigitalOceanComputeContext):
	def __init__(self, builder):
		ComputeContextImpl.__init__(self, builder)

	def createNode(self, node_temp):
		"""
        Create a node.

        :keyword    ex_ssh_key_ids: A list of ssh key ids which will be added
                                   to the server. (optional)
        :type       ex_ssh_key_ids: ``list`` of ``str``

        :return: The newly created node.
        :rtype: :class:`Node`
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_digitalocean_template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_digitalocean_template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_digitalocean_template(self, node_temp, kwargs):
		ex_ssh_key_ids = node_temp.getExSshKeyIds()
		kwargs = get_property_list(self, ex_ssh_key_ids, 'ex_ssh_key_ids',
					 kwargs,lambda x : jlist_str_to_pylist(x))
		return kwargs

	def getTemplateBuilder(self):
		return DigitalOceanNodeTemplateImpl.newBuilder()

	def exListSshKeys(self):
		"""
        List all the available SSH keys.

        :return: Available SSH keys.
        :rtype: ``list`` of :class:`SSHKey`
        """
		try:
			return wrap_listing(self.conn.ex_list_ssh_keys(), SSHKeyImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCreateSshKey(self, name, ssh_key_pub):
		"""
        Create a new SSH key.

        :param      name: Key name (required)
        :type       name: ``str``

        :param      name: Valid public key string (required)
        :type       name: ``str``
        """
		try:
			return SSHKeyImpl(self.conn.ex_create_ssh_key(name, ssh_key_pub))
		except Exception, ex:
			raise wrap_exception(ex)

	def exDestroySshKey(self, key_id):
		"""
        Delete an existing SSH key.

        :param      key_id: SSH key id (required)
        :type       key_id: ``str``
        """
		try:
			return self.conn.ex_destroy_ssh_key(key_id)
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.digitalocean import SSHKey

class SSHKeyImpl(SSHKey):

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
		if hasattr(obj, 'pub_key'):
			self.pub_keyp = none_check(obj.pub_key, ' ')
		else:
			self.pub_keyp = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getName(self):
		return self.namep

	def getPubKey(self):
		return self.pub_keyp

	def toString(self):
		return self.reprp

