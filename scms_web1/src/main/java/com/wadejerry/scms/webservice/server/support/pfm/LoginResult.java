package com.wadejerry.scms.webservice.server.support.pfm;

import com.wadejerry.scms.webservice.server.support.CommonResult;

public class LoginResult extends CommonResult {
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Integer[] getPrivilegeCode() {
		return privilegeCode;
	}
	public void setPrivilegeCode(Integer[] privilegeCode) {
		this.privilegeCode = privilegeCode;
	}
	private String sessionId;
	// 用户ID
	private int userId;
	//模块权限,TODO 权限先不处理
	private Integer[] privilegeCode;
}
