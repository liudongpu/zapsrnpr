package com.srnpr.zapdata.dbsupport;

import java.util.Map;

import org.junit.Test;

import com.srnpr.zapcom.basemodel.MSoMap;
import com.srnpr.zapcom.topdo.TopBase;
import com.srnpr.zapdata.TestZapdata;
import com.srnpr.zapdata.dbdo.DbUp;
import com.sun.org.apache.bcel.internal.classfile.FieldOrMethod;

public class TestDataSource extends TopBase {

	@Test
	public void testSource() {
		
	
		

		
		for(Map<String, Object> mResult:DbUp.upTable("cc_cardinfo").queryList("", "", "", null, 0, 0))
		{
			bLogDebug(0, mResult.get("card_money").toString());
		}
	
		
	}

}
