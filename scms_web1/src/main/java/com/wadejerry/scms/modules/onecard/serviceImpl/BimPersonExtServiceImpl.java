package com.wadejerry.scms.modules.onecard.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wadejerry.scms.modules.onecard.dao.BimPersonExtMapper;
import com.wadejerry.scms.modules.onecard.dao.PersonManagerMapper;
import com.wadejerry.scms.modules.onecard.dto.BimPersonDto;
import com.wadejerry.scms.modules.onecard.dto.BimPersonExtDto;
import com.wadejerry.scms.modules.onecard.service.BimPersonExtService;



@Service("bimPersonExtService")
public class BimPersonExtServiceImpl implements BimPersonExtService {
	@Autowired
	private BimPersonExtMapper bimPersonExtMapper;
	@Autowired
	private PersonManagerMapper personManagerMapper;

	public void saveInfo(BimPersonExtDto bimPersonExtDto) {
		bimPersonExtMapper.saveInfo(bimPersonExtDto);
	}

	/*@Override
	public void updateInfo(BimPersonExtDto bimPersonExtDto) {
		// TODO Auto-generated method stub
		
	}*/

	/*@Transactional
	public void updateInfo(BimPersonDto bimPersonExtDto) {
		//bimPersonExtMapper.updateInfo(bimPersonExtDto);
		//personManagerMapper.updateEmail(bimPersonExtDto);
	}*/
	/*
	@Transactional
	public void updateInfo(BimPersonExtDto bimPersonExtDto) {
		// TODO Auto-generated method stub
		bimPersonExtMapper.updateInfo(bimPersonExtDto);
	}*/

}
