package com.srnpr.zapcom.topapi;

import java.lang.reflect.Method;

import org.apache.commons.lang.ClassUtils;

import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapcom.basemodel.MApiAuthorize;
import com.srnpr.zapcom.basemodel.MApiModel;
import com.srnpr.zapcom.rootclass.RootCache;

/**
 * API缓存类
 * 
 * @author srnpr
 * 
 */
public class DefaultApiCache extends RootCache<String, MApiModel> implements
		IBaseInstance {

	public static final DefaultApiCache INSTANCE = new DefaultApiCache();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapcom.rootclass.RootCache#upOne(java.lang.Object)
	 */
	public MApiModel upOne(String sKey) {
		MApiModel mApiModel = new MApiModel();

		// Class<?> c = Class.forName(sClassName);

		Class<?> c = null;
		try {
			c = ClassUtils.getClass(sKey);

			Class<?> cReturn = null;
			Class<?> cInputClass = null;

			for (Method method : c.getMethods()) {

				if (method.getName().equals("Process") && !method.isBridge()) {
					cReturn = method.getReturnType();
					cInputClass = method.getParameterTypes()[0];

				}

			}

			mApiModel.setApiClass(c);
			mApiModel.setInputClass(cInputClass);
			mApiModel.setResultClass(cReturn);

			inElement(sKey, mApiModel);
		} catch (ClassNotFoundException e) {

			inElement(sKey, null);

			e.printStackTrace();
		}

		return mApiModel;
	}

	public void refresh() {

	}

}
