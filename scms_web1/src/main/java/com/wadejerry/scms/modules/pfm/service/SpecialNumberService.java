package com.wadejerry.scms.modules.pfm.service;

import java.util.List;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.pfm.dto.SpecialNumDto;

public interface SpecialNumberService {

	 //查询总记录数
		int getRecordTotal(PageParamsDto paramsDto);
	    //分页获取角色信息
		List<SpecialNumDto> getSpecialListByPage(PageParamsDto paramsDto);
		void delete(List<SpecialNumDto> list);
}
