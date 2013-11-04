package com.srnpr.zapweb.webapi;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basemodel.MStringMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapcom.topapi.RootInput;
import com.srnpr.zapcom.topcall.LoadProperties;
import com.srnpr.zapcom.topdo.TopDir;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webfactory.WebLogFactory;

/**
 * 加载leader的配置文件 各个follow会自己同步更新自己的配置
 * 
 * @author srnpr
 * 
 */
public class LeaderConfig extends RootApi<LeaderConfigResult, RootInput> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapcom.baseface.IBaseApi#Process(java.lang.Object,
	 * com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public LeaderConfigResult Process(RootInput inputParam, MDataMap mRequestMap) {

		LeaderConfigResult leaderConfigResult = new LeaderConfigResult();

		TopDir topDir = new TopDir();

		File file = new File(topDir.upCustomPath("config")
				+ WebConst.CONST_CONFIG_FILE_LEADER);

		LoadProperties load = new LoadProperties();

		Collection<File> cFiles = new ArrayList<File>();

		if (file.exists()) {
			cFiles.add(file);
		}

		MStringMap map = load.loadMapFromFiles(cFiles);

		leaderConfigResult.setConfigMap(map);

		return leaderConfigResult;

	}

}
