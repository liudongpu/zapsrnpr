package com.srnpr.zapweb.webpage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webmodel.MPageData;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebOperate;
import com.srnpr.zapweb.webmodel.MWebPage;
import com.srnpr.zapweb.webmodel.MWebView;

/**
 * 页面执行类 该类主要封装各种页面获取操作
 * 
 * @author srnpr
 * 
 */
public class PageExec extends RootExec {

	/**
	 * 列表页数据
	 * 
	 * @param webPage
	 * @param mReqMap
	 * @return
	 */
	public MPageData chartData(MWebPage webPage, MDataMap mReqMap) {

		MDataMap mOptionMap = new MDataMap();
		return upChartData(webPage, mReqMap, mOptionMap);

	}

	

	

	/**
	 * 添加页面数据
	 * 
	 * @param webPage
	 * @param mReqMap
	 * @return
	 */
	public List<MWebField> addData(MWebPage webPage, MDataMap mReqMap) {

		List<MWebField> listPageFields = recheckFields(webPage.getPageFields(),
				mReqMap);

		for (MWebField mField : listPageFields) {
			// 判断如果是组件则重新输出文字
			if (mField.getFieldTypeAid().equals("104005003")) {
				mField.setPageFieldValue(WebUp.upComponent(
						mField.getSourceCode()).upAddText(mField, mReqMap));

			}
		}

		return listPageFields;
	}

	public List<MWebField> inquireData(MWebPage webPage, MDataMap mReqMap) {

		return upInquireData(webPage, mReqMap);

	}

}
