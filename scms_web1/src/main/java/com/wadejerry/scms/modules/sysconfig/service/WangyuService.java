package com.wadejerry.scms.modules.sysconfig.service;

import java.util.List;

import com.wadejerry.scms.frame.entity.OperateResult;
import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.sysconfig.model.BimNetZone;

public interface WangyuService {
	public abstract int getWangyuCount(PageParamsDto pageDto);
	public abstract List<BimNetZone> getWangyuListByPage(PageParamsDto pageDto);
	public abstract OperateResult insertWangyu(BimNetZone netzone);
	public abstract OperateResult updateWangyu(BimNetZone netzone);
	public abstract int delWangyu(List<BimNetZone> list);
	public abstract List<BimNetZone> getWangyuList();
}
