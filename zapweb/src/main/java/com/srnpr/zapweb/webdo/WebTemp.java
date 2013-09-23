package com.srnpr.zapweb.webdo;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;

/**
 * 
 * 缓存类 调用该类的方法一般是非经常修改的
 * 
 * @author srnpr
 * 
 */
public class WebTemp {

	private final static ObjectCache objectCache = new ObjectCache();

	/**
	 * 获取指定字段的数据库值 该方法获取的值一般情况下不会修改
	 * 
	 * @param sTableName
	 * @param sFields
	 * @param sParams
	 * @return
	 */
	public static String upTempDataOne(String sTableName, String sFields,
			String... sParams) {

		String sReturn = "";

		String sKeyString = sTableName + sFields
				+ StringUtils.join(sParams, WebConst.CONST_SPLIT_LINE);

		if (objectCache.containsKey(sKeyString)) {
			sReturn = objectCache.upValue(sKeyString).toString();
		} else {
			MDataMap mWhereMap = new MDataMap();
			mWhereMap.inAllValues(sParams);

			sReturn = DbUp.upTable(sTableName).one(sParams).get(sFields);
			objectCache.inElement(sKeyString, sReturn);
		}

		return sReturn;

	}

	/**
	 * 获取listmap
	 * 
	 * @param sTableName
	 * @param sFields
	 * @param sOrders
	 * @param sWhere
	 * @param sParams
	 * @return
	 */
	public static List<MDataMap> upTempDataList(String sTableName,
			String sFields, String sOrders, String sWhere, String... sParams) {

		List<MDataMap> listReturn = null;
		String sKeyString = sTableName + sFields + sOrders + sWhere
				+ StringUtils.join(sParams, WebConst.CONST_SPLIT_LINE);

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
