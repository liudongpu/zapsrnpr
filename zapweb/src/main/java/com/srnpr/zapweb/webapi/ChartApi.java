package com.srnpr.zapweb.webapi;

import java.util.ArrayList;
import java.util.List;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapcom.topapi.RootInput;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webfactory.UserFactory;
import com.srnpr.zapweb.webmodel.MPageData;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebPage;
import com.srnpr.zapweb.webmodel.MWebResult;
import com.srnpr.zapweb.webpage.PageExec;
import com.srnpr.zapweb.webpage.PageProcess;

public class ChartApi extends RootApi<ChartResult, ChartInput> {

	private static final PageExec page_exec = new PageExec();

	public ChartResult Process(ChartInput inputParam, MDataMap mRequestMap) {

		ChartResult mResult = new ChartResult();

		if (UserFactory.INSTANCE.checkUserLogin()) {

			String sPageCode = "";

			boolean bFlagRows = mRequestMap.containsKey("pagecode");

			if (bFlagRows) {
				sPageCode = mRequestMap.get("pagecode");
			} else {
				sPageCode = inputParam.getPageCode();
			}

			MWebPage webPage = WebUp.upPage(sPageCode);

			if (bFlagRows) {
				
				if(mRequestMap.containsKey("page"))
				{
					mRequestMap.put(WebConst.CONST_WEB_PAGINATION_NAME+"index", mRequestMap.get("page"));
				}
				if(mRequestMap.containsKey("rows"))
				{
					mRequestMap.put(WebConst.CONST_WEB_PAGINATION_NAME+"size", mRequestMap.get("rows"));
				}
				

				MPageData mPageData = page_exec.upChartData(webPage,
						mRequestMap, new MDataMap());

				mResult.setTotal(mPageData.getPageCount());

				List<MDataMap> mRowMaps = new ArrayList<MDataMap>();
				for (List<String> lRow : mPageData.getPageData()) {
					MDataMap map = new MDataMap();
					for (int i = 0, j = lRow.size(); i < j; i++) {
						map.put("f_" + String.valueOf(i), lRow.get(i));
					}
					mRowMaps.add(map);
				}

				mResult.setRows(mRowMaps);

			} else {
				// mResult.setPageFields(webPage.getPageFields());

				List<MWebField> listFields = page_exec.recheckFields(
						webPage.getPageFields(), mRequestMap);

				List<String> listHeader = new ArrayList<String>();
				for (MWebField mField : listFields) {
					listHeader.add(mField.getFieldNote());
				}

				mResult.setPageFields(listHeader);

			}

			// mResult.setPageData();
		}

		return mResult;

	}

}
