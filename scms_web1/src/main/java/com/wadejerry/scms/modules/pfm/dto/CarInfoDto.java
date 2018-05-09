package com.wadejerry.scms.modules.pfm.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CarInfoDto {

	private Integer carInfoId;
	private Integer personId;
	private String carNumber;
	private String cardId;
	private String personName;
	private String	sex;	
	private String status;
	private String parkSpaceCode;
	private String phone;	
	private String note;
	private String addres;
	private String certId;
	private String typeName;
	private String ruleName;
	private String userName;
	private Integer pfmParkingLotId;
	private Integer pfmParkSpace;
	private Integer carTypeId;
	private Integer carTypeRelId;
	private Integer parkSpaceCarRelId;
	 @JsonFormat(pattern = "yyyy-MM-dd")
	private Date enableTime;
	 @JsonFormat(pattern = "yyyy-MM-dd")
	private Date disableTime;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getCarInfoId() {
		return carInfoId;
	}
	public void setCarInfoId(Integer carInfoId) {
		this.carInfoId = carInfoId;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String perosnName) {
		this.personName = perosnName;
	}

	

	public Integer getCarTypeRelId() {
		return carTypeRelId;
	}
	public void setCarTypeRelId(Integer carTypeRelId) {
		this.carTypeRelId = carTypeRelId;
	}

	public String getParkSpaceCode() {
		return parkSpaceCode;
	}
	public void setParkSpaceCode(String parkSpaceCode) {
		this.parkSpaceCode = parkSpaceCode;
	}
	public Integer getParkSpaceCarRelId() {
		return parkSpaceCarRelId;
	}
	public void setParkSpaceCarRelId(Integer parkSpaceCarRelId) {
		this.parkSpaceCarRelId = parkSpaceCarRelId;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getPfmParkingLotId() {
		return pfmParkingLotId;
	}
	public void setPfmParkingLotId(Integer pfmParkingLotId) {
		this.pfmParkingLotId = pfmParkingLotId;
	}
	public Integer getCarTypeId() {
		return carTypeId;
	}
	public void setCarTypeId(Integer carTypeId) {
		this.carTypeId = carTypeId;
	}
	public Integer getPfmParkSpace() {
		return pfmParkSpace;
	}
	public void setPfmParkSpace(Integer pfmParkSpace) {
		this.pfmParkSpace = pfmParkSpace;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getEnableTime() {
		return enableTime;
	}
	public void setEnableTime(Date enableTime) {
		this.enableTime = enableTime;
	}
	public Date getDisableTime() {
		return disableTime;
	}
	public void setDisableTime(Date disableTime) {
		this.disableTime = disableTime;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public String getAddres() {
		return addres;
	}
	public void setAddres(String addres) {
		this.addres = addres;
	}
	public String getCertId() {
		return certId;
	}
	public void setCertId(String certId) {
		this.certId = certId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}
