package org.askalon.jlibcloud.compute.driverSpecific.opsource;

import org.askalon.jlibcloud.compute.core.Providers;
import org.askalon.jlibcloud.compute.driverSpecific.opsource.OpsourceNodeTemplate;
import org.askalon.jlibcloud.compute.driverSpecific.opsource.OpsourceNodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;

	public class OpsourceNodeTemplateImpl implements OpsourceNodeTemplate{

	private final String exDescription;
	private final String keyPair;
	private final String authSshKey;
	private final NodeLocation location;
	private final String authPassword;
	private final String nodeName;
	private final String locationId;
	private final NodeImage image;
	private final OpsourceNetwork exNetwork;
	private final NodeSize size;
	private final String imageId;
	private final String name;
	private final Boolean exIsStarted;
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
	private final String type = Providers.OPSOURCE.name;

	private OpsourceNodeTemplateImpl(OpsourceNodeTemplateBuilderImpl builder){
		this.exDescription = builder.exDescription;
		this.keyPair = builder.keyPair;
		this.authSshKey = builder.authSshKey;
		this.location = builder.location;
		this.authPassword = builder.authPassword;
		this.nodeName = builder.nodeName;
		this.locationId = builder.locationId;
		this.image = builder.image;
		this.exNetwork = builder.exNetwork;
		this.size = builder.size;
		this.imageId = builder.imageId;
		this.name = builder.name;
		this.exIsStarted = builder.exIsStarted;
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
		return new OpsourceNodeTemplateBuilderImpl();
	}

	@Override
	public String getTemplateType() {
		return type;
	}

	@Override
	public String getExDescription() {
		return exDescription;
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
	public String getNodeName() {
		return nodeName;
	}

	@Override
	public String getLocationId() {
		return locationId;
	}

	@Override
	public NodeImage getImage() {
		return image;
	}

	@Override
	public OpsourceNetwork getExNetwork() {
		return exNetwork;
	}

	@Override
	public NodeSize getSize() {
		return size;
	}

	@Override
	public String getImageId() {
		return imageId;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Boolean getExIsStarted() {
		return exIsStarted;
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

	private static class OpsourceNodeTemplateBuilderImpl implements OpsourceNodeTemplateBuilder {
		private String exDescription = null;
		private String keyPair = null;
		private String authSshKey = null;
		private NodeLocation location = null;
		private String authPassword = null;
		private String nodeName = null;
		private String locationId = null;
		private NodeImage image = null;
		private OpsourceNetwork exNetwork = null;
		private NodeSize size = null;
		private String imageId = null;
		private String name = null;
		private Boolean exIsStarted = null;
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
		public OpsourceNodeTemplateBuilderImpl exDescription(String exDescription) {
			this.exDescription = exDescription;
			return this;
		}

		@Override
		public OpsourceNodeTemplateBuilderImpl keyPair(String keyPair) {
			this.keyPair = keyPair;
			return this;
		}

		@Override
		public OpsourceNodeTemplateBuilderImpl authSshKey(String authSshKey) {
			this.authSshKey = authSshKey;
			return this;
		}

		@Override
		public OpsourceNodeTemplateBuilderImpl location(NodeLocation location) {
			this.location = location;
			return this;
		}

		@Override
		public OpsourceNodeTemplateBuilderImpl authPassword(String authPassword) {
			this.authPassword = authPassword;
			return this;
		}

		@Override
		public OpsourceNodeTemplateBuilderImpl nodeName(String nodeName) {
			this.nodeName = nodeName;
			return this;
		}

		@Override
		public OpsourceNodeTemplateBuilderImpl locationId(String locationId) {
			this.locationId = locationId;
			return this;
		}

		@Override
		public OpsourceNodeTemplateBuilderImpl image(NodeImage image) {
			this.image = image;
			return this;
		}

		@Override
		public OpsourceNodeTemplateBuilderImpl exNetwork(OpsourceNetwork exNetwork) {
			this.exNetwork = exNetwork;
			return this;
		}

		@Override
		public OpsourceNodeTemplateBuilderImpl size(NodeSize size) {
			this.size = size;
			return this;
		}

		@Override
		public OpsourceNodeTemplateBuilderImpl imageId(String imageId) {
			this.imageId = imageId;
			return this;
		}

		@Override
		public OpsourceNodeTemplateBuilderImpl name(String name) {
			this.name = name;
			return this;
		}

		@Override
		public OpsourceNodeTemplateBuilderImpl exIsStarted(boolean exIsStarted) {
			this.exIsStarted = exIsStarted;
			return this;
		}

		@Override
		public OpsourceNodeTemplateBuilderImpl sizeId(String sizeId) {
			this.sizeId = sizeId;
			return this;
		}

		//DEPLOYMENT SETTERS
		//deployment isn't yet supported
		/*
		@Override
		public OpsourceNodeTemplateBuilderImpl maxTries(int maxTries) {
			this.maxTries = maxTries;
			return this;
		}

		@Override
		public OpsourceNodeTemplateBuilderImpl sshTimeout(float sshTimeout) {
			this.sshTimeout = sshTimeout;
			return this;
		}

		@Override
		public OpsourceNodeTemplateBuilderImpl sshUsername(String sshUsername) {
			this.sshUsername = sshUsername;
			return this;
		}

		@Override
		public OpsourceNodeTemplateBuilderImpl sshKey(String sshKey) {
			this.sshKey = sshKey;
			return this;
		}

		@Override
		public OpsourceNodeTemplateBuilderImpl sshPort(int sshPort) {
			this.sshPort = sshPort;
			return this;
		}

		@Override
		public OpsourceNodeTemplateBuilderImpl sshAlternateUsernames(List<String> sshAlternateUsernames) {
			this.sshAlternateUsernames = sshAlternateUsernames;
			return this;
		}

		@Override
		public OpsourceNodeTemplateBuilderImpl sshInterface(String sshInterface) {
			this.sshInterface = sshInterface;
			return this;
		}

		@Override
		public OpsourceNodeTemplateBuilderImpl timeout(int timeout) {
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
			return new OpsourceNodeTemplateImpl(this);
		}
	}
}