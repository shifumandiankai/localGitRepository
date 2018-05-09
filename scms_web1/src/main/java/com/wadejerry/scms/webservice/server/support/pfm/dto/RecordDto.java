package com.wadejerry.scms.webservice.server.support.pfm.dto;

import java.util.Date;

import com.wadejerry.scms.utils.date.DateUtil;

public class RecordDto {
	
	public int recordId;
	public int carId;
    public String carNumber;
    public String cardId;
    public String personName;
    public int carTypeId;
    public String carTypeName;
    public int parkId;
    public String parkName;
    public int entranceId;
    public String entranceName;
    public int carriagewayId;
    public String carriagewayName;
    public int boothId;
    public String boothName;
    public int deviceId;
    public String deviceName;
    public String eventTime;
    public String photo;
    public String note;
    public String insertTime;
    public int userId;
    public String userName;
    public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String direction;
    
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
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
	public int getEntranceId() {
		return entranceId;
	}
	public void setEntranceId(int entranceId) {
		this.entranceId = entranceId;
	}
	public String getEntranceName() {
		return entranceName;
	}
	public void setEntranceName(String entranceName) {
		this.entranceName = entranceName;
	}
	public int getCarriagewayId() {
		return carriagewayId;
	}
	public void setCarriagewayId(int carriagewayId) {
		this.carriagewayId = carriagewayId;
	}
	public String getCarriagewayName() {
		return carriagewayName;
	}
	public void setCarriagewayName(String carriagewayName) {
		this.carriagewayName = carriagewayName;
	}
	public int getBoothId() {
		return boothId;
	}
	public void setBoothId(int boothId) {
		this.boothId = boothId;
	}
	public String getBoothName() {
		return boothName;
	}
	public void setBoothName(String boothName) {
		this.boothName = boothName;
	}
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(Date eventTime) {
		this.eventTime = DateUtil.df.format(eventTime);
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = DateUtil.df.format(insertTime);
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

    
}
