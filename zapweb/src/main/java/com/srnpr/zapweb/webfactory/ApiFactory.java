package com.srnpr.zapweb.webfactory;

import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseface.IBaseApi;
import com.srnpr.zapcom.baseface.IBaseInput;
import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapcom.baseface.IBaseResult;
import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapcom.basemodel.MApiModel;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.DefaultApiCache;
import com.srnpr.zapweb.webpage.RootProcess;

public class ApiFactory implements IBaseInstance {

	public final static ApiFactory INSTANCE = new ApiFactory();

	private final static RootProcess ROOT_PROCESS = new RootProcess();

	public String upProcess(HttpServletRequest mDataMap) {

		String sTarget = "com.srnpr.zapweb.webfactory.ListApi";

		return "";
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
