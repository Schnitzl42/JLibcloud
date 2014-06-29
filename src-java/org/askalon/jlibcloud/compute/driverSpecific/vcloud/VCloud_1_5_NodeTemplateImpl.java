package org.askalon.jlibcloud.compute.driverSpecific.vcloud;

import org.askalon.jlibcloud.compute.core.Providers;
import org.askalon.jlibcloud.compute.driverSpecific.vcloud.VCloud_1_5_NodeTemplate;
import org.askalon.jlibcloud.compute.driverSpecific.vcloud.VCloud_1_5_NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;

	public class VCloud_1_5_NodeTemplateImpl implements VCloud_1_5_NodeTemplate{

	private final String exVmIpmode;
	private final String keyPair;
	private final String authSshKey;
	private final NodeLocation location;
	private final String authPassword;
	private final String[] exVmNames;
	private final String locationId;
	private final String nodeName;
	private final NodeImage image;
	private final String exVdc;
	private final String exNetwork;
	private final String exVmScript;
	private final String exVmNetwork;
	private final NodeSize size;
	private final Boolean exDeploy;
	private final String imageId;
	private final Integer exCloneTimeout;
	private final Integer exVmCpu;
	private final String exVmFence;
	private final Integer exVmMemory;
	private final String sizeId;
	//deployment attributes
	//deployment isn't yet supported
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
	private final String type = Providers.VCLOUD.name;

	private VCloud_1_5_NodeTemplateImpl(VCloud_1_5_NodeTemplateBuilderImpl builder){
		this.exVmIpmode = builder.exVmIpmode;
		this.keyPair = builder.keyPair;
		this.authSshKey = builder.authSshKey;
		this.location = builder.location;
		this.authPassword = builder.authPassword;
		this.exVmNames = builder.exVmNames;
		this.locationId = builder.locationId;
		this.nodeName = builder.nodeName;
		this.image = builder.image;
		this.exVdc = builder.exVdc;
		this.exNetwork = builder.exNetwork;
		this.exVmScript = builder.exVmScript;
		this.exVmNetwork = builder.exVmNetwork;
		this.size = builder.size;
		this.exDeploy = builder.exDeploy;
		this.imageId = builder.imageId;
		this.exCloneTimeout = builder.exCloneTimeout;
		this.exVmCpu = builder.exVmCpu;
		this.exVmFence = builder.exVmFence;
		this.exVmMemory = builder.exVmMemory;
		this.sizeId = builder.sizeId;
		//set deployment fields
		//deployment isn't yet supported
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
		return new VCloud_1_5_NodeTemplateBuilderImpl();
	}

	@Override
	public String getTemplateType() {
		return type;
	}

	@Override
	public String getExVmIpmode() {
		return exVmIpmode;
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
	public String[] getExVmNames() {
		return exVmNames;
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
	public String getExVdc() {
		return exVdc;
	}

	@Override
	public String getExNetwork() {
		return exNetwork;
	}

	@Override
	public String getExVmScript() {
		return exVmScript;
	}

	@Override
	public String getExVmNetwork() {
		return exVmNetwork;
	}

	@Override
	public NodeSize getSize() {
		return size;
	}

	@Override
	public Boolean getExDeploy() {
		return exDeploy;
	}

	@Override
	public String getImageId() {
		return imageId;
	}

	@Override
	public Integer getExCloneTimeout() {
		return exCloneTimeout;
	}

	@Override
	public Integer getExVmCpu() {
		return exVmCpu;
	}

	@Override
	public String getExVmFence() {
		return exVmFence;
	}

	@Override
	public Integer getExVmMemory() {
		return exVmMemory;
	}

	@Override
	public String getSizeId() {
		return sizeId;
	}

	//DEPLOYMENT GETTERS

	//deployment isn't yet supported
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

	private static class VCloud_1_5_NodeTemplateBuilderImpl implements VCloud_1_5_NodeTemplateBuilder {
		private String exVmIpmode = null;
		private String keyPair = null;
		private String authSshKey = null;
		private NodeLocation location = null;
		private String authPassword = null;
		private String[] exVmNames = null;
		private String locationId = null;
		private String nodeName = null;
		private NodeImage image = null;
		private String exVdc = null;
		private String exNetwork = null;
		private String exVmScript = null;
		private String exVmNetwork = null;
		private NodeSize size = null;
		private Boolean exDeploy = null;
		private String imageId = null;
		private Integer exCloneTimeout = null;
		private Integer exVmCpu = null;
		private String exVmFence = null;
		private Integer exVmMemory = null;
		private String sizeId = null;
		//deployment fields
		//deployment isn't yet supported
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
		public VCloud_1_5_NodeTemplateBuilderImpl exVmIpmode(String exVmIpmode) {
			this.exVmIpmode = exVmIpmode;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl keyPair(String keyPair) {
			this.keyPair = keyPair;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl authSshKey(String authSshKey) {
			this.authSshKey = authSshKey;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl location(NodeLocation location) {
			this.location = location;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl authPassword(String authPassword) {
			this.authPassword = authPassword;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl exVmNames(String... exVmNames) {
			this.exVmNames = exVmNames;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl locationId(String locationId) {
			this.locationId = locationId;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl nodeName(String nodeName) {
			this.nodeName = nodeName;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl image(NodeImage image) {
			this.image = image;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl exVdc(String exVdc) {
			this.exVdc = exVdc;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl exNetwork(String exNetwork) {
			this.exNetwork = exNetwork;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl exVmScript(String exVmScript) {
			this.exVmScript = exVmScript;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl exVmNetwork(String exVmNetwork) {
			this.exVmNetwork = exVmNetwork;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl size(NodeSize size) {
			this.size = size;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl exDeploy(boolean exDeploy) {
			this.exDeploy = exDeploy;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl imageId(String imageId) {
			this.imageId = imageId;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl exCloneTimeout(int exCloneTimeout) {
			this.exCloneTimeout = exCloneTimeout;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl exVmCpu(int exVmCpu) {
			this.exVmCpu = exVmCpu;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl exVmFence(String exVmFence) {
			this.exVmFence = exVmFence;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl exVmMemory(int exVmMemory) {
			this.exVmMemory = exVmMemory;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl sizeId(String sizeId) {
			this.sizeId = sizeId;
			return this;
		}

		//DEPLOYMENT SETTERS
		//deployment isn't yet supported
		/*
		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl maxTries(int maxTries) {
			this.maxTries = maxTries;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl sshTimeout(float sshTimeout) {
			this.sshTimeout = sshTimeout;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl sshUsername(String sshUsername) {
			this.sshUsername = sshUsername;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl sshKey(String sshKey) {
			this.sshKey = sshKey;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl sshPort(int sshPort) {
			this.sshPort = sshPort;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl sshAlternateUsernames(List<String> sshAlternateUsernames) {
			this.sshAlternateUsernames = sshAlternateUsernames;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl sshInterface(String sshInterface) {
			this.sshInterface = sshInterface;
			return this;
		}

		@Override
		public VCloud_1_5_NodeTemplateBuilderImpl timeout(int timeout) {
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
			return new VCloud_1_5_NodeTemplateImpl(this);
		}
	}
}