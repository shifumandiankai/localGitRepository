package com.wadejerry.scms.modules.auth.dao;

import com.wadejerry.scms.modules.auth.model.BimCompany;

public interface BimCompanyMapper {
    int deleteByPrimaryKey(Integer bimCompanyId);

    int insert(BimCompany record);

    int insertSelective(BimCompany record);

    BimCompany selectByPrimaryKey(Integer bimCompanyId);

    int updateByPrimaryKeySelective(BimCompany record);

    int updateByPrimaryKey(BimCompany record);
}