package com.wadejerry.scms.modules.onecard.service;

import java.util.List;
import java.util.Map;

import com.wadejerry.scms.frame.entity.OperateResult;
import com.wadejerry.scms.modules.onecard.dto.BimDeptDto;

public interface BimDeptService {
	public BimDeptDto selectInfoByTreeId(Integer integer, Integer companyid);

	public BimDeptDto selectInfoByCodeOrName(Integer integer, String deptName, Integer companyid);

	void insertInfo(BimDeptDto bimDeptDto);

	int selectTotalCount();

	public BimDeptDto selectIncodeById(Integer inCode);

	void updateInfo(BimDeptDto bimDeptDto);

	void deleteTreeNode(BimDeptDto bimDeptDto);

	BimDeptDto selectCount(Integer deptCode, String deptName);

	BimDeptDto selectInfoByDeptName(String deptName);

	List<Map<String, Object>> download(int companyid);

	public OperateResult validateAndInsert(List<Map<String, String>> list);

	public BimDeptDto selectByDeptCode(Integer code);
}
