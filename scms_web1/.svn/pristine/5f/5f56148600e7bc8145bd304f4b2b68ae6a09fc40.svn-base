package com.wadejerry.scms.modules.acm.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wadejerry.scms.frame.constant.ConstParamLog;
import com.wadejerry.scms.frame.entity.LoginInfo;
import com.wadejerry.scms.frame.entity.OperateResult;
import com.wadejerry.scms.frame.log.LogRecord;
import com.wadejerry.scms.modules.acm.dao.AcmServerMapper;
import com.wadejerry.scms.modules.acm.model.AcmServer;
import com.wadejerry.scms.modules.acm.service.AcmServerService;
import com.wadejerry.scms.modules.device.model.PfmServer;

@Service
public class AcmServerServiceImpl implements AcmServerService {
	@Autowired
	private AcmServerMapper acmServerMapper;
	@Autowired
	private LogRecord logRecord;
	@Override
	public OperateResult checkPort(String id, String ip, String port, String flag) {
		// TODO Auto-generated method stub
		OperateResult o =new OperateResult();
		o.setResult(true);
		if(flag.equals("0")){
		if( acmServerMapper.selectCountByIpANDPort(ip, port, LoginInfo.getCompanyId())>0){
			o.setResult(false);
		}
		}
		else{
			if( acmServerMapper.selectCountByIpANDPortExceptself(Integer.parseInt(id),ip, port, LoginInfo.getCompanyId())>0){
				o.setResult(false);
			}
		}
		 return o;
	}
	@Override
	public OperateResult insertServer(AcmServer server) {
		OperateResult result = new OperateResult(true,"");
		try {
			acmServerMapper.insertSelective(server);
		}catch(Exception e) {
			result.setResult(false);
			result.setMsg("修改失败");
		}
		
			return result;
	}
	@Override
	public OperateResult updateServer(AcmServer server) {
		// TODO Auto-generated method stub
				OperateResult result =new OperateResult(true,"");
				try {
					acmServerMapper.updateByPrimaryKey(server);
				}catch(Exception e) {
					result.setResult(false);
					result.setMsg("修改失败");
				}
			
				 return result;
	}
	@Override
	@Transactional
	public int delServer(List<AcmServer> list) {
		StringBuilder servers = new StringBuilder();
		
		
		for(AcmServer p:list){
			
			acmServerMapper.deleteByPrimaryKey(p.getAcmServerId());
			servers.append(p.getServerName()+";");
		}
		logRecord.recordLog(ConstParamLog.LOG_MODULE_BASIC_PFM_SERVER, ConstParamLog.LOG_OPER_DELETE, ConstParamLog.LOG_TYPE_CONFIG, servers.toString());

		return 1;
	}

}
