package com.srnpr.zapweb.webdo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.AbstractDocument.BranchElement;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.rootclass.RootCache;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebView;

public class ViewCache extends RootCache<String, MWebView> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapcom.baseface.IBaseCache#refresh()
	 */
	public void refresh() {
		/*
		 * for (MDataMap mViewDataMap : DbUp.upTable("zw_view").queryByWhere())
		 * {
		 * 
		 * for (MDataMap mViewTypeDataMap : DbUp.upTable("zd_abstract")
		 * .queryByWhere("parent_aid", "116022")) {
		 * 
		 * String sViewType = mViewTypeDataMap.get("abstract_aids");
		 * 
		 * // 判断如果类型 则跳出循环 if (sViewType.equals("116022014")) { continue; }
		 * 
		 * MWebView mWebView = new MWebView();
		 * mWebView.setViewCode(mViewDataMap.get("view_code"));
		 * mWebView.setViewName(mViewDataMap.get("view_name"));
		 * mWebView.setTableName(mViewDataMap.get("table_name"));
		 * 
		 * mWebView.setViewKey(mWebView.getViewCode() + "-" + sViewType);
		 * 
		 * String sSortField = "sort_" + mViewTypeDataMap.get("abstract_value");
		 * 
		 * List<MWebField> mFields = new ArrayList<MWebField>();
		 * 
		 * MDataMap mFiledQuery = new MDataMap(); mFiledQuery.put("view_code",
		 * mWebView.getViewCode());
		 * 
		 * // 循环视图的字段列表 for (MDataMap mFieldDataMap : DbUp.upTable("zw_field")
		 * .queryAll("", sSortField, "", mFiledQuery)) { MWebField mWebField =
		 * new MWebField();
		 * mWebField.setColumnName(mFieldDataMap.get("column_name"));
		 * mWebField.setFieldNote(mFieldDataMap.get("field_note"));
		 * mWebField.setQueryTypeAid(mFieldDataMap .get("query_type_aid"));
		 * mWebField.setRegexValue(mFieldDataMap.get("regex_value"));
		 * 
		 * // 特殊判断 如果正则标记为无 则置为空 if
		 * (mWebField.getRegexValue().equals("469923180001")) {
		 * mWebField.setRegexValue(""); }
		 * 
		 * mWebField.setFieldTypeAid(mFieldDataMap .get("field_type_aid"));
		 * 
		 * mWebField.setSort(mFieldDataMap.get(sSortField));
		 * mWebField.setFieldScope(mFieldDataMap.get("field_scope"));
		 * 
		 * mWebField.setFieldName(mFieldDataMap.get("field_name"));
		 * 
		 * mWebField.setSourceCode(mFieldDataMap.get("source_code"));
		 * mWebField.setSourceParam(mFieldDataMap.get("source_param"));
		 * 
		 * mWebField.setPageFieldName(WebConst.CONST_WEB_FIELD_NAME +
		 * mWebField.getFieldName());
		 * 
		 * mFields.add(mWebField); }
		 * 
		 * mWebView.setFields(mFields);
		 * 
		 * this.inElement(mWebView.getViewKey(), mWebView); } }
		 */
	}

	@Override
	public MWebView upOne(String k) {

		String[] sKey = StringUtils.split(k, "-");

		MWebView mWebView = null;

		if (sKey.length == 2) {
			String sViewCode = sKey[0];

			String sViewType = sKey[1];

			MDataMap mViewDataMap = DbUp.upTable("zw_view").one("view_code",
					sViewCode);
			if (mViewDataMap != null) {

				MDataMap mViewTypeDataMap = DbUp.upTable("zd_abstract").one(
						"parent_aid", "116022", "abstract_aids", sViewType);

				if (mViewTypeDataMap != null) {

					// 判断如果类型 则跳出循环
					if (sViewType.equals("116022014")) {
						// break;
					}

					mWebView = new MWebView();
					mWebView.setViewCode(mViewDataMap.get("view_code"));
					mWebView.setViewName(mViewDataMap.get("view_name"));
					mWebView.setTableName(mViewDataMap.get("table_name"));

					mWebView.setViewKey(mWebView.getViewCode() + "-"
							+ sViewType);

					String sSortField = "sort_"
							+ mViewTypeDataMap.get("abstract_value");

					List<MWebField> mFields = new ArrayList<MWebField>();

					MDataMap mFiledQuery = new MDataMap();
					mFiledQuery.put("view_code", mWebView.getViewCode());

					// 循环视图的字段列表
					for (MDataMap mFieldDataMap : DbUp.upTable("zw_field")
							.queryAll("", sSortField, "", mFiledQuery)) {
						MWebField mWebField = new MWebField();
						mWebField.setColumnName(mFieldDataMap
								.get("column_name"));
						mWebField.setFieldNote(mFieldDataMap.get("field_note"));
						mWebField.setQueryTypeAid(mFieldDataMap
								.get("query_type_aid"));
						mWebField.setRegexValue(mFieldDataMap
								.get("regex_value"));

						mWebField.setFieldScope(mFieldDataMap
								.get("field_scope"));

						mWebField.setFieldTypeAid(mFieldDataMap
								.get("field_type_aid"));

						mWebField.setSort(mFieldDataMap.get(sSortField));

						mWebField.setFieldName(mFieldDataMap.get("field_name"));

						mWebField.setSourceCode(mFieldDataMap
								.get("source_code"));
						mWebField.setSourceParam(mFieldDataMap
								.get("source_param"));

						// 特殊判断 如果正则标记为无 则置为空
						if (mWebField.getRegexValue().equals("469923180001")) {
							mWebField.setRegexValue("");
						}

						// 添加、修改模式下如果字段的设置不为空 则附加扩展字段
						if (sViewType.equals("116022001")
								|| sViewType.equals("116022005")) {
							if (!mWebField.getRegexValue().equals(
									"")) {
								mWebField.setFieldExtend(mWebField
										.getFieldExtend()
										+ " "
										+ WebConst.CONST_WEB_FIELD_ATTR
										+ "regex_id=\""
										+ mWebField.getRegexValue() + "\" ");
								
								mWebField.setFieldNote(WebConst.CONST_WEB_FIELD_NOT_NULL_TEXT+mWebField.getFieldNote());
							}
							
							mWebField.setFieldNote(mWebField.getFieldNote()+"：");

							MDataMap mScopeMap = new MDataMap().inUrlParams(
									mWebField.getFieldScope()).upSubMap(
									WebConst.CONST_WEB_PAGINATION_NAME);

							if (mScopeMap != null && mScopeMap.size() > 0) {
								if (mScopeMap.containsKey("cssname")) {
									mWebField.setFieldExtend(mWebField
											.getFieldExtend()
											+ " class=\""
											+ mScopeMap.get("cssname") + "\" ");
								}
							}

						}

						mWebField
								.setPageFieldName(WebConst.CONST_WEB_FIELD_NAME
										+ mWebField.getFieldName());

						mFields.add(mWebField);
					}

					mWebView.setFields(mFields);

					this.inElement(mWebView.getViewKey(), mWebView);
				}
			}

		}

		return mWebView;

	}

}
