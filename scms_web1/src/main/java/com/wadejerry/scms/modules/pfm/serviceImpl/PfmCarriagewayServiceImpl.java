package com.wadejerry.scms.modules.pfm.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wadejerry.scms.modules.pfm.dao.PfmCarriagewayMapper;
import com.wadejerry.scms.modules.pfm.model.PfmCarriageway;
import com.wadejerry.scms.modules.pfm.service.PfmCarriagewayService;
@Service("pfmCarriagewayService")
public class PfmCarriagewayServiceImpl implements PfmCarriagewayService{
    @Autowired
    private PfmCarriagewayMapper pfmCarriagewayMapper;
    
	@Override
	public List<PfmCarriageway> selectInfoByEntranceId(int a, int b, int c) {
		
		return pfmCarriagewayMapper.selectInfoByEntranceId(a,b,c);
	}

	@Override
	public List<PfmCarriageway> selectAllInfoByEntranceId(int id, int companyid) {
		
		return pfmCarriagewayMapper.selectAllInfoByEntranceId(id,companyid);
	}

	@Override
	public PfmCarriageway selectIfoBycarriagewayId(int carriagewayId) {
		
		return pfmCarriagewayMapper.selectIfoBycarriagewayId(carriagewayId);
	}

	@Override
	public PfmCarriageway selectByName(String name, int entranceid, int company) {
		
		return pfmCarriagewayMapper.selectByName(name,entranceid,company);
	}

}
