package org.askalon.jlibcloud.compute.driverSpecific.voxel;

import org.askalon.jlibcloud.compute.core.Providers;
import org.askalon.jlibcloud.compute.driverSpecific.voxel.VoxelNodeTemplate;
import org.askalon.jlibcloud.compute.driverSpecific.voxel.VoxelNodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;

	public class VoxelNodeTemplateImpl implements VoxelNodeTemplate{

	private final String exRootpass;
	private final String exSshuser;
	private final String keyPair;
	private final Boolean exVoxelAccess;
	private final String authSshKey;
	private final String exPublicip;
	private final NodeLocation location;
	private final String authPassword;
	private final String exPrivateip;
	private final String locationId;
	private final String nodeName;
	private final NodeImage image;
	private final NodeSize size;
	private final String imageId;
	private final String exConsolepass;
	private final String name;
	private final String exSshpass;
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
	private final String type = Providers.VOXEL.name;

	private VoxelNodeTemplateImpl(VoxelNodeTemplateBuilderImpl builder){
		this.exRootpass = builder.exRootpass;
		this.exSshuser = builder.exSshuser;
		this.keyPair = builder.keyPair;
		this.exVoxelAccess = builder.exVoxelAccess;
		this.authSshKey = builder.authSshKey;
		this.exPublicip = builder.exPublicip;
		this.location = builder.location;
		this.authPassword = builder.authPassword;
		this.exPrivateip = builder.exPrivateip;
		this.locationId = builder.locationId;
		this.nodeName = builder.nodeName;
		this.image = builder.image;
		this.size = builder.size;
		this.imageId = builder.imageId;
		this.exConsolepass = builder.exConsolepass;
		this.name = builder.name;
		this.exSshpass = builder.exSshpass;
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
		return new VoxelNodeTemplateBuilderImpl();
	}

	@Override
	public String getTemplateType() {
		return type;
	}

	@Override
	public String getExRootpass() {
		return exRootpass;
	}

	@Override
	public String getExSshuser() {
		return exSshuser;
	}

	@Override
	public String getKeyPair() {
		return keyPair;
	}

	@Override
	public Boolean getExVoxelAccess() {
		return exVoxelAccess;
	}

	@Override
	public String getAuthSshKey() {
		return authSshKey;
	}

	@Override
	public String getExPublicip() {
		return exPublicip;
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
	public String getExPrivateip() {
		return exPrivateip;
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
	public NodeSize getSize() {
		return size;
	}

	@Override
	public String getImageId() {
		return imageId;
	}

	@Override
	public String getExConsolepass() {
		return exConsolepass;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getExSshpass() {
		return exSshpass;
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

	private static class VoxelNodeTemplateBuilderImpl implements VoxelNodeTemplateBuilder {
		private String exRootpass = null;
		private String exSshuser = null;
		private String keyPair = null;
		private Boolean exVoxelAccess = null;
		private String authSshKey = null;
		private String exPublicip = null;
		private NodeLocation location = null;
		private String authPassword = null;
		private String exPrivateip = null;
		private String locationId = null;
		private String nodeName = null;
		private NodeImage image = null;
		private NodeSize size = null;
		private String imageId = null;
		private String exConsolepass = null;
		private String name = null;
		private String exSshpass = null;
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
		public VoxelNodeTemplateBuilderImpl exRootpass(String exRootpass) {
			this.exRootpass = exRootpass;
			return this;
		}

		@Override
		public VoxelNodeTemplateBuilderImpl exSshuser(String exSshuser) {
			this.exSshuser = exSshuser;
			return this;
		}

		@Override
		public VoxelNodeTemplateBuilderImpl keyPair(String keyPair) {
			this.keyPair = keyPair;
			return this;
		}

		@Override
		public VoxelNodeTemplateBuilderImpl exVoxelAccess(boolean exVoxelAccess) {
			this.exVoxelAccess = exVoxelAccess;
			return this;
		}

		@Override
		public VoxelNodeTemplateBuilderImpl authSshKey(String authSshKey) {
			this.authSshKey = authSshKey;
			return this;
		}

		@Override
		public VoxelNodeTemplateBuilderImpl exPublicip(String exPublicip) {
			this.exPublicip = exPublicip;
			return this;
		}

		@Override
		public VoxelNodeTemplateBuilderImpl location(NodeLocation location) {
			this.location = location;
			return this;
		}

		@Override
		public VoxelNodeTemplateBuilderImpl authPassword(String authPassword) {
			this.authPassword = authPassword;
			return this;
		}

		@Override
		public VoxelNodeTemplateBuilderImpl exPrivateip(String exPrivateip) {
			this.exPrivateip = exPrivateip;
			return this;
		}

		@Override
		public VoxelNodeTemplateBuilderImpl locationId(String locationId) {
			this.locationId = locationId;
			return this;
		}

		@Override
		public VoxelNodeTemplateBuilderImpl nodeName(String nodeName) {
			this.nodeName = nodeName;
			return this;
		}

		@Override
		public VoxelNodeTemplateBuilderImpl image(NodeImage image) {
			this.image = image;
			return this;
		}

		@Override
		public VoxelNodeTemplateBuilderImpl size(NodeSize size) {
			this.size = size;
			return this;
		}

		@Override
		public VoxelNodeTemplateBuilderImpl imageId(String imageId) {
			this.imageId = imageId;
			return this;
		}

		@Override
		public VoxelNodeTemplateBuilderImpl exConsolepass(String exConsolepass) {
			this.exConsolepass = exConsolepass;
			return this;
		}

		@Override
		public VoxelNodeTemplateBuilderImpl name(String name) {
			this.name = name;
			return this;
		}

		@Override
		public VoxelNodeTemplateBuilderImpl exSshpass(String exSshpass) {
			this.exSshpass = exSshpass;
			return this;
		}

		@Override
		public VoxelNodeTemplateBuilderImpl sizeId(String sizeId) {
			this.sizeId = sizeId;
			return this;
		}

		//DEPLOYMENT SETTERS
		//deployment isn't yet supported
		/*
		@Override
		public VoxelNodeTemplateBuilderImpl maxTries(int maxTries) {
			this.maxTries = maxTries;
			return this;
		}

		@Override
		public VoxelNodeTemplateBuilderImpl sshTimeout(float sshTimeout) {
			this.sshTimeout = sshTimeout;
			return this;
		}

		@Override
		public VoxelNodeTemplateBuilderImpl sshUsername(String sshUsername) {
			this.sshUsername = sshUsername;
			return this;
		}

		@Override
		public VoxelNodeTemplateBuilderImpl sshKey(String sshKey) {
			this.sshKey = sshKey;
			return this;
		}

		@Override
		public VoxelNodeTemplateBuilderImpl sshPort(int sshPort) {
			this.sshPort = sshPort;
			return this;
		}

		@Override
		public VoxelNodeTemplateBuilderImpl sshAlternateUsernames(List<String> sshAlternateUsernames) {
			this.sshAlternateUsernames = sshAlternateUsernames;
			return this;
		}

		@Override
		public VoxelNodeTemplateBuilderImpl sshInterface(String sshInterface) {
			this.sshInterface = sshInterface;
			return this;
		}

		@Override
		public VoxelNodeTemplateBuilderImpl timeout(int timeout) {
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
			return new VoxelNodeTemplateImpl(this);
		}
	}
}