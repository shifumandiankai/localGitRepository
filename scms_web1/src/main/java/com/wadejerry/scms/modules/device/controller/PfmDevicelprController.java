package com.wadejerry.scms.modules.device.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wadejerry.scms.frame.constant.ConstParamLog;
import com.wadejerry.scms.frame.entity.DataTableDto;
import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.frame.entity.SelectDataDto;
import com.wadejerry.scms.frame.log.LogRecord;
import com.wadejerry.scms.modules.device.dto.DevicelrpDto;
import com.wadejerry.scms.modules.device.model.Devicelrp;
import com.wadejerry.scms.modules.device.service.PfmDeviceService;
import com.wadejerry.scms.modules.pfm.dto.ConfigServiceDto;
import com.wadejerry.scms.utils.AjaxUtil;
import com.wadejerry.scms.utils.Log.LogManager;
import com.wadejerry.scms.utils.json.JacksonUtil;

@Controller
public class PfmDevicelprController {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private PfmDeviceService pfmDeviceService;
	@Autowired
	private LogRecord logRecord;
	/*
	 * 分页查询显示信息
	 */
	@RequestMapping("/devicelpr/getdevicelprInfo.do")
	@ResponseBody
	public void GetDevicelrpinfoByPage(){
		PageParamsDto paramsDto = new PageParamsDto(request); // 获取分 页参数
		int iTotal=0;
		List<DevicelrpDto> dtolist=null;
		if(!SecurityUtils.getSubject().isPermitted("DeviceSee")){
		}else{
			iTotal=pfmDeviceService.getRecordTotal(paramsDto);
		if(iTotal>0){
			if(paramsDto.getLength()==-1){
				paramsDto.setLength(iTotal);  //查询所有记录
			}
			dtolist=pfmDeviceService.getListByPage(paramsDto);
		}
		}
		DataTableDto data = new DataTableDto(); // 返回数据封装
		
		data.setDataList(dtolist);
		data.setDraw(paramsDto.getDraw());
		data.setRecordsFiltered(iTotal);
		data.setRecordsTotal(iTotal);
		AjaxUtil.ajaxWriteDataTable(data, response);
	}
	/*
	 * 保存信息
	 */
	@RequestMapping("/devicelpr/savedevicelprInfo.do")
	@ResponseBody
	public void SaveInfo(){
		String formData=request.getParameter("condition");
		DevicelrpDto devicelrpDto=JacksonUtil.toObject(formData, DevicelrpDto.class); // 获取表单数据
		if(devicelrpDto.getCongfigsel()==null||devicelrpDto.getCongfigsel().equals("0")){
			//无服务器可选择
			AjaxUtil.ajaxWrite(false, "无服务器选择，请先添加服务器信息", response);
			return;
		}
		PageParamsDto paramsDto = new PageParamsDto(request); // 获取分页参数
		DevicelrpDto resultDto=null;
		Devicelrp devicelrp= pfmDeviceService.findByDeviceCode(devicelrpDto.getDeviceCode(),paramsDto.getCompanyId());
		Devicelrp devicelrpName=pfmDeviceService.findByDeviceName(devicelrpDto.getDeviceName(),paramsDto.getCompanyId());
		
		if(devicelrpDto.getPfmDeviceLprId()==null){//新增角色信息
			if(devicelrp!=null){
				AjaxUtil.ajaxWrite(false, "编号已使用", response);
				return;
			}
			if(devicelrpName!=null){
				AjaxUtil.ajaxWrite(false, "设备名称已使用", response);
				return;
			}
			if(pfmDeviceService.ifHasDeviceOnServer(devicelrpDto.getIp(), devicelrpDto.getCongfigsel().toString())){
				AjaxUtil.ajaxWrite(false, "此服务器上的设备ip已被使用", response);
				return;
			}
			resultDto=new DevicelrpDto();
			resultDto.setBimCompanyId(paramsDto.getCompanyId());
			resultDto.setDeviceCode(devicelrpDto.getDeviceCode());
			resultDto.setDeviceName(devicelrpDto.getDeviceName());
			resultDto.setIp(devicelrpDto.getIp());
			resultDto.setPort(devicelrpDto.getPort());
			resultDto.setLoginId(devicelrpDto.getLoginId());
			resultDto.setLoginPassword(devicelrpDto.getLoginPassword());
			resultDto.setInsertTime(new Date());
			resultDto.setPfmServerId(Integer.valueOf(devicelrpDto.getCongfigsel()));
			resultDto.setMac("");
			//resultDto.setMac(devicelrpDto.getMac());
			pfmDeviceService.insertDtoInfo(resultDto);
			logRecord.recordLog(ConstParamLog.LOG_MODULE_BASIC_DEVICE_LPR, ConstParamLog.LOG_OPER_ADD,  ConstParamLog.LOG_TYPE_CONFIG,devicelrpDto.getDeviceName());
			
		}
		else{
			//修改
			
			/*if(devicelrp!=null){
				int a=devicelrpDto.getPfmDeviceLprId();
				int b=devicelrp.getPfmDeviceLprId();
				if(a!=b){
					AjaxUtil.ajaxWrite(false, "编号已使用", response);
					return;
				}
				
			}*/
			if(devicelrp!=null&&devicelrpDto.getPfmDeviceLprId()!=devicelrp.getPfmDeviceLprId()){
				AjaxUtil.ajaxWrite(false, "编号已使用", response);
				return;
			}
			if(devicelrpName!=null&&devicelrpDto.getPfmDeviceLprId()!=devicelrpName.getPfmDeviceLprId()){
				AjaxUtil.ajaxWrite(false, "设备名称已使用", response);
				return;
			}
			if(pfmDeviceService.ifHasDeviceOnServer2(devicelrpDto.getIp(), devicelrpDto.getCongfigsel().toString(),devicelrpDto.getPfmDeviceLprId())){
				AjaxUtil.ajaxWrite(false, "此服务器上的设备ip已被使用", response);
				return;
			}
			
				resultDto=new DevicelrpDto();
				 resultDto.setPfmDeviceLprId(devicelrpDto.getPfmDeviceLprId());
				 resultDto.setDeviceCode(devicelrpDto.getDeviceCode());
				 resultDto.setDeviceName(devicelrpDto.getDeviceName());
				 resultDto.setIp(devicelrpDto.getIp());
				 resultDto.setPort(devicelrpDto.getPort());
				
				 resultDto.setLoginId(devicelrpDto.getLoginId());
				 resultDto.setLoginPassword(devicelrpDto.getLoginPassword());
				 resultDto.setUpdateTime(new Date());
				 resultDto.setPfmServerId(Integer.valueOf(devicelrpDto.getCongfigsel()));
				 pfmDeviceService.updateDtoInfo(resultDto);
				 logRecord.recordLog(ConstParamLog.LOG_MODULE_BASIC_DEVICE_LPR, ConstParamLog.LOG_OPER_UPDATE,  ConstParamLog.LOG_TYPE_CONFIG,devicelrpDto.getDeviceName());
			
			 
		
		}
		AjaxUtil.ajaxWrite(true, "", response);
	}
	/*
	 * 删除信息
	 */
	@RequestMapping("/devicelpr/deldevicelprInfo.do")
	@ResponseBody
	public void DelInfo() throws JSONException{
		String jsonArrayStr = request.getParameter("condition");
		/*JSONArray jsonArray=new JSONArray();
		for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonobj=jsonArray.getJSONObject(i);
			if(jsonobj.get("pfmServerId")!=null){
				
			}
		}*/
		try {
			List<DevicelrpDto> delDtoList = JacksonUtil.json2list(jsonArrayStr, DevicelrpDto.class);
			for (DevicelrpDto listDto : delDtoList) {
				
				pfmDeviceService.deleteById(listDto.getPfmDeviceLprId());
				logRecord.recordLog(ConstParamLog.LOG_MODULE_BASIC_DEVICE_LPR, ConstParamLog.LOG_OPER_DELETE,  ConstParamLog.LOG_TYPE_CONFIG,listDto.getDeviceName());
			}
			
		} catch (Exception e) {
			LogManager.logException(e);
			AjaxUtil.ajaxWrite(false, e.getMessage(), response); // 返回失败
			return;
		}
		AjaxUtil.ajaxWrite(true, "", response); // 返回成功
	}
	/*
	 * 配置服务器信息
	 */
	@RequestMapping("/devicelpr/configservice.do")
	@ResponseBody
	public void ConfigService(){
		//String serverName=request.getParameter("serverName");
		List<ConfigServiceDto> listDto =pfmDeviceService.selectConfig();
		SelectDataDto selectDto=null;
		List<SelectDataDto> list=new ArrayList<SelectDataDto>();
		if(listDto.size()==0){
			selectDto=new SelectDataDto();
			selectDto.setId(0);
			selectDto.setText("无服务器选择");
			list.add(selectDto);
			AjaxUtil.ajaxWriteObject(list, response);
			return ;
		}
		else{
			for(ConfigServiceDto configserviceDto:listDto){
				selectDto=new SelectDataDto();
				selectDto.setId(configserviceDto.getPfmServerId());
				selectDto.setText(configserviceDto.getServerName());
				list.add(selectDto);
			   }
			AjaxUtil.ajaxWriteObject(list, response);
		}
		
		//return ;
	}
	/*
	 * 保存服务器配置
	 
	@RequestMapping("/devicelpr/updateconfig.do")
	@ResponseBody
	public void SaveConfigService(){
		String serverid=request.getParameter("serverid");
		String boothid=request.getParameter("boothid");
		pfmDeviceService.updateConfig(Integer.valueOf(serverid),Integer.valueOf(boothid),new Date());
		AjaxUtil.ajaxWrite(true, "", response); // 返回成功
	}   */
	 /*
	  * 查询server信息
	  */
		@RequestMapping("/devicelpr/selectServerInfo.do")
		@ResponseBody
		public void SaveConfigService(){
			String serverName=request.getParameter("serverName");
			DevicelrpDto devicelrpDto=pfmDeviceService.selectServerInfo(serverName);
			AjaxUtil.ajaxWriteObject(devicelrpDto, response);
		}   
	
}
