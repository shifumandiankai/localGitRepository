package com.wadejerry.scms.modules.pfm.service;

import java.util.List;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.pfm.dto.TimeDto;
import com.wadejerry.scms.modules.pfm.model.PfmTime;

public interface PfmTimeService {
    //查询总记录数
	int getRecordTotal(PageParamsDto paramsDto, List<Integer> listtypeId, Integer flag);
    //分页获取角色信息
	List<TimeDto> getTimeListByPage(PageParamsDto paramsDto, List<Integer> listtypeId, Integer flag);
	//判断时段名称是否已存在
	PfmTime findByTimeName(String string, int i);
	//添加信息
	void insertTimeDto(PfmTime inserttime);
	void updateTimeDto(PfmTime inserttime);
	void deleteTimeById(Integer pfmTimeId);
	PfmTime selectByTimeName(String timeName, int i);
	List<TimeDto> configTime(int commpanyId);

}
