package com.srnpr.zapcom.basehelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class FormatHelper {




	/**
	 * @param sBaseString 输入字符串
	 * @param sFromStrings  替换字符串组
	 * @return 返回替换后结果
	 */
	public static String formatString(String sBaseString,
			String... sFromStrings) {
		for (int i = 0, j = sFromStrings.length; i < j; i++) {
			sBaseString = sBaseString.replace("{" + (i) + "}", sFromStrings[i]);
		}
		return sBaseString;

	}

	

	/**
	 * 强制进制转换函数 用数字表示一个字符串或者反向转换
	 * @param sInput 
	 * @param sParam
	 * @return 
	 */
	public static long convertFormatStringNumber(String sInput, String sParam) {

		char[] cNumber = sParam.toCharArray();
		char[] cInfo = sInput.toCharArray();
		int iNumLength = cNumber.length;
		long lReturnNum = 0;
		for (int i = 0, j = cInfo.length; i < j; i++) {
			int iNow = 0;
			for (int n = 0; n < iNumLength; n++) {
				if (cNumber[n] == cInfo[i]) {
					iNow = n + 1;
				}
			}
			lReturnNum += iNow * Math.pow(iNumLength, j - i - 1);
		}

		return lReturnNum;
	}

	/**
	 * @param dSource
	 * @param sParam
	 * @return
	 */
	public static String convertFormatStringNumber(Double dSource, String sParam) {

		char[] cNumber = sParam.toCharArray();

		int iLength = cNumber.length;

		int iStep = 0;

		ArrayList<Integer> aList = new ArrayList<Integer>();

		while (dSource / Math.pow(iLength, iStep) >= 1) {
			int iNow = (int) ((dSource % (Math.pow(iLength, iStep + 1))) / Math
					.pow(iLength, iStep));
			if (iNow == 0) {
				iNow = iLength;
			}

			dSource = dSource - iNow * Math.pow(iLength, iStep);
			aList.add(iNow);
			iStep++;
		}

		StringBuffer sBuffer = new StringBuffer();
		for (int i = aList.size() - 1; i >= 0; i--) {
			if (aList.get(i) == 0) {
				aList.set(i, iLength);
			}

			sBuffer.append(cNumber[aList.get(i) - 1]);
		}

		return sBuffer.toString();
	}

}
