package com.srnpr.zapweb.websupport;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.RegexHelper;
import com.srnpr.zapcom.basemodel.MStringMap;
import com.srnpr.zapcom.topdo.TopConst;
import com.srnpr.zapcom.topdo.TopDir;
import com.srnpr.zapcom.topdo.TopUp;
import com.srnpr.zapweb.webdo.WebConst;

/**
 * 文件合并类 该类主要合并resources路径下的各种静态文件
 * 
 * @author srnpr
 * 
 */
public class FileConcatSupport extends BaseClass {

	/**
	 * 根据配置文件执行静态文件合并操作
	 */
	public void concatByConfig() {

		String sConfigName = "zapweb.fileconcat";

		MStringMap mConcatMap = TopUp.upConfigMap(sConfigName);

		if (mConcatMap != null && mConcatMap.size() > 0) {
			bLogInfo(969905201, sConfigName);

			String sBasePath = new TopDir().upServerletPath("resources/");

			String sTargetPath = new TopDir()
					.upServerletPath("resources/fileconcat/");

			bLogInfo(969905201, sBasePath);

			try {
				for (String sKey : mConcatMap.keySet()) {
					if (sKey.startsWith("js-")) {
						concatFile(sBasePath, mConcatMap.get(sKey), sTargetPath
								+ sKey + ".js");

					}

					if (sKey.startsWith("css-")) {
						concatFile(sBasePath, mConcatMap.get(sKey), sTargetPath
								+ sKey + ".css");

					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private void concatFile(String sBasePath, String sSourceFile,
			String sTargetFile) throws IOException {

		StringBuffer sBuffer = new StringBuffer();

		for (String sSource : sSourceFile.split(",")) {
			// 去掉引号
			sSource = StringUtils.replace(sSource, "\"", "");

			String sContent = FileUtils.readFileToString(new File(sBasePath
					+ sSource),TopConst.CONST_BASE_ENCODING);

			// 判断如果是css文件 则格式化其中的相对图片路径
			if (sSource.endsWith(".css")) {

				Matcher matcher = Pattern.compile("url\\(['\"](.*?)['\"]\\)")
						.matcher(sContent);

				// 如果路径中有相对路径 替换掉相对路径
				String sCheckLink = "/../";

				String sCssPath = "../"
						+ StringUtils.substringBeforeLast(sSource, "/") + "/";
				while (matcher.find()) {
					String sInfo = matcher.group();
					String sLink = matcher.group(1);

					String sNewLink = sCssPath + sLink;

					while (sNewLink.contains(sCheckLink)) {

						sNewLink = StringUtils.substringBeforeLast(StringUtils
								.substringBefore(sNewLink, sCheckLink), "/")
								+ "/"
								+ StringUtils.substringAfter(sNewLink,
										sCheckLink);
					}

					String sNewInfo = sInfo.replace(sLink, sNewLink);
					sContent = StringUtils.replace(sContent, sInfo, sNewInfo);

				}

			}

			sBuffer.append(sContent);

		}

		FileUtils.writeStringToFile(new File(sTargetFile), sBuffer.toString(),TopConst.CONST_BASE_ENCODING);

	}

}
