package com.wadejerry.scms.modules.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wadejerry.scms.modules.auth.dto.AuthTreeDto;
import com.wadejerry.scms.modules.auth.model.BimRoleAreaAuth;

public interface BimRoleAreaAuthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BimRoleAreaAuth record);

    int insertSelective(BimRoleAreaAuth record);

    BimRoleAreaAuth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BimRoleAreaAuth record);

    int updateByPrimaryKey(BimRoleAreaAuth record);
    
    List<AuthTreeDto> selectAreaAuthTree(@Param("roleId")Integer id,@Param("companyId")Integer companyId);
    
    int deleteByRoleId( Integer id);
}