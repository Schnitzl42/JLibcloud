'''
Created on 11.12.2013

@author: root
'''
from org.askalon.jlibcloud.compute.wrapperInterfaces.base import Node as JNode
from org.askalon.jlibcloud.compute.types import NodeState

from javaimpl.compute.utils import none_check

class NodeImpl(JNode):
    """
    Represents a node.
    """
    def __init__(self, node):
        """
        :param node: an existing node
        :type node: :class:``libcloud.compute.base.Node``
        """
        self.node = node
        self.obj = node
        if hasattr(node, 'uuid'):
            self.uuidp = none_check(node.uuid, "")
        else:
            self.uuidp = ""
        if hasattr(node, 'id'):
            self.idp = none_check(node.id, "")
        else:
            self.idp = ""
        if hasattr(node, 'name'):
            self.namep = none_check(node.name, "")
        else:
            self.namep = ""
        if hasattr(node, 'extra'):
            self.extrap = node.extra
        else:
            self.extrap = {}  
        if hasattr(node, '__repr__()'):
            self.reprp = node.__repr__()
        else:
            self.reprp = str(node) 
        
    def getUUID(self):
        return self.uuidp
    
    def getID(self):
        return self.idp
    
    def getName(self):
        return self.namep
    
    def getState(self):
        curr_state = None
        if hasattr(self.node, 'state'):
            curr_state = self.node.state
        if curr_state == 0:
           return NodeState.RUNNING
        elif curr_state == 1:
            return NodeState.REBOOTING
        elif curr_state == 2:
            return NodeState.TERMINATED
        elif curr_state == 3:
            return NodeState.PENDING
        elif curr_state == 4:
            return NodeState.UNKNOWN
        elif curr_state == 5:
            return NodeState.STOPPED
        else:
            return NodeState.UNKNOWN
        
        
    def getPublicIP(self):
        if hasattr(self.node, 'public_ips'):
            for ip in self.node.public_ips:
                return ip
        else:
            return ''
    
    def getPrivateIP(self):
        if hasattr(self.node, 'private_ips'):
            for ip in self.node.private_ips:
                return ip
        else:
            return ''
    
    def getSize(self):
        from NodeSizeImpl import NodeSizeImpl
        if hasattr(self.node, 'size'):
            return NodeSizeImpl(self.node.size)
        else:
            return NodeSizeImpl(None)
    
    def getImage(self):
        from NodeImageImpl import NodeImageImpl
        if hasattr(self.node, 'image'):
            return NodeImageImpl(self.node.image)
        else:
            return NodeImageImpl(None)
    
    def getExtra(self):
        return self.extrap
    
    def reboot(self):
        return self.node.reboot()
        
    def destroy(self):
        self.node.destroy()
    
    def toString(self):
        return self.reprp
    