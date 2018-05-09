package com.wadejerry.scms.modules.pfm.dao;

import java.util.List;

import com.wadejerry.scms.modules.pfm.model.PfmUnitPeriodTime;

public interface PfmUnitPeriodTimeMapper {
    int deleteByPrimaryKey(Integer unitPeriodTimeId);

    int insert(PfmUnitPeriodTime record);

    int insertSelective(PfmUnitPeriodTime record);

    PfmUnitPeriodTime selectByPrimaryKey(Integer unitPeriodTimeId);

    int updateByPrimaryKeySelective(PfmUnitPeriodTime record);

    int updateByPrimaryKey(PfmUnitPeriodTime record);

	List<PfmUnitPeriodTime> selectByRuleId(int id);
}