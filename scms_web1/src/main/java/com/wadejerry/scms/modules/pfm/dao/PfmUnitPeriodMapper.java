package com.wadejerry.scms.modules.pfm.dao;

import com.wadejerry.scms.modules.pfm.model.PfmUnitPeriod;

public interface PfmUnitPeriodMapper {
    int deleteByPrimaryKey(Integer unitPeriodId);

    int insert(PfmUnitPeriod record);

    int insertSelective(PfmUnitPeriod record);

    PfmUnitPeriod selectByPrimaryKey(Integer unitPeriodId);

    int updateByPrimaryKeySelective(PfmUnitPeriod record);

    int updateByPrimaryKey(PfmUnitPeriod record);
    
    PfmUnitPeriod selectByRuleId(Integer ruleId);

}