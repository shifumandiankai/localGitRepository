package com.wadejerry.scms.modules.dvm.dto;

import java.util.Date;

/**
* 派车申请单DTO 
* @ClassName: ApplayDto
* @Description: TODO
* @author zhanying
* @date 2017年1月6日 上午9:32:52
*
 */
public class ApplayDto {
	
	private String orderNum;	//申请单号
	private Integer personId;	//申请人ID
	private String personCode; //申请人编号
	private String personName; //申请人名称
	private Integer deptId; //部门编号
	private String deptCode; //部门编号
	private String deptName; //部门名称
	private Integer carId;//车辆ID
	private String carNumber; //车牌
	private Date useCarTime; 	//用车时间
	private float useCarTimeLen; //用车时长
	private Date applayTime;	//申请时间
	private int status ;	//申请单状态
	private String statusDes ; 	//状态说明
	private String note ; 	//备注
	
	
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public String getPersonCode() {
		return personCode;
	}
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public Date getUseCarTime() {
		return useCarTime;
	}
	public void setUseCarTime(Date useCarTime) {
		this.useCarTime = useCarTime;
	}
	public float getUseCarTimeLen() {
		return useCarTimeLen;
	}
	public void setUseCarTimeLen(float useCarTimeLen) {
		this.useCarTimeLen = useCarTimeLen;
	}
	public Date getApplayTime() {
		return applayTime;
	}
	public void setApplayTime(Date applayTime) {
		this.applayTime = applayTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStatusDes() {
		return statusDes;
	}
	public void setStatusDes(String statusDes) {
		this.statusDes = statusDes;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	
}
