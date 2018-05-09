package com.wadejerry.scms.modules.device.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Comparator;
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
import com.wadejerry.scms.frame.constant.user.ConstUser;
import com.wadejerry.scms.frame.entity.DataTableDto;
import com.wadejerry.scms.frame.entity.LoginInfo;
import com.wadejerry.scms.frame.entity.OperateResult;
import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.frame.log.LogRecord;
import com.wadejerry.scms.modules.acm.dao.AcmServerMapper;
import com.wadejerry.scms.modules.acm.model.AcmServer;
import com.wadejerry.scms.modules.acm.service.AcmServerService;
import com.wadejerry.scms.modules.device.dao.PfmServerMapper;
import com.wadejerry.scms.modules.device.model.PfmServer;
import com.wadejerry.scms.modules.device.model.Server;
import com.wadejerry.scms.modules.device.service.ServerService;
import com.wadejerry.scms.modules.sysconfig.dao.BimNetZoneMapper;
import com.wadejerry.scms.modules.sysconfig.model.BimNetZone;
import com.wadejerry.scms.utils.AjaxUtil;
import com.wadejerry.scms.utils.json.JacksonUtil;
import com.wadejerry.scms.webservice.client.PfmWebServiceStub;
import com.wadejerry.scms.webservice.client.PfmWebServiceStub.RemoteConfiguration;
import com.wadejerry.scms.webservice.client.PfmWebServiceStub.RemoteConfigurationResponse;
import com.wadejerry.scms.webservice.client.PfmWebServiceStub.ServiceParams;


@Controller
public class ServerController {

