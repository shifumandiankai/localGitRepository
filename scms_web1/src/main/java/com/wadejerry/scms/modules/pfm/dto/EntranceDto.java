package com.wadejerry.scms.modules.pfm.dto;

import java.util.Date;
import java.util.List;

public class EntranceDto {
private Integer pfmEntranceId;//出入口编号(1~4)
private Integer bimCompanyId;
private String entranceName;//出入口名称
private Integer etenabled;//出入口启用
private Integer timeId;//时段
private String carriagewayName;//车道名称
private Integer awayenabled;//车道启用
private Integer modle;//车卡模式
private Integer carriagewayType;//车道类型
private Integer inOut;//旅放模式
private Integer custom3;//军警车辆
private Integer custom1;//车道编号(1~4)
//private Integer deviceId;//设备编号
private Integer carriagewayId;//车道Id
private Integer autoReleaseFixed;//固定车免费自动放行
private Integer autoReleaseTemp;//临时车免费自动放行
private Integer releaseFixed;//入口固定车放行
private Integer releaseTemp;//入口临时车放行
private Integer parkId;//parkId
private Date updateTime;
private Integer entranceId;//出入口编号
private String selectId;//车道id
private String parentId;//出入口id
private Date updateCarriawayTime;
private List<String> deviceId;//设备编号
private Integer timeInterval;//出入口间隔
private Integer carriageTimeInterval;//车道间隔
private Integer autoCharge;//储值车自动放行
/*private String carriagewayNamedto;
private Integer carriagewayTypedto;
private Integer modledto;*/
private Integer htimeId;
private String hentranceName;
private String hcarriagewayName;
private Integer hcarriagewayType;
private Integer hinOut;
private Integer hreleaseFixed;
private Integer hreleaseTemp;
private Integer hautoReleaseFixed;
private Integer hautoReleaseTemp;
private Integer htimeInterval;
private Integer hcarriageTimeInterval;
private Integer hautoCharge;
private Integer hcustom3;
public String getEntranceName() {
	return entranceName;
}
public void setEntranceName(String entranceName) {
	this.entranceName = entranceName;
}
public Integer getEtenabled() {
	return etenabled;
}
public void setEtenabled(Integer etenabled) {
	this.etenabled = etenabled;
}
public Integer getTimeId() {
	return timeId;
}
public void setTimeId(Integer timeId) {
	this.timeId = timeId;
}
public String getCarriagewayName() {
	return carriagewayName;
}
public void setCarriagewayName(String carriagewayName) {
	this.carriagewayName = carriagewayName;
}

public Integer getAwayenabled() {
	return awayenabled;
}
public void setAwayenabled(Integer awayenabled) {
	this.awayenabled = awayenabled;
}
public Integer getModle() {
	return modle;
}
public void setModle(Integer modle) {
	this.modle = modle;
}
public Integer getCarriagewayType() {
	return carriagewayType;
}
public void setCarriagewayType(Integer carriagewayType) {
	this.carriagewayType = carriagewayType;
}
public Integer getInOut() {
	return inOut;
}
public void setInOut(Integer inOut) {
	this.inOut = inOut;
}
public Integer getCustom1() {
	return custom1;
}
public void setCustom1(Integer custom1) {
	this.custom1 = custom1;
}
/*public Integer getDeviceId() {
	return deviceId;
}
public void setDeviceId(Integer deviceId) {
	this.deviceId = deviceId;
}*/
public Integer getCarriagewayId() {
	return carriagewayId;
}
public void setCarriagewayId(Integer carriagewayId) {
	this.carriagewayId = carriagewayId;
}
public Integer getAutoReleaseFixed() {
	return autoReleaseFixed;
}
public void setAutoReleaseFixed(Integer autoReleaseFixed) {
	this.autoReleaseFixed = autoReleaseFixed;
}
public Integer getAutoReleaseTemp() {
	return autoReleaseTemp;
}
public void setAutoReleaseTemp(Integer autoReleaseTemp) {
	this.autoReleaseTemp = autoReleaseTemp;
}
public Integer getReleaseFixed() {
	return releaseFixed;
}
public void setReleaseFixed(Integer releaseFixed) {
	this.releaseFixed = releaseFixed;
}
public Integer getReleaseTemp() {
	return releaseTemp;
}
public void setReleaseTemp(Integer releaseTemp) {
	this.releaseTemp = releaseTemp;
}
public Integer getPfmEntranceId() {
	return pfmEntranceId;
}
public void setPfmEntranceId(Integer pfmEntranceId) {
	this.pfmEntranceId = pfmEntranceId;
}
public Integer getParkId() {
	return parkId;
}
public void setParkId(Integer parkId) {
	this.parkId = parkId;
}
public Date getUpdateTime() {
	return updateTime;
}
public void setUpdateTime(Date updateTime) {
	this.updateTime = updateTime;
}
public Integer getEntranceId() {
	return entranceId;
}
public void setEntranceId(Integer entranceId) {
	this.entranceId = entranceId;
}
/*public String getCarriagewayNamedto() {
	return carriagewayNamedto;
}
public void setCarriagewayNamedto(String carriagewayNamedto) {
	this.carriagewayNamedto = carriagewayNamedto;
}
public Integer getCarriagewayTypedto() {
	return carriagewayTypedto;
}
public void setCarriagewayTypedto(Integer carriagewayTypedto) {
	this.carriagewayTypedto = carriagewayTypedto;
}
public Integer getModledto() {
	return modledto;
}
public void setModledto(Integer modledto) {
	this.modledto = modledto;
}
*/
public String getSelectId() {
	return selectId;
}
public void setSelectId(String selectId) {
	this.selectId = selectId;
}
public String getParentId() {
	return parentId;
}
public void setParentId(String parentId) {
	this.parentId = parentId;
}
public Date getUpdateCarriawayTime() {
	return updateCarriawayTime;
}
public void setUpdateCarriawayTime(Date updateCarriawayTime) {
	this.updateCarriawayTime = updateCarriawayTime;
}
public Integer getBimCompanyId() {
	return bimCompanyId;
}
public void setBimCompanyId(Integer bimCompanyId) {
	this.bimCompanyId = bimCompanyId;
}
public List<String> getDeviceId() {
	return deviceId;
}
public void setDeviceId(List<String> deviceId) {
	this.deviceId = deviceId;
}
public Integer getHtimeId() {
	return htimeId;
}
public void setHtimeId(Integer htimeId) {
	this.htimeId = htimeId;
}
public String getHentranceName() {
	return hentranceName;
}
public void setHentranceName(String hentranceName) {
	this.hentranceName = hentranceName;
}
public String getHcarriagewayName() {
	return hcarriagewayName;
}
public void setHcarriagewayName(String hcarriagewayName) {
	this.hcarriagewayName = hcarriagewayName;
}
public Integer getHcarriagewayType() {
	return hcarriagewayType;
}
public void setHcarriagewayType(Integer hcarriagewayType) {
	this.hcarriagewayType = hcarriagewayType;
}
public Integer getHinOut() {
	return hinOut;
}
public void setHinOut(Integer hinOut) {
	this.hinOut = hinOut;
}
public Integer getHreleaseFixed() {
	return hreleaseFixed;
}
public void setHreleaseFixed(Integer hreleaseFixed) {
	this.hreleaseFixed = hreleaseFixed;
}
public Integer getHreleaseTemp() {
	return hreleaseTemp;
}
public void setHreleaseTemp(Integer hreleaseTemp) {
	this.hreleaseTemp = hreleaseTemp;
}
public Integer getHautoReleaseFixed() {
	return hautoReleaseFixed;
}
public void setHautoReleaseFixed(Integer hautoReleaseFixed) {
	this.hautoReleaseFixed = hautoReleaseFixed;
}
public Integer getHautoReleaseTemp() {
	return hautoReleaseTemp;
}
public void setHautoReleaseTemp(Integer hautoReleaseTemp) {
	this.hautoReleaseTemp = hautoReleaseTemp;
}
public Integer getHtimeInterval() {
	return htimeInterval;
}
public void setHtimeInterval(Integer htimeInterval) {
	this.htimeInterval = htimeInterval;
}
public Integer getHcarriageTimeInterval() {
	return hcarriageTimeInterval;
}
public void setHcarriageTimeInterval(Integer hcarriageTimeInterval) {
	this.hcarriageTimeInterval = hcarriageTimeInterval;
}
public Integer getHautoCharge() {
	return hautoCharge;
}
public void setHautoCharge(Integer hautoCharge) {
	this.hautoCharge = hautoCharge;
}
public Integer getTimeInterval() {
	return timeInterval;
}
public void setTimeInterval(Integer timeInterval) {
	this.timeInterval = timeInterval;
}
public Integer getCarriageTimeInterval() {
	return carriageTimeInterval;
}
public void setCarriageTimeInterval(Integer carriageTimeInterval) {
	this.carriageTimeInterval = carriageTimeInterval;
}
public Integer getAutoCharge() {
	return autoCharge;
}
public void setAutoCharge(Integer autoCharge) {
	this.autoCharge = autoCharge;
}
public Integer getCustom3() {
	return custom3;
}
public void setCustom3(Integer custom3) {
	this.custom3 = custom3;
}
public Integer getHcustom3() {
	return hcustom3;
}
public void setHcustom3(Integer hcustom3) {
	this.hcustom3 = hcustom3;
}


}
