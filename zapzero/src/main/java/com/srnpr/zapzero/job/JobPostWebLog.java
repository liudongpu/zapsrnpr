package com.srnpr.zapzero.job;

import org.quartz.JobExecutionContext;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.rootclass.RootJob;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webcache.WebCacheLog;
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

		String sDateTime = FormatHelper.upDateTime();
		for (String sKey : WebCacheLog.INSTANCE.upKeys()) {
			JmsSupport.getInstance().sendToTopic(
					"469910200001",
					jsonHelper.ObjToString(WebCacheLog.INSTANCE
							.upValueAndRemove(sKey)),
					new MDataMap("code", sKey, "create_time", sDateTime));

		}

	}

}
