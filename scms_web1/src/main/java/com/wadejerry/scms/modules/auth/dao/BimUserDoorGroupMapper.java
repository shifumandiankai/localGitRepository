package com.wadejerry.scms.modules.auth.dao;

import com.wadejerry.scms.modules.auth.model.BimUserDoorGroup;

public interface BimUserDoorGroupMapper {
    int deleteByPrimaryKey(Integer bimUseDoorGroupId);

    int insert(BimUserDoorGroup record);

    int insertSelective(BimUserDoorGroup record);

    BimUserDoorGroup selectByPrimaryKey(Integer bimUseDoorGroupId);

    int updateByPrimaryKeySelective(BimUserDoorGroup record);

    int updateByPrimaryKey(BimUserDoorGroup record);
}