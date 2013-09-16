package com.srnpr.zapcom.rootclass;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import com.srnpr.zapcom.baseface.IBaseCache;
import com.srnpr.zapcom.topdo.TopBase;

/**
 * 缓存基类
 * 
 * @author srnpr
 * 
 * @param <K>
 * @param <V>
 */
public abstract class RootCache<K, V> extends TopBase implements IBaseCache {

	private Cache cache;

	/**
	 * 构造函数
	 */
	public RootCache() {
		CacheDefine cDefine = new CacheDefine();
		String sClassNameString = this.getClass().getName();
		cache = cDefine.inCache(sClassNameString);
	}

	/**
	 * 添加缓存
	 * 
	 * @param k
	 * @param v
	 */
	public void inElement(K k, V v) {

		cache.put(new Element(k, v));
	}

	/**
	 * 判断是否存在
	 * 
	 * @param k
	 * @return
	 */
	public boolean containsKey(K k) {
		// return cache.getKeys().contains(k);
		return cache.isKeyInCache(k);
	}

	/**
	 * 获取所有缓存的Key
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<K> upKeys() {
		return cache.getKeys();
	}

	/**
	 * 获取一个 默认返回null 客户端如果继承该类需要自行封装业务逻辑 该方法通用用于客户端覆盖 延迟加载判断
	 * 
	 * @param k
	 * @return
	 */
	public V upOne(K k) {
		return null;
	}

	/**
	 * 获取缓存的值
	 * 
	 * @param k
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public V upValue(K k) {

		if (!containsKey(k)) {
			synchronized (this) {
				refresh();
			}

		}

		if (containsKey(k)) {
			return (V) cache.get(k).getObjectValue();
		} else {
			return upOne(k);
		}
	}

}
