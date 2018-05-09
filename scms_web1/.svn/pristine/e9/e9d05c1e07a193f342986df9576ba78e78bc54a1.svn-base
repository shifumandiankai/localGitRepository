package com.wadejerry.scms.modules.onecard.dao;

import java.util.Date;
import java.util.List;

import com.wadejerry.scms.modules.onecard.model.BimCate;
import com.wadejerry.scms.modules.onecard.model.BimCatePerson;

public interface BimCateMapper {

	List<BimCate> selectAllInfo(int i);

	int selectRandom();

	void insertCateInfo(BimCate bimCate);

	void updateCateInfo(Integer bimCateId, String cateName, String note, Date date);

	BimCate selectByCateName(String cateName);

	void deleteInfo(Integer bimCateId);

	void insertBimCatePerson(BimCatePerson bimCatePerson);

	void deleteBimCatePerson(Integer personId);

	void updateCustom1(Integer bimCateId);

	List<Integer> selectByPersonId(Integer bimCateId);

}
