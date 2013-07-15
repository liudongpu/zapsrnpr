package com.srnpr.zapweb.topcall;

import com.srnpr.zapcom.baseface.IBaseInit;
import com.srnpr.zapcom.rootclass.RootInit;
import com.srnpr.zapweb.webdo.PageCache;


public class InitZapweb extends RootInit implements IBaseInit {

	public void init() {
		initCache();
	}

	private void initCache() {
		

		PageCache pCache = new PageCache();
		pCache.refresh();

	}

}
