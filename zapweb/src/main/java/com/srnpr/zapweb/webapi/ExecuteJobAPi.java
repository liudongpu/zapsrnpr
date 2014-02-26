package com.srnpr.zapweb.webapi;

import org.apache.commons.lang.ClassUtils;

import com.srnpr.zapcom.baseface.IBaseJob;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapcom.topapi.RootResult;

public class ExecuteJobAPi extends RootApi<RootResult, ExecuteJobInput> {

	@Override
	public RootResult Process(ExecuteJobInput inputParam, MDataMap mRequestMap) {
		
		RootResult rootResult=new RootResult();
		
		try {
			IBaseJob iBaseJob=(IBaseJob)ClassUtils.getClass(inputParam.getJobName()).newInstance();
			iBaseJob.execute(null);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return rootResult;
	}

}
