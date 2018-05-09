package com.wadejerry.scms.modules.auth.dao;

import com.wadejerry.scms.modules.auth.model.BimLoginStatus;

public interface BimLoginStatusMapper {
    int deleteByPrimaryKey(Integer logId);

	int insert(BimLoginStatus record);

	int insertSelective(BimLoginStatus record);

	BimLoginStatus selectByPrimaryKey(Integer logId);

	int updateByPrimaryKeySelective(BimLoginStatus record);

	int updateByPrimaryKey(BimLoginStatus record);
	
	int deleteBySessionId(String sessionID);

	int deleteAll();
}