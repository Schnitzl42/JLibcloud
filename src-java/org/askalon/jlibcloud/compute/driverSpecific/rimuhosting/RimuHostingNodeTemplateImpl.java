package org.askalon.jlibcloud.compute.driverSpecific.rimuhosting;

import org.askalon.jlibcloud.compute.core.Providers;
import org.askalon.jlibcloud.compute.driverSpecific.rimuhosting.RimuHostingNodeTemplate;
import org.askalon.jlibcloud.compute.driverSpecific.rimuhosting.RimuHostingNodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;

	public class RimuHostingNodeTemplateImpl implements RimuHostingNodeTemplate{

	private final String keyPair;
	private final String authSshKey;
	private final Integer exDiskSpace2Mb;
	private final NodeLocation location;
	private final String authPassword;
	private final Integer exNumIps;
	private final String locationId;
	private final String nodeName;
	private final NodeImage image;
	private final String exBillingOid;
	private final String exControlPanel;
	private final NodeSize size;
	private final Integer exMemoryMb;
	private final String imageId;
	private final String name;
	private final Integer exDiskSpaceMb;
	private final String exExtraIpReason;
	private final String exVpsOrderOidToClone;
	private final String exHostServerOid;
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
	private final String type = Providers.RIMUHOSTING.name;

	private RimuHostingNodeTemplateImpl(RimuHostingNodeTemplateBuilderImpl builder){
		this.keyPair = builder.keyPair;
		this.authSshKey = builder.authSshKey;
		this.exDiskSpace2Mb = builder.exDiskSpace2Mb;
		this.location = builder.location;
		this.authPassword = builder.authPassword;
		this.exNumIps = builder.exNumIps;
		this.locationId = builder.locationId;
		this.nodeName = builder.nodeName;
		this.image = builder.image;
		this.exBillingOid = builder.exBillingOid;
		this.exControlPanel = builder.exControlPanel;
		this.size = builder.size;
		this.exMemoryMb = builder.exMemoryMb;
		this.imageId = builder.imageId;
		this.name = builder.name;
		this.exDiskSpaceMb = builder.exDiskSpaceMb;
		this.exExtraIpReason = builder.exExtraIpReason;
		this.exVpsOrderOidToClone = builder.exVpsOrderOidToClone;
		this.exHostServerOid = builder.exHostServerOid;
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
		return new RimuHostingNodeTemplateBuilderImpl();
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
	public Integer getExDiskSpace2Mb() {
		return exDiskSpace2Mb;
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
	public Integer getExNumIps() {
		return exNumIps;
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
	public String getExBillingOid() {
		return exBillingOid;
	}

	@Override
	public String getExControlPanel() {
		return exControlPanel;
	}

	@Override
	public NodeSize getSize() {
		return size;
	}

	@Override
	public Integer getExMemoryMb() {
		return exMemoryMb;
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
	public Integer getExDiskSpaceMb() {
		return exDiskSpaceMb;
	}

	@Override
	public String getExExtraIpReason() {
		return exExtraIpReason;
	}

	@Override
	public String getExVpsOrderOidToClone() {
		return exVpsOrderOidToClone;
	}

	@Override
	public String getExHostServerOid() {
		return exHostServerOid;
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

	private static class RimuHostingNodeTemplateBuilderImpl implements RimuHostingNodeTemplateBuilder {
		private String keyPair = null;
		private String authSshKey = null;
		private Integer exDiskSpace2Mb = null;
		private NodeLocation location = null;
		private String authPassword = null;
		private Integer exNumIps = null;
		private String locationId = null;
		private String nodeName = null;
		private NodeImage image = null;
		private String exBillingOid = null;
		private String exControlPanel = null;
		private NodeSize size = null;
		private Integer exMemoryMb = null;
		private String imageId = null;
		private String name = null;
		private Integer exDiskSpaceMb = null;
		private String exExtraIpReason = null;
		private String exVpsOrderOidToClone = null;
		private String exHostServerOid = null;
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
		public RimuHostingNodeTemplateBuilderImpl keyPair(String keyPair) {
			this.keyPair = keyPair;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl authSshKey(String authSshKey) {
			this.authSshKey = authSshKey;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl exDiskSpace2Mb(int exDiskSpace2Mb) {
			this.exDiskSpace2Mb = exDiskSpace2Mb;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl location(NodeLocation location) {
			this.location = location;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl authPassword(String authPassword) {
			this.authPassword = authPassword;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl exNumIps(int exNumIps) {
			this.exNumIps = exNumIps;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl locationId(String locationId) {
			this.locationId = locationId;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl nodeName(String nodeName) {
			this.nodeName = nodeName;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl image(NodeImage image) {
			this.image = image;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl exBillingOid(String exBillingOid) {
			this.exBillingOid = exBillingOid;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl exControlPanel(String exControlPanel) {
			this.exControlPanel = exControlPanel;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl size(NodeSize size) {
			this.size = size;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl exMemoryMb(int exMemoryMb) {
			this.exMemoryMb = exMemoryMb;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl imageId(String imageId) {
			this.imageId = imageId;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl name(String name) {
			this.name = name;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl exDiskSpaceMb(int exDiskSpaceMb) {
			this.exDiskSpaceMb = exDiskSpaceMb;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl exExtraIpReason(String exExtraIpReason) {
			this.exExtraIpReason = exExtraIpReason;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl exVpsOrderOidToClone(String exVpsOrderOidToClone) {
			this.exVpsOrderOidToClone = exVpsOrderOidToClone;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl exHostServerOid(String exHostServerOid) {
			this.exHostServerOid = exHostServerOid;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl sizeId(String sizeId) {
			this.sizeId = sizeId;
			return this;
		}

		//DEPLOYMENT SETTERS
		//deployment isn't yet supported
		/*
		@Override
		public RimuHostingNodeTemplateBuilderImpl maxTries(int maxTries) {
			this.maxTries = maxTries;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl sshTimeout(float sshTimeout) {
			this.sshTimeout = sshTimeout;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl sshUsername(String sshUsername) {
			this.sshUsername = sshUsername;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl sshKey(String sshKey) {
			this.sshKey = sshKey;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl sshPort(int sshPort) {
			this.sshPort = sshPort;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl sshAlternateUsernames(List<String> sshAlternateUsernames) {
			this.sshAlternateUsernames = sshAlternateUsernames;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl sshInterface(String sshInterface) {
			this.sshInterface = sshInterface;
			return this;
		}

		@Override
		public RimuHostingNodeTemplateBuilderImpl timeout(int timeout) {
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
			return new RimuHostingNodeTemplateImpl(this);
		}
	}
}