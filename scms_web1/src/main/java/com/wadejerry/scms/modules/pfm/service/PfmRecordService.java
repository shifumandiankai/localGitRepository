package com.wadejerry.scms.modules.pfm.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.frame.entity.ReportPageParamsDto;
import com.wadejerry.scms.modules.pfm.dto.PfmRecordDto;
import com.wadejerry.scms.modules.pfm.model.PfmRecord;

public interface PfmRecordService {

	int selectRecordTotal(PageParamsDto paramsDto);

	List<PfmRecordDto> selectAllListByPage(ReportPageParamsDto paramsDto);

	int selectabNormalRecordTotal(PageParamsDto paramsDto);

	List<PfmRecordDto> selectabNormalListByPage(ReportPageParamsDto paramsDto);

	int selectRecordTotal(Date startTime, Date endTime, String carNumber, Integer onlyentrance, Integer direction, Integer parkId, List<Integer> listentranceId, Integer carriagewayId, List<Integer> listtypeId, String hasPermission, String size);

	int selectabNormalRecordTotal(Date startTime, Date endTime, Integer onlyentrance, Integer direction, Integer parkId, List<Integer> listentranceId, Integer carriagewayId, List<Integer> listtypeId, String hasPermission, String size);

	

	List<Map<String, Object>> exportabnormal(List<Integer> listTypeId, String size);

	/*int selectParkReportTotal(Date startTime, Date endTime, String carNumber, Integer onlyentrance, Integer carType, String parkId, List<Integer> listtypeId, String hasPermission, String size);*/

	List<PfmRecordDto> selectAllParkReportListByPage(ReportPageParamsDto paramsDto);

	List<Map<String, Object>> exportParkReport(Date startTime, Date endTime, String carNumber, Integer carType, List<Integer> listTypeId, List<Integer> listentranceId, String size, String order);

	int selectParkReportTotal(Date startTime, Date endTime, String carNumber, Integer parkId, List<Integer> listentranceId,
			Integer carriagewayId, Integer carType, List<Integer> listtypeId, String hasPermission, String size);

	List<Map<String, Object>> exportNormalCharge(Date startTime, Date endTime, String carNumber, Integer direction,
			List<Integer> listentranceId, List<Integer> listTypeId, String size, String order);

	List<Map<String, Object>> exportAbnormalCharge(Date startTime, Date endTime, Integer direction,
			List<Integer> listentranceId, List<Integer> listTypeId, String size, String order);

}
