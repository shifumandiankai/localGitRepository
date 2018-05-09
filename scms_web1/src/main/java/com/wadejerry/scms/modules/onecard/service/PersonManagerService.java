package com.wadejerry.scms.modules.onecard.service;

import java.util.List;
import java.util.Map;

import com.wadejerry.scms.frame.entity.OperateResult;
import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.onecard.dto.BimPersonDto;
import com.wadejerry.scms.modules.onecard.dto.DeptTreeDto;
import com.wadejerry.scms.modules.onecard.dto.PersonManagerDto;
import com.wadejerry.scms.modules.onecard.model.PersonManager;
import com.wadejerry.scms.modules.pfm.dto.ZTreeDto;

public interface PersonManagerService {

	int getRecordTotal(PageParamsDto paramsDto, String flag, List<Integer> listauth);

	List<PersonManagerDto> getpersonListByPage(PageParamsDto paramsDto, String flag, List<Integer> listauth);

	List<DeptTreeDto> selectPTree(int companyid);

	BimPersonDto findByPersonCode(String personCode, int companyId);

	public void SavePersonInfo(BimPersonDto bpDto);

	BimPersonDto getInfoById(Integer personId);

	void updatePersonInfo(BimPersonDto bpDto);

	List<PersonManagerDto> updateInfoByNode(PageParamsDto paramsDto);

	int getRecordTotalByNode(Integer valueOf);

	PersonManager selectInfoByTreeId(String treeid, int id);


	PersonManager selectInfoByNmame(String deptName);

	PersonManager selectInfoByCode(String deptCode, String deptName, int commpanyId);

	BimPersonDto selectByCodeAndName(String personCode, String personName);

	void updateDeptInfo(Integer integer, Integer valueOf);

	void deletePersonById(Integer personId);

	List<BimPersonDto> selectInfoByDeptId(Integer valueOf);

	List<Map<String, Object>> download(int commpanyId);

	OperateResult validateAndInsert(List<Map<String, String>> list);

	BimPersonDto selectInfoByPersonId(Integer personId, PageParamsDto paramsDto);

	int getRecordTotalByPerson(List<Integer> personIdList, PageParamsDto paramsDto);

	List<DeptTreeDto> selectPTreeByDeptName(int companyid, String val);

	

}
