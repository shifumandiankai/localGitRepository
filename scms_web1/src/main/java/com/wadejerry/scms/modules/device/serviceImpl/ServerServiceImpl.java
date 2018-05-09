package com.wadejerry.scms.modules.device.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wadejerry.scms.frame.constant.ConstParamLog;
import com.wadejerry.scms.frame.constant.user.ConstSystem;
import com.wadejerry.scms.frame.entity.LoginInfo;
import com.wadejerry.scms.frame.entity.OperateResult;
import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.frame.log.LogRecord;
import com.wadejerry.scms.modules.acm.dao.AcmServerMapper;
import com.wadejerry.scms.modules.acm.model.AcmServer;
import com.wadejerry.scms.modules.acm.service.AcmServerService;
import com.wadejerry.scms.modules.device.dao.PfmServerMapper;
import com.wadejerry.scms.modules.device.model.PfmServer;
import com.wadejerry.scms.modules.device.service.ServerService;

@Service("serverService")
public class ServerServiceImpl implements ServerService {
	@Autowired
	private PfmServerMapper pfmServerMapper;
	@Autowired
	private AcmServerMapper acmServerMapper;
	@Autowired
	private LogRecord logRecord;
	@Override
	public int getServerCount(PageParamsDto pageDto) {
		// TODO Auto-generated method stub
		int result = 0;
		for(Integer temp : LoginInfo.getSubSystem()) {
			if(temp.equals(ConstSystem.SUB_SYSTEM_CODE_PFM)) {
				result+=pfmServerMapper.selectCountByTable(pageDto, LoginInfo.getCompanyId());
				
			}
			if( temp.equals(ConstSystem.SUB_SYSTEM_CODE_ACM) ) {////////////////////////////TO MODIFY/////////////////
				result+=acmServerMapper.selectCountByTable(pageDto, LoginInfo.getCompanyId());
			}
		}
		return result;
	}

	@Override
	public List<? extends Object> getServerListByPage(PageParamsDto pageDto) {
		List<Object> result = new ArrayList<>();
		for(Integer temp : LoginInfo.getSubSystem()) {
			if(temp.equals(ConstSystem.SUB_SYSTEM_CODE_PFM)) {
				
				result.addAll(pfmServerMapper.selectByTable(pageDto,  LoginInfo.getCompanyId()));
			}
			if( temp.equals(ConstSystem.SUB_SYSTEM_CODE_ACM) ) {/////////////TO MODIFY////////////////////////
				result.addAll(acmServerMapper.selectByTable(pageDto,  LoginInfo.getCompanyId()));
			}
		}
		return result;
	}

	@Override
	public OperateResult insertServer(PfmServer server) {
		// TODO Auto-generated method stub
		OperateResult result = new OperateResult(true,"");
		if(pfmServerMapper.selectParkLotByparkId(LoginInfo.getCompanyId(), server.getParkId())==0)
		{
		pfmServerMapper.insertSelective(server);
		}else{
			result.setResult(false);
			result.setMsg("当前所选车场已绑定服务器，请重新选择车场");
		}
		
		return result;
	}

	@Override
	@Transactional
	public int delServer(List<PfmServer> list,List<AcmServer> list2) {
		// TODO Auto-generated method stub
		StringBuilder pfmservers = new StringBuilder();
		StringBuilder acmservers = new StringBuilder();
		//
		for(PfmServer p:list){
			
			if(pfmServerMapper.checkIfDeviceRelated(p.getPfmServerId())>0) {
				return 0;
			}
		}
		
		for(PfmServer p:list){
			
			pfmServerMapper.deleteByPrimaryKey(p.getPfmServerId());
			pfmservers.append(p.getServerName()+";");
		}
		for(AcmServer p:list2){
			
			acmServerMapper.deleteByPrimaryKey(p.getAcmServerId());
			acmservers.append(p.getServerName()+";");
		}
		
		logRecord.recordLog(ConstParamLog.LOG_MODULE_BASIC_PFM_SERVER, ConstParamLog.LOG_OPER_DELETE, ConstParamLog.LOG_TYPE_CONFIG, pfmservers.toString());

		//TODO acm日志
		
		
		return 1;
	}

	@Override
	public OperateResult updateServer(PfmServer server) {
		// TODO Auto-generated method stub
		OperateResult result =new OperateResult(true,"");
		if(pfmServerMapper.selectParkLotByparkIdExceptSelf(LoginInfo.getCompanyId(), server.getParkId(),server.getPfmServerId())==0)
		{
		 pfmServerMapper.updateByPrimaryKey(server);
		}else{
			result.setResult(false);
			result.setMsg("修改失败，所选车场已绑定服务器，请重新选择车场");
		}
		 return result;
	}

	@Override
	public OperateResult checkPort(String id,String ip, String port,String flag) {
		// TODO Auto-generated method stub
		OperateResult o =new OperateResult();
		o.setResult(true);
		if(flag.equals("0")){
		if( pfmServerMapper.selectCountByIpANDPort(ip, port, LoginInfo.getCompanyId())>0){
			o.setResult(false);
		}
		}
		else{
			if( pfmServerMapper.selectCountByIpANDPortExceptself(Integer.parseInt(id),ip, port, LoginInfo.getCompanyId())>0){
				o.setResult(false);
			}
		}
		 return o;
	}

	@Override
	public List<Map<String, String>> selectAllParkingLot() {
		// TODO Auto-generated method stub
		List<Map<String,String>> a =pfmServerMapper.selectAllTopLot(LoginInfo.getCompanyId());
		return  a;
	}

	@Override
	public List<PfmServer> selectAllServer() {
		return pfmServerMapper.selectAllServer();
	}

}
