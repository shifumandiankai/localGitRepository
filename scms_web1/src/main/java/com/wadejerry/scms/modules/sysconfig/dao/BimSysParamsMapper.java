package com.wadejerry.scms.modules.sysconfig.dao;

import java.util.List;

import com.wadejerry.scms.modules.sysconfig.model.BimSysParams;


public interface BimSysParamsMapper {
    int deleteByPrimaryKey(Integer bimSysParamsId);

    int insert(BimSysParams record);

    int insertSelective(BimSysParams record);

    BimSysParams selectByPrimaryKey(Integer bimSysParamsId);

    int updateByPrimaryKeySelective(BimSysParams record);

    int updateByPrimaryKey(BimSysParams record);
    
    List<BimSysParams> select(Integer configType);
}