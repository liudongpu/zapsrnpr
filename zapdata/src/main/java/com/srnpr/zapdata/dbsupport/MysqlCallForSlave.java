package com.srnpr.zapdata.dbsupport;

import java.util.List;
import java.util.Map;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbcache.ConnCache;
import com.srnpr.zapdata.dbdo.DataConst;

/**
 * 读写分离模型
 * @author srnpr
 *
 */
public class MysqlCallForSlave extends MysqlCall {

	private String dataBaseName = null;

	/**
	 * 表名
	 */
	private String dataTableName = null;

	/**
	 * 从库数据源 该数据源为只读数据源
	 */
	private DbTemplate slaveTemplate = null;

	public MysqlCallForSlave(DbTemplate dBase, String sDataBaseName,
			String sTableName) {

		super(dBase, sTableName);

		dataBaseName = sDataBaseName;
		dataTableName = sTableName;
	}

	/**
	 * 获取从库驱动模型
	 * 
	 * @return
	 */
	private DbTemplate upSlaveTemplate() {
		if (slaveTemplate == null || slaveTemplate.getFlagEnable() == 0) {

			slaveTemplate = ConnCache.INSTANCE
					.upValue(DataConst.CONST_DATA_SLAVE_NAME + dataBaseName);

			// 判断如果从库为空 则开始读取主库数据
			if (slaveTemplate == null || slaveTemplate.getFlagEnable() == 0) {

				bLogInfo(968005004, dataBaseName);

				slaveTemplate = upTemplate();
			}

		}

		return slaveTemplate;
	}

	public List<Map<String, Object>> dataSqlList(String sSql, MDataMap mWhereMap) {

		List<Map<String, Object>> listReturnMaps = null;

		if (DataConst.CONST_DATA_RUN_TYPE == 1) {

			try {
				listReturnMaps = upSlaveTemplate()
						.queryForList(sSql, mWhereMap);
			} catch (Exception e) {
				bLogError(968005003, dataBaseName, dataTableName);
				ConnCache.INSTANCE.upValue(
						DataConst.CONST_DATA_SLAVE_NAME + dataBaseName)
						.setFlagEnable(0);
				slaveTemplate = upTemplate();
				listReturnMaps = upSlaveTemplate()
						.queryForList(sSql, mWhereMap);
			}
		} else {
			listReturnMaps = upTemplate().queryForList(sSql, mWhereMap);
		}

		return listReturnMaps;
	}

}
