package com.srnpr.zapcom.basehelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.terracotta.statistics.Time.TimeSource;

/**
 * 多线程测试类
 * 
 * @author srnpr
 * 
 */
public abstract class ThreadTestHelper extends TestHelper implements Runnable {

	private static int threadNowIndex = 0;
	private static int threadMaxNumber = 0;

	private static long timerStart = 0;

	private static long timerEnd = 0;

	public void watchBegin(int iMax) {
		threadNowIndex = 0;
		threadMaxNumber = iMax;

	}

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
		

		// thread.start();
		
		if(threadMaxNumber==0)
		{
			threadMaxNumber=iNumber;
		}startWatch();

		for (int i = 0; i < iNumber; i++) {

		

			onRun();
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

			
			if(threadMaxNumber==0)
			{
				threadMaxNumber=iNumber;
			}
			startWatch();
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
	public void threadPool(int iNumber,int iThread) {

		if(iThread<=0)
		{
			iThread=100;
		}
		//ExecutorService pool = Executors.newCachedThreadPool();
		 ExecutorService pool = Executors.newFixedThreadPool(iThread);
		if (iNumber < 0) {

		} else {
			/*
			 * List<Thread> list = new ArrayList<Thread>();
			 * 
			 * for (int i = 0; i < iNumber; i++) {
			 * 
			 * Thread a1 = new Thread(this, "test_thread_" + i);
			 * 
			 * 
			 * 
			 * list.add(a1); }
			 */
			
			if(threadMaxNumber==0)
			{
				threadMaxNumber=iNumber;
			}
			startWatch();
			

			for (int i = 0; i < iNumber; i++) {
				try {
					// bLogTest("test" + i);
					// bLogTest("test" + i);
					// list.get(i).join();
					// pool.execute(list.get(i));
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

		// pool.shutdownNow();

	}

	public void startWatch() {
		// 如果第一次执行则开始
		if (threadMaxNumber > 0 && timerStart == 0) {

			bLogTest("开始计时");
			timerStart = System.currentTimeMillis();

		}
	}

	public void run() {

		onRun();
		listenResult();
	}

	public synchronized void listenResult() {

		if (threadMaxNumber > 0) {

			threadNowIndex=threadNowIndex+1;
			
			if (threadNowIndex >= threadMaxNumber) {

				timerEnd = System.currentTimeMillis();

				long lStep = timerEnd - timerStart;

				bLogTest("finish " + String.valueOf(lStep));

				bLogTest("every"
						+ String.valueOf(threadMaxNumber / (lStep / 1000)));

			}

		}

	}

	public abstract void onRun();

}
