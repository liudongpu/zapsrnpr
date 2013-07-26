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

		String sPaginationField = WebConst.CONST_WEB_PAGINATION_NAME;

		if (mReqMap.containsKey(sPaginationField + "count")) {
			mReturnData.setPageCount(Integer.valueOf(mReqMap
					.get(sPaginationField + "count")));
		}
		if (mReqMap.containsKey(sPaginationField + "index")) {
			mReturnData.setPageIndex(Integer.valueOf(mReqMap
					.get(sPaginationField + "index")));
		}

		List<List<String>> listData = new ArrayList<List<String>>();

		List<MWebField> listFields = recheckFields(webPage.getPageFields());

		List<String> listHeader = new ArrayList<String>();
		for (MWebField mField : listFields) {
			listHeader.add(mField.getFieldNote());
		}
		mReturnData.setPageHead(listHeader);

		MDataMap mQueryMap = new MDataMap();

		if (mReturnData.getPageCount() < 0) {
			mReturnData.setPageCount(DbUp.upTable(webPage.getPageTable())
					.count());

		}

		if (mReturnData.getPageMax() < 0) {
			mReturnData.setPageMax((int) Math.ceil((double) mReturnData
					.getPageCount() / (double) mReturnData.getPageSize()));
		}

		for (MDataMap mData : DbUp.upTable(webPage.getPageTable()).query("",
				"", "", mQueryMap,
				(mReturnData.getPageIndex() - 1) * mReturnData.getPageSize(),
				mReturnData.getPageSize())) {
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
				listReturnFields.add(mField.clone());
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

		List<MWebField> listPageFields = recheckFields(webPage.getPageFields());

		return listPageFields;
	}

	public List<MWebField> inquireData(MWebPage webPage, MDataMap mReqMap) {

		MWebView mView = WebUp.upQueryView(webPage.getViewCode());
		List<MWebField> listFields = recheckFields(mView.getFields());

		return listFields;

	}

}
