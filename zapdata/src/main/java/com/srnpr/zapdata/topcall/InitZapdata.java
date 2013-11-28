package com.srnpr.zapdata.topcall;

import com.srnpr.zapcom.rootclass.RootInit;
import com.srnpr.zapdata.dbcache.TableCache;

public class InitZapdata extends RootInit {
	@Override
	public boolean onInit() {

		topInitCache(new TableCache());

		return true;
	}

	@Override
	public boolean onDestory() {
		// TODO Auto-generated method stub
		return false;
	}

}
