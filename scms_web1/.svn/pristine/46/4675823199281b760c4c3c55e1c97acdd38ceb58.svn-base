package com.wadejerry.scms.modules.device.dao;

import java.util.List;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.device.model.PfmDeviceScreen;
import com.wadejerry.scms.modules.pfm.dto.ScreenDto;

public interface PfmDeviceScreenMapper {
    int deleteByPrimaryKey(Integer pfmDeviceScreenId);

    int insert(PfmDeviceScreen record);

    int insertSelective(PfmDeviceScreen record);

    PfmDeviceScreen selectByPrimaryKey(Integer pfmDeviceScreenId);

    int updateByPrimaryKeySelective(PfmDeviceScreen record);

    int updateByPrimaryKey(PfmDeviceScreen record);
    
    List<PfmDeviceScreen> selectByCompanyId(int companyid);

	int getRecordTotal(PageParamsDto paramsDto);

	List<ScreenDto> getListByPage(PageParamsDto paramsDto);

	ScreenDto findByName(String deviceName, int companyId);

	ScreenDto findByCode(String deviceCode, int companyId);

	void insertInfo(ScreenDto screenDto);

	ScreenDto findByLpr(String deviceId);

	void updateInfo(ScreenDto screenDto);

	void deleteInfo(Integer pfmDeviceScreenId);

	void updateDeviceArr(PfmDeviceScreen deviceScreen);
}