package com.srnpr.zapcom.rootclass;

import java.net.URL;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.Configuration;

import com.srnpr.zapcom.topdo.TopBase;

public class CacheDefine extends TopBase {

	
	private static CacheManager cManager;

	public CacheDefine() {
		if (cManager == null) {
			URL url=this.getClass().getResource("/META-INF/ehcache/ehcache.xml");
			cManager=CacheManager.create(url);

		}
	}

	public Cache inCache(String sCacheName) {
		

		if (cManager.cacheExists(sCacheName)) {
			return upCache(sCacheName);
		} else {
			
			Cache memoryOnlyCache = new Cache(sCacheName, 0, false, true, 0, 0);
			cManager.addCache(memoryOnlyCache);
			
			return memoryOnlyCache;
		}
	}

	public Cache upCache(String sCacheName) {
		return cManager.getCache(sCacheName);
	}
	
	
}
