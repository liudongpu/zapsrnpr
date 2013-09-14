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

}
