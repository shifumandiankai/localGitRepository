package com.wadejerry.scms.webservice.server.support.pfm.dto;

import java.util.Date;

public class SpecialCarNumDto {
	 private Integer pfmSpecialCarId;
	 private String carNumber;
	 private long updateTime;	// 修改时间
	 private int  isDel; //标识是否修改
	 
	 
	 public Integer getPfmSpecialCarId() {
		return pfmSpecialCarId;
	}
	public void setPfmSpecialCarId(Integer pfmSpecialCarId) {
		this.pfmSpecialCarId = pfmSpecialCarId;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime.getTime();
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

}
