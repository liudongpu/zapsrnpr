package com.srnpr.zapcom;

import static org.junit.Assert.*;

import org.junit.Test;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.topdo.TopTest;

public class TestBase extends TopTest {

	@Test
	public void test() {
		
		
		
		String sBaseString="abcdefghijklmnopqrstuvwxyz";
		
		bLog(FormatHelper.convertFormatStringNumber("zc", sBaseString));
	}
	
	
	
	
	
	

}
