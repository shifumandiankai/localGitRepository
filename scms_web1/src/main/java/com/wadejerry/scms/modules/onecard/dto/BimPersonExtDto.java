package com.wadejerry.scms.modules.onecard.dto;

import java.util.Date;

public class BimPersonExtDto {
private Integer personExtId;
private Integer personId;
private String personCode;
private String personName;
private Integer bimDeptId;
private String englishName;
private String mail;
private Date jobDate;
private Date resignationDate;
private String educational;
private String ethnic;
private Date birthday;

public Integer getPersonExtId() {
	return personExtId;
}
public void setPersonExtId(Integer personExtId) {
	this.personExtId = personExtId;
}
public String getPersonCode() {
	return personCode;
}
public void setPersonCode(String personCode) {
	this.personCode = personCode;
}
public String getPersonName() {
	return personName;
}
public void setPersonName(String personName) {
	this.personName = personName;
}
public Integer getBimDeptId() {
	return bimDeptId;
}
public void setBimDeptId(Integer bimDeptId) {
	this.bimDeptId = bimDeptId;
}
public String getEnglishName() {
	return englishName;
}
public void setEnglishName(String englishName) {
	this.englishName = englishName;
}
public String getMail() {
	return mail;
}
public void setMail(String mail) {
	this.mail = mail;
}
public Date getJobDate() {
	return jobDate;
}
public void setJobDate(Date jobDate) {
	this.jobDate = jobDate;
}
public Date getResignationDate() {
	return resignationDate;
}
public void setResignationDate(Date resignationDate) {
	this.resignationDate = resignationDate;
}
public String getEducational() {
	return educational;
}
public void setEducational(String educational) {
	this.educational = educational;
}
public String getEthnic() {
	return ethnic;
}
public void setEthnic(String ethnic) {
	this.ethnic = ethnic;
}
public Integer getPersonId() {
	return personId;
}
public void setPersonId(Integer personId) {
	this.personId = personId;
}
public Date getBirthday() {
	return birthday;
}
public void setBirthday(Date birthday) {
	this.birthday = birthday;
}


}
