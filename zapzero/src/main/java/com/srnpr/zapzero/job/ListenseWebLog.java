package com.srnpr.zapzero.job;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapzero.root.RootJmsListenser;

public class ListenseWebLog extends RootJmsListenser {

	public boolean onReceiveText(String sMessage,MDataMap mDataMap) {
		
		//DbUp.upTable("lc_web_log").insert("")
		
		DbUp.upTable("lc_web_log").insert("code",mDataMap.get("code"),"info",sMessage,"create_time",mDataMap.get("create_time"));
		
		
		
		return true;
		
		
	}

}
