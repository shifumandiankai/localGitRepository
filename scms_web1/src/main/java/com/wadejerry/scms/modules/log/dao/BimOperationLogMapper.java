package com.wadejerry.scms.modules.log.dao;

import com.wadejerry.scms.modules.log.model.BimOperationLog;

public interface BimOperationLogMapper {
    int deleteByPrimaryKey(Integer operationlogId);

    int insert(BimOperationLog record);

    int insertSelective(BimOperationLog record);

    BimOperationLog selectByPrimaryKey(Integer operationlogId);

    int updateByPrimaryKeySelective(BimOperationLog record);

    int updateByPrimaryKey(BimOperationLog record);
}