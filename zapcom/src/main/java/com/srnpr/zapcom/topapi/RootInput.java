package com.srnpr.zapcom.topapi;

import com.srnpr.zapcom.baseface.IBaseInput;

/**
 * 输入参数
 * 
 * @author srnpr
 * 
 */
public class RootInput implements IBaseInput {

	/**
	 * 版本标记 默认值为1 该参数用于扩展使用
	 */
	private int version = 1;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	/**
	 * 随机标记位  该参数用户防止缓存  由调用方随机生成
	 */
	private String random="";
	
	

}
