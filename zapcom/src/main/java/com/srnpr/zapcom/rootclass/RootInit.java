package com.srnpr.zapcom.rootclass;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseCache;
import com.srnpr.zapcom.baseface.IBaseInit;

public abstract class RootInit extends BaseClass implements IBaseInit {

	public RootInit() {
		bLogInfo(0,"init class : "+ this.getClass().getName());
	}

	/**
	 * 初始化缓存
	 * @param baseCaches
	 */
	public void topInitCache(IBaseCache... baseCaches) {
		for (IBaseCache iCache : baseCaches) {
			iCache.refresh();
		}
	}

}
