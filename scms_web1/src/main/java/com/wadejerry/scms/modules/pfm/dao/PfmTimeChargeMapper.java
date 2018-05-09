package com.wadejerry.scms.modules.pfm.dao;

import com.wadejerry.scms.modules.pfm.model.PfmTimeCharge;

public interface PfmTimeChargeMapper {
    int deleteByPrimaryKey(Integer timeChargeId);

    int insert(PfmTimeCharge record);

    int insertSelective(PfmTimeCharge record);

    PfmTimeCharge selectByPrimaryKey(Integer timeChargeId);

    int updateByPrimaryKeySelective(PfmTimeCharge record);

    int updateByPrimaryKey(PfmTimeCharge record);
    
    PfmTimeCharge selectByRuleId(Integer ruleId);
}