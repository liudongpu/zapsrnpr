package com.srnpr.zapdata.dbcache;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.rootclass.RootCache;
import com.srnpr.zapdata.dbdo.DataConst;
import com.srnpr.zapdata.dbdo.DataEnumerConnType;
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

			Map<String, Object> mData = null;

			//判断连接类型
			if (StringUtils.startsWith(k, DataConst.CONST_DATA_SLAVE_NAME)) {
				mData = upConnInfo(StringUtils.substringAfter(k,
						DataConst.CONST_DATA_SLAVE_NAME),
						DataEnumerConnType.CodeSlave);
			} else if (DataConst.CONST_DATA_RUN_TYPE == 2) {
				mData = upConnInfo(k, DataEnumerConnType.ReplivationDriver);
			} else {
				mData = upConnInfo(k, DataEnumerConnType.BaseConn);
			}

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
	 * @param sServerName
	 * @param dataEnumerConnType
	 * @return
	 */
	public Map<String, Object> upConnInfo(String sServerName,
			DataEnumerConnType dataEnumerConnType) {
		DataInit dbInit = new DataInit();
		dbInit.init();

		String sTypeDid = "104020013";

		switch (dataEnumerConnType) {
		case CodeSlave:
			sTypeDid = "104020019";
			break;
		case ReplivationDriver:
			sTypeDid = "104020004";
			break;

		default:
			sTypeDid = "104020013";
			break;
		}

		Map<String, Object> mData = upValue(
				dbInit.bConfig("zapdata.base_jdbc_name"))
				.queryForMap(
						"select * from "
								+ dbInit.bConfig("zapdata.base_jdbc_table")
								+ " where server_name=:server_name and server_type_aid="
								+ sTypeDid,
						new MDataMap("server_name", sServerName));

		return mData;
	}

}
