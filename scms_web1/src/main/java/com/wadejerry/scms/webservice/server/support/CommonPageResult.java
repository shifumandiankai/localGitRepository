package com.wadejerry.scms.webservice.server.support;

public class CommonPageResult extends CommonResult{
	
	private int	allRow;
	private int	totalPage;
	private int	currentPage;
	private int	pageSize;
	
	/**
	 * 创建一个新的实例DepartmentListResult.
	 */
	public CommonPageResult() {
		super();
	}
	
	/**
	 * 创建一个新的实例DepartmentListResult.
	 * @param allRow
	 * @param totalPage
	 * @param currentPage
	 * @param pageSize
	 * @param departmentArray
	 */
	public CommonPageResult(int allRow, int totalPage, int currentPage, int pageSize) {
		super();
		this.allRow = allRow;
		this.totalPage = totalPage;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
	
	public int getAllRow() {
		return allRow;
	}
	
	public void setAllRow(int allRow) {
		this.allRow = allRow;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
