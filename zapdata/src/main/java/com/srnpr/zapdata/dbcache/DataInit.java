package com.srnpr.zapdata.dbcache;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseInit;
import com.srnpr.zapdata.dbdo.DataConst;

public class DataInit extends BaseClass implements IBaseInit {

	public boolean init() {

		return true;
	}

	public ComboPooledDataSource upDataSource(String sDrive, String sUrl,
			String sUser, String sPass) {
		ComboPooledDataSource cm = new ComboPooledDataSource();


		try {
		
			
			cm.setDriverClass(sDrive);
			cm.setJdbcUrl(sUrl);
			cm.setUser(sUser);
			cm.setPassword(sPass);
			//设置连接池的测试连接
			cm.setIdleConnectionTestPeriod(120);
			//最小连接数
			cm.setMinPoolSize(1);
			//初始化时初始的连接数
			cm.setInitialPoolSize(1);
		
			//<!--当连接池中的连接耗尽的时候c3p0一次同时获取的连接数。Default: 3 -->
			//cm.setAcquireIncrement(1);
			//最大空闲时间,60秒内未使用则连接被丢弃。若为0则永不丢弃。Default: 0
			cm.setMaxIdleTime(60);
			
			//最大连接数
			cm.setMaxPoolSize(DataConst.CONST_DATA_INIT_MAX_POOL);
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cm;
	}

}
