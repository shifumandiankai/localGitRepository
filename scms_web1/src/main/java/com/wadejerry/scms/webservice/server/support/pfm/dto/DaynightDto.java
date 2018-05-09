package com.wadejerry.scms.webservice.server.support.pfm.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
* 日夜分时按次DTO类 
* @ClassName: DaynightDto
* @Description: TODO
* @author zhanying
* @date 2017年2月9日 上午8:58:11
*
 */
public class DaynightDto {
	
	private int daynightId; //id
	private long periodStartTime; //按时段收费开始时间
	private int firstTime; //首段时间 分钟
	private BigDecimal firstTimeCharge; //首段时间收费
	private int unitTime; //单位时间
	private BigDecimal unitTimeCharge;	//单位时间收费
	private long pertimeStartTime; //按此收费开始时间
	private BigDecimal pertimeCharge;	//每次收费金额
	private int freeTime; //免费时长
	private int ifChargeOverFreeTime; //超过时长是否收费 0:收费 1:不收费
	private int useTopMomeyIn24; //24小时内是否启用封顶金额 0 启用 1 不起用
	private BigDecimal topMoney; //封顶金额
	private int oneOne; //按次收费时一次出入算一次 0 是 1 不是
	private int byTimePeriod; //24小时内存在按时段收费则全按时段收费 0 是 1 不是
	private int ruleId; //规则id
	private int custom1; //备用字段
    private int custom2; //备用字段
    private String custom3; //备用字段
    private String custom4; //备用字段
    
    
	public int getDaynightId() {
		return daynightId;
	}
	public void setDaynightId(int daynightId) {
		this.daynightId = daynightId;
	}
	public long getPeriodStartTime() {
		return periodStartTime;
	}
	public void setPeriodStartTime(Date periodStartTime) {
		this.periodStartTime = periodStartTime.getTime();
	}
	public int getFirstTime() {
		return firstTime;
	}
	public void setFirstTime(int firstTime) {
		this.firstTime = firstTime;
	}
	public BigDecimal getFirstTimeCharge() {
		return firstTimeCharge;
	}
	public void setFirstTimeCharge(BigDecimal firstTimeCharge) {
		this.firstTimeCharge = firstTimeCharge;
	}
	public int getUnitTime() {
		return unitTime;
	}
	public void setUnitTime(int unitTime) {
		this.unitTime = unitTime;
	}
	public BigDecimal getUnitTimeCharge() {
		return unitTimeCharge;
	}
	public void setUnitTimeCharge(BigDecimal unitTimeCharge) {
		this.unitTimeCharge = unitTimeCharge;
	}
	public long getPertimeStartTime() {
		return pertimeStartTime;
	}
	public void setPertimeStartTime(Date pertimeStartTime) {
		this.pertimeStartTime = pertimeStartTime.getTime();
	}
	public BigDecimal getPertimeCharge() {
		return pertimeCharge;
	}
	public void setPertimeCharge(BigDecimal pertimeCharge) {
		this.pertimeCharge = pertimeCharge;
	}
	public int getFreeTime() {
		return freeTime;
	}
	public void setFreeTime(int freeTime) {
		this.freeTime = freeTime;
	}
	public int getIfChargeOverFreeTime() {
		return ifChargeOverFreeTime;
	}
	public void setIfChargeOverFreeTime(int ifChargeOverFreeTime) {
		this.ifChargeOverFreeTime = ifChargeOverFreeTime;
	}
	public int getUseTopMomeyIn24() {
		return useTopMomeyIn24;
	}
	public void setUseTopMomeyIn24(int useTopMomeyIn24) {
		this.useTopMomeyIn24 = useTopMomeyIn24;
	}
	public BigDecimal getTopMoney() {
		return topMoney;
	}
	public void setTopMoney(BigDecimal topMoney) {
		this.topMoney = topMoney;
	}
	public int getOneOne() {
		return oneOne;
	}
	public void setOneOne(int oneOne) {
		this.oneOne = oneOne;
	}
	public int getByTimePeriod() {
		return byTimePeriod;
	}
	public void setByTimePeriod(int byTimePeriod) {
		this.byTimePeriod = byTimePeriod;
	}
	public int getRuleId() {
		return ruleId;
	}
	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}
	public int getCustom1() {
		return custom1;
	}
	public void setCustom1(int custom1) {
		this.custom1 = custom1;
	}
	public int getCustom2() {
		return custom2;
	}
	public void setCustom2(int custom2) {
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
}
