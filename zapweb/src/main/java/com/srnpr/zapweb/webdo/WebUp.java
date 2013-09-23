package com.srnpr.zapweb.webdo;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topdo.TopUp;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webface.IWebComponent;
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

	private final static ComponentCache componentCache = new ComponentCache();

	private final static ObjectCache objectCache = new ObjectCache();

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
	 * 
	 * @param sViewCode
	 * @return
	 */
	public static MWebView upQueryView(String sViewCode) {
		return viewCache.upValue(sViewCode + "-" + "116022009");
	}

	/**
	 * 获取页面
	 * 
	 * @param sKey
	 * @return
	 */
	public static MWebPage upPage(String sKey) {
		return pageCache.upValue(sKey);
	}

	/**
	 * 获取函数
	 * 
	 * @param sKey
	 * @return
	 */
	public static IWebFunc upFunc(String sKey) {
		return funcCache.upValue(sKey);
	}

	/**
	 * 获取操作
	 * 
	 * @param sKey
	 * @return
	 */
	public static MWebOperate upOperate(String sKey) {
		return operateCache.upValue(sKey);
	}

	/**
	 * 获取数据源
	 * 
	 * @param sKey
	 * @return
	 */
	public static MWebSource upSource(String sKey) {
		return sourceCache.upValue(sKey);
	}

	/**
	 * 获取组件
	 * 
	 * @param sKey
	 * @return
	 */
	public static IWebComponent upComponent(String sKey) {
		return componentCache.upValue(sKey);
	}

	/**
	 * 获取项目调用编号
	 * 
	 * @return
	 */
	public static String upProjectId() {
		String sProject = TopUp.upConfig("zapcom.projectid");
		if (StringUtils.isEmpty(sProject)) {
			sProject = "101002677";
		}
		return sProject;
	}

	public static List<MDataMap> upTempDataList(String sTableName,
			String sFields, String sOrders, String sWhere, String... sParams) {

		List<MDataMap> listReturn = null;
		String sKeyString = sTableName + sFields + sOrders + sWhere
				+ StringUtils.join(sParams, "&");

		if (objectCache.containsKey(sKeyString)) {
			listReturn = (List<MDataMap>) objectCache.upValue(sKeyString);
		} else {

			MDataMap mWhereMap = new MDataMap();
			mWhereMap.inAllValues(sParams);
			
			

			listReturn =

			DbUp.upTable(sTableName).queryAll(sFields, sOrders, sWhere,
					mWhereMap);

			objectCache.inElement(sKeyString, listReturn);

		}
		return listReturn;

		// DbUp.upTable(sTableName).listByWhere(sParams)

	}
}
