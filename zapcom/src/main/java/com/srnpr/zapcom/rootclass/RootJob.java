package com.srnpr.zapcom.rootclass;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseJob;

public abstract class RootJob extends BaseClass implements Job, IBaseJob {

	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		doExecute(context);

	}

	

}
