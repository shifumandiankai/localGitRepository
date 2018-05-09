package com.wadejerry.scms.modules.auth.dto;

/**
* 登录信息DTO
* @ClassName: LoginDto
* @Description: TODO
* @author zhanying
* @date 2016年9月29日 上午10:55:40
*
 */
public class LoginDto {
	
    private String userName;
	private String password; 
	private String time;
	
	private Boolean rememberMe;
	
    public Boolean getRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
    
}
