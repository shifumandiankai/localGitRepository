package com.wadejerry.scms.modules.acm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.acm.model.AcmServer;

public interface AcmServerMapper {
    int deleteByPrimaryKey(Integer acmServerId);

    int insert(AcmServer record);

    int insertSelective(AcmServer record);

    AcmServer selectByPrimaryKey(Integer acmServerId);

    int updateByPrimaryKeySelective(AcmServer record);

    int updateByPrimaryKey(AcmServer record);
    
    int selectCountByTable(@Param("pageParam")PageParamsDto ppd,@Param("companyId")int companyId);
    
    List<AcmServer> selectByTable(@Param("pageParam")PageParamsDto ppd,@Param("companyId")int companyId);
    
    int selectCountByIpANDPort(@Param("ip")String ip,@Param("port")String port,@Param("companyId")int companyId);
    
    int selectCountByIpANDPortExceptself(@Param("id")Integer id,@Param("ip")String ip,@Param("port")String port,@Param("companyId")int companyId);
    
    
}