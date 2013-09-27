package com.srnpr.zapweb.webdo;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.rootclass.RootCache;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webmodel.MWebSource;

public class SourceCache extends RootCache<String, MWebSource> {

	public void refresh() {

		for (MDataMap mDataMap : DbUp.upTable("zw_source").queryByWhere()) {

			MWebSource mWebSource = new MWebSource();

			mWebSource.setFieldText(mDataMap.get("field_text"));
			mWebSource.setFieldValue(mDataMap.get("field_value"));
			mWebSource.setSourceCode(mDataMap.get("source_code"));
			mWebSource.setSourceFrom(mDataMap.get("source_from"));
			mWebSource.setWhereBook(mDataMap.get("where_book"));
			mWebSource.setWhereEdit(mDataMap.get("where_edit"));
			mWebSource.setFieldSort(mDataMap.get("field_sort"));

			inElement(mWebSource.getSourceCode(), mWebSource);

		}

	}

	@Override
	public MWebSource upOne(String k) {
	
		return null;
	}

}
