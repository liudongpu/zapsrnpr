package com.srnpr.zapdata.dbsupport;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basemodel.MStringMap;
import com.srnpr.zapdata.dbface.ITableCall;

public abstract class DbCall extends BaseClass implements ITableCall {

	public String dataInsert(String... sParams) {
		MStringMap mData = new MStringMap();

		mData.inAllValues(sParams);

		return dataInsert(mData);
	}

}
