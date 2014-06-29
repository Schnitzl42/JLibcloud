package org.askalon.jlibcloud.compute.types;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NestedMapImpl<K,V> implements NestedMap<K,V> {

	//this map is used for the standard case
	private Map<K,V> stdMap;
	private Map<K,Map<K,V>> nMap;
	
	public NestedMapImpl() {
		this.stdMap = new HashMap<K,V>();
		this.nMap = new HashMap<K,Map<K,V>>();
	}

	@Override
	public int size() {
		return this.stdMap.size() + this.nMap.size();
	}

	@Override
	public boolean isEmpty() {
		return this.stdMap.isEmpty() && this.nMap.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return this.stdMap.containsKey(key) || this.nMap.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return this.stdMap.containsValue(value);
	}

	@Override
	public V get(Object key) {
		return this.stdMap.get(key);
	}

	@Override
	public V put(K key, V value) {
		return this.stdMap.put(key, value);
	}

	@Override
	public V remove(Object key) {
		this.nMap.remove(key);
		return this.stdMap.remove(key);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		this.stdMap.putAll(m);
	}

	@Override
	public void clear() {
		this.stdMap.clear();
		this.nMap.clear();
	}

	@Override
	public Set<K> keySet() {
		Set<K> fullKS = new HashSet<K>();
		fullKS.addAll(this.stdMap.keySet());
		fullKS.addAll(this.nMap.keySet());
		return fullKS;
	}

	/**
	 * 
	 * @return only the values from the non nested map items
	 */
	@Override
	public Collection<V> values() {
		return this.stdMap.values();
	}

	/**
	 * 
	 * @return only the entry set for the non nested map items
	 */
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return this.stdMap.entrySet();
	}

	@Override
	public Map<K, V> put(K key, Map<K,V> map) {
		return this.nMap.put(key, map);
	}

	@Override
	public boolean isNested(K key) {
		return this.nMap.containsKey(key);
	}

	@Override
	public Map<K, V> getMap(Object key) {
		return this.nMap.get(key);
	}

}
