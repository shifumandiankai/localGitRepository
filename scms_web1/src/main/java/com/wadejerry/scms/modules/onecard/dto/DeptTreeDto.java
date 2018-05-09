package com.wadejerry.scms.modules.onecard.dto;

public class DeptTreeDto {
private Integer bimDeptId;
private Integer deptCode;
private Integer id;
private Integer pId;
//private String pId;
private String name;
private Integer SpId;
private String custom3;
private String custom4;
private Integer custom1;
private Integer custom2;
private String pinyin;
private String pName;


public String getpName() {
	return pName;
}
public void setpName(String pName) {
	this.pName = pName;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}

/*public String getpId() {
	return pId;
}
public void setpId(String pId) {
	this.pId = pId;
}*/
public Integer getpId() {
	return pId;
}
public void setpId(Integer pId) {
	this.pId = pId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
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
public Integer getSpId() {
	return SpId;
}
public void setSpId(Integer spId) {
	SpId = spId;
}
public Integer getBimDeptId() {
	return bimDeptId;
}
public void setBimDeptId(Integer bimDeptId) {
	this.bimDeptId = bimDeptId;
}
public Integer getDeptCode() {
	return deptCode;
}
public void setDeptCode(Integer deptCode) {
	this.deptCode = deptCode;
}
public String getPinyin() {
	return pinyin;
}
public void setPinyin(String pinyin) {
	this.pinyin = pinyin;
}




}
