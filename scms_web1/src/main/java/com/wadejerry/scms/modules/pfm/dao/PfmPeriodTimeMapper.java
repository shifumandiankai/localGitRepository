package com.wadejerry.scms.modules.pfm.dao;

import java.util.List;

import com.wadejerry.scms.modules.pfm.model.PfmPeriodTime;

public interface PfmPeriodTimeMapper {
    int deleteByPrimaryKey(Integer periodTimeId);

    int insert(PfmPeriodTime record);

    int insertSelective(PfmPeriodTime record);

    PfmPeriodTime selectByPrimaryKey(Integer periodTimeId);

    int updateByPrimaryKeySelective(PfmPeriodTime record);

    int updateByPrimaryKey(PfmPeriodTime record);

	List<PfmPeriodTime> selectByRuleId(int id);
}