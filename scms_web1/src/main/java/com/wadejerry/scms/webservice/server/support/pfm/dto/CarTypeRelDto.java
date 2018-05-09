package com.wadejerry.scms.webservice.server.support.pfm.dto;

import java.util.Date;

/**
* 车辆月类型关联表DTO 
* @ClassName: CarTypeRelDto
* @Description: TODO
* @author zhanying
* @date 2017年2月9日 上午10:46:20
*
 */
public class CarTypeRelDto {
	private Integer carTypeRelId;

    private Integer carInfoId;

    private Integer carTypeId;

    private long insertTime;

    private long updateTime;

    private String userName;
    
	public Integer getCarTypeRelId() {
		return carTypeRelId;
	}

	public void setCarTypeRelId(Integer carTypeRelId) {
		this.carTypeRelId = carTypeRelId;
	}

	public Integer getCarInfoId() {
		return carInfoId;
	}

	public void setCarInfoId(Integer carInfoId) {
		this.carInfoId = carInfoId;
	}

	public Integer getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(Integer carTypeId) {
		this.carTypeId = carTypeId;
	}

	public long getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime.getTime();
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime.getTime();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}




}
