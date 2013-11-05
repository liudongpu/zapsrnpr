package com.srnpr.zapcom.topcache;

import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapcom.basemodel.MStringMap;
import com.srnpr.zapcom.rootclass.RootCache;

/**
 * 配置缓存类  该类缓存配置信息
 * @author srnpr
 *
 */
public class CacheTempConfigStringMap extends TempCache<String, MStringMap>
		implements IBaseInstance {

	private static CacheTempConfigStringMap objectCache = null;

	public static CacheTempConfigStringMap getInstance() {
		if (objectCache == null) {
			objectCache = new CacheTempConfigStringMap();
		}
		return objectCache;
	}

	public void refresh() {

	}

	@Override
	public MStringMap upOne(String k) {
		// TODO Auto-generated method stub
		return null;
	}

}
