# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/libvirt_driver.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/libvirt_driver.py
#
# Apache Libcloud is licensed under the Apache 2.0 license.
# For more information, please see LICENSE and NOTICE file or:
# http://www.apache.org/licenses/LICENSE-2.0
from javaimpl.compute.ComputeContextImpl import ComputeContextImpl
from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception, jlist_str_to_pylist
from javaimpl.compute.utils import jlist_obj_to_pylist, get_property, get_property_list
from javaimpl.compute.utils import jmap_to_pymap, jlist_map_to_pylist_map
from javaimpl.compute.base.libcloudcomputebaseNodeImpl import NodeImpl
from javaimpl.compute.base.NodeImpl import NodeImpl
from org.askalon.jlibcloud.compute.driverSpecific.libvirt_driver import LibvirtComputeContext

class LibvirtComputeContextImpl(ComputeContextImpl, LibvirtComputeContext):
	def __init__(self, builder):
		ComputeContext.__init__(self, builder)

	def createNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def exStartNode(self, node):
		"""
        Start a stopped node.

        :param  node: Node which should be used
        :type   node: :class:`Node`

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_start_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exShutdownNode(self, node):
		"""
        Shutdown a running node.

        Note: Usually this will result in sending an ACPI event to the node.

        :param  node: Node which should be used
        :type   node: :class:`Node`

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_shutdown_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exSuspendNode(self, node):
		"""
        Suspend a running node.

        :param  node: Node which should be used
        :type   node: :class:`Node`

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_suspend_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exResumeNode(self, node):
		"""
        Resume a suspended node.

        :param  node: Node which should be used
        :type   node: :class:`Node`

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_resume_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exTakeNodeScreenshot(self, node, directory, screen=0):
		"""
        Take a screenshot of a monitoring of a running instance.

        :param node: Node to take the screenshot of.
        :type node: :class:`libcloud.compute.base.Node`

        :param directory: Path where the screenshot will be saved.
        :type directory: ``str``

        :param screen: ID of the monitor to take the screenshot of.
        :type screen: ``int``

        :return: Full path where the screenshot has been saved.
        :rtype: ``str``
        """
		try:
			if not screen:
				screen = 0
			if node:
				node = node.obj
			return self.conn.ex_take_node_screenshot(node, directory, screen)
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetHypervisorHostname(self):
		"""
        Return a system hostname on which the hypervisor is running.
        """
		try:
			return self.conn.ex_get_hypervisor_hostname()
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetHypervisorSysinfo(self):
		"""
        Retrieve hypervisor system information.

        :rtype: ``dict``
        """
		try:
			return self.conn.ex_get_hypervisor_sysinfo()
		except Exception, ex:
			raise wrap_exception(ex)

