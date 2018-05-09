package com.wadejerry.scms.modules.pfm.dao;

import java.util.List;

import com.wadejerry.scms.modules.pfm.model.PfmCarTypeRel;

public interface PfmCarTypeRelMapper {
    int deleteByPrimaryKey(Integer carTypeRelId);

    int insert(PfmCarTypeRel record);

    int insertSelective(PfmCarTypeRel record);

    PfmCarTypeRel selectByPrimaryKey(Integer carTypeRelId);

    int updateByPrimaryKeySelective(PfmCarTypeRel record);

    int updateByPrimaryKey(PfmCarTypeRel record);
    
    int selectifHasCarsbyCarTypeId(Integer carTypeId);
    
    int deleteByCarTypeId(int id);

	List<PfmCarTypeRel> selectByCarTypeId(Integer carTypeId);
}