import sys, os

def none_check(att, default):
    '''
    Returns the given attribute if != None
    else the default value is returned
    '''
    #convert att to str in case it is an object
    #to avoid throwing an exception
    if default == ' ':
        if att:
            return str(att)
    return att if att!=None else default

def wrap_listing(listing, clazz):
    '''
    wraps the given listing into a list
    containing the given class
    '''
    #check if listing is only one element
    if isinstance(listing, list):
        listt = []
        for entry in listing:
            listt.append(clazz(entry))
        return listt
    else:
        return [clazz(listing)]
  

def jlist_str_to_pylist(jlist):
    if jlist:
        listt = []
        for elem in jlist:
            listt.append(str(elem))
        return listt
    else:
        return None
    
def jlist_obj_to_pylist(jlist):
    if jlist:
        listt = []
        for elem in jlist:
            listt.append(elem.obj)
        return listt
    else:
        return None

def read_from_jar(relative_path):
    '''
    reads a file from within a jar. returns the file content
    '''
    from java.lang import ClassLoader
    from java.io import InputStreamReader, BufferedReader

    loader = ClassLoader.getSystemClassLoader()
    stream = loader.getResourceAsStream(relative_path)
    reader = BufferedReader(InputStreamReader(stream))
    
    line = reader.readLine()
    buf = ''
    while line is not None:
        buf += line
        line = reader.readLine()
        
    reader.close()
    stream.close()
    return buf

def wrap_exception(ex):
    '''
    call this function within an except
    to wrap a given exception into a LibcloudException
    
    name: ex
    type: ex :the exception derived from 'Exception'
    '''
    import traceback
    from org.askalon.jlibcloud.compute.types import LibcloudException
    
    exc_type, exc_value, exc_tb = sys.exc_info()
    trace = []
    for elem in traceback.extract_tb(exc_tb):
        lst = []
        for val in elem:
            lst.append(str(val))
        trace.append(lst)
    return LibcloudException(str(exc_type), str(exc_value), trace)

def get_property(self, keyword, keyword_str, kwargs, lambdaf):
    '''
    this function is used to parse the nodeTemplate
    in the create node function and read a keyword from
    a properties file.
    
    :param:   keyword:    the keyword to check
                            from nodeTemplate.getKeyword()
    :type:    keyword:    `str` or `int` or `bool` or `float` or `None`
    
    :param:    keyword_str: a string representation of the keyword
                            to aissign the variable in kwargs
    :type:    keyword_str: `str`
    
    :param:    kwargs:    the keyword map, which is passed to
                            create_node() later on
    :type:    kwargs:    `dict`
    
    :param:    lambdaf    a function to assign the correct 
                           type from the prop file to kwargs
                           e.g. string was read from prop-file
                            but the keyword should be an integer.
                            the function takes keyword as argument
    :type:    labmdaf    'lambda'
    
    :return:    the new kwargs map
    :rtype:    `dict`
    '''
    if not keyword and self.prop:
        keyword = self.prop.getProperty(keyword_str)
    #check for None if keyword is boolean false
    if keyword != None:
        kwargs[keyword_str] = lambdaf(keyword)
    return kwargs
    
def get_property_list(self, keyword, keyword_str, kwargs, lambdaf):
    '''
    nearly the same as get_property(), except the keyword type
    is list of str. the keywords are enumerated with an integer
    suffix in the properties file. e.g.: 
    group0=FirstGroup
    group1=SecondGroup

    :param: keyword:    the keyword
    :type: keyword:    `list` of `str`
    '''
    if not keyword and self.prop:
        keyword = []
        for i in range(0,15):
            tmp = self.prop.getProperty(keyword_str+str(i))
            if tmp:
                keyword.append(tmp)
    #empty keyword-list is false
    if keyword:
        kwargs[keyword_str] = lambdaf(keyword)
    return kwargs

def jmap_to_pymap(jmap):
    '''
    converts a java map into a python map
    
    :param  jmap: a java map
    :type   jmap: Map<String, org.askalon.jlibcloud.compute.wrapperInterfaces.deployment.Arg>
    
    :return: `dict`
    '''
    pymap = None
    if jmap:
        from org.askalon.jlibcloud.compute.types import Arg
        from org.askalon.jlibcloud.compute.types import NestedMap
        from java.util import Map
        pymap = {}
        for key in jmap.keySet():
            if isinstance(jmap, NestedMap):
                if jmap.isNested(key):
                    pymap[key] = jmap_to_pymap(jmap.getMap(key))
                    continue
            value = jmap.get(key)
            if isinstance(value, Arg):
                if value.isInt():
                    pymap[key] = value.getInt()
                elif value.isFloat():
                    pymap[key] = value.getFloat()
                else:
                    pymap[key] = value.getString()
            else:
                pymap[key] = value
    return pymap

def jlist_map_to_pylist_map(jlist_map):
    pylist_map = None
    if jlist_map:
        pylist_map = []
        for map in jlist_map:
            pylist_map.append(jmap_to_pymap(map))
    return pylist_map
    