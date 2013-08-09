package com.srnpr.zapweb.webpage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basehelper.MapHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
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

	/**
	 * 修改页面数据
	 * 
	 * @param mWebPage
	 * @param mReqMap
	 * @return
	 */
	public List<MWebField> editData(MWebPage mWebPage, MDataMap mReqMap) {

		List<MWebField> listPageFields = recheckFields(
				mWebPage.getPageFields(), mReqMap);

		String sEditKeyString = WebConst.CONST_WEB_FIELD_NAME + "uid";

		String sUid = mReqMap.containsKey(sEditKeyString) ? mReqMap
				.get(sEditKeyString) : "";

		if (StringUtils.isNotEmpty(sUid)) {

			MDataMap mEditDataMap = DbUp.upTable(mWebPage.getPageTable())
					.oneWhere(WebHelper.upFieldSql(listPageFields), "", "",
							"uid", sUid);

			for (MWebField mField : listPageFields) {
				// 判断如果是组件则重新输出文字
				if (mField.getFieldTypeAid().equals("104005003")) {
					mField.setPageFieldValue(WebUp.upComponent(
							mField.getSourceCode()).upEditText(mField,
							mEditDataMap));
				} else {

					if (mEditDataMap.containsKey(mField.getFieldName())) {

						mField.setPageFieldValue(mEditDataMap.get(mField
								.getFieldName()));
					}

				}
			}

		}

		return listPageFields;
	}

	public List<MWebField> inquireData(MWebPage webPage, MDataMap mReqMap) {

		return upInquireData(webPage, mReqMap);

	}

}
