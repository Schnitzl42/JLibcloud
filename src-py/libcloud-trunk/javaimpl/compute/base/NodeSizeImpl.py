'''
Created on 03.01.2014

@author: root
'''
from org.askalon.jlibcloud.compute.wrapperInterfaces.base import NodeSize as JNodeSize
from javaimpl.compute.utils import none_check

class NodeSizeImpl(JNodeSize):
   
    def __init__(self, node_size):
        #keep a reference to access in jython
        self.size = node_size
        self.obj = node_size
        if hasattr(node_size, 'uuid'):
            self.uuidp = none_check(node_size.uuid, "")
        else:
            self.uuidp = ""
        if hasattr(node_size, 'id'):
            self.idp = none_check(node_size.id, "")
        else:
            self.idp = ""
        if hasattr(node_size, 'name'):
            self.namep = none_check(node_size.name, "")
        else:
            self.namep = ""
        if hasattr(node_size, 'ram'):
            self.ramp = none_check(node_size.ram, -1)
        else:
            self.ramp = -1
        if hasattr(node_size, 'disk'):
            self.diskp = none_check(node_size.disk, -1)
        else:
            self.diskp = -1 
        if hasattr(node_size, 'bandwidt'):
            self.bandwidtp = none_check(node_size.bandwidt, -1)
        else:
            self.bandwidtp = -1
        if hasattr(node_size, 'price'):
            self.pricep = none_check(node_size.price, -1.0)
        else:
            self.pricep = -1.0
        if hasattr(node_size, '__repr__()'):
            self.reprp = node_size.__repr__()
        else:
            self.reprp = str(node_size)     
   
    def getUUID(self):
        return self.uuidp
    
    def getId(self):
        return self.idp
    
    def getName(self):
        return self.namep 
    
    def getRamMB(self):
        return self.ramp
    
    def getDiskSizeGB(self):
        return self.diskp
    
    def getBandwidth(self):
        return self.bandwidtp
    
    def getHourPriceDollar(self):
        return self.pricep
    
    def toString(self):
        return self.reprp