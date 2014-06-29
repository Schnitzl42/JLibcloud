package org.askalon.jlibcloud.compute.driverSpecific.ec2;

import org.askalon.jlibcloud.compute.driverSpecific.ec2.BaseEC2NodeTemplate;
import org.askalon.jlibcloud.compute.driverSpecific.ec2.BaseEC2NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.types.Arg;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;
import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;
import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;
import org.askalon.jlibcloud.compute.core.Providers;

import java.util.List;
import java.util.Map;

	public class BaseEC2NodeTemplateImpl implements BaseEC2NodeTemplate{

	private final String exUserdata;
	private final List<Map<String,Arg>> exBlockdevicemappings;
	private final String[] exSecurityGroups;
	private final String keyPair;
	private final String authSshKey;
	private final NodeLocation location;
	private final String authPassword;
	private final String locationId;
	private final String nodeName;
	private final NodeImage image;
	private final Integer exMincount;
	private final NodeSize size;
	private final String exKeyname;
	private final String imageId;
	private final Integer exMaxcount;
	private final String exIamprofile;
	private final Map<String,Arg> exMetadata;
	private final String exClienttoken;
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
	private final String type = Providers.EC2.name;

	private BaseEC2NodeTemplateImpl(BaseEC2NodeTemplateBuilderImpl builder){
		this.exUserdata = builder.exUserdata;
		this.exBlockdevicemappings = builder.exBlockdevicemappings;
		this.exSecurityGroups = builder.exSecurityGroups;
		this.keyPair = builder.keyPair;
		this.authSshKey = builder.authSshKey;
		this.location = builder.location;
		this.authPassword = builder.authPassword;
		this.locationId = builder.locationId;
		this.nodeName = builder.nodeName;
		this.image = builder.image;
		this.exMincount = builder.exMincount;
		this.size = builder.size;
		this.exKeyname = builder.exKeyname;
		this.imageId = builder.imageId;
		this.exMaxcount = builder.exMaxcount;
		this.exIamprofile = builder.exIamprofile;
		this.exMetadata = builder.exMetadata;
		this.exClienttoken = builder.exClienttoken;
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
		return new BaseEC2NodeTemplateBuilderImpl();
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
	public List<Map<String,Arg>> getExBlockdevicemappings() {
		return exBlockdevicemappings;
	}

	@Override
	public String[] getExSecurityGroups() {
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
	public Integer getExMincount() {
		return exMincount;
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
	public Integer getExMaxcount() {
		return exMaxcount;
	}

	@Override
	public String getExIamprofile() {
		return exIamprofile;
	}

	@Override
	public Map<String,Arg> getExMetadata() {
		return exMetadata;
	}

	@Override
	public String getExClienttoken() {
		return exClienttoken;
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

	private static class BaseEC2NodeTemplateBuilderImpl implements BaseEC2NodeTemplateBuilder {
		private String exUserdata = null;
		private List<Map<String,Arg>> exBlockdevicemappings = null;
		private String[] exSecurityGroups = null;
		private String keyPair = null;
		private String authSshKey = null;
		private NodeLocation location = null;
		private String authPassword = null;
		private String locationId = null;
		private String nodeName = null;
		private NodeImage image = null;
		private Integer exMincount = null;
		private NodeSize size = null;
		private String exKeyname = null;
		private String imageId = null;
		private Integer exMaxcount = null;
		private String exIamprofile = null;
		private Map<String,Arg> exMetadata = null;
		private String exClienttoken = null;
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
		public BaseEC2NodeTemplateBuilderImpl exUserdata(String exUserdata) {
			this.exUserdata = exUserdata;
			return this;
		}

		@Override
		public BaseEC2NodeTemplateBuilderImpl exBlockdevicemappings(List<Map<String,Arg>> exBlockdevicemappings) {
			this.exBlockdevicemappings = exBlockdevicemappings;
			return this;
		}

		@Override
		public BaseEC2NodeTemplateBuilderImpl exSecurityGroups(String... exSecurityGroups) {
			this.exSecurityGroups = exSecurityGroups;
			return this;
		}

		@Override
		public BaseEC2NodeTemplateBuilderImpl keyPair(String keyPair) {
			this.keyPair = keyPair;
			return this;
		}

		@Override
		public BaseEC2NodeTemplateBuilderImpl authSshKey(String authSshKey) {
			this.authSshKey = authSshKey;
			return this;
		}

		@Override
		public BaseEC2NodeTemplateBuilderImpl location(NodeLocation location) {
			this.location = location;
			return this;
		}

		@Override
		public BaseEC2NodeTemplateBuilderImpl authPassword(String authPassword) {
			this.authPassword = authPassword;
			return this;
		}

		@Override
		public BaseEC2NodeTemplateBuilderImpl locationId(String locationId) {
			this.locationId = locationId;
			return this;
		}

		@Override
		public BaseEC2NodeTemplateBuilderImpl nodeName(String nodeName) {
			this.nodeName = nodeName;
			return this;
		}

		@Override
		public BaseEC2NodeTemplateBuilderImpl image(NodeImage image) {
			this.image = image;
			return this;
		}

		@Override
		public BaseEC2NodeTemplateBuilderImpl exMincount(int exMincount) {
			this.exMincount = exMincount;
			return this;
		}

		@Override
		public BaseEC2NodeTemplateBuilderImpl size(NodeSize size) {
			this.size = size;
			return this;
		}

		@Override
		public BaseEC2NodeTemplateBuilderImpl exKeyname(String exKeyname) {
			this.exKeyname = exKeyname;
			return this;
		}

		@Override
		public BaseEC2NodeTemplateBuilderImpl imageId(String imageId) {
			this.imageId = imageId;
			return this;
		}

		@Override
		public BaseEC2NodeTemplateBuilderImpl exMaxcount(int exMaxcount) {
			this.exMaxcount = exMaxcount;
			return this;
		}

		@Override
		public BaseEC2NodeTemplateBuilderImpl exIamprofile(String exIamprofile) {
			this.exIamprofile = exIamprofile;
			return this;
		}

		@Override
		public BaseEC2NodeTemplateBuilderImpl exMetadata(Map<String,Arg> exMetadata) {
			this.exMetadata = exMetadata;
			return this;
		}

		@Override
		public BaseEC2NodeTemplateBuilderImpl exClienttoken(String exClienttoken) {
			this.exClienttoken = exClienttoken;
			return this;
		}

		@Override
		public BaseEC2NodeTemplateBuilderImpl sizeId(String sizeId) {
			this.sizeId = sizeId;
			return this;
		}

		//DEPLOYMENT SETTERS
		//deployment isn't yet supported
		/*
		@Override
		public BaseEC2NodeTemplateBuilderImpl maxTries(int maxTries) {
			this.maxTries = maxTries;
			return this;
		}

		@Override
		public BaseEC2NodeTemplateBuilderImpl sshTimeout(float sshTimeout) {
			this.sshTimeout = sshTimeout;
			return this;
		}

		@Override
		public BaseEC2NodeTemplateBuilderImpl sshUsername(String sshUsername) {
			this.sshUsername = sshUsername;
			return this;
		}

		@Override
		public BaseEC2NodeTemplateBuilderImpl sshKey(String sshKey) {
			this.sshKey = sshKey;
			return this;
		}

		@Override
		public BaseEC2NodeTemplateBuilderImpl sshPort(int sshPort) {
			this.sshPort = sshPort;
			return this;
		}

		@Override
		public BaseEC2NodeTemplateBuilderImpl sshAlternateUsernames(List<String> sshAlternateUsernames) {
			this.sshAlternateUsernames = sshAlternateUsernames;
			return this;
		}

		@Override
		public BaseEC2NodeTemplateBuilderImpl sshInterface(String sshInterface) {
			this.sshInterface = sshInterface;
			return this;
		}

		@Override
		public BaseEC2NodeTemplateBuilderImpl timeout(int timeout) {
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
			return new BaseEC2NodeTemplateImpl(this);
		}
	}
}