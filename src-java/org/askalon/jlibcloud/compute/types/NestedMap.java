package org.askalon.jlibcloud.compute.types;

import java.util.Map;

/**
 * This class allows to put a map of type <K,V>
 * as V into a map.
 * 
 * @author root
 *
 * @param <K>
 * @param <V>
 */
public interface NestedMap<K, V> extends Map<K, V> {

	/**
	 * 
	 * @param key
	 * @param map
	 * @return the previous value assiciated with this key
	 *  or null if no value was associated
	 */
	public Map<K,V> put(K key, Map<K,V> map);
	
	/**
	 * 
	 * @param key
	 * @return true if the assiciated value is of type Map
	 * 	else False
	 */
	public boolean isNested(K key);
	
	/**
	 * Call this getter when isNested()
	 * returns true and the value is needed
	 * 
	 * @param key
	 * @return returns the map associated with
	 * the key, or null if the key isn't a map
	 * or no key value is assigned to this key
	 */
	public Map<K,V> getMap(Object key);
}
