package org.askalon.jlibcloud.computeCodeGenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public final class Utils {

	/**
	 * 
	 * @param list a list
	 * @return returns the last element of the list
	 */
	public static <T> T last(List<T> list){
		return list.get(list.size()-1);
	}
	
	/**
	 * 
	 * @param attribute some string
	 * @return the string with the character at position 0 capitalized
	 */
	public static String capitalizeFirst(String attribute) {
		return Character.toUpperCase(attribute.charAt(0))
				+ attribute.substring(1);
	}
	
	/**
	 * 
	 * @param s a string
	 * @return the number of upper case letters in s
	 */
	public static int countUpperCaseLetters(String s) {
		int caps = 0;

		for (int i = 0; i < s.length(); i++) {
			if (Character.isUpperCase(s.charAt(i)))
				caps++;
		}
		return caps;
	}
	
	/**
	 * Convert a python signature name into camel case
	 * for java methods
	 * e.g.: ex_list_method -> exListMethod
	 * 
	 * @param pyName
	 * @return the camelCase pyName
	 */
	public static String getJavaSigNameFrom(String pyName) {
		String sigName = pyName;
		while (sigName.contains("_")) {
			int underIndex = sigName.indexOf('_');
			char letter = sigName.charAt(underIndex + 1);
			String regex = "_" + letter;
			char upLetter = Character.toUpperCase(letter);
			sigName = sigName.replaceAll(regex, String.valueOf(upLetter));
		}
		return sigName;
	}
	
	public static boolean isJavaBaseType(String type){
		if(type.matches("int|boolean|String|float")){
			return true;
		}
		return false;
	}
	
	public static Set<String> getBaseClassNames(){
		Set<String> names = new HashSet<String>();
		names.add("NodeImage");
		names.add("Node");
		names.add("NodeLocation");
		names.add("NodeSize");
		names.add("StorageVolume");
		names.add("VolumeSnapshot");
		names.add("KeyPair");
		return names;
	}
	
	public static boolean isInBaseClassNames(String name){
		for(String n : getBaseClassNames()){
			if(n.equals(name)){
				return true;
			}
		}
	return false;
	}
	
	public static String[] splitAtUpperCase(String camelString){
		return camelString.split("(?=[A-Z])");
	}
}
