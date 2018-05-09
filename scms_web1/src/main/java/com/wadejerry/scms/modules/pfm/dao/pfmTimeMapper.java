package com.wadejerry.scms.modules.pfm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.pfm.dto.TimeDto;
import com.wadejerry.scms.modules.pfm.model.PfmTime;

public interface pfmTimeMapper {

	int getRecordTotal(@Param("paramsDto")PageParamsDto paramsDto, @Param("listtypeId")List<Integer> listtypeId, @Param("flag")Integer flag);

	List<TimeDto> getTimeListByPage(@Param("paramsDto")PageParamsDto paramsDto, @Param("listtypeId")List<Integer> listtypeId, @Param("flag")Integer flag);

	PfmTime findByTimeName(String string, int i);

	void insertTimeDto(PfmTime inserttime);

	void updateTimeDto(PfmTime inserttime);

	void deleteTimeById(Integer pfmTimeId);

	PfmTime selectByTimeName(String timeName,int i);

	List<TimeDto> configTime(int commpanyId);

}
