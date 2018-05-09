package com.wadejerry.scms.modules.pfm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.device.dto.DevicelrpDto;
import com.wadejerry.scms.modules.pfm.dto.BoothDto;
import com.wadejerry.scms.modules.pfm.model.PfmEntrance;
import com.wadejerry.scms.modules.pfm.model.PfmBooth;


public interface PfmBoothService {

	int getRecordTotal(PageParamsDto paramsDto);

	List<BoothDto> getBoothListByPage(PageParamsDto paramsDto);

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
    
	
	//List<DevicelrpDto> selectAllLpr();

}
