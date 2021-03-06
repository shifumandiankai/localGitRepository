package com.wadejerry.scms.modules.device.model;

import java.util.Date;

public class PfmServer {
    private Integer pfmServerId;

    private Integer bimCompanyId;

    private String serverName;

    private String ip;

    private String port;

    private Date insertTime;

    private Date updateTime;

    private String userName;
    
    private Integer netZoneId;
    
    private Integer parkId;
    
    private Integer pfmDeviceLprId;
    
    private String deviceName;

    public Integer getPfmServerId() {
        return pfmServerId;
    }

    public void setPfmServerId(Integer pfmServerId) {
        this.pfmServerId = pfmServerId;
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

	public Integer getNetZoneId() {
		return netZoneId;
	}

	public void setNetZoneId(Integer netZoneId) {
		this.netZoneId = netZoneId;
	}

	public Integer getParkId() {
		return parkId;
	}

	public void setParkId(Integer parkId) {
		this.parkId = parkId;
	}

	public Integer getPfmDeviceLprId() {
		return pfmDeviceLprId;
	}

	public void setPfmDeviceLprId(Integer pfmDeviceLprId) {
		this.pfmDeviceLprId = pfmDeviceLprId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	
}