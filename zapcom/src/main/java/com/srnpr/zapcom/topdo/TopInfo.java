package com.srnpr.zapcom.topdo;

import com.srnpr.zapcom.basehelper.IoHelper;
import com.srnpr.zapcom.basemodel.MStringMap;
import com.srnpr.zapcom.rootclass.RootCache;
import com.srnpr.zapcom.topcall.LoadProperties;

/**
 * 获取顶级配置消息
 * 
 * @author srnpr
 * 
 */
class TopInfo extends RootCache<Long, String> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapcom.rootclass.RootCache#refresh()
	 */
	@Override
	public synchronized void refresh() {
		TopDir topDir = new TopDir();
		String sTempConfigString = topDir.upTempDir("info");
		bLog(0, "refresh " + sTempConfigString);
		IoHelper ioHelper = new IoHelper();
		ioHelper.copyResources(
				"classpath*:META-INF/zapsrnpr/info/*.properties",
				sTempConfigString);
		LoadProperties loadProperties = new LoadProperties();
		MStringMap mStringMap = loadProperties.loadMap(sTempConfigString);
		for (String s : mStringMap.upKeys()) {

			this.inElement(Long.parseLong(s), mStringMap.get(s));
		}

	}

}
