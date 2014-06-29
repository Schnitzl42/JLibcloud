package org.askalon.jlibcloud.compute.types;

/**
 * The NodeState represents the Sate of a node.
 * The enum is mapped to the types from libcloud.compute.types.NodeState
 */
public enum NodeState {

	
	RUNNING(0), REBOOTING(1), TERMINATED(2), PENDING(3), UNKNOWN(4), STOPPED(5);
	
	private int val;
	
	private NodeState(int value){
		this.val = value;
	}
	
	/**
	 * This returns the integer value of the enum,
	 * it should return the same value as getOrdinal()
	 * @return the integer value of this enum
	 */
	int getValue(){
		return this.val;
	}
}
