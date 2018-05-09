package com.wadejerry.scms.modules.pfm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.device.dto.DevicelrpDto;
import com.wadejerry.scms.modules.pfm.model.PfmBooth;

public interface pfmBoothMapper {

	int getRecordTotal(@Param("pageParam")PageParamsDto paramsDto,@Param("entrances")List<Integer> list);

	List getBoothListByPage(@Param("pageParam")PageParamsDto paramsDto,@Param("entrances")List<Integer> list);

	PfmBooth findByBoothName(String boothName, int companyId);

	void insertBoothDto(PfmBooth insertbooth);

	//pfmBooth selectID(String boothName);

	PfmBooth selectID(String parkName, String timeName);

	PfmBooth findByCodeName(String boothCode, int companyId);

	void updateBoothDto(PfmBooth insertbooth);

	void deleteBoothById(Integer pfmBoothId);

	PfmBooth selectAllLpr(int id);

	List<DevicelrpDto> selectAll();

	PfmBooth selectByPfmEntranceId(Integer pfmEntranceId, int commpanyId);

	//DevicelrpDto selectAllLpr();
	
	List<Integer> selectBoothIdByEntranceIds(@Param("entranceId")List<Integer> ids,@Param("companyId")Integer companyId);

}
