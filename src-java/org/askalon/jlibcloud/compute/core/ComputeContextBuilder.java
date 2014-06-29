package org.askalon.jlibcloud.compute.core;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

import org.askalon.jlibcloud.compute.driverSpecific.abiquo.AbiquoComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.brightbox.BrightboxComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.cloudframes.CloudFramesComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.cloudsigma.CloudSigma_1_0_ComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.cloudsigma.CloudSigma_2_0_ComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.cloudstack.CloudStackComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.digitalocean.DigitalOceanComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.dreamhost.DreamhostComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.ec2Ex.EC2ComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.elasticstack.ElasticStackBaseComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.gandi.GandiComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.gce.GCEComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.gogrid.GoGridComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.hostvirtual.HostVirtualComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.ibm_sce.IBMComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.joyent.JoyentComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.libvirt_driver.LibvirtComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.linode.LinodeComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.nephoscale.NephoscaleComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.opennebula.OpenNebulaComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.opennebula.OpenNebula_2_0_ComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.opennebula.OpenNebula_3_0_ComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.openstack.OpenStack_1_0_ComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.openstack.OpenStack_1_1_ComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.opsource.OpsourceComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.rimuhosting.RimuHostingComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.softlayer.SoftLayerComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.vcl.VCLComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.vcloud.VCloudComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.vcloud.VCloud_1_5_ComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.voxel.VoxelComputeContext;
import org.askalon.jlibcloud.compute.driverSpecific.vpsnet.VPSNetComputeContext;
import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;
import org.python.core.Py;
import org.python.util.PythonInterpreter;

/**
 * This class is used to instanciate a
 * {@link org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext} class.
 * Using {@link org.askalon.jlibcloud.compute.core.Providers} a 
 * ComputeContext for EC2 can be instantiated with the following code:
 * <pre>
 * 
 * 
 * </pre>
 */
public class ComputeContextBuilder {

	private final String provider;
	private String identity = null;
	private String authentication = null;
	// key, secret=None, secure=True, host=None, port=None, api_version=None
	private Boolean secure = null;
	private String host = null;
	private Integer port = null;
	private String apiVersion = null;
	private String region = null;
	private String filePath = null;
	private Properties prop = null;
	// abiquo
	private String endpoint = null;
	// openStack
	private String ex_force_base_url = null;
	private String ex_force_auth_url = null;
	private String ex_force_auth_version = null;
	private String ex_force_auth_token = null;
	private String ex_tenant_name = null;
	private String ex_force_service_type = null;
	private String ex_force_service_name = null;
	private String ex_force_service_region = null;

	private PythonInterpreter interpreter;

	private ComputeContextBuilder(String provider) {
		this.provider = provider;
	}

	public static ComputeContextBuilder newBuilder(String provider) {
		return new ComputeContextBuilder(provider);
	}

	/*--------------------------------------------
	 *  getter
	 *--------------------------------------------*/
	public String getProvider() {
		return this.provider;
	}

	public String getIdentity() {
		return this.identity;
	}

	public String getAuthentication() {
		return this.authentication;
	}

	public Boolean isSecure() {
		return secure;
	}

	public String getHost() {
		return host;
	}

