package org.askalon.jlibcloud.compute.driverSpecific.abiquo;

import org.askalon.jlibcloud.compute.core.Providers;
import org.askalon.jlibcloud.compute.driverSpecific.abiquo.AbiquoNodeTemplate;
import org.askalon.jlibcloud.compute.driverSpecific.abiquo.AbiquoNodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;

	public class AbiquoNodeTemplateImpl implements AbiquoNodeTemplate{

	private final String keyPair;
	private final String groupName;
	private final String authSshKey;
	private final String imageId;
	private final NodeLocation location;
	private final String authPassword;
	private final String name;
	private final String locationId;
	private final String nodeName;
	private final NodeImage image;
	private final String sizeId;
	private final NodeSize size;
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
	private final String type = Providers.ABIQUO.name;

	
	private AbiquoNodeTemplateImpl(AbiquoNodeTemplateBuilderImpl builder){
		this.keyPair = builder.keyPair;
		this.groupName = builder.groupName;
		this.authSshKey = builder.authSshKey;
		this.imageId = builder.imageId;
		this.location = builder.location;
		this.authPassword = builder.authPassword;
		this.name = builder.name;
		this.locationId = builder.locationId;
		this.nodeName = builder.nodeName;
		this.image = builder.image;
		this.sizeId = builder.sizeId;
		this.size = builder.size;
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
		return new AbiquoNodeTemplateBuilderImpl();
	}

	@Override
	public String getTemplateType() {
		return type;
	}

	@Override
	public String getKeyPair() {
		return keyPair;
	}

	@Override
	public String getGroupName() {
		return groupName;
	}

	@Override
	public String getAuthSshKey() {
		return authSshKey;
	}

	@Override
	public String getImageId() {
		return imageId;
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
	public String getName() {
		return name;
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
	public String getSizeId() {
		return sizeId;
	}

	@Override
	public NodeSize getSize() {
		return size;
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

	private static class AbiquoNodeTemplateBuilderImpl implements AbiquoNodeTemplateBuilder {
		private String keyPair = null;
		private String groupName = null;
		private String authSshKey = null;
		private String imageId = null;
		private NodeLocation location = null;
		private String authPassword = null;
		private String name = null;
		private String locationId = null;
		private String nodeName = null;
		private NodeImage image = null;
		private String sizeId = null;
		private NodeSize size = null;
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
		public AbiquoNodeTemplateBuilderImpl keyPair(String keyPair) {
			this.keyPair = keyPair;
			return this;
		}

		@Override
		public AbiquoNodeTemplateBuilderImpl groupName(String groupName) {
			this.groupName = groupName;
			return this;
		}

		@Override
		public AbiquoNodeTemplateBuilderImpl authSshKey(String authSshKey) {
			this.authSshKey = authSshKey;
			return this;
		}

		@Override
		public AbiquoNodeTemplateBuilderImpl imageId(String imageId) {
			this.imageId = imageId;
			return this;
		}

		@Override
		public AbiquoNodeTemplateBuilderImpl location(NodeLocation location) {
			this.location = location;
			return this;
		}

		@Override
		public AbiquoNodeTemplateBuilderImpl authPassword(String authPassword) {
			this.authPassword = authPassword;
			return this;
		}

		@Override
		public AbiquoNodeTemplateBuilderImpl name(String name) {
			this.name = name;
			return this;
		}

		@Override
		public AbiquoNodeTemplateBuilderImpl locationId(String locationId) {
			this.locationId = locationId;
			return this;
		}

		@Override
		public AbiquoNodeTemplateBuilderImpl nodeName(String nodeName) {
			this.nodeName = nodeName;
			return this;
		}

		@Override
		public AbiquoNodeTemplateBuilderImpl image(NodeImage image) {
			this.image = image;
			return this;
		}

		@Override
		public AbiquoNodeTemplateBuilderImpl sizeId(String sizeId) {
			this.sizeId = sizeId;
			return this;
		}

		@Override
		public AbiquoNodeTemplateBuilderImpl size(NodeSize size) {
			this.size = size;
			return this;
		}

		//DEPLOYMENT SETTERS
		//deployment isn't yet supported
		/*
		@Override
		public AbiquoNodeTemplateBuilderImpl maxTries(int maxTries) {
			this.maxTries = maxTries;
			return this;
		}

		@Override
		public AbiquoNodeTemplateBuilderImpl sshTimeout(float sshTimeout) {
			this.sshTimeout = sshTimeout;
			return this;
		}

		@Override
		public AbiquoNodeTemplateBuilderImpl sshUsername(String sshUsername) {
			this.sshUsername = sshUsername;
			return this;
		}

		@Override
		public AbiquoNodeTemplateBuilderImpl sshKey(String sshKey) {
			this.sshKey = sshKey;
			return this;
		}

		@Override
		public AbiquoNodeTemplateBuilderImpl sshPort(int sshPort) {
			this.sshPort = sshPort;
			return this;
		}

		@Override
		public AbiquoNodeTemplateBuilderImpl sshAlternateUsernames(List<String> sshAlternateUsernames) {
			this.sshAlternateUsernames = sshAlternateUsernames;
			return this;
		}

		@Override
		public AbiquoNodeTemplateBuilderImpl sshInterface(String sshInterface) {
			this.sshInterface = sshInterface;
			return this;
		}

		@Override
		public AbiquoNodeTemplateBuilderImpl timeout(int timeout) {
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
			return new AbiquoNodeTemplateImpl(this);
		}
	}
}