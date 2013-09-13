package com.srnpr.zapcom.topapi;

import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapcom.basemodel.MApiModel;
import com.srnpr.zapcom.rootclass.RootCache;

public class DefaultApiCache extends RootCache<String, MApiModel> implements
		IBaseInstance {

	public static final DefaultApiCache INSTANCE = new DefaultApiCache();

	public void refresh() {
		// TODO Auto-generated method stub

	}

}
