package com.wadejerry.scms.modules.device.model;

import java.util.Date;
//server服务器界面使用
public class Server {
	
		public Integer getServerId() {
		return serverId;
	}

	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}

	public Integer getBimCompanyId() {
		return bimCompanyId;
	}

	public void setBimCompanyId(Integer bimCompanyId) {
		this.bimCompanyId = bimCompanyId;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getNetZoneId() {
		return netZoneId;
	}

	public void setNetZoneId(Integer netZoneId) {
		this.netZoneId = netZoneId;
	}

	public Integer getParkId() {
		return parkId;
	}

	public void setParkId(Integer parkId) {
		this.parkId = parkId;
	}

	public String getBelongto() {
		return belongto;
	}

	public void setBelongto(String belongto) {
		this.belongto = belongto;
	}

		private Integer serverId;

	    private Integer bimCompanyId;

	    private String serverName;

	    private String ip;

	    private String port;

	    private Date insertTime;

	    private Date updateTime;

	    private String userName;
	    
	    private Integer netZoneId;
	    
	    private Integer parkId;
	    
	    private String belongto;
	    

}
