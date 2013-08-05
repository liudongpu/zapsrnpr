package com.srnpr.zapweb.webcomponent;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webface.IWebComponent;

public abstract class RootComponent extends BaseClass implements IWebComponent {

	public String upFieldName(String sColumnName) {
		return WebConst.CONST_WEB_FIELD_COMPONENT + sColumnName;
	}

}
