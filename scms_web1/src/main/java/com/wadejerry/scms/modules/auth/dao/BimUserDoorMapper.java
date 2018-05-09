package com.wadejerry.scms.modules.auth.dao;

import com.wadejerry.scms.modules.auth.model.BimUserDoor;

public interface BimUserDoorMapper {
    int deleteByPrimaryKey(Integer bimUseDoorId);

    int insert(BimUserDoor record);

    int insertSelective(BimUserDoor record);

    BimUserDoor selectByPrimaryKey(Integer bimUseDoorId);

    int updateByPrimaryKeySelective(BimUserDoor record);

    int updateByPrimaryKey(BimUserDoor record);
}