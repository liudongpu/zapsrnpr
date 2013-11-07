package com.srnpr.zapzero.job;

import org.quartz.JobExecutionContext;

import com.srnpr.zapcom.rootclass.RootJob;
import com.srnpr.zapzero.api.ApiKeepLiveInput;
import com.srnpr.zapzero.cache.CacheKeepLive;
import com.srnpr.zapzero.server.ServerInfo;

/**
 * 存放心跳信息到数据库 并判断是否有follower掉线
 * 
 * @author srnpr
 * 
 */
public class JobSaveLiveToDataBase extends RootJob {

	public void doExecute(JobExecutionContext context) {
		// TODO Auto-generated method stub

		
		CacheKeepLive.getInstance().inElement(ServerInfo.INSTANCE.getServerCode(),
				ServerInfo.INSTANCE);
		
		for(String sKey:CacheKeepLive.getInstance().upKeys())
		{
			
			ApiKeepLiveInput aInfo=CacheKeepLive.getInstance().upValue(sKey);
			
			
		
			
		}
		
		
		
		
		
	}

}
