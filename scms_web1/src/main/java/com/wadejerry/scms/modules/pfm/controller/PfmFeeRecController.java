package com.wadejerry.scms.modules.pfm.controller;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wadejerry.scms.frame.Authority.AuthorityGetter;
import com.wadejerry.scms.frame.constant.ConstParamLog;
import com.wadejerry.scms.frame.entity.DataTableDto;
import com.wadejerry.scms.frame.entity.ExTemplate;
import com.wadejerry.scms.frame.entity.LoginInfo;
import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.frame.entity.ReportPageParamsDto;
import com.wadejerry.scms.frame.log.LogRecord;
import com.wadejerry.scms.modules.pfm.dto.CarInfoDto;
import com.wadejerry.scms.modules.pfm.dto.PfmFeeRecDto;
import com.wadejerry.scms.modules.pfm.model.PfmChargeRecord;
import com.wadejerry.scms.modules.pfm.model.PfmFeeRec;
import com.wadejerry.scms.modules.pfm.service.PfmFeeRecService;
import com.wadejerry.scms.utils.AjaxUtil;
import com.wadejerry.scms.utils.StringUtil;
import com.wadejerry.scms.utils.date.DateUtil;
@Controller
public class PfmFeeRecController {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private PfmFeeRecService pfmfeerecService;
	@Autowired
	private AuthorityGetter authorityGetter;
	@Autowired
	private ExTemplate exTemplate;
	@Autowired
	private LogRecord logRecord;
	
	//查询账户消费记录，用来显示前10条记录
	@RequestMapping("/account/selectrecording.do")
	@ResponseBody
	public void SelectRecord(){
		String cardid=request.getParameter("cardid");
		String Fee=null;
		List<PfmFeeRecDto> listDto=pfmfeerecService.selectRecording(Integer.valueOf(cardid),LoginInfo.getCompanyId());
		for(PfmFeeRecDto tempDto:listDto){
			//String date=DateUtil.YMDHMdf.format(tempDto.getTime());
			String date=DateUtil.YMDHMdf.format(tempDto.getInsertTime());
			tempDto.setStrTime(date);
		}
		AjaxUtil.ajaxWriteObject(listDto,response);
	}

