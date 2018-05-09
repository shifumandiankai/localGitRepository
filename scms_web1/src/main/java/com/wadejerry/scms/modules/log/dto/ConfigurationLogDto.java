package com.wadejerry.scms.modules.log.dto;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.wadejerry.scms.frame.constant.ConstParamLog;

public class ConfigurationLogDto {
	private Date startTime; 
	private Date endTime; 
	private Integer systemCode;
	private Integer user;
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
	public Integer getSystemCode() {
		return systemCode;
	}
	public void setSystemCode(Integer systemCode) {
		this.systemCode = systemCode;
	}
	public Integer getUser() {
		return user;
	}
	public void setUser(Integer user) {
		this.user = user;
	}
    
}
