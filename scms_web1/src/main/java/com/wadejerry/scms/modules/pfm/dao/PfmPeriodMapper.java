package com.wadejerry.scms.modules.pfm.dao;

import com.wadejerry.scms.modules.pfm.model.PfmPeriod;
import com.wadejerry.scms.modules.pfm.model.PfmUnitPeriod;

public interface PfmPeriodMapper {
    int deleteByPrimaryKey(Integer periodId);

    int insert(PfmPeriod record);

    int insertSelective(PfmPeriod record);

    PfmPeriod selectByPrimaryKey(Integer periodId);

    int updateByPrimaryKeySelective(PfmPeriod record);

    int updateByPrimaryKey(PfmPeriod record);
    
    PfmPeriod selectByRuleId(Integer ruleId);

}