package org.askalon.jlibcloud.compute.driverSpecific.openstack;

import org.askalon.jlibcloud.compute.driverSpecific.openstack.OpenStack_1_1_NodeTemplate;
import org.askalon.jlibcloud.compute.driverSpecific.openstack.OpenStack_1_1_NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.core.Providers;

import java.util.List;
import java.util.Map;

	public class OpenStack_1_1_NodeTemplateImpl implements OpenStack_1_1_NodeTemplate{

	private final String exUserdata;
	private final OpenStackNetwork networks;
	private final List<OpenStackSecurityGroup> exSecurityGroups;
	private final String keyPair;
	private final String authSshKey;
	private final NodeLocation location;
	private final String authPassword;
	private final String locationId;
	private final String nodeName;
	private final NodeImage image;
	private final Map<String,Arg> exFiles;
	private final NodeSize size;
	private final String exKeyname;
	private final String imageId;
	private final String exDiskConfig;
	private final Map<String,Arg> exMetadata;
	private final String sizeId;
	private final String exAdminPass;
	//deployment attributes
	//ignore imports for deployment
	/*
	private final Integer maxTries;
	private final Float sshTimeout;
	private final String sshUsername;
	private final String sshKey;
	private final Integer sshPort;
	private final List<String> sshAlternateUsernames;
	private final String sshInterface;
	private final Integer timeout;
	private final List<Deployment> deployments;
	*/
	private final String type = Providers.OPENSTACK.name;

	private OpenStack_1_1_NodeTemplateImpl(OpenStack_1_1_NodeTemplateBuilderImpl builder){
		this.exUserdata = builder.exUserdata;
		this.networks = builder.networks;
		this.exSecurityGroups = builder.exSecurityGroups;
		this.keyPair = builder.keyPair;
		this.authSshKey = builder.authSshKey;
		this.location = builder.location;
		this.authPassword = builder.authPassword;
		this.locationId = builder.locationId;
		this.nodeName = builder.nodeName;
		this.image = builder.image;
		this.exFiles = builder.exFiles;
		this.size = builder.size;
		this.exKeyname = builder.exKeyname;
		this.imageId = builder.imageId;
		this.exDiskConfig = builder.exDiskConfig;
		this.exMetadata = builder.exMetadata;
		this.sizeId = builder.sizeId;
		this.exAdminPass = builder.exAdminPass;
		//set deployment fields
		//ignore imports for deployment
		/*
		this.maxTries = builder.maxTries;
		this.sshTimeout = builder.sshTimeout;
		this.sshUsername = builder.sshUsername;
		this.sshKey = builder.sshKey;
		this.sshPort = builder.sshPort;
		this.sshAlternateUsernames = builder.sshAlternateUsernames;
		this.sshInterface = builder.sshInterface;
		this.timeout = builder.timeout;
		this.deployments = builder.deployments;
		*/
	}

	public static NodeTemplateBuilder newBuilder() {
		return new OpenStack_1_1_NodeTemplateBuilderImpl();
	}
	
	@Override
	public String getTemplateType() {
		return type;
	}

	@Override
	public String getExUserdata() {
		return exUserdata;
	}

	@Override
	public OpenStackNetwork getNetworks() {
		return networks;
	}

	@Override
	public List<OpenStackSecurityGroup> getExSecurityGroups() {
		return exSecurityGroups;
	}

	@Override
	public String getKeyPair() {
		return keyPair;
	}

	@Override
	public String getAuthSshKey() {
		return authSshKey;
	}

	@Override
	public NodeLocation getLocation() {
		return location;
	}

	@Override
	public String getAuthPassword() {
		return authPassword;
	}

	@Override
	public String getLocationId() {
		return locationId;
	}

	@Override
	public String getNodeName() {
		return nodeName;
	}

	@Override
	public NodeImage getImage() {
		return image;
	}

	@Override
	public Map<String,Arg> getExFiles() {
		return exFiles;
	}

	@Override
	public NodeSize getSize() {
		return size;
	}

	@Override
	public String getExKeyname() {
		return exKeyname;
	}

	@Override
	public String getImageId() {
		return imageId;
	}

	@Override
	public String getExDiskConfig() {
		return exDiskConfig;
	}

	@Override
	public Map<String,Arg> getExMetadata() {
		return exMetadata;
	}

	@Override
	public String getSizeId() {
		return sizeId;
	}
	
	@Override
	public String getExAdminPass() {
		return exAdminPass;
	}

	//DEPLOYMENT GETTERS
	//ignore imports for deployment
	/*
	@Override
	public Integer getMaxTries() {
		return maxTries;
	}

	@Override
	public Float getSshTimeout() {
		return sshTimeout;
	}

	@Override
	public String getSshUsername() {
		return sshUsername;
	}

	@Override
	public String getSshKey() {
		return sshKey;
	}

	@Override
	public Integer getSshPort() {
		return sshPort;
	}

	@Override
	public List<String> getSshAlternateUsernames() {
		return sshAlternateUsernames;
	}

	@Override
	public String getSshInterface() {
		return sshInterface;
	}

	@Override
	public Integer getTimeout() {
		return timeout;
	}

	@Override
	public List<Deployment> getDeploy() {
		return this.deployments;
	}
	*/
	
	private static class OpenStack_1_1_NodeTemplateBuilderImpl implements OpenStack_1_1_NodeTemplateBuilder {
		private String exUserdata = null;
		private OpenStackNetwork networks = null;
		private List<OpenStackSecurityGroup> exSecurityGroups = null;
		private String keyPair = null;
		private String authSshKey = null;
		private NodeLocation location = null;
		private String authPassword = null;
		private String locationId = null;
		private String nodeName = null;
		private NodeImage image = null;
		private Map<String,Arg> exFiles = null;
		private NodeSize size = null;
		private String exKeyname = null;
		private String imageId = null;
		private String exDiskConfig = null;
		private Map<String,Arg> exMetadata = null;
		private String sizeId = null;
		private String exAdminPass = null;
		//deployment fields
		//ignore imports for deployment
		/*
		private Integer maxTries = null;
		private Float sshTimeout = null;
		private String sshUsername = null;
		private String sshKey = null;
		private Integer sshPort = null;
		private List<String> sshAlternateUsernames = null;
		private String sshInterface = null;
		private Integer timeout = null;
		private List<Deployment> deployments = null;
		*/

		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl exUserdata(String exUserdata) {
			this.exUserdata = exUserdata;
			return this;
		}

		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl networks(OpenStackNetwork networks) {
			this.networks = networks;
			return this;
		}

		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl exSecurityGroups(List<OpenStackSecurityGroup> exSecurityGroups) {
			this.exSecurityGroups = exSecurityGroups;
			return this;
		}

		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl keyPair(String keyPair) {
			this.keyPair = keyPair;
			return this;
		}

		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl authSshKey(String authSshKey) {
			this.authSshKey = authSshKey;
			return this;
		}

		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl location(NodeLocation location) {
			this.location = location;
			return this;
		}

		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl authPassword(String authPassword) {
			this.authPassword = authPassword;
			return this;
		}

		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl locationId(String locationId) {
			this.locationId = locationId;
			return this;
		}

		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl nodeName(String nodeName) {
			this.nodeName = nodeName;
			return this;
		}

		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl image(NodeImage image) {
			this.image = image;
			return this;
		}

		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl exFiles(Map<String,Arg> exFiles) {
			this.exFiles = exFiles;
			return this;
		}

		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl size(NodeSize size) {
			this.size = size;
			return this;
		}

		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl exKeyname(String exKeyname) {
			this.exKeyname = exKeyname;
			return this;
		}

		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl imageId(String imageId) {
			this.imageId = imageId;
			return this;
		}

		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl exDiskConfig(String exDiskConfig) {
			this.exDiskConfig = exDiskConfig;
			return this;
		}

		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl exMetadata(Map<String,Arg> exMetadata) {
			this.exMetadata = exMetadata;
			return this;
		}

		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl sizeId(String sizeId) {
			this.sizeId = sizeId;
			return this;
		}
		
		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl exAdminPass(String exAdminPass) {
			this.exAdminPass = exAdminPass;
			return this;
		}

		//DEPLOYMENT SETTERS
		//ignore imports for deployment
		/*

		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl maxTries(int maxTries) {
			this.maxTries = maxTries;
			return this;
		}

		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl sshTimeout(float sshTimeout) {
			this.sshTimeout = sshTimeout;
			return this;
		}

		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl sshUsername(String sshUsername) {
			this.sshUsername = sshUsername;
			return this;
		}

		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl sshKey(String sshKey) {
			this.sshKey = sshKey;
			return this;
		}

		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl sshPort(int sshPort) {
			this.sshPort = sshPort;
			return this;
		}

		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl sshAlternateUsernames(List<String> sshAlternateUsernames) {
			this.sshAlternateUsernames = sshAlternateUsernames;
			return this;
		}

		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl sshInterface(String sshInterface) {
			this.sshInterface = sshInterface;
			return this;
		}

		@Override
		public OpenStack_1_1_NodeTemplateBuilderImpl timeout(int timeout) {
			this.timeout = timeout;
			return this;
		}

		@Override
		public NodeTemplateBuilder deploy(Deployment deploy) {
			if(this.deployments == null){
				this.deployments = new LinkedList<Deployment>();
			}
			this.deployments.add(deploy);
			return this;
		}
		*/
		
		@Override
		public <T extends NodeTemplateBuilder> T as(Class<T> klass) {
			return klass.cast(this);
		}

		@Override
		public NodeTemplate build() {
			return new OpenStack_1_1_NodeTemplateImpl(this);
		}
	}
}