package com.wadejerry.scms.modules.device.service;

import java.util.Date;
import java.util.List;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.device.dto.DevicelrpDto;
import com.wadejerry.scms.modules.device.model.Devicelrp;
import com.wadejerry.scms.modules.pfm.dto.ConfigServiceDto;

public interface PfmDeviceService {

	int getRecordTotal(PageParamsDto paramsDto);

	List<DevicelrpDto> getListByPage(PageParamsDto paramsDto);

	List<DevicelrpDto> getListPage(PageParamsDto paramsDto);

	public Devicelrp findByDeviceCode(String devicecode, int companyid);

	public void insertDtoInfo(DevicelrpDto devicelrpDto);

	public void updateDtoInfo(DevicelrpDto devicelrpDto);

	public void deleteById(Integer id);

	List<DevicelrpDto> selectAllLpr();

	public void SaveLpr(int in1, int out1, int in2, int out2, int in3, int out3, int in4, int out4, int id);

	List<ConfigServiceDto> selectConfig();

	public void updateConfig(Integer integer, Integer integer2, Date date);

	DevicelrpDto selectServerInfo(String name);

	public Devicelrp findByDeviceName(String devicename, int companyid);

	public boolean ifHasDeviceOnServer(String ip, String serverid);

	public boolean ifHasDeviceOnServer2(String ip, String serverid, Integer id);

	Devicelrp selectId(Integer valueOf);

	DevicelrpDto findByLpr(Integer screenid);

}
