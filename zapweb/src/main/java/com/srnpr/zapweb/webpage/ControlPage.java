package com.srnpr.zapweb.webpage;

import java.util.List;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webmodel.MPageData;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebPage;

public class ControlPage {

	/**
	 * 输入参数
	 */
	private MDataMap reqMap = new MDataMap();

	/**
	 * 页面实体
	 */
	private MWebPage webPage = new MWebPage();

	/**
	 * 页面操作类
	 */
	private final static PageExec pageExec = new PageExec();

	public MDataMap getReqMap() {
		return reqMap;
	}

	public void setReqMap(MDataMap reqMap) {
		this.reqMap = reqMap;
	}

	public MWebPage getWebPage() {
		return webPage;
	}

	public void setWebPage(MWebPage webPage) {
		this.webPage = webPage;
	}

	/**
	 * 得到列表页数据
	 * @return
	 */
	public MPageData upChartData() {
		return pageExec.chartData(webPage.getViewCode(), reqMap);
	}

	/**
	 * 得到添加页数据
	 * @return
	 */
	public List<MWebField> upAddData() {
		return pageExec.addData(webPage.getViewCode(), reqMap);
	}

}
