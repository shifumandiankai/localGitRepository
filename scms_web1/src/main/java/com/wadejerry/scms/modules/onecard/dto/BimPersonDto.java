package com.wadejerry.scms.modules.onecard.dto;

import java.util.Date;

public class BimPersonDto {
private Integer personId;//人员id
private Integer bimCompanyId;//企业id
private String personCode;//人员编号
private String personName;//姓名
private Integer bimDeptId;//部门Id
private Integer sex;//性别
private String certtype;//证件类型
private String certId;//证件号码
private Integer custom1;//短信开门
private String custom3;//身份类型
private String pinyin;//拼音代码
private String phone;//联系电话
private String address;//联系地址
private String photo1;//照片
private Date birthday;//出生日期
private String deptName;//部门名称
private String englishName;//英文名
private String mail;//E-MAIL
private Date jobDate;//入职日期
private Date resignationDate;//离职日期
private String educational;//学历
private String ethnic;//名族
private String custom4;
private String imageSrc;//头像的的路径径
private Integer fingerprint1Num;
private Integer fingerprint2Num;
private String fingerprintGuid;
private String fingerprint1;
private String fingerprint2;
private String photoSrc;
public String getDeptName() {
	return deptName;
}
public void setDeptName(String deptName) {
	this.deptName = deptName;
}
public Integer getPersonId() {
	return personId;
}
public void setPersonId(Integer personId) {
	this.personId = personId;
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
/*public String getBimDeptId() {
	return bimDeptId;
}
public void setBimDeptId(String bimDeptId) {
	this.bimDeptId = bimDeptId;
}*/
public Integer getSex() {
	return sex;
}
public void setSex(Integer sex) {
	this.sex = sex;
}
public String getCerttype() {
	return certtype;
}
public void setCerttype(String certtype) {
	this.certtype = certtype;
}
/*public Integer getCertId() {
	return certId;
}
public void setCertId(Integer certId) {
	this.certId = certId;
}*/
public String getCustom3() {
	return custom3;
}
public void setCustom3(String custom3) {
	this.custom3 = custom3;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getPhoto1() {
	return photo1;
}
public void setPhoto1(String photo1) {
	this.photo1 = photo1;
}
public String getCertId() {
	return certId;
}
public void setCertId(String certId) {
	this.certId = certId;
}
/*public String getCustom4() {
	return custom4;
}
public void setCustom4(String custom4) {
	this.custom4 = custom4;
}*/

public Integer getCustom1() {
	return custom1;
}
public String getPinyin() {
	return pinyin;
}
public void setPinyin(String pinyin) {
	this.pinyin = pinyin;
}
public void setCustom1(Integer custom1) {
	this.custom1 = custom1;
}
public Integer getBimCompanyId() {
	return bimCompanyId;
}
public void setBimCompanyId(Integer bimCompanyId) {
	this.bimCompanyId = bimCompanyId;
}
public Date getBirthday() {
	return birthday;
}
public void setBirthday(Date birthday) {
	this.birthday = birthday;
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
public String getCustom4() {
	return custom4;
}
public void setCustom4(String custom4) {
	this.custom4 = custom4;
}
public String getImageSrc() {
	return imageSrc;
}
public void setImageSrc(String imageSrc) {
	this.imageSrc = imageSrc;
}
public Integer getFingerprint1Num() {
	return fingerprint1Num;
}
public void setFingerprint1Num(Integer fingerprint1Num) {
	this.fingerprint1Num = fingerprint1Num;
}
public Integer getFingerprint2Num() {
	return fingerprint2Num;
}
public void setFingerprint2Num(Integer fingerprint2Num) {
	this.fingerprint2Num = fingerprint2Num;
}
public String getFingerprintGuid() {
	return fingerprintGuid;
}
public void setFingerprintGuid(String fingerprintGuid) {
	this.fingerprintGuid = fingerprintGuid;
}
public String getFingerprint1() {
	return fingerprint1;
}
public void setFingerprint1(String fingerprint1) {
	this.fingerprint1 = fingerprint1;
}
public String getPhotoSrc() {
	return photoSrc;
}
public void setPhotoSrc(String photoSrc) {
	this.photoSrc = photoSrc;
}
public String getFingerprint2() {
	return fingerprint2;
}
public void setFingerprint2(String fingerprint2) {
	this.fingerprint2 = fingerprint2;
}




}
