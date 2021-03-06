package com.wadejerry.scms.modules.pfm.model;

import java.math.BigDecimal;
import java.util.Date;

public class PfmFeeRec {
	private Integer pfmFeeRecId;
	private Integer bimCompanyId;
	private String carTypeName;
	private Integer carTypeId;
	private String carNumber;
	private String personName;
	private BigDecimal fee;
	private BigDecimal lastFee;
	private Date disableTime;
	private Date lastDisableTime;
	private String typeId;
	private Date time;
	private String userName;
	private Date insertTime;
	private Integer merchantId;
	private Integer merchantName;
	private String cardId;
	private Integer appId;
	private String appCode;
	private Integer tradeId;
	private Integer payMethod;
	private String note;
	private Date beforeDisableTime;
	private Integer carId;
	
	public Integer getPfmFeeRecId() {
		return pfmFeeRecId;
	}
	public void setPfmFeeRecId(Integer pfmFeeRecId) {
		this.pfmFeeRecId = pfmFeeRecId;
	}
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
	public Date getDisableTime() {
		return disableTime;
	}
	public void setDisableTime(Date disableTime) {
		this.disableTime = disableTime;
	}
	/*public Date getBeforeDisabeTime() {
		return beforeDisabeTime;
	}
	public void setBeforeDisabeTime(Date beforeDisabeTime) {
		this.beforeDisabeTime = beforeDisabeTime;
	}*/
	
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
	public Date getLastDisableTime() {
		return lastDisableTime;
	}
	public void setLastDisableTime(Date lastDisableTime) {
		this.lastDisableTime = lastDisableTime;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	
}
