package com.wadejerry.scms.modules.device.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.device.dao.PfmDeviceScreenMapper;
import com.wadejerry.scms.modules.device.dao.PfmDevicelrpMapper;
import com.wadejerry.scms.modules.pfm.dto.ScreenDto;
import com.wadejerry.scms.modules.pfm.service.SrceenService;

@Service("srceenService")
public class ScreenServiceImpl implements SrceenService{

	@Autowired
	private PfmDeviceScreenMapper deviceScreenMapper;
	@Autowired
	private PfmDevicelrpMapper devicelrpMapper;
	
	@Override
	public int getRecordTotal(PageParamsDto paramsDto) {
		
		return deviceScreenMapper.getRecordTotal(paramsDto);
	}

	@Override
	public List<ScreenDto> getListByPage(PageParamsDto paramsDto) {
		
		return deviceScreenMapper.getListByPage(paramsDto);
	}

	@Override
	public ScreenDto findByName(String deviceName, int companyId) {
		
		return deviceScreenMapper.findByName(deviceName,companyId);
	}

	@Override
	public ScreenDto findByCode(String deviceCode, int companyId) {
		
		return deviceScreenMapper.findByCode(deviceCode,companyId);
	}

	@Transactional
	public void insertInfo(ScreenDto screenDto) {
		deviceScreenMapper.insertInfo(screenDto);
		if(screenDto.getDeviceIdArr()!=null){
			List<String> list=Arrays.asList(screenDto.getDeviceIdArr().split(","));
			for(String temp:list){
				devicelrpMapper.insertByScreenId(screenDto.getPfmDeviceScreenId(),Integer.valueOf(temp),screenDto.getInsertTime());
			}
		}
		
		
	}

	@Override
	public ScreenDto findByLpr(String deviceId) {
	
		return deviceScreenMapper.findByLpr(deviceId);
	}

	@Transactional
	public void updateInfo(ScreenDto screenDto) {
		deviceScreenMapper.updateInfo(screenDto);
		devicelrpMapper.updateByScreenId(screenDto.getPfmDeviceScreenId(),screenDto.getUpdateTime());//把screenId清空
		if(screenDto.getDeviceIdArr()!=null){
			List<String> list=Arrays.asList(screenDto.getDeviceIdArr().split(","));
			for(String temp:list){
				devicelrpMapper.insertByScreenId(screenDto.getPfmDeviceScreenId(),Integer.valueOf(temp),screenDto.getUpdateTime());
			}
		}
	}

	@Transactional
	public void deleteInfo(ScreenDto screenDto) {
		deviceScreenMapper.deleteInfo(screenDto.getPfmDeviceScreenId());
		devicelrpMapper.updateByScreenId(screenDto.getPfmDeviceScreenId(),screenDto.getUpdateTime());//把screenId清空
	}

	

	
	/*public void insert(ScreenDto screenDto) {
		deviceScreenMapper.insert(screenDto);
		
	}*/

}
