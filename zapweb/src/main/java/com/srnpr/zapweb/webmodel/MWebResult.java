package com.srnpr.zapweb.webmodel;

import java.util.List;

import com.srnpr.zapcom.topdo.TopUp;

/**
 * 
 * 系统操作返回 默认resultCode结果为1 如果不为1则说明错误
 * 
 * @author srnpr
 * 
 */
public class MWebResult {

	/**
	 * 操作标记  默认为1  不为1则表示错误
	 */
	private int resultCode = 1;

	/**
	 * 返回消息
	 */
	private String resultMessage = "";
	
	
	/**
	 * 结果类型  默认为空  否则参照
	 */
	private String resultType="";
	


	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public Object getResultObject() {
		return resultObject;
	}

	public void setResultObject(Object resultObject) {
		this.resultObject = resultObject;
	}

	public List<Object> getResultList() {
		return resultList;
	}

	public void setResultList(List<Object> resultList) {
		this.resultList = resultList;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * 操作结果
	 */
	private Object resultObject;

	/**
	 * 操作列表
	 */
	private List<Object> resultList;
	
	
	public boolean upFlagTrue()
	{
		return resultCode==1;
	}
	
	public void inErrorMessage(int iErrorCode,String sParms)
	{
		resultCode=iErrorCode;
		resultMessage =TopUp.upLogInfo(iErrorCode, sParms);
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	
	
}
