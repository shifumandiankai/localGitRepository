package com.wadejerry.scms.modules.device.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.device.dto.DevicelrpDto;
import com.wadejerry.scms.modules.device.model.Devicelrp;
import com.wadejerry.scms.modules.pfm.dto.ConfigServiceDto;

public interface PfmDevicelrpMapper {
	int getRecordTotal(PageParamsDto paramsDto);

	List<DevicelrpDto> getListByPage(PageParamsDto paramsDto);

	public Devicelrp findByDeviceCode(String devicecode, int companyid);

	public void insertDtoInfo(DevicelrpDto devicelrpDto);

	public void updateDtoInfo(DevicelrpDto devicelrpDto);

	public void deleteById(Integer id);

	List<DevicelrpDto> selectAllLpr();

	public void SaveLpr(@Param("int_channel1") int in1, @Param("out_channel1") int out1, @Param("int_channel2") int in2,
			@Param("out_channel2") int out2, @Param("int_channel3") int in3, @Param("out_channel3") int out3,
			@Param("int_channel4") int in4, @Param("out_channel4") int out4, @Param("id") int id);

	List<ConfigServiceDto> selectConfig();

	public void updateConfig(Integer serverid, Integer boothid, Date date);

	public List<DevicelrpDto> getListPage(PageParamsDto paramsDto);

	DevicelrpDto selectServerInfo(String name);

	public Devicelrp findByDeviceName(String devicename, int companyid);

	public int selectDeviceCountBy(@Param("companyid") Integer companyId, @Param("serverid") Integer serverid,
			@Param("ip") String ip);

	public int selectDeviceCountBy2(@Param("companyid") Integer companyId, @Param("serverid") Integer serverid,
			@Param("ip") String ip, @Param("id") Integer id);

	Devicelrp selectId(Integer valueOf);

	DevicelrpDto findByLpr(Integer screenId);

	void insertByScreenId(Integer pfmDeviceScreenId, Integer integer, Date date);

	void updateByScreenId(Integer pfmDeviceScreenId, Date date);
}
