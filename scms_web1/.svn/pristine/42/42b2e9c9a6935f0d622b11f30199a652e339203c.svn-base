package com.wadejerry.scms.utils.quartz;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;


/**
*  Quartz任务初始化信息类
* @ClassName: QuartzTaskJob
* @Description: TODO
* @author zhanying
* @date 2016年12月28日 上午11:38:11
*
 */
public class QuartzTaskJob {
	
	/** 任务的Id，一般为所定义Bean的ID */
	private String jobId;
	
	/** 任务所属组的名称 */
	private String jobGroup;

	/** 调度策略，最常用的是 SimpleTrigger和 CronTrigger */
	private Trigger trigger;

	/** 执行类，同步的执行类需要从StatefulMethodInvokingJob继承，异步的执行类需要从MethodInvokingJob继承 */
	private Class<?> jobExecuteClass;
	
	/** 无参构造函数 */
	public QuartzTaskJob() {
	}
	
	/** 有参构造函数，SimpleTrigger策略，startTime起始时间，intervalMillisecond间隔毫秒数 */
	public QuartzTaskJob(String jobId, Class<?> jobExecuteClass, Date startTime, long intervalMillisecond) {
		this.jobId = jobId;
		this.jobGroup = jobId + "_Group";
		this.jobExecuteClass = jobExecuteClass;
		SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(intervalMillisecond);
        trigger = TriggerBuilder.newTrigger().withIdentity(getJobId(), getJobGroup()).startAt(startTime).withSchedule(builder).build();
		
	}
	
	/** 有参构造函数，CronTrigger策略，cronExpression定时任务运行时间表达式 */
	public QuartzTaskJob(String jobId, Class<?> jobExecuteClass, String cronExpression) {
		this.jobId = jobId;
		this.jobGroup = jobId + "_Group";
		this.jobExecuteClass = jobExecuteClass;
		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        trigger = TriggerBuilder.newTrigger().withIdentity(getJobId(), getJobGroup())
                .withSchedule(cronScheduleBuilder).build();
        
	}
	
	public String getJobId() {
		return jobId;
	}
	
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	
	public String getJobGroup() {
		return jobGroup;
	}
	
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	
	public Trigger getTrigger() {
		return trigger;
	}
	
	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}
	
	public Class<?> getJobExecuteClass() {
		return jobExecuteClass;
	}
	
	public void setJobExecuteClass(Class<?> jobExecuteClass) {
		this.jobExecuteClass = jobExecuteClass;
	}

}