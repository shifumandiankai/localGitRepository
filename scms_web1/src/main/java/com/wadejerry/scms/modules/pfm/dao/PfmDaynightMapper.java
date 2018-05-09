package com.wadejerry.scms.modules.pfm.dao;

import com.wadejerry.scms.modules.pfm.model.PfmBaoqi;
import com.wadejerry.scms.modules.pfm.model.PfmDaynight;

public interface PfmDaynightMapper {
    int deleteByPrimaryKey(Integer daynightId);

    int insert(PfmDaynight record);

    int insertSelective(PfmDaynight record);

    PfmDaynight selectByPrimaryKey(Integer daynightId);

    int updateByPrimaryKeySelective(PfmDaynight record);

    int updateByPrimaryKey(PfmDaynight record);
    
    PfmDaynight selectByRuleId(Integer ruleId);

}