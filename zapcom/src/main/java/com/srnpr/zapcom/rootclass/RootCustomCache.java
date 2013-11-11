package com.srnpr.zapcom.rootclass;

import java.util.List;

import com.srnpr.zapcom.topdo.TopBase;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;

public class RootCustomCache<K, V> extends TopBase {

	private Cache cache;

	/**
	 * 构造函数
	 */
	public RootCustomCache() {
		CacheDefine cDefine = new CacheDefine();
		String sCacheName = this.getClass().getName();
		CacheConfiguration cacheConfiguration = new CacheConfiguration();

		cacheConfiguration.setName(sCacheName);

		cacheConfiguration.setMaxEntriesLocalHeap(0);
		// 设置最长存活时间
		cacheConfiguration.setTimeToIdleSeconds(6000);

		cacheConfiguration.setMemoryStoreEvictionPolicy("FIFO");

		cache = cDefine.inCustomCache(sCacheName, cacheConfiguration);
	}

	public void inElement(K k, V v) {

		cache.put(new Element(k, v));
	}

	@SuppressWarnings("unchecked")
	public List<K> upKeys() {
		return cache.getKeys();
	}

	public V upValueAndRemove(K k) {

		Object oReturnObject = null;
		if (cache.isKeyInCache(k)) {
			Element eCachElement = cache.get(k);
			if (eCachElement != null) {
				oReturnObject = eCachElement.getObjectValue();
			}

		}

		cache.remove(k);
		return (V) oReturnObject;

	}

}
