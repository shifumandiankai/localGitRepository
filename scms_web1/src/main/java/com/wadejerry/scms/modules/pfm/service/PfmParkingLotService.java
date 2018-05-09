package com.wadejerry.scms.modules.pfm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.pfm.dto.ParkingLotDto;
import com.wadejerry.scms.modules.pfm.model.PfmParkingLot;
import com.wadejerry.scms.modules.pfm.model.PfmParkingLotInfo;

public interface PfmParkingLotService {
	/**
	* 获取用户或角色信息
	* @author 
	 * @param listtypeId 
	 * @param flag 
	* @param  @param dto
	* @param  @param iType
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public int getRecordTotal(PageParamsDto paramsDto, List<Integer> listtypeId, Integer flag);
	/**
	* 分页获取角色信息
	* @author 
	 * @param flag 
	 * @param listtypeId 
	* @param  @param dto
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
		
	public List<ParkingLotDto> getPfmParkingListByPage(PageParamsDto paramsDto, List<Integer> listtypeId, Integer flag);
	//查询车场名称
	public PfmParkingLotInfo findByParkName(String parkName, int constRoleType);
	//添加车场信息
	public void insertParkingDto(PfmParkingLotInfo park);
	//查询修改时车场的编号
	public PfmParkingLotInfo getUserById(Integer pfmParkingLotId);
	//更新修改数据
	public void updateParkingDto(PfmParkingLotInfo park);
	
	public void deleteParkById(Integer pfmParkingLotId);
	
	public void updateAttribution(String parkName,String attribution);
	
	public ParkingLotDto selectByinId(int inId);
	
	public List<ParkingLotDto> selectParkInfo(int i);
	
	public ParkingLotDto selectparkInfo(String parkinglotname);
	
	public int selectMaxParkinglotId();
	
	public int selectMaxID(int commpanyId);
	
	public List<String> selectCarInfoByType(Integer valueOf);
	
	public List<ParkingLotDto> selectentranceId(Integer pfmParkingLotId);
		
	public PfmParkingLot selectByPrimaryKey(int parkId);
	
	public PfmParkingLotInfo selectByCode(String parkingLotCode);
	public int selectSumByInId(Integer inId);
	public int selectSomeSumByInId(Integer inId, Integer pfmParkingLotId);
	public int selectCountByInId(Integer valueOf);
	public int selectSomeCountByInId(Integer inId, Integer pfmParkingLotId);
	
	public List<PfmParkingLot> selectAllByCompanyId(int companyId);
	

}
