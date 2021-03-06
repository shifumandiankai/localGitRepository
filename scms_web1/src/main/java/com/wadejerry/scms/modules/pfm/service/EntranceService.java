package com.wadejerry.scms.modules.pfm.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;


import com.wadejerry.scms.modules.pfm.dto.EntranceDto;
import com.wadejerry.scms.modules.pfm.dto.ZEntranceTreeDto;
import com.wadejerry.scms.modules.pfm.dto.ZTreeDto;
import com.wadejerry.scms.modules.pfm.model.PfmEntrance;

public  interface EntranceService {
	List<ZEntranceTreeDto> SelectParkingTree(int companyid);
	List<PfmEntrance> selectInfoByParkId(int parkid,int companyid, Integer id);
	PfmEntrance selectByName(String name,Integer id, Integer parkid, Integer custom1);
	PfmEntrance  selectEntranceName(String name,Integer id, Integer parkid);
	void updateInfo(EntranceDto dto, HttpServletResponse response);
	List<PfmEntrance> selectByParkid(int parkid, int i);
	List<PfmEntrance> selectInfoByPfmEntranceId(int pfmEntranceId);
	void updateInfoNoAway(EntranceDto dto);
	PfmEntrance selectByPfmEntranceId(int pfmentranceid,int companyid);
	PfmEntrance selectName(String name,int parkid,int companyid);
	void updateInfoAway(EntranceDto dto);
	List<PfmEntrance> selectInfoForPfmEntranceId(int companyid);
	void updateEntranceType(EntranceDto dto);
	List<PfmEntrance> selectentranceId(Integer pfmParkingLotId);
	List<ZEntranceTreeDto> SelectParkingTreeByType(int companyid, List<Integer> listtypeId, Integer flag);
	List<PfmEntrance> selectByParkidByType(Integer valueOf, int companyId, List<Integer> listtypeId, Integer flag, List<Integer> listentranceId);
}
