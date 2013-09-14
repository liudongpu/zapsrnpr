package com.srnpr.zapcom.topapi;

import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapcom.basemodel.MApiAuthorize;
import com.srnpr.zapcom.rootclass.RootCache;

public class DefaultAuthorizeCache extends RootCache<String, MApiAuthorize>
		implements IBaseInstance {

	private static DefaultAuthorizeCache defaultAuthorizeCache = null;

	public static DefaultAuthorizeCache getInstance() {
		if (defaultAuthorizeCache == null) {
			defaultAuthorizeCache = new DefaultAuthorizeCache();
		}
		return defaultAuthorizeCache;
	}

	public synchronized void refresh() {

	}

}
