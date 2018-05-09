package com.wadejerry.scms.modules.pfm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.pfm.dto.SpecialNumDto;
import com.wadejerry.scms.modules.pfm.model.PfmSpecialCar;

public interface PfmSpecialCarMapper {
    int deleteByPrimaryKey(Integer pfmSpecialCarId);

    int insert(PfmSpecialCar record);

    int insertSelective(PfmSpecialCar record);

    PfmSpecialCar selectByPrimaryKey(Integer pfmSpecialCarId);

    int updateByPrimaryKeySelective(PfmSpecialCar record);

    int updateByPrimaryKey(PfmSpecialCar record);
    
    //查询符合条件的结果集总大小
    int selectAllCount(@Param("pageParam")PageParamsDto ppd);
    //查询所有符合条件的数据
    List<SpecialNumDto> selectAllDataBypage(@Param("pageParam")PageParamsDto ppd);
    
    int deleteByisdel(Integer id);
    
    int selectIfHasCarNum(@Param("carNum")String carNum,@Param("companyId")Integer bimcompanyId);//添加判断
    int selectIfHasCarNumExcepSelf(@Param("carNum")String carNum,@Param("companyId")Integer bimcompanyId,@Param("pfmSpecialCarId") Integer id);//修改判断
}