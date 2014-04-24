package com.srnpr.zapweb.topcall;

import com.srnpr.zapcom.baseface.IBaseInit;
import com.srnpr.zapcom.rootclass.RootInit;
import com.srnpr.zapweb.webdo.ComponentCache;
import com.srnpr.zapweb.webdo.FuncCache;
import com.srnpr.zapweb.webdo.OperateCache;
import com.srnpr.zapweb.webdo.PageCache;
import com.srnpr.zapweb.webdo.SourceCache;
import com.srnpr.zapweb.webdo.ViewCache;
import com.srnpr.zapweb.websupport.FileConcatSupport;

/**
 * @author srnpr
 * 
 */
public class InitZapweb extends RootInit implements IBaseInit {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapcom.baseface.IBaseInit#init()
	 */
	public boolean onInit() {
		topInitCache(new ViewCache(), new PageCache(), new OperateCache(),
				new FuncCache(), new ComponentCache(), new SourceCache());

		// 初始化合并文件
		new FileConcatSupport().concatByConfig();

		return true;
	}

	public boolean desctory() {

		return true;
	}

	@Override
	public boolean onDestory() {
		return true;
	}

}
