package com.wadejerry.scms.modules.pfm.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wadejerry.scms.frame.constant.ConstParamLog;
import com.wadejerry.scms.frame.entity.LoginInfo;
import com.wadejerry.scms.frame.entity.OperateResult;
import com.wadejerry.scms.frame.entity.SelectDataDto;
import com.wadejerry.scms.frame.log.LogRecord;
import com.wadejerry.scms.modules.pfm.dto.ZTreeDto;
import com.wadejerry.scms.modules.pfm.model.PfmCarType;
import com.wadejerry.scms.modules.pfm.model.PfmParkingLot;
import com.wadejerry.scms.modules.pfm.service.PfmCarTypeService;
import com.wadejerry.scms.utils.AjaxUtil;
import com.wadejerry.scms.utils.json.JacksonUtil;

@Controller
public class CarTypeController {
	
	@Autowired
	private PfmCarTypeService pcts;
	@Autowired
	private LogRecord logRecord;
	
	@RequestMapping(value = "pfm/cartype/delete.do")
	@ResponseBody
	public void deleteCarType(HttpServletResponse response,@RequestParam("node")String nodeid) {
		int companyid = LoginInfo.getCompanyId();
		OperateResult o=pcts.deleteNode(companyid,Integer.parseInt(nodeid) );
		AjaxUtil.ajaxWrite(o, response);
	}
	
	@RequestMapping(value = "pfm/cartype/add.do")
	@ResponseBody
	public void addCarType(HttpServletResponse response,@RequestParam("condition")String condition,@RequestParam("flag")String flag,@RequestParam(value="nodeid",required=false)String nodeid) {
		int companyid = LoginInfo.getCompanyId();
		try {
			JSONObject obj = new JSONObject(condition);
			if(!obj.has("custom3")) {
				obj.put("custom3", "");
			}
			else if(obj.get("custom3").getClass()==JSONArray.class) {
			
				obj.put("custom3", obj.getJSONArray("custom3").join(",").replaceAll("\"", ""));
			}
			condition = obj.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PfmCarType ct = JacksonUtil.toObject(condition,PfmCarType.class);
		if("0".equals(flag)){//flag==0做添加操作
	
		ct.setBimCompanyId(companyid);
		ct.setInsertTime(new Date());
		ct.setTypeCode("");
		ct.setUpdateTime(new Date());
		ct.setUserName(LoginInfo.getLoginName());
		ZTreeDto dto=null;
		if((dto=pcts.insertCarType(ct))!=null){
			if(!LoginInfo.isCompanyManager()){
			dto.setHasPermission(1);
			}
			else{
				dto.setHasPermission(0);
			}
			AjaxUtil.ajaxWriteObject(new OperateResult(true, "添加成功"), dto, response);
			logRecord.recordLog(ConstParamLog.LOG_MODULE_PFM_CAR_TYPE, ConstParamLog.LOG_OPER_ADD, ConstParamLog.LOG_TYPE_CONFIG, ct.getTypeName());

			
		}
		else{
			AjaxUtil.ajaxWrite(new OperateResult(false, "添加失败"), response);
		}

		}
		
		else{//修改操作，，nodeid代表类型的id
			ct.setUpdateTime(new Date());
			ct.setCarTypeId(Integer.parseInt(nodeid));
			if(pcts.updateCarType(ct).isResult()){
				ZTreeDto dto=pcts.selectCarTypeById(ct.getCarTypeId());
				dto.setHasPermission(0);
				AjaxUtil.ajaxWriteObject(new OperateResult(true, "修改成功"), dto, response);
				logRecord.recordLog(ConstParamLog.LOG_MODULE_PFM_CAR_TYPE, ConstParamLog.LOG_OPER_UPDATE, ConstParamLog.LOG_TYPE_CONFIG, ct.getTypeName());

			}
			else
				AjaxUtil.ajaxWrite(new OperateResult(false, "修改失败"), response);

		}
		
		
		
	}

	@RequestMapping(value = "pfm/cartype/getcttree.do")
	@ResponseBody
	public void getCarTypeTreeData(HttpServletResponse response){
		int companyid = LoginInfo.getCompanyId();
		List<ZTreeDto> lots = pcts.selectCarTypesTree(companyid);
	
		AjaxUtil.ajaxWriteObject(lots, response);
		
	}
	@RequestMapping(value = "pfm/cartype/getlots.do")
	@ResponseBody
	public void getParkLots(HttpServletResponse response){
		int companyid = LoginInfo.getCompanyId();
		List<PfmParkingLot> lots = pcts.select1PL(companyid);
	
		AjaxUtil.ajaxWriteObject(lots, response);
		
	}
	
	@RequestMapping(value = "pfm/cartype/getcartypeby1lot.do")
	@ResponseBody
	public void getParkLotsq(HttpServletResponse response,@RequestParam("lotid") String lotid){
		int companyid = LoginInfo.getCompanyId();
		List<PfmCarType> lots = pcts.select1CarTypeByLotId(Integer.parseInt(lotid), companyid);
	
		AjaxUtil.ajaxWriteObject(lots, response);
		
	}
	@RequestMapping(value = "/subparks/getselectdata")
	@ResponseBody
	public void getsubparks(HttpServletResponse response,@RequestParam("ppark") String parkId){
		Integer parkid =null;
		try {
			parkid = new Integer(parkId);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		if(parkid!=null)
		{
		List<SelectDataDto> lots = pcts.selectsubParkData(parkid);
	
		AjaxUtil.ajaxWriteObject(lots, response);
		}
		else {
			AjaxUtil.ajaxWriteObject("", response);
		}
	}

}
