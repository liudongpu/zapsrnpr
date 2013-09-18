package com.srnpr.zapcom.basehelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 多线程测试类
 * 
 * @author srnpr
 * 
 */
public abstract class ThreadTestHelper extends TestHelper implements Runnable {

	/**
	 * 获取线程名称
	 * 
	 * @return
	 */
	public String upThreadName() {
		return Thread.currentThread().getName();
	}

	/**
	 * 单线程循环次数
	 * 
	 * @param iNumber
	 */
	public void singleThread(int iNumber) {
		Thread thread = new Thread(this);

		// thread.start();

		for (int i = 0; i < iNumber; i++) {

			bLogTest("test" + i);

			thread.run();
		}

		try {

			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 多线程测试类 调用该类需要继承接口
	 * 
	 * 
	 * @param iNumber
	 *            调用run的次数 正数为多线程的线程数量
	 */
	public void muliThread(int iNumber) {

		if (iNumber < 0) {

		} else {
			List<Thread> list = new ArrayList<Thread>();

			for (int i = 0; i < iNumber; i++) {

				Thread a1 = new Thread(this, "test_thread_" + i);

				// bLogTest("test" + i);
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