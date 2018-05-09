package com.wadejerry.scms.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.StatefulMethodInvokingJob;

import com.wadejerry.scms.frame.AppContext;
import com.wadejerry.scms.modules.sysconfig.service.DBManageConfigService;
import com.wadejerry.scms.utils.Log.LogManager;


/**
* @ClassName: DatabaseBackUpTask
* @Description: 数据库备份任务
* @author zhanying
* @date 2016年12月28日 上午9:54:16
*
 */
@DisallowConcurrentExecution
public class DatabaseBackUpTask implements Job {
	/**
	 * 定时任务处理方法
	 */

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogManager.logInfo("数据库备份任务执行中：" + sdf.format(new Date()));
		DBManageConfigService dbManageConfigService = AppContext.getBean("dbManageConfigService");
		try {
			if (dbManageConfigService.judgeExecuteTask()) {
				dbManageConfigService.backUpDataBaseFile();
		
			}
		} catch (Exception e) {
			LogManager.logException(e);
		}
		
	}
}
