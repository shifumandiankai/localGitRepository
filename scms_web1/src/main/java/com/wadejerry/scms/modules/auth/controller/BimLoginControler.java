package com.wadejerry.scms.modules.auth.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wadejerry.scms.frame.AppContext;
import com.wadejerry.scms.frame.constant.user.ConstUser;
import com.wadejerry.scms.frame.entity.LoginInfo;
import com.wadejerry.scms.frame.entity.OperateResult;
import com.wadejerry.scms.modules.auth.dao.BimLoginStatusMapper;
import com.wadejerry.scms.modules.auth.dao.BimUserMapper;
import com.wadejerry.scms.modules.auth.dto.LoginDto;
import com.wadejerry.scms.modules.auth.model.BimLoginStatus;
import com.wadejerry.scms.modules.auth.model.BimUser;
import com.wadejerry.scms.modules.auth.service.BimUserService;
import com.wadejerry.scms.modules.license.service.LiscenseService;
import com.wadejerry.scms.utils.AjaxUtil;
import com.wadejerry.scms.utils.Log.LogManager;
import com.wadejerry.scms.utils.json.JacksonUtil;

@Controller
public class BimLoginControler {
	@Autowired
	private BimUserService bimUserService;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private BimUserMapper userMapper;
	@Autowired
	private LiscenseService liscenseService;
	@RequestMapping("/{id}/showUser")
	public String showUser(@PathVariable int id, HttpServletRequest request) {
		BimUser user = bimUserService.getUserById(id);
		System.out.println(user.getUserName());
		request.setAttribute("user", user);
		return "showUser";
	}

	@RequestMapping("/")
	public String toindex(Model model) {
		return "redirect:/index";
	}
	@RequestMapping("/heartbeat")
	@ResponseBody
	public void heartBeat(Model model) {
		AjaxUtil.ajaxWrite("{\"status\":\"Online OK!\"}", response);
	}
	@RequestMapping("/*")
	public String to404(Model model) {
		return "redirect:/common/views/404.jsp";
	}
	@RequestMapping("/login")
	public String tologin(Model model) {
		LogManager.logDebug("登录IP[" + request.getRemoteHost() + "]");
		/*if(liscenseService.currentLiscenseInfocan()) {
			return "login";
		}
		else {
			return "redirect:/toLiscenseImport";
		}*/
			
			return "login";
	}

	/**
	* 首页
	* @author zhanying 2017年7月27日 上午10:46:20
	* @param  @param model
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	@RequestMapping("/index")
	public String toIndex(Model model) {
		return "yindex";
	}

	/**
	* 基础资料主页面
	* @author zhanying 2017年7月25日 下午4:28:06
	* @param  @param model
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	@RequestMapping("/basic.do")
	public String toBasicndex(Model model) {
		return "pages/basic_index";
	}

	/**
	* 安全管理主页
	* @author zhanying 2017年7月27日 上午10:45:22
	* @param  @param model
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	@RequestMapping("/safety.do")
	public String toPfmIndex(Model model) {
		return "pages/safety_index";
	}
	
	/**
	* 信息显示主页
	* @author zhanying 2017年8月1日 下午4:02:54
	* @param  @param model
	* @param  @return 
	* @modificationHistory=========================逻辑或功能性重大变更记录
	* @modify by user: 
	* @modify by reason:
	 */
	@RequestMapping("/info.do")
	public String toInfoDispay(Model model) {
		return "pages/info_display_index";
	}
	
