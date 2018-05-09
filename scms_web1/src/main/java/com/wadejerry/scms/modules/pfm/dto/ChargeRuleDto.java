package com.wadejerry.scms.modules.pfm.dto;

import java.util.Date;

public class ChargeRuleDto {
	private Integer ruleId;
	private String ruleName;
	private String ruleType;
	private String typeName;
	private Integer ruleTypeIndex;
	private String tempMatch;
	private Date updateTime;
	private String userName;
	private Integer jianmianId;
	private Integer yichangId;
	private Integer mobilejianmianId;
	
	public Integer getRuleId() {
		return ruleId;
	}
	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTempMatch() {
		return tempMatch;
	}
	public void setTempMatch(String tempMatch) {
		this.tempMatch = tempMatch;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	public Integer getRuleTypeIndex() {
		return ruleTypeIndex;
	}
	public void setRuleTypeIndex(Integer ruleTypeIndex) {
		this.ruleTypeIndex = ruleTypeIndex;
	}
	public Integer getJianmianId() {
		return jianmianId;
	}
	public void setJianmianId(Integer jianmianId) {
		this.jianmianId = jianmianId;
	}
	public Integer getYichangId() {
		return yichangId;
	}
	public void setYichangId(Integer yichangId) {
		this.yichangId = yichangId;
	}
	public Integer getMobilejianmianId() {
		return mobilejianmianId;
	}
	public void setMobilejianmianId(Integer mobilejianmianId) {
		this.mobilejianmianId = mobilejianmianId;
	}

	

}
