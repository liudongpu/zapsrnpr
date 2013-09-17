package com.srnpr.zapcom.basehelper;

import java.security.AlgorithmConstraints;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestHelper {

	private Log logger = LogFactory.getLog(this.getClass());

	public void bLogTest(Object... object) {

		// System.out.println(StringUtils.join(object));

		logger.info(StringUtils.join(object));

	}

	

}
