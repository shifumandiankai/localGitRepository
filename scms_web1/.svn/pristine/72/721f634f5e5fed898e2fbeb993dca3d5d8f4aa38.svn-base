package com.wadejerry.scms.modules.pfm.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wadejerry.scms.frame.entity.ReportPageParamsDto;
import com.wadejerry.scms.modules.pfm.dto.CzReportDto;

public interface pfmCzReportService {

	int selectRecordTotal(Date startTime, Date endTime, String carNumber, List<Integer> listtypeId, String size);

	

	List<CzReportDto> selectAllListByPage(Date startTime, Date endTime, String carNumber, List<Integer> listtypeId,
			String size, int length, int start);



	Date selectCarTypeName(Integer integer);



	List selectDoubleRecordTotal(Date startTime, Date endTime, String carNumber, List<Integer> listcarType, String size, List listtype);



	List<CzReportDto> selectDoubleListByPage(Date startTime, Date endTime, String carNumber, List<Integer> listcarType, String size,
			int length, int start, List listtype, String order);



	List<String> selectByTypeList();



	Date selectInserttime(Date startTime, Date endTime, String carNumber, Integer integer, String size, List<Integer> listcarType);



	List<Map<String, Object>> exportDay(Date startTime, Date endTime, List<Integer> list, String size, List<Integer> listtype, String carNumber, String order);



	List<CzReportDto> selectMonthRecordTotal(Integer year, Integer month, String carNumber, List<Integer> listcarType, String size, Date startTime, Date endTime, Integer nowMonth, Integer endmonth, List<Integer> listtype);



	List<CzReportDto> selectMonthListByPage(Integer year, Integer month, String carNumber, List<Integer> listcarType, String size,
			int length, int start, Date startTime, Date endTime, Integer nowMonth, Integer endmonth, List<Integer> listtype, String order);



	List<CzReportDto> selectMonthInserttime(Integer year, Integer month, String carNumber, Integer typeId, String size,
			Date startTime, Date endTime, Integer nowMonth);



	List<CzReportDto> selectYearRecordTotal(Integer year, String carNumber, List<Integer> listcarType, String size, Date startTime,
			Date endTime, Integer endyear, List<Integer> listtype, Integer nowYear);



	List<CzReportDto> selectYearListByPage(Integer startyear, Integer endyear, String carNumber, List<Integer> listcarType,
			String size, int length, int start, Date startTime, Date endTime, Integer nowYear,
			List<Integer> listtype, String order);




	List<Map<String, Object>> exportMonth(Date startTime, Date endTime, List<Integer> carTypelist, String size, List<Integer> listtype,
			String carNumber, Integer nowMonth, Integer year, String order);



	List<Map<String, Object>> exportYear(Integer startyear, String carNumber, List<Integer> carTypelist, String size,
			Date startTime, Date endTime, Integer endyear, List<Integer> listtype, Integer nowYear, String order);



	//List<Map<String, Object>> exportMonth(Date startTime, Date endTime, List<Integer> listTypeId, String size);

}
