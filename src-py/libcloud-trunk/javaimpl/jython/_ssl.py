import os, sys
import socket
        
from java.lang import String as JString

# fake values
OPENSSL_VERSION_NUMBER = 0
OPENSSL_VERSION_INFO = 0
OPENSSL_VERSION = 0
SSLError = 0
CERT_NONE = 0
CERT_OPTIONAL = 0
CERT_REQUIRED = 0
PROTOCOL_SSLv2 = 0
PROTOCOL_SSLv3 = 0
PROTOCOL_SSLv23 = 0
PROTOCOL_TLSv1 = 0
RAND_status = 0
RAND_egd = 0
RAND_add = 0
SSL_ERROR_ZERO_RETURN = 0
SSL_ERROR_WANT_READ = 0
SSL_ERROR_WANT_WRITE = 0
SSL_ERROR_WANT_X509_LOOKUP = 0
SSL_ERROR_SYSCALL = 0
SSL_ERROR_SSL = 0
SSL_ERROR_WANT_CONNECT = 0
SSL_ERROR_EOF = 0
SSL_ERROR_INVALID_ERROR_CODE = 0

class SSLSocket:
    def __init__(self, sock, keyfile = None, certfile = None, server_side = False,
                  *args, **kwargs):      
        if keyfile or certfile:
            raise ssl.SSLError('keyfile and certfile not supported.') 
        self._sslsock = socket.ssl(sock, keyfile, certfile)

    def do_handshake(self):
        return self._sslsock.ssl_sock.startHandshake()

    def read(self, *args):
        return self._sslsock.read(*args)

    def recv(self, *args):
        return self._sslsock.read(*args)

    def write(self, data):
        return self._sslsock.write(data)

    def send(self, data):
        return self._sslsock.write(data)

    def sendall(self, data):
        return self._sslsock.write(data)

    def _wrap_alt_names(self, altNames):
        '''create pythonic subjectAltName-entry (tuple of tuples)
        parse: [[2, ec2.us-east-1.amazonaws.com], [2, ec2.amazonaws.com], [2, us-east-1.ec2.amazonaws.com]]
        into: (('DNS', 'ec2.us-east-1.amazonaws.com'), ('DNS', 'ec2.amazonaws.com'), ('DNS', 'us-east-1.ec2.amazonaws.com'))'''
        tuple = ()
        if altNames != None:
            for elem in altNames:
                tuple1 = ('DNS', elem[1]),
                tuple = tuple + tuple1
        return tuple
        
    def _wrap_subject_issuer(self, subj_iss):
        '''create pythonic subject entry
        parse: CN=ec2.us-east-1.amazonaws.com, O=Amazon.com Inc., L=Seattle, ST=Washington, C=US
        into: ((('commonName', 'ec2.us-east-1.amazonaws.com'),), (('organizationName', 'Amazon.com Inc.'),), (('localityName', 'Seattle'),), 
        (('stateOrProvinceName', 'Washington'),), (('countryName', 'US'),))'''
        names = {'C=' : 'countryName', 'OU=' : 'organizationUnitName', 'O=' : 'organizationName', 
		   'L=' : 'localityName', 'CN=' : 'commonName', 'ST=' : 'stateOrProvinceName'}
        subjects = subj_iss.split(',')
        tuple = ()
        for subj in subjects:
	    for key in names.keys():
	         if key in subj and key != 'OU=':
		      tuple += ((names[key], subj.replace(key, "")),),
		      break
		 elif key in subj:
		      tuple += ((names[key], subj.replace(key, "")),),
        return tuple
    
    def _format_date(self, date):
	'''
	convert
	date: Fri Jul 11 04:57:59 CEST 2014
	to date.toGMTString(): 11 Jul 2014 02:57:59 GMT
	to: Jul 11 02:57:59 2014 GMT
	'''
	if date != None:
	    parts = JString(date.toGMTString().encode('ascii','ignore')).split("\\s+")
	    if len(parts) == 5:
	         return parts[1] +' '+ parts[0] +' '+ parts[3] +' '+ parts[2] +' '+ parts[4]
	else:	 
	    return ""
	
    def peer_certificate(self, binary_form=False):
        '''returns the essential information from the server-certificate
        to be parsed and accepted by libclouds libcloud/httplib_ssl._verify_hostname(self, hostname, cert) method.'''
        cert = self._sslsock._get_server_cert()
        pycert = {}
        serial = cert.getSerialNumberObject().toString().replace(" ", "")
        serial = serial.replace("SerialNumber:[", "").replace("]", "")
        pycert['serialNumber'] = serial
        pycert['subject'] = self._wrap_subject_issuer(cert.getSubjectX500Principal().getName())
        pycert['issuer'] = self._wrap_subject_issuer(cert.getIssuerX500Principal().getName())
        pycert['notAfter'] = self._format_date(cert.getNotAfter())
        pycert['notBefore'] = self._format_date(cert.getNotBefore())
        altNames = cert.getSubjectAlternativeNames()
        if altNames != None:
            pycert['subjectAltName'] = self._wrap_alt_names(altNames)
        pycert['version'] = cert.getVersion()
        return pycert
            
def sslwrap(sock, server_side, keyfile, certfile, *args, **keyw):
    return SSLSocket(sock, server_side = server_side)

def wrap_socket(*args, **kwargs):
    return SSLSocket(*args, **kwargs)

ssl = wrap_socket