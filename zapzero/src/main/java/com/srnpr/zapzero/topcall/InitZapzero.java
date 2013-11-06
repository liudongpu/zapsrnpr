package com.srnpr.zapzero.topcall;

import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basemodel.MStringMap;
import com.srnpr.zapcom.observable.ConfigObservable;
import com.srnpr.zapcom.rootclass.RootInit;
import com.srnpr.zapcom.topdo.TopConst;
import com.srnpr.zapcom.topdo.TopDir;
import com.srnpr.zapcom.topdo.TopUp;
import com.srnpr.zapweb.helper.WebHelper;
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

		// 调用主服务器
		ApiCallSupport<SimpleApiInput, LeaderConfigResult> apiCallSupport = new ApiCallSupport<SimpleApiInput, LeaderConfigResult>();

		SimpleApiInput sInput = new SimpleApiInput();
		LeaderConfigResult lResult = new LeaderConfigResult();

		String[] sMaserServer = bConfig("default.leader_server_address").split(
				",");

		sInput.setInputString(bConfig("default.follower_load_config"));

		if (bReturn) {
			if (sMaserServer.length == 0) {
				bReturn = false;
			}
		}

		if (bReturn) {

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
		}

		if (bReturn) {

			// bLogInfo(0,lResult.getConfigMap().size());

			MStringMap mConfigMap = lResult.getConfigMap();

			for (String sName : mConfigMap.keySet()) {
				TopDir topDir = new TopDir();

				String sSavePath = topDir
						.upCustomPath(TopConst.CONST_TOP_CUSTOM_CONFIG_PATH
								+ sName);

				try {
					
					
					FileUtils.writeStringToFile(new File(sSavePath),
							mConfigMap.get(sName),
							TopConst.CONST_BASE_ENCODING);
				} catch (IOException e) {
					bReturn = false;
					bLogError(970212013, sSavePath);
					e.printStackTrace();
				}
			}

		}

		// apiCallSupport.doCallApi(sAddress, sTarget, sApiKey, sApiPass, input,
		// tResult)

		return bReturn;
	}

	public void update(Observable o, Object arg) {

		doUpdateConfig();

	}

}
