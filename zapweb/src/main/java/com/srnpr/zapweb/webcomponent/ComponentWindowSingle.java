package com.srnpr.zapweb.webcomponent;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebHtml;
import com.srnpr.zapweb.webmodel.MWebResult;

public class ComponentWindowSingle extends RootSimpleComponent {

	@Override
	public String upText(MWebField mWebField, MDataMap mDataMap, int iType) {
		MDataMap mSetMap = upSetMap(mWebField.getSourceParam());

		MWebHtml mDivHtml = new MWebHtml("div");

		mDivHtml.addChild("hidden", "id", mWebField.getPageFieldName(),
				"value", mWebField.getPageFieldValue());

		mDivHtml.addChild("script",
				"zapjs.f.require(['zapadmin/js/zapadmin_single'],function(a){a.init({id:'"
						+ mWebField.getPageFieldName() + "'});});");

		return mDivHtml.upString();

	}

	@Override
	public MWebResult inDo(MWebField mWebField, MDataMap mDataMap, int iType) {

		return null;
	}

}
