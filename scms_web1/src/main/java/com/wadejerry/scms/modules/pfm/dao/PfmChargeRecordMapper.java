package com.wadejerry.scms.modules.pfm.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.frame.entity.ReportPageParamsDto;
import com.wadejerry.scms.modules.pfm.dto.CzReportDto;
import com.wadejerry.scms.modules.pfm.dto.PfmChargeRecordDto;
import com.wadejerry.scms.modules.pfm.model.PfmChargeRecord;

public interface PfmChargeRecordMapper {
    int deleteByPrimaryKey(Integer chargeRecordId);

    int insert(PfmChargeRecord record);

    int insertSelective(PfmChargeRecord record);

    PfmChargeRecord selectByPrimaryKey(Integer chargeRecordId);

    int updateByPrimaryKeySelective(PfmChargeRecord record);

    int updateByPrimaryKey(PfmChargeRecord record);

	List<PfmChargeRecord> selectAllListByPage(ReportPageParamsDto paramsDto);

	int selectRecordTotal(ReportPageParamsDto paramsDto);

	List<PfmChargeRecord> selectAllByCharge(PfmChargeRecord chargeRecord);

	int selectRecordTotal(@Param("startTime")Date startTime, @Param("endTime")Date endTime,@Param("carNumber")String carNumber, @Param("parkId")Integer parkId, @Param("entranceId")List<Integer> entranceId, @Param("carriagewayId")Integer awayId, @Param("listtype")List<Integer> listtype, @Param("hasPermission")String hasPermission,@Param("size")String size);

	List<PfmChargeRecord> selectAllListByPage(@Param("startTime")Date startTime, @Param("endTime")Date endTime,@Param("carNumber") String carNumber, @Param("inentranceid")int inentranceid,
			@Param("outentranceid")int outentranceid);

	List<Map<String, Object>> exportcharge(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("carNumber")String carNumber, @Param("listtype")List<Integer> list,  @Param("listentranceId")List<Integer> listentranceId, @Param("size")String size, @Param("order")String order);

	List<PfmChargeRecordDto> selectChargeRecordTotal(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("carNumber")String carNumber, @Param("listcarType")List<Integer> listcarType, @Param("size")String size, @Param("listtype") List typeid);

	Date selectInserttime(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("carNumber")String carNumber, @Param("listcarType")List<Integer> listcarType, @Param("size")String size, @Param("listtype")List<Integer> listtype);

	List<PfmChargeRecordDto> selectChargeDayListByPage(@Param("startTime")Date startTime,  @Param("endTime")Date endTime, @Param("carNumber")String carNumber, @Param("listcarType")List<Integer> listcarType,@Param("size")String size, @Param("length")int length,  @Param("start")int start,@Param("listtype")List listtype, @Param("order")String order);

	List<PfmChargeRecordDto> selectMonthRecordTotal(@Param("year")Integer year, @Param("month")Integer month, @Param("carNumber")String carNumber, @Param("listcarType")List<Integer> listcarType, @Param("size")String size,@Param("startTime") Date startTime,@Param("endTime") Date endTime,@Param("nowMonth") Integer nowMonth, @Param("endMonth")Integer endmonth, @Param("listtype")List<Integer> listtype);

	List<PfmChargeRecordDto> selectMonthListByPage(@Param("year")Integer year, @Param("month")Integer month, @Param("carNumber")String carNumber, @Param("listcarType")List<Integer> listcarType,@Param("size") String size,
			@Param("length")int length, @Param("start")int start, @Param("startTime")Date startTime, @Param("endTime")Date endTime,  @Param("nowMonth")Integer nowMonth, @Param("endMonth")Integer endMonth, @Param("listtype")List<Integer> listtype, @Param("order")String order);

	/*List<PfmChargeRecordDto> selectYearRecordTotal(@Param("startyear")Integer year, @Param("carNumber")String carNumber,  @Param("listcarType")List<Integer> listcarType,  @Param("size")String size, @Param("startTime")Date startTime,
			@Param("endTime")Date endTime, @Param("endyear")Integer endyear,@Param("listtype")List<Integer> listtype, @Param("nowyear")Integer nowyear);

	List<PfmChargeRecordDto> selectYearListByPage(@Param("startyear")Integer startyear, @Param("endyear")Integer endyear, @Param("carNumber")String carNumber,@Param("listtype") List<Integer> listtype,@Param("size")String size, @Param("length")int length,@Param("start") int start, @Param("startTime")Date startTime,@Param("endTime") Date endTime, @Param("nowyear")Integer nowYear,@Param("listcarType")List<Integer> listcarType, @Param("order")String order);*/

	List<Map<String, Object>> exportChargeDay(@Param("start")Date startTime, @Param("endTime")Date endTime, @Param("listcarType")List<Integer> listcarType,  @Param("size")String size,@Param("carNumber")String carNumber, @Param("listtype")List<Integer> listtype, @Param("order")String order);

	List<Map<String, Object>> exportChargeMonth(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("listcarType")List<Integer> listcarType, @Param("size")String size,
			@Param("listtype")List<Integer> listtype, @Param("carNumber")String carNumber,@Param("nowMonth") Integer nowMonth, @Param("year")Integer year, @Param("order")String order);

	List<Map<String, Object>> exportChargeYear(@Param("startyear")Integer startyear, @Param("carNumber")String carNumber, @Param("listcarType")List<Integer> listcarType, @Param("size")String size,@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("endyear")Integer endyear, @Param("listtype")List<Integer> listtype, @Param("nowyear")Integer nowYear, @Param("order")String order);

	List<PfmChargeRecordDto> selectYearRecordTotal(@Param("startyear")Integer startyear, @Param("carNumber")String carNumber, @Param("listcarType")List<Integer> listcarType,
			@Param("size")String size, @Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("endyear")Date endyear, @Param("listtype")List<Integer> listtype, @Param("nowyear")Integer nowYear);

	List<PfmChargeRecordDto> selectYearListByPage(@Param("startyear")Integer startyear,@Param("endyear") Integer endyear, @Param("carNumber")String carNumber,
			@Param("listcarType")List<Integer> listcarType, @Param("size")String size, @Param("length")int length, @Param("start")int start, @Param("startTime")Date startTime, @Param("endTime")Date endTime,
			@Param("nowyear")Integer nowYear, @Param("listtype")List<Integer> listtype, @Param("order")String order);

	

	
}