package com.wadejerry.scms.modules.acm.model;

import java.util.Date;

public class AcmServer {
    private Integer acmServerId;

    private Integer bimCompanyId;

    private String serverName;

    private String ip;

    private String port;

    private Date updateTime;

    private String userName;

    private Integer netZoneId;

    public Integer getAcmServerId() {
        return acmServerId;
    }

    public void setAcmServerId(Integer acmServerId) {
        this.acmServerId = acmServerId;
    }

    public Integer getBimCompanyId() {
        return bimCompanyId;
    }

    public void setBimCompanyId(Integer bimCompanyId) {
        this.bimCompanyId = bimCompanyId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName == null ? null : serverName.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port == null ? null : port.trim();
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

    public Integer getNetZoneId() {
        return netZoneId;
    }

    public void setNetZoneId(Integer netZoneId) {
        this.netZoneId = netZoneId;
    }
}