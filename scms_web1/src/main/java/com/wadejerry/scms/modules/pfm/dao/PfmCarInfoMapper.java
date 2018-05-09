package com.wadejerry.scms.modules.pfm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.pfm.dto.CarInfoDto;
import com.wadejerry.scms.modules.pfm.model.PfmCarInfo;

public interface PfmCarInfoMapper {
    int deleteByPrimaryKey(Integer carInfoId);

    int insert(PfmCarInfo record);

    int insertSelective(PfmCarInfo record);

    PfmCarInfo selectByPrimaryKey(Integer carInfoId);

    int updateByPrimaryKeySelective(PfmCarInfo record);

    int updateByPrimaryKey(PfmCarInfo record);
   
    //查询符合条件的结果集总大小
    int selectAllCount(@Param("pageParam")PageParamsDto ppd,@Param("companyId")int companyId,@Param("carTypeIds")List<Integer> list);
    //查询所有符合条件的数据
    List<CarInfoDto> selectAllDataBypage(@Param("pageParam")PageParamsDto ppd,@Param("companyId")int companyId,@Param("carTypeIds")List<Integer> list);
    
    int selectifExistbyCarNumber( @Param("carNumber")String carNumber,@Param("companyId")Integer companyId);
    //验证是否有重复车牌号 或卡号
    int selectifExistbyCarNumberExcepself( @Param("carNumber")String carNumber,@Param("companyId")Integer companyId,@Param("carInfoId")int carinfoid);
    int selectifExistbyCardIdExcepself( @Param("cardid")String cardId,@Param("companyId")Integer companyId,@Param("carInfoId")int carinfoid);
    
    int selectifExistByCardId(@Param("cardId")String cardId,@Param("companyId")int companyId);
    int selectifExistByCarnum(@Param("carnum")String carnum,@Param("companyId")int companyId);

    List<Map<String,Object>> selectExcel(@Param("companyId")int companyId,@Param("carType")Integer cartype,@Param("carTypeIds")List<Integer> list,@Param("order")String order,@Param("searchval")String search);
    
    PfmCarInfo selectByCarNum(@Param("searchValue")String serchValue,@Param("companyId")int companyId);
    //假删除车辆信息
    int deleteCarinfo(Integer id);
    
    //按车牌查询车牌
    List<String> selectCarNumByCarNum(@Param("carNum")String carNum,@Param("companyId")int companyId);
    List<String> selectCarNumByKaHao(@Param("kahao")String kahao,@Param("companyId")int companyId);
    List<String> selectCarNumByPhone(@Param("phone")String phone,@Param("companyId")int companyId);
    //按车牌查询车辆信息
    List<PfmCarInfo> selectCarInfoByCarNum(@Param("carNum")String carNum,@Param("companyId")int companyId);
    List<PfmCarInfo> selectCarInfoByKaHao(@Param("kahao")String kahao,@Param("companyId")int companyId);
    List<PfmCarInfo> selectCarInfoByPhone(@Param("phone")String phone,@Param("companyId")int companyId);
    List<String> selectAllCarNum(int companyId);
    PfmCarInfo selectByCardID(@Param("cardId")String cardId,@Param("companyId")int companyId);

	List<String> selectAllByCarNumer();

	List<String> selectAllByCardId();

	List<String> selectAllByPhone();
    
}