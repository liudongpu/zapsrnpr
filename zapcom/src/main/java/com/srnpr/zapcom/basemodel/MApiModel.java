package com.srnpr.zapcom.basemodel;

public class MApiModel {

	private Class<?> apiClass = null;

	private Class<?> inputClass = null;

	private Class<?> resultClass = null;

	public Class<?> getApiClass() {
		return apiClass;
	}

	public void setApiClass(Class<?> apiClass) {
		this.apiClass = apiClass;
	}

	public Class<?> getInputClass() {
		return inputClass;
	}

	public void setInputClass(Class<?> inputClass) {
		this.inputClass = inputClass;
	}

	public Class<?> getResultClass() {
		return resultClass;
	}

	public void setResultClass(Class<?> resultClass) {
		this.resultClass = resultClass;
	}

}
