package com.wadejerry.scms.modules.pfm.service;

import java.util.List;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.pfm.dto.ScreenDto;

public interface SrceenService {

	int getRecordTotal(PageParamsDto paramsDto);

	List<ScreenDto> getListByPage(PageParamsDto paramsDto);

	ScreenDto findByName(String deviceName, int companyId);

	ScreenDto findByCode(String deviceCode, int companyId);

	void insertInfo(ScreenDto screenDto);

	ScreenDto findByLpr(String string);

	void updateInfo(ScreenDto screenDto);

	void deleteInfo(ScreenDto screenDto);

	//void insert(ScreenDto screenDto);

	

}