	public Integer getPort() {
		return port;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public Properties getProp() {
		return prop;
	}

	public String getRegion() {
		return this.region;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public Properties getProperties() {
		return this.prop;
	}

	// abiquo
	public String getEndpoint() {
		return endpoint;
	}

	// OpenStack
	public String getExForceBaseUrl() {
		return ex_force_base_url;
	}

	public String getExForceAuthUrl() {
		return ex_force_auth_url;
	}

	public String getExForceAuthVersion() {
		return ex_force_auth_version;
	}

	public String getExForceAuthToken() {
		return ex_force_auth_token;
	}

	public String getExTenantName() {
		return ex_tenant_name;
	}

	public String getExForceServiceType() {
		return ex_force_service_type;
	}

	public String getExForceServiceName() {
		return ex_force_service_name;
	}

	public String getExForceServiceRegion() {
		return ex_force_service_region;
	}

	/*-------------------------------
	 * Setter
	 --------------------------------*/

	public void exForceBaseUrl(String exForceBaseUrl) {
		ex_force_base_url = exForceBaseUrl;
	}

	public void exForceAuthUrl(String exForceAuthUrl) {
		ex_force_auth_url = exForceAuthUrl;
	}

	public void exForceAuthVersion(String exForceAuthVersion) {
		ex_force_auth_version = exForceAuthVersion;
	}

	public String getExForceAuthToken(String exForceAuthToken) {
		return ex_force_auth_token;
	}

	public void exTenantName(String exTenantName) {
		ex_tenant_name = exTenantName;
	}

	public void exForceServiceType(String exForceServiceType) {
		ex_force_service_type = exForceServiceType;
	}

	public void exForceServiceName(String exForceServiceName) {
		ex_force_service_name = exForceServiceName;
	}

	public void exForceServiceRegion(String exForceServiceRegion) {
		ex_force_service_region = exForceServiceRegion;
	}

	/**
	 * dummy driver
	 * 
	 * @param identity
	 * @return this ComputeContextBuilder
	 */
	public ComputeContextBuilder credentials(String identity) {
		this.identity = identity;
		return this;
	}

	/**
	 * Providers: EC2, Eucalyptus, Rackspace
	 * 
	 * @param identity
	 * @param authentication
	 * @return this ComputeContextBuilder
	 */
	public ComputeContextBuilder credentials(String identity,
			String authentication) {
		this.authentication = authentication;
		return this.credentials(identity);
	}

	/**
	 * abiquo
	 * 
	 * @param identity
	 * @param authentication
	 * @param endpoint
	 * @return this ComputeContextBuilder
	 */
	public ComputeContextBuilder credentials(String identity,
			String authentication, String endpoint) {
		this.endpoint = endpoint;
		return this.credentials(identity, authentication);
	}

	/**
	 * Vcloud
	 * 
	 * @param identity
	 * @param authentication
	 * @param host
	 * @param apiVersion
	 * @return this ComputeContextBuilder
	 */
	public ComputeContextBuilder credentials(String identity,
			String authentication, String host, String apiVersion) {
		this.apiVersion = apiVersion;
		this.host = host;
		return this.credentials(identity, authentication);
	}

	/**
	 * Providers: OpenNebula
	 * 
	 * @param identity
	 * @param authentication
	 * @param secure
	 * @param host
	 * @param port
	 * @return this ComputeContextBuilder
	 */
	public ComputeContextBuilder credentials(String identity,
			String authentication, boolean secure, String host, int port) {
		this.secure = secure;
		this.host = host;
		this.port = port;
		return this.credentials(identity, authentication);
	}

	/**
	 * 
	 * @param identity
	 * @param authentication
	 * @param host
	 * @param port
	 * @param secure
	 * @param apiVersion
	 * @return this ComputeContextBuilder
	 */
	public ComputeContextBuilder credentials(String identity,
			String authentication, String host, int port, boolean secure,
			String apiVersion) {
		this.apiVersion = apiVersion;
		return this.credentials(identity, authentication, secure, host, port);
	}

	/**
	 * Providers: OpenStack
	 * 
	 * @param identity
	 * @param authentication
	 * @param secure
	 * @param host
	 * @param port
	 * @param ex_force_base_url
	 * @param ex_force_auth_url
	 * @param ex_force_auth_version
	 * @param ex_tenant_name
	 * @return this ComputeContextBuilder
	 */
	public ComputeContextBuilder credentials(String identity,
			String authentication, boolean secure, String host, int port,
			String ex_force_base_url, String ex_force_auth_url,
			String ex_force_auth_version, String ex_tenant_name) {
		this.ex_force_base_url = ex_force_base_url;
		this.ex_force_auth_url = ex_force_auth_url;
		this.ex_force_auth_version = ex_force_auth_version;
		this.ex_tenant_name = ex_tenant_name;
		return this.credentials(identity, authentication, secure, host, port);
	}

	/**
	 * set the region of the provider, if it supports different regions.
	 * 
	 * @param region
	 * @return this ComputeContextBuilder
	 */
	public ComputeContextBuilder region(String region) {
		this.region = region;
		return this;
	}

	/**
	 * laod the credentials from a file. For an example File see:
	 * libcloud-trunk/javaimpl/compute/secrets.py-dist
	 * <p><p>
	 * If this method is used, all other credentials methods
	 * are ignored.
	 * 
	 * @param filePath
	 *            trys to load the driver credentials from the file
	 * 
	 * @return this ComputeContextBuilder
	 */
	public ComputeContextBuilder credentialsFromFile(String filePath) {
		this.filePath = filePath;
		return this;
	}

	/**
	 * The same as @see #credentialsFromFile(String)
	 * trys to laod the credentials from:
	 * libcloud-trunk/javaimpl/compute/secrets.py
	 * 
	 * @return this ComputeContextBuilder
	 */
	public ComputeContextBuilder credentialsFromFile() {
		this.filePath = "std";
		return this;
	}

	/**
	 * Loads a given properties file for the
	 * {@link org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext}
	 * from the given filepath.
	 * 
	 * The
	 * {@link org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext
	 * ComputeContext} uses the properties when creating a node, if the required
	 * fields in the
	 * {@link org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate} are
	 * uninitialized.
	 * 
	 * <p>
	 * For valid attribute names use the Javadoc name from
	 * <tt>NodeTemplate.class</tt> or the <tt>NodeTemplateBuilder.class</tt>.
	 * Allowed fields for the standard
	 * {@link org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext}
	 * are:
	 * 
	 * <pre>
	 * {@code
	 * name=nodeName
	 * image_id=someImage
	 * size_id=someSize
	 * location_id=location
	 * ex_keyname=someKey
	 * }
	 * </pre>
	 * 
	 * In general for the <tt> ComputeContext</tt> and derived classes alls
	 * <tt>String, int, boolean</tt> and <tt>float</tt> values can be read from
	 * a .properties file. <tt>List\<String\></tt> or <tt>String[]</tt> values
	 * can also be read. Therfore add an an index from [0,14] to the attribute
	 * name. for example:
	 * 
	 * <pre>
	 * {@code
	 * ex_security_groups0=group1
	 * ex_security_groups1=group2
	 * ex_security_groups2=group3
	 * }
	 * </pre>
	 * 
	 * @param filepath
	 *            path to the <i> .properties </i> file
	 * @return this <tt>ComputeContextBuilder</tt>
	 */
	public ComputeContextBuilder nodePropertiesFrom(String filepath) {
		this.prop = Utils.loadPropertiesFrom(filepath);
		return this;
	}

	/**
	 * 
	 * @return the appropriate {@link org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext} instance.
	 */
	public ComputeContext build() {
		// more efficient version than PythonInterpreter. see jython-book
		// chapter 10
		// optimized version didnt work
		/*
		 * PySystemState sys = new PySystemState();
		 * sys.path.append(Py.newString(jarPath + newPath + libcloudBasePath +
		 * newPath + libcloudBasePath + slash + "javaimpl" + slash + "jython" +
		 * newPath)); PyObject importer = sys.getBuiltins().__getitem__(
		 * Py.newString("__import__")); PyObject module = importer.__call__(Py
		 * .newString("javaimpl.compute.drivers.EC2ComputeContextImpl"));
		 * PyObject klass = module.__getattr__(Py
		 * .newString("EC2ComputeContextImpl")); return (ComputeContext)
		 * klass.__call__(Py.java2py(this)).__tojava__(
		 * EC2ComputeContext.class);
		 */
		// configure and initialize the python interpreter
		Properties props = new Properties();
		String jarPath = getJarPath();
		String slash = System.getProperty("file.separator");
		String newPath = slash + System.getProperty("path.separator");
		String libcloudBasePath = jarPath + slash + "libcloud-trunk";
		props.setProperty("python.path", jarPath + newPath + libcloudBasePath
				+ newPath + libcloudBasePath + slash + "javaimpl" + slash
				+ "jython" + newPath);

		PythonInterpreter.initialize(System.getProperties(), props,
				new String[] { "" });
		interpreter = new PythonInterpreter();

		if (this.provider.equals(Providers.ABIQUO.name)) {
			return create("drivers.AbiquoComputeContext",
					"AbiquoComputeContextImpl", AbiquoComputeContext.class);

		} else if (this.provider.equals(Providers.BRIGHTBOX.name)) {
			return create("drivers.BrightboxComputeContext",
					"BrightboxComputeContextImpl",
					BrightboxComputeContext.class);

		} else if (this.provider.equals(Providers.CLOUDFRAMES.name)) {
			return create("drivers.CloudframesComputeContext",
					"CloudramesComputeContextImpl",
					CloudFramesComputeContext.class);

		} else if (this.provider.equals(Providers.CLOUDSIGMA.name)) {
			if (this.apiVersion != null && apiVersion.equals("1.0")) {
				return create("drivers.CloudsigmaComputeContext",
						"CloudSigma_1_0_ComputeContextImpl",
						CloudSigma_1_0_ComputeContext.class);
			}
			return create("drivers.CloudsigmaComputeContext",
					"CloudSigma_2_0_ComputeContextImpl",
					CloudSigma_2_0_ComputeContext.class);

		} else if (this.provider.equals(Providers.CLOUDSTACK.name)
				|| this.provider.equals(Providers.EXOSCALE.name)
				|| this.provider.equals(Providers.IKOULA.name)
				|| this.provider.equals(Providers.KTUCLOUD.name)
				|| this.provider.equals(Providers.NINEFOLD.name)) {
			return create("drivers.CloudstackComputeContext",
					"CloudStackComputeContextImpl",
					CloudStackComputeContext.class);

		} else if (this.provider.equals(Providers.DIGITAL_OCEAN.name)) {
			return create("drivers.DigitaloceanComputeContext",
					"DigitalOceanComputeContextImpl",
					DigitalOceanComputeContext.class);

		} else if (this.provider.equals(Providers.DREAMHOST.name)) {
			return create("drivers.DreamhostComputeContext",
					"DreamhostComputeContextImpl",
					DreamhostComputeContext.class);

			// Eucalyptus extends the BaseEC2NodeDriver
		} else if (this.provider.equals(Providers.EC2.name)
				|| this.provider.equals(Providers.EUCALYPTUS.name)
				|| this.provider.equals(Providers.NIMBUS.name)) {
			// BaseEC2ComputeContext shouldn't be used directly
			return create("drivers.Ec2ComputeContextEx",
					"EC2ComputeContextImpl", EC2ComputeContext.class);
			// no direct driver for elasticstack

			// also elasticstack
		} else if (this.provider.equals(Providers.ELASTICHOSTS.name)) {
			return create("drivers.ElasticstackComputeContext",
					"ElasticStackBaseComputeContextImpl",
					ElasticStackBaseComputeContext.class);

		} else if (this.provider.equals(Providers.GANDI.name)) {
			return create("drivers.GandiComputeContext",
					"GandiComputeContextImpl", GandiComputeContext.class);

		} else if (this.provider.equals(Providers.GCE.name)) {
			return create("drivers.GceComputeContext", "GCEComputeContextImpl",
					GCEComputeContext.class);

		} else if (this.provider.equals(Providers.GOGRID.name)) {
			return create("drivers.GogridComputeContext",
					"GoGridComputeContextImpl", GoGridComputeContext.class);

		} else if (this.provider.equals(Providers.HOSTVIRTUAL.name)) {
			return create("drivers.HostvirtualComputeContext",
					"HostVirtualComputeContextImpl",
					HostVirtualComputeContext.class);

		} else if (this.provider.equals(Providers.IBM.name)) {
			return create("drivers.Ibm_sceComputeContext",
					"IBMComputeContextImpl", IBMComputeContext.class);

		} else if (this.provider.equals(Providers.JOYENT.name)) {
			return create("drivers.JoyentComputeContext",
					"JoyentComputeContextImpl", JoyentComputeContext.class);

		} else if (this.provider.equals(Providers.LIBVIRT.name)) {
			return create("drivers.Libvirt_driverComputeContext",
					"LibvirtComputeContextImpl", LibvirtComputeContext.class);

		} else if (this.provider.equals(Providers.LINODE.name)) {
			return create("drivers.LinodeComputeContext",
					"LinodeComputeContextImpl", LinodeComputeContext.class);

		} else if (this.provider.equals(Providers.NEPHOSCALE.name)) {
			return create("drivers.NephoscaleComputeContext",
					"NephoscaleComputeContextImpl",
					NephoscaleComputeContext.class);

		} else if (this.provider.equals(Providers.OPENNEBULA.name)) {
			if (this.apiVersion != null) {
				float apiVers = Float.valueOf(apiVersion);
				if (apiVers < 2.0) {
					return create("drivers.OpennebulaComputeContext",
							"OpenNebulaComputeContextImpl",
							OpenNebulaComputeContext.class);
				} else if (apiVers < 3.0) {
					return create("drivers.OpennebulaComputeContext",
							"OpenNebula_2_0_ComputeContextImpl",
							OpenNebula_2_0_ComputeContext.class);
				}
			}
			return create("drivers.OpennebulaComputeContext",
					"OpenNebula_3_0_ComputeContextImpl",
					OpenNebula_3_0_ComputeContext.class);

		} else if (this.provider.equals(Providers.OPENSTACK.name)
				|| this.provider.equals(Providers.RACKSPACE_FIRST_GEN.name)
				|| this.provider.equals(Providers.RACKSPACE.name)) {
			if ((apiVersion != null && apiVersion.equals("1.0"))
					|| this.provider.equals(Providers.RACKSPACE_FIRST_GEN.name)) {
				return create("drivers.OpenstackComputeContext",
						"OpenStack_1_0_ComputeContextImpl",
						OpenStack_1_0_ComputeContext.class);
			}
			// RACKSPACE extends OPENSTACK_1_1
			return create("drivers.OpenstackComputeContext",
					"OpenStack_1_1_ComputeContextImpl",
					OpenStack_1_1_ComputeContext.class);

		} else if (this.provider.equals(Providers.OPSOURCE.name)) {
			return create("drivers.OpsourceComputeContext",
					"OpsourceComputeContextImpl", OpsourceComputeContext.class);

		} else if (this.provider.equals(Providers.RIMUHOSTING.name)) {
			return create("drivers.RimuhostingComputeContext",
					"RimuHostingComputeContextImpl",
					RimuHostingComputeContext.class);

		} else if (this.provider.equals(Providers.SOFTLAYER.name)) {
			return create("drivers.SoftlayerComputeContext",
					"SoftLayerComputeContextImpl",
					SoftLayerComputeContext.class);

		} else if (this.provider.equals(Providers.VCL.name)) {
			return create("drivers.VclComputeContext", "VCLComputeContextImpl",
					VCLComputeContext.class);

		} else if (this.provider.equals(Providers.VCLOUD.name)
				|| this.provider.equals(Providers.TERREMARK.name)) {
			if (apiVersion != null && apiVersion.equals("1.5")) {
				return create("drivers.VcloudComputeContext",
						"VCloud_1_5_ComputeContextImpl",
						VCloud_1_5_ComputeContext.class);
			}
			return create("drivers.VcloudComputeContext",
					"VCloudComputeContextImpl", VCloudComputeContext.class);

		} else if (this.provider.equals(Providers.VOXEL.name)) {
			return create("drivers.VoxelComputeContext",
					"VoxelComputeContextImpl", VoxelComputeContext.class);

		} else if (this.provider.equals(Providers.VPSNET.name)) {
			return create("drivers.VpsnetComputeContext",
					"VPSNetComputeContextImpl", VPSNetComputeContext.class);

		} else {
			// bluebox, dummy, ecp, gridspot, serverlove, skalicloud
			return create("ComputeContextImpl", "ComputeContextImpl",
					ComputeContext.class);
		}
	}

	/**
	 * perform the actual coercion of the referenced python class into Java
	 * bytecode
	 * 
	 * @param module
	 *            the module name within the javaimpl.compute package. e.g.
	 *            drivers.AbiquoComputeContext
	 * @param importKlass
	 *            the class name from the module e.g. AbiquoComputeContextImpl
	 * @param targetKlass
	 *            the java interface e.g. AbiquoComputeContext.class
	 * @return the implemented targetKlass
	 */
	@SuppressWarnings("unchecked")
	public <T extends ComputeContext> T create(String module,
			String importKlass, final Class<T> targetKlass) {
		String fromImport = "from javaimpl.compute." + module + " import "
				+ importKlass;
		interpreter.exec(fromImport);
		// get the ComputeContext class, call the constructor with this as
		// parameter
		// convert the received class to java
		return (T) interpreter.get(importKlass).__call__(Py.java2py(this))
				.__tojava__(targetKlass);
	}

	/**
	 * 
	 * @return the absolute path (including destination in jar) of this class.
	 */
	public String getJarPath() {
		String jarPath = null;
		try {
			String path = ComputeContextBuilder.class.getProtectionDomain()
					.getCodeSource().getLocation().getPath();
			jarPath = URLDecoder.decode(path, "UTF-8");
			// System.out.println("jarPath: " + jarPath);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return jarPath;
	}
}
