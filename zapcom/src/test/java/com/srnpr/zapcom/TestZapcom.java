package com.srnpr.zapcom;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.Test;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basehelper.TestHelper;
import com.srnpr.zapcom.topdo.TopTest;

public class TestZapcom extends TestHelper {

	
	public void testFormat26() {
		
		String sBaseString="abcdefghijklmnopqrstuvwxyz";
		
		bLogTest(String.valueOf( FormatHelper.convertFormatStringNumber("zz", sBaseString)));
		
		//bLogTest(String.valueOf( convertFormatNumberBack("1755835901725160785883", sBaseString)));
		bLogTest(String.valueOf( FormatHelper.convertFormatNumberBack("702", sBaseString)));
	}
	
	
	
	
	
	
	
	
	
	

}
