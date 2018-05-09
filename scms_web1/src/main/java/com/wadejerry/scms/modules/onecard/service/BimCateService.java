package com.wadejerry.scms.modules.onecard.service;

import java.util.Date;
import java.util.List;

import com.wadejerry.scms.modules.onecard.model.BimCate;

public interface BimCateService {

	List<BimCate> selectAllInfo(int i);

	int selectRandom();

	void insertCateInfo(BimCate bimCate);

	void updateCateInfo(Integer valueOf, String cateName, String note, Date date);

	BimCate selectByCateName(String cateName);

	void deleteInfo(Integer bimCateId, List<Integer> list);

	List<Integer> selectByPersonId(Integer integer);

	void deleteByCateId(Integer valueOf);

	//List<BimCate> selectAllInfo();

}
