package com.srnpr.zapcom.testroot;

import static org.junit.Assert.*;

import net.sf.ehcache.CacheManager;

import org.junit.Test;

import com.srnpr.zapcom.topdo.TopConfig;
import com.srnpr.zapcom.topdo.TopTest;

public class TestCache extends TopTest {

	@Test
	public void test() {
		
		new TopConfig().refresh();
		
		//CacheManager.getInstance().removeAllCaches();
		
		String[] sNames= CacheManager.getInstance().getCacheNames();
		for(String s:sNames)
		{
			bLog(0, "cache:"+s);
		}
		
		
	}

}
