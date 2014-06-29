package org.askalon.jlibcloud.compute.driverSpecific.linode;

import org.askalon.jlibcloud.compute.core.Providers;
import org.askalon.jlibcloud.compute.driverSpecific.linode.LinodeNodeTemplate;
import org.askalon.jlibcloud.compute.driverSpecific.linode.LinodeNodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;

	public class LinodeNodeTemplateImpl implements LinodeNodeTemplate{

	private final String keyPair;
	private final Integer exSwap;
	private final String authSshKey;
	private final String lconfig;
	private final NodeLocation location;
	private final String authPassword;
	private final String locationId;
	private final String nodeName;
	private final NodeImage image;
	private final Boolean exPrivate;
	private final String lswap;
	private final NodeSize size;
	private final String imageId;
	private final Integer exRsize;
	private final String name;
	private final Integer exPayment;
	private final String exComment;
	private final String exKernel;
	private final String lroot;
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
	private final String type = Providers.LINODE.name;

	private LinodeNodeTemplateImpl(LinodeNodeTemplateBuilderImpl builder){
		this.keyPair = builder.keyPair;
		this.exSwap = builder.exSwap;
		this.authSshKey = builder.authSshKey;
		this.lconfig = builder.lconfig;
		this.location = builder.location;
		this.authPassword = builder.authPassword;
		this.locationId = builder.locationId;
		this.nodeName = builder.nodeName;
		this.image = builder.image;
		this.exPrivate = builder.exPrivate;
		this.lswap = builder.lswap;
		this.size = builder.size;
		this.imageId = builder.imageId;
		this.exRsize = builder.exRsize;
		this.name = builder.name;
		this.exPayment = builder.exPayment;
		this.exComment = builder.exComment;
		this.exKernel = builder.exKernel;
		this.lroot = builder.lroot;
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
		return new LinodeNodeTemplateBuilderImpl();
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
	public Integer getExSwap() {
		return exSwap;
	}

	@Override
	public String getAuthSshKey() {
		return authSshKey;
	}

	@Override
	public String getLconfig() {
		return lconfig;
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
	public Boolean getExPrivate() {
		return exPrivate;
	}

	@Override
	public String getLswap() {
		return lswap;
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
	public Integer getExRsize() {
		return exRsize;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Integer getExPayment() {
		return exPayment;
	}

	@Override
	public String getExComment() {
		return exComment;
	}

	@Override
	public String getExKernel() {
		return exKernel;
	}

	@Override
	public String getLroot() {
		return lroot;
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

	private static class LinodeNodeTemplateBuilderImpl implements LinodeNodeTemplateBuilder {
		private String keyPair = null;
		private Integer exSwap = null;
		private String authSshKey = null;
		private String lconfig = null;
		private NodeLocation location = null;
		private String authPassword = null;
		private String locationId = null;
		private String nodeName = null;
		private NodeImage image = null;
		private Boolean exPrivate = null;
		private String lswap = null;
		private NodeSize size = null;
		private String imageId = null;
		private Integer exRsize = null;
		private String name = null;
		private Integer exPayment = null;
		private String exComment = null;
		private String exKernel = null;
		private String lroot = null;
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
		public LinodeNodeTemplateBuilderImpl keyPair(String keyPair) {
			this.keyPair = keyPair;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl exSwap(int exSwap) {
			this.exSwap = exSwap;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl authSshKey(String authSshKey) {
			this.authSshKey = authSshKey;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl lconfig(String lconfig) {
			this.lconfig = lconfig;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl location(NodeLocation location) {
			this.location = location;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl authPassword(String authPassword) {
			this.authPassword = authPassword;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl locationId(String locationId) {
			this.locationId = locationId;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl nodeName(String nodeName) {
			this.nodeName = nodeName;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl image(NodeImage image) {
			this.image = image;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl exPrivate(boolean exPrivate) {
			this.exPrivate = exPrivate;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl lswap(String lswap) {
			this.lswap = lswap;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl size(NodeSize size) {
			this.size = size;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl imageId(String imageId) {
			this.imageId = imageId;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl exRsize(int exRsize) {
			this.exRsize = exRsize;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl name(String name) {
			this.name = name;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl exPayment(int exPayment) {
			this.exPayment = exPayment;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl exComment(String exComment) {
			this.exComment = exComment;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl exKernel(String exKernel) {
			this.exKernel = exKernel;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl lroot(String lroot) {
			this.lroot = lroot;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl sizeId(String sizeId) {
			this.sizeId = sizeId;
			return this;
		}

		//DEPLOYMENT SETTERS
		//deployment isn't yet supported
		/*
		@Override
		public LinodeNodeTemplateBuilderImpl maxTries(int maxTries) {
			this.maxTries = maxTries;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl sshTimeout(float sshTimeout) {
			this.sshTimeout = sshTimeout;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl sshUsername(String sshUsername) {
			this.sshUsername = sshUsername;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl sshKey(String sshKey) {
			this.sshKey = sshKey;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl sshPort(int sshPort) {
			this.sshPort = sshPort;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl sshAlternateUsernames(List<String> sshAlternateUsernames) {
			this.sshAlternateUsernames = sshAlternateUsernames;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl sshInterface(String sshInterface) {
			this.sshInterface = sshInterface;
			return this;
		}

		@Override
		public LinodeNodeTemplateBuilderImpl timeout(int timeout) {
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
			return new LinodeNodeTemplateImpl(this);
		}
	}
}