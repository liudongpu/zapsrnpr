package com.srnpr.zapdata.spring;

import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topdo.TopUp;
import com.srnpr.zapdata.dbcache.ConnCache;
import com.srnpr.zapdata.dbdo.DataEnumerConnType;
import com.srnpr.zapdata.dbdo.DbUp;

/**
 * 数据源bean 该类用于配制spring中的数据源
 * 
 * @author srnpr
 * 
 */
public class XaDataSourceBean extends AtomikosDataSourceBean {

	private static final long serialVersionUID = 1L;

	/**
	 * 数据源名称 该数据源对应为数据库中zd_server的数据库连接
	 */
	private String serverName = "";

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
		Properties properties = new Properties();

		/*
		 * properties.put("URL",
		 * "jdbc:mysql://192.168.1.221:3306/zapdata?useUnicode=true&amp;characterEncoding=utf-8"
		 * ); properties.put("user", "zjwc"); properties.put("password",
		 * "ssssss");
		 */

		Map<String, Object> mDataMap = ConnCache.INSTANCE.upConnInfo(
				this.serverName, DataEnumerConnType.TxConn);

		String sUrlString = mDataMap.get("jdbc_dburl").toString();
		String sUserString = mDataMap.get("jdbc_user").toString();
		String sPasswodsString = mDataMap.get("jdbc_password").toString();

		properties.put("URL", sUrlString);
		properties.put("user", sUserString);
		properties.put("password", sPasswodsString);

		if (StringUtils.isEmpty(this.getUniqueResourceName())) {
			this.setUniqueResourceName(this.serverName);
		}

		this.setXaProperties(properties);
	}

}
