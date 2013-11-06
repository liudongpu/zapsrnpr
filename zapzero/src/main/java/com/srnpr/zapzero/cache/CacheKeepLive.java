package com.srnpr.zapzero.cache;

import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapcom.rootclass.RootCache;
import com.srnpr.zapzero.api.ApiKeepLiveInput;

/**
 * 心跳缓存信息
 * 
 * @author srnpr
 * 
 */
public class CacheKeepLive extends RootCache<String, ApiKeepLiveInput>
		implements IBaseInstance {

	private static CacheKeepLive cacheKeepLive = null;

	public static CacheKeepLive getInstance() {
		if (cacheKeepLive == null) {
			cacheKeepLive = new CacheKeepLive();
		}
		return cacheKeepLive;

	}

	public void refresh() {

	}

	@Override
	public ApiKeepLiveInput upOne(String k) {

		return null;
	}

}
