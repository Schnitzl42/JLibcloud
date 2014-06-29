# Parts or the whole documentation of this module
# are copied from the respective module:
# libcloud/compute/drivers/opsource.py
# see also:
# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/opsource.py
#
# Apache Libcloud is licensed under the Apache 2.0 license.
# For more information, please see LICENSE and NOTICE file or:
# http://www.apache.org/licenses/LICENSE-2.0
from javaimpl.compute.ComputeContextImpl import ComputeContextImpl
from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception, jlist_str_to_pylist
from javaimpl.compute.utils import jlist_obj_to_pylist, get_property, get_property_list
from javaimpl.compute.utils import jmap_to_pymap, jlist_map_to_pylist_map
from org.askalon.jlibcloud.compute.driverSpecific.opsource import OpsourceNodeTemplateImpl
from javaimpl.compute.base.NodeImageImpl import NodeImageImpl
from javaimpl.compute.base.NodeLocationImpl import NodeLocationImpl
from javaimpl.compute.base.NodeImpl import NodeImpl
from org.askalon.jlibcloud.compute.driverSpecific.opsource import OpsourceComputeContext

class OpsourceComputeContextImpl(ComputeContextImpl, OpsourceComputeContext):
	def __init__(self, builder):
		ComputeContext.__init__(self, builder)

	def createNode(self, node_temp):
		"""
        Create a new opsource node

        :keyword    name:   String with a name for this new node (required)
        :type       name:   ``str``

        :keyword    image:  OS Image to boot on node. (required)
        :type       image:  :class:`NodeImage`

        :keyword    auth:   Initial authentication information for the
                            node (required)
        :type       auth:   :class:`NodeAuthPassword`

        :keyword    ex_description:  description for this node (required)
        :type       ex_description:  ``str``

        :keyword    ex_network:  Network to create the node within (required)
        :type       ex_network: :class:`OpsourceNetwork`

        :keyword    ex_isStarted:  Start server after creation? default
                                   true (required)
        :type       ex_isStarted:  ``bool``

        :return: The newly created :class:`Node`. NOTE: Opsource does not
                 provide a
                 way to determine the ID of the server that was just created,
                 so the returned :class:`Node` is not guaranteed to be the same
                 one that was created.  This is only the case when multiple
                 nodes with the same name exist.
        :rtype: :class:`Node`
        """
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._parse_opsource_template(node_temp, kwargs)
			return wrap_listing(self.conn.create_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def deployNode(self, node_temp):
		try:
			kwargs = self._eval_template(node_temp)
			kwargs = self._eval_deploy_template(node_temp, kwargs)
			kwargs = self._parse_opsource_template(node_temp, kwargs)
			return wrap_listing(self.conn.deploy_node(**kwargs), NodeImpl)
		except Exception, ex:
			raise wrap_exception(ex)


	def _parse_opsource_template(self, node_temp, kwargs):
		name = node_temp.getName()
		kwargs = get_property(self, name, 'name',
					 kwargs,lambda x : x)
		image = node_temp.getImage()
		kwargs = get_property(self, image, 'image',
					 kwargs,lambda x : x.obj)
		ex_description = node_temp.getExDescription()
		kwargs = get_property(self, ex_description, 'ex_description',
					 kwargs,lambda x : x)
		ex_network = node_temp.getExNetwork()
		kwargs = get_property(self, ex_network, 'ex_network',
					 kwargs,lambda x : x.obj)
		ex_isStarted = node_temp.getExIsStarted()
		l = lambda x : True if x == 'true' or x == True else False
		kwargs = get_property(self, ex_isStarted, 'ex_isStarted',
					 kwargs,l)
		return kwargs

	def getTemplateBuilder(self):
		return OpsourceNodeTemplateImpl.newBuilder()

	def exStartNode(self, node):
		"""
        Powers on an existing deployed server

        :param      node: Node which should be used
        :type       node: :class:`Node`

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_start_node(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exShutdownGraceful(self, node):
		"""
        This function will attempt to "gracefully" stop a server by
        initiating a shutdown sequence within the guest operating system.
        A successful response on this function means the system has
        successfully passed the request into the operating system.

        :param      node: Node which should be used
        :type       node: :class:`Node`

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_shutdown_graceful(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exPowerOff(self, node):
		"""
        This function will abruptly power-off a server.  Unlike
        ex_shutdown_graceful, success ensures the node will stop but some OS
        and application configurations may be adversely affected by the
        equivalent of pulling the power plug out of the machine.

        :param      node: Node which should be used
        :type       node: :class:`Node`

        :rtype: ``bool``
        """
		try:
			if node:
				node = node.obj
			return self.conn.ex_power_off(node)
		except Exception, ex:
			raise wrap_exception(ex)

	def exListNetworks(self):
		"""
        List networks deployed across all data center locations for your
        organization.  The response includes the location of each network.

        :return: a list of OpsourceNetwork objects
        :rtype: ``list`` of :class:`OpsourceNetwork`
        """
		try:
			return wrap_listing(self.conn.ex_list_networks(), OpsourceNetworkImpl)
		except Exception, ex:
			raise wrap_exception(ex)

	def exGetLocationById(self, id):
		"""
        Get location by ID.

        :param  id: ID of the node location which should be used
        :type   id: ``str``

        :rtype: :class:`NodeLocation`
        """
		try:
			return NodeLocationImpl(self.conn.ex_get_location_by_id(id))
		except Exception, ex:
			raise wrap_exception(ex)


from org.askalon.jlibcloud.compute.driverSpecific.opsource import OpsourceStatus

class OpsourceStatusImpl(OpsourceStatus):
	'''
    Opsource API pending operation status class
        action, requestTime, username, numberOfSteps, updateTime,
        step.name, step.number, step.percentComplete, failureReason,
	'''

	def __init__(self, obj):
		self.obj=obj
		if hasattr(obj, 'action'):
			self.actionp = none_check(obj.action, ' ')
		else:
			self.actionp = ' '
		if hasattr(obj, 'requestTime'):
			self.requestTimep = none_check(obj.requestTime, ' ')
		else:
			self.requestTimep = ' '
		if hasattr(obj, 'userName'):
			self.userNamep = none_check(obj.userName, ' ')
		else:
			self.userNamep = ' '
		if hasattr(obj, 'numberOfSteps'):
			self.numberOfStepsp = none_check(obj.numberOfSteps, ' ')
		else:
			self.numberOfStepsp = ' '
		if hasattr(obj, 'updateTime'):
			self.updateTimep = none_check(obj.updateTime, ' ')
		else:
			self.updateTimep = ' '
		if hasattr(obj, 'step_name'):
			self.step_namep = none_check(obj.step_name, ' ')
		else:
			self.step_namep = ' '
		if hasattr(obj, 'step_number'):
			self.step_numberp = none_check(obj.step_number, ' ')
		else:
			self.step_numberp = ' '
		if hasattr(obj, 'step_percentComplete'):
			self.step_percentCompletep = none_check(obj.step_percentComplete, ' ')
		else:
			self.step_percentCompletep = ' '
		if hasattr(obj, 'failureReason'):
			self.failureReasonp = none_check(obj.failureReason, ' ')
		else:
			self.failureReasonp = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getAction(self):
		return self.actionp

	def getRequestTime(self):
		return self.requestTimep

	def getUserName(self):
		return self.userNamep

	def getNumberOfSteps(self):
		return self.numberOfStepsp

	def getUpdateTime(self):
		return self.updateTimep

	def getStepName(self):
		return self.step_namep

	def getStepNumber(self):
		return self.step_numberp

	def getStepPercentComplete(self):
		return self.step_percentCompletep

	def getFailureReason(self):
		return self.failureReasonp

	def toString(self):
		return self.reprp


from org.askalon.jlibcloud.compute.driverSpecific.opsource import OpsourceNetwork

class OpsourceNetworkImpl(OpsourceNetwork):
	'''
    Opsource network with location.
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
		if hasattr(obj, 'description'):
			self.descriptionp = none_check(obj.description, ' ')
		else:
			self.descriptionp = ' '
		if hasattr(obj, 'location'):
			self.locationp = NodeLocationImpl(obj.location)
		else:
			self.locationp = NodeLocationImpl(None)
		if hasattr(obj, 'privateNet'):
			self.privateNetp = none_check(obj.privateNet, ' ')
		else:
			self.privateNetp = ' '
		if hasattr(obj, 'multicast'):
			self.multicastp = none_check(obj.multicast, ' ')
		else:
			self.multicastp = ' '
		if hasattr(obj, 'status'):
			self.statusp = none_check(obj.status, ' ')
		else:
			self.statusp = ' '
		if hasattr(obj, '__repr__()'):
			self.reprp = obj.__repr__()
		else:
			self.reprp = str(obj)

	def getId(self):
		return self.idp

	def getName(self):
		return self.namep

	def getDescription(self):
		return self.descriptionp

	def getLocation(self):
		return self.locationp

	def getPrivateNet(self):
		return self.privateNetp

	def getMulticast(self):
		return self.multicastp

	def getStatus(self):
		return self.statusp

	def toString(self):
		return self.reprp

