package com.srnpr.zapweb.webmethod;

import java.util.List;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webface.IWebMethod;

/**
 * 
 * 页面调用函数类 该类一向为静态传递给页面调用
 * 
 * @author srnpr
 * 
 */
public abstract class RootMethod extends BaseClass implements IWebMethod {

	/**
	 * 获取查询
	 * 
	 * @param sTaleName
	 * @param sOrders
	 * @param sWhere
	 * @param sPrams
	 * @return
	 */
	public List<MDataMap> upDataQuery(String sTaleName, String sOrders,
			String sWhere, String... sPrams) {
		return DbUp.upTable(sTaleName).queryAll("", sOrders, sWhere,
				new MDataMap(sPrams));

	}
	
	
	
	
	
	

}
