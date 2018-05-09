package com.wadejerry.scms.modules.pfm.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.frame.entity.ReportPageParamsDto;
import com.wadejerry.scms.modules.pfm.dto.CzReportDto;
import com.wadejerry.scms.modules.pfm.dto.PfmChargeRecordDto;
import com.wadejerry.scms.modules.pfm.model.PfmChargeRecord;

public interface PfmChargeRecordReportService {

	List<PfmChargeRecord> selectAllListByPage(ReportPageParamsDto paramsDto);

	int selectRecordTotal(ReportPageParamsDto paramsDto);

	List<PfmChargeRecord> selectAllByCharge(PfmChargeRecord chargeRecord);

	int selectRecordTotal(Date startTime, Date endTime, String carNumber, Integer parkId, List<Integer> listentranceId, Integer carriagewayId, List<Integer> listtype, String hasPermission, String size);

	List<PfmChargeRecord> selectAllListByPage(Date startTime, Date endTime, String carNumber, int inentranceid,
			int outentranceid);

	List<Map<String, Object>> exportcharge(Date startTime, Date endTime, String carNumber, List<Integer> listTypeId, List<Integer> listentranceId, String size, String order);

	List<PfmChargeRecordDto> selectChargeRecordTotal(Date startTime, Date endTime, String carNumber, List<Integer> listcarType,
			String size, List<Integer> listtype);

	Date selectInserttime(Date startTime, Date endTime, String carNumber, List<Integer> listcarType, String size,
			List<Integer> listtype);

	List<PfmChargeRecordDto> selectChargeDayListByPage(Date startTime, Date endTime, String carNumber, List<Integer> listcarType,
			String size, int length, int start, List<Integer> listtype, String order);

	List<PfmChargeRecordDto> selectMonthRecordTotal(Integer year, Integer month, String carNumber, List<Integer> listcarType,
			String size, Date startTime, Date endTime, Integer nowMonth, Integer endmonth, List<Integer> listtype);

	List<PfmChargeRecordDto> selectMonthListByPage(Integer year, Integer month, String carNumber, List<Integer> listcarType,
			String size, int length, int start, Date startTime, Date endTime, Integer nowMonth, Integer endmonth,
			List<Integer> listtype, String order);

	/*List<PfmChargeRecordDto> selectYearRecordTotal(Integer startyear, String carNumber, List<Integer> listcarType, String size,
			Date startTime, Date endTime, Integer endyear, List<Integer> listtype, Integer nowYear);

	List<PfmChargeRecordDto> selectYearListByPage(Integer startyear, Integer endyear, String carNumber, List<Integer> typelist,
			String size, int length, int start, Date startTime, Date endTime, Integer nowYear,
			List<Integer> listcarType, String order);*/

	List<Map<String, Object>> exportChargeDay(Date startTime, Date endTime, List<Integer> list, String size,
			List<Integer> listtype, String carNumber, String order);

	List<Map<String, Object>> exportChargeMonth(Date startTime, Date endTime, List<Integer> list, String size,
			List<Integer> listtype, String carNumber, Integer nowMonth, Integer year, String order);

	List<Map<String, Object>> exportChargeYear(Integer startyear, String carNumber, List<Integer> list, String size,
			Date startTime, Date endTime, Integer endyear, List<Integer> listtype, Integer nowYear, String order);

	List<PfmChargeRecordDto> selectYearRecordTotal(Integer startyear, String carNumber, List<Integer> listcarType,
			String size, Date startTime, Date endTime, Integer endyear, List<Integer> listtype, Integer nowYear);

	List<PfmChargeRecordDto> selectYearListByPage(Integer startyear, Integer endyear, String carNumber,
			List<Integer> listcarType, String size, int length, int start, Date startTime, Date endTime,
			Integer nowYear, List<Integer> listtype, String order);


}
