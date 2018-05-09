package com.wadejerry.scms.modules.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wadejerry.scms.modules.auth.dto.AuthTreeDto;
import com.wadejerry.scms.modules.auth.model.BimAuthorization;
import com.wadejerry.scms.modules.pfm.dto.CarTypeTreeDto;

public interface BimAuthorizationMapper {
    int deleteByPrimaryKey(Integer bimAuthorizationId);

    int insert(BimAuthorization record);

    int insertSelective(BimAuthorization record);

    BimAuthorization selectByPrimaryKey(Integer bimAuthorizationId);

    int updateByPrimaryKeySelective(BimAuthorization record);

    int updateByPrimaryKey(BimAuthorization record);
    
    List<AuthTreeDto> selectAll(@Param("roleId")Integer id,@Param("subs")List<Integer> subs);
    
    List<String> selectPermissionByUserId(Integer id);
    
    List<String> selectPermissionsBySubs(@Param("subs")List<Integer> subs);
    
    List<CarTypeTreeDto> selectCartypeBy(@Param("companyId")Integer companyId,@Param("ids")List<Integer> carTypeId);
}