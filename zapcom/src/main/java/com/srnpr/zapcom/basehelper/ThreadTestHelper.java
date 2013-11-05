package com.srnpr.zapcom.basehelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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

	/**
	 * @param iNumber
	 */
	public void threadPool(int iNumber) {

		ExecutorService pool = Executors.newCachedThreadPool();
		 //ExecutorService pool = Executors.newFixedThreadPool(3);
		if (iNumber < 0) {

		} else {
			/*
			List<Thread> list = new ArrayList<Thread>();

			for (int i = 0; i < iNumber; i++) {

				Thread a1 = new Thread(this, "test_thread_" + i);

				

				list.add(a1);
			}*/

			for (int i = 0; i < iNumber; i++) {
				try {
					//bLogTest("test" + i);
					// bLogTest("test" + i);
					// list.get(i).join();
					//pool.execute(list.get(i));
					pool.execute(this);
					

				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		}
		
		
		
		pool.shutdown();
		try {
			pool.awaitTermination(12, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		//pool.shutdownNow();

	}

	public abstract void run();
	

}
