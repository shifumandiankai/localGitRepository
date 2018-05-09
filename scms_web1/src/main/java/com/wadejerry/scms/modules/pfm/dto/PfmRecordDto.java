package com.wadejerry.scms.modules.pfm.dto;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PfmRecordDto {
	  private Integer recordId;

	    private Integer carId;

	    private String carNumber;

	    private String cardId;

	    private String personName;

	    private Integer carTypeId;

	    private String carTypeName;

	    private Integer parkId;

	    private String parkName;

	    private Integer entranceId;

	    private String entranceName;

	    private Integer carriagewayId;

	    private String carriagewayName;

	    private Integer boothId;

	    private String boothName;

	    private Integer deviceId;

	    private String deviceName;

	    private Date eventTime;

	    private String photo;

	    private Integer flag;

	    private String note;

	    private Date insertTime;

	    private Integer userId;

	    private String userName;

	    private BigDecimal advance;

	    private Integer advanceType;

	    private Integer direction;

	    private Integer custom1;

	    private Integer custom2;

	    private String custom3;

	    private String custom4;

	    private Integer inRecordId;
	    
	    private String strdirection;

		public Integer getRecordId() {
			return recordId;
		}

		public void setRecordId(Integer recordId) {
			this.recordId = recordId;
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

		public Integer getCarTypeId() {
			return carTypeId;
		}

		public void setCarTypeId(Integer carTypeId) {
			this.carTypeId = carTypeId;
		}

		public String getCarTypeName() {
			return carTypeName;
		}

		public void setCarTypeName(String carTypeName) {
			this.carTypeName = carTypeName;
		}

		public Integer getParkId() {
			return parkId;
		}

		public void setParkId(Integer parkId) {
			this.parkId = parkId;
		}

		public String getParkName() {
			return parkName;
		}

		public void setParkName(String parkName) {
			this.parkName = parkName;
		}

		public Integer getEntranceId() {
			return entranceId;
		}

		public void setEntranceId(Integer entranceId) {
			this.entranceId = entranceId;
		}

		public String getEntranceName() {
			return entranceName;
		}

		public void setEntranceName(String entranceName) {
			this.entranceName = entranceName;
		}

		public Integer getCarriagewayId() {
			return carriagewayId;
		}

		public void setCarriagewayId(Integer carriagewayId) {
			this.carriagewayId = carriagewayId;
		}

		public String getCarriagewayName() {
			return carriagewayName;
		}

		public void setCarriagewayName(String carriagewayName) {
			this.carriagewayName = carriagewayName;
		}

		public Integer getBoothId() {
			return boothId;
		}

		public void setBoothId(Integer boothId) {
			this.boothId = boothId;
		}

		public String getBoothName() {
			return boothName;
		}

		public void setBoothName(String boothName) {
			this.boothName = boothName;
		}

		public Integer getDeviceId() {
			return deviceId;
		}

		public void setDeviceId(Integer deviceId) {
			this.deviceId = deviceId;
		}

		public String getDeviceName() {
			return deviceName;
		}

		public void setDeviceName(String deviceName) {
			this.deviceName = deviceName;
		}

		public Date getEventTime() {
			return eventTime;
		}

		public void setEventTime(Date eventTime) {
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
	    	String str=sdf.format(eventTime);
	    	if(str.equals("2000")){
	    		eventTime=null;
	    	}
			
			
			this.eventTime = eventTime;
		}

		public String getPhoto() {
			return photo;
		}

		public void setPhoto(String photo) {
			this.photo = photo;
		}

		public Integer getFlag() {
			return flag;
		}

		public void setFlag(Integer flag) {
			this.flag = flag;
		}

		public String getNote() {
			return note;
		}

		public void setNote(String note) {
			this.note = note;
		}

		public Date getInsertTime() {
			return insertTime;
		}

		public void setInsertTime(Date insertTime) {
			this.insertTime = insertTime;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
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

		public Integer getAdvanceType() {
			return advanceType;
		}

		public void setAdvanceType(Integer advanceType) {
			this.advanceType = advanceType;
		}

		public String getDirection() {

				if(direction==1){
					return "进";
				}else{
					return "出";
				}
		}

		public void setDirection(Integer direction) {
			this.direction = direction;
		}

		public Integer getCustom1() {
			return custom1;
		}

		public void setCustom1(Integer custom1) {
			this.custom1 = custom1;
		}

		public Integer getCustom2() {
			return custom2;
		}

		public void setCustom2(Integer custom2) {
			this.custom2 = custom2;
		}

		public String getCustom3() {
			return custom3;
		}

		public void setCustom3(String custom3) {
			this.custom3 = custom3;
		}

		public String getCustom4() {
			return custom4;
		}

		public void setCustom4(String custom4) {
			this.custom4 = custom4;
		}

		public Integer getInRecordId() {
			return inRecordId;
		}

		public void setInRecordId(Integer inRecordId) {
			this.inRecordId = inRecordId;
		}

		public String getStrdirection() {
			return strdirection;
		}

		public void setStrdirection(String strdirection) {
			this.strdirection = strdirection;
		}
	    
}
