package com.wadejerry.scms.modules.pfm.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wadejerry.scms.frame.entity.LoginInfo;
import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.auth.dao.BimUserMapper;
import com.wadejerry.scms.modules.auth.dto.RoleDto;
import com.wadejerry.scms.modules.pfm.dto.ParkingLotDto;
import com.wadejerry.scms.modules.pfm.model.PfmEntrance;
import com.wadejerry.scms.modules.pfm.model.PfmParkingLot;
import com.wadejerry.scms.modules.pfm.model.PfmCarInfo;
import com.wadejerry.scms.modules.pfm.model.PfmCarType;
import com.wadejerry.scms.modules.pfm.model.PfmCarTypeRel;
import com.wadejerry.scms.modules.pfm.model.PfmCarriageway;
import com.wadejerry.scms.modules.pfm.model.PfmCarriagewayDeviceRel;
import com.wadejerry.scms.modules.pfm.model.PfmParkingLotInfo;
import com.wadejerry.scms.modules.pfm.dao.PfmCarInfoMapper;
import com.wadejerry.scms.modules.pfm.dao.PfmCarTypeMapper;
import com.wadejerry.scms.modules.pfm.dao.PfmCarTypeRelMapper;
import com.wadejerry.scms.modules.pfm.dao.PfmCarriagewayDeviceRelMapper;
import com.wadejerry.scms.modules.pfm.dao.PfmCarriagewayMapper;
import com.wadejerry.scms.modules.pfm.dao.PfmEntranceMapper;
import com.wadejerry.scms.modules.pfm.dao.PfmParkingLotMapper;
import com.wadejerry.scms.modules.pfm.service.PfmParkingLotService;
@Service("pfmParkingLotService")
public class PfmParkingLotServiceImpl implements PfmParkingLotService {
	@Autowired
	private PfmParkingLotMapper pfmParkingLotMapper;
	@Autowired 
	private PfmCarriagewayMapper carriagewayMapper;
	@Autowired 
	private PfmEntranceMapper entranceMapper;
	@Autowired
	private PfmCarriagewayDeviceRelMapper carriagewayDeviceRelMapper;
	@Autowired
	private PfmCarTypeMapper cartypeMapper;
	@Autowired
	private PfmCarTypeRelMapper carTypeRelMapper;
	@Autowired
	private PfmCarInfoMapper carInfoMapper;

	public int getRecordTotal(PageParamsDto paramsDto,List<Integer> listtypeId,Integer flag) {
		int iTotal = pfmParkingLotMapper.selectRecordTotal(paramsDto,listtypeId,flag);
		return iTotal;
	}

	public List<ParkingLotDto> getPfmParkingListByPage(PageParamsDto paramsDto,List<Integer> listtypeId,Integer flag) {
		List<ParkingLotDto> roleDtoList = pfmParkingLotMapper.selectRoleListByPage(paramsDto,listtypeId,flag);
		return roleDtoList;
	}

	public PfmParkingLotInfo findByParkName(String parkName, int constRoleType) {
		List<PfmParkingLotInfo> listDto=pfmParkingLotMapper.findByParkName(parkName,constRoleType);
		if(listDto.size()>0){
			return listDto.get(0);
		}
		return null ;
	}
	
	@Transactional
	public void insertParkingDto(PfmParkingLotInfo parkinglotDto) {
		pfmParkingLotMapper.insertParkingDto(parkinglotDto);
		for(int i=0;i<4;i++){
			PfmEntrance entrance=new PfmEntrance();
			entrance.setBimCompanyId(parkinglotDto.getBimCompanyId());
			entrance.setEntranceName("出入口"+(i+1));
			entrance.setEnabled(0);
			//entrance.setCustom1(i+1);
			entrance.setCustom1(0);
			entrance.setParkId(parkinglotDto.getPfmParkingLotId());
			entrance.setInsertTime(new Date());
			entranceMapper.insertInfo(entrance);
			for(int j=0;j<4;j++){
				PfmCarriageway carriageway=new PfmCarriageway();
				PfmCarriagewayDeviceRel carriagewayDeviceRel=new PfmCarriagewayDeviceRel();
				//carriageway.setCarriagewayId(number+j+1);
				carriageway.setBimCompanyId(LoginInfo.getCompanyId());
				carriageway.setCarriagewayName("车道"+(j+1));
				carriageway.setEntranceId(entrance.getPfmEntranceId());
				carriageway.setEnabled(0);
				carriageway.setModle(0);
				carriageway.setCarriagewayType(1);
				carriageway.setInOut(0);
				carriageway.setAutoReleaseFixed(0);
				carriageway.setAutoReleaseTemp(0);
				carriageway.setReleaseFixed(0);
				carriageway.setReleaseTemp(0);
				//carriageway.setCustom1(j+1);
				carriageway.setCustom1(0);
				carriageway.setCustom2(30);
				carriageway.setCustom3(0);//军警车辆是否允许通行
				carriageway.setInsertTime(new Date());
				carriagewayMapper.insertInfo(carriageway);
				//carriagewayDeviceRel.setCarriagewayId(carriageway.getCarriagewayId());
				//carriagewayDeviceRelMapper.insertInfo(carriagewayDeviceRel);
				
			}
		}
		if(parkinglotDto.getInId()==null||parkinglotDto.getInId()==0){
		PfmCarType type= new PfmCarType();
		type.setCustom1(parkinglotDto.getPfmParkingLotId());
		type.setStatus((short)0);
		type.setInsertTime(new Date());
		type.setTypeName(parkinglotDto.getParkingLotName());
		type.setTypeCode("");
		type.setUpdateTime(new Date());
		type.setUserName(LoginInfo.getLoginName());
		type.setBimCompanyId(LoginInfo.getCompanyId());
		type.setCarFull(0);
		type.setMaxstop(1);
		cartypeMapper.insertSelective(type);
		}
	}

