package com.wadejerry.scms.modules.pfm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.pfm.dto.ParkingLotDto;
import com.wadejerry.scms.modules.pfm.model.PfmParkingLot;
import com.wadejerry.scms.modules.pfm.model.PfmParkingLotInfo;

public interface PfmParkingLotMapper {
    int deleteByPrimaryKey(Integer pfmParkingLotId);

    int insert(PfmParkingLot record);

    int insertSelective(PfmParkingLot record);

    PfmParkingLot selectByPrimaryKey(Integer pfmParkingLotId);

    int updateByPrimaryKeySelective(PfmParkingLot record);

    int updateByPrimaryKey(PfmParkingLot record);
    
   List<PfmParkingLot> selectAllbyCompanyId(int companyid);
    
    int selectRecordTotal(@Param("paramsDto")PageParamsDto paramsDto, @Param("listtypeId")List<Integer> listtypeId, @Param("flag")Integer flag );
    
    List<ParkingLotDto> selectRoleListByPage(@Param("paramsDto")PageParamsDto paramsDto, @Param("listtypeId")List<Integer> listtypeId, @Param("flag")Integer flag );
    
   List<PfmParkingLotInfo> findByParkName(String parkName,int constRoleType);
    
    void insertParkingDto(PfmParkingLotInfo parkinglotDto);
    
    PfmParkingLotInfo getUserById(Integer pfmParkingLotId);
    
    void updateParkingDto(PfmParkingLotInfo park);
    
    void deleteParkById(Integer pfmParkingLotId);
    
    void updateAttribution(String parkName,String attribution);

	List<PfmParkingLot> select1ParkingLots(int companyid);
	
	public ParkingLotDto selectByinId(int inId);
	
	public List<ParkingLotDto> selectParkInfo(int i);
	public ParkingLotDto selectparkInfo(String parkinglotname);
	public int selectMaxParkinglotId();
	public int selectMaxID(int commpanyId);

	List<String> selectCarInfoByType(Integer parkId);

	List<ParkingLotDto> selectentranceId(Integer pfmParkingLotId);

	PfmParkingLotInfo selectByCode(String parkingLotCode);

	int selectSumByInId(Integer inId);

	int selectSomeSumByInId(Integer inId, Integer pfmParkingLotId);

	int selectCountByInId(Integer inId);

	int selectSomeCountByInId(Integer inId, Integer pfmParkingLotId);
	
	//ldy
	List<PfmParkingLot> selectSubParksByInid(Integer id);
}