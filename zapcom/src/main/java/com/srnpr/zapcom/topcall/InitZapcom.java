package com.srnpr.zapcom.topcall;

import com.srnpr.zapcom.baseface.IBaseDestory;
import com.srnpr.zapcom.rootclass.CacheDefine;
import com.srnpr.zapcom.rootclass.RootInit;

public class InitZapcom extends RootInit {

	public boolean onInit() {

		return true;
	}

	@Override
	public boolean onDestory() {

		// 清除所有缓存
		new CacheDefine().removeAllCache();

		return true;
	}

}
