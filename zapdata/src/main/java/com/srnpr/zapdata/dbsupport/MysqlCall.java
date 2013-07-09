package com.srnpr.zapdata.dbsupport;

import com.srnpr.zapcom.basemodel.MSoMap;
import com.srnpr.zapcom.basemodel.MStringMap;

public class MysqlCall extends DbCall {

	private DbTemplate dataTemplate = null;

	public MysqlCall(DbTemplate dBase, String sTableName) {
		dataTemplate = dBase;

		dataTableName = sTableName;
	}

	private String dataTableName = null;

	public MSoMap queryList(String sFields, String sOrders,
			MStringMap mWhereMap, int iStart, int iEnd) {
		// TODO Auto-generated method stub
		return null;
	}

}
