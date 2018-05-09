package com.wadejerry.scms.modules.pfm.dao;

import java.util.List;

import com.wadejerry.scms.modules.pfm.model.PfmSubsidiaryCarNum;

public interface PfmSubsidiaryCarNumMapper {
    int deleteByPrimaryKey(Integer pfmSubsidiaryCarNumId);

    int insert(PfmSubsidiaryCarNum record);

    int insertSelective(PfmSubsidiaryCarNum record);

    PfmSubsidiaryCarNum selectByPrimaryKey(Integer pfmSubsidiaryCarNumId);

    int updateByPrimaryKeySelective(PfmSubsidiaryCarNum record);

    int updateByPrimaryKey(PfmSubsidiaryCarNum record);
    
    List<PfmSubsidiaryCarNum> selectByCarId(int id);
    
    int deleByCarId(int id);
}