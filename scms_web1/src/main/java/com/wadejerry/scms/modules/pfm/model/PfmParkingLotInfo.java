package com.wadejerry.scms.modules.pfm.model;

import java.util.Date;

public class PfmParkingLotInfo {
	private Integer pfmParkingLotId;
	private Integer bimCompanyId;
	private String marchantName;
	private String parkingLotName;
	private Integer carNumber;
	private Integer remainName;
	private String attribution;
	private String note;
	private String userType;
	private Integer inId;
	private Date insertTime;
	private Date updateTime;
	private Integer reserveCarNumber;
	private String parkingLotCode;
	
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
	public String getMarchantName() {
		return marchantName;
	}
	public void setMarchantName(String marchantName) {
		this.marchantName = marchantName;
	}
	/*public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}*/
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getParkingLotName() {
		return parkingLotName;
	}
	public void setParkingLotName(String parkingLotName) {
		this.parkingLotName = parkingLotName;
	}
	public Integer getInId() {
		return inId;
	}
	public void setInId(Integer inId) {
		this.inId = inId;
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
	public Integer getReserveCarNumber() {
		return reserveCarNumber;
	}
	public void setReserveCarNumber(Integer reserveCarNumber) {
		this.reserveCarNumber = reserveCarNumber;
	}
	public String getParkingLotCode() {
		return parkingLotCode;
	}
	public void setParkingLotCode(String parkingLotCode) {
		this.parkingLotCode = parkingLotCode;
	}
	
}