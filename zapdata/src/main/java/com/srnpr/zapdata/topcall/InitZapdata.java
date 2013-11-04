package com.srnpr.zapdata.topcall;

import com.srnpr.zapcom.rootclass.RootInit;
import com.srnpr.zapdata.dbcache.TableCache;

public class InitZapdata extends RootInit {

	public boolean init() {

		topInitCache(new TableCache());

		return true;
	}

}
