package com.srnpr.zapweb.webdo;

import java.util.ArrayList;
import java.util.List;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.rootclass.RootCache;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebView;

public class ViewCache extends RootCache<String, MWebView> {

	public void refresh() {

		for (MDataMap mViewDataMap : DbUp.upTable("zw_view").queryByWhere()) {

			for (MDataMap mViewTypeDataMap : DbUp.upTable("zd_abstract")
					.queryByWhere("parent_aid", "116022")) {
				
				
				
				

				String sViewType = mViewTypeDataMap.get("abstract_aids");

				if(sViewType.equals("116022014"))
				{
					continue;
				}
				
				
				MWebView mWebView = new MWebView();
				mWebView.setViewCode(mViewDataMap.get("view_code"));
				mWebView.setViewName(mViewDataMap.get("view_name"));
				mWebView.setTableName(mViewDataMap.get("table_name"));

				mWebView.setViewKey(mWebView.getViewCode() + "-" + sViewType);

				String sSortField = "sort_"
						+ mViewTypeDataMap.get("abstract_value");

				List<MWebField> mFields = new ArrayList<MWebField>();

				MDataMap mFiledQuery = new MDataMap();
				mFiledQuery.put("view_code", mWebView.getViewCode());

				for (MDataMap mFieldDataMap : DbUp.upTable("zw_field")
						.queryAll("", sSortField, "", mFiledQuery)) {
					MWebField mWebField = new MWebField();
					mWebField.setColumnName(mFieldDataMap.get("column_name"));
					mWebField.setFieldNote(mFieldDataMap.get("field_note"));
					mWebField.setQueryTypeAid(mFieldDataMap
							.get("query_type_aid"));
					mWebField.setRegexValue(mFieldDataMap.get("regex_value"));
					mWebField.setFieldTypeAid(mFieldDataMap
							.get("field_type_aid"));

					mWebField.setSort(mFieldDataMap.get(sSortField));

					mWebField.setSourceCode(mFieldDataMap.get("source_code"));
					mWebField.setSourceParam(mFieldDataMap.get("source_param"));

					mWebField.setPageFieldName(WebConst.CONST_WEB_FIELD_NAME
							+ mWebField.getColumnName());

					mFields.add(mWebField);
				}

				mWebView.setFields(mFields);

				this.inElement(mWebView.getViewKey(), mWebView);
			}
		}
	}

}
