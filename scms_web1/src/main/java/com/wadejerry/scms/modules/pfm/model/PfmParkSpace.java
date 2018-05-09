package com.wadejerry.scms.modules.pfm.model;

import java.util.Date;

public class PfmParkSpace {
    private Integer pfmParkSpace;

    private Integer pfmParkingLotId;

    private String parkSpaceCode;

    private Short status;

    private String note;

    private Date lastUseTime;

    private Integer lastUseCar;

    private Date insetTime;

    private Date updateTime;

    private String userName;

    public Integer getPfmParkSpace() {
        return pfmParkSpace;
    }

    public void setPfmParkSpace(Integer pfmParkSpace) {
        this.pfmParkSpace = pfmParkSpace;
    }

    public Integer getPfmParkingLotId() {
        return pfmParkingLotId;
    }

    public void setPfmParkingLotId(Integer pfmParkingLotId) {
        this.pfmParkingLotId = pfmParkingLotId;
    }

    public String getParkSpaceCode() {
        return parkSpaceCode;
    }

    public void setParkSpaceCode(String parkSpaceCode) {
        this.parkSpaceCode = parkSpaceCode == null ? null : parkSpaceCode.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Date getLastUseTime() {
        return lastUseTime;
    }

    public void setLastUseTime(Date lastUseTime) {
        this.lastUseTime = lastUseTime;
    }

    public Integer getLastUseCar() {
        return lastUseCar;
    }

    public void setLastUseCar(Integer lastUseCar) {
        this.lastUseCar = lastUseCar;
    }

    public Date getInsetTime() {
        return insetTime;
    }

    public void setInsetTime(Date insetTime) {
        this.insetTime = insetTime;
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
}