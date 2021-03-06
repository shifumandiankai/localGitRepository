package com.wadejerry.scms.modules.pfm.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wadejerry.scms.frame.constant.ConstParamLog;
import com.wadejerry.scms.frame.entity.DataTableDto;
import com.wadejerry.scms.frame.entity.LoginInfo;
import com.wadejerry.scms.frame.entity.OperateResult;
import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.frame.log.LogRecord;
import com.wadejerry.scms.modules.pfm.dao.PfmSpecialCarMapper;
import com.wadejerry.scms.modules.pfm.dto.SpecialNumDto;
import com.wadejerry.scms.modules.pfm.model.PfmSpecialCar;
import com.wadejerry.scms.modules.pfm.service.SpecialNumberService;
import com.wadejerry.scms.utils.AjaxUtil;
import com.wadejerry.scms.utils.Log.LogManager;
import com.wadejerry.scms.utils.json.JacksonUtil;

@Controller
public class SpecialCarNumController {
	
	@Autowired 
	private SpecialNumberService specialService;
	@Autowired 
	private PfmSpecialCarMapper pfmSpecialCarMapper ;
	@Autowired
	private LogRecord logRecord;
	@RequestMapping("/special/getspecialInfo.do")
	@ResponseBody
	public void GetScreeninfoByPage(HttpServletRequest request,HttpServletResponse response){
		PageParamsDto paramsDto = new PageParamsDto(request); // 获取分 页参数
		int iTotal=0;
		List<SpecialNumDto> dtolist=null;
		if(!SecurityUtils.getSubject().isPermitted("SpecialSee")){
		}else{
			iTotal=specialService.getRecordTotal(paramsDto);
		if(iTotal>0){
			if(paramsDto.getLength()==-1){
				paramsDto.setLength(iTotal);  //查询所有记录
			}
			dtolist=specialService.getSpecialListByPage(paramsDto);

		}
		}
		DataTableDto data = new DataTableDto(); // 返回数据封装
		
		data.setDataList(dtolist);
		data.setDraw(paramsDto.getDraw());
		data.setRecordsFiltered(iTotal);
		data.setRecordsTotal(iTotal);
		AjaxUtil.ajaxWriteDataTable(data, response);
	}
	
	
		@RequestMapping("/special/delspecialInfo.do")
		@ResponseBody
		public void delSpecial(HttpServletRequest request,HttpServletResponse response){
		
			String jsonArrayStr = request.getParameter("condition");
			try {
				List<SpecialNumDto> delDtoList = JacksonUtil.json2list(jsonArrayStr, SpecialNumDto.class);
				specialService.delete(delDtoList);
				
			} catch (Exception e) {
				LogManager.logException(e);
				AjaxUtil.ajaxWrite(false, e.getMessage(), response); // 返回失败
				return;
			}
			AjaxUtil.ajaxWrite(true,"", response); // 返回成功
		
			
		}
		
			
		@RequestMapping("/special/addspecialInfo.do")
		@ResponseBody
		public void editSpecial(HttpServletRequest request,HttpServletResponse response){
			String condition = request.getParameter("condition");
			OperateResult result= new OperateResult();
			try{
			Map<String,Object> conditionMap = JacksonUtil.json2map(condition);
			if(conditionMap.get("pfmSpecialCarId").equals("")){//添加
				result.setMsg("添加失败");
				PfmSpecialCar special = new PfmSpecialCar();
				special.setBimCompanyId(LoginInfo.getCompanyId());
				special.setCarNumber(conditionMap.get("carNumber").toString());
				special.setInsertTime(new Date());
				special.setIsDel(0);
				special.setUpdateTime(new Date());
				special.setUserName(LoginInfo.getLoginName());
				special.setNote(conditionMap.get("note").toString());
				pfmSpecialCarMapper.insertSelective(special);
				logRecord.recordLog(ConstParamLog.LOG_MODULE_PFM_SPECIALCARNUM, ConstParamLog.LOG_OPER_ADD, ConstParamLog.LOG_TYPE_CONFIG, special.getCarNumber());

			}else{
				PfmSpecialCar special = new PfmSpecialCar();
				result.setMsg("修改失败");
				special.setCarNumber(conditionMap.get("carNumber").toString());
				special.setPfmSpecialCarId(Integer.parseInt(conditionMap.get("pfmSpecialCarId").toString()));
				special.setUpdateTime(new Date());
				special.setNote(conditionMap.get("note").toString());
				pfmSpecialCarMapper.updateByPrimaryKeySelective(special);
				logRecord.recordLog(ConstParamLog.LOG_MODULE_PFM_SPECIALCARNUM, ConstParamLog.LOG_OPER_UPDATE, ConstParamLog.LOG_TYPE_CONFIG, special.getCarNumber());

			}
			result.setResult(true);
			result.setMsg("");
			}
			catch(Exception e){
				result.setResult(false);
				
			}
			AjaxUtil.ajaxWrite(result, response);
		}
		
		
		
		
		@RequestMapping("/special/validatecarnum")
		@ResponseBody
		public void validateCarnum(HttpServletRequest request,HttpServletResponse response){
			String carNum = request.getParameter("carNum");
			String flag = request.getParameter("flag");
			String pfmSpecialCarId = request.getParameter("pfmSpecialCarId");
			if(flag.equals("0")){
				if(pfmSpecialCarMapper.selectIfHasCarNum(carNum, LoginInfo.getCompanyId())>0){
					AjaxUtil.ajaxWrite(false,"已存在此特殊车牌", response);
				}
			}
			else{
				if(pfmSpecialCarMapper.selectIfHasCarNumExcepSelf(carNum, LoginInfo.getCompanyId(), Integer.parseInt(pfmSpecialCarId))>0){
					AjaxUtil.ajaxWrite(false,"已存在此特殊车牌", response);
				}
			}
			
			AjaxUtil.ajaxWrite(true,"", response);
			
		}
}
