package com.wadejerry.scms.webservice.server.support.pfm.dto;

import java.sql.Date;
import java.sql.Time;

/**
 * 分时收费的时间段
* @ClassName: TimeChargeTimeDto
* @Description: TODO
* @author zhanying
* @date 2017年2月8日 下午4:19:15
*
 */
public class TimeChargeTimeDto {
	 private Integer timeChargeTimeId;

	    private long startTime;

	    private long endTime;

	    private double chargeMoney;

	    private Integer timeChargeId;

	    private Integer custom1;

	    private Integer custom2;

	    private String custom3;

	    private String custom4;

	    public Integer getTimeChargeTimeId() {
	        return timeChargeTimeId;
	    }

	    public void setTimeChargeTimeId(Integer timeChargeTimeId) {
	        this.timeChargeTimeId = timeChargeTimeId;
	    }

	    public long getStartTime() {
	        return startTime;
	    }

	    public void setStartTime(Time startTime) {
	        this.startTime = startTime.getTime();
	    }

	    public long getEndTime() {
	        return endTime;
	    }

	    public void setEndTime(Time endTime) {
	        this.endTime = endTime.getTime();
	    }

	    public double getChargeMoney() {
	        return chargeMoney;
	    }

	    public void setChargeMoney(double chargeMoney) {
	        this.chargeMoney = chargeMoney;
	    }

	    public Integer getTimeChargeId() {
	        return timeChargeId;
	    }

	    public void setTimeChargeId(Integer timeChargeId) {
	        this.timeChargeId = timeChargeId;
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
}
