package com.wadejerry.scms.modules.onecard.model;

import java.util.Date;

public class BimCard {
private Integer bimCardId;
private Integer bimCompanyId;
private Integer personId;
private String cardId;
private String useSystem;
private Integer cardType;
private Integer cardStatus;
private Date updateTime;
private Integer custom1;
private Integer custom2;
private String custom3;
private String custom4;
public Integer getBimCardId() {
	return bimCardId;
}
public void setBimCardId(Integer bimCardId) {
	this.bimCardId = bimCardId;
}
public Integer getBimCompanyId() {
	return bimCompanyId;
}
public void setBimCompanyId(Integer bimCompanyId) {
	this.bimCompanyId = bimCompanyId;
}
public Integer getPersonId() {
	return personId;
}
public void setPersonId(Integer personId) {
	this.personId = personId;
}

public String getCardId() {
	return cardId;
}
public void setCardId(String cardId) {
	this.cardId = cardId;
}
public String getUseSystem() {
	return useSystem;
}
public void setUseSystem(String useSystem) {
	this.useSystem = useSystem;
}
public Integer getCardType() {
	return cardType;
}
public void setCardType(Integer cardType) {
	this.cardType = cardType;
}
public Integer getCardStatus() {
	return cardStatus;
}
public void setCardStatus(Integer cardStatus) {
	this.cardStatus = cardStatus;
}
public Date getUpdateTime() {
	return updateTime;
}
public void setUpdateTime(Date updateTime) {
	this.updateTime = updateTime;
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
	this.custom3 = custom3;
}
public String getCustom4() {
	return custom4;
}
public void setCustom4(String custom4) {
	this.custom4 = custom4;
}


}
