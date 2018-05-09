package com.wadejerry.scms.modules.pfm.model;

import java.util.Date;


public class PfmCarInfo {
    private Integer carInfoId;
    
   

    private Integer personId;

    private Integer bimCompanyId;



    private String carNumber;

    private String personName;

    private String cardId;

    private String certId;
    
    

    @Override
	public String toString() {
		return "PfmCarInfo [carInfoId=" + carInfoId + ", personId=" + personId + ", bimCompanyId=" + bimCompanyId
				+  ", carNumber=" + carNumber + ", personName=" + personName
				+ ", cardId=" + cardId + ", certId=" + certId + ", addres=" + addres + ", phone=" + phone + ", sex="
				+ sex + ", photo=" + photo + ", status=" + status + ", enableTime=" + enableTime + ", disableTime="
				+ disableTime + ", insertTime=" + insertTime + ", updateTime=" + updateTime + ", note=" + note
				+ ", userName=" + userName + ", custom1=" + custom1 + ", custom2=" + custom2 + ", custom3=" + custom3
				+ ", custom4=" + custom4 + "]";
	}

	private String addres;

    private String phone;

    private Short sex;

    private String photo;

    private Integer status;

    private Date enableTime;

    private Date disableTime;

    private Date insertTime;

    private Date updateTime;

    private String note;

    private String userName;

    private Integer custom1;

    private Integer custom2;

    private String custom3;

    private String custom4;
    private Integer isDel;

    public Integer getCarInfoId() {
        return carInfoId;
    }

    public void setCarInfoId(Integer carInfoId) {
        this.carInfoId = carInfoId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getBimCompanyId() {
        return bimCompanyId;
    }

    public void setBimCompanyId(Integer bimCompanyId) {
        this.bimCompanyId = bimCompanyId;
    }



    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber == null ? null : carNumber.trim();
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId == null ? null : certId.trim();
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres == null ? null : addres.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getEnableTime() {
        return enableTime;
    }

    public void setEnableTime(Date enableTime) {
        this.enableTime = enableTime;
    }

    public Date getDisableTime() {
        return disableTime;
    }

    public void setDisableTime(Date disableTime) {
        this.disableTime = disableTime;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
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

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}



}