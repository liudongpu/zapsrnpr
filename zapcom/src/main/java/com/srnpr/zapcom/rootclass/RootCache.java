package com.srnpr.zapcom.rootclass;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import com.srnpr.zapcom.baseface.IBaseCache;
import com.srnpr.zapcom.topdo.TopBase;

public abstract class RootCache<K,V> extends TopBase implements IBaseCache {

	private Cache cache;
	
	public RootCache()
	{
		CacheDefine cDefine=new CacheDefine();
		String sClassNameString=this.getClass().getName();
		cache=cDefine.inCache(sClassNameString);
	}
	
	
	public abstract void refresh();
	
	
	public void inElement(K k,V v)
	{
		
		cache.put(new Element(k, v));
	}
	
	
	public boolean containsKey(K k)
	{
		return cache.getKeys().contains(k);
	}
	
	
	public List<K> upKeys()
	{
		
		return  cache.getKeys();
	}
	
	
	@SuppressWarnings("unchecked")
	public V upValue(K k)
	{
		if(cache.get(k)==null)
		{
			refresh();
		}
		
		return (V)cache.get(k).getObjectValue();
	}
	
	
	
	
}
