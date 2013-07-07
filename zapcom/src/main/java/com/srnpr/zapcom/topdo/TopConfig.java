package com.srnpr.zapcom.topdo;

import java.util.Enumeration;

import net.sf.ehcache.Element;

import com.srnpr.zapcom.baseface.IBaseCache;
import com.srnpr.zapcom.basehelper.IoHelper;
import com.srnpr.zapcom.basemodel.MStringMap;
import com.srnpr.zapcom.rootclass.RootCache;
import com.srnpr.zapcom.topcall.LoadProperties;

/**
 * @author srnpr
 * 初始化加载配置
 */
 class TopConfig extends RootCache<String,String> implements IBaseCache {


	
	/* (non-Javadoc)
	 * @see com.srnpr.zapcom.baseface.IBaseCache#refresh()
	 */
	@Override
	public synchronized void refresh() {
		
		TopDir topDir=new TopDir();
		String sTempConfigString= topDir.upTempDir("config");
		//topDir.upZapDir();
		bLog(0,"refresh "+sTempConfigString);
		IoHelper ioHelper=new IoHelper();
		ioHelper.copyResources("classpath*:META-INF/zapsrnpr/config/*.properties", sTempConfigString);
		LoadProperties loadProperties=new LoadProperties();
		
		
		MStringMap mStringMap=loadProperties.loadMap(sTempConfigString);
		
	
		for(String s:mStringMap.upKeys())
		{
			this.inElement(s, mStringMap.get(s));
		}
		
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
}
