package com.srnpr.zapweb.demo;

import com.srnpr.zapcom.topapi.RootResult;

/**
 * Demo API返回结果类
 * 
 * @author srnpr
 * 
 */
public class DemoApiResult extends RootResult {

	/**
	 * 返回结果一号
	 */
	private String resultFiledOne = "";

	/**
	 * 返回2吧
	 */
	private int resultFieldTwo = 2;

	public String getResultFiledOne() {
		return resultFiledOne;
	}

	public void setResultFiledOne(String resultFiledOne) {
		this.resultFiledOne = resultFiledOne;
	}

	public int getResultFieldTwo() {
		return resultFieldTwo;
	}

	public void setResultFieldTwo(int resultFieldTwo) {
		this.resultFieldTwo = resultFieldTwo;
	}

}
