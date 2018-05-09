package com.wadejerry.scms.modules.pfm.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wadejerry.scms.frame.entity.ReportPageParamsDto;
import com.wadejerry.scms.modules.pfm.dto.PfmFeeRecDto;
import com.wadejerry.scms.modules.pfm.model.PfmChargeRecord;
import com.wadejerry.scms.modules.pfm.model.PfmFeeRec;

public interface PfmFeeRecService {

	List<PfmFeeRecDto> selectRecording(Integer valueOf, int enterpriseAdministrator);
	Date SelectMaxDisableTime(Integer carInfoId,Integer tradeId);
	int selectMaxTradeId(Integer carInfoId);
	int selectMaxTradeIdByType(Integer carInfoId);
	PfmFeeRecDto selectInfo(Integer carId,Integer typeId);
	void insertchongInfo(PfmFeeRecDto pfmFeeRecDto);
	Date selectMaxTime(Integer carid);
	public String selectFeeTatal(Integer carid);
	List<PfmFeeRecDto> selectPFRInfo(Integer carid, int enterpriseAdministrator);
	List<PfmFeeRecDto> selectPFRAllInfo();
	Double selectAllFeeTatal(int carid,int type,int companyid);
	int  selectInfoByType(int carid,int type,int company);
	int getFeeReportCount(Date startTime, Date endTime, String carNumber, List<Integer> listtypeId, String size, Integer typeId, Integer accountType);
	List<PfmChargeRecord> getFeeReportListByPage(ReportPageParamsDto paramsDto);
	List<Map<String, Object>> exportaccount(Date startTime, Date endTime, String carNumber, List<Integer> listTypeId,
			String size, String order, Integer accountType, Integer typeId);
}
