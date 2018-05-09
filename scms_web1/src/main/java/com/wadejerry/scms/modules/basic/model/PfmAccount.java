package com.wadejerry.scms.modules.basic.model;

import java.math.BigDecimal;
import java.util.Date;

public class PfmAccount {
private Integer carInfoId;
private String cardId;
private String carInfoCode;
private String carNumber;
private String personName;
private String phone;
private Integer pfmFeeRecId;
private String strfee;
private String strlastfee;
private Date time;
private String userName;
private BigDecimal bdfee;
private BigDecimal bdlastfee;
private BigDecimal bdbeforefee;
private String strtime;
public String getCardId() {
	return cardId;
}
public void setCardId(String cardId) {
	this.cardId = cardId;
}

public Integer getCarInfoId() {
	return carInfoId;
}
public void setCarInfoId(Integer carInfoId) {
	this.carInfoId = carInfoId;
}
public String getCarInfoCode() {
	return carInfoCode;
}
public void setCarInfoCode(String carInfoCode) {
	this.carInfoCode = carInfoCode;
}
/*public String getCarCode() {
	return carCode;
}
public void setCarCode(String carCode) {
	this.carCode = carCode;
}*/
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
public Integer getPfmFeeRecId() {
	return pfmFeeRecId;
}
public void setPfmFeeRecId(Integer pfmFeeRecId) {
	this.pfmFeeRecId = pfmFeeRecId;
}
public String getStrfee() {
	return strfee;
}
public void setStrfee(String strfee) {
	this.strfee = strfee;
}
public String getStrlastfee() {
	return strlastfee;
}
public void setStrlastfee(String strlastfee) {
	this.strlastfee = strlastfee;
}
public Date getTime() {
	return time;
}
public void setTime(Date time) {
	this.time = time;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public BigDecimal getBdfee() {
	return bdfee;
}
public void setBdfee(BigDecimal bdfee) {
	this.bdfee = bdfee;
}
public BigDecimal getBdlastfee() {
	return bdlastfee;
}
public void setBdlastfee(BigDecimal bdlastfee) {
	this.bdlastfee = bdlastfee;
}
public BigDecimal getBdbeforefee() {
	return bdbeforefee;
}
public void setBdbeforefee(BigDecimal bdbeforefee) {
	this.bdbeforefee = bdbeforefee;
}
public String getStrtime() {
	return strtime;
}
public void setStrtime(String strtime) {
	this.strtime = strtime;
}

}
