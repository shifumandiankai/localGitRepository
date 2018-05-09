package com.wadejerry.scms.modules.pfm.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SpecialNumDto {

	 private Integer pfmSpecialCarId;

	    private Integer bimCompanyId;

	    private String note;

	    private String carNumber;

	    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	    private Date insertTime;
	    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	    private Date updateTime;

	    private String userName;

	    private Integer isDel;

	    public Integer getPfmSpecialCarId() {
	        return pfmSpecialCarId;
	    }

	    public void setPfmSpecialCarId(Integer pfmSpecialCarId) {
	        this.pfmSpecialCarId = pfmSpecialCarId;
	    }

	    public Integer getBimCompanyId() {
	        return bimCompanyId;
	    }

	    public void setBimCompanyId(Integer bimCompanyId) {
	        this.bimCompanyId =bimCompanyId;
	    }

	    public String getNote() {
	        return note;
	    }

	    public void setNote(String note) {
	        this.note = note == null ? null : note.trim();
	    }

	    public String getCarNumber() {
	        return carNumber;
	    }

	    public void setCarNumber(String carNumber) {
	        this.carNumber = carNumber == null ? null : carNumber.trim();
	    }

	    public Date getInsertTime() {
	        return insertTime;
	    }

	    public void setInsertTime(Date insertTime) {
	        this.insertTime = insertTime;
	    }

	    public Date getUpdateTime() {
	        return updateTime;
	    }

	    public void setUpdateTime(Date updateTime) {
	        this.updateTime = updateTime;
	    }

	    public String getUserName() {
	        return userName;
	    }

	    public void setUserName(String userName) {
	        this.userName = userName == null ? null : userName.trim();
	    }

	    public Integer getIsDel() {
	        return isDel;
	    }

	    public void setIsDel(Integer isDel) {
	        this.isDel = isDel;
	    }
}
