package com.wadejerry.scms.modules.sysconfig.service;

import com.wadejerry.scms.modules.sysconfig.model.BimSysParams;
import com.wadejerry.scms.utils.quartz.QuartzTaskJob;

public interface BimSysParamsService {
	/**
	* 保存系统参数
	* @author zhanying 2017年6月6日 上午11:35:42
	* @param  @param paramIdAndValue 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public void UpdateParams(Integer key,Object value);
	
	/**
	* 根据id获取参数值
	* @author zhanying 2017年6月7日 上午10:06:00
	* @param  @param key
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public BimSysParams GetParamByKey(Integer key);
	
	/**
	* 获取定时校时job
	* @author zhanying 2017年6月9日 下午5:05:40
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public QuartzTaskJob getQuartzTimeCalibratingTaskJob();
}
