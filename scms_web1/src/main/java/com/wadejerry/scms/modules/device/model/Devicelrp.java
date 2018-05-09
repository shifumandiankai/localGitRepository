package com.wadejerry.scms.modules.device.model;

import java.util.Date;

public class Devicelrp {
private Integer pfmDeviceLprId;
private Integer bimCompanyId;
private Integer pfmServerId;
private String deviceCode;
private String deviceName;
private String ip;
private Integer port;
private String mac;
private String loginId;
private String loginPassword;
private Date insertTime;
private Date updateTime;
private String userName;
private Integer pfmScreenId;
public Integer getPfmDeviceLprId() {
	return pfmDeviceLprId;
}
public void setPfmDeviceLprId(Integer pfmDeviceLprId) {
	this.pfmDeviceLprId = pfmDeviceLprId;
}
public Integer getBimCompanyId() {
	return bimCompanyId;
}
public void setBimCompanyId(Integer bimCompanyId) {
	this.bimCompanyId = bimCompanyId;
}

public Integer getPfmServerId() {
	return pfmServerId;
}
public void setPfmServerId(Integer pfmServerId) {
	this.pfmServerId = pfmServerId;
}
public String getDeviceCode() {
	return deviceCode;
}
public void setDeviceCode(String deviceCode) {
	this.deviceCode = deviceCode;
}
public String getDeviceName() {
	return deviceName;
}
public void setDeviceName(String deviceName) {
	this.deviceName = deviceName;
}
public String getIp() {
	return ip;
}
public void setIp(String ip) {
	this.ip = ip;
}
public Integer getPort() {
	return port;
}
public void setPort(Integer port) {
	this.port = port;
}

public String getLoginId() {
	return loginId;
}
public void setLoginId(String loginId) {
	this.loginId = loginId;
}
public String getLoginPassword() {
	return loginPassword;
}
public void setLoginPassword(String loginPassword) {
	this.loginPassword = loginPassword;
}
public Date getInsertTime() {
	return insertTime;
}
public void setInsertTime(Date insertTime) {
	this.insertTime = insertTime;
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
public String getMac() {
	return mac;
}
public void setMac(String mac) {
	this.mac = mac;
}
public Integer getPfmScreenId() {
	return pfmScreenId;
}
public void setPfmScreenId(Integer pfmScreenId) {
	this.pfmScreenId = pfmScreenId;
}

}
