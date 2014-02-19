package com.srnpr.zapdata.dbsupport;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbcache.ConnCache;
import com.srnpr.zapdata.dbdo.DataConst;

/**
 * Mysql调用
 * 
 * @author srnpr
 * 
 */
public class MysqlCall extends DbCall {

	/**
	 * 数据源
	 */
	private DbTemplate dataTemplate = null;

	/**
	 * 从库数据源 该数据源为只读数据源
	 */
	private DbTemplate slaveTemplate = null;

	/**
	 * @param dBase
	 *            数据源
	 * @param sTableName
	 *            表名称
	 */
	public MysqlCall(DbTemplate dBase, String sDataBaseName, String sTableName) {
		dataTemplate = dBase;

		dataBaseName = sDataBaseName;
		dataTableName = sTableName;
	}

	private String dataBaseName = null;

	/**
	 * 表名
	 */
	private String dataTableName = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapdata.dbface.ITableCall#queryList(java.lang.String,
	 * java.lang.String, java.lang.String, com.srnpr.zapcom.basemodel.MDataMap,
	 * int, int)
	 */
	public List<Map<String, Object>> dataQuery(String sFields, String sOrders,
			String sWhere, MDataMap mWhereMap, int iStart, int iNumber) {

		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("select ");

		// 加载字段
		if (StringUtils.isEmpty(sFields) || sFields.trim().equals("*")) {
			sBuffer.append("*");
		} else {
			sBuffer.append(sFields);
		}

		sBuffer.append(" from " + dataTableName);

		// 加载条件判断

		if (StringUtils.isNotEmpty(sWhere)) {
			sBuffer.append(" where " + sWhere);
		}

		else if (mWhereMap != null && mWhereMap.size() > 0) {
			sBuffer.append(" where "
					+ (StringUtils.isEmpty(sWhere) ? FormatHelper
							.joinWhereStrings(mWhereMap.upKeys()) : sWhere)
					+ " ");
		}

		if (StringUtils.isNotEmpty(sOrders)) {

			String[] sOrderStrings = sOrders.split(",");
			for (int i = 0, j = sOrderStrings.length; i < j; i++) {
				if (StringUtils.startsWith(sOrderStrings[i], "-")) {
					sOrderStrings[i] = StringUtils.substringAfter(
							sOrderStrings[i], "-") + " desc ";
				}
			}

			sBuffer.append(" order by " + StringUtils.join(sOrderStrings, ",")
					+ " ");

		}

		if (iStart > -1 && iNumber > 0) {
			sBuffer.append(" limit " + String.valueOf(iStart) + ","
					+ String.valueOf(iNumber));
		}

		return dataSqlList(sBuffer.toString(), mWhereMap);
	}

	public Map<String, Object> dataSqlOne(String sSql, MDataMap mWhereMap) {
		List<Map<String, Object>> listResult = dataSqlList(sSql, mWhereMap);

		if (listResult.size() > 0) {
			return listResult.get(0);
		} else {
			return null;
		}

	}

	private DbTemplate upSlaveTemplate() {
		if (slaveTemplate == null) {

			slaveTemplate = ConnCache.INSTANCE
					.upValue(DataConst.CONST_DATA_SLAVE_NAME + dataBaseName);

			// 判断如果从库为空 则开始读取主库数据
			if (slaveTemplate == null) {

				bLogInfo(968005004, dataBaseName);

				slaveTemplate = dataTemplate;
			}

		}

		return slaveTemplate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapdata.dbface.ITableCall#dataSqlList(java.lang.String,
	 * com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public List<Map<String, Object>> dataSqlList(String sSql, MDataMap mWhereMap) {

		List<Map<String, Object>> listReturnMaps = null;

		try {
			listReturnMaps = upSlaveTemplate().queryForList(sSql, mWhereMap);
		} catch (Exception e) {
			bLogError(968005003, dataBaseName, dataTableName);
			slaveTemplate = dataTemplate;
			listReturnMaps = upSlaveTemplate().queryForList(sSql, mWhereMap);
		}

		return listReturnMaps;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.srnpr.zapdata.dbface.ITableCall#dataInsert(com.srnpr.zapcom.basemodel
	 * .MDataMap)
	 */
	public String dataInsert(MDataMap mDataMap) {
		String sUid = UUID.randomUUID().toString().replace("-", "");
		if (!mDataMap.containsKey("uid")) {
			mDataMap.put("uid", sUid);
		}

		baseInsert(mDataMap);
		return sUid;

	}

	/**
	 * @param mDataMap
	 */
	public void baseInsert(MDataMap mDataMap) {

		StringBuffer sSqlBuffer = new StringBuffer();
		sSqlBuffer.append("insert into " + dataTableName + "(");
		String[] sKey = mDataMap.upKeys().toArray(new String[] {});
		sSqlBuffer.append(StringUtils.join(sKey, ","));
		sSqlBuffer.append(") values(:");
		sSqlBuffer.append(StringUtils.join(sKey, ",:"));
		sSqlBuffer.append(")");

		dataExec(sSqlBuffer.toString(), mDataMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapdata.dbface.ITableCall#dataExec(java.lang.String,
	 * com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public int dataExec(String sSql, MDataMap mDataMap) {
		return dataTemplate.update(sSql, mDataMap);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.srnpr.zapdata.dbface.ITableCall#dataUpdate(com.srnpr.zapcom.basemodel
	 * .MDataMap, java.lang.String, java.lang.String)
	 */
	public int dataUpdate(MDataMap mDataMap, String sUpdateFields,
			String sWhereFields) {
		StringBuffer sSqlBuffer = new StringBuffer();
		sSqlBuffer.append("update " + dataTableName + " set ");

		String[] sUpdates = StringUtils.isNotEmpty(sUpdateFields) ? sUpdateFields
				.split(",") : mDataMap.convertKeysToStrings();

		for (int i = 0, j = sUpdates.length; i < j; i++) {
			sUpdates[i] = sUpdates[i] + "=:" + sUpdates[i];
		}

		sSqlBuffer.append(" " + StringUtils.join(sUpdates, ",") + " ");

		if (StringUtils.isNotEmpty(sWhereFields)) {
			String[] sSqlWheres = sWhereFields.split(",");

			for (int i = 0, j = sSqlWheres.length; i < j; i++) {
				sSqlWheres[i] = sSqlWheres[i] + "=:" + sSqlWheres[i];
			}
			sSqlBuffer.append(" where " + StringUtils.join(sSqlWheres, " and ")
					+ " ");
		}

		return dataExec(sSqlBuffer.toString(), mDataMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapdata.dbface.ITableCall#dataDelete(java.lang.String,
	 * com.srnpr.zapcom.basemodel.MDataMap, java.lang.String)
	 */
	public int dataDelete(String sDeleteSql, MDataMap mDataMap,
			String sWhereFields) {

		StringBuffer sSqlBuffer = new StringBuffer();

		sSqlBuffer.append(" delete from " + dataTableName);

		if (StringUtils.isEmpty(sDeleteSql)) {

			String[] sSqlWheres = StringUtils.isNotEmpty(sWhereFields) ? sWhereFields
					.split(",") : mDataMap.convertKeysToStrings();

			for (int i = 0, j = sSqlWheres.length; i < j; i++) {
				sSqlWheres[i] = sSqlWheres[i] + "=:" + sSqlWheres[i];
			}
			if (sSqlWheres.length > 0) {
				sSqlBuffer.append(" where "
						+ StringUtils.join(sSqlWheres, " and ") + " ");
			}
		} else {

			sSqlBuffer.append(" where " + sDeleteSql);
		}

		return dataExec(sSqlBuffer.toString(), mDataMap);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapdata.dbface.ITableCall#dataGet(java.lang.String,
	 * java.lang.String, com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public Object dataGet(String sField, String sWhere, MDataMap mWhereMap) {
		Map<String, Object> rResultMap = dataQuery(sField + " as dataget", "",
				sWhere, mWhereMap, -1, -1).get(0);

		return rResultMap.get("dataget");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapdata.dbface.ITableCall#dataCount(java.lang.String,
	 * com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public int dataCount(String sWhere, MDataMap mWhereMap) {

		return Integer.valueOf(dataGet("count(1) ", sWhere, mWhereMap)
				.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapdata.dbface.ITableCall#upTemplate()
	 */
	public DbTemplate upTemplate() {

		return dataTemplate;
	}

}
