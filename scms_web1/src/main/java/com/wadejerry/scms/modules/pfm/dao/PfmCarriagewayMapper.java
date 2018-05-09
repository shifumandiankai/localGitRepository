package com.wadejerry.scms.modules.pfm.dao;

import java.util.List;

import com.wadejerry.scms.modules.pfm.dto.EntranceDto;
import com.wadejerry.scms.modules.pfm.model.PfmCarriageway;

public interface PfmCarriagewayMapper {

	void insertInfo(PfmCarriageway carriageway);
	List<PfmCarriageway> selectInfoByEntranceId(int a,int b,int c);
	void updateInfo(PfmCarriageway carriageway);
	int selectCount(Integer bimCompanyId);
	List<PfmCarriageway> selectAllInfoByEntranceId(int id,int companyid);
	void deleteInfo(Integer id);
	PfmCarriageway selectIfoBycarriagewayId(int carriagewayId);
	PfmCarriageway selectByName(String name,int entranceid,int company);
	void updateInfoAway(EntranceDto dto);
	PfmCarriageway selectDeviceId(int deviceid);
	void updateCarriwayEnabled(EntranceDto dto);
}
