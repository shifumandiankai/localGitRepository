package com.wadejerry.scms.modules.sysconfig.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.sysconfig.model.BimNetZone;

public interface BimNetZoneMapper {
    int deleteByPrimaryKey(Integer netZoneId);

    int insert(BimNetZone record);

    int insertSelective(BimNetZone record);

    BimNetZone selectByPrimaryKey(Integer netZoneId);

    int updateByPrimaryKeySelective(BimNetZone record);

    int updateByPrimaryKey(BimNetZone record);
    
    int selectCountByTable(@Param("pageParam")PageParamsDto ppd,@Param("companyId")int companyId);
    
    List<BimNetZone> selectByTable(@Param("pageParam")PageParamsDto ppd,@Param("companyId")int companyId);
    
    int selectByUrl(@Param("netzone")BimNetZone netzone,@Param("companyId")int companyId);
    
    int selectByUrlExceptSelf(@Param("netzone")BimNetZone netzone,@Param("companyId")int companyId);
    
    List<BimNetZone> selectByCompanyId(Integer companyId);
}