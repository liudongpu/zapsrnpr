package com.srnpr.zapcom.topcache;

import com.srnpr.zapcom.rootclass.RootCache;

public abstract class TempCache<K, V> extends RootCache<K, V> {

	/**
	 * 获取临时缓存名称
	 * 
	 * @param sName
	 * @return
	 */
	public static String upTempCacheName(String sName) {
		return "tempcache-" + sName;
	}

}
