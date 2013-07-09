package com.srnpr.zapcom.basemodel;

import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

/**
 * @author srnpr
 * String Map
 */
public class MStringMap extends MObjMap<String, String> implements Map<String,String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1113400831514224701L;
	
	
	public MStringMap()
	{
		
	}
	public MStringMap(Map<String, Object> mData)
	{
	
		for(String sKyey:mData.keySet())
		{
			this.put(sKyey, mData.get(sKyey).toString());
		}
	}
	
	
	/**
	 * 转换主键到数组
	 * @return
	 */
	public String[] convertKeysToStrings()
	{
		return this.upKeys().toArray(new String[]{});
	}
	
	
	

}
