package com.wadejerry.scms.webservice.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis2.context.MessageContext;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wadejerry.scms.frame.AppContext;
import com.wadejerry.scms.frame.Authority.AuthorityGetter;
import com.wadejerry.scms.frame.constant.user.ConstUser;
import com.wadejerry.scms.frame.web.SystemParams;
import com.wadejerry.scms.modules.auth.dao.BimLoginStatusMapper;
import com.wadejerry.scms.modules.auth.dao.BimUserRoleMapper;
import com.wadejerry.scms.modules.auth.model.BimLoginStatus;
import com.wadejerry.scms.modules.auth.model.BimUser;
import com.wadejerry.scms.modules.auth.service.BimUserService;
import com.wadejerry.scms.modules.pfm.dao.WebServiceMapper;
import com.wadejerry.scms.utils.Log.LogManager;
import com.wadejerry.scms.utils.date.DateUtil;
import com.wadejerry.scms.webservice.server.support.CommonResult;
import com.wadejerry.scms.webservice.server.support.pfm.BoothResult;
import com.wadejerry.scms.webservice.server.support.pfm.ChargeRecordResult;
import com.wadejerry.scms.webservice.server.support.pfm.EntranceClientResult;
import com.wadejerry.scms.webservice.server.support.pfm.LoginResult;
import com.wadejerry.scms.webservice.server.support.pfm.LprPhotoResult;
import com.wadejerry.scms.webservice.server.support.pfm.PfmServerResult;
import com.wadejerry.scms.webservice.server.support.pfm.RecordResult;
import com.wadejerry.scms.webservice.server.support.pfm.dto.BoothDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.CarriagewayDeviceRelDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.CarriagewayDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.ChargeRecordDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.EntranceDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.LPRDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.PfmServerDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.RecordDto;
import com.wadejerry.scms.webservice.server.support.pfm.dto.RecordPageRequest;
import com.wadejerry.scms.webservice.server.utils.ClientSessionKEY;

/**
 * 
 * @ClassName: PfmClientService
 * @Description: 客户端Service
 * @author zhanying
 * @date 2017年3月31日 上午9:58:53
 *
 */
@Component("pfmClientService")
public class PfmClientService {
	
	@Autowired
	private  AuthorityGetter authorityGetter;

