package com.srnpr.zapcom.topdo;

import com.srnpr.zapcom.baseface.IBaseCache;
import com.srnpr.zapcom.rootclass.RootCache;

/**
 * @author srnpr
 * 初始化加载配置
 */
public class TopConfig extends RootCache<String,String> implements IBaseCache {


	
	/* (non-Javadoc)
	 * @see com.srnpr.zapcom.baseface.IBaseCache#refresh()
	 */
	public void refresh() {
		
		
		
		TopDir topDir=new TopDir();
		
		
		//topDir.upZapDir();
		
		BInfo("refresh");
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
}
