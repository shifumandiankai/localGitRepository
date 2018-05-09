package com.wadejerry.scms.modules.pfm.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wadejerry.scms.frame.entity.LoginInfo;
import com.wadejerry.scms.modules.auth.dto.UserRoleRelDto;
import com.wadejerry.scms.modules.auth.model.BimUser;
import com.wadejerry.scms.modules.pfm.dao.PfmCarriagewayDeviceRelMapper;
import com.wadejerry.scms.modules.pfm.dto.ZEntranceTreeDto;
import com.wadejerry.scms.modules.pfm.model.PfmCarriageway;
import com.wadejerry.scms.modules.pfm.model.PfmCarriagewayDeviceRel;
import com.wadejerry.scms.modules.pfm.model.PfmEntrance;
import com.wadejerry.scms.modules.pfm.service.EntranceService;
import com.wadejerry.scms.modules.pfm.service.PfmCarriagewayService;
import com.wadejerry.scms.utils.AjaxUtil;
@Controller
public class carReportController {
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private EntranceService entranceService;
	@Autowired
	private PfmCarriagewayService pfmCarriagewayService;
	@Autowired
	private PfmCarriagewayDeviceRelMapper pfmCarriagewayDeviceRelMapper;
	
	@RequestMapping("/report/carreport/gettree.do")
	@ResponseBody
	public void GetTree(){
		int companyid = LoginInfo.getCompanyId();
		List<ZEntranceTreeDto> list=new ArrayList<ZEntranceTreeDto>();
		List<ZEntranceTreeDto> list2=new ArrayList<ZEntranceTreeDto>();
		list=entranceService.SelectParkingTree(companyid);
		int listsize=list.size();
		if(listsize==0){
			 AjaxUtil.ajaxWriteObject(list2, response);
			 return ;
		}
		else{
			int k=0,a=0,b=0;
			for(ZEntranceTreeDto dto:list){
				List<PfmEntrance> entrancelist=entranceService.selectByParkid(Integer.valueOf(dto.getId()),LoginInfo.getCompanyId());
				ZEntranceTreeDto treeDto3=new ZEntranceTreeDto();
				//treeDto3.setStrid("p"+dto.getId());
				treeDto3.setId("p"+dto.getId());
				treeDto3.setName(dto.getName());
				treeDto3.setpId("0");
				//treeDto3.setHasPermission(true);
				list2.add(treeDto3);
				for(int i=0;i<entrancelist.size();i++){
					List<PfmCarriageway> CarriagewayDto=pfmCarriagewayService.selectAllInfoByEntranceId(entrancelist.get(i).getPfmEntranceId(),LoginInfo.getCompanyId());
					a++;
					ZEntranceTreeDto treeDto1=new ZEntranceTreeDto();          
					//treeDto1.setStrid("e"+entrancelist.get(i).getPfmEntranceId());
					treeDto1.setId("e"+entrancelist.get(i).getPfmEntranceId());
					treeDto1.setName(entrancelist.get(i).getEntranceName());
					treeDto1.setpId(treeDto3.getId()); 
					list2.add(treeDto1);
					
					if(a==entrancelist.size()){
						a=0;	
					}
					
					for(int j=0;j<CarriagewayDto.size();j++){
						b++;
						//List<PfmCarriagewayDeviceRel> carriageways=pfmCarriagewayDeviceRelMapper.selectByCaId(CarriagewayDto.get(j).getCarriagewayId());
						ZEntranceTreeDto treeDto2=new ZEntranceTreeDto();
						//treeDto2.setStrid("c"+CarriagewayDto.get(j).getCarriagewayId());
						treeDto2.setId("c"+CarriagewayDto.get(j).getCarriagewayId());
						treeDto2.setName(CarriagewayDto.get(j).getCarriagewayName());
						treeDto2.setpId(treeDto1.getId());
						//treeDto2.setHasPermission(treeDto1.isHasPermission());
						list2.add(treeDto2);
						if(b==CarriagewayDto.size()){
							b=0;
						}
					}
				}
			}
			
			
			AjaxUtil.ajaxWriteObject(list2, response);
		
	}
}
}
