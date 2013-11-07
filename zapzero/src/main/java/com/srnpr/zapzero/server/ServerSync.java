package com.srnpr.zapzero.server;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.net.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basemodel.MStringMap;
import com.srnpr.zapcom.basesupport.JobSupport;
import com.srnpr.zapcom.topdo.TopConfig;
import com.srnpr.zapcom.topdo.TopConst;
import com.srnpr.zapcom.topdo.TopDir;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.websupport.ApiCallSupport;
import com.srnpr.zapzero.api.ApiKeepLiveInput;
import com.srnpr.zapzero.api.ApiLoadConfigResult;

public class ServerSync extends BaseClass {

	public void init() {
		// ServerInfo.INSTANCE.setIpAddress(ipAddress);
	}

	/**
	 * 初始化任务加载
	 * 
	 * @return
	 */
	public boolean initJob() {

		// DbUp.upTable("za_job").queryByWhere("")

		String sRunList = bConfig("default.local_run_list");

		if (StringUtils.isNotBlank(sRunList)) {

			MDataMap mapDefine = new MDataMap();
			mapDefine.put("parent_did", "46991807");

			List<String> lDids = new ArrayList<String>();
			for (MDataMap mDefineDataMap : DbUp.upTable("zw_define").queryIn(
					"define_dids", "", "", mapDefine, -1, -1, "define_name",
					sRunList)) {
				lDids.add(mDefineDataMap.get("define_dids"));
			}

			MDataMap mJoQuerybMap = new MDataMap();
			mJoQuerybMap.put("parent_did", "46991807");
			for (MDataMap mJob : DbUp.upTable("za_job").queryIn("", "", "",
					mJoQuerybMap, -1, -1, "run_group_did",
					StringUtils.join(lDids, ","))) {

				JobSupport.getInstance().addJob(mJob.get("job_class"),
						mJob.get("job_triger"), mJob.get("uid"));

				bLogInfo(970212016, mJob.get("job_title"),
						mJob.get("job_triger"));

			}
		}

		return true;
	}

	/**
	 * 初始化运行模式
	 * 
	 * @return
	 */
	public boolean initServer() {

		boolean bFlagReturn = true;

		// 初始化基本信息
		if (bFlagReturn) {
			String sServerType = bConfig("default.local_run_type");

			String sServerCode = bConfig("default.local_server_code");

			ServerInfo.INSTANCE.setIpAddress(getIpAddress().getHostAddress());

			// 判断如果没有定义servercode则自动使用ip地址作为servercode
			if (StringUtils.isBlank(sServerCode)
					|| sServerCode.equals("default")) {
				sServerCode = ServerInfo.INSTANCE.getIpAddress();
			}

			ServerInfo.INSTANCE.setServerCode(sServerCode);

			ServerInfo.INSTANCE.setRunType(sServerType);

			bLogInfo(970212010, sServerType);

		}

		// 如果加载的是leader 则标记历史信息以做备份用
		if (bFlagReturn) {

			MDataMap mUpdateMap = new MDataMap();
			mUpdateMap.put("leader_code", ServerInfo.INSTANCE.getServerCode());
			mUpdateMap.put("flag_delete", "1");
			mUpdateMap.put("delete_time", FormatHelper.upDateTime());

			DbUp.upTable("za_livekeep").dataUpdate(mUpdateMap,
					"flag_delete,delete_time", "leader_code");
		}

		// 如果加载的是跟随者 则开始连接主服务器的配置
		if (bFlagReturn) {
			if (ServerInfo.INSTANCE.getRunType().equals("follower")) {

				// ConfigObservable.INSTANCE.addObserver(this);

				bFlagReturn = doUpdateConfig();

				if (!bFlagReturn) {
					bLogInfo(970212012);
				}
			}

		}

		if (bFlagReturn) {
			bFlagReturn = initJob();
		}

		return bFlagReturn;

	}

	/**
	 * 更新配置
	 * 
	 * @return
	 */
	/**
	 * @return
	 */
	private boolean doUpdateConfig() {

		boolean bReturn = true;

		// 调用主服务器
		ApiCallSupport<ApiKeepLiveInput, ApiLoadConfigResult> apiCallSupport = new ApiCallSupport<ApiKeepLiveInput, ApiLoadConfigResult>();

		ApiLoadConfigResult lResult = new ApiLoadConfigResult();

		String[] sMaserServer = bConfig("default.leader_server_address").split(
				",");

		// sInput.setInputString(bConfig("default.follower_load_config"));

		ServerInfo.INSTANCE
				.setSyncConfig(bConfig("default.follower_load_config"));

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
							"com_srnpr_zapzero_api_ApiLoadConfig",
							bConfig("default.leader_server_apikey"),
							bConfig("default.leader_server_apipass"),
							ServerInfo.INSTANCE, lResult);
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

		// 将配置文件写入到config文件夹
		if (bReturn) {

			// bLogInfo(0,lResult.getConfigMap().size());

			MStringMap mConfigMap = lResult.getConfigMap();

			for (String sName : mConfigMap.keySet()) {
				TopDir topDir = new TopDir();

				String sSavePath = topDir
						.upCustomPath(TopConst.CONST_TOP_CUSTOM_CONFIG_PATH
								+ sName);

				try {

					FileUtils
							.writeStringToFile(new File(sSavePath),
									mConfigMap.get(sName),
									TopConst.CONST_BASE_ENCODING);
					bLogInfo(970212014, sName);
				} catch (IOException e) {
					bReturn = false;
					bLogError(970212013, sSavePath);
					e.printStackTrace();
				}
			}

		}

		// 重新刷新配置文件
		TopConfig.Instance.refresh();

		bLogDebug(970212015, bConfig("all.version"));

		// apiCallSupport.doCallApi(sAddress, sTarget, sApiKey, sApiPass, input,
		// tResult)

		return bReturn;
	}

	/**
	 * Get host IP address
	 * 
	 * @return IP Address
	 */
	public InetAddress getIpAddress() {
		try {
			for (Enumeration<NetworkInterface> interfaces = NetworkInterface
					.getNetworkInterfaces(); interfaces.hasMoreElements();) {
				NetworkInterface networkInterface = interfaces.nextElement();
				if (networkInterface.isLoopback()
						|| networkInterface.isVirtual()
						|| !networkInterface.isUp()) {
					continue;
				}
				Enumeration<InetAddress> addresses = networkInterface
						.getInetAddresses();
				if (addresses.hasMoreElements()) {
					return addresses.nextElement();
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return null;
	}

}
