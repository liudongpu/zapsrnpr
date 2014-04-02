package com.srnpr.zapweb.webclass;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.ietf.jgss.Oid;

import us.codecraft.webmagic.selector.Html;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapcom.basehelper.RegexHelper;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webmodel.MWebResult;

public class WebCheck extends BaseClass implements IBaseInstance {

	/**
	 * 检查内容中是否存在非允许链接
	 * 
	 * @param sSource
	 *            要检查的内容
	 * @param sAllow
	 *            允许的域名后缀
	 * @param sDangerRegex
	 *            恶意代码表达式
	 * @return
	 */
	public MWebResult checkLinks(String sSource, String sAllow,
			String sDangerRegex) {
		MWebResult mResult = new MWebResult();

		sSource = StringUtils.lowerCase(sSource);
		if (sSource.indexOf("<") > -1) {
			Html htmlSource = new Html(sSource);

			List<String> listCheck = new ArrayList<String>();

			listCheck.addAll(htmlSource.xpath("//a/@href").all());
			listCheck.addAll(htmlSource.xpath("//img/@src").all());

			if (listCheck.size() > 0) {

				String[] strAllowStrings = sAllow
						.split(WebConst.CONST_SPLIT_COMMA);

				for (String sCheck : listCheck) {
					// 判断如果是空 则跳过该链接
					if (StringUtils.isEmpty(sCheck)) {
						continue;
					}
					// 判断如果是以#号开始 则跳过处理
					if (StringUtils.startsWith(sCheck, "#")) {
						continue;
					}

					// 将链接中的#号替换为空

					String sRegexCheck = sCheck;

					// 以下逻辑用于获取链接地址的hostname

					// 获取链接的http:// 或者https://之后的内容
					sRegexCheck = StringUtils.substringAfter(sRegexCheck, "//");
					sRegexCheck = StringUtils.substringBefore(sRegexCheck, "#");
					sRegexCheck = StringUtils.substringBefore(sRegexCheck, "?");
					sRegexCheck = StringUtils.substringBefore(sRegexCheck, "/");

					if (!StringUtils.endsWithAny(sRegexCheck, strAllowStrings)) {
						mResult.setResultCode(969905120);
						mResult.inErrorMessage(969905120, sCheck);
						break;
					}

				}
			}
		}

		// 开始校验脚本之类的过滤内容
		if (mResult.upFlagTrue()) {
			// String[] sDangerHtml=new
			// StringUtils()[]{"<script.*?>","<iframe.*?>"};
			String[] sDanger = sDangerRegex.split(WebConst.CONST_SPLIT_COMMA);

			String sDangerString = RegexHelper.upRegexExist(sSource, sDanger);

			if (StringUtils.isNotEmpty(sDangerString)) {
				sDangerString=RegexHelper.upReplaceShowHtml(sDangerString);
				mResult.setResultCode(969905121);
				mResult.inErrorMessage(969905121, sDangerString);
			}

		}

		return mResult;

	}

}
