package com.wadejerry.scms.modules.pfm.service;

import java.util.List;

import com.wadejerry.scms.modules.pfm.model.PfmCarriageway;

public interface PfmCarriagewayService {
	List<PfmCarriageway> selectInfoByEntranceId(int a,int b,int c);
	List<PfmCarriageway> selectAllInfoByEntranceId(int id,int companyid);
	PfmCarriageway selectIfoBycarriagewayId(int carriagewayId);
	PfmCarriageway selectByName(String name,int entranceid,int company);
}
