package com.wadejerry.scms.modules.pfm.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wadejerry.scms.frame.constant.ConstParamLog;
import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.frame.log.LogRecord;
import com.wadejerry.scms.modules.pfm.dao.PfmSpecialCarMapper;
import com.wadejerry.scms.modules.pfm.dto.SpecialNumDto;
import com.wadejerry.scms.modules.pfm.service.SpecialNumberService;

@Service
public class SpecialNumberServiceImpl implements SpecialNumberService {

	@Autowired 
	private PfmSpecialCarMapper specialMapper;
	@Autowired
	private LogRecord logRecord;
	@Override
	public int getRecordTotal(PageParamsDto paramsDto) {
		// TODO Auto-generated method stub
		return specialMapper.selectAllCount(paramsDto);
	}

	@Override
	public List<SpecialNumDto> getSpecialListByPage(PageParamsDto paramsDto) {
		// TODO Auto-generated method stub
		return specialMapper.selectAllDataBypage(paramsDto);
	}

	@Override
	@Transactional
	public void delete(List<SpecialNumDto> list) {
		// TODO Auto-generated method stub
		StringBuilder carNum = new StringBuilder();
		for(SpecialNumDto dto:list){
			
			specialMapper.deleteByisdel(dto.getPfmSpecialCarId());
			carNum.append(dto.getCarNumber()+";");
			
		}
		logRecord.recordLog(ConstParamLog.LOG_MODULE_PFM_SPECIALCARNUM, ConstParamLog.LOG_OPER_DELETE, ConstParamLog.LOG_TYPE_CONFIG, carNum.toString());
	}

}
