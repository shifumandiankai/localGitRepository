package com.wadejerry.scms.modules.pfm.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
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

import com.wadejerry.scms.frame.Authority.AuthorityGetter;
import com.wadejerry.scms.frame.constant.ConstParamLog;
import com.wadejerry.scms.frame.entity.DataTableDto;
import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.frame.log.LogRecord;
import com.wadejerry.scms.modules.pfm.dto.ParkingLotDto;
import com.wadejerry.scms.modules.pfm.dto.TimeDto;
import com.wadejerry.scms.modules.pfm.model.PfmTime;
import com.wadejerry.scms.modules.pfm.service.PfmTimeService;
import com.wadejerry.scms.utils.AjaxUtil;
import com.wadejerry.scms.utils.Log.LogManager;
import com.wadejerry.scms.utils.date.DateUtil;
import com.wadejerry.scms.utils.json.JacksonUtil;

@Controller
public class TimeControler {
@Autowired	
private HttpServletRequest request;
@Autowired	
private HttpServletResponse response;
@Autowired
private PfmTimeService pfmtimeservice;//Service
@Autowired
private LogRecord logRecord;
@Autowired
private AuthorityGetter authorityGetter;

/**
 * 分页获取用户 信息
 * 
 * @author
 * @param
 * @throws ParseException 
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user:
 * @modify by reason:
 */
@RequestMapping("/time/getTimeInfo.do")
@ResponseBody
private void GetTimeInfoByPage() throws ParseException{
	List<Integer> listtype=authorityGetter.parkAuth();
	List<Integer> listtypeId=null;
	Integer flag=null;
	PageParamsDto paramsDto = new PageParamsDto(request); // 获取分 页参数
	int iTotal=0;
	List<TimeDto> dtoList=null;
	if(listtype!=null){//不是企业管理员，需要权限
		if(listtype.size()>0){
			//有权限
			listtypeId=listtype;
	}else{
		//操作员但是没给权限
		flag=0;
	}
		}
	/*if(!SecurityUtils.getSubject().isPermitted("PeriodSee")){
	}else{*/
		iTotal=pfmtimeservice.getRecordTotal(paramsDto,listtypeId,flag);
	if(iTotal>0){
		if(paramsDto.getLength()==-1){
			paramsDto.setLength(iTotal);  //查询所有记录
		}
		dtoList=pfmtimeservice.getTimeListByPage(paramsDto,listtypeId,flag);
		for(TimeDto dto:dtoList){
			/*StringBuffer sb=new StringBuffer();
			String str=dto.getEnableWeek();
			String[] arr=str.split(",");
			List list=Arrays.asList(arr);
            for(int i=0;i<list.size();i++){
            	String str1=(String) list.get(i);
            	switch(str1){
				case "周日":dto.setStrweek("0");
				break;
				case "周一":dto.setStrweek("1");
				break;
				case "周二":dto.setStrweek("2");
				break;
				case "周三":dto.setStrweek("3");
				break;
				case "周四":dto.setStrweek("4");
				break;
				case "周五":dto.setStrweek("5");
				break;
				case "周六":dto.setStrweek("6");
				break;
				case "每天":dto.setStrweek("7");
				break;
				}
            	if(i==0){
            		sb.append(dto.getStrweek());
            	}
            	else{
            		sb.append(","+dto.getStrweek());
            	}
            	
			}
           dto.setStrweek(sb.toString());*/
			
			
			
			if(dto.getDayBeginTime1()!=null&&dto.getDayEndTime1()!=null){
				dto.setStrBeginTime1(DateUtil.HMsdf.format(dto.getDayBeginTime1()));
				dto.setStrEndTime1(DateUtil.HMsdf.format(dto.getDayEndTime1()));
			}
			if(dto.getDayBeginTime2()!=null&&dto.getDayEndTime2()!=null){
				dto.setStrBeginTime2(DateUtil.HMsdf.format(dto.getDayBeginTime2()));
				dto.setStrEndTime2(DateUtil.HMsdf.format(dto.getDayEndTime2()));
			}
			if(dto.getDayBeginTime3()!=null&&dto.getDayEndTime3()!=null){
				dto.setStrBeginTime3(DateUtil.HMsdf.format(dto.getDayBeginTime3()));
				dto.setStrEndTime3(DateUtil.HMsdf.format(dto.getDayEndTime3()));
			}
			if(dto.getDayBeginTime4()!=null&&dto.getDayEndTime4()!=null){
				dto.setStrBeginTime4(DateUtil.HMsdf.format(dto.getDayBeginTime4()));
				dto.setStrEndTime4(DateUtil.HMsdf.format(dto.getDayEndTime4()));
			}
			if(dto.getBeginTime()!=null&&dto.getEndTime()!=null){
				dto.setStrbeginTime(DateUtil.YMDsdf.format(dto.getBeginTime()));
				dto.setStrendTime(DateUtil.YMDsdf.format(dto.getEndTime()));
			}
			
		}	
	}
//}
	DataTableDto data = new DataTableDto(); // 返回数据封装
	data.setDataList(dtoList);
	data.setDraw(paramsDto.getDraw());
	data.setRecordsFiltered(iTotal);
	data.setRecordsTotal(iTotal);
	AjaxUtil.ajaxWriteDataTable(data, response);
}

/*
 *  保存修改信息
 */
@RequestMapping("/time/saveTimeInfo.do")
@ResponseBody
private void SaveTimeInfo() throws JSONException{
	    String formData=request.getParameter("condition");
		JSONObject jsonObject=new JSONObject(formData);
		String strbeginTime=jsonObject.get("strbeginTime").toString();
		String strendTime=jsonObject.get("strendTime").toString();
	    jsonObject.put("beginTime",strbeginTime+" 00:00:00" );
		jsonObject.put("endTime",strendTime+" 23:59:59" );
			//添加
		//if(jsonObject.has("strBeginTime1")){
				//如果没有被禁用
			   if(!jsonObject.get("strBeginTime1").toString().equals("")&&!jsonObject.get("strEndTime1").toString().equals("")){
				   jsonObject.put("dayBeginTime1",jsonObject.get("strBeginTime1").toString()+":00" );
					jsonObject.put("dayEndTime1",jsonObject.get("strEndTime1").toString()+":00" );
			   }
			   if(!jsonObject.get("strBeginTime2").toString().equals("")&&!jsonObject.get("strEndTime2").toString().equals("")){
				   jsonObject.put("dayBeginTime2",jsonObject.get("strBeginTime2").toString()+":00" );
					jsonObject.put("dayEndTime2",jsonObject.get("strEndTime2").toString()+":00" );
			   }
			   if(!jsonObject.get("strBeginTime3").toString().equals("")&&!jsonObject.get("strEndTime3").toString().equals("")){
				   jsonObject.put("dayBeginTime3",jsonObject.get("strBeginTime3").toString()+":00" );
					jsonObject.put("dayEndTime3",jsonObject.get("strEndTime3").toString()+":00" );
			   }
			   if(!jsonObject.get("strBeginTime4").toString().equals("")&&!jsonObject.get("strEndTime4").toString().equals("")){
				   jsonObject.put("dayBeginTime4",jsonObject.get("strBeginTime4").toString()+":00" );
					jsonObject.put("dayEndTime4",jsonObject.get("strEndTime4").toString()+":00" );
			   }
			//}
		//判断序列化过来的"list"是对象还是数组
		if(!(jsonObject.get("list") instanceof JSONArray)){
			ArrayList<String> arrayList=new ArrayList<String>();
			arrayList.add((String) jsonObject.get("list"));
			
			jsonObject.put("list", arrayList);
		}
		
	formData = jsonObject.toString();
	TimeDto timeDto=JacksonUtil.toObject(formData, TimeDto.class);//获取表单数据
	PageParamsDto paramsDto = new PageParamsDto(request); // 获取分 页参数
	PfmTime inserttime=null;
	PfmTime timeAll=pfmtimeservice.findByTimeName(timeDto.getTimeCode(),paramsDto.getCompanyId());//查询数据中原有的数据
	PfmTime timeName=pfmtimeservice.selectByTimeName(timeDto.getTimeName(),paramsDto.getCompanyId());
	StringBuffer sb=new StringBuffer();
	int count=1;
	String everyday=null;
	for(int i=0;i<timeDto.getList().size();i++){
		if(timeDto.getList().get(i).equals("每天")){
			everyday="7";
			break;
		}else{
			    String temp=null;
            	String str1=timeDto.getList().get(i);
            	switch(str1){
				case "周日":temp="0";
				break;
				case "周一":temp="1";
				break;
				case "周二":temp="2";
				break;
				case "周三":temp="3";
				break;
				case "周四":temp="4";
				break;
				case "周五":temp="5";
				break;
				case "周六":temp="6";
				break;
				case "每天":temp="7";
				break;
				}
			sb.append(temp);
			if(count<timeDto.getList().size()){
				sb.append(",");
					count++;
				}
		}
	}
		
	if(timeDto.getPfmTimeId()==null){//添加功能
		if(timeAll!=null){
			AjaxUtil.ajaxWrite(false, "时段编号已存在", response);
			return;
		} 
		if(timeName!=null){
			AjaxUtil.ajaxWrite(false, "时段名称已存在", response);
			return;
		}
		    inserttime=new PfmTime();
		    inserttime.setDayBeginTime1(timeDto.getDayBeginTime1());
			inserttime.setDayEndTime1(timeDto.getDayEndTime1());
			inserttime.setDayBeginTime2(timeDto.getDayBeginTime2());
			inserttime.setDayEndTime2(timeDto.getDayEndTime2());
			inserttime.setDayBeginTime3(timeDto.getDayBeginTime3());
			inserttime.setDayEndTime3(timeDto.getDayEndTime3());
		    inserttime.setDayBeginTime4(timeDto.getDayBeginTime4());
			inserttime.setDayEndTime4(timeDto.getDayEndTime4());
			inserttime.setTimeCode(timeDto.getTimeCode());
			inserttime.setTimeName(timeDto.getTimeName());
			inserttime.setBeginTime(timeDto.getBeginTime());
			inserttime.setEndTime(timeDto.getEndTime());
			inserttime.setInsertTime(new Date());
		if(timeDto.getEnableIn()!=null){
			inserttime.setEnableIn(0);
		}
		else{
			inserttime.setEnableIn(1);
		}
		if(timeDto.getEnableOut()!=null){
			inserttime.setEnableOut(0);
		}else{
			inserttime.setEnableOut(1);
		}
			
		if(everyday!=null||timeDto.getList().size()>=7){
			
			inserttime.setEnableWeek("7");
		}
			else{
				
				inserttime.setEnableWeek(sb.toString());//StringBuffer转为String
			}
			
			
			
		inserttime.setDayLimit(0);//启用
			pfmtimeservice.insertTimeDto(inserttime);//添加信息
			
			logRecord.recordLog(ConstParamLog.LOG_MODULE_PFM_TIME, ConstParamLog.LOG_OPER_ADD, ConstParamLog.LOG_TYPE_CONFIG,timeDto.getTimeName());
			
			//
			
	}
	else{//修改功能  ， 修改角色信息  修改的时候能获取到formID，可以根据id查询提交之前的数据
		
		if(timeAll!=null&&!timeDto.getPfmTimeId().equals(timeAll.getPfmTimeId())){
			AjaxUtil.ajaxWrite(false, "时段编号已使用", response);
			return;
		}
		if(timeName!=null&&!timeDto.getPfmTimeId().equals(timeName.getPfmTimeId())){
			AjaxUtil.ajaxWrite(false, "时段名称已使用", response);
			return;
		}
		inserttime=new PfmTime();
		inserttime.setPfmTimeId(timeDto.getPfmTimeId());
		inserttime.setTimeCode(timeDto.getTimeCode());
		inserttime.setTimeName(timeDto.getTimeName());
		inserttime.setBeginTime(timeDto.getBeginTime());
		inserttime.setEndTime(timeDto.getEndTime());
		inserttime.setDayBeginTime1(timeDto.getDayBeginTime1());
		inserttime.setDayEndTime1(timeDto.getDayEndTime1());
		inserttime.setDayBeginTime2(timeDto.getDayBeginTime2());
		inserttime.setDayEndTime2(timeDto.getDayEndTime2());
		inserttime.setDayBeginTime3(timeDto.getDayBeginTime3());
		inserttime.setDayEndTime3(timeDto.getDayEndTime3());
		inserttime.setDayBeginTime4(timeDto.getDayBeginTime4());
		inserttime.setDayEndTime4(timeDto.getDayEndTime4());
		inserttime.setUpdateTime(new Date());
		if(timeDto.getEnableIn()!=null){
			inserttime.setEnableIn(0);
		}
		else{
			inserttime.setEnableIn(1);
		}
		if(timeDto.getEnableOut()!=null){
			inserttime.setEnableOut(0);
		}else{
			inserttime.setEnableOut(1);
		}
        if(everyday!=null||timeDto.getList().size()>=7){
			
			inserttime.setEnableWeek("7");
		}
		else{
			
			inserttime.setEnableWeek(sb.toString());//StringBuffer转为String
		}
		
		
		pfmtimeservice.updateTimeDto(inserttime);
		logRecord.recordLog(ConstParamLog.LOG_MODULE_PFM_TIME, ConstParamLog.LOG_OPER_UPDATE, ConstParamLog.LOG_TYPE_CONFIG,timeDto.getTimeName());
	}
	
	
	AjaxUtil.ajaxWrite(true, "", response);
			
}
/*
 *  删除信息
 */
@RequestMapping("/time/delTimeInfo.do")
@ResponseBody
private void DelTimeInfo(){
		String jsonArrayStr = request.getParameter("condition");

		try {

			/*JSONArray jsonArray = new JSONArray(jsonArrayStr);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				if (jsonObject.has("dayBeginTime1") || jsonObject.has("dayEndTime1")) {
					String dayBeginTime1 = jsonObject.get("dayBeginTime1").toString();
					String dayEndTime1 = jsonObject.get("dayEndTime1").toString();
					jsonObject.put("dayBeginTime1", "2010-10-10 " + dayBeginTime1);
					jsonObject.put("dayEndTime1", "2010-10-10 " + dayEndTime1);
					jsonArray.put(i, jsonObject);

				}
				if(jsonObject.has("beginTime")||jsonObject.has("endTime")){
					String beginTime = jsonObject.get("beginTime").toString();
					String endTime = jsonObject.get("endTime").toString();
					
					
					if(beginTime.equals("null")||endTime.equals("null")){
						jsonObject.put("beginTime", "2010-01-01 00:00:00");
						jsonObject.put("endTime","2010-01-01 23:59:59");
					}
					else{
					jsonObject.put("beginTime", beginTime+" 00:00:00");
					jsonObject.put("endTime",endTime+" 23:59:59");
					jsonArray.put(i, jsonObject);
					}
				}

			}
			String jsonArrayToString = jsonArray.toString();*/
			//List<TimeDto> delDtoList = JacksonUtil.json2list(jsonArrayToString, TimeDto.class);
		List<TimeDto> delDtoList = JacksonUtil.json2list(jsonArrayStr, TimeDto.class);
			for (TimeDto timeDto : delDtoList) {

				pfmtimeservice.deleteTimeById(timeDto.getPfmTimeId());
				logRecord.recordLog(ConstParamLog.LOG_MODULE_PFM_TIME, ConstParamLog.LOG_OPER_DELETE, ConstParamLog.LOG_TYPE_CONFIG,timeDto.getTimeName());
			}
			
		} catch (Exception e) {
			LogManager.logException(e);
			AjaxUtil.ajaxWrite(false, e.getMessage(), response); // 返回失败
			return;
		}
		AjaxUtil.ajaxWrite(true, "", response); // 返回成功
	}
}

