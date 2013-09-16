package com.srnpr.zapcom.rootclass;

import java.net.URL;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.Configuration;

import com.srnpr.zapcom.topdo.TopBase;

/**
 * 缓存定义类
 * 
 * @author srnpr
 * 
 */
public class CacheDefine extends TopBase {

	private static CacheManager cManager;

	/**
	 * 缓存定义
	 */
	public CacheDefine() {
		if (cManager == null) {
			URL url = this.getClass().getResource(
					"/META-INF/ehcache/ehcache.xml");
			cManager = CacheManager.create(url);

		}
	}

	/**
	 * 添加缓存
	 * 
	 * @param sCacheName
	 * @return
	 */
	public Cache inCache(String sCacheName) {

		if (cManager.cacheExists(sCacheName)) {
			return upCache(sCacheName);
		} else {

			Cache memoryOnlyCache = new Cache(sCacheName, 5000, false, true, 0,
					0);
			cManager.addCache(memoryOnlyCache);

			return memoryOnlyCache;
		}
	}

	/**
	 * 获取缓存
	 * 
	 * @param sCacheName
	 * @return
	 */
	public Cache upCache(String sCacheName) {
		return cManager.getCache(sCacheName);
	}

}
