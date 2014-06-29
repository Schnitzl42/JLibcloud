'''
Created on 04.01.2014

@author: root
'''
from org.askalon.jlibcloud.compute.wrapperInterfaces.base import NodeLocation as JNodeLocation
from javaimpl.compute.utils import none_check

class NodeLocationImpl(JNodeLocation):
    '''
    classdocs
    '''
    def __init__(self, location):
        '''
        Constructor
        '''
        #keep a reference to access in jython
        self.location = location
        self.obj = location
        if hasattr(location, 'id'):
            self.idp = none_check(location.id, "")
        else:
            self.idp = ""
        if hasattr(location, 'name'):
            self.namep = none_check(location.name, "")
        else:
            self.namep = ""
        if hasattr(location, 'country'):
            self.countryp = none_check(location.country, "")
        else:
            self.countryp = ""
        if hasattr(location, '__repr__()'):
            self.reprp = location.__repr__()
        else:
            self.reprp = str(location)
            
    def getId(self):
        return self.idp
    
    def getName(self):
        return self.namep
    
    def getCountry(self):
        return self.countryp
    
    def toString(self):
        return self.reprp