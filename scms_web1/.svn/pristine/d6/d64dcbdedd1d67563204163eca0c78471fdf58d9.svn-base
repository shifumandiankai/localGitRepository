package com.wadejerry.scms.modules.pfm.service;

import java.util.List;

import com.wadejerry.scms.frame.entity.OperateResult;
import com.wadejerry.scms.frame.entity.SelectDataDto;
import com.wadejerry.scms.modules.pfm.dto.ZTreeDto;
import com.wadejerry.scms.modules.pfm.model.PfmCarType;
import com.wadejerry.scms.modules.pfm.model.PfmParkingLot;

public interface PfmCarTypeService {
	
		//删除车辆类型
		public OperateResult deleteNode(int companyid,int nodeid);
		//添加车辆类型
		public ZTreeDto insertCarType(PfmCarType p);
		//xiugai
		public OperateResult updateCarType(PfmCarType p);
		//获取车场节点
		public List<ZTreeDto> selectCarTypesTree(int companyid);
//		
//		public List<ZTreeDto>  selectCartypebyLotId(int companyid,int id);
		
		public List<PfmParkingLot> select1PL(int companyid);
		
		public List<PfmCarType> select1CarTypeByLotId(int lotid,int companyid);
		
		public ZTreeDto selectCarTypeById(int id);
		public List<PfmCarType> configParkReport();
		//查询子车畅
		List<SelectDataDto> selectsubParkData(Integer parkId);

}
