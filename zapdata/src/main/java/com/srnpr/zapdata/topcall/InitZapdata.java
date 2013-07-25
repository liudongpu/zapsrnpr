package com.srnpr.zapdata.topcall;

import com.srnpr.zapcom.rootclass.RootInit;
import com.srnpr.zapdata.dbcache.DbCache;
import com.srnpr.zapdata.dbcache.TableCache;

public class InitZapdata extends RootInit {

	public void init() {

		topInitCache(new TableCache());
	}

}
