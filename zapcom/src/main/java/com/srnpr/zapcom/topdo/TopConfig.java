package com.srnpr.zapcom.topdo;

import java.util.Enumeration;

import net.sf.ehcache.Element;

import com.srnpr.zapcom.baseface.IBaseCache;
import com.srnpr.zapcom.basehelper.IoHelper;
import com.srnpr.zapcom.basemodel.MStringMap;
import com.srnpr.zapcom.rootclass.RootCache;
import com.srnpr.zapcom.topcall.LoadProperties;

/**
 * @author srnpr 初始化加载配置
 */
class TopConfig extends RootCache<String, String> implements IBaseCache {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapcom.baseface.IBaseCache#refresh()
	 */

	public synchronized void refresh() {

		TopDir topDir = new TopDir();
		String sTempConfigString = topDir.upTempDir("config");
		// topDir.upZapDir();
		bLogInfo(0, "refresh " + sTempConfigString);
		IoHelper ioHelper = new IoHelper();
		ioHelper.copyResources(
				"classpath*:META-INF/zapsrnpr/config/*.properties",
				sTempConfigString, "/zapsrnpr/config/");
		LoadProperties loadProperties = new LoadProperties();

		// 开始读取配置
		{
			MStringMap mStringMap = loadProperties.loadMap(sTempConfigString);

			for (String s : mStringMap.upKeys()) {
				this.inElement(s, mStringMap.get(s));
			}
		}

		// 开始扫描扩展自定义的设置
		{
			String sCustom = topDir.upCustomPath("config/");
			bLogInfo(0, "scan custom config " + sCustom + "");

			MStringMap mCustomMap = loadProperties.loadMap(sCustom);

			if (mCustomMap.size() == 0) {
				bLogWarn(0, "scan custom config  not exist");
			} else {

				for (String s : mCustomMap.upKeys()) {
					this.inElement(s, mCustomMap.get(s));
				}
			}
		}
	}

	@Override
	public String upOne(String k) {

		return null;
	}

}
