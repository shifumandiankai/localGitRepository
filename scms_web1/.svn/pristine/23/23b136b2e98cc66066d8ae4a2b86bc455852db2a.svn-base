package com.wadejerry.scms.modules.device.controller;

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
import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.frame.entity.SelectDataDto;
import com.wadejerry.scms.frame.log.LogRecord;
import com.wadejerry.scms.modules.device.dto.DevicelrpDto;
import com.wadejerry.scms.modules.device.model.Devicelrp;
import com.wadejerry.scms.modules.device.service.PfmDeviceService;
import com.wadejerry.scms.modules.pfm.dto.ScreenDto;
import com.wadejerry.scms.modules.pfm.service.SrceenService;
import com.wadejerry.scms.utils.AjaxUtil;
import com.wadejerry.scms.utils.Log.LogManager;
import com.wadejerry.scms.utils.json.JacksonUtil;

@Controller
public class ScreenController {
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private SrceenService srceenService;
	@Autowired
	private PfmDeviceService pfmDeviceService;
	@Autowired
	private LogRecord logRecord;
	/*
	 * 分页查询显示信息
	 */
	@RequestMapping("/devicelpr/getscreenInfo.do")
	@ResponseBody
	public void GetScreeninfoByPage(){
		PageParamsDto paramsDto =  new PageParamsDto(request); // 获取分页参数
		int iTotal=0;
		List<ScreenDto> dtolist=null;
		if(!SecurityUtils.getSubject().isPermitted("ScreenSee")){
		}else{
			iTotal=srceenService.getRecordTotal(paramsDto);
		if(iTotal>0){
			if(paramsDto.getLength()==-1){
				paramsDto.setLength(iTotal);  //查询所有记录
			}
			dtolist=srceenService.getListByPage(paramsDto);
			for(int i=0;i<dtolist.size();i++){
				StringBuffer sb=new StringBuffer();
				
				if(dtolist.get(i).getDeviceIdArr()!=null){
					
					dtolist.get(i).setListarr(Arrays.asList(dtolist.get(i).getDeviceIdArr().split(",")));
					List<String> listArr=Arrays.asList(dtolist.get(i).getDeviceIdArr().split(","));
					for(int j=0;j<listArr.size();j++){
						Devicelrp devicelrp=pfmDeviceService.selectId(Integer.valueOf(listArr.get(j)));
						//dtolist.get(i).setArrStr(devicelrp.getDeviceName());
						if(j==0){
							sb.append(devicelrp.getDeviceName());
						}else{
							sb.append(","+devicelrp.getDeviceName());
						}
						dtolist.get(i).setDeviceIdArr(sb.toString());
					}
				}
				
				
				
			}
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
	 * 设备绑定
	 */
	@RequestMapping("/devicelpr/configDeviceLpr.do")
	@ResponseBody
	public void configDeviceLpr(){
		List<DevicelrpDto> listDto =pfmDeviceService.selectAllLpr();
		SelectDataDto selectDto=null;
		List<SelectDataDto> list=new ArrayList<SelectDataDto>();
		if(listDto.size()==0){
			selectDto=new SelectDataDto();
			selectDto.setId(0);
			selectDto.setText("无设备选择");
			list.add(selectDto);
			AjaxUtil.ajaxWriteObject(list, response);
			return ;
		}
		else{
			for(DevicelrpDto Devicelrpdto:listDto){
				selectDto=new SelectDataDto();
				selectDto.setId(Devicelrpdto.getPfmDeviceLprId());
				selectDto.setText(Devicelrpdto.getDeviceName());
				list.add(selectDto);
			   }
			AjaxUtil.ajaxWriteObject(list, response);
		}
	}
	/*
	 * 保存信息
	 */
	@RequestMapping("/devicelpr/savescreenInfo.do")
	@ResponseBody
	private void SaveScreenInfo() throws JSONException {
		String formData = request.getParameter("condition");
		StringBuffer sb=new StringBuffer();
		JSONObject json=new JSONObject(formData);
		if(json.has("listarr")){
			if(!(json.get("listarr") instanceof JSONArray)){
				//多个个设备
				List<String> list=new ArrayList<String>();
				list.add((String) json.get("listarr"));
				json.put("listarr", list);
			}
		}
		formData=json.toString();
		ScreenDto screenDto = JacksonUtil.toObject(formData, ScreenDto.class); // 获取表单数据
		//PfmDeviceScreen deviceScreen = JacksonUtil.toObject(formData, PfmDeviceScreen.class); // 获取表单数据
		PageParamsDto pageParamsDto = new PageParamsDto(request);
		//ScreenDto screenAll = null;
		
		ScreenDto screenByName = srceenService.findByName(screenDto.getDeviceName(),pageParamsDto.getCompanyId());//判断名称是否存在
		ScreenDto screenByCode = srceenService.findByCode(screenDto.getDeviceCode(),pageParamsDto.getCompanyId());//判断编号是否存在
		
		if (screenDto.getPfmDeviceScreenId() == null) { // 新增角色信息
			if (screenByName != null) {
				AjaxUtil.ajaxWrite(false, "设备名称已使用", response);
				return;
			}
			if (screenByCode != null) {
				AjaxUtil.ajaxWrite(false, "设备编号已使用", response);
				return;
			}
			if(screenDto.getListarr()!=null&&!screenDto.getListarr().equals("")&&!screenDto.getListarr().get(0).equals("0")){
				List<String> list=new ArrayList<String>();
				list=screenDto.getListarr();
				//int count=list.size();
				for(int i=0;i<list.size();i++){
					DevicelrpDto devicelrpDto=pfmDeviceService.findByLpr(Integer.valueOf(list.get(i)));
					if(devicelrpDto.getPfmScreenId()!=null){
						AjaxUtil.ajaxWrite(false, "LPR设备已被绑定", response);
						return;
					}
					if(i==0){
						sb.append(list.get(i));
					}else{
						sb.append(","+list.get(i));
					}
				}
				//screenDto.setArrStr(sb.toString());
				screenDto.setDeviceIdArr(sb.toString());
			}
			screenDto.setInsertTime(new Date());
			screenDto.setBimCompanyId(pageParamsDto.getCompanyId());
			screenDto.setIp("");
			screenDto.setPort(1);
			srceenService.insertInfo(screenDto);
			logRecord.recordLog(ConstParamLog.LOG_MODULE_BASIC_DEVICE_SCREEN, ConstParamLog.LOG_OPER_ADD,  ConstParamLog.LOG_TYPE_CONFIG,screenDto.getDeviceName());
			
		} else{
			if(screenByName!=null){
				int a=screenByName.getPfmDeviceScreenId();
				int b=screenDto.getPfmDeviceScreenId();
				if(a!=b){
					AjaxUtil.ajaxWrite(false, "设备名称已使用", response);
					return;
				}
			}
			if(screenByCode!=null){
				int a=screenByCode.getPfmDeviceScreenId();
				int b=screenDto.getPfmDeviceScreenId();
				if(a!=b){
					AjaxUtil.ajaxWrite(false, "设备编号已使用", response);
					return;
				}
			}
			if(screenDto.getListarr()!=null&&!screenDto.getListarr().equals("")&&!screenDto.getListarr().get(0).equals("0")){
				List<String> list=new ArrayList<String>();
				list=screenDto.getListarr();
				//int count=list.size();
				for(int i=0;i<list.size();i++){
					DevicelrpDto devicelrpDto=pfmDeviceService.findByLpr(Integer.valueOf(list.get(i)));
					if(devicelrpDto.getPfmScreenId()!=null&&!devicelrpDto.getPfmScreenId().equals(screenDto.getPfmDeviceScreenId())){
						AjaxUtil.ajaxWrite(false, "LPR设备已被绑定", response);
						return;
					}
					if(i==0){
						sb.append(list.get(i));
					}else{
						sb.append(","+list.get(i));
					}
				}
				//screenDto.setArrStr(sb.toString());
				screenDto.setDeviceIdArr(sb.toString());
			}
			screenDto.setUpdateTime(new Date());
			screenDto.setBimCompanyId(pageParamsDto.getCompanyId());
			srceenService.updateInfo(screenDto);
			logRecord.recordLog(ConstParamLog.LOG_MODULE_BASIC_DEVICE_SCREEN, ConstParamLog.LOG_OPER_UPDATE,  ConstParamLog.LOG_TYPE_CONFIG,screenDto.getDeviceName());
		}
		
		
		AjaxUtil.ajaxWrite(true, "", response);

	}
	
	
	@RequestMapping("/devicelpr/delscreenInfo.do")
	@ResponseBody
	private void DelParkInfo() {
		String jsonArrayStr = request.getParameter("condition");
		try {
			List<ScreenDto> delDtoList = JacksonUtil.json2list(jsonArrayStr, ScreenDto.class);
			for (ScreenDto screenDto : delDtoList) {
				screenDto.setUpdateTime(new Date());
				srceenService.deleteInfo(screenDto);
				logRecord.recordLog(ConstParamLog.LOG_MODULE_BASIC_DEVICE_SCREEN, ConstParamLog.LOG_OPER_DELETE,  ConstParamLog.LOG_TYPE_CONFIG,screenDto.getDeviceName());
				
			}
			
		} catch (Exception e) {
			LogManager.logException(e);
			AjaxUtil.ajaxWrite(false, e.getMessage(), response); // 返回失败
			return;
		}
		AjaxUtil.ajaxWrite(true, "", response); // 返回成功
	}
}
