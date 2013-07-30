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

		List<MWebField> listFields = recheckFields(webPage.getPageFields(),
				mReqMap);

		List<String> listHeader = new ArrayList<String>();
		for (MWebField mField : listFields) {
			listHeader.add(mField.getFieldNote());
		}
		mReturnData.setPageHead(listHeader);

		MDataMap mQueryMap = new MDataMap();
		String sWhere = "";

		// 开始加载查询条件判断
		if (mReqMap.size() > 0) {

			ArrayList<String> aWhereStrings = new ArrayList<String>();

			for (MWebField mField : inquireData(webPage, mReqMap)) {

				switch (Integer.parseInt(mField.getQueryTypeAid())) {
				case 104009002:

					if (StringUtils.isNotEmpty(mReqMap.get(mField
							.getPageFieldName()
							+ WebConst.CONST_WEB_FIELD_AFTER + "between_from"))) {

						aWhereStrings.add(mField.getColumnName() + ">=:"
								+ mField.getColumnName()
								+ WebConst.CONST_WEB_FIELD_AFTER
								+ "between_from");
						mQueryMap.put(mField.getColumnName()
								+ WebConst.CONST_WEB_FIELD_AFTER
								+ "between_from", mReqMap.get(mField
										.getPageFieldName()
										+ WebConst.CONST_WEB_FIELD_AFTER + "between_from"));

					}
					
					
					if (StringUtils.isNotEmpty(mReqMap.get(mField
							.getPageFieldName()
							+ WebConst.CONST_WEB_FIELD_AFTER + "between_to"))) {

						aWhereStrings.add(mField.getColumnName() + "<=:"
								+ mField.getColumnName()
								+ WebConst.CONST_WEB_FIELD_AFTER
								+ "between_to");
						mQueryMap.put(mField.getColumnName()
								+ WebConst.CONST_WEB_FIELD_AFTER
								+ "between_to", mReqMap.get(mField
										.getPageFieldName()
										+ WebConst.CONST_WEB_FIELD_AFTER + "between_to"));

					}
					
					

					break;

				default:

					if (StringUtils.isNotEmpty(mField.getPageFieldValue())) {
						aWhereStrings.add(" " + mField.getColumnName()
								+ " like :" + mField.getColumnName());
						mQueryMap.put(mField.getColumnName(),
								"%" + mField.getPageFieldValue() + "%");
					}

					break;
				}

			}

			if (aWhereStrings.size() > 0) {
				sWhere = StringUtils.join(aWhereStrings, " and ");
			}

		}

		// 判断如果没有请求则重新统计数量
		if (mReturnData.getPageCount() < 0) {
			mReturnData.setPageCount(DbUp.upTable(webPage.getPageTable())
					.dataCount(sWhere, mQueryMap));

		}

		if (mReturnData.getPageMax() < 0) {
			mReturnData.setPageMax((int) Math.ceil((double) mReturnData
					.getPageCount() / (double) mReturnData.getPageSize()));
		}

		for (MDataMap mData : DbUp.upTable(webPage.getPageTable()).query("",
				"", sWhere, mQueryMap,
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
	private List<MWebField> recheckFields(List<MWebField> inputFields,
			MDataMap mReqMap) {
		List<MWebField> listReturnFields = new ArrayList<MWebField>();

		for (MWebField mField : inputFields) {
			if (!mField.getSort().equals("0")) {

				MWebField mCloneField = mField.clone();

				if (mReqMap.containsKey(mCloneField.getPageFieldName())) {
					mCloneField.setPageFieldValue(mReqMap.get(mCloneField
							.getPageFieldName()));
				}

				listReturnFields.add(mCloneField);

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

		List<MWebField> listPageFields = recheckFields(webPage.getPageFields(),
				mReqMap);

		return listPageFields;
	}

	public List<MWebField> inquireData(MWebPage webPage, MDataMap mReqMap) {

		MWebView mView = WebUp.upQueryView(webPage.getViewCode());
		List<MWebField> listFields = recheckFields(mView.getFields(), mReqMap);

		return listFields;

	}

}
