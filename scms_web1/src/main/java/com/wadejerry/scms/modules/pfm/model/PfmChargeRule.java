package com.wadejerry.scms.modules.pfm.model;

import java.util.Date;

public class PfmChargeRule {
    private Integer ruleId;

    private Integer bimCompanyId;

    private String ruleName;

    private Integer carTypeId;

    private Short ruleType;

    private Date insertTime;

    private Date updateTime;

    private String userName;

    private Integer custom1;

    private Integer custom2;

    private String custom3;

    private String custom4;

    private Short tempMatch;

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getBimCompanyId() {
        return bimCompanyId;
    }

    public void setBimCompanyId(Integer bimCompanyId) {
        this.bimCompanyId = bimCompanyId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    public Integer getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(Integer carTypeId) {
        this.carTypeId = carTypeId;
    }

    public Short getRuleType() {
        return ruleType;
    }

    public void setRuleType(Short ruleType) {
        this.ruleType = ruleType;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
        this.userName = userName == null ? null : userName.trim();
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
        this.custom3 = custom3 == null ? null : custom3.trim();
    }

    public String getCustom4() {
        return custom4;
    }

    public void setCustom4(String custom4) {
        this.custom4 = custom4 == null ? null : custom4.trim();
    }

    public Short getTempMatch() {
        return tempMatch;
    }

    public void setTempMatch(Short tempMatch) {
        this.tempMatch = tempMatch;
    }
}