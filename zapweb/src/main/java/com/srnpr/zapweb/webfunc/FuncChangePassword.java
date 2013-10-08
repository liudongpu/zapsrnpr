package com.srnpr.zapweb.webfunc;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webmodel.MWebResult;

/**
 * 修改密码
 * 
 * @author srnpr
 * 
 */
public class FuncChangePassword extends RootFunc {

	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {

		MWebResult mResult = new MWebResult();
		
		MDataMap mWindowMap=mDataMap.upSubMap("window_change_password_");
		

		if (mResult.upFlagTrue()) {

			if(StringUtils.isBlank(mWindowMap.get("old_password")))
			{
				mResult.inErrorMessage(969905048);
			}else if(StringUtils.isBlank(mWindowMap.get("new_password")))
			{
				mResult.inErrorMessage(969905048);
			}
			
			
			
			
		}

		return mResult;

	}

}
