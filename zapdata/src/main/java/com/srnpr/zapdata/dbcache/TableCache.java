package com.srnpr.zapdata.dbcache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.srnpr.zapcom.basemodel.MSoMap;
import com.srnpr.zapcom.basemodel.MStringMap;
import com.srnpr.zapcom.rootclass.RootCache;
import com.srnpr.zapdata.dbcache.DataInit;
import com.srnpr.zapdata.dbcache.DbCache;
import com.srnpr.zapdata.dbface.ITableCall;
import com.srnpr.zapdata.dbsupport.DbTemplate;
import com.sun.org.apache.xml.internal.security.Init;

public class TableCache extends RootCache<String, ITableCall> {

	public void refresh() {

		DataInit dbInit = new DataInit();
		dbInit.init();

		DbTemplate dTemplate = new DbTemplate(dbInit.upDataSource(
				dbInit.bConfig("zapdata.base_jdbc_drive"),
				dbInit.bConfig("zapdata.base_jdbc_dburl"),
				dbInit.bConfig("zapdata.base_jdbc_user"),
				dbInit.bConfig("zapdata.base_jdbc_password")));

		//List<Map<String, Object>> lBaseDataList=dTemplate.queryForList("select * from "+dbInit.bConfig("zapdata.base_jdbc_table"), new MStringMap());
		
		ConcurrentHashMap<String, DbTemplate> cMap=new ConcurrentHashMap<String, DbTemplate>();
		
		
		for(Map<String, Object> mData:dTemplate.queryForList("select * from "+dbInit.bConfig("zapdata.base_jdbc_table"), new MStringMap()))
		{
			DbTemplate dServer = new DbTemplate(dbInit.upDataSource(
					mData.get("jdbc_driver").toString(),
					mData.get("jdbc_dburl").toString(),
					mData.get("jdbc_user").toString(),
					mData.get("jdbc_password").toString()));
			cMap.put(mData.get("server_name").toString(), dServer);
		}
		
		
		
		
		
		
		
	
	
		
		
		
		
		
		
		
		
		
		

	}

}
