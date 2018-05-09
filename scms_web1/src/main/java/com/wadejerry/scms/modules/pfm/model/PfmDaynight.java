package com.wadejerry.scms.modules.pfm.model;

import java.math.BigDecimal;
import java.util.Date;

public class PfmDaynight {
    private Integer daynightId;

    private Date periodStartTime;

    private Integer firstTime;

    private BigDecimal firstTimeCharge;

    private Short unitTime;

    private BigDecimal unitTimeCharge;

    private Date pertimeStartTime;

    private BigDecimal pertimeCharge;

    private Short freeTime;

    private Short ifChargeOverFreeTime;

    private Short useTopMomeyIn24;

    private Short oneOne;

    private Short byTimePeriod;

    private Integer ruleId;

    private Integer custom2;

    private String custom3;

    private String custom4;
    
    private BigDecimal topMoney;

    public Integer getDaynightId() {
        return daynightId;
    }

    public void setDaynightId(Integer daynightId) {
        this.daynightId = daynightId;
    }

    public Date getPeriodStartTime() {
        return periodStartTime;
    }

    public void setPeriodStartTime(Date periodStartTime) {
        this.periodStartTime = periodStartTime;
    }

    public Integer getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(Integer firstTime) {
        this.firstTime = firstTime;
    }

    public BigDecimal getFirstTimeCharge() {
        return firstTimeCharge;
    }

    public void setFirstTimeCharge(BigDecimal firstTimeCharge) {
        this.firstTimeCharge = firstTimeCharge;
    }

    public Short getUnitTime() {
        return unitTime;
    }

    public void setUnitTime(Short unitTime) {
        this.unitTime = unitTime;
    }

    public BigDecimal getUnitTimeCharge() {
        return unitTimeCharge;
    }

    public void setUnitTimeCharge(BigDecimal unitTimeCharge) {
        this.unitTimeCharge = unitTimeCharge;
    }

    public Date getPertimeStartTime() {
        return pertimeStartTime;
    }

    public void setPertimeStartTime(Date pertimeStartTime) {
        this.pertimeStartTime = pertimeStartTime;
    }

    public BigDecimal getPertimeCharge() {
        return pertimeCharge;
    }

    public void setPertimeCharge(BigDecimal pertimeCharge) {
        this.pertimeCharge = pertimeCharge;
    }

    public Short getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(Short freeTime) {
        this.freeTime = freeTime;
    }

    public Short getIfChargeOverFreeTime() {
        return ifChargeOverFreeTime;
    }

    public void setIfChargeOverFreeTime(Short ifChargeOverFreeTime) {
        this.ifChargeOverFreeTime = ifChargeOverFreeTime;
    }

    public Short getUseTopMomeyIn24() {
        return useTopMomeyIn24;
    }

    public void setUseTopMomeyIn24(Short useTopMomeyIn24) {
        this.useTopMomeyIn24 = useTopMomeyIn24;
    }

    public Short getOneOne() {
        return oneOne;
    }

    public void setOneOne(Short oneOne) {
        this.oneOne = oneOne;
    }

    public Short getByTimePeriod() {
        return byTimePeriod;
    }

    public void setByTimePeriod(Short byTimePeriod) {
        this.byTimePeriod = byTimePeriod;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
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

	public BigDecimal getTopMoney() {
		return topMoney;
	}

	public void setTopMoney(BigDecimal topMoney) {
		this.topMoney = topMoney;
	}
}