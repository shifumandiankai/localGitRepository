package com.wadejerry.scms.modules.pfm.controller;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.wadejerry.scms.frame.entity.DataTableDto;
import com.wadejerry.scms.frame.entity.ExTemplate;
import com.wadejerry.scms.frame.entity.ReportPageParamsDto;
import com.wadejerry.scms.modules.pfm.dto.CzReportDto;
import com.wadejerry.scms.modules.pfm.dto.PfmChargeRecordDto;
import com.wadejerry.scms.modules.pfm.service.PfmChargeRecordReportService;
import com.wadejerry.scms.utils.AjaxUtil;
import com.wadejerry.scms.utils.StringUtil;
import com.wadejerry.scms.utils.date.DateUtil;

@Controller
public class ChargeReportController {
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private AuthorityGetter authorityGetter;
	@Autowired
	private PfmChargeRecordReportService chargeRecordService;
	@Autowired
	private ExTemplate exTemplate;
	
	/*
	 * 消费日报表查询
	 */
	@RequestMapping("/report/Czreport/selectChargeDayAllListByPage.do")
	@ResponseBody
	public void GetChargeDayRecordInfoByPage() throws ParseException, JSONException{
		List<Integer> listtype=authorityGetter.carTypeAuth();
		ReportPageParamsDto paramsDto=ReportPageParamsDto.GetParamsDtoByRequest(request);
		//String val=request.getParameter("columns[0][search][value]");
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
	    List<Integer> listcarType=null;
	    String strday=null;
	    String size=null;
	    List<PfmChargeRecordDto> dtolist=null;
	    String order=null;
	    Date insertTime=null;
	    List<PfmChargeRecordDto> list=new ArrayList<PfmChargeRecordDto>();
	    if(paramsDto.getSearchValue2()!=null&&!paramsDto.getSearchValue2().equals("")){
			carNumber=paramsDto.getSearchValue2();
		}
		if(paramsDto.getListType()!=null&&!paramsDto.getListType().equals("")){
			listcarType=paramsDto.getListType();
		}
		if(paramsDto.getSearchValue0()==null&&paramsDto.getSearchValue1()==null){
			//页面刷新的时候
			startTime=new Date();
			String str=DateUtil.YMDsdf.format(startTime);
			startTime=DateUtil.YMDsdf.parse(str);
			String strend=str+" 23:59:59";
			endTime=DateUtil.df.parse(strend);
				
		}
			//只有开始或者结束时间，有一个为null
			if(paramsDto.getSearchValue0()!=null&&paramsDto.getSearchValue1()==null){
				startTime=paramsDto.getSearchValue0();
				String str=DateUtil.YMDsdf.format(startTime);
				String strend=str+" 23:59:59";
				endTime=DateUtil.df.parse(strend);
			}
			if(paramsDto.getSearchValue1()!=null&&paramsDto.getSearchValue0()==null){
				endTime=paramsDto.getSearchValue1();
				String str=DateUtil.YMDsdf.format(endTime);
				String strstart=str+" 00:00:00";
				startTime=DateUtil.df.parse(strstart);
			}
			if(paramsDto.getSearchValue0()!=null&&paramsDto.getSearchValue1()!=null){
				//如果开始时间和结束时间都不为空
				startTime=paramsDto.getSearchValue0();//输入的开始时间
				endTime=paramsDto.getSearchValue1();//输入的结束时间
				
		}
			if(listtype!=null){//不是企业管理员，需要权限
				
					if(listtype.size()>0){
							List<PfmChargeRecordDto> listdto=chargeRecordService.selectChargeRecordTotal(startTime,endTime,carNumber,listcarType,size,listtype);
							iTotal=listdto.size();
							if(iTotal>0){
								//当天有数据查到
								if(paramsDto.getLength()==-1){
									paramsDto.setLength(iTotal);  //查询所有记录
								}
								if(!paramsDto.getOrder().equals("  order by insert_Time asc")&&!paramsDto.getOrder().equals("  order by insert_Time desc")){
				                	order=paramsDto.getOrder();
				                }
								insertTime=chargeRecordService.selectInserttime(startTime,endTime,carNumber,listcarType,size,listtype);
								dtolist=chargeRecordService.selectChargeDayListByPage(startTime,endTime,carNumber,listcarType,size,paramsDto.getLength(),paramsDto.getStart(),listtype,order);
								
								if(startTime!=null&&endTime!=null){
									String strstart=DateUtil.YMDsdf.format(startTime);
									String strend=DateUtil.YMDsdf.format(endTime);
									if(strstart.equals(strend)){
										//开始结束是同一天
										insertTime=startTime;
										
									}else{
										insertTime=startTime;
										SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
										strday=sdf.format(startTime)+"-"+sdf.format(endTime);
									}
									
									
								
								}
								
								
								for(PfmChargeRecordDto temp:dtolist){
									if(insertTime!=null){
										temp.setInsertTime(insertTime,0,strday);
										list.add(temp);
									}
									
									
								}
							}
							
							
							
							
							
							
						
						
					}
					
				
				
				
				
			}else{
				//是管理员
				List<PfmChargeRecordDto> listdto=chargeRecordService.selectChargeRecordTotal(startTime,endTime,carNumber,null,size,listcarType);
				iTotal=listdto.size();
				if(iTotal>0){
					//当天有数据查到
					if(paramsDto.getLength()==-1){
						paramsDto.setLength(iTotal);  //查询所有记录
					}
					if(!paramsDto.getOrder().equals("  order by insert_Time asc")&&!paramsDto.getOrder().equals("  order by insert_Time desc")){
	                	order=paramsDto.getOrder();
	                }
					if(startTime!=null&&endTime!=null){
						String strstart=DateUtil.YMDsdf.format(startTime);
						String strend=DateUtil.YMDsdf.format(endTime);
						if(strstart.equals(strend)){
							//开始结束是同一天
							insertTime=startTime;
							
						}else{
							insertTime=startTime;
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
							strday=sdf.format(startTime)+"-"+sdf.format(endTime);
						}
						
						
					
					}
					dtolist=chargeRecordService.selectChargeDayListByPage(startTime,endTime,carNumber,null,size,paramsDto.getLength(),paramsDto.getStart(),listcarType,order);
					for(PfmChargeRecordDto temp:dtolist){
						if(insertTime!=null){
							temp.setInsertTime(insertTime,0,strday);
							list.add(temp);
						}
						
						
					}
				}
				
				
				
			}
			
			
		
		DataTableDto data = new DataTableDto(); // 返回数据封装
		data.setDataList(list);
		data.setDraw(paramsDto.getDraw());
		data.setRecordsFiltered(iTotal);
		data.setRecordsTotal(iTotal);
		AjaxUtil.ajaxWriteDataTable(data, response);
		}
	}
	
	/*
	 * 消费月报表查询
	 */
	
	@RequestMapping("/report/Czreport/selectChargeMonthAllListByPage.do")
	@ResponseBody
	public void selectMonthAllListByPage() throws ParseException, JSONException{
		
		List<Integer> listtype=authorityGetter.carTypeAuth();
		ReportPageParamsDto paramsDto=ReportPageParamsDto.GetParamsDtoByRequest(request);
		//String val=request.getParameter("columns[0][search][value]");
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
	    //List<Integer> listtypeId=null;
	    String size=null;
	    List<PfmChargeRecordDto> dtolist=null;
	    //String result=null;//查询信息中是否有查询结果，null则为没有结果
	    Calendar cal=Calendar.getInstance();
	    //Integer typeId=null;
	    Date insertTime=null;
	    //List<PfmChargeRecordDto> Alldto=null;
	    List<Integer> listcarType=null;//选择的车辆类型
	    Integer month=null;//开始时间月份
	    Integer endmonth=null;//结束时间月份
	    Integer year=null;//年份
	    Integer nowMonth=null;//本月份
	    String strmonth=null;//当两个时间都不为空时拼接时间
	    String order=null;
		//String hasPermission=null;
	    List<PfmChargeRecordDto> list=new ArrayList<PfmChargeRecordDto>();
	    if(paramsDto.getSearchValue2()!=null&&!paramsDto.getSearchValue2().equals("")){
			carNumber=paramsDto.getSearchValue2();
		}
	    if(paramsDto.getListType()!=null&&!paramsDto.getListType().equals("")){
			listcarType=paramsDto.getListType();
		}
		if(paramsDto.getSearchValue0()==null&&paramsDto.getSearchValue1()==null){
			//页面刷新的时候
			nowMonth=1;
			Date curcent=new Date();
			cal.setTime(curcent);
			month=cal.get(Calendar.MONTH)+1;//当前月份
			year=cal.get(Calendar.YEAR);//当前年份
		    
			
				
		}
			//只有开始时间
			if(paramsDto.getSearchValue0()!=null&&paramsDto.getSearchValue1()==null){
				startTime=paramsDto.getSearchValue0();
				cal.setTime(startTime);
				month=cal.get(Calendar.MONTH)+1;//当前月份
				year=cal.get(Calendar.YEAR);//当前年份
			}
			//只有结束时间
			if(paramsDto.getSearchValue1()!=null&&paramsDto.getSearchValue0()==null){
				endTime=paramsDto.getSearchValue1();
				cal.setTime(endTime);
				month=cal.get(Calendar.MONTH)+1;//当前月份
				year=cal.get(Calendar.YEAR);//当前年份
			}
			if(paramsDto.getSearchValue0()!=null&&paramsDto.getSearchValue1()!=null){
				//如果开始时间和结束时间都不为空
				startTime=paramsDto.getSearchValue0();//输入的开始时间
				endTime=paramsDto.getSearchValue1();//输入的结束时间
				cal.setTime(startTime);
				month=cal.get(Calendar.MONTH)+1;//当前月份
				year=cal.get(Calendar.YEAR);//当前年份
				cal.setTime(endTime);
				endmonth=cal.get(Calendar.MONTH)+1;
			    
				
		}
			if(listtype!=null){//不是企业管理员，需要权限
				
					
					if(listtype.size()>0){
						//有多个权限
						/*listtypeId=listtype;
						paramsDto.setListType(listtype);
						for(int i=0;i<listtypeId.size();i++){
							//遍历每一个权限
							
								typeId=listtypeId.get(i);*/
								
						    List<PfmChargeRecordDto> listdto =chargeRecordService.selectMonthRecordTotal(year,month,carNumber,listcarType,size,startTime,endTime,nowMonth,endmonth,listtype);
						    iTotal=listdto.size();
						    if(iTotal>0){
								//当天有数据查到
								if(paramsDto.getLength()==-1){
									paramsDto.setLength(iTotal);  //查询所有记录
								}
								if(!paramsDto.getOrder().equals("  order by insert_Time asc")&&!paramsDto.getOrder().equals("  order by insert_Time desc")){
				                	order=paramsDto.getOrder();
				                }
								if(startTime!=null&&endTime==null){
									   insertTime=startTime;	
									}
									if(startTime==null&&endTime!=null){
										insertTime=endTime;
									}
									if(startTime==null&&endTime==null){
										insertTime=new Date();
									}
									if(startTime!=null&&endTime!=null){
										SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月份");
										strmonth=sdf.format(startTime)+"-"+sdf.format(endTime);
										insertTime=new Date();
									}
									dtolist=chargeRecordService.selectMonthListByPage(year,month,carNumber,listcarType,size,paramsDto.getLength(),paramsDto.getStart(),startTime,endTime,nowMonth,endmonth,listtype,order);
									for(PfmChargeRecordDto temp:dtolist){
										if(insertTime!=null){
											temp.setInsertTime(insertTime,1,strmonth);
											list.add(temp);
										}
										
										
									}
						    }
						   
							
							
						//}
						
					}
					
				
				
				
				
			}else{
				//是管理员
				
				
				List<PfmChargeRecordDto> listdto=chargeRecordService.selectMonthRecordTotal(year,month,carNumber,listcarType,size,startTime,endTime,nowMonth,endmonth,listtype);
				iTotal=listdto.size();
				if(iTotal>0){
					//当天有数据查到
					if(paramsDto.getLength()==-1){
						paramsDto.setLength(iTotal);  //查询所有记录
					}
					if(!paramsDto.getOrder().equals("  order by insert_Time asc")&&!paramsDto.getOrder().equals("  order by insert_Time desc")){
	                	order=paramsDto.getOrder();
	                }
					if(startTime!=null&&endTime==null){
						   insertTime=startTime;	
						}
						if(startTime==null&&endTime!=null){
							insertTime=endTime;
						}
						if(startTime==null&&endTime==null){
							insertTime=new Date();
						}
						if(startTime!=null&&endTime!=null){
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月份");
							strmonth=sdf.format(startTime)+"-"+sdf.format(endTime);
							insertTime=new Date();
						}
					
						dtolist=chargeRecordService.selectMonthListByPage(year,month,carNumber,listcarType,size,paramsDto.getLength(),paramsDto.getStart(),startTime,endTime,nowMonth,endmonth,listtype,order);
						for(PfmChargeRecordDto temp:dtolist){
							if(insertTime!=null){
								temp.setInsertTime(insertTime,1,strmonth);
								list.add(temp);
							}
							
						}
				}
				
				
			}
			
			
		
		DataTableDto data = new DataTableDto(); // 返回数据封装
		data.setDataList(list);
		data.setDraw(paramsDto.getDraw());
		data.setRecordsFiltered(iTotal);
		data.setRecordsTotal(iTotal);
		AjaxUtil.ajaxWriteDataTable(data, response);
		}
	}
	
	/*
	 * 消费年报表查询
	 */
	
	@RequestMapping("/report/Czreport/selectChargeYearAllListByPage.do")
	@ResponseBody
	public void selectYearAllListByPage() throws ParseException, JSONException{
		
		List<Integer> listtype=authorityGetter.carTypeAuth();
		ReportPageParamsDto paramsDto=ReportPageParamsDto.GetParamsDtoByRequest(request);
		//String val=request.getParameter("columns[0][search][value]");
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
	    //List<Integer> listtypeId=null;
	    String size=null;
	    List<PfmChargeRecordDto> dtolist=null;
	    //String result=null;//查询信息中是否有查询结果，null则为没有结果
	    Calendar cal=Calendar.getInstance();
	    //Integer typeId=null;
	    Date insertTime=null;
	    String order=null;
	    //List<PfmChargeRecordDto> Alldto=null;
	    List<Integer> listcarType=null;//选择的车辆类型
	   // Integer month=null;//开始时间月份
	    //Integer endmonth=null;//结束时间月份
	    Integer startyear=null;//开始年份
	    Integer endyear=null;//结束年份
	    Integer nowYear=null;//本年(当时间都为空是使用)
	    String strmonth=null;//当两个时间都不为空时拼接时间
		//String hasPermission=null;
	    List<PfmChargeRecordDto> list=new ArrayList<PfmChargeRecordDto>();
	    if(paramsDto.getSearchValue2()!=null&&!paramsDto.getSearchValue2().equals("")){
			carNumber=paramsDto.getSearchValue2();
		}
	    if(paramsDto.getListType()!=null&&!paramsDto.getListType().equals("")){
			listcarType=paramsDto.getListType();
		}
		if(paramsDto.getSearchValue0()==null&&paramsDto.getSearchValue1()==null){
			//页面刷新的时候
			//nowYear=1;
			Date curcent=new Date();
			cal.setTime(curcent);
			nowYear=cal.get(Calendar.YEAR);//当前年份
		    
			
				
		}
			//只有开始时间
			if(paramsDto.getSearchValue0()!=null&&paramsDto.getSearchValue1()==null){
				startTime=paramsDto.getSearchValue0();
				cal.setTime(startTime);
				nowYear=cal.get(Calendar.YEAR);//当前年份
			}
			//只有结束时间
			if(paramsDto.getSearchValue1()!=null&&paramsDto.getSearchValue0()==null){
				endTime=paramsDto.getSearchValue1();
				cal.setTime(endTime);
				nowYear=cal.get(Calendar.YEAR);//当前年份
			}
			if(paramsDto.getSearchValue0()!=null&&paramsDto.getSearchValue1()!=null){
				//如果开始时间和结束时间都不为空
				startTime=paramsDto.getSearchValue0();//输入的开始时间
				endTime=paramsDto.getSearchValue1();//输入的结束时间
				cal.setTime(startTime);
				startyear=cal.get(Calendar.YEAR);//当前年份
				cal.setTime(endTime);
				endyear=cal.get(Calendar.YEAR);//结束年份
				
		}
			
			if(listtype!=null){//不是企业管理员，需要权限
					
					if(listtype.size()>0){
						//有多个权限
						    List<PfmChargeRecordDto> listdto =chargeRecordService.selectYearRecordTotal(startyear,carNumber,listcarType,size,startTime,endTime,endyear,listtype,nowYear);
						    iTotal=listdto.size();
						    if(iTotal>0){
								//当天有数据查到
								if(paramsDto.getLength()==-1){
									paramsDto.setLength(iTotal);  //查询所有记录
								}
								if(!paramsDto.getOrder().equals("  order by insert_Time asc")&&!paramsDto.getOrder().equals("  order by insert_Time desc")){
				                	order=paramsDto.getOrder();
				                }
								if(startTime!=null&&endTime==null){
									   insertTime=startTime;	
									}
									if(startTime==null&&endTime!=null){
										insertTime=endTime;
									}
									if(startTime==null&&endTime==null){
										insertTime=new Date();
									}
									if(startTime!=null&&endTime!=null){
										SimpleDateFormat sdf=new SimpleDateFormat("yyyy年");
										strmonth=sdf.format(startTime)+"-"+sdf.format(endTime);
										insertTime=new Date();
									}
									//dtolist=chargeRecordService.selectYearListByPage(startyear,endyear,carNumber,listcarType,size,paramsDto.getLength(),paramsDto.getStart(),startTime,endTime,nowYear,listtype,order);
									dtolist=chargeRecordService.selectYearListByPage(startyear,endyear,carNumber,listcarType,size,paramsDto.getLength(),paramsDto.getStart(),startTime,endTime,nowYear,listtype,order);
									for(PfmChargeRecordDto temp:dtolist){
										if(insertTime!=null){
											temp.setInsertTime(insertTime,2,strmonth);
											list.add(temp);
										}
										
										
									}
							}
						   
							
							
						
						
					}
					else{
						//无任何权限
						size="1";
					}
				
				
				
				
			}else{
				//是管理员
				List<PfmChargeRecordDto> listdto =chargeRecordService.selectYearRecordTotal(startyear,carNumber,listcarType,size,startTime,endTime,endyear,listtype,nowYear);
				 iTotal=listdto.size();
				    if(iTotal>0){
						//当天有数据查到
						if(paramsDto.getLength()==-1){
							paramsDto.setLength(iTotal);  //查询所有记录
						}
						if(!paramsDto.getOrder().equals("  order by insert_Time asc")&&!paramsDto.getOrder().equals("  order by insert_Time desc")){
		                	order=paramsDto.getOrder();
		                }
						if(startTime!=null&&endTime==null){
							   insertTime=startTime;	
							}
							if(startTime==null&&endTime!=null){
								insertTime=endTime;
							}
							if(startTime==null&&endTime==null){
								insertTime=new Date();
							}
							if(startTime!=null&&endTime!=null){
								SimpleDateFormat sdf=new SimpleDateFormat("yyyy年");
								strmonth=sdf.format(startTime)+"-"+sdf.format(endTime);
								insertTime=new Date();
							}
							dtolist=chargeRecordService.selectYearListByPage(startyear,endyear,carNumber,listcarType,size,paramsDto.getLength(),paramsDto.getStart(),startTime,endTime,nowYear,listtype,order);
							for(PfmChargeRecordDto temp:dtolist){
								if(insertTime!=null){
									temp.setInsertTime(insertTime,2,strmonth);
									list.add(temp);
								}
								
							}
				    }
			    		
			}
			
			
		
		DataTableDto data = new DataTableDto(); // 返回数据封装
		data.setDataList(list);
		data.setDraw(paramsDto.getDraw());
		data.setRecordsFiltered(iTotal);
		data.setRecordsTotal(iTotal);
		AjaxUtil.ajaxWriteDataTable(data, response);
		}
		
	}
	
	/*
	 * 消费日报表导出
	 */
	@RequestMapping("/report/Czreport/exportChargeDay/{formdata}.do")
	@ResponseBody
	public void ExportDay(@PathVariable("formdata") String formdata) throws ParseException, NumberFormatException, JSONException{
		//String a=formdata;
		Date startTime=null;
		Date endTime=null;
		String carNumber=null;
		List<Integer> list=null;
		//Integer typeId=null;
		String order=null;
		List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
		if(!formdata.equals("")){
			JSONObject json=new JSONObject(formdata);
			//只有开始时间
			if(!json.getString("startTime").equals("")&&json.getString("endTime").equals("")){
				
				startTime=DateUtil.df.parse(json.getString("startTime"));
				String strendTime=DateUtil.YMDsdf.format(startTime)+" 23:59:59";
				endTime=DateUtil.df.parse(strendTime);
			}
			//只有结束时间
			if(!json.getString("endTime").equals("")&&json.getString("startTime").equals("")){
				endTime=DateUtil.df.parse(json.getString("endTime"));
				String strStartTime=DateUtil.YMDsdf.format(startTime)+" 23:59:59";
				startTime=DateUtil.df.parse(strStartTime);
			}
			//开始结束时间都有
			if(!json.getString("endTime").equals("")&&!json.getString("startTime").equals("")){
				endTime=DateUtil.df.parse(json.getString("endTime"));
				startTime=DateUtil.df.parse(json.getString("startTime"));
				
			}
			//开始结束时间都没有
			if(json.getString("endTime").equals("")&&json.getString("startTime").equals("")){
				startTime=new Date();
				String strendTime=DateUtil.YMDsdf.format(startTime)+" 23:59:59";
				endTime=DateUtil.df.parse(strendTime);
			}
			if(json.has("carNumber")&&!json.getString("carNumber").equals("")){
				carNumber=json.getString("carNumber");
			}
			if(json.has("carType")&&!json.getString("carType").equals("")){
				
				if(json.get("carType") instanceof JSONArray){
					//如果车辆类型有多个
					JSONArray a=json.getJSONArray("carType");
					 list=new ArrayList();
					for(int i=0;i<a.length();i++){
						list.add( Integer.valueOf((String) a.get(i)));
					}
					
			}else{
				//车辆类型只有一个
				list=new ArrayList();
				list.add(Integer.valueOf(json.getString("carType").toString()));
				
			}
				
			
		}
		
			if((json.has("order1")&&!json.getString("order1").equals(""))&&(json.has("order2")&&!json.getString("order2").equals(""))){
		    	if(!json.getString("order1").equals("insertTime")){
		    		order=MessageFormat.format("  order by {0} {1}", 
							StringUtil.objNameToTableColName(json.getString("order1").toString())
							, json.getString("order2").toString());
			    	
		    	}
		    	
		    }
			
			
		}
		
		List<Integer> listtype=authorityGetter.carTypeAuth();
		//List<Integer> listTypeId=null;
		String size=null;
		if(listtype!=null){//不是企业管理员，需要权限
			
			if(listtype.size()>0){
				//有多个权限
				/*listTypeId=listtype;
				for(int i=0;i<listTypeId.size();i++){
					//遍历每一个权限
					
						typeId=listTypeId.get(i);*/
					
						List<Map<String,Object>> listMap=chargeRecordService.exportChargeDay(startTime,endTime,list,size,listtype,carNumber,order);
					    for(Map temp:listMap){
						data.add(temp);
					    }
					
				//}
				
			}
			else{
				//无任何权限
				size="1";
				List<Map<String,Object>> listMap=chargeRecordService.exportChargeDay(startTime,endTime,list,size,listtype,carNumber,order);
				for(Map temp:listMap){
					data.add(temp);
				}
			}
		
		
		
		
	}else{
		//是管理员
		//data=chargeRecordService.exportDay(startTime,endTime,typeId,size,list,carNumber);
		
		data=chargeRecordService.exportChargeDay(startTime,endTime,list,size,listtype,carNumber,order);
		
	}
	
		
		if(data.size()!=0){
			for(int i=0;i<data.size();i++){
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
				String time=null;
				String strStart=DateUtil.YMDsdf.format(startTime);
				String strEnd=DateUtil.YMDsdf.format(endTime);
				if(strStart.equals(strEnd)){
					time=strStart;
				}else{
					time=sdf.format(startTime)+"-"+sdf.format(endTime);
				}
				
				data.get(i).put("insert_time", time);
			}
		}
		
		String[] head = {"时间","车辆类型","记录数量","总计金额"};
		String[] column={"insert_time","type_name","czcount","czfee"};
		exTemplate.Export(data, head, column);
		
	}
	
	/*
	 * 消费月报表导出
	 */
	@RequestMapping("/report/Czreport/exportChargeMonth/{formdata}.do")
	@ResponseBody
	public void ExportMonth(@PathVariable("formdata") String formdata) throws ParseException, JSONException{
		
		List<Integer> listtype=authorityGetter.carTypeAuth();
		//List<Integer> listTypeId=null;
		String size=null;
		String carNumber=null;
		List<Integer> list=null;
		//Integer typeId=null;
		String order=null;
		List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
		Date startTime=null;
		Date endTime=null;
		Integer nowMonth=null;//本月份
		Integer year=null;//年份
		if(!formdata.equals("")){
			JSONObject json=new JSONObject(formdata);
			//只有开始时间
			if(!json.getString("startTime").equals("")&&json.getString("endTime").equals("")){
				
				startTime=DateUtil.df.parse(json.getString("startTime"));
				Calendar cal=Calendar.getInstance();
				cal.setTime(startTime);
				nowMonth=cal.get(Calendar.MONTH)+1;//当前月份
				year=cal.get(Calendar.YEAR);//当前年
			}
			//只有结束时间
			if(!json.getString("endTime").equals("")&&json.getString("startTime").equals("")){
				endTime=DateUtil.df.parse(json.getString("endTime"));
				Calendar cal=Calendar.getInstance();
				cal.setTime(endTime);
				nowMonth=cal.get(Calendar.MONTH)+1;//当前月份
				year=cal.get(Calendar.YEAR);//当前年
			}
			//开始结束时间都有
			if(!json.getString("endTime").equals("")&&!json.getString("startTime").equals("")){
				endTime=DateUtil.df.parse(json.getString("endTime"));
				startTime=DateUtil.df.parse(json.getString("startTime"));
				Calendar cal=Calendar.getInstance();
				cal.setTime(startTime);
				year=cal.get(Calendar.YEAR);//当前年
			}
			//开始结束时间都没有
			if(json.getString("endTime").equals("")&&json.getString("startTime").equals("")){
				Calendar cal=Calendar.getInstance();
				cal.setTime(new Date());
				nowMonth=cal.get(Calendar.MONTH)+1;//当前月份
				year=cal.get(Calendar.YEAR);//当前年份
			}
			if(json.has("carNumber")&&!json.getString("carNumber").equals("")){
				carNumber=json.getString("carNumber");
			}
			if(json.has("carType")&&!json.getString("carType").equals("")){
				
				if(json.get("carType") instanceof JSONArray){
					//如果车辆类型有多个
					JSONArray a=json.getJSONArray("carType");
					 list=new ArrayList();
					for(int i=0;i<a.length();i++){
						list.add( Integer.valueOf((String) a.get(i)));
					}
					
			}else{
				//车辆类型只有一个
				list=new ArrayList();
				list.add(Integer.valueOf(json.getString("carType").toString()));
				
			}
				
			
		}
			if((json.has("order1")&&!json.getString("order1").equals(""))&&(json.has("order2")&&!json.getString("order2").equals(""))){
		    	if(!json.getString("order1").equals("insertTime")){
		    		order=MessageFormat.format("  order by {0} {1}", 
							StringUtil.objNameToTableColName(json.getString("order1").toString())
							, json.getString("order2").toString());
			    	
		    	}
		    	
		    }
			
			
		}else{
			//什么都没有
			Calendar cal=Calendar.getInstance();
			cal.setTime(new Date());
			nowMonth=cal.get(Calendar.MONTH)+1;//当前月份
			year=cal.get(Calendar.YEAR);//当前年份
		}
		
		if(listtype!=null){//不是企业管理员，需要权限
			if(listtype.size()>0){
				//listTypeId=listtype;
				
				if(listtype.size()>0){
					//有多个权限
					/*listTypeId=listtype;
					for(int i=0;i<listTypeId.size();i++){
						//遍历每一个权限
						
							typeId=listTypeId.get(i);*/
						
							List<Map<String,Object>> listMap=chargeRecordService.exportChargeMonth(startTime,endTime,list,size,listtype,carNumber,nowMonth,year,order);
						    for(Map temp:listMap){
							data.add(temp);
						    }
						
					//}
					
				}
				else{
					//无任何权限
					size="1";
					List<Map<String,Object>> listMap=chargeRecordService.exportChargeMonth(startTime,endTime,list,size,listtype,carNumber,nowMonth,year,order);
					for(Map temp:listMap){
						data.add(temp);
					}
				}
			
				
				
				
			}/*else{
				
				data=czReportService.exportDay(startTime,endTime,typeId,size,list,carNumber);
			}*/
		}else{
			//是管理员
			data=chargeRecordService.exportChargeMonth(startTime,endTime,list,size,listtype,carNumber,nowMonth,year,order);
			
			
		}
		if(data.size()!=0){
			for(int i=0;i<data.size();i++){
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月份");
				String time=null;
				if(startTime==null&&endTime==null){
				    time=sdf.format(new Date());
				}else if(startTime!=null&&endTime!=null){
					time=sdf.format(startTime)+"-"+sdf.format(endTime);
				}else if(endTime==null){
					 time=sdf.format(startTime);
				}else{
					 time=sdf.format(endTime);
				}
				data.get(i).put("insert_time", time);
				}
				
				
			}
		
		String[] head = {"时间","车辆类型","记录数量","总计金额"};
		String[] column={"insert_time","type_name","czcount","czfee"};
		exTemplate.Export(data, head, column);

	}
	
	
	/*
	 * 消费年报表导出
	 */
	@RequestMapping("/report/Czreport/exportChargeYear/{formdata}.do")
	@ResponseBody
	public void ExportYear(@PathVariable("formdata") String formdata) throws ParseException, JSONException{
		List<Integer> listtype=authorityGetter.carTypeAuth();
		//List<Integer> listTypeId=null;
		String size=null;
		String carNumber=null;
		List<Integer> list=null;
		//Integer typeId=null;
		List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
		Date startTime=null;
		Date endTime=null;
		Integer startyear=null;//开始年份
	    Integer endyear=null;//结束年份
	    Integer nowYear=null;//本年(当时间都为空是使用)
	    String order=null;
	    Calendar cal=Calendar.getInstance();
		if(!formdata.equals("")){
			JSONObject json=new JSONObject(formdata);
			//只有开始时间
			if(!json.getString("startTime").equals("")&&json.getString("endTime").equals("")){
				startTime=DateUtil.df.parse(json.getString("startTime"));
				cal.setTime(startTime);
				nowYear=cal.get(Calendar.YEAR);//当前年份
			
			}
			//只有结束时间
			if(!json.getString("endTime").equals("")&&json.getString("startTime").equals("")){
				endTime=DateUtil.df.parse(json.getString("endTime"));
				cal.setTime(endTime);
				nowYear=cal.get(Calendar.YEAR);//当前年份
			
			}
			//开始结束时间都有
			if(!json.getString("endTime").equals("")&&!json.getString("startTime").equals("")){
				startTime=DateUtil.df.parse(json.getString("startTime"));//输入的开始时间
				endTime=DateUtil.df.parse(json.getString("endTime"));//输入的结束时间
				cal.setTime(startTime);
				startyear=cal.get(Calendar.YEAR);//当前年份
				cal.setTime(endTime);
				endyear=cal.get(Calendar.YEAR);//结束年份
			
			}
			//开始结束时间都没有
			if(json.getString("endTime").equals("")&&json.getString("startTime").equals("")){
				Date curcent=new Date();
				cal.setTime(curcent);
				nowYear=cal.get(Calendar.YEAR);//当前年份
			}
			if(json.has("carNumber")&&!json.getString("carNumber").equals("")){
				carNumber=json.getString("carNumber");
			}
			if(json.has("carType")&&!json.getString("carType").equals("")){
				
				if(json.get("carType") instanceof JSONArray){
					//如果车辆类型有多个
					JSONArray a=json.getJSONArray("carType");
					 list=new ArrayList();
					for(int i=0;i<a.length();i++){
						list.add( Integer.valueOf((String) a.get(i)));
					}
					
			}else{
				//车辆类型只有一个
				list=new ArrayList();
				list.add(Integer.valueOf(json.getString("carType").toString()));
				
			}
				
			
		}
			if((json.has("order1")&&!json.getString("order1").equals(""))&&(json.has("order2")&&!json.getString("order2").equals(""))){
		    	if(!json.getString("order1").equals("insertTime")){
		    		order=MessageFormat.format("  order by {0} {1}", 
							StringUtil.objNameToTableColName(json.getString("order1").toString())
							, json.getString("order2").toString());
			    	
		    	}
		    	
		    }
			
		}else{
			//什么都没有
			Date curcent=new Date();
			cal.setTime(curcent);
			nowYear=cal.get(Calendar.YEAR);//当前年份
		}
		if(listtype!=null){//不是企业管理员，需要权限
			if(listtype.size()>0){
				//listTypeId=listtype;
				
				if(listtype.size()>0){
					//有多个权限
					/*listTypeId=listtype;
					for(int i=0;i<listTypeId.size();i++){
						//遍历每一个权限
						
							typeId=listTypeId.get(i);*/
							List<Map<String,Object>> listMap=chargeRecordService.exportChargeYear(startyear,carNumber,list,size,startTime,endTime,endyear,listtype,nowYear,order);
						    for(Map temp:listMap){
							data.add(temp);
						    }
						
					//}
					
				}
				else{
					//无任何权限
					size="1";
					List<Map<String,Object>> listMap=chargeRecordService.exportChargeYear(startyear,carNumber,list,size,startTime,endTime,endyear,listtype,nowYear,order);
					for(Map temp:listMap){
						data.add(temp);
					}
				}
			
				
				
				
			}
		}else{
			//是管理员
			data=chargeRecordService.exportChargeYear(startyear,carNumber,list,size,startTime,endTime,endyear,listtype,nowYear,order);
			
			
		}
		//List<Map<String,Object>> data=czReportService.exportYear(year,listTypeId,size);
		if(data.size()!=0){
			for(int i=0;i<data.size();i++){
				String time=null;
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy年");
				if(startTime==null&&endTime==null){
				    time=sdf.format(new Date());
				}else if(startTime!=null&&endTime!=null){
					time=sdf.format(startTime)+"-"+sdf.format(endTime);
				}else if(endTime==null){
					 time=sdf.format(startTime);
				}else{
					 time=sdf.format(endTime);
				}
				
				
				data.get(i).put("insert_time",time);
				
			}
		}
		
		String[] head = {"时间","车辆类型","记录数量","总计金额"};
		String[] column={"insert_time","type_name","czcount","czfee"};
		exTemplate.Export(data, head, column);
		
	}
		
	

}
