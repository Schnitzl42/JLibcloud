package org.askalon.jlibcloud.compute.wrapperInterfacesImpl;


import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;


public class NodeTemplateImpl implements NodeTemplate {

	private final String name;
	private final String sizeId;
	private final NodeSize size;
	private final String imageId;
	private final NodeImage image;
	private final String locationId;
	private final NodeLocation location;
	private final String authPassword;
	private final String authSshKey;
	private final String keypair;
	//deployment
	//deployment isn't yet supported
	/*
	private final String sshUsername;
	private final List<String> sshAlternateUsernames;
	private final Integer sshPort;
	private final Float sshTimeout;
	private final String sshKey;
	private final Integer timeout;
	private final Integer maxTries;
	private final String sshInterface;
	private final List<Deployment> deployments;
	*/
	private final String type = "default";

	private NodeTemplateImpl(NodeTemplateBuilderImpl builder) {
		this.name = builder.name;
		this.sizeId = builder.sizeId;
		this.size = builder.size;
		this.imageId = builder.imageId;
		this.image = builder.image;
		this.locationId = builder.locationId;
		this.location = builder.location;
		this.authPassword = builder.authPassword;
		this.authSshKey = builder.authSshKey;
		this.keypair = builder.keypair;
		//deployments
		//deployment isn't yet supported
		/*
		this.sshUsername = builder.sshUsername;
		this.sshAlternateUsernames = builder.sshAlternateUsernames;
		this.sshPort = builder.sshPort;
		this.sshTimeout = builder.sshTimeout;
		this.sshKey = builder.sshKey;
		this.timeout = builder.timeout;
		this.maxTries = builder.maxTries;
		this.sshInterface = builder.sshInterface;
		this.deployments = builder.deployments;
		*/
	}

	public static NodeTemplateBuilder newBuilder() {
		return new NodeTemplateBuilderImpl();
	}

	@Override
	public String getTemplateType() {
		return this.type;
	}

	@Override
	public String getNodeName() {
		return this.name;
	}

	@Override
	public String getSizeId() {
		return this.sizeId;
	}

	@Override
	public NodeSize getSize(){
		return this.size;
	}
	
	@Override
	public String getImageId() {
		return this.imageId;
	}
	
	@Override
	public NodeImage getImage() {
		return this.image;
	}
	
	@Override
	public String getLocationId(){
		return this.locationId;
	}
	
	@Override
	public NodeLocation getLocation(){
		return this.location;
	}
	
	@Override
	public String getAuthPassword(){
		return this.authPassword;
	}
	
	@Override
	public String getAuthSshKey(){
		return this.authSshKey;
	}
	
	@Override
	public String getKeyPair() {
		return this.keypair;
	}
	
	//DEPLOYMENT METHODS
	//deployment isn't yet supported
	/*
	@Override
	public String getSshUsername() {
		return this.sshUsername;
	}

	@Override
	public List<String> getSshAlternateUsernames() {
		return this.sshAlternateUsernames;
	}

	@Override
	public Integer getSshPort() {
		return this.sshPort;
	}

	@Override
	public Float getSshTimeout() {
		return this.sshTimeout;
	}

	@Override
	public String getSshKey() {
		return this.sshKey;
	}

	@Override
	public Integer getTimeout() {
		return this.timeout;
	}

	@Override
	public Integer getMaxTries() {
		return this.maxTries;
	}

	@Override
	public String getSshInterface() {
		return this.sshInterface;
	}

	@Override
	public List<Deployment> getDeploy() {
		return this.deployments;
	}
	*/
	
	private static class NodeTemplateBuilderImpl implements NodeTemplateBuilder {

		private String name = null;
		private String sizeId = null;
		private NodeSize size = null;
		private String imageId = null;
		private NodeImage image = null;
		private String locationId = null;
		private NodeLocation location = null;
		private String authPassword = null;
		private String authSshKey = null;
		private String keypair = null;
		//deploy
		//deployment isn't yet supported
		/*
		private String sshUsername = null;
		private List<String> sshAlternateUsernames = null;
		private Integer sshPort = null;
		private Float sshTimeout = null;
		private String sshKey = null;
		private Integer timeout = null;
		private Integer maxTries = null;
		private String sshInterface = null;
		private List<Deployment> deployments = null;
		*/
		
		@Override
		public NodeTemplateBuilder nodeName(String name) {
			this.name = name;
			return this;
		}

		@Override
		public NodeTemplateBuilder sizeId(String sizeId) {
			this.sizeId = sizeId;
			return this;
		}
		
		@Override
		public NodeTemplateBuilder size(NodeSize size) {
			this.size = size;
			return this;
		}

		@Override
		public NodeTemplateBuilder imageId(String imageId) {
			this.imageId = imageId;
			return this;
		}
		
		@Override
		public NodeTemplateBuilder image(NodeImage image) {
			this.image = image;
			return this;
		}
		
		@Override
		public NodeTemplateBuilder locationId(String locationId) {
			this.locationId = locationId;
			return this;
		}

		@Override
		public NodeTemplateBuilder location(NodeLocation location) {
			this.location = location;
			return this;
		}
		
		@Override
		public NodeTemplateBuilder authPassword(String password) {
			this.authPassword = password;
			return this;
		}
		
		@Override
		public NodeTemplateBuilder authSshKey(String sshKey) {
			this.authSshKey = sshKey;
			return this;
		}
		
		@Override
		public NodeTemplateBuilder keyPair(String keyPair) {
			this.keypair = keyPair;
			return this;
		}
		
		//DEPLOYMENT 
		//deployment isn't yet supported
		/*
		@Override
		public NodeTemplateBuilder sshUsername(String sshUsername) {
			this.sshUsername = sshUsername;
			return this;
		}

		@Override
		public NodeTemplateBuilder sshAlternateUsernames(
				List<String> sshAlternateUsernames) {
			this.sshAlternateUsernames = sshAlternateUsernames;
			return this;
		}

		@Override
		public NodeTemplateBuilder sshPort(int sshPort) {
			this.sshPort = sshPort;
			return this;
		}

		@Override
		public NodeTemplateBuilder sshTimeout(float sshTimeout) {
			this.sshTimeout = sshTimeout;
			return this;
		}

		@Override
		public NodeTemplateBuilder sshKey(String sshKey) {
			this.sshKey = sshKey;
			return this;
		}

		@Override
		public NodeTemplateBuilder timeout(int timeout) {
			this.timeout = timeout;
			return this;
		}

		@Override
		public NodeTemplateBuilder maxTries(int maxTries) {
			this.maxTries = maxTries;
			return this;
		}

		@Override
		public NodeTemplateBuilder sshInterface(String sshInterface) {
			this.sshInterface = sshInterface;
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
			return new NodeTemplateImpl(this);
		}
	}	
}
