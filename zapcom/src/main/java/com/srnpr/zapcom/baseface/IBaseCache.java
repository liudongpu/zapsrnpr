package com.srnpr.zapcom.baseface;

public interface IBaseCache {

	
	/**
	 * 刷新缓存 该方法一向定义为synchronized
	 */
	public void refresh();
}
