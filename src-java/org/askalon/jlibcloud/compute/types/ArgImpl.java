package org.askalon.jlibcloud.compute.types;


public class ArgImpl implements Arg{

	private Integer intVal = null;
	private Float floatVal = null;
	private String stringVal = null;
	
	public ArgImpl(int arg) {
		this.intVal = arg;
	}
	
	public ArgImpl(float arg) {
		this.floatVal = arg;
	}
	
	public ArgImpl(String arg) {
		this.stringVal = arg;
	}
	
	@Override
	public Integer getInt() {
		return this.intVal;
	}

	@Override
	public Float getFloat() {
		return this.floatVal;
	}

	@Override
	public String getString() {
		return this.stringVal;
	}
	
	@Override
	public boolean isInt(){
		return (this.intVal!=null) ? true : false;
	}
	
	@Override
	public boolean isFloat(){
		return (this.floatVal!=null) ? true : false;
	}
	
	@Override
	public boolean isString(){
		return (this.stringVal!=null) ? true : false;
	}
}
