package com.wadejerry.scms.frame.entity;

import java.util.List;

import org.apache.shiro.SecurityUtils;

import com.wadejerry.scms.frame.AppContext;
import com.wadejerry.scms.modules.license.service.LiscenseService;

/**
*  用户登录信息 
* @ClassName: LoginInfo
* @Description: TODO
* @author zhanying
* @date 2016年9月22日 下午9:30:29
*/
public class LoginInfo {
	
	public static int getLoginId() {
		return (int) SecurityUtils.getSubject().getSession().getAttribute("userId");
	}
	
	public static String getLoginName() {
		return SecurityUtils.getSubject().getSession().getAttribute("userName").toString();
	}
	
	public static int getCompanyId() {
		return (int) SecurityUtils.getSubject().getSession().getAttribute("companyId");
	}
	
	public static String getLoginAddress() {
		return SecurityUtils.getSubject().getSession().getAttribute("loginAddress").toString();
	}
	
	//拥有的子系统集合，ConstSystem
	public static List<Integer> getSubSystem(){
		LiscenseService service = (LiscenseService)AppContext.getBean("liscenseService");
		return service.subSystems();
	}
	public static boolean isCompanyManager(){
		return (boolean) SecurityUtils.getSubject().getSession().getAttribute("isCompanyManager");
	}
	//TODO 权限,session等 信息
	
}
