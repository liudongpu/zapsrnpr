package com.srnpr.zapweb.webcomponent;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebHtml;
import com.srnpr.zapweb.webmodel.MWebResult;

public class ComponentSetReplace extends RootSimpleComponent {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.srnpr.zapweb.webcomponent.RootSimpleComponent#upText(com.srnpr.zapweb
	 * .webmodel.MWebField, com.srnpr.zapcom.basemodel.MDataMap, int)
	 */
	@Override
	public String upText(MWebField mWebField, MDataMap mDataMap, int iType) {

		String sReturnString = "";

		MWebHtml mBaseDiv = new MWebHtml("div");

		MWebHtml mTextArea = mBaseDiv.addChild("textarea", "name",
				mWebField.getPageFieldName(), "id",
				mWebField.getPageFieldName());
		if (mDataMap.containsKey(mWebField.getFieldName())) {
			mTextArea.setHtml(mDataMap.get(mWebField.getFieldName()));
		}

		mBaseDiv.addChild("button", "value", "change", "class", "btn ");

		MWebHtml mScript = mBaseDiv.addChild("script");

		mScript.setHtml("zapjs.f.require([\"zapadmin/js/zapadmin_setreplace\"],function(a){ a.init(\""
				+ mWebField.getPageFieldName() + "\");  })");

		sReturnString = mBaseDiv.upString();

		return sReturnString;

	}

	@Override
	public MWebResult inDo(MWebField mWebField, MDataMap mDataMap, int iType) {
		// TODO Auto-generated method stub
		return null;
	}

}
