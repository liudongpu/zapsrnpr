package com.srnpr.zapzero.job;

import org.quartz.JobExecutionContext;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.rootclass.RootJob;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webcache.WebCacheLog;
import com.srnpr.zapzero.enumer.EJmsMessageType;
import com.srnpr.zapzero.support.JmsSupport;

/**
 * 转存web日志文件
 * 
 * @author srnpr
 * 
 */
public class JobPostWebLog extends RootJob {

	private JsonHelper<MDataMap> jsonHelper = new JsonHelper<MDataMap>();

	public void doExecute(JobExecutionContext context) {

		//String sDateTime = FormatHelper.upDateTime();
		for (String sKey : WebCacheLog.INSTANCE.upKeys()) {
			
			MDataMap map=WebCacheLog.INSTANCE
					.upValueAndRemove(sKey);
			
			JmsSupport.getInstance().sendMessage(
					"469910200001",
					jsonHelper.ObjToString(map),
					new MDataMap("code", sKey, "create_time", map.get("time")),
					EJmsMessageType.Queue);

		}

	}

}
