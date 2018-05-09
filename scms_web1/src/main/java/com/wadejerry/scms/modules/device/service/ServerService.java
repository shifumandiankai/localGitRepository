package com.wadejerry.scms.modules.device.service;

import java.util.List;
import java.util.Map;

import com.wadejerry.scms.frame.entity.OperateResult;
import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.acm.model.AcmServer;
import com.wadejerry.scms.modules.device.model.PfmServer;

public interface ServerService {
	
public abstract int getServerCount(PageParamsDto pageDto);
public abstract List<? extends Object> getServerListByPage(PageParamsDto pageDto);
public abstract OperateResult insertServer(PfmServer server);
public abstract OperateResult updateServer(PfmServer server);
public abstract int delServer(List<PfmServer> list,List<AcmServer> list2);
public abstract OperateResult checkPort(String id,String ip,String port,String flag);
public List<Map<String,String>> selectAllParkingLot();
public List<PfmServer> selectAllServer();

}
