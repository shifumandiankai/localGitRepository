package com.wadejerry.scms.modules.basic.dao;

import com.wadejerry.scms.modules.basic.model.BimCardApp;

public interface BimCardAppMapper {
    int deleteByPrimaryKey(Integer bimCardAppId);

    int insert(BimCardApp record);

    int insertSelective(BimCardApp record);

    BimCardApp selectByPrimaryKey(Integer bimCardAppId);

    int updateByPrimaryKeySelective(BimCardApp record);

    int updateByPrimaryKey(BimCardApp record);
    
    BimCardApp selectByCarID(Integer id);
    
    int deleteByCarId(Integer id);
    
}