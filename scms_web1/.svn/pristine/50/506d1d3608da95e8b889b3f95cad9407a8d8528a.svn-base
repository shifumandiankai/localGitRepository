package com.wadejerry.scms.modules.pfm.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wadejerry.scms.frame.entity.LoginInfo;
import com.wadejerry.scms.modules.pfm.dao.PfmCarriagewayDeviceRelMapper;
import com.wadejerry.scms.modules.pfm.dao.PfmCarriagewayMapper;
import com.wadejerry.scms.modules.pfm.dao.PfmEntranceMapper;
import com.wadejerry.scms.modules.pfm.dto.EntranceDto;
import com.wadejerry.scms.modules.pfm.dto.ZEntranceTreeDto;
import com.wadejerry.scms.modules.pfm.dto.ZTreeDto;
import com.wadejerry.scms.modules.pfm.model.PfmCarriageway;
import com.wadejerry.scms.modules.pfm.model.PfmCarriagewayDeviceRel;
import com.wadejerry.scms.modules.pfm.model.PfmEntrance;
import com.wadejerry.scms.modules.pfm.service.EntranceService;
import com.wadejerry.scms.utils.AjaxUtil;
@Service("entranceService")
public class EntranceServiceImpl implements EntranceService{
@Autowired
private  PfmEntranceMapper pfmEntranceMapper;
@Autowired
private PfmCarriagewayMapper pfmCarriagewayMapper;
@Autowired
private PfmCarriagewayDeviceRelMapper pfmCarriagewayDeviceRelMapper;
@Autowired
private HttpServletResponse response;
@Override
public List<ZEntranceTreeDto> SelectParkingTree(int companyid) {
	return pfmEntranceMapper.SelectParkingTree(companyid);
	 
}


@Override
public List<PfmEntrance> selectInfoByParkId(int parkid, int companyid, Integer id) {
	return pfmEntranceMapper.selectInfoByParkId(parkid,companyid,id);
}


@Override
public PfmEntrance selectByName(String name, Integer id,Integer parkid,Integer custom1) {
	return pfmEntranceMapper.selectByName(name,id,parkid,custom1);
}


@Override
public PfmEntrance selectEntranceName(String name, Integer id,Integer parkid) {
	
	return pfmEntranceMapper.selectEntranceName(name,id,parkid);
}


@Transactional
public void updateInfo(EntranceDto dto,HttpServletResponse response) {
	/*List<PfmCarriageway> list=pfmCarriagewayMapper.selectAllInfoByEntranceId(dto.getEntranceId(),LoginInfo.getCompanyId());//查询出入口id的四个车道
	List<PfmCarriageway> listDto=pfmCarriagewayMapper.selectInfoByEntranceId(dto.getEntranceId(),LoginInfo.getCompanyId(),dto.getCustom1());//根据entranceid和车道(1~4)
	for(int i=0;i<list.size();i++){
		PfmCarriagewayDeviceRel carriagewayDeviceRel=pfmCarriagewayDeviceRelMapper.selectDeviceId(list.get(i).getCarriagewayId(),dto.getDeviceId());
		if(carriagewayDeviceRel!=null){
			if(!carriagewayDeviceRel.getCarriagewayId().equals(listDto.get(0).getCarriagewayId())){
				AjaxUtil.ajaxWrite(false, "设备已经被绑定，请重选", response);
				return;
			}
			
		}
	}
	pfmEntranceMapper.updateInfo(dto);
	PfmCarriageway carriageway=new PfmCarriageway();
	carriageway.setBimCompanyId(LoginInfo.getCompanyId());
	carriageway.setCarriagewayName(dto.getCarriagewayName());
	//carriageway.setCarriagewayName(dto.getCarriagewayNamedto());
	carriageway.setEntranceId(dto.getEntranceId());
	carriageway.setEnabled(dto.getAwayenabled());
	carriageway.setModle(dto.getModle());
	//carriageway.setModle(dto.getModledto());
	carriageway.setCarriagewayType(dto.getCarriagewayType());
	//carriageway.setCarriagewayType(dto.getCarriagewayTypedto());
	carriageway.setInOut(dto.getInOut());
	carriageway.setAutoReleaseFixed(dto.getAutoReleaseFixed());
	carriageway.setAutoReleaseTemp(dto.getAutoReleaseTemp());
	carriageway.setReleaseFixed(dto.getReleaseFixed());
	carriageway.setReleaseTemp(dto.getReleaseTemp());
	carriageway.setUpdateTime(dto.getUpdateTime());
	carriageway.setCustom1(dto.getCustom1());
	pfmCarriagewayMapper.updateInfo(carriageway);
	
	
	PfmCarriagewayDeviceRel carriagewayDeviceRel=new PfmCarriagewayDeviceRel();
	carriagewayDeviceRel.setCarriagewayId(listDto.get(0).getCarriagewayId());
	carriagewayDeviceRel.setUpdateTime(dto.getUpdateTime());
	carriagewayDeviceRel.setDeviceId(dto.getDeviceId());
	pfmCarriagewayDeviceRelMapper.update(carriagewayDeviceRel);	*/
	
}

@Override
public List<PfmEntrance> selectByParkid(int parkid, int companyid) {
	
	return pfmEntranceMapper.selectByParkid(parkid,companyid);
}


@Override
public List<PfmEntrance> selectInfoByPfmEntranceId(int pfmEntranceId) {
	
	return pfmEntranceMapper.selectInfoByPfmEntranceId(pfmEntranceId);
}


@Transactional
public void updateInfoNoAway(EntranceDto dto) {
	pfmEntranceMapper.updateInfoNoAway(dto);
	pfmCarriagewayMapper.updateCarriwayEnabled(dto);
}


@Override
public PfmEntrance selectByPfmEntranceId(int pfmentranceid, int companyid) {
	
	return pfmEntranceMapper.selectByPfmEntranceId(pfmentranceid,companyid);
}


@Override
public PfmEntrance selectName(String name, int parkid, int companyid) {
	
	return pfmEntranceMapper.selectName(name,parkid,companyid);
}


@Transactional
public void updateInfoAway(EntranceDto dto) {
	pfmEntranceMapper.updateInfoNoAway(dto);
	pfmCarriagewayMapper.updateInfoAway(dto);
	Date time=new Date();
	List<PfmCarriagewayDeviceRel> listDto=pfmCarriagewayDeviceRelMapper.selectByCaId(dto.getCarriagewayId());//查询设备关联表中车道id个数
	if(!dto.getDeviceId().get(0).equals("")){
		//有设备绑定
		for(String temp:dto.getDeviceId()){
			if(!temp.equals("0")){
				PfmCarriagewayDeviceRel carriagewayDeviceRel=pfmCarriagewayDeviceRelMapper.selectDeviceId(Integer.valueOf(temp));
				if(carriagewayDeviceRel!=null){
					if(!carriagewayDeviceRel.getCarriagewayId().equals(dto.getCarriagewayId())){
						AjaxUtil.ajaxWrite(false, "设备已经被绑定，请重选", response);
						return;
					}
				}
			}
			/*PfmCarriagewayDeviceRel carriagewayDeviceRel=pfmCarriagewayDeviceRelMapper.selectDeviceId(Integer.valueOf(temp));
			if(carriagewayDeviceRel!=null){
				if(!carriagewayDeviceRel.getCarriagewayId().equals(dto.getCarriagewayId())){
					AjaxUtil.ajaxWrite(false, "设备已经被绑定，请重选", response);
					return;
				}
			}*/
		}
		
		if(listDto.size()!=0){
			//先删除
			
			pfmCarriagewayDeviceRelMapper.deleteInfo(dto.getCarriagewayId());
		}
			for(String temp:dto.getDeviceId()){
				PfmCarriagewayDeviceRel deviceRel=new PfmCarriagewayDeviceRel();
				deviceRel.setDeviceId(Integer.valueOf(temp));
				deviceRel.setCarriagewayId(dto.getCarriagewayId());
				deviceRel.setUpdateTime(time);
				pfmCarriagewayDeviceRelMapper.insertInfo(deviceRel);
			}
	}
	else{
		//无设备绑定
		pfmCarriagewayDeviceRelMapper.deleteInfo(dto.getCarriagewayId());
		
	}
		
		
		
	
	
	
	
	
	//在添加
	//pfmCarriagewayDeviceRelMapper
	/*for(String temp:dto.getDeviceId()){
		PfmCarriagewayDeviceRel carriagewayDeviceRel=new PfmCarriagewayDeviceRel();
		carriagewayDeviceRel.setCarriagewayId(dto.getCarriagewayId());
		carriagewayDeviceRel.setDeviceId(Integer.valueOf(temp));
		carriagewayDeviceRel.setUpdateTime(new Date());
		pfmCarriagewayDeviceRelMapper.insertAllInfo(carriagewayDeviceRel);
	}*/
	//pfmCarriagewayDeviceRelMapper.inser
}


@Override
public List<PfmEntrance> selectInfoForPfmEntranceId(int companyid) {
	
	return pfmEntranceMapper.selectInfoForPfmEntranceId(companyid);
}


@Override
public void updateEntranceType(EntranceDto dto) {
	pfmEntranceMapper.updateEntranceType(dto);
	
}


@Override
public List<PfmEntrance> selectentranceId(Integer pfmParkingLotId) {
	
	return pfmEntranceMapper.selectentranceId(pfmParkingLotId);
}


@Override
public List<ZEntranceTreeDto> SelectParkingTreeByType(int companyid, List<Integer> listtypeId, Integer flag) {
	
	return pfmEntranceMapper.SelectParkingTreeByType(companyid,listtypeId,flag);
}


@Override
public List<PfmEntrance> selectByParkidByType(Integer id, int companyId, List<Integer> listtypeId, Integer flag, List<Integer> listentranceId) {
	
	return pfmEntranceMapper.selectByParkidByType(id,companyId,listtypeId,flag,listentranceId);
}













}
