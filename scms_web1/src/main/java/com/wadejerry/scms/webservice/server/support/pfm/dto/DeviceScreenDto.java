package com.wadejerry.scms.webservice.server.support.pfm.dto;

import java.util.Date;

public class DeviceScreenDto {
	private int pfmDeviceScreenId;
	private int pfmDeviceType; // 默认1
	private String deviceCode; // 设备编号
	private String deviceName; // 设备名称
	private String ip; // IP地址 预留
	private int port; // 端口 预留
	private String loginId;// 登录用户名 预留
	private String loginPassword; // 登录密码 预留
	private long insertTime; // 添加时间
	private long updateTime; // 更新时间
	private String userName; // 更新操作员
	private String deviceIdArr; // 关联lpr设备id集合，以',' 相隔
	private int custom1;
	private int custom2;
	private int custom3;
	private int custom4;
	private String custom5;
	private String custom6;
	private String custom7;
	private String custom8;

	public int getPfmDeviceScreenId() {
		return pfmDeviceScreenId;
	}

	public void setPfmDeviceScreenId(int pfmDeviceScreenId) {
		this.pfmDeviceScreenId = pfmDeviceScreenId;
	}

	public int getPfmDeviceType() {
		return pfmDeviceType;
	}

	public void setPfmDeviceType(int pfmDeviceType) {
		this.pfmDeviceType = pfmDeviceType;
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

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
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

	public long getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime.getTime();
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime.getTime();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeviceIdArr() {
		return deviceIdArr;
	}

	public void setDeviceIdArr(String deviceIdArr) {
		this.deviceIdArr = deviceIdArr;
	}

	public int getCustom1() {
		return custom1;
	}

	public void setCustom1(int custom1) {
		this.custom1 = custom1;
	}

	public int getCustom2() {
		return custom2;
	}

	public void setCustom2(int custom2) {
		this.custom2 = custom2;
	}

	public int getCustom3() {
		return custom3;
	}

	public void setCustom3(int custom3) {
		this.custom3 = custom3;
	}

	public int getCustom4() {
		return custom4;
	}

	public void setCustom4(int custom4) {
		this.custom4 = custom4;
	}

	public String getCustom5() {
		return custom5;
	}

	public void setCustom5(String custom5) {
		this.custom5 = custom5;
	}

	public String getCustom6() {
		return custom6;
	}

	public void setCustom6(String custom6) {
		this.custom6 = custom6;
	}

	public String getCustom7() {
		return custom7;
	}

	public void setCustom7(String custom7) {
		this.custom7 = custom7;
	}

	public String getCustom8() {
		return custom8;
	}

	public void setCustom8(String custom8) {
		this.custom8 = custom8;
	}
}