	@Autowired
	private ServerService serverService;
	@Autowired
	private PfmServerMapper pfmServerMapper;
	@Autowired
	private AcmServerService acmServerservice;
	@Autowired
	private AcmServerMapper acmMapper;
	@Autowired
	private BimNetZoneMapper netZoneMapper;
	@Autowired
	private LogRecord logRecord;
	@RequestMapping(value = "auth/server/getServerInfo.do")
	public void getServerInfoList(HttpServletRequest request, HttpServletResponse response) {

		PageParamsDto paramsDto = new PageParamsDto(request); // 获取分页参数
		paramsDto.setIntValue1(ConstUser.CONST_ROLE_TYPE); // 角色类型
		int iTotal =0;

		List<?> dtoList = null;
		if(!SecurityUtils.getSubject().isPermitted("ServerSee")){
		}else{
			iTotal = serverService.getServerCount(paramsDto); // 获取记录总数
			if (iTotal > 0) {
				if (paramsDto.getLength() == -1) { // 查询所有记录
				paramsDto.setLength(iTotal);
				}	

				dtoList = serverService.getServerListByPage(paramsDto); // 获取分页记录
				//分页 排序
				String order = paramsDto.getOrder();
				if(order.contains("server_Name")&&order.contains("asc"))//按服务器名升序
				{
					dtoList.sort(new Comparator<Object>() {

						@Override
						public int compare(Object o1, Object o2) {
							Server s1 = (Server)(o1);
							Server s2 = (Server)(o2);
							if(s1.getServerName().compareTo(s2.getServerName())>0) {
								return 1;
							}
							else if(s1.getServerName().compareTo(s2.getServerName())==0) {
								return 0;
							}
							else return -1;
						}
					});
				}else if(order.contains("server_Name")&&order.contains("desc")){
					
					dtoList.sort(new Comparator<Object>() {

						@Override
						public int compare(Object o1, Object o2) {
							Server s1 = (Server)(o1);
							Server s2 = (Server)(o2);
							if(s1.getServerName().compareTo(s2.getServerName())>0) {
								return -1;
							}
							else if(s1.getServerName().compareTo(s2.getServerName())==0) {
								return 0;
							}
							else return 1;
						}
					});
				}else if(order.contains("ip")&&order.contains("asc")) {
					dtoList.sort(new Comparator<Object>() {

						@Override
						public int compare(Object o1, Object o2) {
							Server s1 = (Server)(o1);
							Server s2 = (Server)(o2);
							if(s1.getIp().compareTo(s2.getIp())>0) {
								return 1;
							}
							else if(s1.getIp().compareTo(s2.getIp())==0) {
								return 0;
							}
							else return -1;
						}
					});
				}else if(order.contains("ip")&&order.contains("desc")) {
					dtoList.sort(new Comparator<Object>() {

						@Override
						public int compare(Object o1, Object o2) {
							Server s1 = (Server)(o1);
							Server s2 = (Server)(o2);
							if(s1.getIp().compareTo(s2.getIp())>0) {
								return -1;
							}
							else if(s1.getIp().compareTo(s2.getIp())==0) {
								return 0;
							}
							else return 1;
						}
					});
				}else if(order.contains("port")&&order.contains("asc")) {
					dtoList.sort(new Comparator<Object>() {

						@Override
						public int compare(Object o1, Object o2) {
							Server s1 = (Server)(o1);
							Server s2 = (Server)(o2);
							if(s1.getPort().compareTo(s2.getPort())>0) {
								return 1;
							}
							else if(s1.getPort().compareTo(s2.getPort())==0) {
								return 0;
							}
							else return -1;
						}
					});
				}else if(order.contains("port")&&order.contains("desc")) {
					dtoList.sort(new Comparator<Object>() {

						@Override
						public int compare(Object o1, Object o2) {
							Server s1 = (Server)(o1);
							Server s2 = (Server)(o2);
							if(s1.getPort().compareTo(s2.getPort())>0) {
								return -1;
							}
							else if(s1.getPort().compareTo(s2.getPort())==0) {
								return 0;
							}
							else return 1;
						}
					});
				}
				else if(order.contains("update_Time")&&order.contains("asc")) {
					dtoList.sort(new Comparator<Object>() {

						@Override
						public int compare(Object o1, Object o2) {
							Server s1 = (Server)(o1);
							Server s2 = (Server)(o2);
							if(s1.getUpdateTime().compareTo(s2.getUpdateTime())>0) {
								return 1;
							}
							else if(s1.getUpdateTime().compareTo(s2.getUpdateTime())==0) {
								return 0;
							}
							else return -1;
						}
					});
				}else if(order.contains("update_Time")&&order.contains("desc")) {
					dtoList.sort(new Comparator<Object>() {

						@Override
						public int compare(Object o1, Object o2) {
							Server s1 = (Server)(o1);
							Server s2 = (Server)(o2);
							if(s1.getUpdateTime().compareTo(s2.getUpdateTime())>0) {
								return -1;
							}
							else if(s1.getUpdateTime().compareTo(s2.getUpdateTime())==0) {
								return 0;
							}
							else return 1;
						}
					});
				}
				else if(order.contains("user_Name")&&order.contains("asc")) {
					dtoList.sort(new Comparator<Object>() {

						@Override
						public int compare(Object o1, Object o2) {
							Server s1 = (Server)(o1);
							Server s2 = (Server)(o2);
							if(s1.getUserName().compareTo(s2.getUserName())>0) {
								return 1;
							}
							else if(s1.getUserName().compareTo(s2.getUserName())==0) {
								return 0;
							}
							else return -1;
						}
					});
				}else if(order.contains("user_Name")&&order.contains("desc")) {
					dtoList.sort(new Comparator<Object>() {

						@Override
						public int compare(Object o1, Object o2) {
							Server s1 = (Server)(o1);
							Server s2 = (Server)(o2);
							if(s1.getUserName().compareTo(s2.getUserName())>0) {
								return -1;
							}
							else if(s1.getUserName().compareTo(s2.getUserName())==0) {
								return 0;
							}
							else return 1;
						}
					});
				}
				//排序完毕
					
					
					
				dtoList = dtoList.subList(paramsDto.getStart(), paramsDto.getStart()+paramsDto.getLength()>=dtoList.size()?dtoList.size():paramsDto.getStart()+paramsDto.getLength());
			
			}
		}
		//TODO 排序，etc
		DataTableDto data = new DataTableDto(); // 返回数据封装
		data.setDataList(dtoList);
		data.setDraw(paramsDto.getDraw());
		data.setRecordsFiltered(iTotal);
		data.setRecordsTotal(iTotal);
		AjaxUtil.ajaxWriteDataTable(data, response);
	}

