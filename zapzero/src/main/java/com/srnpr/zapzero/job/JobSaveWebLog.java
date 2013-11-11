package com.srnpr.zapzero.job;

import org.quartz.JobExecutionContext;

import com.srnpr.zapcom.rootclass.RootJob;
import com.srnpr.zapzero.server.ServerInfo;
import com.srnpr.zapzero.support.JmsSupport;

public class JobSaveWebLog extends RootJob {

	public void doExecute(JobExecutionContext context) {

		ListenseWebLog listener = new ListenseWebLog();

		JmsSupport.getInstance().addTopicLisense("469910200001",
				this.getClass().getName(), listener);

	}

}
