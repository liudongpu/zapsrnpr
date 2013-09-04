package com.srnpr.zapweb.webcomponent;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webmodel.MWebField;
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

		if (iType == 5) {

			// 获取设置
			MDataMap mSetMap = upSetMap(mWebField.getSourceParam());
			
			//WebUp.upComponent(mWebField.getSourceCode()).
			
			
			
			
			
		} else {
			sReturnString = mDataMap.get(mWebField.getFieldName());
		}

		return sReturnString;

	}

	@Override
	public MWebResult inDo(MWebField mWebField, MDataMap mDataMap, int iType) {
		// TODO Auto-generated method stub
		return null;
	}

}
