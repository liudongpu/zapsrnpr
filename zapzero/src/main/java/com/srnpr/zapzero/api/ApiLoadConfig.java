package com.srnpr.zapzero.api;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basemodel.MStringMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapcom.topcache.CacheTempConfigStringMap;
import com.srnpr.zapcom.topdo.TopConst;
import com.srnpr.zapcom.topdo.TopDir;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapzero.server.ServerInfo;

/**
 * 读取配置文件返回给客户端
 * 
 * @author srnpr
 * 
 */
public class ApiLoadConfig extends RootApi<ApiLoadConfigResult, ServerInfo> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapcom.baseface.IBaseApi#Process(java.lang.Object,
	 * com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public ApiLoadConfigResult Process(ServerInfo inputParam,
			MDataMap mRequestMap) {
		ApiLoadConfigResult leaderConfigResult = new ApiLoadConfigResult();

		TopDir topDir = new TopDir();

		String sFile = inputParam.getSyncConfig();
		MStringMap map = new MStringMap();

		if (StringUtils.isNotBlank(sFile)) {

			String sKeyString = CacheTempConfigStringMap
					.upTempCacheName("com.srnpr.zapzero.api.ApiLoadConfig.Process.configfile"
							+ sFile);

			if (!CacheTempConfigStringMap.getInstance().containsKey(sKeyString)
					|| CacheTempConfigStringMap.getInstance().upValue(
							sKeyString) == null) {
				bLogInfo(0, sFile);
				for (String s : sFile.split(",")) {

					String sDirName = "config";
					String sFileNameString = s;
					if (sFileNameString.indexOf("/") > -1) {
						sDirName = StringUtils.substringBeforeLast(
								sFileNameString, "/");
						sFileNameString = StringUtils.substringAfterLast(
								sFileNameString, "/");
					}

					File file = new File(topDir.upCustomPath(sDirName + "/")
							+ sFileNameString);

					if (file.exists()) {

						try {
							map.put(sFileNameString, FileUtils
									.readFileToString(file,
											TopConst.CONST_BASE_ENCODING));
						} catch (IOException e) {

							e.printStackTrace();
						}

					} else {
						bLogInfo(969905080, sDirName + "/" + sFileNameString);
					}

				}
				// map = load.loadMapFromFiles(cFiles);
				CacheTempConfigStringMap.getInstance().inElement(sKeyString,
						map);
			} else {
				// bLogInfo(0, map);
				map = CacheTempConfigStringMap.getInstance()
						.upValue(sKeyString);
			}

		}

		leaderConfigResult.setConfigMap(map);

		addKeepToDB(inputParam);

		return leaderConfigResult;
	}

	/**
	 * 初始化信息到数据库
	 * @param inputParam
	 */
	public void addKeepToDB(ServerInfo inputParam) {
		// 写入信息到数据库中
		{
			MDataMap mLiveKeepDataMap = DbUp.upTable("za_livekeep").one(
					"leader_code", ServerInfo.INSTANCE.getServerCode(),
					"follower_code", inputParam.getServerCode(), "flag_delete",
					"0");
			// 如果已有则更新 否则新增一条
			if (mLiveKeepDataMap != null && mLiveKeepDataMap.size() > 0) {

				mLiveKeepDataMap.put("connect_time", FormatHelper.upDateTime());
				DbUp.upTable("za_livekeep").dataUpdate(mLiveKeepDataMap,
						"connect_time", "zid");

			} else {
				String sDate = FormatHelper.upDateTime();
				MDataMap mInserMap = new MDataMap();
				mInserMap.put("leader_code",
						ServerInfo.INSTANCE.getServerCode());
				mInserMap.put("follower_code", inputParam.getServerCode());
				mInserMap.put("follower_address", inputParam.getIpAddress());
				mInserMap.put("run_type", inputParam.getRunType());
				mInserMap.put("follower_runlist", inputParam.getRunList());

				mInserMap.put("create_time", sDate);
				mInserMap.put("update_time", sDate);
				mInserMap.put("flag_enable", "1");
				mInserMap.put("flag_delete", "0");
				mInserMap.put("connect_time", sDate);
				DbUp.upTable("za_livekeep").dataInsert(mInserMap);

			}

		}
	}

}
