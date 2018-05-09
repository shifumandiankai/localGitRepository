package com.wadejerry.scms.frame.log;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wadejerry.scms.frame.constant.ConstParamLog;
import com.wadejerry.scms.frame.entity.LoginInfo;
import com.wadejerry.scms.modules.log.model.BimOperationLog;
import com.wadejerry.scms.modules.log.service.BimOperationLogService;
import com.wadejerry.scms.utils.Log.LogManager;

/**
* 
* @ClassName: LogRecord
* @Description: 日志操作类
* @author zhanying
* @date 2017年6月15日 下午3:48:38
*
 */
@Service("logRecord")
public class LogRecord {
	@Autowired
	private BimOperationLogService bimOperationLogService;
	
	/**
	* 记录日志 
	* @author zhanying 2017年6月16日 下午1:29:38
	* @param  @param logTime 日志时间 
	* @param  @param moduleId 模块id
	* @param  @param operType 操作类型 
	* @param  @param logType 日志类型 
	* @param  @param contentValu 参数 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public void recordLog(Date logTime, int moduleId, Integer operType,int logType,String...contentValu) {
		try {
			String value  = LogPropertiesUtils.getResourceByCode(logType, moduleId, operType);
			String logInfo = String.format(value, contentValu);
			int systemId = ConstParamLog.moduleIdAndSystemIdMap.get(moduleId); //获取模块对应系统id	
			BimOperationLog log = new BimOperationLog();
			log.setLogType(logType);
			log.setSystemCode(systemId);
			log.setModuleCode(moduleId);
			try {
				log.setUserId(LoginInfo.getLoginId());
				log.setUserName(LoginInfo.getLoginName());
				log.setLoginAdd(LoginInfo.getLoginAddress());
			} catch (Exception e) {
				LogManager.logInfo("get logininfo error ["+logInfo+"]");
			}	
			log.setLogTime(logTime);
			log.setOperType(operType);
			log.setLogInfo(logInfo);
			log.setInsertTime(new Date());
			bimOperationLogService.insertOperationLog(log);
		} catch (Exception e) {
			LogManager.logException(e);
		}
		
	}
	
	/**
	* 记录日志
	* @author zhanying 2017年6月16日 上午9:49:50
	* @param  @param moduleCode
	* @param  @param opType
	* @param  @param logType
	* @param  @param contentValu 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public void recordLog(int moduleId, Integer operType,int logType,String...contentValu) {
		recordLog(new Date(),moduleId,operType,logType,contentValu);
	}
}
