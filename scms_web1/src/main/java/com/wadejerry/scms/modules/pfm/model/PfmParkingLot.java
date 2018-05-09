package com.wadejerry.scms.modules.pfm.model;

import java.util.Date;

public class PfmParkingLot {
    private Integer pfmParkingLotId;

    private String parkingLotCode;

    private Integer bimCompanyId;

    private String parkingLotName;

    private Integer carNumber;

    private String parkingAddress;

    private String note;

    private Integer reserveCarNumber;

    private Integer inId;

    private Date insertTime;

    private Date updateTime;

    private String userName;

    public Integer getPfmParkingLotId() {
        return pfmParkingLotId;
    }

    public void setPfmParkingLotId(Integer pfmParkingLotId) {
        this.pfmParkingLotId = pfmParkingLotId;
    }

    public String getParkingLotCode() {
        return parkingLotCode;
    }

    public void setParkingLotCode(String parkingLotCode) {
        this.parkingLotCode = parkingLotCode == null ? null : parkingLotCode.trim();
    }

    public Integer getBimCompanyId() {
        return bimCompanyId;
    }

    public void setBimCompanyId(Integer bimCompanyId) {
        this.bimCompanyId = bimCompanyId;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName == null ? null : parkingLotName.trim();
    }

    public Integer getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(Integer carNumber) {
        this.carNumber = carNumber;
    }

    public String getParkingAddress() {
        return parkingAddress;
    }

    public void setParkingAddress(String parkingAddress) {
        this.parkingAddress = parkingAddress == null ? null : parkingAddress.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Integer getReserveCarNumber() {
        return reserveCarNumber;
    }

    public void setReserveCarNumber(Integer reserveCarNumber) {
        this.reserveCarNumber = reserveCarNumber;
    }

    public Integer getInId() {
        return inId;
    }

    public void setInId(Integer inId) {
        this.inId = inId;
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
}