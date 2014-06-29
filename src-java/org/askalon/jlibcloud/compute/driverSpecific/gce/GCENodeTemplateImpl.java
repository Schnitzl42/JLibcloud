package org.askalon.jlibcloud.compute.driverSpecific.gce;

import org.askalon.jlibcloud.compute.core.Providers;
import org.askalon.jlibcloud.compute.driverSpecific.gce.GCENodeTemplate;
import org.askalon.jlibcloud.compute.driverSpecific.gce.GCENodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import java.util.List;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.StorageVolume;
import java.util.Map;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;

	public class GCENodeTemplateImpl implements GCENodeTemplate{

	private final String keyPair;
	private final String authSshKey;
	private final StorageVolume exBootDisk;
	private final NodeLocation location;
	private final Boolean useExistingDisk;
	private final String authPassword;
	private final String locationId;
	private final String nodeName;
	private final NodeImage image;
	private final String exNetwork;
	private final NodeSize size;
	private final String imageId;
	private final List<String> exTags;
	private final String name;
	private final GCEAddress externalIp;
	private final Map<String,Arg> exMetadata;
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
	private final String type = Providers.GCE.name;

	private GCENodeTemplateImpl(GCENodeTemplateBuilderImpl builder){
		this.keyPair = builder.keyPair;
		this.authSshKey = builder.authSshKey;
		this.exBootDisk = builder.exBootDisk;
		this.location = builder.location;
		this.useExistingDisk = builder.useExistingDisk;
		this.authPassword = builder.authPassword;
		this.locationId = builder.locationId;
		this.nodeName = builder.nodeName;
		this.image = builder.image;
		this.exNetwork = builder.exNetwork;
		this.size = builder.size;
		this.imageId = builder.imageId;
		this.exTags = builder.exTags;
		this.name = builder.name;
		this.externalIp = builder.externalIp;
		this.exMetadata = builder.exMetadata;
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
		return new GCENodeTemplateBuilderImpl();
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
	public String getAuthSshKey() {
		return authSshKey;
	}

	@Override
	public StorageVolume getExBootDisk() {
		return exBootDisk;
	}

	@Override
	public NodeLocation getLocation() {
		return location;
	}

	@Override
	public Boolean getUseExistingDisk() {
		return useExistingDisk;
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
	public String getExNetwork() {
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
	public List<String> getExTags() {
		return exTags;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public GCEAddress getExternalIp() {
		return externalIp;
	}

	@Override
	public Map<String,Arg> getExMetadata() {
		return exMetadata;
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

	private static class GCENodeTemplateBuilderImpl implements GCENodeTemplateBuilder {
		private String keyPair = null;
		private String authSshKey = null;
		private StorageVolume exBootDisk = null;
		private NodeLocation location = null;
		private Boolean useExistingDisk = null;
		private String authPassword = null;
		private String locationId = null;
		private String nodeName = null;
		private NodeImage image = null;
		private String exNetwork = null;
		private NodeSize size = null;
		private String imageId = null;
		private List<String> exTags = null;
		private String name = null;
		private GCEAddress externalIp = null;
		private Map<String,Arg> exMetadata = null;
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
		public GCENodeTemplateBuilderImpl keyPair(String keyPair) {
			this.keyPair = keyPair;
			return this;
		}

		@Override
		public GCENodeTemplateBuilderImpl authSshKey(String authSshKey) {
			this.authSshKey = authSshKey;
			return this;
		}

		@Override
		public GCENodeTemplateBuilderImpl exBootDisk(StorageVolume exBootDisk) {
			this.exBootDisk = exBootDisk;
			return this;
		}

		@Override
		public GCENodeTemplateBuilderImpl location(NodeLocation location) {
			this.location = location;
			return this;
		}

		@Override
		public GCENodeTemplateBuilderImpl useExistingDisk(boolean useExistingDisk) {
			this.useExistingDisk = useExistingDisk;
			return this;
		}

		@Override
		public GCENodeTemplateBuilderImpl authPassword(String authPassword) {
			this.authPassword = authPassword;
			return this;
		}

		@Override
		public GCENodeTemplateBuilderImpl locationId(String locationId) {
			this.locationId = locationId;
			return this;
		}

		@Override
		public GCENodeTemplateBuilderImpl nodeName(String nodeName) {
			this.nodeName = nodeName;
			return this;
		}

		@Override
		public GCENodeTemplateBuilderImpl image(NodeImage image) {
			this.image = image;
			return this;
		}

		@Override
		public GCENodeTemplateBuilderImpl exNetwork(String exNetwork) {
			this.exNetwork = exNetwork;
			return this;
		}

		@Override
		public GCENodeTemplateBuilderImpl size(NodeSize size) {
			this.size = size;
			return this;
		}

		@Override
		public GCENodeTemplateBuilderImpl imageId(String imageId) {
			this.imageId = imageId;
			return this;
		}

		@Override
		public GCENodeTemplateBuilderImpl exTags(List<String> exTags) {
			this.exTags = exTags;
			return this;
		}

		@Override
		public GCENodeTemplateBuilderImpl name(String name) {
			this.name = name;
			return this;
		}

		@Override
		public GCENodeTemplateBuilderImpl externalIp(GCEAddress externalIp) {
			this.externalIp = externalIp;
			return this;
		}

		@Override
		public GCENodeTemplateBuilderImpl exMetadata(Map<String,Arg> exMetadata) {
			this.exMetadata = exMetadata;
			return this;
		}

		@Override
		public GCENodeTemplateBuilderImpl sizeId(String sizeId) {
			this.sizeId = sizeId;
			return this;
		}

		//DEPLOYMENT SETTERS
		//deployment isn't yet supported
		/*
		@Override
		public GCENodeTemplateBuilderImpl maxTries(int maxTries) {
			this.maxTries = maxTries;
			return this;
		}

		@Override
		public GCENodeTemplateBuilderImpl sshTimeout(float sshTimeout) {
			this.sshTimeout = sshTimeout;
			return this;
		}

		@Override
		public GCENodeTemplateBuilderImpl sshUsername(String sshUsername) {
			this.sshUsername = sshUsername;
			return this;
		}

		@Override
		public GCENodeTemplateBuilderImpl sshKey(String sshKey) {
			this.sshKey = sshKey;
			return this;
		}

		@Override
		public GCENodeTemplateBuilderImpl sshPort(int sshPort) {
			this.sshPort = sshPort;
			return this;
		}

		@Override
		public GCENodeTemplateBuilderImpl sshAlternateUsernames(List<String> sshAlternateUsernames) {
			this.sshAlternateUsernames = sshAlternateUsernames;
			return this;
		}

		@Override
		public GCENodeTemplateBuilderImpl sshInterface(String sshInterface) {
			this.sshInterface = sshInterface;
			return this;
		}

		@Override
		public GCENodeTemplateBuilderImpl timeout(int timeout) {
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
			return new GCENodeTemplateImpl(this);
		}
	}
}