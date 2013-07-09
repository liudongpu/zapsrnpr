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
import com.srnpr.zapdata.dbsupport.MysqlCall;
import com.sun.org.apache.xml.internal.security.Init;

/**
 * @author srnpr
 * 
 */
public class TableCache extends RootCache<String, ITableCall> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapcom.baseface.IBaseCache#refresh()
	 */
	public void refresh() {

		DataInit dbInit = new DataInit();
		dbInit.init();

		// 加载默认配置数据库
		DbTemplate dTemplate = new DbTemplate(dbInit.upDataSource(
				dbInit.bConfig("zapdata.base_jdbc_drive"),
				dbInit.bConfig("zapdata.base_jdbc_dburl"),
				dbInit.bConfig("zapdata.base_jdbc_user"),
				dbInit.bConfig("zapdata.base_jdbc_password")));

		ConcurrentHashMap<String, DbTemplate> cTemplateMap = new ConcurrentHashMap<String, DbTemplate>();
		// 加载各个操作序列
		for (Map<String, Object> mData : dTemplate.queryForList(
				"select * from " + dbInit.bConfig("zapdata.base_jdbc_table"),
				new MStringMap())) {
			DbTemplate dServer = new DbTemplate(dbInit.upDataSource(
					mData.get("jdbc_driver").toString(), mData
							.get("jdbc_dburl").toString(),
					mData.get("jdbc_user").toString(),
					mData.get("jdbc_password").toString()));
			cTemplateMap.put(mData.get("server_name").toString(), dServer);
		}

		// 加载并缓存各个表操作
		for (Map<String, Object> mData : dTemplate.queryForList(
				"select * from " + dbInit.bConfig("zapdata.base_table_name"),
				new MStringMap())) {
			ITableCall mysqlCall = new MysqlCall(cTemplateMap.get(mData
					.get("server_code")), mData.get("table_name").toString());

			inElement(mData.get("table_name").toString(), mysqlCall);
		}

	}

}