	@RequestMapping("/onecard.do")
	public String toOneCard(Model model) {
		return "pages/onecard_index";
	}
	
	
	@RequestMapping("/checkLogin.do")
	@ResponseBody
	public void login() {
		OperateResult operateResult = new OperateResult();
		try {
			String srtUser = request.getParameter("condition");
			LoginDto user = JacksonUtil.toObject(srtUser, LoginDto.class);
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
			Subject currentUser = SecurityUtils.getSubject();
			if (currentUser.isAuthenticated()) {
				currentUser.logout();
			}
			token.setRememberMe(user.getRememberMe());
			currentUser.login(token);
			// currentUser.isPermitted("");
			//operateResult = new OperateResult();
			operateResult.setResult(true);
			BimUserService bimUserService = (BimUserService) AppContext.getBean("bimUserService");
			BimUser user2 = bimUserService.findUserByLoginName(token.getUsername(), ConstUser.CONST_USER_TYPE);
			BimLoginStatus status = new BimLoginStatus();
			status.setLoginAddr(request.getRemoteAddr());
			status.setLoginPort(request.getRemotePort());
			status.setLoginTime(new Date());
			status.setUserName(token.getUsername());
			status.setSessionId(currentUser.getSession().getId().toString());
			status.setUserId(user2.getBimUserId());
			status.setLoginWay("Explorer");
			BimLoginStatusMapper mapper = (BimLoginStatusMapper) AppContext.getBean("bimLoginStatusMapper");
			mapper.insertSelective(status);
			Session session = currentUser.getSession();
			session.setAttribute("userId", user2.getBimUserId());
			session.setAttribute("userName", token.getUsername());
			session.setAttribute("companyId", user2.getBimCompanyId());
			session.setAttribute("loginAddress", request.getRemoteHost());
			if (user2.getsLevel() == ConstUser.ENTERPRISE_ADMINISTRATOR) {
				session.setAttribute("isCompanyManager", true);
			} else if (user2.getsLevel() == ConstUser.GENERAL_MANAGER) {
				session.setAttribute("isCompanyManager", false);
			}
		} catch (UnknownAccountException e) {
			LogManager.logException(e);
			operateResult.setResult(false);
			operateResult.setMsg("用户名不存在");
		} catch (LockedAccountException e) {
			LogManager.logException(e);
			operateResult.setResult(false);
			operateResult.setMsg("用户未启用");
		} catch (DisabledAccountException  e) {
			LogManager.logException(e);
			operateResult.setResult(false);
			operateResult.setMsg("用户使用时间已到期");
		} catch (Exception e) {
			LogManager.logException(e);
			operateResult.setResult(false);
			operateResult.setMsg("用户名或密码错误");
		}
		AjaxUtil.ajaxWrite(operateResult, response);
	}

	@RequestMapping(value = "/logout")
	@ResponseBody
	public void logout() {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
	}

	@RequestMapping(value = "/chklogin", method = RequestMethod.POST)
	@ResponseBody
	public String chkLogin() {
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			return "false";
		}
		return "true";
	}

	@RequestMapping("user/uploadtouxiang")
	@ResponseBody
	public void uoloadTouxiang(HttpServletRequest request, HttpServletResponse response) {
		String formData=request.getParameter("formData");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) (ServletRequest) request;
		// 得到上传的文件
		MultipartFile mFile = multipartRequest.getFile("up_img");
		// 得到上传服务器的路径

		String path = request.getSession().getServletContext().getRealPath("photo");
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}
		// 得到上传的文件的文件名
		String oldFileName = mFile.getOriginalFilename();
		String suffix = oldFileName.substring(oldFileName.lastIndexOf("."));
		String filename = LoginInfo.getLoginName() + new Date().getTime() + suffix;
		path += ("\\" + filename);
		InputStream inputStream = null;
		FileOutputStream outputStream = null;
		try {
			inputStream = mFile.getInputStream();
			outputStream = new FileOutputStream(path);
			byte[] b = new byte[1048576];
			int length = 0;
			while ((length = inputStream.read(b)) != -1) {
				outputStream.write(b, 0, length);
			}
			BimUser user = userMapper.selectByPrimaryKey(LoginInfo.getLoginId());
			user.setPhoto("/photo/" + filename);
			userMapper.updateByPrimaryKeySelective(user);
			OperateResult result = new OperateResult();
			result.setResult(true);
			result.setMsg(user.getPhoto());
			AjaxUtil.ajaxWrite(result, response);
		}

		catch (IOException e) {
			e.printStackTrace();
			OperateResult result = new OperateResult();
			result.setResult(false);
			AjaxUtil.ajaxWrite(result, response);
			File filea = new File(path);
			if (filea.exists()) {
				filea.delete();
			}

		} finally {

			if (inputStream != null)
				try {
					inputStream.close();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (outputStream != null)
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
