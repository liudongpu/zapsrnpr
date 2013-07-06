package com.srnpr.zapcom.topdo;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.IoHelper;
import com.srnpr.zapcom.basemodel.MStringMap;
import com.srnpr.zapcom.rootclass.RootCache;

public class TopInfo extends RootCache<Long, String>  {

	/* (non-Javadoc)
	 * @see com.srnpr.zapcom.rootclass.RootCache#refresh()
	 */
	@Override
	public synchronized void refresh() {
		TopDir topDir=new TopDir();
		String sTempConfigString= topDir.upTempDir("config");
		//topDir.upZapDir();
		bLog("refresh");
		IoHelper ioHelper=new IoHelper();
		ioHelper.copyResources("classpath*:META-INF/zapsrnpr/config/*.properties", sTempConfigString, "/config/");
		MStringMap mStringMap=new MStringMap();
		for(String s:mStringMap.upKeys())
		{
			
			this.inElement(Long.parseLong(s), mStringMap.get(s));
		}
		
	}

	
	
	
}
