package com.wadejerry.scms.modules.auth.dao;

import com.wadejerry.scms.modules.auth.model.BimUserMerchant;

public interface BimUserMerchantMapper {
    int deleteByPrimaryKey(Integer bimUseMerchantId);

    int insert(BimUserMerchant record);

    int insertSelective(BimUserMerchant record);

    BimUserMerchant selectByPrimaryKey(Integer bimUseMerchantId);

    int updateByPrimaryKeySelective(BimUserMerchant record);

    int updateByPrimaryKey(BimUserMerchant record);
}