package com.srnpr.zapcom.basehelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.srnpr.zapcom.baseface.IBaseHelper;

public class RegexHelper implements IBaseHelper {

	/**
	 * 将符合sregs正则的定义替换为空
	 * 
	 * @param sSource
	 * @param sRegs
	 * @return
	 */
	public static String upRegexEmpty(String sSource, String... sRegs) {

		for (String s : sRegs) {
			// sSource = sSource.replaceAll(s, "");

			sSource = Pattern.compile(s, Pattern.CASE_INSENSITIVE)
					.matcher(sSource).replaceAll("");

		}

		return sSource;

	}

	/**
	 * 获取替换内容
	 * 
	 * @param sSource
	 * @param sRegs
	 * @return
	 */
	public static String upReplaceContent(String sSource, String... sRegs) {

		for (String s : sRegs) {

			Matcher matcher = Pattern.compile(s).matcher(sSource);
			while (matcher.find()) {
				sSource = sSource.replace(matcher.group(0), matcher.group(1));
			}

		}
		return sSource;

	}

	public static String upReplaceAll(String sSource, String sReg, String sVal) {

		return sSource.replaceAll(sReg, sVal);

	}

	/**
	 * 过滤html输入内容
	 * 
	 * @param sSource
	 * @return
	 */
	public static String upScanHtml(String sSource) {
		return upRegexEmpty(sSource, "<script.*?</script.*?>",
				"href.*?=.*?script.*?:", "<iframe.*?</iframe.*?>",
				"<frameset.*?</frameset.*?>");
	}
	
	/**
	 * 过滤html输入内容
	 * 
	 * @param sSource
	 * @return
	 */
	public static String upScanHtmlSimple(String sSource) {
		return upRegexEmpty(sSource, "<script.*?</script.*?>","<iframe.*?</iframe.*?>",
				"<frameset.*?</frameset.*?>");
	}

	/**
	 * 替换html中的标记输出展示
	 * 
	 * @param sSource
	 * @return
	 */
	public static String upReplaceShowHtml(String sSource) {
		return sSource.replace("<", "&lt;").replace(">", "&gt;");
	}

	/**
	 * 判断是否正则能匹配输入 如果输入中存在 返回匹配的内容 否则返回空
	 * 
	 * @param sSource
	 * @param sRegs
	 * @return
	 */
	public static String upRegexExist(String sSource, String... sRegs) {
		String sReturn = "";

		for (String s : sRegs) {

			Matcher matcher = Pattern.compile(s, Pattern.CASE_INSENSITIVE)
					.matcher(sSource);

			if (matcher.find()) {
				sReturn = matcher.group();
				break;
			}

		}

		return sReturn;
	}

	/**
	 * 获取html内容的文本表示
	 * 
	 * @param sSource
	 * @return
	 */
	public static String upHtmlText(String sSource) {

		return upRegexEmpty(sSource, "<[^>]+>");
	}

}
