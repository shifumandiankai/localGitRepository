package com.wadejerry.scms.frame.entity;

import java.text.MessageFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.wadejerry.scms.frame.constant.user.ConstUser;
import com.wadejerry.scms.utils.StringUtil;

/**
 * 封装DateTabel插件请求分页参数
 * 
 * @ClassName: PageParamsDto
 * @Description: TODO
 * @author zhanying
 * @date 2016年9月19日 下午10:05:54
 *
 */
public class PageParamsDto {
	private int draw; // 当前页数
	private int start; // 开始记录数
	private int length; // 每页记录数量
	private int companyId; // 企业ID，公用参数
	private int loginUserLevel; // 用户级别
	private int loginUserId; // 登录的用户Id
	private String order; // 排序列
	// 搜索条件字段
	private String searchValue0;
	private String searchValue1;
	private String searchValue2;
	private String searchValue3;
	private String searchValue4;
	private String searchValue5;
	private String searchValue6;
	private String searchValue7;
	private String searchValue8;
	private String searchValue9;
	private int intValue0;
	private int intValue1;
	private int intValue2;
	private int intValue3;
	private int intValue4;
	private int intValue5;
    private Date dateValue1;
    private Date dateValue2;
	
	/**
	 * 修改默认构造函数，解析request对象获取查询条件
	 */
	public PageParamsDto(HttpServletRequest request) {
		this.companyId = LoginInfo.getCompanyId(); // 获取当前操作员所属的企业id
		if (LoginInfo.isCompanyManager()) {
			this.loginUserLevel = ConstUser.ENTERPRISE_ADMINISTRATOR; // 企业管理员
		} else {
			this.loginUserLevel = ConstUser.GENERAL_MANAGER; // 普通管理员
		}
		this.loginUserId = LoginInfo.getLoginId(); // 用户id
		if (request.getParameter("draw") != null) {
			this.draw = Integer.parseInt(request.getParameter("draw"));
		}
		if (request.getParameter("start") != null) {
			this.start = Integer.parseInt(request.getParameter("start")); // 开始记录数
		}
		if (request.getParameter("length") != null) {
			this.length = Integer.parseInt(request.getParameter("length")); // 每页记录数
		}
		if (request.getParameter("order[0][column]") != null) {
			String colIndex = request.getParameter("order[0][column]"); // 获取排序列序号
			this.order = request.getParameter("columns[" + colIndex + "][data]"); // 获取列名
			this.order = MessageFormat.format("  order by {0} {1}", // 封装排序sql语句
					StringUtil.objNameToTableColName(this.order), request.getParameter("order[0][dir]"));
		}
		// 获取查询条件的值
		this.setSearchValue0(request.getParameter("columns[0][search][value]"));
		this.setSearchValue1(request.getParameter("columns[1][search][value]"));
		this.setSearchValue2(request.getParameter("columns[2][search][value]"));
		this.setSearchValue3(request.getParameter("columns[3][search][value]"));
		this.setSearchValue4(request.getParameter("columns[4][search][value]"));
		this.setSearchValue5(request.getParameter("columns[5][search][value]"));
		this.setSearchValue6(request.getParameter("columns[6][search][value]"));
		this.setSearchValue7(request.getParameter("columns[7][search][value]"));
		this.setSearchValue8(request.getParameter("columns[8][search][value]"));
		this.setSearchValue9(request.getParameter("columns[9][search][value]"));
	}

	public String getSearchValue1() {
		return searchValue1;
	}

	public void setSearchValue1(String searchValue1) {
		this.searchValue1 = searchValue1;
	}

	public String getSearchValue2() {
		return searchValue2;
	}

	public void setSearchValue2(String searchValue2) {
		this.searchValue2 = searchValue2;
	}

	public String getSearchValue3() {
		return searchValue3;
	}

	public void setSearchValue3(String searchValue3) {
		this.searchValue3 = searchValue3;
	}

	public String getSearchValue4() {
		return searchValue4;
	}

	public void setSearchValue4(String searchValue4) {
		this.searchValue4 = searchValue4;
	}

	public String getSearchValue5() {
		return searchValue5;
	}

	public void setSearchValue5(String searchValue5) {
		this.searchValue5 = searchValue5;
	}

	public String getSearchValue6() {
		return searchValue6;
	}

	public void setSearchValue6(String searchValue6) {
		this.searchValue6 = searchValue6;
	}

	public String getSearchValue7() {
		return searchValue7;
	}

	public void setSearchValue7(String searchValue7) {
		this.searchValue7 = searchValue7;
	}

	public String getSearchValue8() {
		return searchValue8;
	}

	public void setSearchValue8(String searchValue8) {
		this.searchValue8 = searchValue8;
	}

	public String getSearchValue9() {
		return searchValue9;
	}

	public void setSearchValue9(String searchValue9) {
		this.searchValue9 = searchValue9;
	}

	public String getSearchValue0() {
		return searchValue0;
	}

	public void setSearchValue0(String searchValue0) {
		this.searchValue0 = searchValue0;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getIntValue1() {
		return intValue1;
	}

	public void setIntValue1(int intValue1) {
		this.intValue1 = intValue1;
	}

	public int getIntValue2() {
		return intValue2;
	}

	public void setIntValue2(int intValue2) {
		this.intValue2 = intValue2;
	}

	public int getIntValue3() {
		return intValue3;
	}

	public void setIntValue3(int intValue3) {
		this.intValue3 = intValue3;
	}

	public int getIntValue4() {
		return intValue4;
	}

	public void setIntValue4(int intValue4) {
		this.intValue4 = intValue4;
	}

	public int getIntValue5() {
		return intValue5;
	}

	public void setIntValue5(int intValue5) {
		this.intValue5 = intValue5;
	}

	public int getIntValue0() {
		return intValue0;
	}

	public void setIntValue6(int intValue0) {
		this.intValue0 = intValue0;
	}

	public int getLoginUserLevel() {
		return loginUserLevel;
	}

	public void setLoginUserLevel(int loginUserLevel) {
		this.loginUserLevel = loginUserLevel;
	}

	public int getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(int loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Date getDateValue1() {
		return dateValue1;
	}

	public void setDateValue1(Date dateValue1) {
		this.dateValue1 = dateValue1;
	}

	public Date getDateValue2() {
		return dateValue2;
	}

	public void setDateValue2(Date dateValue2) {
		this.dateValue2 = dateValue2;
	}
	
	
	
}
