package com.wadejerry.scms.modules.onecard.dto;

import java.util.Date;

public class BimDeptDto {
private Integer bimDeptId;
private Integer bimCompanyId;
private Integer deptCode;
private String pinyin;
private String deptName;
private Integer inCode;
private String use_system;
private Date createTime;
private Date updateTime;
private String note;
private Integer deptLevel;
private Integer custom1;//短信开门
private Integer custom2;
private String custom3;//身份类型
private String custom4;
private Integer isDel;
private String useSystem;
private String inCodeName;//上级部门名称
private Integer type;//类型：0 添加 ,1 修改
public Integer getBimDeptId() {
	return bimDeptId;
}
public void setBimDeptId(Integer bimDeptId) {
	this.bimDeptId = bimDeptId;
}
public Integer getBimCompanyId() {
	return bimCompanyId;
}
public void setBimCompanyId(Integer bimCompanyId) {
	this.bimCompanyId = bimCompanyId;
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
public String getDeptName() {
	return deptName;
}
public void setDeptName(String deptName) {
	this.deptName = deptName;
}
public Integer getInCode() {
	return inCode;
}
public void setInCode(Integer inCode) {
	this.inCode = inCode;
}
public String getUse_system() {
	return use_system;
}
public void setUse_system(String use_system) {
	this.use_system = use_system;
}
public Date getCreateTime() {
	return createTime;
}
public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
public Date getUpdateTime() {
	return updateTime;
}
public void setUpdateTime(Date updateTime) {
	this.updateTime = updateTime;
}
public String getNote() {
	return note;
}
public void setNote(String note) {
	this.note = note;
}
public Integer getDeptLevel() {
	return deptLevel;
}
public void setDeptLevel(Integer deptLevel) {
	this.deptLevel = deptLevel;
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
public Integer getIsDel() {
	return isDel;
}
public void setIsDel(Integer isDel) {
	this.isDel = isDel;
}
public String getUseSystem() {
	return useSystem;
}
public void setUseSystem(String useSystem) {
	this.useSystem = useSystem;
}
public String getInCodeName() {
	return inCodeName;
}
public void setInCodeName(String inCodeName) {
	this.inCodeName = inCodeName;
}
public Integer getType() {
	return type;
}
public void setType(Integer type) {
	this.type = type;
}

}
