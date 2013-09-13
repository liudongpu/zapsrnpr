package com.srnpr.zapcom.topapi;

import com.srnpr.zapcom.baseface.IBaseResult;

/**
 * 返回消息
 * 
 * @author srnpr
 * 
 */
public class RootResult implements IBaseResult {

	/**
	 * 返回标记 如果该标记为1则表明返回结果正确 否则都是错误消息
	 */
	private int resultCode = 1;

	/**
	 * 返回消息 一般用于返回错误描述或者操作提示
	 */
	private String resultMessage = "";

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

}
