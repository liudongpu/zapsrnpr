package com.srnpr.zapcom.basehelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.srnpr.zapcom.baseface.IBaseHelper;

public class RegexHelper implements IBaseHelper {

	public static String upRegexEmpty(String sSource, String... sRegs) {

		for (String s : sRegs) {
			//sSource = sSource.replaceAll(s, "");
			
			sSource=Pattern.compile(s,Pattern.CASE_INSENSITIVE).matcher(sSource).replaceAll("");

		}

		return sSource;

	}

	public static String upReplaceContent(String sSource, String... sRegs) {

		for (String s : sRegs) {

			Matcher matcher = Pattern.compile(s).matcher(sSource);
			while (matcher.find()) {
				sSource = sSource.replace(matcher.group(0), matcher.group(1));
			}

		}
		return sSource;

	}
	public static String upReplaceAll(String sSource, String sReg,String sVal) {
		
		
	return 	sSource.replaceAll(sReg, sVal);
		
	}
	
	
	/**
	 * 过滤html输入内容
	 * @param sSource
	 * @return
	 */
	public static String upScanHtml(String sSource)
	{
		return upRegexEmpty(sSource, "<script.*?</script.*?>","href.*?=.*?script.*?:","<iframe.*?</iframe.*?>","<frameset.*?</frameset.*?>");
	}
	
	
	
	

}
