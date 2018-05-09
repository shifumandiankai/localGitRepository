package com.wadejerry.scms.modules.sysconfig.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wadejerry.scms.frame.AppContext;
import com.wadejerry.scms.frame.constant.ConstParamLog;
import com.wadejerry.scms.frame.constant.user.ConstParamSysConfig;
import com.wadejerry.scms.frame.constant.user.ConstUser;
import com.wadejerry.scms.frame.entity.DataTableDto;
import com.wadejerry.scms.frame.entity.OperateResult;
import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.frame.log.LogRecord;
import com.wadejerry.scms.modules.sysconfig.dto.DatabaseBackUpFileDTO;
import com.wadejerry.scms.modules.sysconfig.service.DBManageConfigService;
import com.wadejerry.scms.modules.sysconfig.service.DatabaseService;
import com.wadejerry.scms.modules.sysconfig.service.SysConfigService;
import com.wadejerry.scms.task.QuartzSchedulerTask;
import com.wadejerry.scms.utils.AjaxUtil;
import com.wadejerry.scms.utils.Log.LogManager;
import com.wadejerry.scms.utils.db.DBTools;
import com.wadejerry.scms.utils.json.JacksonUtil;

@Controller
public class DBManageConfigControler{
	@Autowired
	private DatabaseService databaseService;
	@Autowired
	private LogRecord logRecord;
	
	@Autowired(required = false)
	private DBManageConfigService dbManageConfigService;
	@Autowired(required = false)
	private SysConfigService sysConfigService;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpServletRequest request;

	/*
	* 分页获取数据库备份文件
	* @author zhanying 2016年12月28日 下午3:54:27
	* @param   
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	
	@RequestMapping(value="/auth/database/getdatabaseinfo.do")
	@ResponseBody
	public void getDatabaseBackUpFileListAjax() {
		PageParamsDto paramsDto = new PageParamsDto(request); // 获取分 页参数
		//PageParamsDto paramsDto = PageParamsDto.GetParamsDtoByRequest(request); // 获取分页参数
		paramsDto.setIntValue1(ConstUser.CONST_ROLE_TYPE); // 角色类型
		int iTotal = 0;

		List<DatabaseBackUpFileDTO> dtoList = null;
		if(!SecurityUtils.getSubject().isPermitted("DatabaseSee")){
		}else{
			iTotal = dbManageConfigService.getDataBaseCount(paramsDto); // 获取记录总数
		if (iTotal > 0) {
			if (paramsDto.getLength() == -1) { // 查询所有记录
				paramsDto.setLength(iTotal);
			}
			dtoList = dbManageConfigService.getDatabaseBackUpFileListPage(paramsDto);; // 获取分页记录
		}
		}
		DataTableDto data = new DataTableDto(); // 返回数据封装
		data.setDataList(dtoList);
		data.setDraw(paramsDto.getDraw());
		data.setRecordsFiltered(iTotal);
		data.setRecordsTotal(iTotal);
		AjaxUtil.ajaxWriteDataTable(data, response);
		
	}
	
	/**
	* 备份数据库
	* @author zhanying 2016年12月28日 下午3:54:36
	* @param   
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	@RequestMapping(value="/auth/database/backup.do")
	@ResponseBody
	public void backUpDataBaseFileAjax() {
		AjaxUtil.ajaxWrite(dbManageConfigService.backUpDataBaseFile(),response);
	}
	
	/**
	*  还原数据库
	* @author zhanying 2016年12月28日 下午3:54:46
	* @param   
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	@RequestMapping(value="/auth/database/restore.do")
	@ResponseBody
	public void restoreDataBaseByFileNameAjax(@RequestParam("condition") String condition) {
		System.out.println(condition);
		DatabaseBackUpFileDTO dto=JacksonUtil.toObject(condition, DatabaseBackUpFileDTO.class);
		String[] fileNames= new String[]{dto.getFileName()};
		AjaxUtil.ajaxWrite(dbManageConfigService.restoreDataBaseByFileName(fileNames),response);
	}
    
	/**
	* 删除数据库备份文件
	* @author zhanying 2016年12月28日 下午3:54:54
	* @param   
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	@RequestMapping(value="/auth/database/delete.do")
	@ResponseBody
	public void deleteDatabaseBackUpFileAjax() {
		OperateResult result = new OperateResult(false, "删除数据库备份文件失败");
		List<DatabaseBackUpFileDTO> files=null;
		try {
			 files= JacksonUtil.json2list(request.getParameter("condition"), DatabaseBackUpFileDTO.class);
		} catch (Exception e) {
			
			LogManager.logException(e);
		}
		
		if (files == null || files.size() == 0) {
			result.setMsg("没有要删除的数据库备份文件");
		} else {
			try {
				StringBuilder sb = new StringBuilder();
				
				for (DatabaseBackUpFileDTO fileName : files) {
					DBTools.deleteDBFileByFileName(fileName.getFileName());
                    //sysConfigService.recordLog("删除备份文件：" + fileName);
					sb.append(fileName.getFileName()+";");
				}
				logRecord.recordLog(ConstParamLog.LOG_MODULE_BASIC_DATABASE, ConstParamLog.LOG_OPER_DELETE, ConstParamLog.LOG_TYPE_CONFIG, sb.toString());

				result.setResult(true);
				result.setMsg("删除数据库备份文件成功");
			} catch (Exception e) {
				LogManager.logException(e);
			}
		}
		 AjaxUtil.ajaxWrite(result,response);
	}
    
 
	
	
	
	@RequestMapping(value = "/auth/database/update.do")
	@ResponseBody
	public void updateDatabaseSetting(HttpServletRequest request, HttpServletResponse response){
		String condition = request.getParameter("condition");
		Map<String,Object> map=null;
		try {
			 map= JacksonUtil.json2map(condition);
			 databaseService.updateDatabaseSettings(map);
		} catch (Exception e) {
			OperateResult o =new OperateResult();
			o.setResult(false);
			AjaxUtil.ajaxWrite(o, response);
			e.printStackTrace();
			return;
		}
		OperateResult o =new OperateResult();
		o.setResult(true);
		try{
		
		//TODO 触发定时配置，Quartz配置好在处理
		QuartzSchedulerTask quartzSchedulerTask = (QuartzSchedulerTask)AppContext.getBean("quartzSchedulerTask");
		if (!quartzSchedulerTask.enableDatabaseBackUpTask()) {
			o.setResult(false);
			o.setMsg("数据库自动备份计划启动失败");
		}
		
		}catch(Exception e){
			e.printStackTrace();
			o.setResult(false);
			o.setMsg("数据库自动备份计划启动失败");
			
		}
		
		AjaxUtil.ajaxWrite(o, response);
	}
	@RequestMapping(value = "/auth/database/get.do")
	@ResponseBody
	public void getDatabaseSetting(HttpServletRequest request, HttpServletResponse response){
		
		AjaxUtil.ajaxWriteObject(databaseService.getDatabaseSetting(), response);

	}

}
