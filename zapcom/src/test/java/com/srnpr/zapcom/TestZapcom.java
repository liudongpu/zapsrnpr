package com.srnpr.zapcom;

import static org.junit.Assert.*;

import org.junit.Test;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.topdo.TopTest;

public class TestZapcom extends TopTest {

	@Test
	public void test() {
		
		
		
		String sBaseString="abcdefghijklmnopqrstuvwxyz";
		
		bLog(0,String.valueOf( FormatHelper.convertFormatStringNumber("zw", sBaseString)));
	}
	
	
	
	
	
	

}
