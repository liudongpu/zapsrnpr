package com.srnpr.zapweb.webcomponent;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webface.IWebComponent;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebResult;
import com.srnpr.zapweb.webmodel.MWebSource;

public class ComponentCheckbox implements IWebComponent {

	/**
	 * 需要初始化参数
	 * show_table,show_field_text,show_field_value,show_join,in_field_Key
	 * ,in_field_value ,in_table,key_field
	 */
	private MDataMap componentMap = new MDataMap();

	public ComponentCheckbox() {
		componentMap.inAllValues("in_table", "cc_userrole", "in_field_Key",
				"user_uid", "in_field_value", "role_code", "key_field", "uid",
				"show_table", "cc_roleinfo", "show_field_text", "role_name",
				"show_field_value", "role_code");
	}

	public String upListText(MWebField mWebField, MDataMap mDataMap) {

		String sKey = mDataMap.get(componentMap.get("key_field"));

		List<String> listReturnList = new ArrayList<String>();

		List<String> listIn = new ArrayList<String>();

		for (MDataMap mIn : DbUp.upTable(componentMap.get("in_table"))
				.queryByWhere(componentMap.get("in_field_Key"), sKey)) {
			listIn.add("'" + mIn.get(componentMap.get("in_field_value")) + "'");
		}

		for (MDataMap mShow : DbUp.upTable(componentMap.get("show_table"))
				.queryAll(
						"",
						"",
						componentMap.get("show_field_value") + " in ("
								+ StringUtils.join(listIn, ",") + ")",
						new MDataMap())) {
			listReturnList.add(mShow.get(componentMap.get("show_field_text")));
		}

		String sShowJoin = ",";
		if (componentMap.containsKey("show_join")) {
			sShowJoin = componentMap.get("show_join");
		}
		return StringUtils.join(listReturnList, sShowJoin);
	}

	public String upAddText(MWebField mField) {
		return null;
	}

	public String upEditText(MWebField mWebField, MDataMap mDataMap) {
		return null;
	}

	public MWebResult inAdd(MWebField mWebField, MDataMap mDataMap) {
		return null;
	}

	public MWebResult inEdit(MWebField mWebField, MDataMap mDataMap) {
		return null;
	}

	public MDataMap getComponentMap() {
		return componentMap;
	}

	public void setComponentMap(MDataMap componentMap) {
		this.componentMap = componentMap;
	}

}
