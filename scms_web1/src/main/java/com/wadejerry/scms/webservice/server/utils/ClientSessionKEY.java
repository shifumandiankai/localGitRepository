package com.wadejerry.scms.webservice.server.utils;

import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.web.util.RequestPairSource;

public class ClientSessionKEY extends DefaultSessionKey implements RequestPairSource {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	public ClientSessionKEY(HttpServletRequest request,HttpServletResponse response,Serializable sessionId){
		super(sessionId);
		this.request=request;
		this.response=response;
	}

	@Override
	public ServletRequest getServletRequest() {
		// TODO Auto-generated method stub
		return request;
	}

	@Override
	public ServletResponse getServletResponse() {
		// TODO Auto-generated method stub
		return response;
	}

}
