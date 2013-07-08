package com.srnpr.zapdata.dbdo;

import com.srnpr.zapcom.baseface.IBaseUp;
import com.srnpr.zapdata.dbcache.DbCache;
import com.srnpr.zapdata.dbcache.TableCache;
import com.srnpr.zapdata.dbface.ITableCall;


public class DbUp implements IBaseUp {

	

	private final static TableCache tableCache=new TableCache();
	
	public static ITableCall upTable(String sTaleName)
	{
		return tableCache.upValue(sTaleName);
	}
	
	
}
