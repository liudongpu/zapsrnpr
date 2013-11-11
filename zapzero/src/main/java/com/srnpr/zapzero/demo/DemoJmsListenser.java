package com.srnpr.zapzero.demo;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapzero.root.RootJmsListenser;

/**
 * Demo监听主题
 * @author srnpr
 *
 */
public class DemoJmsListenser extends RootJmsListenser {

	/* (non-Javadoc)
	 * @see com.srnpr.zapzero.face.IJmsListener#onReceiveText(java.lang.String)
	 */
	public boolean onReceiveText(String sMessage,MDataMap mDataMap) {
		bLogInfo(0, sMessage);
		return true;
	}

}
