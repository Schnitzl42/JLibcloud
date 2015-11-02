JLibcloud
=========

Java wrapper for Apache's Libcloud
(supports only the compute part so far and uses Libcloud 0.14.1)

Examples on how to use JLibcloud can be found under org/askalon/jlibcloud/compute/examples.
To use a pre-built version of JLibcloud add the jars from dist/ to your Java project.


## Setup using Eclipse

The project was written in Eclipse Kepler(4.3) and supports Java 6.
Link for the latest Eclipse version: https://www.eclipse.org/downloads/

To import the project Jython(http://www.jython.org/) and the PyDev plug in are required.
Tested and working Jython versions are: 2.5.3 and 2.5.4rc1.

### Jython
Download the installer jar from http://www.jython.org/ and run it with:
'''
$ java -jar jython_installer-x-x-x.jar 
'''
or double tap the jar. Afterwards follow the installation process.
To run JLibcloud correctly, choose the full installation of Jython, including sources.
When asked where to install Jython, */usr/local/lib/jython.x.x.x* is recommended.


To avoid conflicts with JLibcloud`s SSL module navigate to your Jython installation.
Enter the *Lib* folder and open the file *socket.py*. Comment
out *ssl_socket.startHandshake()* using a *#*. The 
*startHandshake()* method will be called from within JLibcloud.
In Jython 2.5.3 the line is located in line 1720 and in Jython 2.5.4rc1 in line 1789. 

### PyDev
After installing Jython install the PyDev plugin for Eclipse. 
Instructions on how to install and configure PyDev can also be found under 
http://pydev.org/manual_101_install.html and
http://www.jython.org/jythonbook/en/1.0/JythonIDE.html#eclipse.


To install the PyDev plugin correctly under Java 6 the PyDev version has to be lower than 3.0.
Installation instructions and older versions are listed under *Installing with the zip file*
on: *http://pydev.org/manual_101_install.html*

After the installation routine go to "Window" - "Preferences".
If a section named "PyDev" is displayed the installation was successful. In
"PyDev" - "Interpreters" select "Jython Interpreters". Click on "New" to add
a new Jython interpreter. Enter a name like "jythonX.X.X" and browse the *jython.jar*.
The *jython.jar* is located in the Jython installation directory (*/usr/local/lib/jython2.5.3/jython.jar*).

##LICENSE
This work is licensed under the Apache Commons License 2.0.
http://www.apache.org/licenses/LICENSE-2.0
