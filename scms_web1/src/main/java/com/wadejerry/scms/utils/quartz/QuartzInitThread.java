package com.wadejerry.scms.utils.quartz;

import com.wadejerry.scms.frame.AppContext;
import com.wadejerry.scms.task.QuartzSchedulerTask;
import com.wadejerry.scms.utils.Log.LogManager;

/**
 * Quartz定时任务初始化线程
 * @author jinbenbin 2013-11-20 下午07:54:56
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2013-11-20
 * @modify by reason:{方法名}:{原因}
 */
public class QuartzInitThread extends Thread {
	
	public void run() {
		QuartzSchedulerTask quartzSchedulerTask = (QuartzSchedulerTask)AppContext.getBean("quartzSchedulerTask");
		try {
			quartzSchedulerTask.enableDatabaseBackUpTask(); //初始换启用定时备份数据库
			quartzSchedulerTask.enableTimeCalibratingTask(); //初始换启用定时校时
		} catch (Exception e) {
			LogManager.logException(e);
		}
	}
	
}
