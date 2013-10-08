package com.srnpr.zapweb.webdo;

import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapcom.rootclass.RootCache;

public class ObjectCache extends RootCache<String, Object> implements
		IBaseInstance {

	private static ObjectCache objectCache = null;

	public static ObjectCache getInstance() {
		if (objectCache == null) {
			objectCache = new ObjectCache();
		}
		return objectCache;
	}

	public void refresh() {

	}

	@Override
	public Object upOne(String k) {

		return null;
	}

}
