package com.srnpr.zapzero.job;

import org.quartz.JobExecutionContext;

import com.srnpr.zapcom.rootclass.RootJob;
import com.srnpr.zapzero.enumer.EJmsMessageType;
import com.srnpr.zapzero.server.ServerInfo;
import com.srnpr.zapzero.support.JmsSupport;

public class JobSaveWebLog extends RootJob {

	private final static ListenseWebLog listener = new ListenseWebLog();

	public void doExecute(JobExecutionContext context) {

		JmsSupport.getInstance().addTopicLisense("469910200001", "",
				EJmsMessageType.Queue, listener);

	}

}
