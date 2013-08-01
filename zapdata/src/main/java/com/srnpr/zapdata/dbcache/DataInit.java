package com.srnpr.zapdata.dbcache;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseInit;

public class DataInit extends BaseClass implements IBaseInit {

	public void init() {

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
			cm.setMinPoolSize(5);
			cm.setMaxPoolSize(100);
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cm;
	}

}
