package com.srnpr.zapdata.dbface;

import java.util.List;
import java.util.Map;

import com.srnpr.zapcom.basemodel.MSoMap;
import com.srnpr.zapcom.basemodel.MStringMap;

public interface ITableCall {

	public List<Map<String, Object>> queryList( String sFields,String sOrders,String sWhere,MStringMap mWhereMap,int iStart,int iEnd  );
}
