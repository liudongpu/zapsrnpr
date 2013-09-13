package com.srnpr.zapweb.webmodel;

import java.util.List;

import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapcom.topapi.RootResult;
import com.srnpr.zapcom.topdo.TopUp;
import com.srnpr.zapweb.webface.IWebResult;

/**
 * 
 * 系统操作返回 默认resultCode结果为1 如果不为1则说明错误
 * 
 * @author srnpr
 * 
 */
public class MWebResult extends RootResult {

	/**
	 * 结果类型 默认为空 否则参照
	 */
	private String resultType = "";

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

	/**
	 * 操作结果
	 */
	private Object resultObject;

	/**
	 * 操作列表
	 */
	private List<Object> resultList;

	public boolean upFlagTrue() {
		return getResultCode() == 1;
	}

	/**
	 * 获取json格式表示
	 * 
	 * @return
	 */
	public String upJson() {
		return new JsonHelper<MWebResult>().ObjToString(this);
	}

	public void inErrorMessage(int iErrorCode, Object... sParms) {

		setResultCode(iErrorCode);

		setResultMessage(TopUp.upLogInfo(iErrorCode, sParms));
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

}
