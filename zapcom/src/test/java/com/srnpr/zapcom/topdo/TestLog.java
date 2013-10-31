package com.srnpr.zapcom.topdo;

import static org.junit.Assert.*;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.srnpr.zapcom.TestZapcom;
import com.srnpr.zapcom.baseclass.BaseClass;

public class TestLog extends BaseClass {


	public void test() {
		
		
		TopUp topUp=new TopUp();
		
		//bLog(0, topUp.upConfigMap("zapcom.init").get("zapcom"));
		
		//bLogInfo(967920001, "a");
		
		bLogInfo(0,bInfo(967920001, "aaaa"));
	
	}

}
