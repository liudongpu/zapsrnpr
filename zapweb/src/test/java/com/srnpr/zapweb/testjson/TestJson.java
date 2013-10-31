package com.srnpr.zapweb.testjson;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.web.bind.WebDataBinder;

import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.TestBase;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebResult;

public class TestJson extends TestBase {

	
	public void test() {

		JsonHelper jHelper = new JsonHelper();
		MWebResult mResult = new MWebResult();

		MWebField mField = new MWebField();

		String sJson = jHelper.ObjToString(mResult);

		bLogInfo(0, sJson);

	}

	
	public void testCode() {
		//bLogInfo(0, WebHelper.upCode("test"));
		
		
		  JsonHelper<List<MDataMap>> jsonHelper=new JsonHelper<List<MDataMap>>();
				
		  
		  
		  
		String sResultString=		  jsonHelper.ObjToString(DbUp.upTable("za_menu").queryByWhere());
				  
				 
		bTest(sResultString);
	
	}

}
