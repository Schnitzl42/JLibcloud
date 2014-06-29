# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/ibm_sce.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/ibm_sce.py
#
# Apache Libcloud is licensed under the Apache 2.0 license.
# For more information, please see LICENSE and NOTICE file or:
# http://www.apache.org/licenses/LICENSE-2.0
from javaimpl.compute.ComputeContextImpl import ComputeContextImpl
from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception, jlist_str_to_pylist
from javaimpl.compute.utils import jlist_obj_to_pylist, get_property, get_property_list
from javaimpl.compute.utils import jmap_to_pymap, jlist_map_to_pylist_map
from org.askalon.jlibcloud.compute.driverSpecific.ibm_sce import IBMNodeTemplateImpl
from javaimpl.compute.base.NodeImageImpl import NodeImageImpl
from javaimpl.compute.base.NodeLocationImpl import NodeLocationImpl
from javaimpl.compute.base.NodeImpl import NodeImpl
from javaimpl.compute.base.StorageVolumeImpl import StorageVolumeImpl
from org.askalon.jlibcloud.compute.driverSpecific.ibm_sce import IBMComputeContext

class IBMComputeContextImpl(ComputeContextImpl, IBMComputeContext):
	def __init__(self, builder):
		ComputeContext.__init__(self, builder)

	def createNode(self, node_temp):
		"""
        Creates a node in the IBM SmartCloud Enterprise.

        See :class:`NodeDriver.create_node` for more keyword args.

        @inherits: :class:`NodeDriver.create_node`

        :keyword    auth: Name of the pubkey to use. When constructing
            :class:`NodeAuthSSHKey` instance, 'pubkey' argument must be the
            name of the public key to use. You chose this name when creating
            a new public key on the IBM server.
        :type       auth: :class:`NodeAuthSSHKey`

        :keyword    ex_configurationData: Image-specific configuration
            parameters. Configuration parameters are defined in the parameters
            .xml file.  The URL to this file is defined in the NodeImage at
            extra[parametersURL].
            Note: This argument must be specified when launching a Windows
            instance. It must contain 'UserName' and 'Password' keys.
        :type       ex_configurationData: ``dict``
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_ibm_template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_ibm_template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_ibm_template(self, node_temp, kwargs):
		ex_configurationData = node_temp.getExConfigurationData()
		if ex_configurationData:
			kwargs['ex_configurationData'] = jmap_to_pymap(ex_configurationData)
		return kwargs
	
	def listLocations(self):
		try:
			return wrap_listing(self.conn.list_locations(), IBMNodeLocationImpl)
		except Exception, ex:
			wrap_exception(ex)
			
	def getTemplateBuilder(self):
		return IBMNodeTemplateImpl.newBuilder()

	def exDestroyImage(self, image):
		"""
        Destroys an image.

        :param      image: Image to be destroyed
        :type       image: :class:`NodeImage`

        :return: ``bool``
        """
		try:
			if image:
				image = image.obj
			return self.conn.ex_destroy_image(image)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListStorageOfferings(self):
		"""
        List the storage center offerings

        :rtype: ``list`` of :class:`VolumeOffering`
        """
		try:
			return wrap_listing(self.conn.ex_list_storage_offerings(), VolumeOfferingImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exAllocateAddress(self, location_id, offering_id, vlan_id=None):
		"""
        Allocate a new reserved IP address

        :param      location_id: Target data center
        :type       location_id: ``str``

        :param      offering_id: Offering ID for address to create
        :type       offering_id: ``str``

        :param      vlan_id: ID of target VLAN
        :type       vlan_id: ``str``

        :return: :class:`Address` object
        :rtype: :class:`Address`
        """
		try:
			if not vlan_id:
				vlan_id = None
			return self.conn.ex_allocate_address(location_id, offering_id, vlan_id)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListAddresses(self, resource_id=None):
		"""
        List the reserved IP addresses

        :param      resource_id: If this is supplied only a single address will
         be returned (optional)
        :type       resource_id: ``str``

        :rtype: ``list`` of :class:`Address`
        """
		try:
			if not resource_id:
				resource_id = None
			return wrap_listing(self.conn.ex_list_addresses(resource_id), AddressImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exCopyTo(self, image, volume):
		"""
        Copies a node image to a storage volume

        :param      image: source image to copy
        :type       image: :class:`NodeImage`

        :param      volume: Target storage volume to copy to
        :type       volume: :class:`StorageVolume`

        :return: ``bool`` The success of the operation
        :rtype: ``bool``
        """
		try:
			if volume:
				volume = volume.obj
			if image:
				image = image.obj
			return self.conn.ex_copy_to(image, volume)
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeleteAddress(self, resource_id):
		"""
        Delete a reserved IP address

        :param      resource_id: The address to delete (required)
        :type       resource_id: ``str``

        :rtype: ``bool``
        """
		try:
			return self.conn.ex_delete_address(resource_id)
		except Exception, ex:
			raise wrap_exception(ex)

	def exWaitStorageState(self, volume, state=VolumeState.DETACHED, wait_period=60, timeout=1200):
		"""
        Block until storage volume state changes to the given value

        :param      volume: Storage volume.
        :type       volume: :class:`StorageVolume`

        :param      state: The target state to wait for
        :type       state: ``int``

        :param      wait_period: How many seconds to between each loop
                                 iteration (default is 3)
        :type       wait_period: ``int``

        :param      timeout: How many seconds to wait before timing out
                             (default is 1200)
        :type       timeout: ``int``

        :rtype: :class:`StorageVolume`
        """
		try:
			if not timeout:
				timeout = 1200
			if not wait_period:
				wait_period = 60
			if not state:
				state = VolumeState.DETACHED
			if volume:
				volume = volume.obj
			return StorageVolumeImpl(self.conn.ex_wait_storage_state(volume, state, wait_period, timeout))
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.ibm_sce import IBMNodeLocation

class IBMNodeLocationImpl(NodeLocationImpl, IBMNodeLocation):
	'''
    Extends the base LibCloud NodeLocation to contain additional attributes
	'''

	def __init__(self, obj):
		NodeLocationImpl.__init__(self, obj)
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
		if hasattr(obj, 'country'):
			self.countryp = none_check(obj.country, ' ')
		else:
			self.countryp = ' '
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

	def getCountry(self):
		return self.countryp

	def getExtra(self):
		return self.extrap

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.ibm_sce import VolumeOffering

class VolumeOfferingImpl(VolumeOffering):
	'''
    An SCE specific storage volume offering class.
    The volume offering ID is needed to create a volume.
    Volume offering IDs are different for each data center.
	'''

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'location'):
			self.locationp = NodeLocationImpl(obj.location)
		else:
			self.locationp = NodeLocationImpl(None)
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
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

	def getLocation(self):
		return self.locationp

	def getName(self):
		return self.namep

	def getExtra(self):
		return self.extrap

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.ibm_sce import Address

class AddressImpl(Address):
	'''
    A reserved IP address that can be attached to an instance.
    Properties: id, ip, state, options(location, type, created_time, state,
     hostname, instance_ids, vlan, owner,
    mode, offering_id)
	'''

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, 'ip'):
			self.ipp = none_check(obj.ip, ' ')
		else:
			self.ipp = ' '
		if hasattr(obj, 'state'):
			self.statep = none_check(obj.state, ' ')
		else:
			self.statep = ' '
		if hasattr(obj, 'options'):
			self.optionsp = none_check(obj.options, ' ')
		else:
			self.optionsp = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getIp(self):
		return self.ipp

	def getState(self):
		return self.statep

	def getOptions(self):
		return self.optionsp

	def toString(self):
		return self.reprp

