package com.srnpr.zapweb.webexport;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webmodel.MPageData;
import com.srnpr.zapweb.webmodel.MWebPage;
import com.srnpr.zapweb.webpage.PageExec;

public class ExportChart extends RootExport {

	public void exportExcel(String sOperateId, HttpServletRequest request,
			HttpServletResponse response) {

		MWebPage mPage = WebUp.upPage(sOperateId);

		MDataMap mReqMap = convertRequest(request);

		PageExec pExec = new PageExec();

		MPageData mPageData= pExec.chartData(mPage, mReqMap);

		exportExcelFile(mPageData, response);

	}

}
