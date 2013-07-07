package com.srnpr.zapcom.testroot;

import static org.junit.Assert.*;

import net.sf.ehcache.CacheManager;

import org.junit.Test;
import com.srnpr.zapcom.topdo.TopTest;

public class TestCache extends TopTest {

	@Test
	public void test() {
		
		
		
		//CacheManager.getInstance().removeAllCaches();
		bConfig("zapcom.version");
		String[] sNames= CacheManager.getInstance().getCacheNames();
		for(String s:sNames)
		{
			bLog(0, "cache:"+s);
		}
		
		
	}

}
