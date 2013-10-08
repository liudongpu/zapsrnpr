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

		MDataMap mMap = upTempDataMap(sTableName, sFields, sParams);

		if (mMap != null && mMap.containsKey(sFields)) {
			sReturn = mMap.get(sFields);
		}

		return sReturn;

	}

	/**
	 * @param sTableName
	 * @param sFields
	 * @param sParams
	 * @return
	 */
	public static MDataMap upTempDataMap(String sTableName, String sFields,
			String... sParams) {

		MDataMap mReturnDataMap = null;

		String sKeyString = WebConst.CONST_OBJECT_CACHE_NAME
				+ "com.srnpr.zapweb.webdo.WebTemp.upTempDataMap" + sTableName
				+ sFields
				+ StringUtils.join(sParams, WebConst.CONST_SPLIT_LINE);

		if (ObjectCache.getInstance().containsKey(sKeyString)) {
			mReturnDataMap = (MDataMap) ObjectCache.getInstance().upValue(
					sKeyString);
		} else {
			MDataMap mWhereMap = new MDataMap();
			mWhereMap.inAllValues(sParams);

			mReturnDataMap = DbUp.upTable(sTableName).one(sParams);
			ObjectCache.getInstance().inElement(sKeyString, mReturnDataMap);
		}

		return mReturnDataMap;

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
		String sKeyString = WebConst.CONST_OBJECT_CACHE_NAME
				+ "com.srnpr.zapweb.webdo.WebTemp.upTempDataList" + sTableName
				+ sFields + sOrders + sWhere
				+ StringUtils.join(sParams, WebConst.CONST_SPLIT_LINE);

		if (ObjectCache.getInstance().containsKey(sKeyString)) {
			listReturn = (List<MDataMap>) ObjectCache.getInstance().upValue(
					sKeyString);
		} else {

			MDataMap mWhereMap = new MDataMap();
			mWhereMap.inAllValues(sParams);

			listReturn =

			DbUp.upTable(sTableName).queryAll(sFields, sOrders, sWhere,
					mWhereMap);

			ObjectCache.getInstance().inElement(sKeyString, listReturn);

		}
		return listReturn;

		// DbUp.upTable(sTableName).listByWhere(sParams)

	}
}
