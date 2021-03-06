package com.wadejerry.scms.modules.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wadejerry.scms.modules.auth.dto.AuthTreeDto;
import com.wadejerry.scms.modules.auth.model.BimRoleDeptAuth;

public interface BimRoleDeptAuthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BimRoleDeptAuth record);

    int insertSelective(BimRoleDeptAuth record);

    BimRoleDeptAuth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BimRoleDeptAuth record);

    int updateByPrimaryKey(BimRoleDeptAuth record);
    
    List<AuthTreeDto> selectDeptAuthTree(@Param("roleId")Integer id,@Param("companyId")Integer companyId);
    
    int deleteByRoleId( Integer id);

	List<Integer> selectDeptId();
}