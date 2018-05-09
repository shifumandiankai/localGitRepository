package com.wadejerry.scms.modules.pfm.dao;

import com.wadejerry.scms.modules.pfm.model.PfmPeriod;
import com.wadejerry.scms.modules.pfm.model.PfmPertime;

public interface PfmPertimeMapper {
    int deleteByPrimaryKey(Integer pertimeId);

    int insert(PfmPertime record);

    int insertSelective(PfmPertime record);

    PfmPertime selectByPrimaryKey(Integer pertimeId);

    int updateByPrimaryKeySelective(PfmPertime record);

    int updateByPrimaryKey(PfmPertime record);
    
    PfmPertime selectByRuleId(Integer ruleId);

}