package com.srnpr.zapcom.rootclass;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseCache;
import com.srnpr.zapcom.baseface.IBaseDestory;
import com.srnpr.zapcom.baseface.IBaseInit;

/**
 * 各种初始化调用类
 * @author srnpr
 *
 */
public abstract class RootInit extends BaseClass implements IBaseInit,
		IBaseDestory {

	public RootInit() {
		bLogInfo(0, "init class : " + this.getClass().getName());
	}

	/**
	 * 初始化缓存
	 * 
	 * @param baseCaches
	 */
	public void topInitCache(IBaseCache... baseCaches) {
		for (IBaseCache iCache : baseCaches) {

			iCache.refresh();
		}
	}

	public boolean init() {
		return onInit();
	}

	/**
	 * 当系统初始化时调用
	 * @return
	 */
	public abstract boolean onInit();

	public boolean destory() {
		return onDestory();
	}

	/**
	 * 当容器关闭时调用
	 * @return
	 */
	public abstract boolean onDestory();

}
