package com.wadejerry.scms.webservice.server.support.pfm.dto;

import java.util.Date;

public  class ParkDto {
	private Integer pfmParkingLotId;
	private Integer bimCompanyId;
	private String parkingLotName;
	private Integer carNumber;
	private Integer remainName;
	private String attribution;
	private String userType;
	private String note;
	private Integer inId;
	private long insertTime;
	private long updateTime;
	private Integer reserveCarNumber;
	public Integer getPfmParkingLotId() {
		return pfmParkingLotId;
	}
	public void setPfmParkingLotId(Integer pfmParkingLotId) {
		this.pfmParkingLotId = pfmParkingLotId;
	}
	public Integer getBimCompanyId() {
		return bimCompanyId;
	}
	public void setBimCompanyId(Integer bimCompanyId) {
		this.bimCompanyId = bimCompanyId;
	}
	public String getParkingLotName() {
		return parkingLotName;
	}
	public void setParkingLotName(String parkingLotName) {
		this.parkingLotName = parkingLotName;
	}
	public Integer getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(Integer carNumber) {
		this.carNumber = carNumber;
	}
	public Integer getRemainName() {
		return remainName;
	}
	public void setRemainName(Integer remainName) {
		this.remainName = remainName;
	}
	public String getAttribution() {
		return attribution;
	}
	public void setAttribution(String attribution) {
		this.attribution = attribution;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getInId() {
		return inId;
	}
	public void setInId(Integer inId) {
		this.inId = inId;
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
	public Integer getReserveCarNumber() {
		return reserveCarNumber;
	}
	public void setReserveCarNumber(Integer reserveCarNumber) {
		this.reserveCarNumber = reserveCarNumber;
	}


}
