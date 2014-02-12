package com.srnpr.zapweb.webfactory;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.srnpr.zapcom.baseface.IBaseApi;
import com.srnpr.zapcom.baseface.IBaseInput;
import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapcom.baseface.IBaseResult;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapcom.basehelper.SecrurityHelper;
import com.srnpr.zapcom.basemodel.MApiAuthorize;
import com.srnpr.zapcom.basemodel.MApiModel;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.DefaultApiCache;
import com.srnpr.zapcom.topapi.DefaultAuthorizeCache;
import com.srnpr.zapcom.topapi.RootResult;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webcache.WebCacheLog;
import com.srnpr.zapweb.webdo.ObjectCache;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webdo.WebTemp;
import com.srnpr.zapweb.webmodel.MWebResult;
import com.srnpr.zapweb.webpage.RootProcess;

/**
 * API调用基类
 * 
 * @author srnpr
 * 
 */
public class ApiFactory implements IBaseInstance {

	public final static ApiFactory INSTANCE = new ApiFactory();

	private final static RootProcess ROOT_PROCESS = new RootProcess();

	/**
	 * 获取处理结果
	 * 
	 * @param hRequest
	 * @return
	 */
	public String upProcess(String sUrl, HttpServletRequest hRequest) {

		String sReturnString = "";

		String sTarget = "";
		String sInputString = "";
		String sTimeSpan = "";
		String sApiKey = "";
		String sApiSecret = "";

		String sApiPass = "";

		String sApiClassString = "";

		// 定义日志Guid
		String sLogGuid = "";

		MDataMap mDataMap = ROOT_PROCESS.convertRequest(hRequest);

		// 定义是否需要加密验证
		boolean bSecret = true;

		MWebResult mResult = new MWebResult();

		// 开始验证api名称和apikey
		if (mResult.upFlagTrue()) {
			if (!mDataMap.containsKey("api_key")) {
				mResult.inErrorMessage(969905007, "api_key");
			} else {
				sTarget = StringUtils.defaultIfBlank(
						mDataMap.get("api_target"), sUrl);

				sApiClassString = StringUtils.trim(sTarget.replace("_", "."));

				sApiKey = mDataMap.get("api_key");

				if (mDataMap.containsKey("api_input")) {
					sInputString = StringUtils.defaultIfBlank(
							mDataMap.get("api_input"), "");
				} else {

					// 如果没有传入api_input 则自动根据url参数拼接简单json格式

					List<String> lInput = new ArrayList<String>();

					for (String skey : mDataMap.keySet()) {
						if (!skey.startsWith("api_")) {

							if (!skey.equals("callbackparam")
									&& !skey.equals("_")) {

								lInput.add("\"" + skey + "\":\""
										+ mDataMap.get(skey) + "\"");
							}
						}

					}

					if (lInput.size() > 0) {
						sInputString = "{" + StringUtils.join(lInput, ",")
								+ "}";
					}

				}

			}
		}

		// 开始判断是否存在
		if (mResult.upFlagTrue()) {

			String sKeyString = WebConst.CONST_OBJECT_CACHE_NAME
					+ "com.srnpr.zapweb.webfactory.ApiFactory.upProcess.apiKey"
					+ sApiClassString;

			if (!ObjectCache.getInstance().containsKey(sKeyString)) {

				String sTypeDid = WebTemp.upTempDataOne("za_apiinfo",
						"api_type_did", "class_name", sApiClassString);

				if (StringUtils.isNotEmpty(sTypeDid)) {

					ObjectCache.getInstance().inElement(sKeyString, sTypeDid);

				} else {
					mResult.inErrorMessage(969905018, sTarget);
				}
			}

			if (ObjectCache.getInstance().containsKey(sKeyString)) {

				bSecret = !ObjectCache.getInstance().upValue(sKeyString)
						.toString().equals("467701200002");
			}

		}

		// 如果是加密参数 则写入日志
		if (bSecret) {
			sLogGuid = WebHelper.upUuid();
			WebCacheLog.INSTANCE.inLog("api_request_" + sLogGuid, hRequest);
		}

		// 开始判断输入参数存在
		if (bSecret && mResult.upFlagTrue()) {
			if (!mDataMap.containsKey("api_timespan")) {
				mResult.inErrorMessage(969905007, "api_timespan");
			} else if (!mDataMap.containsKey("api_secret")) {
				mResult.inErrorMessage(969905007, "api_secret");
			} else {

				sTimeSpan = mDataMap.get("api_timespan");

				sApiSecret = mDataMap.get("api_secret").toUpperCase();

			}
		}

		// 开始验证apikey是否存在并设置pass
		if (mResult.upFlagTrue()) {
			MApiAuthorize mAuthorize = null;
			if (DefaultAuthorizeCache.getInstance().containsKey(sApiKey)) {
				mAuthorize = DefaultAuthorizeCache.getInstance().upValue(
						sApiKey);
			} else {

				MDataMap mAPiAuth = DbUp.upTable("za_apiauthorize").one(
						"api_key", sApiKey);

				if (mAPiAuth != null) {

					mAuthorize = new MApiAuthorize();

					mAuthorize.setApiKey(mAPiAuth.get("api_key"));
					mAuthorize.setApiPass(mAPiAuth.get("api_pass"));
					mAuthorize.setApiAble(mAPiAuth.get("api_able"));

					DefaultAuthorizeCache.getInstance().inElement(
							mAuthorize.getApiKey(), mAuthorize);

				} else {
					mResult.inErrorMessage(969905011);
				}

			}

			if (mAuthorize != null) {
				sApiPass = mAuthorize.getApiPass();
			}
			if (!"".equals(sApiClassString) && mAuthorize.getApiAble() != null
					&& !"".equals(mAuthorize.getApiAble())) {
				String reg = mAuthorize.getApiAble();
				Pattern p = Pattern.compile(reg);
				Matcher m = p.matcher(sApiClassString);
				if (!m.find()) {
					mResult.inErrorMessage(969905020);
				}
			}

		}

		// 开始判断日期时间差
		if (bSecret && mResult.upFlagTrue()) {
			try {

				Date date = FormatHelper.parseDate(sTimeSpan);

				long diff = date.getTime() - (new Date()).getTime();
				long minutes = diff / (1000 * 60);
				if (Math.abs(minutes) > 10) {
					mResult.inErrorMessage(969905009, "10");
				}

			} catch (ParseException e) {
				mResult.inErrorMessage(969905008);
				e.printStackTrace();
			}

		}

		// 开始验证密码戳正确性
		if (bSecret && mResult.upFlagTrue()) {

			String sSource = sTarget + sApiKey + sInputString + sTimeSpan
					+ sApiPass;
			String sSec = SecrurityHelper.MD5(sSource);

			if (!StringUtils.equals(sSec, sApiSecret)) {
				mResult.inErrorMessage(969905010);
			}

		}

		// 开始返回值
		if (mResult.upFlagTrue()) {

			try {
				sReturnString = doProcess(sApiClassString, sInputString,
						mDataMap);
			} catch (Exception e) {
				mResult.inErrorMessage(969905012);

				if (StringUtils.isNotEmpty(sLogGuid)) {
					WebCacheLog.INSTANCE.inElement(
							"api_error_" + sLogGuid,
							new MDataMap("uid", sLogGuid, "time", FormatHelper
									.upDateTime(), "error", e.getMessage()));
				}

				e.printStackTrace();
			}

		}

		// 如果失败 则重新格式化返回消息
		if (!mResult.upFlagTrue()) {

			// 重新格式化输出结果

			RootResult rootResult = new RootResult();
			rootResult.setResultCode(mResult.getResultCode());
			rootResult.setResultMessage(mResult.getResultMessage());

			JsonHelper<RootResult> rJsonHelper = new JsonHelper<RootResult>();

			sReturnString = rJsonHelper.ObjToString(rootResult);

		}

		if (StringUtils.isNotEmpty(sLogGuid)) {
			WebCacheLog.INSTANCE.inElement(
					"api_result_" + sLogGuid,
					new MDataMap("uid", sLogGuid, "time", FormatHelper
							.upDateTime(), "result", sReturnString));
		}

		return sReturnString;
	}

