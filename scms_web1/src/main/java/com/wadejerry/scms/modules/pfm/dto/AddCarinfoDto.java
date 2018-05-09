package com.wadejerry.scms.modules.pfm.dto;

public class AddCarinfoDto {
	private String carInfoId;
	private String carNumber;
	private String personName;
	private String phone;
	private String carTypeId;
	private String carSpaceId;
	private String currentParkLot;
	private String status;
	private String sex;
	private String carTypeRelId;
	private String parkSpaceCarRelId;
	private String subsidiaryCarNum;

	// 新增
	private String cardId;
	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
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

	public String getEnableTime() {
		return enableTime;
	}

	public void setEnableTime(String enableTime) {
		this.enableTime = enableTime;
	}

	public String getDisableTime() {
		return disableTime;
	}

	public void setDisableTime(String disableTime) {
		this.disableTime = disableTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	private String addres;
	private String certId;
	private String enableTime;
	private String disableTime;
	private String note;
	private String personId;

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(String carTypeId) {
		this.carTypeId = carTypeId;
	}

	public String getCarSpaceId() {
		return carSpaceId;
	}

	public void setCarSpaceId(String carSpaceId) {
		this.carSpaceId = carSpaceId;
	}

	public String getCurrentParkLot() {
		return currentParkLot;
	}

	public void setCurrentParkLot(String currentParkLot) {
		this.currentParkLot = currentParkLot;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCarInfoId() {
		return carInfoId;
	}

	public void setCarInfoId(String carInfoId) {
		this.carInfoId = carInfoId;
	}

	public String getCarTypeRelId() {
		return carTypeRelId;
	}

	public void setCarTypeRelId(String carTypeRelId) {
		this.carTypeRelId = carTypeRelId;
	}

	public String getParkSpaceCarRelId() {
		return parkSpaceCarRelId;
	}

	public void setParkSpaceCarRelId(String parkSpaceCarRelId) {
		this.parkSpaceCarRelId = parkSpaceCarRelId;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getSubsidiaryCarNum() {
		return subsidiaryCarNum;
	}

	public void setSubsidiaryCarNum(String subsidiaryCarNum) {
		this.subsidiaryCarNum = subsidiaryCarNum;
	}

}
