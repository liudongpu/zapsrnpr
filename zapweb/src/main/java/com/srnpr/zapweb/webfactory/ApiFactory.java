package com.srnpr.zapweb.webfactory;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.srnpr.zapcom.baseface.IBaseApi;
import com.srnpr.zapcom.baseface.IBaseInput;
import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapcom.baseface.IBaseResult;
import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapcom.basemodel.MApiModel;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.DefaultApiCache;
import com.srnpr.zapweb.webmodel.MWebResult;
import com.srnpr.zapweb.webpage.RootProcess;

public class ApiFactory implements IBaseInstance {

	public final static ApiFactory INSTANCE = new ApiFactory();

	private final static RootProcess ROOT_PROCESS = new RootProcess();

	public String upProcess(HttpServletRequest hRequest) {

		String sReturnString = "";

		String sTarget = "";
		String sInputString = "";
		String sTimeSpan = "";
		String sApiKey = "";
		String sApiSecret = "";

		MDataMap mDataMap = ROOT_PROCESS.convertRequest(hRequest);

		MWebResult mResult = new MWebResult();

		// 开始判断输入参数存在
		if (mResult.upFlagTrue()) {
			if (!mDataMap.containsKey("api_target")) {
				mResult.inErrorMessage(969905007, "api_target");
			} else if (!mDataMap.containsKey("api_key")) {
				mResult.inErrorMessage(969905007, "api_key");
			} else if (!mDataMap.containsKey("api_timespan")) {
				mResult.inErrorMessage(969905007, "api_timespan");
			} else if (!mDataMap.containsKey("api_secret")) {
				mResult.inErrorMessage(969905007, "api_secret");
			} else if (!mDataMap.containsKey("api_input")) {
				mResult.inErrorMessage(969905007, "api_input");
			} else {
				sTarget = mDataMap.get("api_target");
				sTimeSpan = mDataMap.get("api_timespan");
				sInputString = mDataMap.get("api_input");
				sApiKey = mDataMap.get("api_key");
				sApiSecret = mDataMap.get("api_secret");

			}
		}

		// 开始判断日期时间差
		if (mResult.upFlagTrue()) {
			try {
				Date date = DateFormat.getDateTimeInstance().parse(sTimeSpan);

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

		// 开始返回值
		if (mResult.upFlagTrue()) {
			
			sTarget =sTarget.replace("_", ".");
			
			sReturnString = doProcess(sTarget, sInputString);
		} else {
			sReturnString = mResult.upJson();
		}

		return sReturnString;
	}

	public MApiModel upApiModel(String sClassName) {
		MApiModel mApiModel = null;
		try {

			if (!DefaultApiCache.INSTANCE.containsKey(sClassName)) {

				mApiModel = new MApiModel();

				Class<?> c = Class.forName(sClassName);

				Class<?> cReturn = null;
				Class<?> cInputClass = null;

				for (Method method : c.getMethods()) {

					if (method.getName().equals("Process")
							&& !method.isBridge()) {
						cReturn = method.getReturnType();
						cInputClass = method.getParameterTypes()[0];

					}

				}

				mApiModel.setApiClass(c);
				mApiModel.setInputClass(cInputClass);
				mApiModel.setResultClass(cReturn);

				DefaultApiCache.INSTANCE.inElement(sClassName, mApiModel);

			} else {
				mApiModel = DefaultApiCache.INSTANCE.upValue(sClassName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mApiModel;
	}

	public String doProcess(String sClassName, String sInputJson) {

		String sReturnString = "";

		IBaseApi iBaseApi = null;

		IBaseInput iBaseInput = null;

		try {

			MApiModel mApiModel = upApiModel(sClassName);

			iBaseApi = (IBaseApi) mApiModel.getApiClass().newInstance();

			iBaseInput = (IBaseInput) mApiModel.getInputClass().newInstance();

		} catch (Exception e) {
			e.printStackTrace();
		}

		JsonHelper<IBaseInput> jsonInput = new JsonHelper<IBaseInput>();

		iBaseInput = jsonInput.StringToObj(sInputJson, iBaseInput);

		IBaseResult iResult = (IBaseResult) iBaseApi.Process(iBaseInput);

		JsonHelper<IBaseResult> jsonResult = new JsonHelper<IBaseResult>();

		sReturnString = jsonResult.ObjToString(iResult);

		return sReturnString;

	}

}
