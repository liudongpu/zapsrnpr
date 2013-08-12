package com.srnpr.zapweb.topcall;

import com.srnpr.zapcom.baseface.IBaseInit;
import com.srnpr.zapcom.rootclass.RootInit;
import com.srnpr.zapweb.webdo.ComponentCache;
import com.srnpr.zapweb.webdo.FuncCache;
import com.srnpr.zapweb.webdo.OperateCache;
import com.srnpr.zapweb.webdo.PageCache;
import com.srnpr.zapweb.webdo.SourceCache;
import com.srnpr.zapweb.webdo.ViewCache;

public class InitZapweb extends RootInit implements IBaseInit {

	public void init() {
		topInitCache(new ViewCache(), new PageCache(), new OperateCache(),
				new FuncCache(), new ComponentCache(), new SourceCache());
	}

}
