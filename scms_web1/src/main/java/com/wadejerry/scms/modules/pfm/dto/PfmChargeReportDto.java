package com.wadejerry.scms.modules.pfm.dto;

import java.util.Date;

public class PfmChargeReportDto {
private String charge;
private String normal;
private String abnormal;
private Date startTime;
private Date endTime;
//private String startTime;
//private String endTime;
private String carNumber;
private String entranceName;
private Integer direction;
public String getCharge() {
	return charge;
}
public void setCharge(String charge) {
	this.charge = charge;
}
public String getNormal() {
	return normal;
}
public void setNormal(String normal) {
	this.normal = normal;
}
public String getAbnormal() {
	return abnormal;
}
public void setAbnormal(String abnormal) {
	this.abnormal = abnormal;
}

public String getCarNumber() {
	return carNumber;
}
public void setCarNumber(String carNumber) {
	this.carNumber = carNumber;
}
public String getEntranceName() {
	return entranceName;
}
public void setEntranceName(String entranceName) {
	this.entranceName = entranceName;
}
public Integer getDirection() {
	return direction;
}
public void setDirection(Integer direction) {
	this.direction = direction;
}
public Date getStartTime() {
	return startTime;
}
public void setStartTime(Date startTime) {
	this.startTime = startTime;
}
public Date getEndTime() {
	return endTime;
}
public void setEndTime(Date endTime) {
	this.endTime = endTime;
}

}
