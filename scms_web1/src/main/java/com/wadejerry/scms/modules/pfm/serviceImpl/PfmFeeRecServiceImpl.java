package com.wadejerry.scms.modules.pfm.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wadejerry.scms.frame.entity.ReportPageParamsDto;
import com.wadejerry.scms.modules.pfm.dao.PfmFeeRecMapper;
import com.wadejerry.scms.modules.pfm.dto.PfmFeeRecDto;
import com.wadejerry.scms.modules.pfm.model.PfmChargeRecord;
import com.wadejerry.scms.modules.pfm.service.PfmFeeRecService;

@Service("pfmfeerecService")
public class PfmFeeRecServiceImpl implements PfmFeeRecService {
	@Autowired
	private PfmFeeRecMapper pfmfeerecMapper;

	public List<PfmFeeRecDto> selectRecording(Integer cardid, int enterpriseAdministrator) {

		return pfmfeerecMapper.selectRecording(cardid, enterpriseAdministrator);
	}

	public Date SelectMaxDisableTime(Integer carInfoId, Integer tradeId) {
		return pfmfeerecMapper.SelectMaxDisableTime(carInfoId, tradeId);

	}

	public int selectMaxTradeId(Integer carInfoId) {
		return pfmfeerecMapper.selectMaxTradeId(carInfoId);

	}

	public void insertchongInfo(PfmFeeRecDto pfmFeeRecDto) {
		pfmfeerecMapper.insertchongInfo(pfmFeeRecDto);

	}

	public int selectMaxTradeIdByType(Integer typeid) {
		return pfmfeerecMapper.selectMaxTradeIdByType(typeid);

	}

	public Date selectMaxTime(Integer carid) {

		return pfmfeerecMapper.selectMaxTime(carid);
	}

	public String selectFeeTatal(Integer carid) {

		return pfmfeerecMapper.selectFeeTatal(carid);
	}

	public PfmFeeRecDto selectInfo(Integer carId, Integer tradeId) {
		return pfmfeerecMapper.selectInfo(carId, tradeId);
	}

	public List<PfmFeeRecDto> selectPFRInfo(Integer carid, int companyid) {

		return pfmfeerecMapper.selectPFRInfo(carid, companyid);
	}

	@Override
	public List<PfmFeeRecDto> selectPFRAllInfo() {

		return pfmfeerecMapper.selectPFRAllInfo();
	}

	@Override
	public Double selectAllFeeTatal(int carid, int type, int companyid) {

		return pfmfeerecMapper.selectAllFeeTatal(carid, type, companyid);
	}

	@Override
	public int selectInfoByType(int carid, int type, int companyid) {

		return pfmfeerecMapper.selectInfoByType(carid, type, companyid);
	}

	@Override
	public int getFeeReportCount(Date startTime, Date endTime, String carNumber, List<Integer> listtypeId,
			String size,Integer typeId,Integer accountType) {
		// TODO Auto-generated method stub
		return pfmfeerecMapper.getFeeReportCount(startTime,endTime,carNumber,listtypeId,size,typeId,accountType);
	}

	@Override
	public List<PfmChargeRecord> getFeeReportListByPage(ReportPageParamsDto paramsDto) {
		// TODO Auto-generated method stub
		return pfmfeerecMapper.getFeeReportListByPage(paramsDto);
	}

	@Override
	public List<Map<String, Object>> exportaccount(Date startTime, Date endTime, String carNumber,
			List<Integer> listTypeId, String size, String order, Integer accountType, Integer typeId) {
		// TODO Auto-generated method stub
		return pfmfeerecMapper.exportaccount(startTime,endTime,carNumber,listTypeId,size,order,accountType,typeId);
	}

}
