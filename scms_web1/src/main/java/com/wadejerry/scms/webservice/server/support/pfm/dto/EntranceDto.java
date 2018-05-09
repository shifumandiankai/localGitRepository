package com.wadejerry.scms.webservice.server.support.pfm.dto;

import java.util.Date;

public class EntranceDto {
    private Integer pfmEntranceId;

    private Integer bimCompanyId;

    private String entranceName;

    private Integer enabled;

    private Integer parkId;

    private Integer timeId;

    private long insertTime;

    private long updateTime;

    private String userName;
    
    private int custom1 ;

    public Integer getPfmEntranceId() {
        return pfmEntranceId;
    }

    public void setPfmEntranceId(Integer pfmEntranceId) {
        this.pfmEntranceId = pfmEntranceId;
    }

    public Integer getBimCompanyId() {
        return bimCompanyId;
    }

    public void setBimCompanyId(Integer bimCompanyId) {
        this.bimCompanyId = bimCompanyId;
    }

    public String getEntranceName() {
        return entranceName;
    }

    public void setEntranceName(String entranceName) {
        this.entranceName = entranceName == null ? null : entranceName.trim();
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public Integer getTimeId() {
        return timeId;
    }

    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
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
        this.userName = userName == null ? null : userName.trim();
    }

	public int getCustom1() {
		return custom1;
	}

	public void setCustom1(int custom1) {
		this.custom1 = custom1;
	}
}
