package com.wadejerry.scms.modules.pfm.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wadejerry.scms.frame.entity.ReportPageParamsDto;
import com.wadejerry.scms.modules.pfm.dao.pfmCzReportMapper;
import com.wadejerry.scms.modules.pfm.dto.CzReportDto;
import com.wadejerry.scms.modules.pfm.service.pfmCzReportService;
@Service("pfmCzReportService")
public class pfmCzReportServiceImpl implements pfmCzReportService{
    @Autowired
    private pfmCzReportMapper czReportMapper;

	@Override
	public int selectRecordTotal(Date startTime, Date endTime, String carNumber, List<Integer> listtypeId,String size) {
		
		return czReportMapper.selectRecordTotal(startTime,endTime,carNumber,listtypeId,size);
	}

	/*@Override
	public List<CzReportDto> selectAllListByPage(ReportPageParamsDto paramsDto) {
		
		return czReportMapper.selectDayAllListByPage(paramsDto);
	}*/

	@Override
	public List<CzReportDto> selectAllListByPage(Date startTime, Date endTime, String carNumber,
			List<Integer> listtypeId, String size, int length, int start) {
	
		return czReportMapper.selectDayAllListByPage(startTime,endTime,carNumber,listtypeId,size,length,start);
	}

	@Override
	public Date selectCarTypeName(Integer typeId) {
		
		return czReportMapper.selectCarTypeName(typeId);
	}

	@Override
	public List selectDoubleRecordTotal(Date startTime, Date endTime, String carNumber, List<Integer> listcarType, String size,List listtype) {
		
		return czReportMapper.selectDoubleRecordTotal(startTime,endTime,carNumber,listcarType,size,listtype);
	}

	@Override
	public List<CzReportDto> selectDoubleListByPage(Date startTime, Date endTime, String carNumber, List<Integer> listcarType,
			String size, int length, int start,List listtype,String order) {
		
		return czReportMapper.selectDoubleListByPage(startTime,endTime,carNumber,listcarType,size,length,start,listtype,order);
	}

	@Override
	public List<String> selectByTypeList() {
		
		return czReportMapper.selectByTypeList();
	}

	@Override
	public Date selectInserttime(Date startTime, Date endTime, String carNumber, Integer temp, String size,List<Integer> listcarType) {
		
		return czReportMapper.selectInserttime(startTime,endTime,carNumber,temp,size,listcarType);
	}

	@Override
	public List<Map<String, Object>> exportDay(Date startTime, Date endTime, List<Integer> list, String size, List<Integer> listtype, String carNumber,String order) {
		
		return czReportMapper.exportDay(startTime,endTime,list,size,carNumber,listtype,order);
	}

	@Override
	public List<CzReportDto> selectMonthRecordTotal(Integer year, Integer month, String carNumber, List<Integer> listcarType, String size,Date startTime,Date endTime,Integer nowMonth,Integer endmonth,List<Integer> listtype) {
		
		return czReportMapper.selectMonthRecordTotal(year,month,carNumber,listcarType,size,startTime,endTime,nowMonth,endmonth,listtype);
	}

	@Override
	public List<CzReportDto> selectMonthListByPage(Integer year, Integer month, String carNumber, List<Integer> listcarType,
			String size, int length, int start, Date startTime,Date endTime,Integer nowMonth,Integer endMonth,List<Integer> listtype,String order) {
		
		return czReportMapper.selectMonthListByPage(year,month,carNumber,listcarType,size,length,start,startTime,endTime,nowMonth,endMonth,listtype,order);
	}

	@Override
	public List<CzReportDto> selectMonthInserttime(Integer year, Integer month, String carNumber, Integer typeId,
			String size, Date startTime, Date endTime, Integer nowMonth) {
		
		return czReportMapper.selectMonthInserttime(year,month,carNumber,typeId,size,startTime,endTime,nowMonth);
	}

	@Override
	public List<CzReportDto> selectYearRecordTotal(Integer year, String carNumber, List<Integer> listcarType, String size,
			Date startTime, Date endTime, Integer endyear, List<Integer> listtype,Integer nowyear) {
		
		return czReportMapper.selectYearRecordTotal(year,carNumber,listcarType,size,startTime,endTime,endyear,listtype,nowyear);
	}

	@Override
	public List<CzReportDto> selectYearListByPage(Integer startyear, Integer endyear, String carNumber, List<Integer> listcarType,
			String size, int length, int start, Date startTime, Date endTime, Integer nowYear,
			List<Integer> listtype,String order) {
		
		return czReportMapper.selectYearListByPage(startyear,endyear,carNumber,listcarType,size,length,start,startTime,endTime,nowYear,listtype,order);
	}

	

	@Override
	public List<Map<String, Object>> exportMonth(Date startTime, Date endTime, List<Integer> carTypelist, String size,
			List<Integer> listtype, String carNumber,Integer nowMonth,Integer year,String order) {
		
		return czReportMapper.exportMonth(startTime,endTime,carTypelist,size,listtype,carNumber,nowMonth,year,order);
	}

	@Override
	public List<Map<String, Object>> exportYear(Integer startyear, String carNumber, List<Integer> list, String size,
			Date startTime, Date endTime, Integer endyear, List<Integer> listtype, Integer nowYear,String order) {
		
		return czReportMapper.exportYear(startyear,carNumber,list,size,startTime,endTime,endyear,listtype,nowYear,order);
	}

	
	/*@Override
	public List<Map<String, Object>> exportMonth(Date startTime, Date endTime, List<Integer> listTypeId, String size) {
		
		return czReportMapper.exportMonth(startTime,endTime,);
	}*/

}
