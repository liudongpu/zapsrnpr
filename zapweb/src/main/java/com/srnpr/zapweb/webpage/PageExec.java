package com.srnpr.zapweb.webpage;

import java.util.ArrayList;
import java.util.List;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webmodel.MPageData;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebPage;
import com.srnpr.zapweb.webmodel.MWebView;

public class PageExec {

	/**
	 * 列表页数据
	 * 
	 * @param webPage
	 * @param mReqMap
	 * @return
	 */
	public MPageData chartData(MWebPage webPage, MDataMap mReqMap) {

		MPageData mReturnData = new MPageData();

		List<List<String>> listData = new ArrayList<List<String>>();

		List<MWebField> listFields = recheckFields(webPage.getPageFields());
		List<String> listHeader = new ArrayList<String>();
		for (MWebField mField : listFields) {
			listHeader.add(mField.getFieldNote());
		}
		mReturnData.setPageHead(listHeader);

		for (MDataMap mData : DbUp.upTable(webPage.getPageTable())
				.queryByWhere()) {
			List<String> listEach = new ArrayList<String>();

			for (MWebField mField : listFields) {

				listEach.add(mData.get(mField.getColumnName()));
			}

			listData.add(listEach);

		}

		mReturnData.setPageData(listData);
		return mReturnData;

	}

	/**
	 * 重新审查字段
	 * 
	 * @param inputFields
	 * @return
	 */
	private List<MWebField> recheckFields(List<MWebField> inputFields) {
		List<MWebField> listReturnFields = new ArrayList<MWebField>();

		for (MWebField mField : inputFields) {
			if (!mField.getSort().equals("0")) {
				listReturnFields.add(mField);
			}
		}
		return listReturnFields;
	}

	/**
	 * 添加页面数据
	 * 
	 * @param webPage
	 * @param mReqMap
	 * @return
	 */
	public List<MWebField> addData(MWebPage webPage, MDataMap mReqMap) {

		List<MWebField> listFields = recheckFields(webPage.getPageFields());

		List<MWebField> listPageFields = new ArrayList<MWebField>();

		for (MWebField mWebField : listFields) {
			MWebField mPageField = mWebField.clone();
			listPageFields.add(mPageField);
		}

		return listPageFields;
	}

}
