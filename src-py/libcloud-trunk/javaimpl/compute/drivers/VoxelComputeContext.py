# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/voxel.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/voxel.py
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
from org.askalon.jlibcloud.compute.driverSpecific.voxel import VoxelNodeTemplateImpl
from javaimpl.compute.base.NodeImpl import NodeImpl
from javaimpl.compute.base.NodeSizeImpl import NodeSizeImpl
from org.askalon.jlibcloud.compute.driverSpecific.voxel import VoxelComputeContext

class VoxelComputeContextImpl(ComputeContextImpl, VoxelComputeContext):
	def __init__(self, builder):
		ComputeContext.__init__(self, builder)

	def createNode(self, node_temp):
		"""Create Voxel Node

        :keyword name: the name to assign the node (mandatory)
        :type    name: ``str``

        :keyword image: distribution to deploy
        :type    image: :class:`NodeImage`

        :keyword size: the plan size to create (mandatory)
                       Requires size.disk (GB) to be set manually
        :type    size: :class:`NodeSize`

        :keyword location: which datacenter to create the node in
        :type    location: :class:`NodeLocation`

        :keyword ex_privateip: Backend IP address to assign to node;
                               must be chosen from the customer's
                               private VLAN assignment.
        :type    ex_privateip: ``str``

        :keyword ex_publicip: Public-facing IP address to assign to node;
                              must be chosen from the customer's
                              public VLAN assignment.
        :type    ex_publicip: ``str``

        :keyword ex_rootpass: Password for root access; generated if unset.
        :type    ex_rootpass: ``str``

        :keyword ex_consolepass: Password for remote console;
                                 generated if unset.
        :type    ex_consolepass: ``str``

        :keyword ex_sshuser: Username for SSH access
        :type    ex_sshuser: ``str``

        :keyword ex_sshpass: Password for SSH access; generated if unset.
        :type    ex_sshpass: ``str``

        :keyword ex_voxel_access: Allow access Voxel administrative access.
                                  Defaults to False.
        :type    ex_voxel_access: ``bool``

        :rtype: :class:`Node` or ``None``
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_voxel_template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_voxel_template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_voxel_template(self, node_temp, kwargs):
		name = node_temp.getName()
		kwargs = get_property(self, name, 'name',
					 kwargs,lambda x : x)
		image = node_temp.getImage()
		kwargs = get_property(self, image, 'image',
					 kwargs,lambda x : x.obj)
		size = node_temp.getSize()
		kwargs = get_property(self, size, 'size',
					 kwargs,lambda x : x.obj)
		location = node_temp.getLocation()
		kwargs = get_property(self, location, 'location',
					 kwargs,lambda x : x.obj)
		ex_privateip = node_temp.getExPrivateip()
		kwargs = get_property(self, ex_privateip, 'ex_privateip',
					 kwargs,lambda x : x)
		ex_publicip = node_temp.getExPublicip()
		kwargs = get_property(self, ex_publicip, 'ex_publicip',
					 kwargs,lambda x : x)
		ex_rootpass = node_temp.getExRootpass()
		kwargs = get_property(self, ex_rootpass, 'ex_rootpass',
					 kwargs,lambda x : x)
		ex_consolepass = node_temp.getExConsolepass()
		kwargs = get_property(self, ex_consolepass, 'ex_consolepass',
					 kwargs,lambda x : x)
		ex_sshuser = node_temp.getExSshuser()
		kwargs = get_property(self, ex_sshuser, 'ex_sshuser',
					 kwargs,lambda x : x)
		ex_sshpass = node_temp.getExSshpass()
		kwargs = get_property(self, ex_sshpass, 'ex_sshpass',
					 kwargs,lambda x : x)
		ex_voxel_access = node_temp.getExVoxelAccess()
		l = lambda x : True if x == 'true' or x == True else False
		kwargs = get_property(self, ex_voxel_access, 'ex_voxel_access',
					 kwargs,l)
		return kwargs

	def getTemplateBuilder(self):
		return VoxelNodeTemplateImpl.newBuilder()

