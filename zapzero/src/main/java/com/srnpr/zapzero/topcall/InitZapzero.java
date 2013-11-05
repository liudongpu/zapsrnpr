package com.srnpr.zapzero.topcall;

import java.util.Observable;
import java.util.Observer;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.observable.ConfigObservable;
import com.srnpr.zapcom.rootclass.RootInit;
import com.srnpr.zapcom.topdo.TopUp;
import com.srnpr.zapweb.webapi.LeaderConfig;
import com.srnpr.zapweb.webapi.LeaderConfigResult;
import com.srnpr.zapweb.webapi.SimpleApiInput;
import com.srnpr.zapweb.websupport.ApiCallSupport;
import com.srnpr.zapzero.server.ServerInfo;

public class InitZapzero extends RootInit implements Observer {

	public boolean init() {
		return initServer();
	}

	/**
	 * 初始化运行模式
	 * 
	 * @return
	 */
	private boolean initServer() {

		boolean bFlagReturn = true;

		if (bFlagReturn) {
			String sServerType = bConfig("default.local_run_type");

			ServerInfo.INSTANCE
					.setServerCode(bConfig("default.local_server_code"));

			ServerInfo.INSTANCE.setRunType(sServerType);

			bLogInfo(970212010, sServerType);

			// 如果加载的是跟随者 则开始连接主服务器的配置
			if (ServerInfo.INSTANCE.getRunType().equals("follower")) {

				ConfigObservable.INSTANCE.addObserver(this);

				bFlagReturn = doUpdateConfig();

				if (!bFlagReturn) {
					bLogInfo(970212012);
				}
			}

		}

		return bFlagReturn;

	}

	/**
	 * 更新配置
	 * 
	 * @return
	 */
	private boolean doUpdateConfig() {

		boolean bReturn = true;

		
		//调用主服务器
		ApiCallSupport<SimpleApiInput, LeaderConfigResult> apiCallSupport = new ApiCallSupport<SimpleApiInput, LeaderConfigResult>();

		SimpleApiInput sInput = new SimpleApiInput();
		LeaderConfigResult lResult = new LeaderConfigResult();

		String[] sMaserServer = bConfig("default.leader_server_address").split(
				",");
		
		sInput.setInputString(bConfig("default.follower_load_config"));
		

		for (String s : sMaserServer) {
			bLogInfo(970212011, s);
			try {
				
				lResult = apiCallSupport.doCallApi(s,
						"com_srnpr_zapweb_webapi_LeaderConfig",
						bConfig("default.leader_server_apikey"),
						bConfig("default.leader_server_apipass"), sInput,
						lResult);
			} catch (Exception e) {
				bReturn = false;
				e.printStackTrace();

			}

			// 如果返回结果错误 也置为失败
			if (lResult.getResultCode() != 1) {
				bReturn = false;
			}

			// 如果连接成功 则设置连接配置
			if (bReturn) {
				ServerInfo.INSTANCE.setApiHost(s);
				break;
			}

		}

		if (bReturn) {
			
			
			bLogInfo(0,lResult.getConfigMap().size());
			

		}

		// apiCallSupport.doCallApi(sAddress, sTarget, sApiKey, sApiPass, input,
		// tResult)

		return bReturn;
	}

	public void update(Observable o, Object arg) {

		doUpdateConfig();

	}

}
