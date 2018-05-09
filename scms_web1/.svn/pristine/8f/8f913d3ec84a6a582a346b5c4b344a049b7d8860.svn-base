package com.wadejerry.scms.utils.quartz;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdScheduler;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.wadejerry.scms.frame.AppContext;
import com.wadejerry.scms.utils.Log.LogManager;

/**
* 使用Quartz调度器，能对spring定时器进行动态的增，删，改
* @ClassName: QuartzManager
* @Description: TODO
* @author zhanying
* @date 2016年12月28日 上午11:30:19
*
 */
public class QuartzManager {
	
	/** 调度任务工厂 */
	private static Scheduler scheduler;
	
	static {
		//WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext(); 
		scheduler = (StdScheduler)AppContext.getBean("schedulerFactory");
	}
	
	/**
	* 启动一个自定义的job，现在启动之前如果job已经存在则先删除
	* @author zhanying 2016年12月28日 上午11:31:21
	* @param  @param schedulingJob
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	@SuppressWarnings("unchecked")
	public static boolean enableCronSchedule(QuartzTaskJob schedulingJob) {
		try {
			if (disableSchedule(schedulingJob.getJobId())) {
				JobDetail jobDetail =JobBuilder.newJob((Class<? extends Job>)schedulingJob.getJobExecuteClass()).withIdentity(schedulingJob.getJobId(), schedulingJob.getJobGroup()).build();
				scheduler.scheduleJob(jobDetail, schedulingJob.getTrigger());
				return true;
			}
///			if (trigger == null) {// 如果不存在该trigger则创建一个
//				JobDetail jobDetail =JobBuilder.newJob((Class<? extends Job>)schedulingJob.getJobExecuteClass()).withIdentity(schedulingJob.getJobId(), schedulingJob.getJobGroup()).build();
//			//jobDetail.setJobDataMap(paramsMap);
//			scheduler.scheduleJob(jobDetail, schedulingJob.getTrigger());
//			} else {// Trigger已存在，那么更新相应的定时设置
//				Trigger newTrigger = schedulingJob.getTrigger();
//				
//					
//					scheduler.rescheduleJob(TriggerKey.triggerKey(schedulingJob.getJobId()), newTrigger);
//				
//			}
		} catch (Exception e) {
			LogManager.logException(e);
		}
		return false;
	}
			
	/**
	 * 删除一个job
	 * @author jinbenbin 2013-9-26 下午08:26:24
	 * @param jobId job的ID
	 * @return 成功返回true，失败返回false
	 */
	public static boolean disableSchedule(String jobId) {
		if (jobId == null) {
			return false;
		}
		try {
			Trigger trigger = scheduler.getTrigger(TriggerKey.triggerKey(jobId, jobId + "_Group"));
			if (trigger != null) {
				return scheduler.deleteJob(JobKey.jobKey(jobId, jobId + "_Group"));
			} else {
				return true;
			}
		} catch (SchedulerException e) {
			LogManager.logException(e);
		}
		return false;
	}
	
}