	/**
	 * 获取Api模型
	 * 
	 * @param sClassName
	 * @return
	 */
	public MApiModel upApiModel(String sClassName) {

		return DefaultApiCache.INSTANCE.upValue(sClassName);
	}

	/**
	 * 处理逻辑
	 * 
	 * @param sClassName
	 * @param sInputJson
	 * @param mDataMap
	 * @return
	 * @throws IOException
	 */
	public String doProcess(String sClassName, String sInputJson,
			MDataMap mDataMap) throws IOException {

		String sReturnString = "";

		IBaseApi<IBaseResult, IBaseInput> iBaseApi = null;

		IBaseInput iBaseInput = null;

		try {

			MApiModel mApiModel = upApiModel(sClassName);

			iBaseApi = (IBaseApi<IBaseResult, IBaseInput>) mApiModel
					.getApiClass().newInstance();
			if (StringUtils.isNotBlank(sInputJson))
				iBaseInput = (IBaseInput) mApiModel.getInputClass()
						.newInstance();

		} catch (Exception e) {
			e.printStackTrace();
		}

		JsonHelper<IBaseInput> jsonInput = new JsonHelper<IBaseInput>();
		if (StringUtils.isNotBlank(sInputJson))
			iBaseInput = jsonInput.StringToObjExp(sInputJson, iBaseInput);

		IBaseResult iResult = (IBaseResult) iBaseApi.Process(iBaseInput,
				mDataMap);

		JsonHelper<IBaseResult> jsonResult = new JsonHelper<IBaseResult>();

		sReturnString = jsonResult.ObjToString(iResult);

		return sReturnString;

	}

}
