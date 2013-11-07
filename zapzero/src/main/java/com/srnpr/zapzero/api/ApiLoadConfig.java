package com.srnpr.zapzero.api;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basemodel.MStringMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapcom.topcache.CacheTempConfigStringMap;
import com.srnpr.zapcom.topdo.TopConst;
import com.srnpr.zapcom.topdo.TopDir;
import com.srnpr.zapzero.server.ServerInfo;

public class ApiLoadConfig extends RootApi<ApiLoadConfigResult, ServerInfo> {

	public ApiLoadConfigResult Process(ServerInfo inputParam,
			MDataMap mRequestMap) {
		ApiLoadConfigResult leaderConfigResult = new ApiLoadConfigResult();

		TopDir topDir = new TopDir();

		String sFile = inputParam.getServerCode();
		MStringMap map = new MStringMap();

		if (StringUtils.isNotBlank(sFile)) {

			String sKeyString = CacheTempConfigStringMap
					.upTempCacheName("com.srnpr.zapweb.webapi.LeaderConfig.Process.configfile"
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
				//bLogInfo(0, map);
				map = CacheTempConfigStringMap.getInstance()
						.upValue(sKeyString);
			}

		}

		leaderConfigResult.setConfigMap(map);

		return leaderConfigResult;
	}

}
