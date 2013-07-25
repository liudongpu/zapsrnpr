package com.srnpr.zapcom.basemodel;

import java.util.Enumeration;
import java.util.Map;

public class MDataMap extends MObjMap<String, String> implements
		Map<String, String> {
	/**
	 * 转换主键到数组
	 * 
	 * @return
	 */
	public String[] convertKeysToStrings() {
		return this.upKeys().toArray(new String[] {});
	}

	
	public MDataMap()
	{
		
	}
	
	public MDataMap(String... sInputs)
	{
		inAllValues(sInputs);
	}
	
	
	
	public MDataMap(Map<String, Object> mInput) {
		
		for(String s:mInput.keySet())
		{
			put(s, mInput.get(s).toString());
		}
	}

}
