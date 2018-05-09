package com.wadejerry.scms.modules.device.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wadejerry.scms.frame.entity.LoginInfo;
import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.device.dao.PfmDeviceScreenMapper;
import com.wadejerry.scms.modules.device.dao.PfmDevicelrpMapper;
import com.wadejerry.scms.modules.device.dto.DevicelrpDto;
import com.wadejerry.scms.modules.device.model.Devicelrp;
import com.wadejerry.scms.modules.device.model.PfmDeviceScreen;
import com.wadejerry.scms.modules.device.service.PfmDeviceService;
import com.wadejerry.scms.modules.pfm.dao.PfmCarriagewayDeviceRelMapper;
import com.wadejerry.scms.modules.pfm.dto.ConfigServiceDto;

@Service("pfmDerviceService")
public class PfmDeviceServiceImpl implements PfmDeviceService{
	@Autowired
	private PfmDevicelrpMapper pfmDevicelrpMapper;
	@Autowired
	private PfmCarriagewayDeviceRelMapper carriagewayDeviceRelMapper;
	@Autowired
	private PfmDeviceScreenMapper deviceScreenMapper;
	public int getRecordTotal(PageParamsDto paramsDto) {
		int iTotal=pfmDevicelrpMapper.getRecordTotal(paramsDto);
		return iTotal;
		
	}

	public List<DevicelrpDto> getListByPage(PageParamsDto paramsDto) {
		return pfmDevicelrpMapper.getListByPage(paramsDto);
		 
	}

	public Devicelrp findByDeviceCode(String devicecode, int companyid) {
		
		return pfmDevicelrpMapper.findByDeviceCode(devicecode,companyid);
	}

	public void insertDtoInfo(DevicelrpDto devicelrpDto) {
		pfmDevicelrpMapper.insertDtoInfo(devicelrpDto);
		
	}

	public void updateDtoInfo(DevicelrpDto devicelrpDto) {
		pfmDevicelrpMapper.updateDtoInfo(devicelrpDto);
		
	}
@Transactional
	public void deleteById(Integer id) {
	    StringBuffer sb=new StringBuffer();
	    Devicelrp devicelrp=pfmDevicelrpMapper.selectId(id);
		pfmDevicelrpMapper.deleteById(id);
		if(devicelrp.getPfmScreenId()!=null){
			PfmDeviceScreen deviceScreen=deviceScreenMapper.selectByPrimaryKey(devicelrp.getPfmScreenId());
			//deviceScreen.getDeviceIdArr().split(",");
			String[] arr=deviceScreen.getDeviceIdArr().split(",");
			for(String temp:arr){
				
				if(!temp.equals(String.valueOf(devicelrp.getPfmDeviceLprId()))){
					if(sb.length()>0){
						sb.append(","+temp);	
					}else{
						sb.append(temp);
					}
					
					
				}
				
			}
			if(sb.length()==0){
				deviceScreen.setDeviceIdArr(null);
			}else{
				deviceScreen.setDeviceIdArr(sb.toString());
			}
			
			deviceScreenMapper.updateDeviceArr(deviceScreen);
			
		}
		carriagewayDeviceRelMapper.deleteByDeviceId(id);
	}

	public List<DevicelrpDto> selectAllLpr() {
		return pfmDevicelrpMapper.selectAllLpr();
		 
	}

	public void SaveLpr(int in1, int out1, int in2, int out2, int in3, int out3, int in4, int out4,int id) {
		pfmDevicelrpMapper.SaveLpr(in1,out1,in2,out2,in3,out3,in4,out4,id);
		
	}

	public List<ConfigServiceDto> selectConfig() {
		return pfmDevicelrpMapper.selectConfig();
		
	}

	public void updateConfig(Integer serverid, Integer boothid,Date date) {
		pfmDevicelrpMapper.updateConfig(serverid,boothid,date);
	}

	public List<DevicelrpDto> getListPage(PageParamsDto paramsDto) {
		return pfmDevicelrpMapper.getListPage(paramsDto);
	
		
	}

	@Override
	public DevicelrpDto selectServerInfo(String name) {
		
		return pfmDevicelrpMapper.selectServerInfo(name);
	}

	@Override
	public Devicelrp findByDeviceName(String devicename, int companyid) {
		
		return pfmDevicelrpMapper.findByDeviceName(devicename,companyid);
	}

	@Override
	public boolean ifHasDeviceOnServer(String ip, String serverid) {
		if(pfmDevicelrpMapper.selectDeviceCountBy(LoginInfo.getCompanyId(), Integer.valueOf(serverid), ip)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean ifHasDeviceOnServer2(String ip, String serverid,Integer id) {
		if(pfmDevicelrpMapper.selectDeviceCountBy2(LoginInfo.getCompanyId(), Integer.valueOf(serverid), ip,id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Devicelrp selectId(Integer valueOf) {
		
		return pfmDevicelrpMapper.selectId(valueOf);
	}

	@Override
	public DevicelrpDto findByLpr(Integer screenId) {
		//
		return pfmDevicelrpMapper.findByLpr(screenId);
	}

	/*public void SaveLpr(Integer in1, Integer out1, Integer boothid) {
		
		
		
	}*/

	

}
