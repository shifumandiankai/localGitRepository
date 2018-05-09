package com.wadejerry.scms.mq.dto;

import java.math.BigDecimal;

public class InRecordDto {
	@Override
	public String toString() {
		return "InRecordDto [inRecordId=" + inRecordId + ", carId=" + carId + ", carNumber=" + carNumber + ", cardId="
				+ cardId + ", personName=" + personName + ", carTypeId=" + carTypeId + ", carTypeName=" + carTypeName
				+ ", parkId=" + parkId + ", parkName=" + parkName + ", inEntranceId=" + inEntranceId
				+ ", inEntranceName=" + inEntranceName + ", inCarriagewayId=" + inCarriagewayId + ", inCarriagewayName="
				+ inCarriagewayName + ", inBoothId=" + inBoothId + ", inBoothName=" + inBoothName + ", inDeviceId="
				+ inDeviceId + ", inDeviceName=" + inDeviceName + ", strInTime=" + strInTime + ", inPhoto=" + inPhoto
				+ ", upload=" + upload + ", flag=" + flag + ", note=" + note + ", strInsertTime=" + strInsertTime
				+ ", userId=" + userId + ", userName=" + userName + ", advance=" + advance + ", advancetype="
				+ advancetype + "]";
	}

	public int inRecordId;

	public int carId;

	public String carNumber;

	public String cardId;

	public String personName;

	public int carTypeId;

	public String carTypeName;

	public int parkId;

	public String parkName;

	public int inEntranceId;

	public String inEntranceName;

	public int inCarriagewayId;

	public String inCarriagewayName;

	public int inBoothId;

	public String inBoothName;

	public int inDeviceId;

	public String inDeviceName;

	public String strInTime;

	public String inPhoto;

	public int upload; // 0 未上传 1 已上传

	public int flag; // 0 正常放行 1 异常放行

	public String note;

	public String strInsertTime;

	public int userId;

	public String userName;

	public BigDecimal advance;

	public int advancetype;
	
	public int getInRecordId() {
		return inRecordId;
	}

	public void setInRecordId(int inRecordId) {
		this.inRecordId = inRecordId;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public int getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(int carTypeId) {
		this.carTypeId = carTypeId;
	}

	public String getCarTypeName() {
		return carTypeName;
	}

	public void setCarTypeName(String carTypeName) {
		this.carTypeName = carTypeName;
	}

	public int getParkId() {
		return parkId;
	}

	public void setParkId(int parkId) {
		this.parkId = parkId;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public int getInEntranceId() {
		return inEntranceId;
	}

	public void setInEntranceId(int inEntranceId) {
		this.inEntranceId = inEntranceId;
	}

	public String getInEntranceName() {
		return inEntranceName;
	}

	public void setInEntranceName(String inEntranceName) {
		this.inEntranceName = inEntranceName;
	}

	public int getInCarriagewayId() {
		return inCarriagewayId;
	}

	public void setInCarriagewayId(int inCarriagewayId) {
		this.inCarriagewayId = inCarriagewayId;
	}

	public String getInCarriagewayName() {
		return inCarriagewayName;
	}

	public void setInCarriagewayName(String inCarriagewayName) {
		this.inCarriagewayName = inCarriagewayName;
	}

	public int getInBoothId() {
		return inBoothId;
	}

	public void setInBoothId(int inBoothId) {
		this.inBoothId = inBoothId;
	}

	public String getInBoothName() {
		return inBoothName;
	}

	public void setInBoothName(String inBoothName) {
		this.inBoothName = inBoothName;
	}

	public int getInDeviceId() {
		return inDeviceId;
	}

	public void setInDeviceId(int inDeviceId) {
		this.inDeviceId = inDeviceId;
	}

	public String getInDeviceName() {
		return inDeviceName;
	}

	public void setInDeviceName(String inDeviceName) {
		this.inDeviceName = inDeviceName;
	}

	public String getStrInTime() {
		return strInTime;
	}

	public void setStrInTime(String strInTime) {
		this.strInTime = strInTime;
	}

	public String getInPhoto() {
		return inPhoto;
	}

	public void setInPhoto(String inPhoto) {
		this.inPhoto = inPhoto;
	}

	public int getUpload() {
		return upload;
	}

	public void setUpload(int upload) {
		this.upload = upload;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getStrInsertTime() {
		return strInsertTime;
	}

	public void setStrInsertTime(String strInsertTime) {
		this.strInsertTime = strInsertTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getAdvance() {
		return advance;
	}

	public void setAdvance(BigDecimal advance) {
		this.advance = advance;
	}

	public int getAdvancetype() {
		return advancetype;
	}

	public void setAdvancetype(int advancetype) {
		this.advancetype = advancetype;
	}
}
