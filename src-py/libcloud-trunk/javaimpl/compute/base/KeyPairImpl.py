'''
Created on 07.01.2014

@author: root
'''
from org.askalon.jlibcloud.compute.wrapperInterfaces.base import KeyPair as JKeyPair

from javaimpl.compute.utils import none_check

class KeyPairImpl(JKeyPair):
    '''
    classdocs
    '''


    def __init__(self, key_pair):
        '''
        Constructor
        '''
        self.key_pair = key_pair
        self.obj = key_pair
        if hasattr(key_pair, 'name'):
              self.namep = none_check(key_pair.name, "")
        else:
            self.namep = ""
        if hasattr(key_pair, 'fingerprint'):
              self.fingerprintp = none_check(key_pair.fingerprint, "")
        else:
            self.fingerprintp = ""
        if hasattr(key_pair, 'public_key'):
              self.public_keyp = none_check(key_pair.public_key, "")
        else:
            self.public_keyp = ""
        if hasattr(key_pair, 'private_key'):
              self.private_keyp = none_check(key_pair.private_key, "")
        else:
            self.private_keyp = ""
        if hasattr(key_pair, 'extra'):
              self.extrap = key_pair.extra
        else:
            self.extrap = {}
        if hasattr(key_pair, '__repr__()'):
            self.reprp = key_pair.__repr__()
        else:
            self.reprp = str(key_pair)
            
    def getName(self):
        return self.namep
    
    def getFingerprint(self):
        return self.fingerprintp
    
    def getPublicKey(self):
        return self.public_keyp
    
    def getPrivateKey(self):
        return self.private_keyp
    
    def getExtra(self):
        return self.extrap
    
    def toString(self):
        return self.reprp
            
    