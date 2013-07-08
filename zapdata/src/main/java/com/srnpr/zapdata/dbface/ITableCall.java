package com.srnpr.zapdata.dbface;

import com.srnpr.zapcom.basemodel.MSoMap;
import com.srnpr.zapcom.basemodel.MStringMap;

public interface ITableCall {

	public MSoMap queryList( String sFields,String sOrders,MStringMap mWhereMap,int iStart,int iEnd  );
}
