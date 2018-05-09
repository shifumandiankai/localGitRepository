package com.wadejerry.scms.modules.auth.model;

import java.util.Date;

public class BimLoginStatus {
    private Integer logId;

	private Integer userId;

	private String userName;

	private String loginAddr;

	private Integer loginPort;

	private Date loginTime;

	private String loginWay;

	private String sessionId;

	private Integer custom1;

	private Integer custom2;

	private String custom3;

	private String custom4;

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getLoginAddr() {
		return loginAddr;
	}

	public void setLoginAddr(String loginAddr) {
		this.loginAddr = loginAddr == null ? null : loginAddr.trim();
	}

	public Integer getLoginPort() {
		return loginPort;
	}

	public void setLoginPort(Integer loginPort) {
		this.loginPort = loginPort;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginWay() {
		return loginWay;
	}

	public void setLoginWay(String loginWay) {
		this.loginWay = loginWay == null ? null : loginWay.trim();
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId == null ? null : sessionId.trim();
	}

	public Integer getCustom1() {
		return custom1;
	}

	public void setCustom1(Integer custom1) {
		this.custom1 = custom1;
	}

	public Integer getCustom2() {
		return custom2;
	}

	public void setCustom2(Integer custom2) {
		this.custom2 = custom2;
	}

	public String getCustom3() {
		return custom3;
	}

	public void setCustom3(String custom3) {
		this.custom3 = custom3 == null ? null : custom3.trim();
	}

	public String getCustom4() {
		return custom4;
	}

	public void setCustom4(String custom4) {
		this.custom4 = custom4 == null ? null : custom4.trim();
	}

	}