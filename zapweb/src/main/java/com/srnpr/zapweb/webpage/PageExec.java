package com.srnpr.zapweb.webpage;

import java.util.ArrayList;
import java.util.List;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webmodel.MPageData;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebView;

public class PageExec {

	public MPageData chartData(String sViewCode, MDataMap mReqMap) {

		MPageData mReturnData = new MPageData();

		MWebView mView = WebUp.upViewCache(sViewCode + "-116022003");

		List<List<String>> listData = new ArrayList<List<String>>();

		List<MWebField> listFields = recheckFields(mView.getFields());
		List<String> listHeader = new ArrayList<String>();
		for (MWebField mField : listFields) {
			listHeader.add(mField.getFieldNote());
		}
		mReturnData.setPageHead(listHeader);

		for (MDataMap mData : DbUp.upTable(mView.getTableName()).queryByWhere()) {
			List<String> listEach = new ArrayList<String>();

			for (MWebField mField : listFields) {

				listEach.add(mData.get(mField.getColumnName()));
			}
			
			listData.add(listEach);

		}

		mReturnData.setPageData(listData);
		return mReturnData;

	}

	private List<MWebField> recheckFields(List<MWebField> inputFields) {
		List<MWebField> listReturnFields = new ArrayList<MWebField>();

		for (MWebField mField : inputFields) {
			if (!mField.getSort().equals("0")) {
				listReturnFields.add(mField);
			}
		}
		return listReturnFields;
	}

}
