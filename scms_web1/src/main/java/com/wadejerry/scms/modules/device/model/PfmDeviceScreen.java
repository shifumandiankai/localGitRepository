package com.wadejerry.scms.modules.device.model;

import java.util.Date;

public class PfmDeviceScreen {
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

    private String userName;

    private String deviceIdArr;

    private Integer custom1;

    private Integer custom2;

    private Integer custom3;

    private Integer custom4;

    private String custom5;

    private String custom6;

    private String custom7;

    private String custom8;

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
        this.deviceCode = deviceCode == null ? null : deviceCode.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
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
        this.loginId = loginId == null ? null : loginId.trim();
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
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
        this.userName = userName == null ? null : userName.trim();
    }

    public String getDeviceIdArr() {
        return deviceIdArr;
    }

    public void setDeviceIdArr(String deviceIdArr) {
        this.deviceIdArr = deviceIdArr == null ? null : deviceIdArr.trim();
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

    public Integer getCustom3() {
        return custom3;
    }

    public void setCustom3(Integer custom3) {
        this.custom3 = custom3;
    }

    public Integer getCustom4() {
        return custom4;
    }

    public void setCustom4(Integer custom4) {
        this.custom4 = custom4;
    }

    public String getCustom5() {
        return custom5;
    }

    public void setCustom5(String custom5) {
        this.custom5 = custom5 == null ? null : custom5.trim();
    }

    public String getCustom6() {
        return custom6;
    }

    public void setCustom6(String custom6) {
        this.custom6 = custom6 == null ? null : custom6.trim();
    }

    public String getCustom7() {
        return custom7;
    }

    public void setCustom7(String custom7) {
        this.custom7 = custom7 == null ? null : custom7.trim();
    }

    public String getCustom8() {
        return custom8;
    }

    public void setCustom8(String custom8) {
        this.custom8 = custom8 == null ? null : custom8.trim();
    }
}