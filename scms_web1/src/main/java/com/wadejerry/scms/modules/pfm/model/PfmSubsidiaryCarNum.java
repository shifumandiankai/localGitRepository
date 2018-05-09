package com.wadejerry.scms.modules.pfm.model;

import java.util.Date;

public class PfmSubsidiaryCarNum {
    private Integer pfmSubsidiaryCarNumId;

    private Integer carId;

    private String carNumber;

    private Date updateTime;

    private String userName;

    public Integer getPfmSubsidiaryCarNumId() {
        return pfmSubsidiaryCarNumId;
    }

    public void setPfmSubsidiaryCarNumId(Integer pfmSubsidiaryCarNumId) {
        this.pfmSubsidiaryCarNumId = pfmSubsidiaryCarNumId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber == null ? null : carNumber.trim();
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