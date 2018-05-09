package com.wadejerry.scms.modules.pfm.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wadejerry.scms.frame.constant.ConstParamLog;
import com.wadejerry.scms.frame.entity.DataTableDto;
import com.wadejerry.scms.frame.entity.LoginInfo;
import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.frame.entity.SelectDataDto;
import com.wadejerry.scms.frame.log.LogRecord;
import com.wadejerry.scms.modules.auth.dao.BimUserMapper;
import com.wadejerry.scms.modules.auth.dao.BimUserRoleMapper;
import com.wadejerry.scms.modules.auth.dto.UserRoleRelDto;
import com.wadejerry.scms.modules.auth.model.BimUser;
import com.wadejerry.scms.modules.device.service.PfmDeviceService;
import com.wadejerry.scms.modules.pfm.dto.BoothDto;
import com.wadejerry.scms.modules.pfm.model.PfmEntrance;
import com.wadejerry.scms.modules.pfm.model.PfmBooth;
import com.wadejerry.scms.modules.pfm.service.EntranceService;
import com.wadejerry.scms.modules.pfm.service.PfmBoothService;
import com.wadejerry.scms.modules.pfm.service.PfmTimeService;
import com.wadejerry.scms.utils.AjaxUtil;
import com.wadejerry.scms.utils.Log.LogManager;
import com.wadejerry.scms.utils.json.JacksonUtil;
@Controller
public class BoothControler {
	

@Autowired
private HttpServletRequest request;
@Autowired
private HttpServletResponse response;
@Autowired
private PfmBoothService Boothservice;
@Autowired
private PfmTimeService pfmtimeservice;//Service
@Autowired
private PfmDeviceService pfmDeviceService;
@Autowired
private EntranceService entranceService;
@Autowired
private BimUserRoleMapper bimUserRoleMapper;
@Autowired
private BimUserMapper userMapper;
@Autowired
private LogRecord logRecord;

 @RequestMapping("/booth/getBoothInfo.do")
 @ResponseBody
public void GetBoothInfoByPage(){
	
	PageParamsDto paramsDto = new PageParamsDto(request); // 获取分页参数
	int iTotal=0;
	List<BoothDto> dtolist=null;
	if(!SecurityUtils.getSubject().isPermitted("StaffSee")){
	}else{
		iTotal=Boothservice.getRecordTotal(paramsDto);
	if(iTotal>0){
		if(paramsDto.getLength()==-1){
			paramsDto.setLength(iTotal);  //查询所有记录
		}
		dtolist=Boothservice.getBoothListByPage(paramsDto);
		
	}
	}
	DataTableDto data = new DataTableDto(); // 返回数据封装
	data.setDataList(dtolist);
	data.setDraw(paramsDto.getDraw());
	data.setRecordsFiltered(iTotal);
	data.setRecordsTotal(iTotal);
	AjaxUtil.ajaxWriteDataTable(data, response);
}
 
