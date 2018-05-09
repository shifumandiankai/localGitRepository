package com.wadejerry.scms.modules.sysconfig.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wadejerry.scms.frame.constant.ConstParamLog;
import com.wadejerry.scms.frame.entity.LoginInfo;
import com.wadejerry.scms.frame.entity.OperateResult;
import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.frame.log.LogRecord;
import com.wadejerry.scms.modules.sysconfig.dao.BimNetZoneMapper;
import com.wadejerry.scms.modules.sysconfig.model.BimNetZone;
import com.wadejerry.scms.modules.sysconfig.service.WangyuService;

@Service
public class WangyuServiceImpl implements WangyuService {
	@Autowired
	private BimNetZoneMapper bimNetZoneMapper;
	@Autowired
	private LogRecord logRecord;
	@Override
	public int getWangyuCount(PageParamsDto pageDto) {
		// TODO Auto-generated method stub
		return bimNetZoneMapper.selectCountByTable(pageDto, LoginInfo.getCompanyId());
	}

	@Override
	public List<BimNetZone> getWangyuListByPage(PageParamsDto pageDto) {
		// TODO Auto-generated method stub
		return bimNetZoneMapper.selectByTable(pageDto, LoginInfo.getCompanyId());
	}

	@Override
	public OperateResult insertWangyu(BimNetZone netzone) {
		// TODO Auto-generated method stub
		OperateResult o = new OperateResult();
		o.setResult(true);
		if(bimNetZoneMapper.selectByUrl(netzone, LoginInfo.getCompanyId())>0){
			o.setResult(false);
			o.setMsg("已存在此IP或名称");
			return o;
		}
		bimNetZoneMapper.insertSelective(netzone);
		return o;
	}

	@Override
	public OperateResult updateWangyu(BimNetZone netzone) {
		// TODO Auto-generated method stub
		OperateResult o = new OperateResult();
		o.setResult(true);
		if(bimNetZoneMapper.selectByUrlExceptSelf(netzone, LoginInfo.getCompanyId())>0){
			o.setResult(false);
			o.setMsg("已存在此IP或名称");
			return o;
		}
		bimNetZoneMapper.updateByPrimaryKeySelective(netzone); 
		return  o;
	}

	@Override
	@Transactional
	public int delWangyu(List<BimNetZone> list) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		for(BimNetZone p:list){
			bimNetZoneMapper.deleteByPrimaryKey(p.getNetZoneId());
			sb.append(p.getName()+";");
		}
		logRecord.recordLog(ConstParamLog.LOG_MODULE_BASIC_WANGYU, ConstParamLog.LOG_OPER_DELETE, ConstParamLog.LOG_TYPE_CONFIG, sb.toString());

		return 0;
	}

	@Override
	public List<BimNetZone> getWangyuList() {
		// TODO Auto-generated method stub
		return bimNetZoneMapper.selectByCompanyId(LoginInfo.getCompanyId());
	}

}
