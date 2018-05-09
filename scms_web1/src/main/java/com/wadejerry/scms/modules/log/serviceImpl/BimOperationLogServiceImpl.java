package com.wadejerry.scms.modules.log.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wadejerry.scms.modules.log.dao.BimOperationLogMapper;
import com.wadejerry.scms.modules.log.model.BimOperationLog;
import com.wadejerry.scms.modules.log.service.BimOperationLogService;

@Service("bimOperationLogService")
public class BimOperationLogServiceImpl implements  BimOperationLogService{
	@Autowired
	private BimOperationLogMapper bimOperationLogMapper;
	
	@Override
	public int insertOperationLog(BimOperationLog log) {
		return bimOperationLogMapper.insertSelective(log);
	}

	@Override
	public int deleteLogByLogTypeAndTime(int logType, Date endTime) {
		// TODO Auto-generated method stub
		return 0;
	}

}
