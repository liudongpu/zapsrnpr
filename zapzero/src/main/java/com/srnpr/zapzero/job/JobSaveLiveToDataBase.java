package com.srnpr.zapzero.job;

import org.quartz.JobExecutionContext;

import com.srnpr.zapcom.rootclass.RootJob;

/**
 * 存放心跳信息到数据库 并判断是否有follower掉线
 * 
 * @author srnpr
 * 
 */
public class JobSaveLiveToDataBase extends RootJob {

	public void doExecute(JobExecutionContext context) {
		// TODO Auto-generated method stub

	}

}
