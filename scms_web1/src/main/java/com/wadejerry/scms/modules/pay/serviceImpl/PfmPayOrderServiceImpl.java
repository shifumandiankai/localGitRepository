package com.wadejerry.scms.modules.pay.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wadejerry.scms.modules.pay.dao.PfmPayOrderFinishMapper;
import com.wadejerry.scms.modules.pay.model.PfmPayOrderFinish;
import com.wadejerry.scms.modules.pay.service.PfmPayOrderService;

@Service("pfmPayOrderService")
public class PfmPayOrderServiceImpl implements  PfmPayOrderService{
	@Autowired
	private PfmPayOrderFinishMapper pfmPayOrderFinishMapper;
	
	@Override
	public void insertPayOrder(PfmPayOrderFinish record) {
		pfmPayOrderFinishMapper.insertSelective(record);
	}


}
