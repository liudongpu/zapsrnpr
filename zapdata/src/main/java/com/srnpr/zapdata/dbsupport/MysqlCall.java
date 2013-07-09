package com.srnpr.zapdata.dbsupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MSoMap;
import com.srnpr.zapcom.basemodel.MStringMap;

public class MysqlCall extends DbCall {

	/**
	 * 数据源
	 */
	private DbTemplate dataTemplate = null;

	/**
	 * @param dBase
	 *            数据源
	 * @param sTableName
	 *            表名称
	 */
	public MysqlCall(DbTemplate dBase, String sTableName) {
		dataTemplate = dBase;

		dataTableName = sTableName;
	}

	/**
	 * 表名
	 */
	private String dataTableName = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapdata.dbface.ITableCall#queryList(java.lang.String,
	 * java.lang.String, java.lang.String,
	 * com.srnpr.zapcom.basemodel.MStringMap, int, int)
	 */
	public List<Map<String, Object>> queryList(String sFields, String sOrders,
			String sWhere, MStringMap mWhereMap, int iStart, int iEnd) {

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
		if (mWhereMap != null && mWhereMap.size() > 0) {
			sBuffer.append(" where "
					+ (StringUtils.isEmpty(sWhere) ? FormatHelper
							.joinWhereStrings(mWhereMap.upKeys()) : sWhere)
					+ " ");
		}

		if (StringUtils.isNotEmpty(sOrders)) {

		}

		return dataTemplate.queryForList(sBuffer.toString(), mWhereMap);
	}

	public String dataInsert(MStringMap mData) {
		String sUid = UUID.randomUUID().toString().replace("-", "");
		if (mData.containsKey("uid")) {
			mData.put("uid", sUid);
		}

		baseInsert(mData);
		return sUid;

	}

	public void baseInsert(MStringMap mData) {

		StringBuffer sSqlBuffer = new StringBuffer();
		sSqlBuffer.append("insert into " + dataTableName + "(");
		String[] sKey = mData.upKeys().toArray(new String[] {});
		sSqlBuffer.append(StringUtils.join(sKey, ","));
		sSqlBuffer.append(") values(:");
		sSqlBuffer.append(StringUtils.join(sKey, ",:"));
		sSqlBuffer.append(")");

		dataExec(sSqlBuffer.toString(), mData);
	}

	public int dataExec(String sSql, MStringMap mData) {
		return dataTemplate.update(sSql, mData);
	}

	public int dataUpdate(MStringMap mHashMap, String sUpdateFields,
			String sWhereFields) {
		StringBuffer sSqlBuffer = new StringBuffer();
		sSqlBuffer.append("update " + dataTableName + " set ");

		String[] sUpdates = StringUtils.isNotEmpty(sUpdateFields) ? sUpdateFields
				.split(",") : mHashMap.convertKeysToStrings();

		for (int i = 0, j = sUpdates.length; i < j; i++) {
			sUpdates[i] = sUpdates[i] + "=:" + sUpdates;
		}

		sSqlBuffer.append(" " + StringUtils.join(sUpdates, ",") + " ");

		if (StringUtils.isNotEmpty(sWhereFields)) {
			String[] sSqlWheres = sWhereFields.split(",");

			for (int i = 0, j = sSqlWheres.length; i < j; i++) {
				sSqlWheres[i] = sSqlWheres[i] + "=:" + sSqlWheres;
			}
			sSqlBuffer.append(" where " + StringUtils.join(sUpdates, " and ")
					+ " ");
		}

		return dataExec(sSqlBuffer.toString(), mHashMap);
	}

	public int dataDelete(MStringMap mHashMap, String sWhereFields) {

		StringBuffer sSqlBuffer = new StringBuffer();

		sSqlBuffer.append(" delete from " + dataTableName);

		String[] sSqlWheres = StringUtils.isNotEmpty(sWhereFields) ? sWhereFields
				.split(",") : mHashMap.convertKeysToStrings();

		for (int i = 0, j = sSqlWheres.length; i < j; i++) {
			sSqlWheres[i] = sSqlWheres[i] + "=:" + sSqlWheres;
		}
		sSqlBuffer.append(" where " + StringUtils.join(sSqlWheres, " and ")
				+ " ");

		return dataExec(sSqlBuffer.toString(), mHashMap);

	}

}
