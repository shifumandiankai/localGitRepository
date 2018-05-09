package com.wadejerry.scms.modules.pfm.dao;

import com.wadejerry.scms.modules.pfm.model.PfmAbnormalRelease;

public interface PfmAbnormalReleaseMapper {
    int deleteByPrimaryKey(Integer abnormalReleaseId);

    int insert(PfmAbnormalRelease record);

    int insertSelective(PfmAbnormalRelease record);

    PfmAbnormalRelease selectByPrimaryKey(Integer abnormalReleaseId);

    int updateByPrimaryKeySelective(PfmAbnormalRelease record);

    int updateByPrimaryKey(PfmAbnormalRelease record);
}