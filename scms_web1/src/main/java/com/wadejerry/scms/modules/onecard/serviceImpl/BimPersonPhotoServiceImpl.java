package com.wadejerry.scms.modules.onecard.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wadejerry.scms.modules.onecard.dao.BimPersonPhotoMapper;
import com.wadejerry.scms.modules.onecard.dto.BimPersonPhotoDto;
import com.wadejerry.scms.modules.onecard.model.BimPersonPhoto;
import com.wadejerry.scms.modules.onecard.service.BimPersonPhotoService;
@Service("bimPersonPhotoService")
public class BimPersonPhotoServiceImpl implements BimPersonPhotoService{
	@Autowired
	private BimPersonPhotoMapper bimPersonPhotoMapper; 
	
	@Override
	public void insertInfo(BimPersonPhoto bimPersonPhoto) {
		bimPersonPhotoMapper.insertInfo(bimPersonPhoto);
		
	}

	@Override
	public void updateFinger(int fingerprint1Num) {
		// TODO Auto-generated method stub
		bimPersonPhotoMapper.updateFinger(fingerprint1Num);
	}

	@Override
	public void updateAllFinger() {
		// TODO Auto-generated method stub
		bimPersonPhotoMapper.updateAllFinger();
	}

	@Override
	public BimPersonPhoto selectByPersonId(Integer personId) {
		// TODO Auto-generated method stub
		return bimPersonPhotoMapper.selectByPersonId(personId);
	}

	@Override
	public BimPersonPhotoDto selectFingerSq(Integer personId) {
		// TODO Auto-generated method stub
		return bimPersonPhotoMapper.selectFingerSq(personId);
	}

}
