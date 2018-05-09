package com.wadejerry.scms.modules.pfm.model;

import java.util.Date;

public class PfmCarTypeRel {
    @Override
	public String toString() {
		return "pfmCarTypeRel [carTypeRelId=" + carTypeRelId + ", carInfoId=" + carInfoId + ", carTypeId=" + carTypeId
				+ ", insertTime=" + insertTime + ", updateTime=" + updateTime + ", userName=" + userName + "]";
	}

	private Integer carTypeRelId;

    private Integer carInfoId;

    private Integer carTypeId;

    private Date insertTime;

    private Date updateTime;

    private String userName;

    public Integer getCarTypeRelId() {
        return carTypeRelId;
    }

    public void setCarTypeRelId(Integer carTypeRelId) {
        this.carTypeRelId = carTypeRelId;
    }

    public Integer getCarInfoId() {
        return carInfoId;
    }

    public void setCarInfoId(Integer carInfoId) {
        this.carInfoId = carInfoId;
    }

    public Integer getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(Integer carTypeId) {
        this.carTypeId = carTypeId;
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
}