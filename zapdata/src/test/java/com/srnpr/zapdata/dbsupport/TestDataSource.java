package com.srnpr.zapdata.dbsupport;

import org.junit.Test;

import com.srnpr.zapcom.topdo.TopBase;
import com.srnpr.zapdata.dbdo.DbUp;

public class TestDataSource extends TopBase {

	@Test
	public void testSource() {
		
	
		
		DbUp.upTable("cc_cardinfo").dataInsert("card_code","aa");
		
	
	
		
	}

}
