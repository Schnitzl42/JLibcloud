package org.askalon.jlibcloud.compute.wrapperInterfaces.base;

import java.util.Map;

public interface KeyPair {

	public String getName();
	
	public String getFingerprint();
	
	/**
	 * 
	 * @return
	 * 		public ssh-key in OpenSSH format
	 */
	public String getPublicKey();
	
	/**
	 * 
	 * @return
	 * 		private ssh-key in PEM format
	 */
	public String getPrivateKey();
	
	public Map<?,?> getExtra();

}
