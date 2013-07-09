package com.srnpr.zapdata.dbsupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MSoMap;
import com.srnpr.zapcom.basemodel.MStringMap;

public class MysqlCall extends DbCall {

	private DbTemplate dataTemplate = null;

	public MysqlCall(DbTemplate dBase, String sTableName) {
		dataTemplate = dBase;

		dataTableName = sTableName;
	}

	private String dataTableName = null;

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

}
