# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/vcloud.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/vcloud.py
#
# Apache Libcloud is licensed under the Apache 2.0 license.
# For more information, please see LICENSE and NOTICE file or:
# http://www.apache.org/licenses/LICENSE-2.0
from javaimpl.compute.ComputeContextImpl import ComputeContextImpl
from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception, jlist_str_to_pylist
from javaimpl.compute.utils import jlist_obj_to_pylist, get_property, get_property_list
from javaimpl.compute.utils import jmap_to_pymap, jlist_map_to_pylist_map
from javaimpl.compute.base.NodeImageImpl import NodeImageImpl
from org.askalon.jlibcloud.compute.driverSpecific.vcloud import VCloud_1_5_NodeTemplateImpl
from javaimpl.compute.base.NodeImpl import NodeImpl
from org.askalon.jlibcloud.compute.driverSpecific.vcloud import VCloudNodeTemplateImpl
from org.askalon.jlibcloud.compute.driverSpecific.vcloud import VCloudComputeContext

class VCloudComputeContextImpl(ComputeContextImpl, VCloudComputeContext):
	def __init__(self, builder):
		ComputeContextImpl.__init__(self, builder)

	def createNode(self, node_temp):
		"""
        Creates and returns node.

        :keyword    ex_network: link to a "Network" e.g.,
                    ``https://services.vcloudexpress...``
        :type       ex_network: ``str``

        :keyword    ex_vdc: Name of organisation's virtual data
                            center where vApp VMs will be deployed.
        :type       ex_vdc: ``str``

        :keyword    ex_cpus: number of virtual cpus (limit depends on provider)
        :type       ex_cpus: ``int``

        :type       ex_row: ``str``

        :type       ex_group: ``str``
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_vcloud_template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_vcloud_template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_vcloud_template(self, node_temp, kwargs):
		ex_network = node_temp.getExNetwork()
		kwargs = get_property(self, ex_network, 'ex_network',
					 kwargs,lambda x : x)
		ex_vdc = node_temp.getExVdc()
		kwargs = get_property(self, ex_vdc, 'ex_vdc',
					 kwargs,lambda x : x)
		ex_cpus = node_temp.getExCpus()
		kwargs = get_property(self, ex_cpus, 'ex_cpus',
					 kwargs,lambda x : int(x))
		ex_group = node_temp.getExGroup()
		kwargs = get_property(self, ex_group, 'ex_group',
					 kwargs,lambda x : x)
		return kwargs

	def getTemplateBuilder(self):
		return VCloudNodeTemplateImpl.newBuilder()

	def exListNodes(self, vdcs=None):
		"""
        List all nodes across all vDCs. Using 'vdcs' you can specify which vDCs
        should be queried.

        :param vdcs: None, vDC or a list of vDCs to query. If None all vDCs
                     will be queried.
        :type vdcs: :class:`Vdc`

        :rtype: ``list`` of :class:`Node`
        """
		try:
			if vdcs:
				vdcs = vdcs.obj
			if not vdcs:
				vdcs = None
			return wrap_listing(self.conn.ex_list_nodes(vdcs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)

from org.askalon.jlibcloud.compute.driverSpecific.vcloud import VCloud_1_5_ComputeContext

class VCloud_1_5_ComputeContextImpl(VCloudComputeContextImpl, VCloud_1_5_ComputeContext):
	def __init__(self, builder):
		VCloudComputeContextImpl.__init__(self, builder)

	def createNode(self, node_temp):
		"""
        Creates and returns node. If the source image is:
          - vApp template - a new vApp is instantiated from template
          - existing vApp - a new vApp is cloned from the source vApp. Can
                            not clone more vApps is parallel otherwise
                            resource busy error is raised.


        @inherits: :class:`NodeDriver.create_node`

        :keyword    image:  OS Image to boot on node. (required). Can be a
                            NodeImage or existing Node that will be cloned.
        :type       image:  :class:`NodeImage` or :class:`Node`

        :keyword    ex_network: Organisation's network name for attaching vApp
                                VMs to.
        :type       ex_network: ``str``

        :keyword    ex_vdc: Name of organisation's virtual data center where
                            vApp VMs will be deployed.
        :type       ex_vdc: ``str``

        :keyword    ex_vm_names: list of names to be used as a VM and computer
                                 name. The name must be max. 15 characters
                                 long and follow the host name requirements.
        :type       ex_vm_names: ``list`` of ``str``

        :keyword    ex_vm_cpu: number of virtual CPUs/cores to allocate for
                               each vApp VM.
        :type       ex_vm_cpu: ``int``

        :keyword    ex_vm_memory: amount of memory in MB to allocate for each
                                  vApp VM.
        :type       ex_vm_memory: ``int``

        :keyword    ex_vm_script: full path to file containing guest
                                  customisation script for each vApp VM.
                                  Useful for creating users & pushing out
                                  public SSH keys etc.
        :type       ex_vm_script: ``str``

        :keyword    ex_vm_network: Override default vApp VM network name.
                                   Useful for when you've imported an OVF
                                   originating from outside of the vCloud.
        :type       ex_vm_network: ``str``

        :keyword    ex_vm_fence: Fence mode for connecting the vApp VM network
                                 (ex_vm_network) to the parent
                                 organisation network (ex_network).
        :type       ex_vm_fence: ``str``

        :keyword    ex_vm_ipmode: IP address allocation mode for all vApp VM
                                  network connections.
        :type       ex_vm_ipmode: ``str``

        :keyword    ex_deploy: set to False if the node shouldn't be deployed
                               (started) after creation
        :type       ex_deploy: ``bool``

        :keyword    ex_clone_timeout: timeout in seconds for clone/instantiate
                                      VM operation.
                                      Cloning might be a time consuming
                                      operation especially when linked clones
                                      are disabled or VMs are created on
                                      different datastores.
                                      Overrides the default task completion
                                      value.
        :type       ex_clone_timeout: ``int``
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_vcloud_1_5__template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_vcloud_1_5__template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_vcloud_1_5__template(self, node_temp, kwargs):
		image = node_temp.getImage()
		kwargs = get_property(self, image, 'image',
					 kwargs,lambda x : x.obj)
		ex_network = node_temp.getExNetwork()
		kwargs = get_property(self, ex_network, 'ex_network',
					 kwargs,lambda x : x)
		ex_vdc = node_temp.getExVdc()
		kwargs = get_property(self, ex_vdc, 'ex_vdc',
					 kwargs,lambda x : x)
		ex_vm_names = node_temp.getExVmNames()
		kwargs = get_property_list(self, ex_vm_names, 'ex_vm_names',
					 kwargs,lambda x : jlist_str_to_pylist(x))
		ex_vm_cpu = node_temp.getExVmCpu()
		kwargs = get_property(self, ex_vm_cpu, 'ex_vm_cpu',
					 kwargs,lambda x : int(x))
		ex_vm_memory = node_temp.getExVmMemory()
		kwargs = get_property(self, ex_vm_memory, 'ex_vm_memory',
					 kwargs,lambda x : int(x))
		ex_vm_script = node_temp.getExVmScript()
		kwargs = get_property(self, ex_vm_script, 'ex_vm_script',
					 kwargs,lambda x : x)
		ex_vm_network = node_temp.getExVmNetwork()
		kwargs = get_property(self, ex_vm_network, 'ex_vm_network',
					 kwargs,lambda x : x)
		ex_vm_fence = node_temp.getExVmFence()
		kwargs = get_property(self, ex_vm_fence, 'ex_vm_fence',
					 kwargs,lambda x : x)
		ex_vm_ipmode = node_temp.getExVmIpmode()
		kwargs = get_property(self, ex_vm_ipmode, 'ex_vm_ipmode',
					 kwargs,lambda x : x)
		ex_deploy = node_temp.getExDeploy()
		l = lambda x : True if x == 'true' or x == True else False
		kwargs = get_property(self, ex_deploy, 'ex_deploy',
					 kwargs,l)
		ex_clone_timeout = node_temp.getExCloneTimeout()
		kwargs = get_property(self, ex_clone_timeout, 'ex_clone_timeout',
					 kwargs,lambda x : int(x))
		return kwargs

	def getTemplateBuilder(self):
		return VCloud_1_5_NodeTemplateImpl.newBuilder()

	def exFindNode(self, node_name, vdcs=None):
		"""
        Searches for node across specified vDCs. This is more effective than
        querying all nodes to get a single instance.

        :param node_name: The name of the node to search for
        :type node_name: ``str``

        :param vdcs: None, vDC or a list of vDCs to search in. If None all vDCs
                     will be searched.
        :type vdcs: :class:`Vdc`

        :return: node instance or None if not found
        :rtype: :class:`Node` or ``None``
        """
		try:
			if vdcs:
				vdcs = vdcs.obj
			if not vdcs:
				vdcs = None
			return NodeImpl(self.conn.ex_find_node(node_name, vdcs))
		except Exception, ex:
			raise wrap_exception(ex)

	def exDeployNode(self, node):
		"""
        Deploys existing node. Equal to vApp "start" operation.

        :param  node: The node to be deployed
        :type   node: :class:`Node`

        :rtype: :class:`Node`
        """
		try:
			if node:
				node = node.obj
			return NodeImpl(self.conn.ex_deploy_node(node))
		except Exception, ex:
			raise wrap_exception(ex)

	def exUndeployNode(self, node):
		"""
        Undeploys existing node. Equal to vApp "stop" operation.

        :param  node: The node to be deployed
        :type   node: :class:`Node`

        :rtype: :class:`Node`
        """
		try:
			if node:
				node = node.obj
			return NodeImpl(self.conn.ex_undeploy_node(node))
		except Exception, ex:
			raise wrap_exception(ex)

	def exPowerOffNode(self, node):
		"""
        Powers on all VMs under specified node. VMs need to be This operation
        is allowed only when the vApp/VM is powered on.

        :param  node: The node to be powered off
        :type   node: :class:`Node`

        :rtype: :class:`Node`
        """
		try:
			if node:
				node = node.obj
			return NodeImpl(self.conn.ex_power_off_node(node))
		except Exception, ex:
			raise wrap_exception(ex)

	def exPowerOnNode(self, node):
		"""
        Powers on all VMs under specified node. This operation is allowed
        only when the vApp/VM is powered off or suspended.

        :param  node: The node to be powered on
        :type   node: :class:`Node`

        :rtype: :class:`Node`
        """
		try:
			if node:
				node = node.obj
			return NodeImpl(self.conn.ex_power_on_node(node))
		except Exception, ex:
			raise wrap_exception(ex)

	def exShutdownNode(self, node):
		"""
        Shutdowns all VMs under specified node. This operation is allowed only
        when the vApp/VM is powered on.

        :param  node: The node to be shut down
        :type   node: :class:`Node`

        :rtype: :class:`Node`
        """
		try:
			if node:
				node = node.obj
			return NodeImpl(self.conn.ex_shutdown_node(node))
		except Exception, ex:
			raise wrap_exception(ex)

	def exSuspendNode(self, node):
		"""
        Suspends all VMs under specified node. This operation is allowed only
        when the vApp/VM is powered on.

        :param  node: The node to be suspended
        :type   node: :class:`Node`

        :rtype: :class:`Node`
        """
		try:
			if node:
				node = node.obj
			return NodeImpl(self.conn.ex_suspend_node(node))
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetControlAccess(self, node):
		"""
        Returns the control access settings for specified node.

        :param  node: node to get the control access for
        :type   node: :class:`Node`

        :rtype: :class:`ControlAccess`
        """
		try:
			if node:
				node = node.obj
			return ControlAccessImpl(self.conn.ex_get_control_access(node))
		except Exception, ex:
			raise wrap_exception(ex)

	def exSetControlAccess(self, node, control_access):
		"""
        Sets control access for the specified node.

        :param  node: node
        :type   node: :class:`Node`

        :param  control_access: control access settings
        :type   control_access: :class:`ControlAccess`

        :rtype: ``None``
        """
		try:
			if control_access:
				control_access = control_access.obj
			if node:
				node = node.obj
			self.conn.ex_set_control_access(node, control_access)
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetMetadata(self, node):
		"""
        :param  node: node
        :type   node: :class:`Node`

        :return: dictionary mapping metadata keys to metadata values
        :rtype: dictionary mapping ``str`` to ``str``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_get_metadata(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exSetMetadataEntry(self, node, key, value):
		"""
        :param  node: node
        :type   node: :class:`Node`

        :param key: metadata key to be set
        :type key: ``str``

        :param value: metadata value to be set
        :type value: ``str``

        :rtype: ``None``
        """
		try:
			if node:
				node = node.obj
			self.conn.ex_set_metadata_entry(node, key, value)
		except Exception, ex:
			raise wrap_exception(ex)

	def exQuery(self, type, filter=None, page=1, page_size=100, sort_asc=None, 
					sort_desc=None):
		"""
        Queries vCloud for specified type. See
        http://www.vmware.com/pdf/vcd_15_api_guide.pdf for details. Each
        element of the returned list is a dictionary with all attributes from
        the record.

        :param type: type to query (r.g. user, group, vApp etc.)
        :type  type: ``str``

        :param filter: filter expression (see documentation for syntax)
        :type  filter: ``str``

        :param page: page number
        :type  page: ``int``

        :param page_size: page size
        :type  page_size: ``int``

        :param sort_asc: sort in ascending order by specified field
        :type  sort_asc: ``str``

        :param sort_desc: sort in descending order by specified field
        :type  sort_desc: ``str``

        :rtype: ``list`` of dict
        """
		try:
			if not sort_desc:
				sort_desc = None
			if not sort_asc:
				sort_asc = None
			if not page_size:
				page_size = 100
			if not page:
				page = 1
			if not filter:
				filter = None
			return self.conn.ex_query(type, filter, page, page_size, sort_asc, sort_desc)
		except Exception, ex:
			raise wrap_exception(ex)

	def exSetVmCpu(self, vapp_or_vm_id, vm_cpu):
		"""
        Sets the number of virtual CPUs for the specified VM or VMs under
        the vApp. If the vapp_or_vm_id param represents a link to an vApp
        all VMs that are attached to this vApp will be modified.

        Please ensure that hot-adding a virtual CPU is enabled for the
        powered on virtual machines. Otherwise use this method on undeployed
        vApp.

        :keyword    vapp_or_vm_id: vApp or VM ID that will be modified. If
                                   a vApp ID is used here all attached VMs
                                   will be modified
        :type       vapp_or_vm_id: ``str``

        :keyword    vm_cpu: number of virtual CPUs/cores to allocate for
                            specified VMs
        :type       vm_cpu: ``int``

        :rtype: ``None``
        """
		try:
			self.conn.ex_set_vm_cpu(vapp_or_vm_id, vm_cpu)
		except Exception, ex:
			raise wrap_exception(ex)

	def exSetVmMemory(self, vapp_or_vm_id, vm_memory):
		"""
        Sets the virtual memory in MB to allocate for the specified VM or
        VMs under the vApp. If the vapp_or_vm_id param represents a link
        to an vApp all VMs that are attached to this vApp will be modified.

        Please ensure that hot-change of virtual memory is enabled for the
        powered on virtual machines. Otherwise use this method on undeployed
        vApp.

        :keyword    vapp_or_vm_id: vApp or VM ID that will be modified. If
                                   a vApp ID is used here all attached VMs
                                   will be modified
        :type       vapp_or_vm_id: ``str``

        :keyword    vm_memory: virtual memory in MB to allocate for the
                               specified VM or VMs
        :type       vm_memory: ``int``

        :rtype: ``None``
        """
		try:
			self.conn.ex_set_vm_memory(vapp_or_vm_id, vm_memory)
		except Exception, ex:
			raise wrap_exception(ex)

	def exAddVmDisk(self, vapp_or_vm_id, vm_disk_size):
		"""
        Adds a virtual disk to the specified VM or VMs under the vApp. If the
        vapp_or_vm_id param represents a link to an vApp all VMs that are
        attached to this vApp will be modified.

        :keyword    vapp_or_vm_id: vApp or VM ID that will be modified. If a
                                   vApp ID is used here all attached VMs
                                   will be modified
        :type       vapp_or_vm_id: ``str``

        :keyword    vm_disk_size: the disk capacity in GB that will be added
                                  to the specified VM or VMs
        :type       vm_disk_size: ``int``

        :rtype: ``None``
        """
		try:
			self.conn.ex_add_vm_disk(vapp_or_vm_id, vm_disk_size)
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.vcloud import Vdc

class VdcImpl(Vdc):
	'''
    Virtual datacenter (vDC) representation
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
		if hasattr(obj, 'allocation_model'):
			self.allocation_modelp = none_check(obj.allocation_model, ' ')
		else:
			self.allocation_modelp = ' '
		if hasattr(obj, 'cpu'):
			self.cpup = none_check(obj.cpu, ' ')
		else:
			self.cpup = ' '
		if hasattr(obj, 'memory'):
			self.memoryp = none_check(obj.memory, -1)
		else:
			self.memoryp = -1
		if hasattr(obj, 'storage'):
			self.storagep = none_check(obj.storage, ' ')
		else:
			self.storagep = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getName(self):
		return self.namep

	def getAllocationModel(self):
		return self.allocation_modelp

	def getCpu(self):
		return self.cpup

	def getMemory(self):
		return self.memoryp

	def getStorage(self):
		return self.storagep

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.vcloud import Capacity

class CapacityImpl(Capacity):
	'''
    Represents CPU, Memory or Storage capacity of vDC.
	'''

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'limit'):
			self.limitp = none_check(obj.limit, ' ')
		else:
			self.limitp = ' '
		if hasattr(obj, 'used'):
			self.usedp = none_check(obj.used, ' ')
		else:
			self.usedp = ' '
		if hasattr(obj, 'units'):
			self.unitsp = none_check(obj.units, ' ')
		else:
			self.unitsp = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getLimit(self):
		return self.limitp

	def getUsed(self):
		return self.usedp

	def getUnits(self):
		return self.unitsp

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.vcloud import ControlAccess

class ControlAccessImpl(ControlAccess):
	'''
    Represents control access settings of a node
	'''

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'node'):
			self.nodep = NodeImpl(obj.node)
		else:
			self.nodep = NodeImpl(None)
		if hasattr(obj, 'everyone_access_level'):
			self.everyone_access_levelp = none_check(obj.everyone_access_level, ' ')
		else:
			self.everyone_access_levelp = ' '
		if hasattr(obj, 'subjects'):
			self.subjectsp = wrap_listing(obj.subjects, SubjectImpl)
		else:
			self.subjectsp = wrap_listing(None, SubjectImpl)
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getNode(self):
		return self.nodep

	def getEveryoneAccessLevel(self):
		return self.everyone_access_levelp

	def getSubjects(self):
		return self.subjectsp

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.vcloud import Subject

class SubjectImpl(Subject):
	'''
    User or group subject
	'''

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'type'):
			self.typep = none_check(obj.type, ' ')
		else:
			self.typep = ' '
		if hasattr(obj, 'name'):
			self.namep = none_check(obj.name, ' ')
		else:
			self.namep = ' '
		if hasattr(obj, 'access_level'):
			self.access_levelp = none_check(obj.access_level, ' ')
		else:
			self.access_levelp = ' '
		if hasattr(obj, 'id'):
			self.idp = none_check(obj.id, ' ')
		else:
			self.idp = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getType(self):
		return self.typep

	def getName(self):
		return self.namep

	def getAccessLevel(self):
		return self.access_levelp

	def getId(self):
		return self.idp

	def toString(self):
		return self.reprp
