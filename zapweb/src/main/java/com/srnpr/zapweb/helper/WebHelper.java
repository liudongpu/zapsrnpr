package com.srnpr.zapweb.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.FormatHelper;
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
	 * 加锁
	 * 
	 * @param keys
	 *            要加锁的Key,用 英文逗号 “ ,” 分割。
	 * @param timeOutSeconds
	 *            过期秒数。
	 * @return
	 */
	public static String addLock(String keys, int timeOutSeconds) {
		// CALL proc_lock_or_unlock_somekey
		// (@somekey,@keysplit,@timeoutsecond,@lockflag,REPLACE(UUID(),'-',''));
		try {
			UUID uuid = UUID.randomUUID();
			String uid = uuid.toString().replace("-", "");
			MDataMap mdataMap = new MDataMap();
			mdataMap.put("somekey", keys);
			mdataMap.put("timeoutsecond", String.valueOf(timeOutSeconds));
			mdataMap.put("uuid", uid);

			Map<String, Object> mResultMap = DbUp
					.upTable("zw_webcode")
					.dataSqlOne(
							"call proc_lock_or_unlock_somekey(:somekey,',',:timeoutsecond,1,:uuid);",
							mdataMap);

			if (mResultMap.get("outFlag").toString().equals("1"))
				return uid;
			else
				return "";
		} catch (Exception ex) {
			return "";
		}

	}

	/**
	 * 解锁
	 * 
	 * @param uuid
	 *            要解锁的uuid
	 * @return
	 */
	public static String unLock(String uuid) {
		// CALL proc_lock_or_unlock_somekey
		// (@somekey,@keysplit,@timeoutsecond,@lockflag,REPLACE(UUID(),'-',''));
		try {
			MDataMap mdataMap = new MDataMap();
			mdataMap.put("uuid", uuid);

			Map<String, Object> mResultMap = DbUp
					.upTable("zw_webcode")
					.dataSqlOne(
							"call proc_lock_or_unlock_somekey('',',',0,2,:uuid);",
							mdataMap);

			if (mResultMap.get("outFlag").toString().equals("1"))
				return uuid;
			else
				return "";
		} catch (Exception ex) {
			return "";
		}
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

	public static String recheckReplace(String sText, MDataMap mDataMap) {
		Pattern p = Pattern.compile("\\[@(.+?)\\$(.*?)\\]");
		Matcher m = p.matcher(sText);
		while (m.find()) {

			String sFull = m.group(0);
			String sKey = m.group(1);
			String sAttr = m.group(2);

			String sReplace = "";

			if (sKey.equals("this")) {
				if (mDataMap.containsKey(sAttr)) {
					sReplace = mDataMap.get(sAttr);
				}
			} else if (sKey.equals("code")) {
				sReplace = WebHelper.upCode(sAttr);
			} else if (sKey.equals("datenow")) {
				sReplace = FormatHelper.upDateTime();
			}

			sText = sText.replace(sFull, sReplace);

		}

		return sText;

	}

}
