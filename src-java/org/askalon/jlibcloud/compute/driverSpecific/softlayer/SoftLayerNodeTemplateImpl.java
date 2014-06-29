package org.askalon.jlibcloud.compute.driverSpecific.softlayer;

import org.askalon.jlibcloud.compute.core.Providers;
import org.askalon.jlibcloud.compute.driverSpecific.softlayer.SoftLayerNodeTemplate;
import org.askalon.jlibcloud.compute.driverSpecific.softlayer.SoftLayerNodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;

	public class SoftLayerNodeTemplateImpl implements SoftLayerNodeTemplate{

	private final Integer exCpus;
	private final String keyPair;
	private final String authSshKey;
	private final NodeLocation location;
	private final String authPassword;
	private final Integer exBandwidth;
	private final String locationId;
	private final String nodeName;
	private final NodeImage image;
	private final Integer exDisk;
	private final NodeSize size;
	private final String exOs;
	private final String imageId;
	private final String exDomain;
	private final Boolean exLocalDisk;
	private final String exDatacenter;
	private final Integer exRam;
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
	private final String type = Providers.SOFTLAYER.name;

	private SoftLayerNodeTemplateImpl(SoftLayerNodeTemplateBuilderImpl builder){
		this.exCpus = builder.exCpus;
		this.keyPair = builder.keyPair;
		this.authSshKey = builder.authSshKey;
		this.location = builder.location;
		this.authPassword = builder.authPassword;
		this.exBandwidth = builder.exBandwidth;
		this.locationId = builder.locationId;
		this.nodeName = builder.nodeName;
		this.image = builder.image;
		this.exDisk = builder.exDisk;
		this.size = builder.size;
		this.exOs = builder.exOs;
		this.imageId = builder.imageId;
		this.exDomain = builder.exDomain;
		this.exLocalDisk = builder.exLocalDisk;
		this.exDatacenter = builder.exDatacenter;
		this.exRam = builder.exRam;
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
		return new SoftLayerNodeTemplateBuilderImpl();
	}

	@Override
	public String getTemplateType() {
		return type;
	}

	@Override
	public Integer getExCpus() {
		return exCpus;
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
	public Integer getExBandwidth() {
		return exBandwidth;
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
	public Integer getExDisk() {
		return exDisk;
	}

	@Override
	public NodeSize getSize() {
		return size;
	}

	@Override
	public String getExOs() {
		return exOs;
	}

	@Override
	public String getImageId() {
		return imageId;
	}

	@Override
	public String getExDomain() {
		return exDomain;
	}

	@Override
	public Boolean getExLocalDisk() {
		return exLocalDisk;
	}

	@Override
	public String getExDatacenter() {
		return exDatacenter;
	}

	@Override
	public Integer getExRam() {
		return exRam;
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

	private static class SoftLayerNodeTemplateBuilderImpl implements SoftLayerNodeTemplateBuilder {
		private Integer exCpus = null;
		private String keyPair = null;
		private String authSshKey = null;
		private NodeLocation location = null;
		private String authPassword = null;
		private Integer exBandwidth = null;
		private String locationId = null;
		private String nodeName = null;
		private NodeImage image = null;
		private Integer exDisk = null;
		private NodeSize size = null;
		private String exOs = null;
		private String imageId = null;
		private String exDomain = null;
		private Boolean exLocalDisk = null;
		private String exDatacenter = null;
		private Integer exRam = null;
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
		public SoftLayerNodeTemplateBuilderImpl exCpus(int exCpus) {
			this.exCpus = exCpus;
			return this;
		}

		@Override
		public SoftLayerNodeTemplateBuilderImpl keyPair(String keyPair) {
			this.keyPair = keyPair;
			return this;
		}

		@Override
		public SoftLayerNodeTemplateBuilderImpl authSshKey(String authSshKey) {
			this.authSshKey = authSshKey;
			return this;
		}

		@Override
		public SoftLayerNodeTemplateBuilderImpl location(NodeLocation location) {
			this.location = location;
			return this;
		}

		@Override
		public SoftLayerNodeTemplateBuilderImpl authPassword(String authPassword) {
			this.authPassword = authPassword;
			return this;
		}

		@Override
		public SoftLayerNodeTemplateBuilderImpl exBandwidth(int exBandwidth) {
			this.exBandwidth = exBandwidth;
			return this;
		}

		@Override
		public SoftLayerNodeTemplateBuilderImpl locationId(String locationId) {
			this.locationId = locationId;
			return this;
		}

		@Override
		public SoftLayerNodeTemplateBuilderImpl nodeName(String nodeName) {
			this.nodeName = nodeName;
			return this;
		}

		@Override
		public SoftLayerNodeTemplateBuilderImpl image(NodeImage image) {
			this.image = image;
			return this;
		}

		@Override
		public SoftLayerNodeTemplateBuilderImpl exDisk(int exDisk) {
			this.exDisk = exDisk;
			return this;
		}

		@Override
		public SoftLayerNodeTemplateBuilderImpl size(NodeSize size) {
			this.size = size;
			return this;
		}

		@Override
		public SoftLayerNodeTemplateBuilderImpl exOs(String exOs) {
			this.exOs = exOs;
			return this;
		}

		@Override
		public SoftLayerNodeTemplateBuilderImpl imageId(String imageId) {
			this.imageId = imageId;
			return this;
		}

		@Override
		public SoftLayerNodeTemplateBuilderImpl exDomain(String exDomain) {
			this.exDomain = exDomain;
			return this;
		}

		@Override
		public SoftLayerNodeTemplateBuilderImpl exLocalDisk(boolean exLocalDisk) {
			this.exLocalDisk = exLocalDisk;
			return this;
		}

		@Override
		public SoftLayerNodeTemplateBuilderImpl exDatacenter(String exDatacenter) {
			this.exDatacenter = exDatacenter;
			return this;
		}

		@Override
		public SoftLayerNodeTemplateBuilderImpl exRam(int exRam) {
			this.exRam = exRam;
			return this;
		}

		@Override
		public SoftLayerNodeTemplateBuilderImpl sizeId(String sizeId) {
			this.sizeId = sizeId;
			return this;
		}

		//DEPLOYMENT SETTERS
		//deployment isn't yet supported
		/*
		@Override
		public SoftLayerNodeTemplateBuilderImpl maxTries(int maxTries) {
			this.maxTries = maxTries;
			return this;
		}

		@Override
		public SoftLayerNodeTemplateBuilderImpl sshTimeout(float sshTimeout) {
			this.sshTimeout = sshTimeout;
			return this;
		}

		@Override
		public SoftLayerNodeTemplateBuilderImpl sshUsername(String sshUsername) {
			this.sshUsername = sshUsername;
			return this;
		}

		@Override
		public SoftLayerNodeTemplateBuilderImpl sshKey(String sshKey) {
			this.sshKey = sshKey;
			return this;
		}

		@Override
		public SoftLayerNodeTemplateBuilderImpl sshPort(int sshPort) {
			this.sshPort = sshPort;
			return this;
		}

		@Override
		public SoftLayerNodeTemplateBuilderImpl sshAlternateUsernames(List<String> sshAlternateUsernames) {
			this.sshAlternateUsernames = sshAlternateUsernames;
			return this;
		}

		@Override
		public SoftLayerNodeTemplateBuilderImpl sshInterface(String sshInterface) {
			this.sshInterface = sshInterface;
			return this;
		}

		@Override
		public SoftLayerNodeTemplateBuilderImpl timeout(int timeout) {
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
			return new SoftLayerNodeTemplateImpl(this);
		}
	}
}