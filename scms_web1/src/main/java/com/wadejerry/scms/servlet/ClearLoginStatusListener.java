package com.wadejerry.scms.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.wadejerry.scms.frame.AppContext;
import com.wadejerry.scms.modules.auth.dao.BimLoginStatusMapper;
import com.wadejerry.scms.pay.PayThread;
import com.wadejerry.scms.utils.Log.LogManager;

public class ClearLoginStatusListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// 停止支付等待线程
		PayThread.started=false; 
		// 清除登录状态信息
		BimLoginStatusMapper dao = (BimLoginStatusMapper)AppContext.getBean("bimLoginStatusMapper");
		int count=-1;
		try{
		count=dao.deleteAll();
		}
		catch(Exception e){
			
		}
		if(count!=-1){
		LogManager.logInfo("loginStatus表清理成功");
		}
		else{
			LogManager.logInfo("loginStatus表清理失败");
		}

	}

}
