package com.wadejerry.scms.modules.pfm.dao;

import com.wadejerry.scms.modules.pfm.model.PfmAnshi;
import com.wadejerry.scms.modules.pfm.model.PfmBaoqi;

public interface PfmBaoqiMapper {
    int deleteByPrimaryKey(Integer baoqiId);

    int insert(PfmBaoqi record);

    int insertSelective(PfmBaoqi record);

    PfmBaoqi selectByPrimaryKey(Integer baoqiId);

    int updateByPrimaryKeySelective(PfmBaoqi record);

    int updateByPrimaryKey(PfmBaoqi record);
    
    PfmBaoqi selectByRuleId(Integer ruleId);

}