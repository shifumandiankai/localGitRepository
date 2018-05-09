package com.wadejerry.scms.webservice.server.support.pfm.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账户信息DTO类
* @ClassName: AccountDto
* @Description: TODO
* @author zhanying
* @date 2017年2月9日 上午11:36:53
*
 */
public class AccountDto {
	private int  bimCardAppId;
	
	private int appId;
	
	private String appCode;
	
	private String appName;
	
	private BigDecimal value1;
	
	private int carId ;
	
	private String cardId;
	
	private long disableTime;
	
	private long updateTime;
	
	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public BigDecimal getValue1() {
		return value1;
	}

	public void setValue1(BigDecimal value1) {
		this.value1 = value1;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public long getDisableTime() {
		return disableTime;
	}

	public void setDisableTime(Date disableTime) {
		this.disableTime = disableTime.getTime();
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime.getTime();
	}

	public int getBimCardAppId() {
		return bimCardAppId;
	}

	public void setBimCardAppId(int bimCardAppId) {
		this.bimCardAppId = bimCardAppId;
	}


}
