package com.wadejerry.scms.modules.pfm.dto;

import java.util.Date;
import java.util.List;

public class ScreenDto {
	private Integer pfmDeviceScreenId;

    private Integer pfmDeviceType;

    private Integer bimCompanyId;

    private String deviceCode;

    private String deviceName;

    private String ip;

    private Integer port;

    private String loginId;

    private String loginPassword;

    private Date insertTime;

    private Date updateTime;
    
    private String arrStr;
    
    private List<String> listarr;
    
    private String deviceIdArr;
	public Integer getPfmDeviceScreenId() {
		return pfmDeviceScreenId;
	}

	public void setPfmDeviceScreenId(Integer pfmDeviceScreenId) {
		this.pfmDeviceScreenId = pfmDeviceScreenId;
	}

	public Integer getPfmDeviceType() {
		return pfmDeviceType;
	}

	public void setPfmDeviceType(Integer pfmDeviceType) {
		this.pfmDeviceType = pfmDeviceType;
	}

	public Integer getBimCompanyId() {
		return bimCompanyId;
	}

	public void setBimCompanyId(Integer bimCompanyId) {
		this.bimCompanyId = bimCompanyId;
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

	public String getArrStr() {
		return arrStr;
	}

	public void setArrStr(String arrStr) {
		this.arrStr = arrStr;
	}

	public List<String> getListarr() {
		return listarr;
	}

	public void setListarr(List<String> listarr) {
		this.listarr = listarr;
	}

	public String getDeviceIdArr() {
		return deviceIdArr;
	}

	public void setDeviceIdArr(String deviceIdArr) {
		this.deviceIdArr = deviceIdArr;
	}

	
    
}
