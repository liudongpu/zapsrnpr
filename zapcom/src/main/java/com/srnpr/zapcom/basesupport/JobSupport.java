package com.srnpr.zapcom.basesupport;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapcom.baseface.IBaseJob;

public class JobSupport extends BaseClass implements IBaseInstance {

	private static JobSupport jobSupport = null;

	private Scheduler scheduler = null;

	private int iNumIndex = 0;

	public static JobSupport getInstance() {
		if (jobSupport == null) {
			
				jobSupport = new JobSupport();
			
		}
		return jobSupport;
	}

	/**
	 * 添加定时任务
	 * 
	 * @param sClassName
	 *            类名称
	 * @param sTriger
	 *            定时表达式 标准quartz结构体
	 * @param sJobName
	 *            任务名称 可以为空 为空则自动生成
	 */
	public synchronized void addJob(String sClassName, String sTriger,
			String sJobName) {

		try {
			if (scheduler == null) {
				SchedulerFactory sf = new StdSchedulerFactory();

				scheduler = sf.getScheduler();
				scheduler.start();

			}
			@SuppressWarnings("unchecked")
			Class<IBaseJob> jClass = ClassUtils.getClass(sClassName);

			if (StringUtils.isBlank(sJobName)) {
				sJobName = "job_" + String.valueOf(iNumIndex) + "_"
						+ sClassName;
			}

			JobDetail job = JobBuilder.newJob(jClass)
					.withIdentity(sJobName, Scheduler.DEFAULT_GROUP).build(); // 设置作业，具体操作在SimpleJob类里

			CronTrigger trigger = (CronTrigger) TriggerBuilder
					.newTrigger()
					.withIdentity("trigger_" + sJobName,
							Scheduler.DEFAULT_GROUP)
					.withSchedule(CronScheduleBuilder.cronSchedule(sTriger))
					.build(); // 设置触发器

			Set<CronTrigger> sTriggers = new HashSet<CronTrigger>();
			sTriggers.add(trigger);

			scheduler.scheduleJob(job, sTriggers, true); // 设置调度作业

		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

	}

	/**
	 * 删除任务
	 * 
	 * @param sJobName
	 * @return
	 */
	public boolean deleteJob(String sJobName) {
		try {
			scheduler.deleteJob(JobKey
					.jobKey(sJobName, Scheduler.DEFAULT_GROUP));
		} catch (SchedulerException e) {

			e.printStackTrace();
		}
		return true;
	}

}
