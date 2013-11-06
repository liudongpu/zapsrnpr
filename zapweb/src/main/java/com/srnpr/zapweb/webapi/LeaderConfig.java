package com.srnpr.zapweb.webapi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basemodel.MStringMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapcom.topapi.RootInput;
import com.srnpr.zapcom.topcache.CacheTempConfigStringMap;
import com.srnpr.zapcom.topcall.LoadProperties;
import com.srnpr.zapcom.topdo.TopConst;
import com.srnpr.zapcom.topdo.TopDir;
import com.srnpr.zapweb.webdo.ObjectCache;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webfactory.WebLogFactory;

/**
 * 加载leader的配置文件 各个follow同步该服务器更新自己的配置
 * 
 * @author srnpr
 * 
 */
public class LeaderConfig extends RootApi<LeaderConfigResult, SimpleApiInput> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapcom.baseface.IBaseApi#Process(java.lang.Object,
	 * com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public LeaderConfigResult Process(SimpleApiInput inputParam,
			MDataMap mRequestMap) {

		LeaderConfigResult leaderConfigResult = new LeaderConfigResult();

		TopDir topDir = new TopDir();

		String sFile = inputParam.getInputString();
		MStringMap map = new MStringMap();

		if (StringUtils.isNotBlank(sFile)) {

			String sKeyString = CacheTempConfigStringMap
					.upTempCacheName("com.srnpr.zapweb.webapi.LeaderConfig.Process.configfile"
							+ sFile);

			if (!CacheTempConfigStringMap.getInstance().containsKey(sKeyString)
					|| CacheTempConfigStringMap.getInstance().upValue(
							sKeyString) == null) {
				bLogInfo(0,sFile);
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
							map.put(s, FileUtils.readFileToString(file,
									TopConst.CONST_BASE_ENCODING));
						} catch (IOException e) {

							e.printStackTrace();
						}

					} else {
						bLogInfo(969905080,sDirName+"/"+sFileNameString);
					}

				}
				// map = load.loadMapFromFiles(cFiles);
				CacheTempConfigStringMap.getInstance().inElement(sKeyString,
						map);
			} else {
				bLogInfo(0,map);
				map = CacheTempConfigStringMap.getInstance().upValue(sKeyString);
			}

		}

		leaderConfigResult.setConfigMap(map);

		return leaderConfigResult;

	}
}
