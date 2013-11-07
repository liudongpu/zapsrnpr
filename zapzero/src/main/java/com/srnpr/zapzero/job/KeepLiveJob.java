package com.srnpr.zapzero.job;

import org.quartz.JobExecutionContext;

import com.srnpr.zapcom.baseface.IBaseInput;
import com.srnpr.zapcom.baseface.IBaseResult;
import com.srnpr.zapcom.rootclass.RootJob;
import com.srnpr.zapweb.websupport.ApiCallSupport;
import com.srnpr.zapzero.api.ApiKeepLiveInput;
import com.srnpr.zapzero.api.ApiKeepLiveResult;
import com.srnpr.zapzero.server.ServerInfo;

/**
 * 心跳服务任务<br>
 * 该类会调用心跳服务与leader服务器同步自身信息
 * 
 * @author srnpr
 * 
 */
public class KeepLiveJob extends RootJob {

	final static ApiCallSupport<IBaseInput, ApiKeepLiveResult> keeperlive = new ApiCallSupport<IBaseInput, ApiKeepLiveResult>();

	public void doExecute(JobExecutionContext context) {

		ApiKeepLiveResult apiResult = new ApiKeepLiveResult();
		try {
			keeperlive.doCallApi(ServerInfo.INSTANCE.getApiHost(),
					"com_srnpr_zapweb_webapi_LeaderConfig",
					bConfig("default.leader_server_apikey"),
					bConfig("default.leader_server_apipass"),
					ServerInfo.INSTANCE, apiResult);
		} catch (Exception e) {
			bLogError(970205001);
			e.printStackTrace();
		}

		// keeperlive.doCallApi(sAddress, sTarget, sApiKey, sApiPass, input,
		// tResult)

		// keeperlive.doCallApi(sAddress, sTarget, sApiKey, sApiPass, input,
		// tResult)

		// keeperlive.doCallApi(ServerInfo.INSTANCE.getApiHost(), sTarget,
		// sApiKey, sApiPass, input, tResult)

	}

}
