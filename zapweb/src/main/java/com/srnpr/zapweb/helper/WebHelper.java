package com.srnpr.zapweb.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basehelper.SecrurityHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topdo.TopConfig;
import com.srnpr.zapcom.topdo.TopUp;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.usermodel.MUserInfo;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webfactory.UserFactory;
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
	 * 获取uuid
	 * 
	 * @return
	 */
	public static String upUuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 加锁
	 * 
	 * @param keys。
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
			ex.printStackTrace();
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

	/**
	 * 格式化字段并重新返回
	 * 
	 * @param sText
	 * @param mDataMap
	 * @return
	 */
	public static String recheckReplace(String sText, MDataMap mDataMap) {

		if (StringUtils.contains(sText, WebConst.CONST_WEB_SET_REPLACE)) {

			Pattern p = Pattern.compile("\\[@(.+?)\\$(.*?)\\]");
			Matcher m = p.matcher(sText);
			while (m.find()) {

				String sFull = m.group(0);
				String sKey = m.group(1);
				String sAttr = m.group(2);

				String sReplace = "";

				// 如果参数是this 则指向当前的map
				if (sKey.equals("this")) {
					if (mDataMap.containsKey(sAttr)) {
						sReplace = mDataMap.get(sAttr);
					}
				}
				// 如果参数是code 则获取后标记位开始的
				else if (sKey.equals("code")) {
					sReplace = WebHelper.upCode(sAttr);
				}
				// 如果参数是datenow 则替换为当前时间
				else if (sKey.equals("datenow")) {
					sReplace = FormatHelper.upDateTime();
					// 如果是Md5 则加密map中的该字段
				} else if (sKey.equals("md5")) {
					String sValueString = mDataMap.get(sAttr);
					if (StringUtils.isNotEmpty(sValueString)) {
						sReplace = SecrurityHelper.MD5Customer(sValueString);
					}
				}
				// 获取request请求值
				else if (sKey.equals("request")) {
					sReplace = WebSessionHelper.create().upRequest(sAttr).trim();
				}
				//替换config
				else if(sKey.equals("config"))
				{
					sReplace=TopUp.upConfig(sAttr);
				}
				// 如果参数是user 则根据后续参数替换
				else if (sKey.equals("user")) {

					MUserInfo mUserInfo = UserFactory.INSTANCE.create();
					if (mUserInfo.getFlagLogin() == 1) {

						if (sAttr.equals("manageCode")) {
							sReplace = mUserInfo.getManageCode();
						} else if (sAttr.equals("loginName")) {
							sReplace = mUserInfo.getLoginName();
						} else if (sAttr.equals("realName")) {
							sReplace = mUserInfo.getRealName();
						} else if (sAttr.equals("userCode")) {
							sReplace = mUserInfo.getUserCode();
						}

					}

				}

				sText = sText.replace(sFull, sReplace);

			}
		}
		return sText;

	}

	/**
	 * 错误信息类
	 * 
	 * @param sCode
	 *            错误编号
	 * @param sErrorType
	 *            错误类型
	 * @param iErrorLevel
	 *            错误级别 数字越大越严重 默认写0
	 * @param sErrorSource
	 *            错误来源 一般传过来类名吧
	 * @param sMessage
	 *            错误内容
	 * @param e
	 *            可传null 如果不为null 则printStackTrace
	 */
	public static void errorMessage(String sCode, String sErrorType,
			int iErrorLevel, String sErrorSource, String sMessage, Exception e) {

		try {

			if (e != null) {
				sMessage = sMessage + " #########" + e.getMessage();

				e.printStackTrace();

			}

			DbUp.upTable("zw_error").insert("error_code", sCode, "error_type",
					sErrorType, "error_level", String.valueOf(iErrorLevel),
					"error_source", sErrorSource, "error_info", sMessage,
					"create_time", FormatHelper.upDateTime());
		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}

}