	@RequestMapping(value = "/auth/server/saveServer.do")
	public void saveServerInfo(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> condition=null;
		OperateResult result=new OperateResult();
		try {
			condition = JacksonUtil.json2map(request.getParameter("condition"));
		} catch (Exception e) {

			result.setResult(false);
			result.setMsg("json转换异常");
			AjaxUtil.ajaxWrite(result, response);
			return;
		}
		if(("").equals(condition.get("serverId"))){//新增服务器
			if("server-1-1".equals(condition.get("serverType"))) {//新增停车场服务器
			PfmServer server = new PfmServer();
			server.setBimCompanyId(LoginInfo.getCompanyId());
			server.setInsertTime(new Date());
			server.setUpdateTime(new Date());
			server.setIp(condition.get("ip").toString());
			server.setPort(condition.get("port").toString());
			server.setServerName(condition.get("serverName").toString());
			server.setUserName(LoginInfo.getLoginName());
			server.setParkId(Integer.parseInt(condition.get("parkId").toString()));
			if(condition.get("netZone").toString().equals("null"))
			{}
			else{
				server.setNetZoneId(Integer.parseInt(condition.get("netZone").toString()));
			}


			try{

				result=serverService.insertServer(server);
				logRecord.recordLog(ConstParamLog.LOG_MODULE_BASIC_PFM_SERVER, ConstParamLog.LOG_OPER_ADD, ConstParamLog.LOG_TYPE_CONFIG, server.getServerName());

				AjaxUtil.ajaxWrite(result, response);

			}
			catch(Exception e){
				result.setResult(false);
				result.setMsg("插入数据失败");
				AjaxUtil.ajaxWrite(result, response);
			}
			}else if("server-1-2".equals(condition.get("serverType"))) {//新增门禁服务器
				AcmServer server = new AcmServer();
				server.setBimCompanyId(LoginInfo.getCompanyId());
				server.setUpdateTime(new Date());
				server.setIp(condition.get("ip").toString());
				server.setPort(condition.get("port").toString());
				server.setServerName(condition.get("serverName").toString());
				server.setUserName(LoginInfo.getLoginName());
				if(condition.get("netZone").toString().equals("null"))
				{}
				else{
					server.setNetZoneId(Integer.parseInt(condition.get("netZone").toString()));
				}


				try{

					result=acmServerservice.insertServer(server);
					//TODO acm新增日志
					//logRecord.recordLog(ConstParamLog.LOG_MODULE_BASIC_PFM_SERVER, ConstParamLog.LOG_OPER_ADD, ConstParamLog.LOG_TYPE_CONFIG, server.getServerName());

					AjaxUtil.ajaxWrite(result, response);

				}
				catch(Exception e){
					result.setResult(false);
					result.setMsg("插入数据失败");
					AjaxUtil.ajaxWrite(result, response);
				}
			}
			
		}else{//修改服务器
			if("server-1-1".equals(condition.get("serverType"))) {//修改停车场服务器
			PfmServer server = pfmServerMapper.selectByPrimaryKey(Integer.parseInt(condition.get("serverId").toString()));
			server.setUpdateTime(new Date());
			server.setIp(condition.get("ip").toString());
			server.setPort(condition.get("port").toString());
			server.setServerName(condition.get("serverName").toString());
			server.setParkId(Integer.parseInt(condition.get("parkId").toString()));

			if(condition.get("netZone").toString().equals("null"))
			{
				server.setNetZoneId(null);
			}
			else{
				server.setNetZoneId(Integer.parseInt(condition.get("netZone").toString()));
			}
			try{

				result = serverService.updateServer(server);
				logRecord.recordLog(ConstParamLog.LOG_MODULE_BASIC_PFM_SERVER, ConstParamLog.LOG_OPER_UPDATE, ConstParamLog.LOG_TYPE_CONFIG, server.getServerName());

				AjaxUtil.ajaxWrite(result, response);
			}

			catch(Exception e){
				result.setResult(false);
				result.setMsg("修改失败");
				AjaxUtil.ajaxWrite(result, response);
			}

			}else if("server-1-2".equals(condition.get("serverType"))) {//修改门禁服务器
				AcmServer server = acmMapper.selectByPrimaryKey(Integer.parseInt(condition.get("serverId").toString()));
				server.setUpdateTime(new Date());
				server.setIp(condition.get("ip").toString());
				server.setPort(condition.get("port").toString());
				server.setServerName(condition.get("serverName").toString());
				

				if(condition.get("netZone").toString().equals("null"))
				{
					server.setNetZoneId(null);
				}
				else{
					server.setNetZoneId(Integer.parseInt(condition.get("netZone").toString()));
				}
				try{

					result = acmServerservice.updateServer(server);
					//TODO acm修改日志
					
					//logRecord.recordLog(ConstParamLog.LOG_MODULE_BASIC_PFM_SERVER, ConstParamLog.LOG_OPER_UPDATE, ConstParamLog.LOG_TYPE_CONFIG, server.getServerName());

					AjaxUtil.ajaxWrite(result, response);
				}

				catch(Exception e){
					result.setResult(false);
					result.setMsg("修改失败");
					AjaxUtil.ajaxWrite(result, response);
				}

			}
		}

	}
	@RequestMapping(value = "/auth/server/delServer.do")
	public void delServerInfo(HttpServletRequest request, HttpServletResponse response) {
		List<Server> list;
		OperateResult result=new OperateResult();
		try {
			list = JacksonUtil.json2list(request.getParameter("condition"),Server.class);
		} catch (Exception e) {

			result.setResult(false);
			result.setMsg("参数违法");
			AjaxUtil.ajaxWrite(result, response);
			return;
		}
		
		List<PfmServer> todelpfm = new ArrayList<>();
		List<AcmServer> todelacm = new ArrayList<>();
		PfmServer pfm = null;
		AcmServer acm = null;
		for(Server tp : list) {
			if("acm".equals(tp.getBelongto())) {
				acm = new AcmServer();
				acm.setAcmServerId(tp.getServerId());
				todelacm.add(acm);
			}else if("pfm".equals(tp.getBelongto())) {
				pfm = new PfmServer();
				pfm.setPfmServerId(tp.getServerId());
				todelpfm.add(pfm);
			}
		}
		
		
		//检查是否有设备关联服务器，有则提示


		try{
			if(serverService.delServer(todelpfm,todelacm)==1) {

				result.setResult(true);
				AjaxUtil.ajaxWrite(result, response);
			}
			else {
				result.setResult(false);
				result.setMsg("存在服务器与停车场设备关联，请先删除停车场设备");
				AjaxUtil.ajaxWrite(result, response);
			}
		}
		catch(Exception e){
			result.setResult(false);
			result.setMsg("删除数据失败");
			AjaxUtil.ajaxWrite(result, response);
		}


	}


