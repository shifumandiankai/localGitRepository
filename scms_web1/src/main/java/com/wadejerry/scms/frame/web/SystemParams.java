package com.wadejerry.scms.frame.web;

import javax.servlet.http.HttpServletRequest;

import com.wadejerry.scms.frame.AppContext;
import com.wadejerry.scms.frame.constant.user.ConstParamSysConfig;
import com.wadejerry.scms.modules.sysconfig.service.BimSysParamsService;
import com.wadejerry.scms.utils.Log.LogManager;

public class SystemParams {
	
	/**
	 * 二级域名地址
	 */
	public static String path;
	/**
	 * 初始化系统参数
	 * 
	 * @param request 请求对象
	 */
	public SystemParams(HttpServletRequest request){
		SystemParams.path = request.getContextPath();
	}
	
	public static String lprPath= "";//车场图片保存路径
	
	public static Integer lprPhotoSaveTime = null; //车辆图片保存时间
	
	public static String payUrl = ""; //支付平台地址
	
	public static String scmsPayUrl = "";  //连接支付平台地址
	
	public static int paySuccessOffTime =30;
	
	static{
		BimSysParamsService bimSysParamsService= (BimSysParamsService)AppContext.getBean("bimSysParamsService");
		lprPhotoSaveTime =bimSysParamsService.GetParamByKey(ConstParamSysConfig.PFM_PHOTO_SAVE_TIME).getIntValue();
		lprPath =bimSysParamsService.GetParamByKey(ConstParamSysConfig.PFM_PHOTO_SVAE_PATH).getStrValue();
		scmsPayUrl=bimSysParamsService.GetParamByKey(ConstParamSysConfig.SCMS_PAY_URL).getStrValue();
		if(!scmsPayUrl.isEmpty()) {
			payUrl =scmsPayUrl +"/services/PayService?wsdl";
			scmsPayUrl =scmsPayUrl +"/services/SCMSService?wsdl";
		}
		paySuccessOffTime = bimSysParamsService.GetParamByKey(ConstParamSysConfig.SUCCESS_FUL_PAY_OFF_TIME).getIntValue();
		LogManager.logDebug("初始化获取系统参数！");
	}
	
	
	
	
}