package com.wadejerry.scms.modules.pfm.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MessageDto {

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getScreenids() {
		return screenids;
	}
	public void setScreenids(String screenids) {
		this.screenids = screenids;
	}
	public Integer getPfmTaskMessageId() {
		return pfmTaskMessageId;
	}
	public void setPfmTaskMessageId(Integer pfmTaskMessageId) {
		this.pfmTaskMessageId = pfmTaskMessageId;
	}
	public String getDeviceScreenIdArr() {
		return deviceScreenIdArr;
	}
	public void setDeviceScreenIdArr(String deviceScreenIdArr) {
		this.deviceScreenIdArr = deviceScreenIdArr;
	}
	private Integer pfmTaskMessageId;
	private String message;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date beginTime;
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date endTime;
	private String deviceScreenIdArr;//screen名字
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date updateTime;
	private String userName;
	private String screenids;//id
}
