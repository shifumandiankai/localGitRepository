package com.wadejerry.scms.modules.pfm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wadejerry.scms.modules.pfm.model.PfmParkSpace;

public interface PfmParkSpaceMapper {
    int deleteByPrimaryKey(Integer pfmParkSpace);

    int insert(PfmParkSpace record);

    int insertSelective(PfmParkSpace record);

    PfmParkSpace selectByPrimaryKey(Integer pfmParkSpace);

    int updateByPrimaryKeySelective(PfmParkSpace record);

    int updateByPrimaryKey(PfmParkSpace record);
    
    List<PfmParkSpace> selectParkSpacelistByparkinglotid(int parkinglotid);
    
    PfmParkSpace selectBycarInfoId(int carinfoid);
    
    //查询是否存在此车位
    int selectCountByLotNameAndCode(@Param("code")String spaceCode,@Param("lotname")String lotName,@Param("companyid")int companyid);
    
    int selectIdBYLotNameAndCode(@Param("code")String spaceCode,@Param("lotname")String lotName,@Param("companyid")int companyid);
}