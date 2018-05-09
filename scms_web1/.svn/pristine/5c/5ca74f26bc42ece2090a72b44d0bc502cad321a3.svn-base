package com.wadejerry.scms.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.wadejerry.scms.frame.entity.DataTableDto;
import com.wadejerry.scms.frame.entity.OperateResult;
import com.wadejerry.scms.utils.json.JacksonUtil;

public class AjaxUtil {

	public static void ajaxWrite(String s, HttpServletResponse response) {
		PrintWriter pw = null;
		try {
			response.setContentType("text/html; charset=utf-8");
			pw = response.getWriter();
			pw.write(s);
		} catch (IOException e) {
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	public static void ajaxWrite(boolean b, HttpServletResponse response) {
		ajaxWrite(b + "", response);
	}

	public static void ajaxWrite(boolean result, String msg, HttpServletResponse response) {
		if (msg == null) {
			msg = "";
		}
		if (result == false && msg.trim().length() == 0) {
			msg = "Ajax operateing faild!";
		}
		ajaxWrite("{\"success\":" + result + ",\"msg\":\"" + msg + "\"}", response);
	}

	public static void ajaxWrite(OperateResult ajaxResult, HttpServletResponse response) {
		ajaxWrite(ajaxResult.isResult(), ajaxResult.getMsg(), response);
	}

	public static void ajaxWriteObject(Object o, HttpServletResponse response) {
		ajaxWrite(JacksonUtil.toJson(o), response);
	}

	public static void ajaxWriteObject(boolean result, Object o, HttpServletResponse response) {
		ajaxWrite("{\"success\":" + result + ",\"object\":" + JacksonUtil.toJson(o) + "}", response);
	}

	public static void ajaxWriteObject(OperateResult operateResult, Object o, HttpServletResponse response) {
		boolean success = false;
		String msg = "";
		if (operateResult != null) {
			success = operateResult.isResult();
			msg = operateResult.getMsg();
		}
		ajaxWrite("{\"success\":" + success + ",\"msg\":\"" + msg + "\"" + ",\"object\":" + JacksonUtil.toJson(o) + "}",
				response);
	}

	public static void ajaxWriteXml(String s, HttpServletResponse response) {
		PrintWriter pw = null;
		try {
			response.setContentType("text/xml; charset=utf-8");
			pw = response.getWriter();
			pw.write(s);
		} catch (IOException e) {
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	public static void ajaxWriteDataTable(DataTableDto data, HttpServletResponse response) {
		StringBuffer strData = new StringBuffer();
		if (data.getDataList() != null) {
			List<?> list = data.getDataList();
			
			for(int i =0;i <list.size();i++){
				if(i== 0){
					strData.append(JacksonUtil.toJson(list.get(i)));
				}else {
					strData.append(",").append(JacksonUtil.toJson(list.get(i)));
				}
			}
		}
		ajaxWrite("{\"draw\":" + data.getDraw() + 
				",\"recordsFiltered\":" + data.getRecordsFiltered()
				+ ",\"recordsFiltered\":" + data.getRecordsFiltered() +
				",\"data\":[" +  strData.toString() + "]}", response);
	}
}
