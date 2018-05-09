package com.wadejerry.scms.modules.pfm.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.wadejerry.scms.webservice.server.support.CommonPageRequest;
import com.wadejerry.scms.webservice.server.support.pfm.dto.ChargeRecordDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.DeviceScreenDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.RecordPageRequest;
import com.wadejerry.scms.webservice.server.support.pfm.dto.SpecialCarNumDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.TaskMessageDto;

public interface WebServiceMapper {
	
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.BoothDto> selectAllBoothDto(@Param("companyId")int companyId,@Param("entranceId")int[] entranceId);
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.BoothDto> selectAllBooth(@Param("boothids")List<Integer> boothids);

	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.LPRDto> selectAllLPRDto(int companyId);
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.CarInfoDto> selectAllCarInfoDto(@Param("companyId")int companyId,@Param("updateTime")Date updateTime,@Param("pageParam")CommonPageRequest page,@Param("parkId")int[] parkId);
	public int selectAllCarInfoDtoCount(@Param("companyId")int companyId,@Param("updateTime")Date updateTime,@Param("pageParam")CommonPageRequest page,@Param("parkId")int[] parkId);
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.SubsidiaryCarNumDto> selectSubsiByCarid(@Param("carId")Set carId);
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.CarTypeDto> selectAllCarTypeDto(@Param("companyId")int companyId,@Param("parkId")int[] parkid);
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.ChargeRuleDto> selectAllChargeRuleDto(@Param("companyId")int commpanyId,@Param("parkId")int[] parkid);
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.AnshiDto> selectAllAnshiDto(@Param("companyId")int commpanyId,@Param("parkId")int[] parkid);
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.BaoqiDto> selectAllBaoqiDto(@Param("companyId")int commpanyId,@Param("parkId")int[] parkid);
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.DaynightDto> selectAllDaynightDto(@Param("companyId")int commpanyId,@Param("parkId")int[] parkid);
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.JianmianReleaseDto> selectAllJianmianReleaseDto(@Param("companyId")int commpanyId,@Param("parkId")int[] parkid);
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.PeriodDto> selectAllPeriodDto(@Param("companyId")int commpanyId,@Param("parkId")int[] parkid);
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.PeriodTimeDto> selectAllPeriodTimeDto(@Param("companyId")int commpanyId,@Param("parkId")int[] parkid);
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.PertimeDto> selectAllPertimeDto(@Param("companyId")int commpanyId,@Param("parkId")int[] parkid);
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.TimeChargeDto> selectAllTimeChargeDto(@Param("companyId")int commpanyId,@Param("parkId")int[] parkid);
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.TimeChargeTimeDto> selectAllTimeChargeTimeDto(@Param("companyId")int commpanyId,@Param("parkId")int[] parkid);
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.UnitPeriodDto> selectAllUnitPeriodDto(@Param("companyId")int commpanyId,@Param("parkId")int[] parkid);
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.UnitPeriodTimeDto> selectAllUnitPeriodTimeDto(@Param("companyId")int commpanyId,@Param("parkId")int[] parkid);
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.CarTypeRelDto> selectAllCarTypeRelDto(@Param("companyId")int companyId,@Param("updateTime")Date updateTime,@Param("pageParam")CommonPageRequest page,@Param("parkId")int[] parkid);
	public int selectAllCarTypeRelDtoCount(@Param("companyId")int companyId,@Param("updateTime")Date updateTime,@Param("pageParam")CommonPageRequest page,@Param("parkId")int[] parkid);
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.AccountDto> selectAllAccountDto(@Param("companyId") int companyID,@Param("updateTime")Date updateTime,@Param("pageParam")CommonPageRequest page,@Param("parkId")int[] parkid);
	public int selectAllAccountDtoCount(@Param("companyId")int id,@Param("updateTime")Date updateTime,@Param("pageParam")CommonPageRequest page,@Param("parkId")int[] parkid);
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.PfmTimeDto> selectAllPfmTimeDto(int commpanyId);
	public int insertChargeRecord(@Param("companyId")int companyId,@Param("chargeRecord")ChargeRecordDto record);
	//public int insertInRecord(@Param("companyId")int companyId,@Param("inRecord")InRecordDto record);
	//public int insertOutRecord(@Param("companyId")int companyId,@Param("outRecord")RecordDto record);
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.ParkDto> selectAllParkDto(@Param("companyId")int companyId,@Param("parkId")int parkId);//获取当前车场和下级车场
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.EntranceDto> selectAllEntranceDto(@Param("companyId")int companyId,@Param("parkId")int[] parkId);
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.CarriagewayDto> selectAllCarriagewayDto(@Param("companyId")int companyId,@Param("entranceId")int[] parkId);
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.CarriagewayDeviceRelDto> selectAllCarriagewayDeviceRelDto(@Param("parkId")int[] parkid);
	public com.wadejerry.scms.webservice.server.support.pfm.dto.PfmServerDto selectPfmServerByBoothId(@Param("companyId")int companyId,@Param("boothid")int boothId);
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.EntranceDto> selectAllEntranceDto2(@Param("companyId")int companyId,@Param("churukouids")List<Integer> churukou);
	public int selectRecordListCount(@Param("startTime")Date start,@Param("endTime")Date endTime,@Param("dir")Integer dir,@Param("entranceIdArr")List<Integer> entranceidarr,@Param("churukouids")List<Integer> entranceauthid,@Param("flag")int flag,@Param("carNum")String carNum,@Param("reqPage")int reqpage,@Param("pageItems")int pageItem);//查询车辆进出记录
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.RecordDto> selectRecordList(@Param("startTime")Date start,@Param("endTime")Date endTime,@Param("dir")Integer dir,@Param("entranceIdArr")List<Integer> entranceidarr,@Param("churukouids")List<Integer> entranceauthid,@Param("flag")int flag,@Param("carNum")String carNum,@Param("reqPage")int reqpage,@Param("pageItems")int pageItem);//查询车辆进出记录
	public int selectChargeRecordListCount(@Param("startTime")Date start,@Param("endTime")Date endTime,@Param("dir")Integer dir,@Param("entranceIdArr")List<Integer> entranceidarr,@Param("flag")int flag,@Param("carNum")String carNum,@Param("reqPage")int reqpage,@Param("pageItems")int pageItem);//查询车辆收费记录
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.ChargeRecordDto> selectChargeRecordList(@Param("startTime")Date start,@Param("endTime")Date endTime,@Param("dir")Integer dir,@Param("entranceIdArr")List<Integer> entranceidarr,@Param("flag")int flag,@Param("carNum")String carNum,@Param("reqPage")int reqpage,@Param("pageItems")int pageItem);//查询车辆收费记录
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.CarriagewayDto> selectCarriageWaydtoByentranceid(List entranceid);
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.CarriagewayDeviceRelDto> selectCarriageWaydevicereldtoBycarriagewayid(List carriagewayid);
	public List<com.wadejerry.scms.webservice.server.support.pfm.dto.LPRDto> selectLPRdtoByDEVICEID(List deviceid);
	public int selectCountFromPfmchargeRecordByCarNumAndinsertTime(@Param("carNum")String carNum,@Param("time")Date time);
	public int selectCountFromPfmRecordByCarNumAndinsertTime(@Param("carNum")String carNum,@Param("time")Date time);
	public List<DeviceScreenDto> selectAllDeviceScreen(int bimcompany);
	public List<TaskMessageDto> selectAllTaskMessage(int companyid);
	public List<SpecialCarNumDto> selectAllSpecial(@Param("companyId")int companyid,@Param("updateTime")Date updateTime);
	public List<String> selectAllSubsidiaryCarNum(int carId);

}



