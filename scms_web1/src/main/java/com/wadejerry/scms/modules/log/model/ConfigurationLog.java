package com.wadejerry.scms.modules.log.model;

import java.util.Date;
import java.util.Map;

import com.wadejerry.scms.frame.constant.ConstParamLog;

public class ConfigurationLog {
	private Integer operationlogId;
	private Integer systemCode;//配置类型
	private Integer moduleCode;//系统模块
	private Integer operType;//操作类型
	private String userName;
    private String loginAdd;
    private Date insertTime;
    private String logInfo;
    
	public Integer getOperationlogId() {
		return operationlogId;
	}
	public void setOperationlogId(Integer operationlogId) {
		this.operationlogId = operationlogId;
	}
	public String getSystemCode() {
	    Map<Integer,String> map=ConstParamLog.systemIdAndStrNameMap;
	    return map.get(systemCode);	
	}
	public void setSystemCode(Integer systemCode) {
		this.systemCode = systemCode;
	}
	public String getModuleCode() {
		Map<Integer,String> map=ConstParamLog.moduleIdAndStrNameMap;
	    return map.get(moduleCode);
		
	}
	public void setModuleCode(Integer moduleCode) {
		this.moduleCode = moduleCode;
	}
	public String getOperType() {
		Map<Integer,String> map=ConstParamLog.logOperIdAndStrNameMap;
	    return map.get(operType);
	}
	public void setOperType(Integer operType) {
		this.operType = operType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginAdd() {
		return loginAdd;
	}
	public void setLoginAdd(String loginAdd) {
		this.loginAdd = loginAdd;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public String getLogInfo() {
		return logInfo;
	}
	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}
}
