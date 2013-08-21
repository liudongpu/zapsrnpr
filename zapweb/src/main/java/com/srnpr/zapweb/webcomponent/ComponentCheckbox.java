package com.srnpr.zapweb.webcomponent;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basehelper.MapHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webface.IWebComponent;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebResult;
import com.srnpr.zapweb.webmodel.MWebSource;

/**
 * 组件-多选框 该组件适用于跨表结构体
 * 
 * @author srnpr
 * 
 */
public class ComponentCheckbox extends RootComponent {

	/**
	 * 需要初始化参数 component_show_table:显示内容表格,component_show_field_text,
	 * component_show_field_value,component_show_join,component_in_field_Key
	 * ,component_in_field_value ,component_in_table,component_key_field
	 */
	private MDataMap componentMap = new MDataMap();

	public ComponentCheckbox() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.srnpr.zapweb.webface.IWebComponent#upListText(com.srnpr.zapweb.webmodel
	 * .MWebField, com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public String upListText(MWebField mWebField, MDataMap mDataMap) {

		String sKey = mDataMap.get(componentMap.get("component_key_field"));

		List<String> listReturnList = new ArrayList<String>();

		List<String> listIn = new ArrayList<String>();

		for (MDataMap mIn : DbUp
				.upTable(componentMap.get("component_in_table")).queryByWhere(
						componentMap.get("component_in_field_Key"), sKey)) {
			listIn.add("'"
					+ mIn.get(componentMap.get("component_in_field_value"))
					+ "'");
		}

		if (listIn.size() > 0) {
			for (MDataMap mShow : DbUp.upTable(
					componentMap.get("component_show_table")).queryAll(
					"",
					"",
					componentMap.get("component_show_field_value") + " in ("
							+ StringUtils.join(listIn, ",") + ")",
					new MDataMap())) {
				listReturnList.add(mShow.get(componentMap
						.get("component_show_field_text")));
			}
		}

		String sShowJoin = ",";
		if (componentMap.containsKey("component_show_join")) {
			sShowJoin = componentMap.get("component_show_join");
		}
		return StringUtils.join(listReturnList, sShowJoin);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.srnpr.zapweb.webface.IWebComponent#upAddText(com.srnpr.zapweb.webmodel
	 * .MWebField, com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public String upAddText(MWebField mField, MDataMap mDataMap) {

		return upShowText(mField, mDataMap);

	}

	/**
	 * 获取展示时输出内容
	 * 
	 * @param mField
	 * @param mDataMap
	 * @return
	 */
	private String upShowText(MWebField mField, MDataMap mDataMap) {
		List<String> listReturnList = new ArrayList<String>();

		String sHtmlCheckbox = bConfig("zapweb.html_checkbox");

		int iStep = 0;
		String sValueString = "";

		MDataMap mExitDataMap = new MDataMap();

		if (mDataMap.containsKey(componentMap.get("component_key_field"))) {
			for (MDataMap mIn : DbUp.upTable(
					componentMap.get("component_in_table")).queryByWhere(
					componentMap.get("component_in_field_Key"),
					mDataMap.get(componentMap.get("component_key_field")))) {
				mExitDataMap.put(
						mIn.get(componentMap.get("component_in_field_value")),
						mIn.get(componentMap.get("component_in_field_Key")));
			}

		}

		for (MDataMap mShow : DbUp.upTable(
				componentMap.get("component_show_table")).queryByWhere()) {

			listReturnList.add(FormatHelper.formatString(
					sHtmlCheckbox,
					upFieldName(mField.getColumnName()) + "_"
							+ String.valueOf(iStep),
					upFieldName(mField.getColumnName()),
					mShow.get(componentMap.get("component_show_field_value")),
					mShow.get(componentMap.get("component_show_field_text")),
					mExitDataMap.containsKey(mShow.get(componentMap.get("component_show_field_value")))?" checked=\"checked\" ":""));

			iStep++;

		}

		return StringUtils.join(listReturnList, "");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.srnpr.zapweb.webface.IWebComponent#upEditText(com.srnpr.zapweb.webmodel
	 * .MWebField, com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public String upEditText(MWebField mWebField, MDataMap mDataMap) {
		return upShowText(mWebField, mDataMap);
	}

	/**
	 * 数据库执行操作
	 * 
	 * @param mWebField
	 * @param mDataMap
	 * @return
	 */
	private MWebResult inUpdatDb(MWebField mWebField, MDataMap mDataMap) {

		MWebResult mReturnResult = new MWebResult();

		MDataMap mFieldMap = MapHelper.subMap(mDataMap,
				WebConst.CONST_WEB_FIELD_NAME);

		if (mFieldMap.containsKey(componentMap.get("component_key_field"))) {
			DbUp.upTable(componentMap.get("component_in_table")).delete(
					componentMap.get("component_in_field_Key"),
					mFieldMap.get(componentMap.get("component_key_field")));
		}
		;

		MDataMap mComponentMap = MapHelper.subMap(mDataMap,
				WebConst.CONST_WEB_FIELD_COMPONENT);

		if (mComponentMap.containsKey(mWebField.getColumnName())) {
			String[] sValueStrings = mComponentMap.get(
					mWebField.getColumnName()).split(",");

			if (sValueStrings.length > 0) {
				String sKeyString = mFieldMap.get(componentMap
						.get("component_key_field"));

				for (String sValue : sValueStrings) {

					DbUp.upTable(componentMap.get("component_in_table"))
							.insert(componentMap.get("component_in_field_Key"),
									sKeyString,
									componentMap
											.get("component_in_field_value"),
									sValue);
				}
			}

		}

		return mReturnResult;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.srnpr.zapweb.webface.IWebComponent#inAdd(com.srnpr.zapweb.webmodel
	 * .MWebField, com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public MWebResult inAdd(MWebField mWebField, MDataMap mDataMap) {

		return inUpdatDb(mWebField, mDataMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.srnpr.zapweb.webface.IWebComponent#inEdit(com.srnpr.zapweb.webmodel
	 * .MWebField, com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public MWebResult inEdit(MWebField mWebField, MDataMap mDataMap) {
		return inUpdatDb(mWebField, mDataMap);
	}

	public MDataMap getComponentMap() {
		return componentMap;
	}

	public void setComponentMap(MDataMap componentMap) {
		this.componentMap = componentMap;
	}

	public MWebResult inDelete(MWebField mWebField, MDataMap mDataMap) {
		// TODO Auto-generated method stub
		return null;
	}

}
