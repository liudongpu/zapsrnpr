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

	public String upThreadName() {
		return Thread.currentThread().getName();
	}

	/**
	 * @param tRun
	 * @param iNumber
	 */
	public void ThreadTest(Runnable tRun, int iNumber) {

		if (iNumber < 0) {

			Thread thread = new Thread(tRun);

			// thread.start();

			for (int i = 0; i < 0 - iNumber; i++) {

				bLogTest("test" + i);

				thread.run();
			}

			try {

				thread.join();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			List<Thread> list = new ArrayList<Thread>();

			for (int i = 0; i < iNumber; i++) {

				Thread a1 = new Thread(tRun, "test_thread_" + i);

				//bLogTest("test" + i);
				a1.start();
				list.add(a1);
			}

			for (int i = 0; i < iNumber; i++) {
				try {

					list.get(i).join();
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
		}

	}

}
