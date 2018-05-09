package com.wadejerry.scms.modules.pfm.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.frame.entity.ReportPageParamsDto;
import com.wadejerry.scms.modules.pfm.dto.PfmRecordDto;
import com.wadejerry.scms.modules.pfm.model.PfmRecord;

public interface PfmRecordMapper {
    int deleteByPrimaryKey(Integer recordId);

    int insert(PfmRecord record);

    int insertSelective(PfmRecord record);

    PfmRecord selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(PfmRecord record);

    int updateByPrimaryKey(PfmRecord record);

	int selectRecordTotal(PageParamsDto paramsDto);

	List<PfmRecordDto> selectAllListByPage(ReportPageParamsDto paramsDto);

	int selectabNormalRecordTotal(PageParamsDto paramsDto);

	List<PfmRecordDto> selectabNormalListByPage(ReportPageParamsDto paramsDto);

	int selectRecordTotal(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("carNumber")String carNumber, @Param("onlyentrance")Integer onlyentrance, @Param("direction")Integer direction, @Param("parkId")Integer parkId, @Param("entranceId")List<Integer> entranceId, @Param("carriagewayId")Integer carriagewayId, @Param("listtype")List<Integer> listtype, @Param("hasPermission")String hasPermission,@Param("size")String size);

	int selectabNormalRecordTotal(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("onlyentrance")Integer onlyentrance, @Param("direction") Integer direction, @Param("parkId")Integer parkId, @Param("entranceId")List<Integer> entranceId, @Param("carriagewayId")Integer carriagewayId, @Param("listtype")List<Integer> listtype, @Param("hasPermission")String hasPermission,@Param("size")String size);

	List<Map<String, Object>> exportcharge(@Param("list")List<Integer> list, @Param("size")String size);

	List<Map<String, Object>> exportabnormal(@Param("list")List<Integer> list, @Param("size")String size);

	int selectParkReportTotal(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("carNumber")String carNumber, @Param("onlyentrance")Integer onlyentrance, @Param("carType")Integer carType,  @Param("parkId")String parkId, @Param("listtype")List<Integer> listtypeId,  @Param("hasPermission")String hasPermission, @Param("size")String size);

	List<PfmRecordDto> selectAllParkReportListByPage(ReportPageParamsDto paramsDto);

	List<Map<String, Object>> exportParkReport(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("carNumber")String carNumber, @Param("carType")Integer carType, @Param("list")List<Integer> list, @Param("entranceId")List<Integer> listentranceId, @Param("size")String size,  @Param("order")String order);

	int selectParkReportTotal(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("carNumber")String carNumber, @Param("parkId")Integer parkId, @Param("entranceId")List<Integer> entranceId, @Param("carriagewayId")Integer carriagewayId,@Param("carType")Integer carType,@Param("listtype")List<Integer> listtypeId, @Param("hasPermission")String hasPermission,@Param("size")String size);

	List<Map<String, Object>> exportNormalCharge(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("carNumber")String carNumber,  @Param("direction")Integer direction,
			@Param("entranceId")List<Integer> listentranceId, @Param("list")List<Integer> listTypeId, @Param("size")String size, @Param("order")String order);

	List<Map<String, Object>> exportAbnormalCharge(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("direction")Integer direction,
			@Param("entranceId")List<Integer> listentranceId, @Param("list")List<Integer> listTypeId, @Param("size")String size, @Param("order")String order);
}