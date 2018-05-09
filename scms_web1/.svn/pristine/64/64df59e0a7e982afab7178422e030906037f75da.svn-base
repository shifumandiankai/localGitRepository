package com.wadejerry.scms.task;

import org.springframework.beans.factory.annotation.Autowired;

import com.wadejerry.scms.modules.sysconfig.service.BimSysParamsService;
import com.wadejerry.scms.modules.sysconfig.service.DBManageConfigService;
import com.wadejerry.scms.utils.Log.LogManager;
import com.wadejerry.scms.utils.quartz.QuartzManager;
import com.wadejerry.scms.utils.quartz.QuartzTaskJob;

/**
* 
* @ClassName: QuartzSchedulerTask
* @Description: 调度器任务执行辅助类
* @author zhanying
* @date 2016年12月28日 上午11:24:23
*
 */
public class QuartzSchedulerTask {
	
	@Autowired(required = false)
	private DBManageConfigService dbManageConfigService; //数据库备份service
	@Autowired(required = false)
	private BimSysParamsService bimSysParamsService;  //系统参数service

	/**
	* 启动数据库自动备份任务
	* @author zhanying 2016年12月28日 上午11:24:36
	* @param  @return 
	 * @throws Exception 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public boolean enableDatabaseBackUpTask() throws Exception {
		boolean isSuccess = false;
		LogManager.logInfo("启动数据库自动备份任务");
		QuartzTaskJob quartzTaskJob = dbManageConfigService.getQuartzTaskJob();
		isSuccess = QuartzManager.enableCronSchedule(quartzTaskJob);
		return isSuccess;
	}
	
	/**
	*  设备定时校时任务
	* @author zhanying 2017年6月9日 下午5:35:53
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public boolean enableTimeCalibratingTask() {
		boolean isSuccess = false;
		LogManager.logInfo("服务设备定时校时任务");
		QuartzTaskJob quartzTaskJob =bimSysParamsService.getQuartzTimeCalibratingTaskJob();
		isSuccess = QuartzManager.enableCronSchedule(quartzTaskJob);
		return isSuccess;
	}
}
	