 @RequestMapping("/booth/saveBoothInfo.do")
 @ResponseBody
public void SaveBoothInfo(){
	   
	    String formData=request.getParameter("condition");
		BoothDto boothDto=JacksonUtil.toObject(formData, BoothDto.class);//获取表单数据
		//PageParamsDto pageParamsDto=new PageParamsDto(request);
		PfmBooth insertbooth=null;
		PfmBooth boothName=Boothservice.findByBoothName(boothDto.getBoothName(),LoginInfo.getCompanyId());//查询岗亭名称是否存在
		PfmBooth boothCode=Boothservice.findByCodeName(boothDto.getBoothCode(),LoginInfo.getCompanyId());//查询岗亭编号是否存在
		//PfmEntrance entrance=entranceService.selectByPfmEntranceId(boothDto.getPfmEntranceId(),LoginInfo.getCompanyId());//根据出入口id和公司id查询pfmentrance表
		PfmBooth booth=Boothservice.selectByPfmEntranceId(boothDto.getPfmEntranceId(),LoginInfo.getCompanyId());//根据出入口id和公司id查询boothid
		if(booth!=null){
			if(booth.getPfmBoothId()!=boothDto.getPfmBoothId()){
				AjaxUtil.ajaxWrite(false, "出入口已被绑定，请重选", response);
				return;
			}
		}
		if(boothDto.getPfmBoothId()==null){//添加功能
			if(boothName!=null){
				AjaxUtil.ajaxWrite(false, "岗亭名称已存在", response);
				return;
			} 
			if(boothCode!=null){
				AjaxUtil.ajaxWrite(false, "岗亭编号已存在", response);
				return;
			} 
			
			    PfmBooth boothID=Boothservice.selectID(boothDto.getParkingLotName(),boothDto.getTimeName());//查询所属车场的时段id和车场id
			    insertbooth=new PfmBooth();
			    if(boothID!=null){
			    	 insertbooth.setPfmParkLotId(boothID.getPfmParkLotId());
					 insertbooth.setPfmTimeId(boothID.getPfmTimeId());
			    }
			   /* if(boothDto.getCenterCharge()==null){
			    	insertbooth.setCenterCharge(0);
			    }else{
			    	insertbooth.setCenterCharge(1);
			    }*/
			    if(boothDto.getResult().equals("是")){
			    	insertbooth.setCenterCharge(0);
			    }else{
			    	insertbooth.setCenterCharge(1);
			    }
			    insertbooth.setBoothName(boothDto.getBoothName());   
			    insertbooth.setPhone(boothDto.getPhone());
			    insertbooth.setClientIp(boothDto.getClientIp());
				insertbooth.setNote(boothDto.getNote());
				insertbooth.setParkingLotName(boothDto.getParkingLotName());
				insertbooth.setTimeName(boothDto.getTimeName());
				
				insertbooth.setInsertTime(new Date());
				//insertbooth.setConsumeConfirm(Integer.valueOf(boothDto.getConsumeConfirmresult()));
				
				//insertbooth.setCenterCharge(Integer.valueOf(boothDto.getCenterChargeresult()));
				/*if(boothDto.getResult().equals("是")){
					insertbooth.setCenterCharge(0);
				}
				else{
					insertbooth.setCenterCharge(1);
				}*/
				if(boothDto.getPfmEntranceId()!=0){
					insertbooth.setPfmEntranceId(boothDto.getPfmEntranceId());
				}
				insertbooth.setBoothCode(boothDto.getBoothCode());
				Boothservice.insertBoothDto(insertbooth);
				
				logRecord.recordLog(ConstParamLog.LOG_MODULE_PFM_BOOTH, ConstParamLog.LOG_OPER_ADD,  ConstParamLog.LOG_TYPE_CONFIG,boothDto.getBoothName());
}
		//修改功能
		else{
			//System.out.println(boothDto.getPfmBoothId());
			//System.out.println(boothCode.getPfmBoothId());
			if(boothName!=null&&boothName.getPfmBoothId()!=boothDto.getPfmBoothId()){
				AjaxUtil.ajaxWrite(false, "岗亭名称已使用", response);
				return;
			}
			
			if(boothCode!=null&&boothCode.getPfmBoothId()!=boothDto.getPfmBoothId()){
				AjaxUtil.ajaxWrite(false, "岗亭编号已使用", response);
				return;
			}
		
			PfmBooth boothID=Boothservice.selectID(boothDto.getParkingLotName(),boothDto.getTimeName());//查询所属车场的时段id和车场id
			insertbooth=new PfmBooth();
			 if(boothID!=null){
		    	 insertbooth.setPfmParkLotId(boothID.getPfmParkLotId());
				 insertbooth.setPfmTimeId(boothID.getPfmTimeId());
		    }
			 if(boothDto.getResult().equals("是")){
			    	insertbooth.setCenterCharge(0);
			    }else{
			    	insertbooth.setCenterCharge(1);
			    }
			insertbooth.setPfmBoothId(boothDto.getPfmBoothId());
		    insertbooth.setBoothName(boothDto.getBoothName());   
		    insertbooth.setPhone(boothDto.getPhone());
		    insertbooth.setClientIp(boothDto.getClientIp());
			//insertbooth.setNote(boothDto.getNote());
			insertbooth.setParkingLotName(boothDto.getParkingLotName());
			//insertbooth.setClientIp(boothDto.getClientIp());
			insertbooth.setNote(boothDto.getNote());
			insertbooth.setTimeName(boothDto.getTimeName());
			insertbooth.setUpdateTime(new Date());
			
			
			if(boothDto.getPfmEntranceId()!=0){
				insertbooth.setPfmEntranceId(boothDto.getPfmEntranceId());
			}
			
			insertbooth.setBoothCode(boothDto.getBoothCode());
			Boothservice.updateBoothDto(insertbooth);
			logRecord.recordLog(ConstParamLog.LOG_MODULE_PFM_BOOTH, ConstParamLog.LOG_OPER_UPDATE,  ConstParamLog.LOG_TYPE_CONFIG,boothDto.getBoothName());
		}
		AjaxUtil.ajaxWrite(true, "", response);
}
 /**
	* 获取角色下拉框数据
	* 
	* @param   
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
/* @RequestMapping("/booth/GetBoothSelectData.do")
 @ResponseBody
 private void GetTimeSelectData(){
	 
	 PageParamsDto paramsDto = new PageParamsDto();
		//paramsDto.setIntValue1(ConstUser.CONST_ROLE_TYPE); // 角色类型
		paramsDto.setStart(0);//开始记录数
		paramsDto.setSearchValue0("");
		int iTotal=pfmtimeservice.getRecordTotal(paramsDto);//获取记录总数
		
		List<TimeDto> timedtoList = new ArrayList<TimeDto>();
		
		if (iTotal > 0) {
			paramsDto.setLength(iTotal);
			timedtoList=pfmtimeservice.getTimeListByPage(paramsDto);
			
		}
		List<SelectDataDto> list = new ArrayList<SelectDataDto>(); 
		SelectDataDto selectDataDto = null;
		SelectDataDto select= new SelectDataDto();
		//如果pfm_time表中没有时段数据
		if(iTotal==0){
			selectDataDto=new SelectDataDto();
			selectDataDto.setText("无时段选择");
			list.add(selectDataDto);
			AjaxUtil.ajaxWriteObject(list, response);
			return ;
		}
	
		for(TimeDto timeDto : timedtoList){	//封装Select2 插件数据类型
			
				selectDataDto = new  SelectDataDto();
				if(timeDto.getTimeName()!=null&&!timeDto.getTimeName().equals("")){
					selectDataDto.setText(timeDto.getTimeName());	
					list.add(selectDataDto);
				}
			
			
		}
		select.setText("无");
		list.add(select);
		AjaxUtil.ajaxWriteObject(list, response);
 }*/
 /**
  * 删除信息
 * @throws JSONException 
  */
 @RequestMapping("/Booth/delBoothInfo.do")
 @ResponseBody
 private void DelTimeInfo() throws JSONException{
	 String Array=request.getParameter("condition");
	 JSONArray jsonArray = new JSONArray(Array);
	 for (int i = 0; i < jsonArray.length(); i++) {
		 JSONObject jsonObject = jsonArray.getJSONObject(i);
		 String str=jsonObject.get("centerCharge").toString();
		 if(str.equals("是")){
			 jsonObject.put("centerCharge",0);
		 }else{
			 jsonObject.put("centerCharge",1);
		 }
		 jsonArray.put(i, jsonObject);
	 }
	 String jsonArrayToString = jsonArray.toString();
	 try {
		List<BoothDto> delDtoList=JacksonUtil.json2list(jsonArrayToString, BoothDto.class);
		for (BoothDto BoothDto : delDtoList) {

			Boothservice.deleteBoothById(BoothDto.getPfmBoothId());
			logRecord.recordLog(ConstParamLog.LOG_MODULE_PFM_BOOTH, ConstParamLog.LOG_OPER_DELETE,  ConstParamLog.LOG_TYPE_CONFIG,BoothDto.getBoothName());
			
		}
	} catch (Exception e) {
		LogManager.logException(e);
		AjaxUtil.ajaxWrite(false, e.getMessage(), response); // 返回失败
		return;
	}
	 AjaxUtil.ajaxWrite(true, "", response); // 返回成功
 }
 
 
 /*
  * 绑定出入口 
  */
 @RequestMapping("/booth/GetEntranceSelectData.do")
 @ResponseBody
 private void GetEntranceSelectData(){
	 
	 //权限
	 List<UserRoleRelDto> userroles=	bimUserRoleMapper.selectUserRoleDtoByUserId(LoginInfo.getLoginId());
		List<Integer> entrances =new ArrayList();
		for(UserRoleRelDto temp:userroles){
			BimUser user = userMapper.selectByPrimaryKey(temp.getRoleId());
			if(user!=null){
				if(user.getCustom4()!=null&&user.getCustom4().length()!=0){
					List<String> list=Arrays.asList(user.getCustom4().split(","));
					for(String temp1:list){
						temp1=temp1.substring(1);
						entrances.add(Integer.parseInt(temp1));
					}
				}
			}

		}
	 List<PfmEntrance> listDto=entranceService.selectInfoForPfmEntranceId(LoginInfo.getCompanyId());
		SelectDataDto selectDto=null;
		SelectDataDto selectDto2=null;
		List<SelectDataDto> list=new ArrayList<SelectDataDto>();
		if(listDto.size()==0){
			selectDto=new SelectDataDto();
			selectDto.setId(-1);
			selectDto.setText("无出入口可选");
			list.add(selectDto);
			AjaxUtil.ajaxWriteObject(list, response);
			return ;
		}
		else{
			selectDto2=new SelectDataDto();
			selectDto2.setId(0);
			selectDto2.setText("无");
			list.add(selectDto2);
			for(PfmEntrance entrance:listDto){
				if(!entrance.getEntranceName().equals("(已禁用)")){
					selectDto=new SelectDataDto();
					selectDto.setId(entrance.getPfmEntranceId());
					selectDto.setText(entrance.getEntranceName());
					if(LoginInfo.isCompanyManager()||entrances.contains(selectDto.getId())){
					list.add(selectDto);
					}
				}
				
			   }
			AjaxUtil.ajaxWriteObject(list, response);
		}
	 
 }
 
}
