'''
Created on 04.01.2014

@author: root
'''
from org.askalon.jlibcloud.compute.wrapperInterfaces.base import NodeImage as JNodeImage
from javaimpl.compute.utils import none_check

class NodeImageImpl(JNodeImage):
    '''
    classdocs
    '''
    def __init__(self, image):
        '''
        Constructor
        '''
        #keep a reference to access in jython
        self.image = image
        self.obj = image
        if hasattr(image, 'uuid'):
            self.uuidp = none_check(image.uuid, "")
        else:
            self.uuidp = ""
        if hasattr(image, 'id'):
            self.idp = none_check(image.id, "")
        else:
            self.idp = ""
        if hasattr(image, 'name'):
            self.namep = none_check(image.name, "")
        else:
            self.namep = ""
        if hasattr(image, 'extra'):
            self.extrap = image.extra
        else:
            self.extrap = {}  
        if hasattr(image, '__repr__()'):
            self.reprp = image.__repr__()
        else:
            self.reprp = str(image)
       
            
    def getUUID(self):
        return self.uuidp
                
    def getId(self):
        return self.idp
    
    def getName(self):
        return self.namep
    
    def getExtra(self):
        return self.extrap
    
    def toString(self):
        return self.reprp