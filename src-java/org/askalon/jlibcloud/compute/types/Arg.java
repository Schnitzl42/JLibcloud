package org.askalon.jlibcloud.compute.types;

/**
 * Represents an (command line) argument.
 * An argument is a int, float or String value.
 * In Python it's possible to put String and int values
 * into a single map.
 * <p>
 * Example usecase:
 * <p>
 * dict = {'key1':'strVal', 'key2':1}<p>
 * def python_method(dict);
 * <p><p>
 * public python_method_wrapper(Map<String, Arg> dict);
 * 
 * @author root
 *
 */
public interface Arg {

	/**
	 * 
	 * @return the int value or null
	 */
	public Integer getInt();
	
	/**
	 * 
	 * @return the float value or null
	 */
	public Float getFloat();
	
	/**
	 * 
	 * @return the String value or null
	 */
	public String getString();
	
	/**
	 * 
	 * @return true if this arg has an int value
	 */
	public boolean isInt();
	
	/**
	 * 
	 * @return true if this arg has an float value
	 */
	public boolean isFloat();
	
	/**
	 * 
	 * @return true if this arg has an String value
	 */
	public boolean isString();
	
}