	@RequestMapping("/report/selectFeeRec.do")
	@ResponseBody
	public void selectFeeRec(HttpServletRequest request) throws ParseException, JSONException{
		List<Integer> listtype=authorityGetter.carTypeAuth();
		ReportPageParamsDto paramsDto=ReportPageParamsDto.GetParamsDtoByRequest(request);
		if(paramsDto==null){
			int draw = Integer.parseInt(request.getParameter("draw"));
			DataTableDto data = new DataTableDto(); // 返回数据封装
			data.setDataList(null);
			data.setDraw(draw);
			data.setRecordsFiltered(0);
			data.setRecordsTotal(0);
			AjaxUtil.ajaxWriteDataTable(data, response);
			return;
		}else{
			
			int iTotal=0;
			Date startTime=null;
			Date endTime=null;
			String carNumber=null;
		    List<Integer> listtypeId=null;
		    Integer typeId=null;
		    Integer accountType=null;
		    String size=null;
			if(paramsDto.getSearchValue0()!=null){
				//开始时间
				startTime=paramsDto.getSearchValue0();
			}
			if(paramsDto.getSearchValue1()!=null){
				//结束时间
				endTime=paramsDto.getSearchValue1();
			}
			if(paramsDto.getSearchValue2()!=null&&!paramsDto.getSearchValue2().equals("")){
				carNumber=paramsDto.getSearchValue2();
			}
			if(paramsDto.getIntValue5()!=-1){
				typeId=paramsDto.getIntValue5();
			}
			if(paramsDto.getIntValue6()!=-1){
				accountType=paramsDto.getIntValue6();
			}
			if(listtype!=null){//不是企业管理员，需要权限
				if(listtype.size()>0){
					listtypeId=listtype;
					paramsDto.setListType(listtype);
				}else{
					size="1";
					paramsDto.setSize("1");
				}
			}
			List<PfmChargeRecord> dtolist=null;
			iTotal=pfmfeerecService.getFeeReportCount(startTime,endTime,carNumber,listtypeId,size,typeId,accountType);
			if(iTotal>0){
				if(paramsDto.getLength()==-1){
					paramsDto.setLength(iTotal);  //查询所有记录
				}
				
				dtolist=pfmfeerecService.getFeeReportListByPage(paramsDto);
				
			}
			logRecord.recordLog(ConstParamLog.LOG_MODULE_PFM_ACCOUNT_REPORT, ConstParamLog.LOG_OPER_QUERY,  ConstParamLog.LOG_TYPE_CONFIG);
			DataTableDto data = new DataTableDto(); // 返回数据封装
			data.setDataList(dtolist);
			data.setDraw(paramsDto.getDraw());
			data.setRecordsFiltered(iTotal);
			data.setRecordsTotal(iTotal);
			AjaxUtil.ajaxWriteDataTable(data, response);
			
			
			
		}
		
		
		}
	   /**
	    * 账户信息查询导出
	    * @param formdata
	 * @throws JSONException 
	 * @throws ParseException 
	    */
	@RequestMapping("/report/exportaccount/{formdata}.do")
	@ResponseBody
	public void ExportAccount(@PathVariable("formdata") String formdata) throws JSONException, ParseException{
		List<Integer> listtype=authorityGetter.carTypeAuth();
		List<Integer> listTypeId=null;
		String size=null;
		Date startTime=null;
		Date endTime=null;
		String carNumber=null;
		String order=null;
		Integer typeId=null;//操作类型
	    Integer accountType=null;//车辆性质
		 List<Integer> listentranceId=null;//出入口
		if(!formdata.equals("")){
			JSONObject json=new JSONObject(formdata);
			//只有开始时间
			if(!json.getString("startTime").equals("")&&json.getString("endTime").equals("")){
				startTime=DateUtil.df.parse(json.getString("startTime"));
			}
			//只有结束时间
			if(!json.getString("endTime").equals("")&&json.getString("startTime").equals("")){
				endTime=DateUtil.df.parse(json.getString("endTime"));
			}
			//开始结束时间都有
			if(!json.getString("endTime").equals("")&&!json.getString("startTime").equals("")){
				endTime=DateUtil.df.parse(json.getString("endTime"));
				startTime=DateUtil.df.parse(json.getString("startTime"));
				
			}
			if(json.has("carNumber")&&!json.getString("carNumber").equals("")){
				carNumber=json.getString("carNumber");
			}
			if(json.has("typeId")&&!json.getString("typeId").equals("")){
				typeId=Integer.valueOf(json.getString("typeId"));
			}
			if(json.has("accountType")&&!json.getString("accountType").equals("")){
				accountType=Integer.valueOf(json.getString("accountType"));
			}
			
			
			if((json.has("order1")&&!json.getString("order1").equals(""))&&(json.has("order2")&&!json.getString("order2").equals(""))){
		    	
		    		order=MessageFormat.format("  order by {0} {1}", 
							StringUtil.objNameToTableColName(json.getString("order1").toString())
							, json.getString("order2").toString());
			    		
		    }
				
			
			
			
			
		}
		if(listtype!=null){//不是企业管理员，需要权限
			if(listtype.size()>0){
				listTypeId=listtype;
				
			}else{
				size="1";
			}
		}
		
		List<Map<String,Object>> data=pfmfeerecService.exportaccount(startTime,endTime,carNumber,listTypeId,size,order,accountType,typeId);
		String[] head = {"车牌","车主姓名","车辆类型","操作类型","操作金额","到期时间","操作时间"};
		String[] column={"car_number","person_name","car_type_name","type_id","fee","disable_time","insert_time"};
		exTemplate.Export(data, head, column);
		logRecord.recordLog(ConstParamLog.LOG_MODULE_PFM_ACCOUNT_REPORT, ConstParamLog.LOG_OPER_EXPORT,  ConstParamLog.LOG_TYPE_CONFIG);
	}
	
	
	
		
	}
	
	
	

