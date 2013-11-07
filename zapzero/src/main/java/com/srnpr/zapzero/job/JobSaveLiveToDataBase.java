package com.srnpr.zapzero.job;

import org.quartz.JobExecutionContext;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.rootclass.RootJob;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapzero.api.ApiKeepLiveInput;
import com.srnpr.zapzero.cache.CacheKeepLive;
import com.srnpr.zapzero.server.ServerInfo;

/**
 * 更新心跳信息到数据库 并判断是否有follower掉线
 * 
 * @author srnpr
 * 
 */
public class JobSaveLiveToDataBase extends RootJob {

	public void doExecute(JobExecutionContext context) {
		// TODO Auto-generated method stub

		String sLeaderCode = ServerInfo.INSTANCE.getServerCode();

		String sDateTime = FormatHelper.upDateTime();

		// 将自身信息写入到存活表
		CacheKeepLive.getInstance().inElement(sLeaderCode, ServerInfo.INSTANCE);

		// 循环写入信息到存活表
		for (String sKey : CacheKeepLive.getInstance().upKeys()) {

			ApiKeepLiveInput aInfo = CacheKeepLive.getInstance().upValue(sKey);

			MDataMap map = new MDataMap();

			map.put("leader_code", sLeaderCode);
			map.put("follower_code", aInfo.getServerCode());
			map.put("flag_delete", "0");

			map.put("update_time", sDateTime);
			map.put("keep_time", aInfo.getNoticeTime());

			DbUp.upTable("za_livekeep").dataUpdate(map,
					"update_time,keep_time",
					"leader_code,follower_code,flag_delete");

		}

	}
}
