package com.srnpr.zapweb.demo;

import com.srnpr.zapcom.topapi.RootApi;

/**
 * DemoAPI调用类
 * 
 * @author srnpr
 * 
 */
public class DemoApiProcess extends RootApi<DemoApiResult, DemoApiInput> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapcom.baseface.IBaseApi#Process(java.lang.Object)
	 */
	public DemoApiResult Process(DemoApiInput apiInput) {

		DemoApiResult rResult = new DemoApiResult();

		rResult.setResultFiledOne("test input " + apiInput.getDemoInputFirst());

		rResult.setResultFieldTwo(apiInput.getDemoInputInt());

		return rResult;

	}

}
