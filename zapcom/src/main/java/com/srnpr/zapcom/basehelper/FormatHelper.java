package com.srnpr.zapcom.basehelper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 转换帮助类
 * 
 * @author srnpr
 * 
 */
public class FormatHelper {

	/**
	 * 联合查询条件
	 * 
	 * @param lStrings
	 * @return
	 */
	public static String joinWhereStrings(List<String> lStrings) {
		String[] sReturns = new String[lStrings.size()];
		for (int i = 0, j = lStrings.size(); i < j; i++) {
			sReturns[i] = lStrings.get(i) + "=:" + lStrings.get(i);
		}

		return StringUtils.join(sReturns, " and ");

	}

	/**
	 * @param sBaseString
	 *            输入字符串
	 * @param sFromStrings
	 *            替换字符串组
	 * @return 返回替换后结果
	 */
	public static String formatString(String sBaseString,
			Object... sFromStrings) {
		for (int i = 0, j = sFromStrings.length; i < j; i++) {
			sBaseString = sBaseString.replace("{" + (i) + "}",
					sFromStrings[i].toString());
		}
		return sBaseString;

	}

	/**
	 * 强制进制转换函数 用数字表示一个字符串或者反向转换
	 * 
	 * @param sInput
	 * @param sParam
	 * @return
	 */
	public static String convertFormatStringNumber(String sInput, String sParam) {

		char[] cNumber = sParam.toCharArray();
		char[] cInfo = sInput.toCharArray();
		int iNumLength = cNumber.length;

		BigInteger bNumLength = BigInteger.valueOf(iNumLength);

		BigInteger lReturnNum = BigInteger.ZERO;

		for (int i = 0, j = cInfo.length; i < j; i++) {
			int iNow = 0;
			for (int n = 0; n < iNumLength; n++) {
				if (cNumber[n] == cInfo[i]) {
					iNow = n + 1;
				}
			}

			lReturnNum = lReturnNum.add(BigInteger.valueOf(iNow).multiply(
					bNumLength.pow(j - i - 1)));

			// lReturnNum += iNow * Math.pow(iNumLength, j - i - 1);
		}

		return lReturnNum.toString();
	}

	public static String convertFormatNumberBack(String dSource, String sParam) {

		char[] cNumber = sParam.toCharArray();

		int iLength = cNumber.length;

		int iStep = 0;

		BigInteger bSource = new BigInteger(dSource);

		ArrayList<Integer> aList = new ArrayList<Integer>();

		while (bSource.divide(BigInteger.valueOf(iLength).pow(iStep))
				.compareTo(BigInteger.ONE) != -1) {

			int iNow = bSource
					.remainder(BigInteger.valueOf(iLength).pow(iStep + 1))
					.divide(BigInteger.valueOf(iLength).pow(iStep)).intValue();

			if (iNow == 0) {
				iNow = iLength;
			}

			bSource = bSource.subtract(BigInteger.valueOf(iNow).multiply(
					BigInteger.valueOf(iLength).pow(iStep)));

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

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String upDateTime() {
		return upDateTime(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 时间格式化
	 * 
	 * @param dDate
	 * @param sPattern
	 * @return
	 */
	public static String upDateTime(Date dDate, String sPattern) {
		SimpleDateFormat sFormat = new SimpleDateFormat(sPattern);
		return sFormat.format(dDate);
	}

	/**
	 * 时间格式化
	 * 
	 * @param sPattern
	 * @return
	 */
	public static String upDateTime(String sPattern) {
		SimpleDateFormat sFormat = new SimpleDateFormat(sPattern);
		return sFormat.format(new Date());
	}

	/**
	 * 获取16进制表示的当前时间
	 * 
	 * @return
	 */
	public static String upDateHex() {

		return Integer.toHexString(Integer.valueOf(upDateTime("yyMMdd")));
	}

	public static Date parseDate(String sDateTime) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return sdf.parse(sDateTime);
	}

	/**
	 * 获取url结构表示出来的字符串参数 a=b&c=d 表示为[a,b,c,d]
	 * 
	 * @param sUrl
	 * @return
	 */
	public static String[] upUrlStrings(String sUrl) {
		ArrayList<String> aList = new ArrayList<String>();
		if (sUrl.indexOf("?") > -1) {
			sUrl = sUrl.substring(sUrl.indexOf("?"));
		}
		for (String sIn : sUrl.split("&")) {
			aList.add(StringUtils.substringBefore(sIn, "="));

			aList.add(StringUtils.substringAfter(sIn, "="));
		}

		return aList.toArray(new String[] {});
	}

	public static String upReplaceSerialize(String sKey) {
		return sKey.replace("_", "").toLowerCase();
	}

	/**
	 * 获取sql格式化
	 * 
	 * @param sSql
	 * @return
	 */
	public static String upReplaceSql(String sSql) {
		return sSql.replaceAll(".*([';]+|(--)+).*", " ");
	}

}
