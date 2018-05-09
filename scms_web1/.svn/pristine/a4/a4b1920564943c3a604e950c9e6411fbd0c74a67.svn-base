package com.wadejerry.scms.modules.auth.dao;

import java.util.List;

import com.wadejerry.scms.modules.auth.dto.UserRoleRelDto;
import com.wadejerry.scms.modules.auth.model.BimUserRole;

public interface BimUserRoleMapper {
    int deleteByPrimaryKey(Integer bimUseRoleId);

    int insert(BimUserRole record);

    int insertSelective(BimUserRole record);

    BimUserRole selectByPrimaryKey(Integer bimUseRoleId);

    int updateByPrimaryKeySelective(BimUserRole record);

    int updateByPrimaryKey(BimUserRole record);
    
    List<UserRoleRelDto> selectUserRoleDtoByUserId(Integer userId);
    
    int deleteByUserId(Integer userId);
    
    int countByRoleId(Integer id);
}