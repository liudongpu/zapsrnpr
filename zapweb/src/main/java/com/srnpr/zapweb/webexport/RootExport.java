package com.srnpr.zapweb.webexport;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webmodel.MPageData;
import com.srnpr.zapweb.webmodel.MWebPage;
import com.srnpr.zapweb.webpage.PageExec;
import com.srnpr.zapweb.webpage.RootProcess;

public abstract class RootExport extends RootProcess {

	/**
	 * 导出文件名
	 */
	private String exportName = "";

	private MPageData pageData = new MPageData();

	private HttpServletResponse httpServletResponse = null;

	public void exportExcel(String sOperateId, HttpServletRequest request,
			HttpServletResponse response) {

		MWebPage mPage = WebUp.upPage(sOperateId);

		MDataMap mReqMap = convertRequest(request);

		PageExec pExec = new PageExec();

		MDataMap mOptionMap = new MDataMap("optionExport", "1");

		pageData = pExec.upChartData(mPage, mReqMap, mOptionMap);

		httpServletResponse = response;
	}

	public void doExport() {

		exportExcelFile(pageData, httpServletResponse);
	}

	public void exportExcelFile(MPageData mPageData,
			HttpServletResponse hResponse) {

		if (StringUtils.isEmpty(exportName)) {
			exportName = "export-"
					+ FormatHelper
							.upDateTime(new Date(), "yyyy-MM-dd-HH-mm-ss");
		}

		hResponse.setContentType("application/binary;charset=ISO8859_1");
		try {
			exportName = new String(exportName.getBytes(), "ISO8859_1");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		hResponse.setHeader("Content-disposition", "attachment; filename="
				+ exportName + ".xls");// 组装附件名称和格式

		ServletOutputStream outputStream = null;
		try {
			outputStream = hResponse.getOutputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}

		HSSFWorkbook wb = new HSSFWorkbook();// 建立新HSSFWorkbook对象

		HSSFSheet sheet = wb.createSheet("excel");

		int iNowRow = 0;

		HSSFRow headRow = sheet.createRow(iNowRow);

		for (int i = 0, j = mPageData.getPageHead().size(); i < j; i++) {
			HSSFCell hCell = headRow.createCell(i);
			hCell.setCellValue(mPageData.getPageHead().get(i));
		}

		HSSFCell cell = null;

		for (List<String> lRow : mPageData.getPageData()) {
			iNowRow++;
			HSSFRow hRow = sheet.createRow(iNowRow);
			for (int i = 0, j = lRow.size(); i < j; i++) {
				HSSFCell hCell = hRow.createCell(i);
				hCell.setCellValue(lRow.get(i));
			}

		}

		try {
			wb.write(outputStream);

			outputStream.flush();
			outputStream.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getExportName() {
		return exportName;
	}

	public void setExportName(String exportName) {
		this.exportName = exportName;
	}

	public MPageData getPageData() {
		return pageData;
	}

	public void setPageData(MPageData pageData) {
		this.pageData = pageData;
	}

}
