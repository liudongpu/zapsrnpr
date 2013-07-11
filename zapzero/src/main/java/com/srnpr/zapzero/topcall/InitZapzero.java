package com.srnpr.zapzero.topcall;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.IoHelper;
import com.srnpr.zapcom.rootclass.RootInit;
import com.srnpr.zapcom.topdo.TopDir;

public class InitZapzero extends RootInit {

	public void init() {
		initFiles();
	}

	private void initFiles() {

		String sTempConfigString = new TopDir().upServerletPath("zapzero");

		if (StringUtils.isNotEmpty(sTempConfigString)) {
			IoHelper ioHelper = new IoHelper();
			ioHelper.copyResources("classpath*:META-INF/zapzero/**/*.*",
					sTempConfigString, "/zapzero/");
		} else {
			bLogDebug(990212001);
		}

	}

}
