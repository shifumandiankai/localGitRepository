package com.wadejerry.scms.modules.onecard.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wadejerry.scms.modules.onecard.dao.BimCateMapper;
import com.wadejerry.scms.modules.onecard.dao.PersonManagerMapper;
import com.wadejerry.scms.modules.onecard.model.BimCate;
import com.wadejerry.scms.modules.onecard.service.BimCateService;
@Service("bimCateService")
public class BimCateServiceImpl implements BimCateService{
	 @Autowired
	 private BimCateMapper bimCateMapper;
	 @Autowired
	 private PersonManagerMapper managerMapper;
	 
	@Override
	public List<BimCate> selectAllInfo(int i) {
		// TODO Auto-generated method stub
		return bimCateMapper.selectAllInfo(i);
	}

	@Override
	public int selectRandom() {
		// TODO Auto-generated method stub
		return bimCateMapper.selectRandom();
	}

	@Override
	public void insertCateInfo(BimCate bimCate) {
		// TODO Auto-generated method stub
		bimCateMapper.insertCateInfo(bimCate);
	}

	@Override
	public void updateCateInfo(Integer bimCateId, String cateName, String note,Date date) {
		// TODO Auto-generated method stub
		bimCateMapper.updateCateInfo(bimCateId,cateName,note,date);
	}

	@Override
	public BimCate selectByCateName(String cateName) {
		// TODO Auto-generated method stub
		return bimCateMapper.selectByCateName(cateName);
	}

	@Transactional
	//@Override
	public void deleteInfo(Integer bimCateId,List<Integer> list) {
		// TODO Auto-generated method stub
		bimCateMapper.deleteInfo(bimCateId);
		if(list.size()>0){
			for(Integer temp:list){
				managerMapper.updateCateType(temp);
			}
		}
		
		//managerMapper.updateCustom1();
		//bimCateMapper.updateCustom1(bimCateId);
		//bimCateMapper.deleteBimCatePerson(bimCateId);
	}

	@Override
	public List<Integer> selectByPersonId(Integer bimCateId) {
		// TODO Auto-generated method stub
		return bimCateMapper.selectByPersonId(bimCateId);
	}

	@Transactional
	public void deleteByCateId(Integer bimCateId) {
		// TODO Auto-generated method stub
		bimCateMapper.deleteInfo(bimCateId);
		//managerMapper.updateCateType(bimCateId);
		managerMapper.updateCustom3(String.valueOf(bimCateId));
	}

}
