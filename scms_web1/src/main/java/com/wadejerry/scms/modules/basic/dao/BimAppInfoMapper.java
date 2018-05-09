package com.wadejerry.scms.modules.basic.dao;

import com.wadejerry.scms.modules.basic.model.BimAppInfo;

public interface BimAppInfoMapper {
    int deleteByPrimaryKey(Integer appId);

    int insert(BimAppInfo record);

    int insertSelective(BimAppInfo record);

    BimAppInfo selectByPrimaryKey(Integer appId);

    int updateByPrimaryKeySelective(BimAppInfo record);

    int updateByPrimaryKey(BimAppInfo record);
}