package com.wadejerry.scms.modules.pfm.dao;

import com.wadejerry.scms.modules.pfm.model.PfmOrder;

public interface PfmOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PfmOrder record);

    int insertSelective(PfmOrder record);

    PfmOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PfmOrder record);

    int updateByPrimaryKey(PfmOrder record);
    
    PfmOrder queryByOrderId(String id);
}