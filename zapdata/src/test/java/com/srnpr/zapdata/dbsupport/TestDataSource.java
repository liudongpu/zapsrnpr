package com.srnpr.zapdata.dbsupport;

import org.junit.Test;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topdo.TopBase;
import com.srnpr.zapdata.dbdo.DbUp;

public class TestDataSource extends TopBase {

	@Test
	public void testSource() {
		
	
		
		DbUp.upTable("cc_cardinfo").insert("card_code","aa");
		
		MDataMap MDataMap=new MDataMap();
		
		MDataMap.put("card_code", "bb");
		MDataMap.put("zid", "7");
		
		 DbUp.upTable("cc_cardinfo").dataUpdate(MDataMap, "", "zid");
	
		bLogDebug(0, DbUp.upTable("cc_cardinfo").count());
		
		
		MDataMap mapOneDataMap=DbUp.upTable("cc_cardinfo").one("zid","27");
		
		bLogDebug(0, mapOneDataMap.get("card_code"));
		
		//bLogDebug(0, DbUp.upTable("cc_cardinfo").delete());
	}

}
