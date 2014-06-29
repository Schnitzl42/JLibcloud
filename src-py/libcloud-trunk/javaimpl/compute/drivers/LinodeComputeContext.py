# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/linode.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/linode.py
#
# Apache Libcloud is licensed under the Apache 2.0 license.
# For more information, please see LICENSE and NOTICE file or:
# http://www.apache.org/licenses/LICENSE-2.0
from javaimpl.compute.ComputeContextImpl import ComputeContextImpl
from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception, jlist_str_to_pylist
from javaimpl.compute.utils import jlist_obj_to_pylist, get_property, get_property_list
from javaimpl.compute.utils import jmap_to_pymap, jlist_map_to_pylist_map
from javaimpl.compute.base.NodeImageImpl import NodeImageImpl
from org.askalon.jlibcloud.compute.driverSpecific.linode import LinodeNodeTemplateImpl
from javaimpl.compute.base.NodeLocationImpl import NodeLocationImpl
from javaimpl.compute.base.NodeImpl import NodeImpl
from javaimpl.compute.base.NodeSizeImpl import NodeSizeImpl
from org.askalon.jlibcloud.compute.driverSpecific.linode import LinodeComputeContext

class LinodeComputeContextImpl(ComputeContextImpl, LinodeComputeContext):
	def __init__(self, builder):
		ComputeContext.__init__(self, builder)

	def createNode(self, node_temp):
		"""Create a new Linode, deploy a Linux distribution, and boot

        This call abstracts much of the functionality of provisioning a Linode
        and getting it booted.  A global grant to add Linodes to the account is
        required, as this call will result in a billing charge.

        Note that there is a safety valve of 5 Linodes per hour, in order to
        prevent a runaway script from ruining your day.

        :keyword name: the name to assign the Linode (mandatory)
        :type    name: ``str``

        :keyword image: which distribution to deploy on the Linode (mandatory)
        :type    image: :class:`NodeImage`

        :keyword size: the plan size to create (mandatory)
        :type    size: :class:`NodeSize`

        :keyword auth: an SSH key or root password (mandatory)
        :type    auth: :class:`NodeAuthSSHKey` or :class:`NodeAuthPassword`

        :keyword location: which datacenter to create the Linode in
        :type    location: :class:`NodeLocation`

        :keyword ex_swap: size of the swap partition in MB (128)
        :type    ex_swap: ``int``

        :keyword ex_rsize: size of the root partition in MB (plan size - swap).
        :type    ex_rsize: ``int``

        :keyword ex_kernel: a kernel ID from avail.kernels (Latest 2.6 Stable).
        :type    ex_kernel: ``str``

        :keyword ex_payment: one of 1, 12, or 24; subscription length (1)
        :type    ex_payment: ``int``

        :keyword ex_comment: a small comment for the configuration (libcloud)
        :type    ex_comment: ``str``

        :keyword ex_private: whether or not to request a private IP (False)
        :type    ex_private: ``bool``

        :keyword lconfig: what to call the configuration (generated)
        :type    lconfig: ``str``

        :keyword lroot: what to call the root image (generated)
        :type    lroot: ``str``

        :keyword lswap: what to call the swap space (generated)
        :type    lswap: ``str``

        :return: Node representing the newly-created Linode
        :rtype: :class:`Node`
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_linode_template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_linode_template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_linode_template(self, node_temp, kwargs):
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
		ex_swap = node_temp.getExSwap()
		kwargs = get_property(self, ex_swap, 'ex_swap',
					 kwargs,lambda x : int(x))
		ex_rsize = node_temp.getExRsize()
		kwargs = get_property(self, ex_rsize, 'ex_rsize',
					 kwargs,lambda x : int(x))
		ex_kernel = node_temp.getExKernel()
		kwargs = get_property(self, ex_kernel, 'ex_kernel',
					 kwargs,lambda x : x)
		ex_payment = node_temp.getExPayment()
		kwargs = get_property(self, ex_payment, 'ex_payment',
					 kwargs,lambda x : int(x))
		ex_comment = node_temp.getExComment()
		kwargs = get_property(self, ex_comment, 'ex_comment',
					 kwargs,lambda x : x)
		ex_private = node_temp.getExPrivate()
		l = lambda x : True if x == 'true' or x == True else False
		kwargs = get_property(self, ex_private, 'ex_private',
					 kwargs,l)
		lconfig = node_temp.getLconfig()
		kwargs = get_property(self, lconfig, 'lconfig',
					 kwargs,lambda x : x)
		lroot = node_temp.getLroot()
		kwargs = get_property(self, lroot, 'lroot',
					 kwargs,lambda x : x)
		lswap = node_temp.getLswap()
		kwargs = get_property(self, lswap, 'lswap',
					 kwargs,lambda x : x)
		return kwargs

	def getTemplateBuilder(self):
		return LinodeNodeTemplateImpl.newBuilder()

