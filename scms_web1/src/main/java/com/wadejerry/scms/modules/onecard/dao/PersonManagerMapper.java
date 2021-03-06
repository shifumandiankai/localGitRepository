package com.wadejerry.scms.modules.onecard.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.onecard.dto.BimPersonDto;
import com.wadejerry.scms.modules.onecard.dto.BimPersonExtDto;
import com.wadejerry.scms.modules.onecard.dto.DeptTreeDto;
import com.wadejerry.scms.modules.onecard.dto.PersonManagerDto;
import com.wadejerry.scms.modules.onecard.model.PersonManager;
import com.wadejerry.scms.modules.pfm.dto.ZTreeDto;

public interface PersonManagerMapper {

	int getRecordTotal(@Param("paramsDto")PageParamsDto paramsDto, @Param("flag")String flag, @Param("listauth")List<Integer> listauth);

	List<PersonManagerDto> getpersonListByPage(@Param("paramsDto")PageParamsDto paramsDto, @Param("flag")String flag, @Param("listauth")List<Integer> listauth);

	List<DeptTreeDto> selectPTree(int companyid);

	BimPersonDto findByPersonCode(String personCode, int companyId);

	public void SavePersonInfo(BimPersonDto bpDto);

	//public void SaveBirthday(bimpersonDto bpDto);

	BimPersonDto getInfoById(Integer personId);

	void updatePersonInfo(BimPersonDto bpDto);

	void updateBirthday(BimPersonDto bpDto);

	int findPersonId(Integer personId, Integer bimCompanyId);

	void SaveBirthday(int a, Date birthday);

	List<PersonManagerDto> updateInfoByNode(PageParamsDto paramsDto);

	int getRecordTotalByNode(Integer valueOf);

	PersonManager selectInfoByTreeId(String treeid);

	PersonManager selectInfoByCode(String deptCode,String deptName);

	PersonManager selectInfoByNmame(String deptName);

	PersonManager selectInfoByTreeId(String treeid, int id);

	PersonManager selectInfoByCode(String deptCode, String deptName, int commpanyId);

	BimPersonDto selectByCodeAndName(String personCode, String personName);
	
	void updateEmail(BimPersonExtDto bimPersonExtDto);

	void SaveBirthday(BimPersonDto bpDto);

	void updateDeptInfo(Integer personId, Integer valueOf);

	void deletePersonById(Integer personId);

	List<BimPersonDto> selectInfoByDeptId(Integer valueOf);

	List<Map<String, Object>> download(int commpanyId);

	int selectCountByPersonCode(String trim, int commpanyId);

	int selectCountByDeptId(Integer integer, int commpanyId);

	int selectCountByCertType(String trim, int commpanyId);

	BimPersonDto selectInfoByPersonId(@Param("personId")Integer personId, @Param("paramsDto")PageParamsDto paramsDto);

	int getRecordTotalByPerson(@Param("personIdList")List<Integer> personIdList, @Param("paramsDto")PageParamsDto paramsDto);

	List<DeptTreeDto> selectPTreeByDeptName(int companyid, String val);

	void updateCateType(Integer temp);

	void updateInfo(BimPersonDto bpDto);

	void updateCustom3(String valueOf);

	//int getRecordTotal(Integer deptId, Integer company, Integer loginUserLevel);

}
