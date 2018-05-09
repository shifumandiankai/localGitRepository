package com.wadejerry.scms.modules.dvm.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wadejerry.scms.frame.entity.DataTableDto;
import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.dvm.dto.ApplayDto;
import com.wadejerry.scms.utils.AjaxUtil;

@Controller
public class ApplyController {

	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpServletRequest request;
	
	/**
	* 分页获取申请单信息
	* @author zhanying 2017年1月6日 上午9:30:22
	* @param   
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	@RequestMapping("/apply/getApplyInfo.do")
	@ResponseBody
	public void GetApplyInfoByPage()
	{
		PageParamsDto paramsDto = new PageParamsDto(request); //获取分页参数
		List<ApplayDto> list =new ArrayList<ApplayDto>();
		ApplayDto dto = null;
		long order =  (new Date()).getTime();
		for (int i = 0; i < 10; i++) {
			order = order+1;
			dto = new ApplayDto();
			dto.setOrderNum(String.valueOf(order));
			dto.setPersonId(1);
			dto.setPersonCode("001");
			dto.setPersonName("李天");
			dto.setDeptId(1);
			dto.setDeptName("后勤部");
			dto.setDeptCode("001");
			dto.setCarId(1);
			String str = String.format("%04d", i);    
			dto.setCarNumber("苏DW"+str);
			Calendar c = Calendar.getInstance();
			c.add(Calendar.HOUR, i);
			dto.setUseCarTime( c.getTime());
			dto.setUseCarTimeLen(2.5f);
			dto.setApplayTime(new Date());
			dto.setStatus(0);
			dto.setStatusDes("等待审批");
			dto.setNote("");
			list.add(dto);
		}
		DataTableDto data = new DataTableDto();  //返回数据封装 
		data.setDataList(list);
		data.setDraw(paramsDto.getDraw());
		data.setRecordsFiltered(100);
		data.setRecordsTotal(100);
		AjaxUtil.ajaxWriteDataTable(data, response);
	}
}
