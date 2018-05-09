package com.wadejerry.scms.modules.pfm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.pfm.dto.ChargeRuleDto;
import com.wadejerry.scms.modules.pfm.model.PfmChargeRule;

public interface PfmChargeRuleMapper {
    int deleteByPrimaryKey(Integer ruleId);

    int insert(PfmChargeRule record);

    int insertSelective(PfmChargeRule record);

    PfmChargeRule selectByPrimaryKey(Integer ruleId);

    int updateByPrimaryKeySelective(PfmChargeRule record);

    int updateByPrimaryKey(PfmChargeRule record);
    
    int selectAllCount(@Param("pageParam")PageParamsDto ppd,@Param("companyId")int companyId,@Param("carTypeIds")List<Integer> list);
    
    List<ChargeRuleDto> selectByCompanyid(@Param("pageParam")PageParamsDto ppd,@Param("companyId")int companyId,@Param("carTypeIds")List<Integer> list);
    
    int selectByCarTypeId(int id);
    
    PfmChargeRule selectBaoqi(int carInfoId);
    
    PfmChargeRule selectByCarId(Integer id);
    
    PfmChargeRule selectByCarType(Integer id);
    
    int deleteByCarType(int id);
    
    
}