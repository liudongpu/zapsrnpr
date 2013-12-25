package com.srnpr.zapdata.dbcache;

import java.util.Map;

import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.rootclass.RootCache;
import com.srnpr.zapdata.dbsupport.DbTemplate;

public class ConnCache extends RootCache<String, DbTemplate> implements
		IBaseInstance {

	public static ConnCache INSTANCE = new ConnCache();

	public void refresh() {

	}

	@Override
	public DbTemplate upOne(String k) {

		DbTemplate dTemplate = null;

		DataInit dbInit = new DataInit();
		dbInit.init();

		// 加载默认配置数据库
		if (k.equals(dbInit.bConfig("zapdata.base_jdbc_name"))) {
			dTemplate = new DbTemplate(dbInit.upDataSource(
					dbInit.bConfig("zapdata.base_jdbc_drive"),
					dbInit.bConfig("zapdata.base_jdbc_dburl"),
					dbInit.bConfig("zapdata.base_jdbc_user"),
					dbInit.bConfig("zapdata.base_jdbc_password")));

			inElement(k, dTemplate);

			dbInit.bLogInfo(967912001, k);

		} else {

			// 加载各个操作序列
			Map<String, Object> mData = upConnInfo(k, 2);
			if (mData != null) {
				dTemplate = new DbTemplate(dbInit.upDataSource(
						mData.get("jdbc_driver").toString(),
						mData.get("jdbc_dburl").toString(),
						mData.get("jdbc_user").toString(),
						mData.get("jdbc_password").toString()));

				inElement(k, dTemplate);

				dbInit.bLogInfo(967912001, k);
			} else {
				bLogError(969905001, k);
			}
		}

		return dTemplate;

	}

	/**
	 * 获取连接信息
	 * 
	 * @param sServerName
	 * @param iConnType
	 *            连接类型 1为conncache获取的连接 2为分布式事务获取的连接
	 * @return
	 */
	public Map<String, Object> upConnInfo(String sServerName, int iConnType) {
		DataInit dbInit = new DataInit();
		dbInit.init();
		Map<String, Object> mData = upValue(
				dbInit.bConfig("zapdata.base_jdbc_name")).queryForMap(
				"select * from " + dbInit.bConfig("zapdata.base_jdbc_table")
						+ " where server_name=:server_name",
				new MDataMap("server_name", sServerName));

		return mData;
	}

}
