package com.srnpr.zapweb.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webmodel.MWebField;

public class WebHelper {

	/**
	 * 获取唯一编号
	 * 
	 * @param sCodeStart
	 * @return
	 */
	public static String upCode(String sCodeStart) {

		Map<String, Object> mResultMap = DbUp.upTable("zw_webcode").dataSqlOne(
				"call proc_zw_getcode(:code);",
				new MDataMap("code", sCodeStart));
		return mResultMap.get("webcode").toString();
	}

	/**
	 * 该操作函数为预留函数 输出性Url统一走该操作 防止以后替换
	 * 
	 * @param sUrl
	 * @return
	 */
	public static String checkUrl(String sUrl) {
		return sUrl;
	}

	/**
	 * 获取字段对应的sql 字段拼接
	 * 
	 * @param lFields
	 * @return
	 */
	public static String upFieldSql(List<MWebField> lFields) {
		List<String> lSqlStrings = new ArrayList<String>();
		for (MWebField mField : lFields) {

			if (StringUtils.isNotEmpty(mField.getColumnName())) {

				lSqlStrings.add(mField.getColumnName() + " as "
						+ mField.getFieldName());
			}
		}
		return StringUtils.join(lSqlStrings, ",");

	}
}