	/**
	 * 客户端登录
	 * @author zhanying 2017年3月31日 上午9:53:34
	 * @param @param userName
	 * @param @param password
	 * @param @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user:
	 * @modify by reason:
	 */
	public LoginResult userLogin(String userName, String password) {
		LoginResult result = new LoginResult();
		result.setiRet(false);
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		Subject currentUser = SecurityUtils.getSubject();
		try {
			if (currentUser.isAuthenticated()) {
				currentUser.logout();
			}
			currentUser.login(token);
			BimUserService bimUserService = (BimUserService) AppContext.getBean("bimUserService");
			BimUser user = bimUserService.findUserByLoginName(token.getUsername(), ConstUser.CONST_USER_TYPE);
			MessageContext mc = MessageContext.getCurrentMessageContext();
			HttpServletRequest request = (HttpServletRequest) mc.getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST);
			BimLoginStatus status = new BimLoginStatus();
			status.setLoginAddr(request.getRemoteAddr());
			status.setLoginPort(request.getRemotePort());
			status.setLoginTime(new Date());
			status.setUserName(token.getUsername());
			status.setSessionId(currentUser.getSession().getId().toString());
			status.setUserId(user.getBimUserId());
			status.setLoginWay("Client");
			BimLoginStatusMapper mapper = (BimLoginStatusMapper) AppContext.getBean("bimLoginStatusMapper");
			mapper.insertSelective(status);
			Session session = currentUser.getSession();
			session.setAttribute("userId", user.getBimUserId());
			session.setAttribute("userName", token.getUsername());
			session.setAttribute("companyId", user.getBimCompanyId());
			if (user.getsLevel() == ConstUser.ENTERPRISE_ADMINISTRATOR) {
				session.setAttribute("isCompanyManager", true);
			} else if (user.getsLevel() == ConstUser.GENERAL_MANAGER) {
				session.setAttribute("isCompanyManager", false);
			}
			result.setiRet(true);
			result.setUserId(user.getBimUserId());
			result.setSessionId(currentUser.getSession().getId().toString());

		} catch (Exception e) {
			result.setStrError(e.getMessage());
			LogManager.logException(e);
		}
		return result;
	}

	/**
	 * 客户端登出
	 * @author zhanying 2017年3月31日 上午9:53:50
	 * @param @param sessionId
	 * @param @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user:
	 * @modify by reason:
	 */
	public CommonResult userLogOut(String sessionId) {
		CommonResult commonResult = new CommonResult();
		commonResult.setiRet(true);
		try {
			MessageContext mc = MessageContext.getCurrentMessageContext();
			HttpServletRequest request = (HttpServletRequest)mc.getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST);
			HttpServletResponse response = (HttpServletResponse)mc.getProperty(HTTPConstants.MC_HTTP_SERVLETRESPONSE);
			Session session = SecurityUtils.getSecurityManager().getSession(new ClientSessionKEY(request,response,sessionId));
			if(session!=null){
				session.stop();
			}
		}  catch (SessionException e) {
			commonResult.setiRet(false);
			commonResult.setStrError("未登录或登录超时");
			LogManager.logException(e);
		}
		return commonResult;
	}

	/**
	 * 用户心跳
	 * @author zhanying 2017年2月24日 上午8:46:59
	 * @param @param sessionId
	 * @param @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user:
	 * @modify by reason:
	 */
	public CommonResult userOnlineHearbeat(String sessionId) {
		CommonResult result = new CommonResult();
		result.setiRet(true);
		try {
			MessageContext mc = MessageContext.getCurrentMessageContext();
			HttpServletRequest request = (HttpServletRequest)mc.getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST);
			HttpServletResponse response = (HttpServletResponse)mc.getProperty(HTTPConstants.MC_HTTP_SERVLETRESPONSE);
			Session session = SecurityUtils.getSecurityManager().getSession(new ClientSessionKEY(request,response,sessionId));
			if(session==null){
				throw new SessionException();
			}
		}  catch (SessionException e) {
			result.setiRet(false);
			result.setStrError("未登录或登录超时");
			LogManager.logException(e);
		}
		return result;
	}

	/**
	 * 获取岗亭信息(包含权限过滤)
	 * @author zhanying 2017年2月23日 上午11:34:06
	 * @param @param serverId
	 * @param @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user:
	 * @modify by reason:
	 */
	public BoothResult getAllBooth(String sessionId) {
		BoothResult boothResult = new BoothResult();
		Session session =null;
		try {
			MessageContext mc = MessageContext.getCurrentMessageContext();
			HttpServletRequest request = (HttpServletRequest)mc.getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST);
			HttpServletResponse response = (HttpServletResponse)mc.getProperty(HTTPConstants.MC_HTTP_SERVLETRESPONSE);
			session = SecurityUtils.getSecurityManager().getSession(new ClientSessionKEY(request,response,sessionId));
			if(session==null){
				throw new SessionException();
			}

		} catch (SessionException e) {
			boothResult.setiRet(false);
			boothResult.setStrError("未登录或登录超时");
			LogManager.logException(e);
			return boothResult;
		}
		WebServiceMapper pfmbm = (WebServiceMapper) AppContext.getBean("webServiceMapper");
		List<Integer> gangtingids = authorityGetter.gangtingAuth((boolean)(session.getAttribute("isCompanyManager")), (Integer)(session.getAttribute("userId")), (Integer)(session.getAttribute("companyId")));
		boothResult.setiRet(true);
		boothResult.setBoothDtoArr(pfmbm.selectAllBooth(gangtingids).toArray(new BoothDto[0]));
		return boothResult;
	}

	/**
	 * 根据岗亭id获取关联的停车场服务信息,根据岗亭ID获取关联的出入口，根据出入口获取提供车场，根据车场获取关联的停车场服务(包含权限过滤)
	 * @author zhanying 2017年3月14日 下午4:47:47
	 * @param @param   sessionId
	 * @param @param boothId
	 * @param @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user:
	 * @modify by reason:
	 */
	public PfmServerResult getPfmServerByBoothId(String sessionId, int boothId) {
		PfmServerResult result = new PfmServerResult();
		result.setiRet(true);
		Session session=null;
		try {
			MessageContext mc = MessageContext.getCurrentMessageContext();
			HttpServletRequest request = (HttpServletRequest)mc.getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST);
			HttpServletResponse response = (HttpServletResponse)mc.getProperty(HTTPConstants.MC_HTTP_SERVLETRESPONSE);
			 session = SecurityUtils.getSecurityManager().getSession(new ClientSessionKEY(request,response,sessionId));
			if(session==null){
				throw new SessionException();
			}
		} catch (SessionException e) {
			result.setiRet(false);
			result.setStrError("未登录或登录超时");
			LogManager.logException(e);
			return result;
		}
		WebServiceMapper pfmbm = (WebServiceMapper) AppContext.getBean("webServiceMapper");
		PfmServerDto pfmServerDto = pfmbm.selectPfmServerByBoothId((int)session.getAttribute("companyId"), boothId);
		result.setPfmServerDto(pfmServerDto);
		return result;
	}

	/**
	 * 获取出入口信息(包含权限过滤)
	 * @author zhanying 2017年3月31日 上午10:22:58
	 * @param @param  sessionId
	 * @param @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user:
	 * @modify by reason:
	 */
	public EntranceClientResult getEntranceList(String sessionId) {
		EntranceClientResult result = new EntranceClientResult();
		result.setiRet(true);
		Session session=null;
		try {
			MessageContext mc = MessageContext.getCurrentMessageContext();
			HttpServletRequest request = (HttpServletRequest)mc.getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST);
			HttpServletResponse response = (HttpServletResponse)mc.getProperty(HTTPConstants.MC_HTTP_SERVLETRESPONSE);
			session = SecurityUtils.getSecurityManager().getSession(new ClientSessionKEY(request,response,sessionId));
			if(session==null){
				throw new SessionException();
			}
		}  catch (SessionException e) {
			result.setiRet(false);
			result.setStrError("未登录或登录超时");
			LogManager.logException(e);
			return result;
		}
		WebServiceMapper pfmbm = (WebServiceMapper) AppContext.getBean("webServiceMapper");
		List<Integer> churukouauthid = authorityGetter.churuKouAuth((boolean)(session.getAttribute("isCompanyManager")), (Integer)(session.getAttribute("userId")), (Integer)(session.getAttribute("companyId")));

		EntranceDto[] entranceDto = pfmbm.selectAllEntranceDto2((int)session.getAttribute("companyId"),churukouauthid).toArray(new EntranceDto[0]);
		result.setEntranceDtoArr(entranceDto);
		List<Integer> entranceid = new ArrayList<>();
		for(EntranceDto dto:entranceDto){
			entranceid.add(dto.getPfmEntranceId());
		}
		if(entranceid.size()!=0){
			result.setCarriagewayDtoArr(pfmbm.selectCarriageWaydtoByentranceid(entranceid).toArray(new CarriagewayDto[0]));
		}
		else {
			result.setCarriagewayDtoArr(new CarriagewayDto[0]);
		}
		
		List<Integer> carriagewayid = new ArrayList<>();
		for(CarriagewayDto dto:result.getCarriagewayDtoArr()){
			carriagewayid.add(dto.getCarriagewayId());
		}
		if(carriagewayid.size()!=0){
			result.setCarriagewayDeviceRelDtoArr(pfmbm.selectCarriageWaydevicereldtoBycarriagewayid(carriagewayid).toArray(new CarriagewayDeviceRelDto[0]));
		}
		else {
			result.setCarriagewayDeviceRelDtoArr(new CarriagewayDeviceRelDto[0]);
		}
		List<Integer> deviceid = new ArrayList<>();
		for(CarriagewayDeviceRelDto dto:result.getCarriagewayDeviceRelDtoArr()){
			deviceid.add(dto.getDeviceId());
		}
		if(deviceid.size()!=0){
			result.setLPRDtoArr(pfmbm.selectLPRdtoByDEVICEID(deviceid).toArray(new LPRDto[0]));
		}
		else {
			result.setLPRDtoArr(new LPRDto[0]);
		}
		return result;
	}

	/**
	 * 查询车辆进出记录(包含权限过滤)
	 * @author zhanying 2017年3月31日 上午11:59:00
	 * @param @paramsessionId
	 * @param @param recordPageRequest
	 * @param @return
	 * @modificationHistory=========================逻辑或功能性重大变更记录
	 * @modify by user:
	 * @modify by reason:
	 */
	public RecordResult RecordQuery(String sessionId, RecordPageRequest recordPageRequest) {
		RecordResult result= new RecordResult();
		result.setiRet(true);
		Session session =null;
		try {
			MessageContext mc = MessageContext.getCurrentMessageContext();
			HttpServletRequest request = (HttpServletRequest)mc.getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST);
			HttpServletResponse response = (HttpServletResponse)mc.getProperty(HTTPConstants.MC_HTTP_SERVLETRESPONSE);
			session = SecurityUtils.getSecurityManager().getSession(new ClientSessionKEY(request,response,sessionId));
			if(session==null){
				throw new SessionException();
			}
		}  catch (SessionException e) {
			result.setiRet(false);
			result.setStrError("未登录或登录超时");
			LogManager.logException(e);
			return result;
		}
		//参数为null方便dao层无需过滤此字段
		Date start=null;//start为0 无需过滤
		Date end=null;//end为0 无需过滤
		Integer dir=null;//dir为0无需过滤
		String carNum = null;//carNum为空字符串时无需过滤
		List<Integer> entranceId=null;//null不过滤
		if(recordPageRequest.getBeginTime()!=0){
			start =new Date( recordPageRequest.getBeginTime());
		}
		if(recordPageRequest.getEndTime()!=0){
			end = new Date(recordPageRequest.getEndTime());
		}
		if(recordPageRequest.getDirection()!=0){
		 dir = recordPageRequest.getDirection();
		}
		int flag = recordPageRequest.getFlag();
		if(!"".equals(recordPageRequest.getCarNum().trim())){
			carNum= recordPageRequest.getCarNum().trim();
		}
		int[] entranceIdarr = recordPageRequest.getEntranceIdArr();//查询条件 
		
		if(entranceIdarr!=null&&entranceIdarr.length!=0){
			entranceId= new ArrayList<>();
			for(int i=0;i<entranceIdarr.length;i++){
				
				entranceId.add(entranceIdarr[i]);
			}
		
		}
		
		List<Integer> churukouauthid = authorityGetter.churuKouAuth((boolean)(session.getAttribute("isCompanyManager")), (Integer)(session.getAttribute("userId")), (Integer)(session.getAttribute("companyId")));

		int reqPage = recordPageRequest.getReqPage();
		int pageItems = recordPageRequest.getPageItems();
		
		WebServiceMapper pfmbm = (WebServiceMapper) AppContext.getBean("webServiceMapper");
		int total=pfmbm.selectRecordListCount(start, end, dir, entranceId,churukouauthid, flag, carNum, reqPage, pageItems);
		
		result.setAllRow(total);
		result.setCurrentPage(reqPage);
		result.setPageSize(pageItems);
		result.setTotalPage(total%pageItems==0?total/pageItems:total/pageItems+1);
		result.setRecordDtoArr(pfmbm.selectRecordList(start, end, dir, entranceId,churukouauthid, flag, carNum, reqPage, pageItems).toArray(new RecordDto[0]));
		return result;
	}

	/**
	* 查询车俩收费记录(包含权限过滤)
	* @author zhanying 2017年3月31日 下午12:02:59
	* @param  @param sessionId
	* @param  @param recordPageRequest
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public ChargeRecordResult ChargeRecordQuery(String sessionId, RecordPageRequest recordPageRequest) {
		ChargeRecordResult result= new ChargeRecordResult();
		result.setiRet(true);
		Session session = null;
		try {
			MessageContext mc = MessageContext.getCurrentMessageContext();
			HttpServletRequest request = (HttpServletRequest)mc.getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST);
			HttpServletResponse response = (HttpServletResponse)mc.getProperty(HTTPConstants.MC_HTTP_SERVLETRESPONSE);
			 session = SecurityUtils.getSecurityManager().getSession(new ClientSessionKEY(request,response,sessionId));
			if(session==null){
				throw new SessionException();
			}
		} catch (SessionException e) {
			result.setiRet(false);
			result.setStrError("未登录或登录超时");
			LogManager.logException(e);
			return result;
		}
		//参数为null方便dao层无所过滤此字段
		Date start=null;//start为0 无需过滤
		Date end=null;//end为0 无需过滤
		Integer dir=null;//dir为0无需过滤
		String carNum = null;//carNum为空字符串时无需过滤
		List<Integer> entranceId=null;//null不过滤
		if(recordPageRequest.getBeginTime()!=0){
			start =new Date( recordPageRequest.getBeginTime());
		}
		if(recordPageRequest.getEndTime()!=0){
			end = new Date(recordPageRequest.getEndTime());
		}
		if(recordPageRequest.getDirection()!=0){
		 dir = recordPageRequest.getDirection();
		}
		int flag = recordPageRequest.getFlag();
		if(!recordPageRequest.getCarNum().trim().equals("")){
			carNum= recordPageRequest.getCarNum().trim();
		}
		int[] entranceIdarr = recordPageRequest.getEntranceIdArr();
		List<Integer> churukouauthid = authorityGetter.churuKouAuth((boolean)(session.getAttribute("isCompanyManager")), (Integer)(session.getAttribute("userId")), (Integer)(session.getAttribute("companyId")));

		if(entranceIdarr!=null&&entranceIdarr.length!=0){
			entranceId= new ArrayList<>();
			for(int i=0;i<entranceIdarr.length;i++){
				entranceId.add(entranceIdarr[i]);
			}
			if(churukouauthid!=null)
				entranceId.retainAll(churukouauthid);
		}
		else {
			if(churukouauthid!=null)
				entranceId= new ArrayList<>(churukouauthid);
		}
	
		
		int reqPage = recordPageRequest.getReqPage();
		int pageItems = recordPageRequest.getPageItems();
		
		WebServiceMapper pfmbm = (WebServiceMapper) AppContext.getBean("webServiceMapper");
		int total=pfmbm.selectChargeRecordListCount(start, end, dir, entranceId, flag, carNum, reqPage, pageItems);
		
		result.setAllRow(total);
		result.setCurrentPage(reqPage);
		result.setPageSize(pageItems);
		result.setTotalPage(total%pageItems==0?total/pageItems:total/pageItems+1);
		result.setChargerRecordDtoArr(pfmbm.selectChargeRecordList(start, end, dir, entranceId, flag, carNum, reqPage, pageItems).toArray(new ChargeRecordDto[0]));
		BigDecimal totalMoney=new BigDecimal("0.00");
		for(ChargeRecordDto dto:pfmbm.selectChargeRecordList(start, end, dir, entranceId, flag, carNum, 1, total).toArray(new ChargeRecordDto[0])){
			BigDecimal temp = dto.getChargefee();
			totalMoney=totalMoney.add(temp);
		}
		result.setTotalMoney(totalMoney);
		return result;
	}
	
	
	/**
	* 获取车俩抓拍图片
	* @author zhanying 2017年3月31日 下午12:11:06
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	public LprPhotoResult getLprPhoto(String sessionId,String photoName){
		//图片路径  SystemParams.lprPath + 图片名称前8位(年月日)+图片名称
		LprPhotoResult result= new LprPhotoResult();
		result.setiRet(true);
		try {
			MessageContext mc = MessageContext.getCurrentMessageContext();
			HttpServletRequest request = (HttpServletRequest)mc.getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST);
			HttpServletResponse response = (HttpServletResponse)mc.getProperty(HTTPConstants.MC_HTTP_SERVLETRESPONSE);
			Session session = SecurityUtils.getSecurityManager().getSession(new ClientSessionKEY(request,response,sessionId));
			if(session==null){
				throw new SessionException();
			}
		} catch (SessionException e) {
			result.setiRet(false);
			result.setStrError("未登录或登录超时");
			LogManager.logException(e);
			return result;
		}
		String picPath=SystemParams.lprPath;
		String todaystring = photoName.substring(0, 8).replace("-", "");
		String path = picPath+"\\"+todaystring+"\\"+photoName;
		File photo=new File(path);
		if(photo.exists()){
			InputStream input = null;
			try{
				input = new FileInputStream(photo);
				result.setPhoto(IOUtils.toByteArray(input));
				result.setiRet(true);
			}
			catch(FileNotFoundException e){
				result.setiRet(false);
				result.setStrError("未找到图片文件");
				LogManager.logException(e);
			} 
			catch (IOException e) {
				result.setiRet(false);
				result.setStrError("服务器读取文件错误");
				LogManager.logException(e);
			}
			
		}
		else{
			result.setiRet(false);
			result.setStrError("未找到图片文件");
		}		
		return result;
	}
}
