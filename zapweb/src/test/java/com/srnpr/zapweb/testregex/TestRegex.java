package com.srnpr.zapweb.testregex;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.TestBase;
import com.srnpr.zapweb.webmodel.MWebResult;

public class TestRegex extends BaseClass  {

	
	public void test() {
		
		Pattern pattern=Pattern.compile("^[0-9]*$");
		Matcher matcher=pattern.matcher("1a2");
		
String aString="11";

		bLogInfo(0, aString.matches("^\\d{1,99}$"));
		
	}
	
	
	
	/**
	 * 业务逻辑
	 * @param mInput
	 * @return
	 */
	public MWebResult getCard(MDataMap mInput)
	{
		return null;
	}
	
	
	
	
	/**
	 * 公开方法
	 * 
	 * @param sMethod
	 * @param sApiKey
	 * @param sJsonInput
	 * @param sSec
	 * @return
	 */
	public String serviceApi(String sMethod,String sApiKey,String sJsonInput,String sSec)
	{
		
		String sReturnString="";
		
		if(bCheck(sApiKey, sJsonInput, sSec))
		{
			if(sMethod=="serviceGetCard")
			{
				sReturnString=serviceGetCard(sJsonInput);
			}
		}
		
		return sReturnString;
	}
	
	/**
	 * 检测
	 * @param sApikey
	 * @param sJson
	 * @param sSec
	 * @return
	 */
	public Boolean bCheck(String sApikey,String sJson,String sSec)
	{
		
		MDataMap mMap=new MDataMap();
		mMap.put("abcd", "defg");
		
		boolean bFlagReturn=false;
		
		if(mMap.containsKey(sApikey))
		{
			String sPassString=mMap.get(sApikey);
			
			String sYzString="方法  Md5（sJoin+sPassString）";
			
			if(sSec.equals(sYzString))
			{
				bFlagReturn=true;
			}
			
		}

		return bFlagReturn;
	}
	
	
	/**
	 * 转换
	 * @param sJson
	 * @return
	 */
	public String serviceGetCard(String sJson)
	{
		
		JsonHelper<MDataMap> jsonHelper=new JsonHelper<MDataMap>();
		
		MDataMap mInput
		=jsonHelper.StringToObj(sJson, new MDataMap());
		JsonHelper<MWebResult> jRet=new JsonHelper<MWebResult>();
		
		return jRet.ObjToString(getCard(mInput));
		
		
	}
	
	
	@Test
	public void sTest()
	{

		Pattern p=Pattern.compile("\\[@(.+?)\\$(.*?)\\]");
		  String u="../export/page_chart_v_export_cc_cardinfo?zapweb_field_uid=[@this$][@this2$]";
		  Matcher m=p.matcher(u);
		
		
		  while(m.find()){
		   bLogInfo(0, m.group(0));
		   bLogInfo(0, m.group(1));
		   bLogInfo(0, m.group(2));
		  }
		/*
		  for(int i=1;i<= m.groupCount();i++){  
			  bLogInfo(0 ,m.group(i).toString()); 
			} 
		  */
	}
	
	
	
	
	
	

}