	public PfmParkingLotInfo getUserById(Integer pfmParkingLotId) {
		return pfmParkingLotMapper.getUserById(pfmParkingLotId);
		
	}
	
	public void updateParkingDto(PfmParkingLotInfo park) {
		pfmParkingLotMapper.updateParkingDto(park);
	}
	
	@Transactional
	public void deleteParkById(Integer pfmParkingLotId) {
		pfmParkingLotMapper.deleteParkById(pfmParkingLotId);
		List<PfmCarType> listType=cartypeMapper.selectByCustom1(pfmParkingLotId);
		//
		if(listType.size()>0){
			for(PfmCarType temp:listType){
				List<PfmCarTypeRel> listrel=carTypeRelMapper.selectByCarTypeId(temp.getCarTypeId());
				if(listrel.size()>0){
					for(PfmCarTypeRel temprel:listrel){
						carTypeRelMapper.deleteByCarTypeId(temprel.getCarTypeId());
						if(temprel.getCarInfoId()!=null){
							carInfoMapper.deleteByPrimaryKey(temprel.getCarInfoId());
						}
						
						
					}
				}
			}
		}
		cartypeMapper.deleteCartType(pfmParkingLotId);
		
		
		
		List<PfmEntrance> list=entranceMapper.selectByParkid(pfmParkingLotId, LoginInfo.getCompanyId());
		if(list.size()!=0){
			for(PfmEntrance entrance:list){
				
				List<PfmCarriageway> carriageways=carriagewayMapper.selectAllInfoByEntranceId(entrance.getPfmEntranceId(), LoginInfo.getCompanyId());
				for(PfmCarriageway carriageway:carriageways){
					carriagewayDeviceRelMapper.deleteInfo(carriageway.getCarriagewayId());
				}
				entranceMapper.deleteInfo(entrance);
				
				carriagewayMapper.deleteInfo(entrance.getPfmEntranceId());
			}
		}
		
		
	}

	public void updateAttribution(String parkName,String attribution) {
		pfmParkingLotMapper.updateAttribution(parkName,attribution);
		
	}

	@Override
	public ParkingLotDto selectByinId(int inId) {
		return pfmParkingLotMapper.selectByinId(inId);
		 
	}

	@Override
	public List<ParkingLotDto> selectParkInfo(int i) {
		return pfmParkingLotMapper.selectParkInfo(i);
	}

	@Override
	public ParkingLotDto selectparkInfo(String parkinglotname) {
		
		return pfmParkingLotMapper.selectparkInfo(parkinglotname);
	}

	@Override
	public int selectMaxParkinglotId() {
		
		return pfmParkingLotMapper.selectMaxParkinglotId();
	}

	@Override
	public int selectMaxID(int commpanyId) {
		
		return pfmParkingLotMapper.selectMaxID(commpanyId);
	}

	@Override
	public List<String> selectCarInfoByType(Integer parkId) {
		
		return pfmParkingLotMapper.selectCarInfoByType(parkId);
	}


	@Override
	public List<ParkingLotDto> selectentranceId(Integer pfmParkingLotId) {
		
		return pfmParkingLotMapper.selectentranceId(pfmParkingLotId);
	}


	@Override
	public PfmParkingLot selectByPrimaryKey(int parkId) {
		return pfmParkingLotMapper.selectByPrimaryKey(parkId);
	}

	@Override
	public PfmParkingLotInfo selectByCode(String parkingLotCode) {
		return pfmParkingLotMapper.selectByCode(parkingLotCode);
	}

	@Override
	public List<PfmParkingLot> selectAllByCompanyId(int companyId) {
		return pfmParkingLotMapper.selectAllbyCompanyId(companyId);
	}
	@Override
	public int selectSumByInId(Integer inId) {
		// TODO Auto-generated method stub
		return pfmParkingLotMapper.selectSumByInId(inId);
	}
	
	@Override
	public int selectSomeSumByInId(Integer inId, Integer pfmParkingLotId) {
		// TODO Auto-generated method stub
		return pfmParkingLotMapper.selectSomeSumByInId(inId,pfmParkingLotId);
	}

	@Override
	public int selectCountByInId(Integer inId) {
		// TODO Auto-generated method stub
		return pfmParkingLotMapper.selectCountByInId(inId);
	}

	@Override
	public int selectSomeCountByInId(Integer inId, Integer pfmParkingLotId) {
		// TODO Auto-generated method stub
		return pfmParkingLotMapper.selectSomeCountByInId(inId,pfmParkingLotId);
	}


	
	

}
