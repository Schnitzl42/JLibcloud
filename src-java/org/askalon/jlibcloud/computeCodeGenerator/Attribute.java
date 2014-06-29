package org.askalon.jlibcloud.computeCodeGenerator;

public class Attribute {

	//int cpus=0: type name=value
	public String name = null;
	public String value = null;
	public String type = null;
	private boolean isKwarg = false;
	public String docParam = "";
	
	/**
	 * this constructor gets called while parsing
	 * the py doc. if the attribute wasn't in
	 * the signature this constructor is used.
	 * Therefore this attribute has to be
	 * in the kwargs map.
	 * 
	 * @param attName
	 * @param attType
	 */
	public Attribute(String attName, String attType) {
		this.name = attName;
		this.type = attType;
	}
	
	public Attribute(String attName, String attType, boolean isKwarg) {
		this.name = attName;
		this.type = attType;
		this.isKwarg = isKwarg;
	}
	
	/**
	 * Call this constructor when parsing the python signature.
	 * @param attName
	 * 	single attribute name (e.g. node)
	 * or attribute with default value (e.g. port=80)
	 */
	public Attribute(String attName) {
		if(attName.contains("=")){
			String[] args = attName.split("=");
			this.name = args[0];
			this.value = args[1];
		}else{
			this.name = attName;
		}
	}
	
	public boolean isKwarg(){
		return this.isKwarg;
	}
}
