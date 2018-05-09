package com.wadejerry.scms.modules.pfm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wadejerry.scms.modules.pfm.dto.CarTypeDto;
import com.wadejerry.scms.modules.pfm.dto.ZTreeDto;
import com.wadejerry.scms.modules.pfm.model.PfmCarType;

public interface PfmCarTypeMapper {
    int deleteByPrimaryKey(Integer carTypeId);

    int insert(PfmCarType record);

    int insertSelective(PfmCarType record);

    PfmCarType selectByPrimaryKey(Integer carTypeId);

    int updateByPrimaryKeySelective(PfmCarType record);

    int updateByPrimaryKey(PfmCarType record);

    //是否是父节点
    int ifparentbyNode(@Param("node")int node,@Param("companyid")int companyid);

    List<PfmCarType> selectCartypeBy1LotId(@Param("id")int id,@Param("companyid")int companyid);
    //查询所有车辆类型
    List<ZTreeDto> selectCartypes(int companyid);
    //根据id查询车辆类型的ztree格式数据
    ZTreeDto selectCartypeById(@Param("id")int id,@Param("companyid")int companyid);
    //查询是否存在此车辆类型
    int selectCountByCartypeName(@Param("name")String name,@Param("companyid")int companyid);
    
    int selectIdBYName(@Param("name")String name,@Param("companyid")int companyid);
    
    PfmCarType selectByCarInfoId(@Param("carinfoid")int carInfoId,@Param("companyid")int companyId);
    
    int updateStatusByPrimaryKey(Integer carTypeId);

	List<PfmCarType> configParkReport();

	List<ZTreeDto> selectCartypesByAuth(@Param("companyid")int companyid,@Param("typeid")List<Integer> typeid);

	void deleteCartType(Integer pfmParkingLotId);

	List<PfmCarType> selectByCustom1(Integer pfmParkingLotId);

}