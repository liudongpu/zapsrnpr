package com.srnpr.zapweb.demo;

import java.util.List;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.basemodel.MAnnotationClass;
import com.srnpr.zapcom.topapi.RootInput;
import com.srnpr.zapweb.webmodel.MWebResult;

/**
 * API调用测试类
 * 
 * @author srnpr
 * 
 */
public class DemoApiInput extends RootInput {

	/**
	 * demo输入字段一号
	 */
	@ZapcomApi(value = "输入字段", remark = "该字段仅供示例使用")
	private String demoInputFirst = "";

	/**
	 * demo输入数字
	 */
	@ZapcomApi(value = "输入2号", remark = "如果有雷同，纯属巧合")
	private int demoInputInt = 0;
	
	@ZapcomApi(value = "测试子结构", remark = "测啊测")
	private List<RootInput> list;
	
	
	@ZapcomApi(value = "测试MAnnotationClass", remark = "测啊测")
	private MWebResult man;
	

	public String getDemoInputFirst() {
		return demoInputFirst;
	}

	public void setDemoInputFirst(String demoInputFirst) {
		this.demoInputFirst = demoInputFirst;
	}

	public int getDemoInputInt() {
		return demoInputInt;
	}

	public void setDemoInputInt(int demoInputInt) {
		this.demoInputInt = demoInputInt;
	}

}
