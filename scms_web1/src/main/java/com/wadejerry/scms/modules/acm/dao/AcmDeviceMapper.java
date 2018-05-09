package com.wadejerry.scms.modules.acm.dao;

import com.wadejerry.scms.modules.acm.model.AcmDevice;

public interface AcmDeviceMapper {
    int deleteByPrimaryKey(Integer deviceId);

    int insert(AcmDevice record);

    int insertSelective(AcmDevice record);

    AcmDevice selectByPrimaryKey(Integer deviceId);

    int updateByPrimaryKeySelective(AcmDevice record);

    int updateByPrimaryKey(AcmDevice record);
}