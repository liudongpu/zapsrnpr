package com.srnpr.zapzero.demo;

import org.quartz.JobExecutionContext;

import com.srnpr.zapcom.rootclass.RootJob;
import com.srnpr.zapzero.enumer.EJmsMessageType;
import com.srnpr.zapzero.support.JmsSupport;

public class DemoJob extends RootJob {

	
	private final static DemoJmsListenser LISTENSER=new DemoJmsListenser();
	
	/* (non-Javadoc)
	 * @see com.srnpr.zapcom.baseface.IBaseJob#doExecute(org.quartz.JobExecutionContext)
	 */
	public void doExecute(JobExecutionContext context) {
		
		
		JmsSupport.getInstance().addTopicLisense("demolistenser", "DemoJob",EJmsMessageType.Toplic, LISTENSER);
		

	}

}
