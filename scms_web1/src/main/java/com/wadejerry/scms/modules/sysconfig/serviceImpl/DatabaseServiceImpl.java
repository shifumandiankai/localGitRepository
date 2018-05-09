package com.wadejerry.scms.modules.sysconfig.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wadejerry.scms.frame.constant.user.ConstParamSysConfig;
import com.wadejerry.scms.modules.sysconfig.dao.BimSysParamsMapper;
import com.wadejerry.scms.modules.sysconfig.model.BimSysParams;
import com.wadejerry.scms.modules.sysconfig.service.DatabaseService;

@Service
public class DatabaseServiceImpl implements DatabaseService {
	@Autowired
	private BimSysParamsMapper bspm;

	@Override
	@Transactional
	public int updateDatabaseSettings(Map<String, Object> map) {
		List<BimSysParams> list = new ArrayList<>();
		BimSysParams sysparams = null;
		//maxFileQuantity
		sysparams = new BimSysParams();
		sysparams.setBimSysParamsId(1);
		sysparams.setIntValue(Integer.parseInt(map.get("maxFileQuantity").toString()));
		list.add(sysparams);
		//maxFileTotalSize
		sysparams = new BimSysParams();
		sysparams.setBimSysParamsId(2);
		sysparams.setIntValue(Integer.parseInt(map.get("maxFileTotalSize").toString()));
		list.add(sysparams);
		//planType
		sysparams = new BimSysParams();
		sysparams.setBimSysParamsId(3);
		sysparams.setIntValue(Integer.parseInt(map.get("planType").toString()));
		list.add(sysparams);
		//bankUpStartTime
		sysparams = new BimSysParams();
		sysparams.setBimSysParamsId(4);
		sysparams.setStrValue(map.get("bankUpStartTime").toString());
		list.add(sysparams);
		//bankUpEndTime
		sysparams = new BimSysParams();
		sysparams.setBimSysParamsId(5);
		sysparams.setStrValue(map.get("bankUpEndTime").toString());
		list.add(sysparams);
		//bankUpExecuteTime
		sysparams = new BimSysParams();
		sysparams.setBimSysParamsId(6);
		sysparams.setStrValue(map.get("bankUpExecuteTime").toString());
		list.add(sysparams);
		//
		switch (map.get("planType").toString()) {
		case "1": {
			sysparams=new BimSysParams();
			sysparams.setBimSysParamsId(7);
			sysparams.setIntValue(Integer.parseInt(map.get("dayPlanExecuteInterval").toString()));
			list.add(sysparams);

		}
			break;
		case "2": {
			sysparams=new BimSysParams();
			sysparams.setBimSysParamsId(8);
			StringBuffer buffer = new StringBuffer(); 
			for(String s:(List<String>)map.get("weekPlanExecuteTime")){
				buffer.append(s).append(",");
			}
			sysparams.setStrValue(buffer.toString());
			list.add(sysparams);

		}
			break;
		case "3": {
			sysparams=new BimSysParams();
			sysparams.setBimSysParamsId(9);
			StringBuffer buffer = new StringBuffer(); 
			for(String s:(List<String>)map.get("monthPlanExecuteTime")){
				buffer.append(s).append(",");
			}
			sysparams.setStrValue(buffer.toString());
			list.add(sysparams);

		}
			break;
		case "4": {
			sysparams=new BimSysParams();
			sysparams.setBimSysParamsId(10);
			sysparams.setIntValue(Integer.parseInt(map.get("intervalPlanExecuteTime").toString()));
			list.add(sysparams);
		}
			break;
		}
		
		for(BimSysParams param : list){
			
			bspm.updateByPrimaryKeySelective(param);
		}

		return 0;
	}

	@Override
	public List<BimSysParams> getDatabaseSetting() {
		// TODO Auto-generated method stub
		return bspm.select(ConstParamSysConfig.CONFIG_TYPE_DBMANAGE);
	}

}
