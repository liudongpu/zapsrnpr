package com.srnpr.zapcom.basehelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class IoHelper {

	/**
	 * @param sDir
	 *            目录地址 创建目录
	 */
	public static void createDir(String sDir) {
		try {
			FileUtils.forceMkdir(new File(sDir));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Resource[] upResources(String sResourceName) throws IOException {

		Resource[] resources = null;

		PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();

		resources = patternResolver.getResources(sResourceName);

		return resources;
	}

	public void copyResources(String sFromClass, String sToPath, String sKeyName) {
		try {
			Resource[] resources = upResources(sFromClass);

			for (Resource r : resources) {

				String sUrlString = r.getURI().toString();
				sUrlString = sUrlString.substring(sUrlString
						.lastIndexOf(sKeyName) + sKeyName.length());

				InputStream inStream = r.getInputStream(); // 读入原文件

				new File(sToPath + sUrlString).getParentFile().mkdirs();
				FileOutputStream fs = new FileOutputStream(sToPath +"/"+ sUrlString);

				IOUtils.copy(inStream, fs);
				fs.flush();
				fs.close();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
