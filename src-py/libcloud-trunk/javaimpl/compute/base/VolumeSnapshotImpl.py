'''
Created on 05.01.2014

@author: root
'''
from org.askalon.jlibcloud.compute.wrapperInterfaces.base import VolumeSnapshot as JVolumeSnapshot
from javaimpl.compute.utils import none_check

class VolumeSnapshotImpl(JVolumeSnapshot):
    '''
    classdocs
    '''
    def __init__(self, snapshot):
        '''
        Constructor
        '''
        #keep a reference to access in jython
        self.snapshot = snapshot
        self.obj = snapshot
        if hasattr(snapshot, 'id'):
            self.idp = none_check(snapshot.id, "")
        else:
            self.idp = ""
        if hasattr(snapshot, 'size'):
            self.sizep = none_check(snapshot.size, -1)
        else:
            self.sizep = -1
        if hasattr(snapshot, 'extra'):
            self.extrap = snapshot.extra
        else:
            self.extrap = {}  
        
    def getId(self):
        return self.idp
        
    def getSize(self):
        return self.sizep
        
    def getExtra(self):
        return self.extrap
        
    def destroy(self):
        return self.snapshot.destroy() 
        
    def toString(self):
        return '<VolumeSnapshot id=%s size=%s>' % (
               self.idp, self.sizep)
        
        
       