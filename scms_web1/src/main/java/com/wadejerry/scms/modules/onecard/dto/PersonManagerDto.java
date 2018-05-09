package com.wadejerry.scms.modules.onecard.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PersonManagerDto {
	private Integer personId;
	private String personCode;
	private String personName;
	private Integer sex;
	private String certId;
	private String certtype;
	private String pinyin;
	private String phone;
	private Integer deptLevel;
	private Integer bimDeptId;
	private String strsex;
	private String deptName;
	/*private Date enabledate;
	private Date disabledate;*/
	private String address;
	private String mail;
	private Date jobDate;
	private Date resignationDate;
	private Date birthday;
	private String national;
	private String ethnic;
	private String englishName;
	private String note;
	private String educational;
	private String custom3;
	private String photo1;
	private Integer fingerprint1Num;
	private Integer fingerprint2Num;
	private Integer personPhotoId;
	private String fingerprint1;
	private String fingerprint2;
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String  getSex() {
		String result=null;
		if(sex==0){
			result="男";
		}else{
			result="女";
		}
		return result;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getCertId() {
		return certId;
	}
	public void setCertId(String certId) {
		this.certId = certId;
	}
	/*public String getCerttype() {
		String result=null;
		switch (certtype) {
		case "0":
			result="身份证";
			break;
		case "1":
			result="军官证";
			break;
		case "2":
			result="学生证";
			break;
		case "3":
			result="驾驶证";
			break;
		case "4":
			result="护照";
			break;
		case "5":
			result="港澳通行证";
			break;
		default:
			break;
		}
		
		return result;
	}
	public void setCerttype(String certtype) {
		this.certtype = certtype;
	}*/
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPersonCode() {
		return personCode;
	}
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}
	public Integer getDeptLevel() {
		return deptLevel;
	}
	public void setDeptLevel(Integer deptLevel) {
		this.deptLevel = deptLevel;
	}
	public Integer getBimDeptId() {
		return bimDeptId;
	}
	public void setBimDeptId(Integer bimDeptId) {
		this.bimDeptId = bimDeptId;
	}
	public String getStrsex() {
		return strsex;
	}
	public void setStrsex(String strsex) {
		this.strsex = strsex;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
	@JsonFormat(pattern="yyyy-MM-dd")
	public Date getJobDate() {
		return jobDate;
	}
	public void setJobDate(Date jobDate) {
		this.jobDate = jobDate;
	}
	@JsonFormat(pattern="yyyy-MM-dd")
	public Date getResignationDate() {
		return resignationDate;
	}
	public void setResignationDate(Date resignationDate) {
		this.resignationDate = resignationDate;
	}
	@JsonFormat(pattern="yyyy-MM-dd")
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getNational() {
		return national;
	}
	public void setNational(String national) {
		this.national = national;
	}
	public String getEthnic() {
		return ethnic;
	}
	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCerttype() {
		return certtype;
	}
	public void setCerttype(String certtype) {
		this.certtype = certtype;
	}
	public String getEducational() {
		return educational;
	}
	public void setEducational(String educational) {
		this.educational = educational;
	}
	public String getCustom3() {
		return custom3;
	}
	public void setCustom3(String custom3) {
		this.custom3 = custom3;
	}
	public String getPhoto1() {
		return photo1;
	}
	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
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
	public Integer getPersonPhotoId() {
		return personPhotoId;
	}
	public void setPersonPhotoId(Integer personPhotoId) {
		this.personPhotoId = personPhotoId;
	}
	public String getFingerprint1() {
		return fingerprint1;
	}
	public void setFingerprint1(String fingerprint1) {
		this.fingerprint1 = fingerprint1;
	}
	public String getFingerprint2() {
		return fingerprint2;
	}
	public void setFingerprint2(String fingerprint2) {
		this.fingerprint2 = fingerprint2;
	}
	
	
	
	
	
}
