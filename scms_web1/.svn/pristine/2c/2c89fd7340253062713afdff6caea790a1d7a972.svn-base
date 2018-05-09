package com.wadejerry.scms.modules.onecard.dao;

import java.util.List;
import java.util.Map;

import com.wadejerry.scms.frame.entity.OperateResult;
import com.wadejerry.scms.modules.onecard.dto.BimDeptDto;

public interface BimDeptMapper {
	public BimDeptDto selectInfoByTreeId(Integer treeid, Integer companyid);

	public BimDeptDto selectInfoByCodeOrName(Integer deptCode, String deptName, Integer companyid);

	void insertInfo(BimDeptDto bimDeptDto);

	int selectTotalCount();

	public BimDeptDto selectIncodeById(Integer inCode);

	void updateInfo(BimDeptDto bimDeptDto);

	void deleteTreeNode(BimDeptDto bimDeptDto);

	BimDeptDto selectCount(Integer deptCode, String deptName);

	BimDeptDto selectInfoByDeptName(String deptName);

	List<Map<String, Object>> download(int companyid);

	public OperateResult validateAndInsert(List<Map<String, String>> list);

	int selectCountByDeptCode(Integer deptcode, Integer id);

	int selectCountByDeptName(String deptName, Integer id);

	int selectCountByInCode(Integer inCode, Integer id);

	void exportDept(BimDeptDto bimDeptDto);

	BimDeptDto selectIncodeByDeptCode(Integer deptCode);

	public BimDeptDto selectByDeptCode(Integer code);
}
