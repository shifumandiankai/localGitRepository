package com.wadejerry.scms.modules.pfm.model;

import java.math.BigDecimal;

public class PfmUnitPeriod {
    private Integer unitPeriodId;

    private Short freeTime;

    private Short useTopMoney;

    private Short includeFreeTime;

    private Integer custom1;

    private Integer custom2;

    private String custom3;

    private String custom4;

    private Integer ruleId;
    
    private BigDecimal topMoney;

    public Integer getUnitPeriodId() {
        return unitPeriodId;
    }

    public void setUnitPeriodId(Integer unitPeriodId) {
        this.unitPeriodId = unitPeriodId;
    }

    public Short getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(Short freeTime) {
        this.freeTime = freeTime;
    }

    public Short getUseTopMoney() {
        return useTopMoney;
    }

    public void setUseTopMoney(Short useTopMoney) {
        this.useTopMoney = useTopMoney;
    }

    public Short getIncludeFreeTime() {
        return includeFreeTime;
    }

    public void setIncludeFreeTime(Short includeFreeTime) {
        this.includeFreeTime = includeFreeTime;
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

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

	public BigDecimal getTopMoney() {
		return topMoney;
	}

	public void setTopMoney(BigDecimal topMoney) {
		this.topMoney = topMoney;
	}
}