package com.wadejerry.scms.webservice.server.support;

public class CommonPageRequest {
	private int reqPage;	//当前页数
	private int pageItems;	//每页条目数

	public int getReqPage() {
		return reqPage;
	}

	public void setReqPage(int reqPage) {
		this.reqPage = reqPage;
	}

	public int getPageItems() {
		return pageItems;
	}

	public void setPageItems(int pageItems) {
		this.pageItems = pageItems;
	}
}
