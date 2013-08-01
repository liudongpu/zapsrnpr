package com.srnpr.zapweb.webdo;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.topdo.TopUp;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webface.IWebFunc;
import com.srnpr.zapweb.webmodel.MWebOperate;
import com.srnpr.zapweb.webmodel.MWebPage;
import com.srnpr.zapweb.webmodel.MWebSource;
import com.srnpr.zapweb.webmodel.MWebView;

public class WebUp {

	private final static ViewCache viewCache = new ViewCache();

	private final static PageCache pageCache = new PageCache();

	private final static OperateCache operateCache = new OperateCache();

	private final static FuncCache funcCache = new FuncCache();
	
	private final static SourceCache sourceCache = new SourceCache();

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

	
	public static MWebSource upSource(String sKey)
	{
		return sourceCache.upValue(sKey);
	}
	
	
	
	public static String upProjectId()
	{
		String sProject=TopUp.upConfig("zapcom.projectid");
		if(StringUtils.isEmpty(sProject))
		{
			sProject="101002677";
		}
		return sProject;
	}
	
	
}
