package com.srnpr.zapweb.webdo;

import com.srnpr.zapweb.webface.IWebFunc;
import com.srnpr.zapweb.webmodel.MWebOperate;
import com.srnpr.zapweb.webmodel.MWebPage;
import com.srnpr.zapweb.webmodel.MWebView;

public class WebUp {

	private final static ViewCache viewCache = new ViewCache();

	private final static PageCache pageCache = new PageCache();

	private final static OperateCache operateCache = new OperateCache();

	private final static FuncCache funcCache = new FuncCache();

	/**
	 * @param sViewKey
	 *            格式为视图编码-视图类型 例如v_test-116022003
	 * @return
	 */
	public static MWebView upViewCache(String sViewKey) {
		return viewCache.upValue(sViewKey);
	}

	/**
	 * 得到查询视图
	 * @param sViewCode
	 * @return
	 */
	public static MWebView upQueryView(String sViewCode) {
		return viewCache.upValue(sViewCode + "-" + "116022009");
	}

	public static MWebPage upPage(String sKey) {
		return pageCache.upValue(sKey);
	}

	public static IWebFunc upFunc(String sKey) {
		return funcCache.upValue(sKey);
	}

	public static MWebOperate upOperate(String sKey) {
		return operateCache.upValue(sKey);
	}

}
