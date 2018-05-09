package com.wadejerry.scms.webservice.server.support.pfm.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.wadejerry.scms.utils.date.DateUtil;


/**
*  收费记录 
* @ClassName: ChargeRecordDto
* @Description: TODO
* @author zhanying
* @date 2017年2月9日 下午12:00:21
*
 */
public class ChargeRecordDto {
	
	public int chargeRecordId;
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
	public int inUserId;
	public String inUserName;
	public int outEntranceId;
	public String outEntranceName;
	public int outCarriagewayId;
	public String outCarriagewayName;
	public int outBoothId;
	public String outBoothName;
	public int outDeviceId;
	public String outDeviceName;
	public String strOutTime;
	public String outPhoto;
	public int ruleId;
	public String ruleName;
	public int ruleType;
	public int parkingduration;
	public BigDecimal chargefee;
	public BigDecimal balance;
	public String strDisableTime;
	public int paymethod;
	public BigDecimal jianmianfee;
	public String jianmianrulename;
	public String note;
	public int flag;
	public int upload;
	public String strInsertTime;
	public int userId;
	public String userName;
	public int custom1;
	public int custom2;
	public String custom3;
	public String custom4;
	
	public int getChargeRecordId() {
		return chargeRecordId;
	}
	public void setChargeRecordId(int chargeRecordId) {
		this.chargeRecordId = chargeRecordId;
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
	public void setStrInTime(Date strInTime) {
		this.strInTime = DateUtil.df.format(strInTime);
	}
	public String getInPhoto() {
		return inPhoto;
	}
	public void setInPhoto(String inPhoto) {
		this.inPhoto = inPhoto;
	}
	public int getInUserId() {
		return inUserId;
	}
	public void setInUserId(int inUserId) {
		this.inUserId = inUserId;
	}
	public String getInUserName() {
		return inUserName;
	}
	public void setInUserName(String inUserName) {
		this.inUserName = inUserName;
	}
	public int getOutEntranceId() {
		return outEntranceId;
	}
	public void setOutEntranceId(int outEntranceId) {
		this.outEntranceId = outEntranceId;
	}
	public String getOutEntranceName() {
		return outEntranceName;
	}
	public void setOutEntranceName(String outEntranceName) {
		this.outEntranceName = outEntranceName;
	}
	public int getOutCarriagewayId() {
		return outCarriagewayId;
	}
	public void setOutCarriagewayId(int outCarriagewayId) {
		this.outCarriagewayId = outCarriagewayId;
	}
	public String getOutCarriagewayName() {
		return outCarriagewayName;
	}
	public void setOutCarriagewayName(String outCarriagewayName) {
		this.outCarriagewayName = outCarriagewayName;
	}
	public int getOutBoothId() {
		return outBoothId;
	}
	public void setOutBoothId(int outBoothId) {
		this.outBoothId = outBoothId;
	}
	public String getOutBoothName() {
		return outBoothName;
	}
	public void setOutBoothName(String outBoothName) {
		this.outBoothName = outBoothName;
	}
	public int getOutDeviceId() {
		return outDeviceId;
	}
	public void setOutDeviceId(int outDeviceId) {
		this.outDeviceId = outDeviceId;
	}
	public String getOutDeviceName() {
		return outDeviceName;
	}
	public void setOutDeviceName(String outDeviceName) {
		this.outDeviceName = outDeviceName;
	}
	public String getStrOutTime() {
		return strOutTime;
	}
	public void setStrOutTime(Date strOutTime) {
		this.strOutTime = DateUtil.df.format(strOutTime);
	}
	public String getOutPhoto() {
		return outPhoto;
	}
	public void setOutPhoto(String outPhoto) {
		this.outPhoto = outPhoto;
	}
	public int getRuleId() {
		return ruleId;
	}
	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public int getRuleType() {
		return ruleType;
	}
	public void setRuleType(int ruleType) {
		this.ruleType = ruleType;
	}
	public int getParkingduration() {
		return parkingduration;
	}
	public void setParkingduration(int parkingduration) {
		this.parkingduration = parkingduration;
	}
	public BigDecimal getChargefee() {
		return chargefee;
	}
	public void setChargefee(BigDecimal chargefee) {
		this.chargefee = chargefee;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getStrDisableTime() {
		return strDisableTime;
	}
	public void setStrDisableTime(Date strDisableTime) {
		this.strDisableTime = DateUtil.df.format(strDisableTime);
	}
	public int getPaymethod() {
		return paymethod;
	}
	public void setPaymethod(int paymethod) {
		this.paymethod = paymethod;
	}
	public BigDecimal getJianmianfee() {
		return jianmianfee;
	}
	public void setJianmianfee(BigDecimal jianmianfee) {
		this.jianmianfee = jianmianfee;
	}
	public String getJianmianrulename() {
		return jianmianrulename;
	}
	public void setJianmianrulename(String jianmianrulename) {
		this.jianmianrulename = jianmianrulename;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getUpload() {
		return upload;
	}
	public void setUpload(int upload) {
		this.upload = upload;
	}
	public String getStrInsertTime() {
		return strInsertTime;
	}
	public void setStrInsertTime(Date strInsertTime) {
		this.strInsertTime = DateUtil.df.format(strInsertTime);
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
	public int getCustom1() {
		return custom1;
	}
	public void setCustom1(int custom1) {
		this.custom1 = custom1;
	}
	public int getCustom2() {
		return custom2;
	}
	public void setCustom2(int custom2) {
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

}
