package com.wadejerry.scms.modules.pfm.model;

import java.util.Date;

public class PfmInRecord {
    private Integer inRecordId;

    private Integer carId;

    private Integer bimCompanyId;

    private String carNumber;

    private String cardId;

    private String personName;

    private Integer carTypeId;

    private String carTypeName;

    private Integer parkId;

    private String parkName;

    private Integer inBoothId;

    private String inBoothName;

    private Integer inDeviceId;

    private String inDeviceName;

    private String inPhoto;

    private Date inTime;

    private Integer outRecordId;

    public Integer getInRecordId() {
        return inRecordId;
    }

    public void setInRecordId(Integer inRecordId) {
        this.inRecordId = inRecordId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
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

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    public Integer getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(Integer carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getCarTypeName() {
        return carTypeName;
    }

    public void setCarTypeName(String carTypeName) {
        this.carTypeName = carTypeName == null ? null : carTypeName.trim();
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName == null ? null : parkName.trim();
    }

    public Integer getInBoothId() {
        return inBoothId;
    }

    public void setInBoothId(Integer inBoothId) {
        this.inBoothId = inBoothId;
    }

    public String getInBoothName() {
        return inBoothName;
    }

    public void setInBoothName(String inBoothName) {
        this.inBoothName = inBoothName == null ? null : inBoothName.trim();
    }

    public Integer getInDeviceId() {
        return inDeviceId;
    }

    public void setInDeviceId(Integer inDeviceId) {
        this.inDeviceId = inDeviceId;
    }

    public String getInDeviceName() {
        return inDeviceName;
    }

    public void setInDeviceName(String inDeviceName) {
        this.inDeviceName = inDeviceName == null ? null : inDeviceName.trim();
    }

    public String getInPhoto() {
        return inPhoto;
    }

    public void setInPhoto(String inPhoto) {
        this.inPhoto = inPhoto == null ? null : inPhoto.trim();
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Integer getOutRecordId() {
        return outRecordId;
    }

    public void setOutRecordId(Integer outRecordId) {
        this.outRecordId = outRecordId;
    }
}