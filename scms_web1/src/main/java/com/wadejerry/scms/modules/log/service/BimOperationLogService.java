package com.wadejerry.scms.modules.log.service;

import java.util.Date;

import com.wadejerry.scms.modules.log.model.BimOperationLog;

public interface BimOperationLogService {
	/**
	* 添加一笔操作日志
	* @author zhanying 2017年6月16日 上午10:36:36
	* @param  @param log
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public int insertOperationLog(BimOperationLog log);
	
	/**
	* 删除指定类型指定时间内的日志
	* @author zhanying 2017年6月16日 上午10:37:01
	* @param  @param logType
	* @param  @param endTime
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public int deleteLogByLogTypeAndTime(int logType,Date endTime);

}
