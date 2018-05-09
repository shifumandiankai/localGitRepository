package com.wadejerry.scms.modules.pfm.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.frame.entity.ReportPageParamsDto;
import com.wadejerry.scms.modules.pfm.dao.PfmChargeRecordMapper;
import com.wadejerry.scms.modules.pfm.dto.CzReportDto;
import com.wadejerry.scms.modules.pfm.dto.PfmChargeRecordDto;
import com.wadejerry.scms.modules.pfm.model.PfmChargeRecord;
import com.wadejerry.scms.modules.pfm.service.PfmChargeRecordReportService;
@Service("chargeRecordService")
public class PfmChargeRecordRecordServiceImpl implements PfmChargeRecordReportService {
	@Autowired
	private PfmChargeRecordMapper chargeRecordMapper; 
	@Override
	public List<PfmChargeRecord> selectAllByCharge(PfmChargeRecord chargeRecord) {
		return chargeRecordMapper.selectAllByCharge(chargeRecord);
		
		
	}

	@Override
	public int selectRecordTotal(ReportPageParamsDto paramsDto) {
		
		return chargeRecordMapper.selectRecordTotal(paramsDto);
	}

	@Override
	public List<PfmChargeRecord> selectAllListByPage(ReportPageParamsDto paramsDto) {
		return chargeRecordMapper.selectAllListByPage(paramsDto);
	}

	@Override
	public int selectRecordTotal(Date startTime, Date endTime, String carNumber, Integer parkId,List<Integer> entranceId,Integer awayId,List<Integer> listtype,String hasPermission,String size) {
		
		return chargeRecordMapper.selectRecordTotal(startTime,endTime,carNumber,parkId,entranceId,awayId,listtype,hasPermission,size);
	}

	@Override
	public List<PfmChargeRecord> selectAllListByPage(Date startTime, Date endTime, String carNumber,
			int inentranceid, int outentranceid) {
		
		return chargeRecordMapper.selectAllListByPage(startTime,endTime,carNumber,inentranceid,outentranceid);
	}

	

	@Override
	public List<PfmChargeRecordDto> selectChargeRecordTotal(Date startTime, Date endTime, String carNumber, List<Integer> listcarType,
			String size, List<Integer> listtype) {
		
		return chargeRecordMapper.selectChargeRecordTotal(startTime,endTime,carNumber,listcarType,size,listtype);
	}

	@Override
	public Date selectInserttime(Date startTime, Date endTime, String carNumber, List<Integer> listcarType, String size,
			List<Integer> listtype) {
		
		return chargeRecordMapper.selectInserttime( startTime,  endTime,  carNumber,  listcarType,  size, listtype);
	}

	@Override
	public List<PfmChargeRecordDto> selectChargeDayListByPage(Date startTime, Date endTime, String carNumber,
			List<Integer> listcarType, String size, int length, int start, List<Integer> listtype,String order) {
		
		return chargeRecordMapper.selectChargeDayListByPage (startTime, endTime, carNumber, listcarType,  size, length, start,  listtype,order);
	}

	@Override
	public List<PfmChargeRecordDto> selectMonthRecordTotal(Integer year, Integer month, String carNumber,
			List<Integer> listcarType, String size, Date startTime, Date endTime, Integer nowMonth, Integer endmonth,
			List<Integer> typeId) {
		
		return chargeRecordMapper.selectMonthRecordTotal( year,  month, carNumber,listcarType,  size, startTime,endTime,  nowMonth,  endmonth,typeId);
	}

	@Override
	public List<PfmChargeRecordDto> selectMonthListByPage(Integer year, Integer month, String carNumber, List<Integer> listcarType,
			String size, int length, int start, Date startTime, Date endTime, Integer nowMonth, Integer endmonth,
			List<Integer> listType,String order) {
		
		return chargeRecordMapper.selectMonthListByPage(year,month,carNumber,listcarType,size,length,start,startTime,endTime,nowMonth,endmonth,listType,order);
	}

	/*@Override
	public List<PfmChargeRecordDto> selectYearRecordTotal(Integer startyear, String carNumber, List<Integer> listcarType,
			String size, Date startTime, Date endTime, Integer endyear, List<Integer> listtype, Integer nowYear) {
		
		return chargeRecordMapper.selectYearRecordTotal(startyear,carNumber,listcarType,size,startTime,endTime,endyear,listtype,nowYear);
	}

	@Override
	public List<PfmChargeRecordDto> selectYearListByPage(Integer startyear, Integer endyear, String carNumber,
			List<Integer> typeId, String size, int length, int start, Date startTime, Date endTime, Integer nowYear,
			List<Integer> listcarType,String order) {
		
		return chargeRecordMapper.selectYearListByPage(startyear,endyear,carNumber,typeId,size,length,start,startTime,endTime,nowYear,listcarType,order);
	}*/

	@Override
	public List<Map<String, Object>> exportChargeDay(Date startTime, Date endTime, List<Integer> list, String size,
			List<Integer> listtype, String carNumber,String order) {
		
		return chargeRecordMapper.exportChargeDay(startTime, endTime, list, carNumber, carNumber, listtype,order);
	}

	@Override
	public List<Map<String, Object>> exportChargeMonth(Date startTime, Date endTime, List<Integer> list, String size,
			List<Integer> listtype, String carNumber, Integer nowMonth, Integer year,String order) {
		
		return chargeRecordMapper.exportChargeMonth(startTime, endTime, list,  size,listtype, carNumber, nowMonth,  year,order);
	}

	@Override
	public List<Map<String, Object>> exportChargeYear(Integer startyear, String carNumber, List<Integer> list, String size,
			Date startTime, Date endTime, Integer endyear, List<Integer> listtype, Integer nowYear,String order) {
		
		return chargeRecordMapper.exportChargeYear(startyear,carNumber,list,size,startTime,endTime,endyear,listtype,nowYear,order);
	}

	@Override
	public List<Map<String, Object>> exportcharge(Date startTime, Date endTime, String carNumber,
			List<Integer> listTypeId, List<Integer> listentranceId, String size,String order) {
		
		return chargeRecordMapper.exportcharge(startTime,endTime,carNumber,listTypeId,listentranceId,size,order);
	}

	@Override
	public List<PfmChargeRecordDto> selectYearRecordTotal(Integer startyear, String carNumber,
			List<Integer> listcarType, String size, Date startTime, Date endTime, Integer endyear,
			List<Integer> listtype, Integer nowYear) {
		
		return chargeRecordMapper.selectYearRecordTotal(startyear,carNumber,listcarType,size,startTime,endTime,endTime,listtype,nowYear);
	}

	@Override
	public List<PfmChargeRecordDto> selectYearListByPage(Integer startyear, Integer endyear, String carNumber,
			List<Integer> listcarType, String size, int length, int start, Date startTime, Date endTime,
			Integer nowYear, List<Integer> listtype, String order) {
		
		return chargeRecordMapper.selectYearListByPage(startyear,endyear,carNumber,listcarType,size,length,start,startTime,endTime,nowYear,listtype,order);
	}

	


}
