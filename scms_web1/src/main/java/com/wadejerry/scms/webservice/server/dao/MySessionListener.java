package com.wadejerry.scms.webservice.server.dao;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import com.wadejerry.scms.frame.AppContext;
import com.wadejerry.scms.modules.auth.dao.BimLoginStatusMapper;
import com.wadejerry.scms.utils.Log.LogManager;

public class MySessionListener implements SessionListener {
	
	private BimLoginStatusMapper mapper;
	
	private BimLoginStatusMapper getMapper(){
		if(mapper==null){
			mapper = (BimLoginStatusMapper)AppContext.getBean("bimLoginStatusMapper");
		}
		return mapper;
	}

	@Override
	public void onStart(Session session) {
		// TODO Auto-generated method stub
		LogManager.logInfo("Session+"+session.getTimeout()+"超时时间"+session.getId()+"创建0000000000000000000000000000000000000000");
		
	}

	@Override
	public void onStop(Session session) {
		// TODO Auto-generated method stub
		LogManager.logInfo("Session"+session.getId()+"停止0000000000000000000000000000000000000000");
		getMapper().deleteBySessionId(session.getId().toString());
		
	}

	@Override
	public void onExpiration(Session session) {
		// TODO Auto-generated method stub
		LogManager.logInfo("Session"+session.getId()+"失效0000000000000000000000000000000000000000");
		getMapper().deleteBySessionId(session.getId().toString());
	}

}
