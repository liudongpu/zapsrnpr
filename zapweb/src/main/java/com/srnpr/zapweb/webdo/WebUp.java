package com.srnpr.zapweb.webdo;

import com.srnpr.zapweb.webmodel.MWebPage;
import com.srnpr.zapweb.webmodel.MWebView;

public class WebUp {

	private final static ViewCache viewCache = new ViewCache();

	private final static PageCache pageCache = new PageCache();

	/**
	 * @param sViewKey  格式为视图编码-视图类型  例如v_test-116022003
	 * @return
	 */
	public static MWebView upViewCache(String sViewKey) {
		return viewCache.upValue(sViewKey);
	}

	public static MWebPage upPage(String sKey) {
		return pageCache.upValue(sKey);
	}

}
