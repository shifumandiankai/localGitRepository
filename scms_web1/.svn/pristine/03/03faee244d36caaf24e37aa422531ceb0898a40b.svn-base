package com.wadejerry.scms.modules.pfm.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wadejerry.scms.frame.entity.ReportPageParamsDto;
import com.wadejerry.scms.modules.pfm.dto.PfmFeeRecDto;
import com.wadejerry.scms.modules.pfm.model.PfmChargeRecord;
import com.wadejerry.scms.modules.pfm.model.PfmFeeRec;

public interface PfmFeeRecMapper {

	List<PfmFeeRecDto> selectRecording(Integer cardid, int enterpriseAdministrator);

	int selectMaxTradeId(Integer carInfoId);

	Date SelectMaxDisableTime(Integer carInfoId, Integer tradeId);

	PfmFeeRecDto selectInfo(Integer carId, Integer typeId);

	void insertchongInfo(PfmFeeRecDto pfmFeeRecDto);

	int selectMaxTradeIdByType( Integer typeid);

	Date selectMaxTime(Integer carid);

	String selectFeeTatal(Integer carid);

	List<PfmFeeRecDto> selectPFRInfo(Integer carid, int companyid);

	List<PfmFeeRecDto> selectPFRAllInfo();

	Double selectAllFeeTatal(int carid, int type, int companyid);

	int selectInfoByType(int carid, int type, int companyid);

	int getFeeReportCount(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("carNumber")String carNumber, @Param("listtypeId")List<Integer> listtypeId, @Param("size")String size,  @Param("typeId")Integer typeId, @Param("accountType")Integer accountType);

	List<PfmChargeRecord> getFeeReportListByPage(ReportPageParamsDto paramsDto);

	List<Map<String, Object>> exportaccount(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("carNumber")String carNumber, @Param("listTypeId")List<Integer> listTypeId,
			@Param("size")String size, @Param("order")String order, @Param("accountType")Integer accountType, @Param("typeId")Integer typeId);

}
