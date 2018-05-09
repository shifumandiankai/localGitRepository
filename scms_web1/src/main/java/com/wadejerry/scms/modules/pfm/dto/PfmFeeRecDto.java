package com.wadejerry.scms.modules.pfm.dto;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

public class PfmFeeRecDto {
	private Integer pfmFeeRecId;
	private Integer bimCompanyId;
	private String carTypeName;
	private Integer carTypeId;
	private String carNumber;
	private String personName;
	private BigDecimal fee;
	private BigDecimal lastFee;
	private String sfee;//从数据库中查询出来的
	private String slastFee;//从数据库中查询出来的
	private Date disableTime;//到期时间
	private Date lastDisableTime;//上一次的到期时间
	private Integer typeId;
	private Date time;
	//private Time time;
	/*public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}*/
	private String userName;
	private Date insertTime;
	private Integer merchantId;
	private Integer merchantName;
	//private Integer cardId;
	private String cardId;
	private Integer appId;
	private String appCode;
	private Integer tradeId;
	private Integer payMethod;
	private String note;
	private Date beforeDisableTime;
	private Integer carId;
	private String strfee;//去掉money类型中的￥等符号
	private String strTime;//Time Date转化成String
	private String strDisableTime;
    private BigDecimal bqmoney;//包期金额
    private Integer carType;//车辆类型：0储值1包期
    
    
    
	public Integer getPfmFeeRecId() {
		return pfmFeeRecId;
	}
	public void setPfmFeeRecId(Integer pfmFeeRecId) {
		this.pfmFeeRecId = pfmFeeRecId;
	}
	/*public Integer getPfmFeeRecId() {
		return pfmFeeRecId;
	}
	public void setPfmFeeRecId(Integer pfmFeeRecId) {
		this.pfmFeeRecId = pfmFeeRecId;
	}*/
	public Integer getBimCompanyId() {
		return bimCompanyId;
	}
	public void setBimCompanyId(Integer bimCompanyId) {
		this.bimCompanyId = bimCompanyId;
	}
	public String getCarTypeName() {
		return carTypeName;
	}
	public void setCarTypeName(String carTypeName) {
		this.carTypeName = carTypeName;
	}
	public Integer getCarTypeId() {
		return carTypeId;
	}
	public void setCarTypeId(Integer carTypeId) {
		this.carTypeId = carTypeId;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	
	public Date getDisableTime() {
		return disableTime;
	}
	public void setDisableTime(Date disableTime) {
		this.disableTime = disableTime;
	}
	/*public Date getLastDisabeTime() {
		return lastDisabeTime;
	}
	public void setLastDisabeTime(Date lastDisabeTime) {
		this.lastDisabeTime = lastDisabeTime;
	}*/
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	public Integer getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(Integer merchantName) {
		this.merchantName = merchantName;
	}
	/*public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}*/
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public Integer getTradeId() {
		return tradeId;
	}
	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}
	public Integer getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getBeforeDisableTime() {
		return beforeDisableTime;
	}
	public void setBeforeDisableTime(Date beforeDisableTime) {
		this.beforeDisableTime = beforeDisableTime;
	}
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	public String getStrfee() {
		return strfee;
	}
	public void setStrfee(String strfee) {
		this.strfee = strfee;
	}
	/*public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getLastFee() {
		return lastFee;
	}
	public void setLastFee(String lastFee) {
		this.lastFee = lastFee;
	}*/
	public String getStrTime() {
		return strTime;
	}
	public void setStrTime(String strTime) {
		this.strTime = strTime;
	}
	public Date getLastDisableTime() {
		return lastDisableTime;
	}
	public void setLastDisableTime(Date lastDisableTime) {
		this.lastDisableTime = lastDisableTime;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public BigDecimal getLastFee() {
		return lastFee;
	}
	public void setLastFee(BigDecimal lastFee) {
		this.lastFee = lastFee;
	}
	public String getSfee() {
		return sfee;
	}
	public void setSfee(String sfee) {
		this.sfee = sfee;
	}
	public String getSlastFee() {
		return slastFee;
	}
	public void setSlastFee(String slastFee) {
		this.slastFee = slastFee;
	}
	public BigDecimal getBqmoney() {
		return bqmoney;
	}
	public void setBqmoney(BigDecimal bqmoney) {
		this.bqmoney = bqmoney;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getStrDisableTime() {
		return strDisableTime;
	}
	public void setStrDisableTime(String strDisableTime) {
		this.strDisableTime = strDisableTime;
	}
	public Integer getCarType() {
		return carType;
	}
	public void setCarType(Integer carType) {
		this.carType = carType;
	}
	
	
	
	
	
}
