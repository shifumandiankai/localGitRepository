package com.wadejerry.scms.modules.pfm.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wadejerry.scms.frame.entity.ReportPageParamsDto;
import com.wadejerry.scms.modules.pfm.dto.CzReportDto;

public interface pfmCzReportMapper {

	int selectRecordTotal(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("carNumber")String carNumber,  @Param("listtype")List<Integer> listtypeId, @Param("size")String size);

	//List<CzReportDto> selectDayAllListByPage(ReportPageParamsDto paramsDto);

	List<CzReportDto> selectDayAllListByPage(@Param("startTime")Date startTime,  @Param("endTime")Date endTime, @Param("carNumber")String carNumber, @Param("listtype")List<Integer> listtypeId,
			@Param("size")String size, @Param("length")int length,  @Param("start")int start);

	Date selectCarTypeName(Integer typeId);

	List selectDoubleRecordTotal(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("carNumber")String carNumber, @Param("listcarType")List<Integer> listcarType, @Param("size")String size, @Param("listtype") List listtype);

	List<CzReportDto> selectDoubleListByPage(@Param("startTime")Date startTime,  @Param("endTime")Date endTime, @Param("carNumber")String carNumber, @Param("listcarType")List<Integer> listcarType,
			@Param("size")String size, @Param("length")int length,  @Param("start")int start,@Param("listtype")List listtype, @Param("order")String order);

	List<String> selectByTypeList();

	Date selectInserttime(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("carNumber")String carNumber, @Param("listtype")Integer temp, @Param("size")String size, @Param("listcarType")List<Integer> listcarType);

	List<Map<String, Object>> exportDay( @Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("cartypelist")List<Integer> list, @Param("size")String size, @Param("carNumber")String carNumber, @Param("listtype")List<Integer> listtype, @Param("order")String order);

	List<CzReportDto> selectMonthRecordTotal(@Param("year")Integer year, @Param("month")Integer month, @Param("carNumber")String carNumber, @Param("listcarType")List<Integer> listcarType, @Param("size")String size,@Param("startTime") Date startTime,@Param("endTime") Date endTime,@Param("nowMonth") Integer nowMonth, @Param("endMonth")Integer endmonth, @Param("listtype")List<Integer> listtype);

	List<CzReportDto> selectMonthListByPage(@Param("year")Integer year, @Param("month")Integer month, @Param("carNumber")String carNumber, @Param("listcarType")List<Integer> listcarType,@Param("size") String size,
			@Param("length")int length, @Param("start")int start, @Param("startTime")Date startTime, @Param("endTime")Date endTime,  @Param("nowMonth")Integer nowMonth, @Param("endMonth")Integer endMonth, @Param("listtype")List<Integer> listtype, @Param("order")String order);

	List<CzReportDto> selectMonthInserttime(@Param("year")Integer year, @Param("month")Integer month, @Param("carNumber")String carNumber, @Param("listtype")Integer typeId, @Param("size")String size,@Param("startTime") Date startTime,@Param("endTime") Date endTime,@Param("nowMonth") Integer nowMonth);

	List<CzReportDto> selectYearRecordTotal(@Param("startyear")Integer year, @Param("carNumber")String carNumber, @Param("listcarType")List<Integer> listcarType,  @Param("size")String size, @Param("startTime")Date startTime,
			@Param("endTime")Date endTime, @Param("endyear")Integer endyear, @Param("listtype")List<Integer> listtype, @Param("nowyear")Integer nowyear);

	List<CzReportDto> selectYearListByPage(@Param("startyear")Integer startyear, @Param("endyear")Integer endyear, @Param("carNumber")String carNumber,@Param("listcarType") List<Integer> listcarType,
			@Param("size")String size, @Param("length")int length,@Param("start") int start, @Param("startTime")Date startTime,@Param("endTime") Date endTime, @Param("nowyear")Integer nowYear,
			@Param("listtype")List<Integer> listtype,@Param("order")String order);

	List<Map<String, Object>> exportMonth( @Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("cartypelist")List<Integer> carTypelist, @Param("size")String size, @Param("listtype")List<Integer> listtype, @Param("carNumber")String carNumber, @Param("nowMonth")Integer nowMonth, @Param("year")Integer year, @Param("order")String order);

	List<Map<String, Object>> exportYear(@Param("startyear")Integer startyear, @Param("carNumber")String carNumber, @Param("listcarType")List<Integer> listcarType, @Param("size")String size,
			@Param("startTime")Date startTime,@Param("endTime")  Date endTime,  @Param("endyear")Integer endyear, @Param("listtype")List<Integer> listtype, @Param("nowyear")Integer nowYear, @Param("order") String order);

}