	@RequestMapping(value = "/auth/server/checkport.do")
	public void checkPort(HttpServletRequest request, HttpServletResponse response) {

		String ip = request.getParameter("ip");
		String port = request.getParameter("port");
		String flag = request.getParameter("flag");
		String id = request.getParameter("id");
		String belongto = request.getParameter("belongto");
		if("pfm".equals(belongto)) {
			AjaxUtil.ajaxWrite(serverService.checkPort(id,ip, port,flag), response);
		}
		else if("acm".equals(belongto)){
			
			AjaxUtil.ajaxWrite(acmServerservice.checkPort(id,ip, port,flag), response);
		}
	}

	@RequestMapping(value = "/auth/server/remoteconfig.do")
	public void remoteConfig(HttpServletRequest request, HttpServletResponse response) {
		OperateResult o = new OperateResult(true,"");
		String condition = request.getParameter("condition");
		
		Server server = JacksonUtil.toObject(condition, Server.class);
		BimNetZone  netZone = netZoneMapper.selectByPrimaryKey(server.getNetZoneId());
		if(netZone==null){
			o.setResult(false);
			o.setMsg("远程配置失败，请配置服务器的网域");
		}else{
			if("pfm".equals(server.getBelongto())) {//停车场服务远程配置
			RemoteConfiguration recofig = new RemoteConfiguration();
			ServiceParams params = new ServiceParams();
			params.setSCMSUrl(netZone.getUrl());
			params.setSCMSPort(netZone.getPort());
			params.setServerName(server.getServerName());
			params.setCompanyId(server.getBimCompanyId());
			params.setUserId(LoginInfo.getLoginId());
			params.setUserName(LoginInfo.getLoginName());
			params.setParkId(server.getParkId());

			params.setServiceId(server.getServerId());
			recofig.setServiceParams(params);

			try {
				RemoteConfigurationResponse resp = new PfmWebServiceStub("http://"+server.getIp()+":"+server.getPort()+"/PfmWebService/service").remoteConfiguration(recofig);
				if(resp.getRemoteConfigurationResult().getIRet()==0){
					o.setMsg("配置成功");
					logRecord.recordLog(ConstParamLog.LOG_MODULE_BASIC_PFM_SERVER, ConstParamLog.LOG_OPER_OTHER, ConstParamLog.LOG_TYPE_CTRL, server.getServerName());

				}
				else{
					o.setResult(false);
					o.setMsg("服务端异常："+resp.getRemoteConfigurationResult().getStrMessage());
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				o.setResult(false);
				o.setMsg("远程配置失败，请检查配置是否正确，服务是否开启");
				e.printStackTrace();
			}
		}else if("acm".equals(server.getBelongto())){//门禁服务远程配置
			
			o.setResult(false);
			o.setMsg("acm暂不支持");
		}
		}

		AjaxUtil.ajaxWrite(o, response);
	}
	@RequestMapping(value = "/auth/server/getparklotlist.do")
	@ResponseBody
	public void getParkLotList(HttpServletRequest request, HttpServletResponse response) {
		
		AjaxUtil.ajaxWriteObject(serverService.selectAllParkingLot(), response);
	}
}
