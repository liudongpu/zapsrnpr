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

	/* (non-Javadoc)
	 * @see com.srnpr.zapcom.baseface.IBaseCache#refresh()
	 */
	public synchronized void refresh() {
		TopDir topDir = new TopDir();
		String sTempConfigString = topDir.upTempDir("info");
		bLogDebug(0, "refresh " + sTempConfigString);
		IoHelper ioHelper = new IoHelper();
		ioHelper.copyResources(
				"classpath*:META-INF/zapsrnpr/info/*.properties",
				sTempConfigString,"/zapsrnpr/info/");
		LoadProperties loadProperties = new LoadProperties();
		MStringMap mStringMap = loadProperties.loadMap(sTempConfigString);
		for (String s : mStringMap.upKeys()) {

			Long lKey=Long.parseLong(s);
			
			if(containsKey(lKey))
			{
				bLogError(0, "key ["+lKey.toString()+"] exist more then two");
			}
			
			this.inElement(Long.parseLong(s), mStringMap.get(s));
		}

	}

	@Override
	public String upOne(Long k) {
		
		return null;
	}

}
