package com.srnpr.zapweb.webpage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basemodel.MKvdList;
import com.srnpr.zapcom.basemodel.MKvdModel;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webmodel.MPageData;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebPage;
import com.srnpr.zapweb.webmodel.MWebSource;

public class ControlPage {

	/**
	 * 页面链接
	 */
	private String pageUrl = "";

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
	 * 
	 * @return
	 */
	public MPageData upChartData() {
		return pageExec.chartData(webPage, reqMap);
	}

	/**
	 * 得到添加页数据
	 * 
	 * @return
	 */
	public List<MWebField> upAddData() {
		return pageExec.addData(webPage, reqMap);
	}

	/**
	 * 查询区域
	 * 
	 * @return
	 */
	public List<MWebField> upInquireData() {
		return pageExec.inquireData(webPage, reqMap);
	}

	public String upReplaceUrl(String sUrl, String... sReplace) {

		if (StringUtils.isEmpty(sUrl)) {
			sUrl = pageUrl;
		}

		String[] sSplitUrl = StringUtils.split(sUrl, "?");

		MDataMap mQueryMap = new MDataMap();
		if (sSplitUrl.length > 1) {

			for (String s : sSplitUrl[1].split("&")) {
				mQueryMap.put(StringUtils.substringBefore(s, "="), s);
			}
		}

		for (String s : sReplace) {
			mQueryMap.put(StringUtils.substringBefore(s, "="), s);
		}

		sUrl = sSplitUrl[0] + "?" + StringUtils.join(mQueryMap.values(), "&");

		return sUrl;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	/**
	 * 获取数据源
	 * @param mField
	 * @return
	 */
	public List<MKvdModel> upDataSource(MWebField mField) {

		MKvdList mReturnList = new MKvdList();

		MWebSource mSource = WebUp.upSource(mField.getSourceCode());

		MDataMap mWhereMap = new MDataMap();

		for (MDataMap mDataMap : DbUp.upTable(mSource.getSourceFrom())
				.queryAll(
						mSource.getFieldText() + " as fieldText, "
								+ mSource.getFieldValue() + " as fieldValue",
						mSource.getFieldSort(), "", mWhereMap)) {

			mReturnList.inElement(mDataMap.get("fieldText"),
					mDataMap.get("fieldValue"));

		}

		return mReturnList.getChildList();
	}
	
	
	/**
	 * 获取请求值
	 * @param sKey
	 * @return
	 */
	public String upReqValue(String sKey)
	{
		return reqMap.get(sKey);
	}
	
	
	
	
	

}
