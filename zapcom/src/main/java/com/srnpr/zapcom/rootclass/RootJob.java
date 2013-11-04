package com.srnpr.zapcom.rootclass;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.srnpr.zapcom.baseclass.BaseClass;

public abstract class RootJob extends BaseClass implements Job {

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
		doExecute(context);

	}

	public abstract void doExecute(JobExecutionContext context);

}
