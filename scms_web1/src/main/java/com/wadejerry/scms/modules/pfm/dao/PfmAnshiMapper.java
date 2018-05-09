package com.wadejerry.scms.modules.pfm.dao;

import com.wadejerry.scms.modules.pfm.model.PfmAnshi;
import com.wadejerry.scms.modules.pfm.model.PfmPeriod;

public interface PfmAnshiMapper {
    int deleteByPrimaryKey(Integer anshiChargeId);

    int insert(PfmAnshi record);

    int insertSelective(PfmAnshi record);

    PfmAnshi selectByPrimaryKey(Integer anshiChargeId);

    int updateByPrimaryKeySelective(PfmAnshi record);

    int updateByPrimaryKey(PfmAnshi record);
    
    PfmAnshi selectByRuleId(Integer ruleId);

}