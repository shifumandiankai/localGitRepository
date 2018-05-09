package com.wadejerry.scms.modules.pfm.dto;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.wadejerry.scms.utils.date.DateUtil;

public class CzReportDto {
  private Integer pfmFeeRecId;
  private Date insertTime;
 /* private String carTypeName;*/
  private String typeName;
  private Integer czcount;
  private BigDecimal czfee;
  private Integer type;//报表的类型(0:日报表，1:月报表，2:年报表)
  private String strtime;//当两个时间都不为空时拼接时间
public String getInsertTime() {
	//return insertTime;
	String time=null;
	if(type==0&&strtime==null){
		//
		//time=DateUtil.YMDsdf.format(insertTime);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
		time=sdf.format(insertTime);
	}
	if(type==0&&strtime!=null){
		
		time=strtime;
	}
	if(type==1){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月份");
		
		time=sdf.format(insertTime);
	}
	if(type==1&&strtime!=null){
		time=strtime;
	}
   if(type==2){
	   SimpleDateFormat sdf=new SimpleDateFormat("yyyy年");
		
		time=sdf.format(insertTime);
   }
   if(type==2&&strtime!=null){
		time=strtime;
	}
	return time;
}
public void setInsertTime(Date insertTime,Integer type,String strtime) {
	this.insertTime = insertTime;
	this.type = type;
	this.strtime=strtime;
}
/*public String getCarTypeName() {
	return carTypeName;
}
public void setCarTypeName(String carTypeName) {
	this.carTypeName = carTypeName;
}*/

public BigDecimal getCzfee() {
	return czfee;
}

public String getTypeName() {
	return typeName;
}
public void setTypeName(String typeName) {
	this.typeName = typeName;
}
public Integer getCzcount() {
	return czcount;
}
public void setCzcount(Integer czcount) {
	this.czcount = czcount;
}
public void setCzfee(BigDecimal czfee) {
	this.czfee = czfee;
}
public Integer getPfmFeeRecId() {
	return pfmFeeRecId;
}
public void setPfmFeeRecId(Integer pfmFeeRecId) {
	this.pfmFeeRecId = pfmFeeRecId;
}

  
}
