package com.wadejerry.scms.modules.pfm.model;

import java.math.BigDecimal;

public class PfmPeriodTime {
    private Integer periodTimeId;

    private Short startPeriod;

    private Short endPeriod;

    private BigDecimal chargeMoney;

    private Integer custom1;

    private Integer custom2;

    private String custom3;

    private String custom4;

    private Integer periodId;

    public Integer getPeriodTimeId() {
        return periodTimeId;
    }

    public void setPeriodTimeId(Integer periodTimeId) {
        this.periodTimeId = periodTimeId;
    }

    public Short getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(Short startPeriod) {
        this.startPeriod = startPeriod;
    }

    public Short getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(Short endPeriod) {
        this.endPeriod = endPeriod;
    }

    public BigDecimal getChargeMoney() {
        return chargeMoney;
    }

    public void setChargeMoney(BigDecimal chargeMoney) {
        this.chargeMoney = chargeMoney;
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

    public Integer getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }
}