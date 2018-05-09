package com.wadejerry.scms.modules.pfm.dao;

import java.util.List;

import com.wadejerry.scms.modules.pfm.model.PfmTimeChargeTime;

public interface PfmTimeChargeTimeMapper {
    int deleteByPrimaryKey(Integer timeChargeTimeId);

    int insert(PfmTimeChargeTime record);

    int insertSelective(PfmTimeChargeTime record);

    PfmTimeChargeTime selectByPrimaryKey(Integer timeChargeTimeId);

    int updateByPrimaryKeySelective(PfmTimeChargeTime record);

    int updateByPrimaryKey(PfmTimeChargeTime record);
    
    List<PfmTimeChargeTime> selectByRuleId(Integer id);
}