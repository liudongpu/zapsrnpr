package com.srnpr.zapcom.rootclass;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseJob;
import com.srnpr.zapcom.baseface.IBaseNotice;

/**
 * 根任务 所有任务接口需要调用该基类
 * 
 * @author srnpr
 * 
 */
public abstract class RootJob extends BaseClass implements Job, IBaseJob {

	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		try {
			doExecute(context);
		} catch (Exception e) {

			bLogError(967905003, this.getClass().getName());
			e.printStackTrace();
		}

	}

}
