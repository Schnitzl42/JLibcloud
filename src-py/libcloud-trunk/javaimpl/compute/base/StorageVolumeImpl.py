'''
Created on 05.01.2014

@author: root
'''
from org.askalon.jlibcloud.compute.wrapperInterfaces.base import StorageVolume as JStorageVolume
from javaimpl.compute.utils import none_check

class StorageVolumeImpl(JStorageVolume):
    '''
    classdocs
    '''
    def __init__(self, volume):
        '''
        Constructor
        '''
        #keep a reference to access in jython
        self.volume = volume
        self.obj = volume
        if hasattr(volume, 'uuid'):
            self.uuidp = none_check(volume.uuid, "")
        else:
            self.uuidp = ""
        if hasattr(volume, 'id'):
            self.idp = none_check(volume.id, "")
        else:
            self.idp = ""
        if hasattr(volume, 'name'):
            self.namep = none_check(volume.name, "")
        else:
            self.namep = ""
        if hasattr(volume, 'size'):
            self.sizep = none_check(volume.size, -1)
        else:
            self.sizep = -1
        if hasattr(volume, 'extra'):
            self.extrap = volume.extra
        else:
            self.extrap = {}  
        if hasattr(volume, '__repr__()'):
            self.reprp = volume.__repr__()
        else:
            self.reprp = str(volume)  
            
    def getUUID(self):
        return self.uuidp
        
    def getId(self):
        return self.idp
        
    def getName(self):
        return self.namep
        
    def getSizeGB(self):
        return self.sizep
        
    def getExtra(self):
        return self.extrap
               
    def attach(self, node, device=None):
        return self.volume.attach(node.node, device)
            
    def detach(self):
        return self.volume.detach()
        
    def destroy(self):
        return self.volume.destroy()
            
    def listSnapshots(self):
        return self.volume.list_snapshots()
        
    def createSnapshot(self, name):
        return self.volume.snapshot(name)
        
    def toString(self):
        return self.reprp