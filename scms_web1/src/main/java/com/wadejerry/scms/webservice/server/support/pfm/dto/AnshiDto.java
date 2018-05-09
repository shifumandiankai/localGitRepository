package com.wadejerry.scms.webservice.server.support.pfm.dto;

import java.math.BigDecimal;

/**
* 按时收费DTO 
* @ClassName: AnshiDto
* @Description: TODO
* @author zhanying
* @date 2017年2月8日 下午4:36:01
*
 */
public class AnshiDto {
	private int anshiChargeId; //按时收费ID
	private int freeTime; //免费停车时长
	private int firstPartChargeTime; //
	private BigDecimal firstPartChargeMoney;//
	private int separateChargeTime;//
	private BigDecimal separateChargeMoney;//
	private int useTopMoney;//启用封顶 0 启用 1 不启用
	private BigDecimal topMoney; //封顶金额
	private int ruleId; //规则id
	private int custom1; //备用字段
    private int custom2; //备用字段
    private String custom3; //备用字段
    private String custom4; //备用字段
    
	public BigDecimal getTopMoney() {
		return topMoney;
	}
	public void setTopMoney(BigDecimal topMoney) {
		this.topMoney = topMoney;
	}
	public int getAnshiChargeId() {
		return anshiChargeId;
	}
	public void setAnshiChargeId(int anshiChargeId) {
		this.anshiChargeId = anshiChargeId;
	}
	public int getFreeTime() {
		return freeTime;
	}
	public void setFreeTime(int freeTime) {
		this.freeTime = freeTime;
	}
	public int getFirstPartChargeTime() {
		return firstPartChargeTime;
	}
	public void setFirstPartChargeTime(int firstPartChargeTime) {
		this.firstPartChargeTime = firstPartChargeTime;
	}
	public BigDecimal getFirstPartChargeMoney() {
		return firstPartChargeMoney;
	}
	public void setFirstPartChargeMoney(BigDecimal firstPartChargeMoney) {
		this.firstPartChargeMoney = firstPartChargeMoney;
	}
	public int getSeparateChargeTime() {
		return separateChargeTime;
	}
	public void setSeparateChargeTime(int separateChargeTime) {
		this.separateChargeTime = separateChargeTime;
	}
	public BigDecimal getSeparateChargeMoney() {
		return separateChargeMoney;
	}
	public void setSeparateChargeMoney(BigDecimal separateChargeMoney) {
		this.separateChargeMoney = separateChargeMoney;
	}
	public int getUseTopMoney() {
		return useTopMoney;
	}
	public void setUseTopMoney(int useTopMoney) {
		this.useTopMoney = useTopMoney;
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
