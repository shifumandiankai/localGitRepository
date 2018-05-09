package com.wadejerry.scms.modules.basic.service;

import com.wadejerry.scms.modules.basic.dto.CardAppDto;

public interface CardAppService {
	//根据车辆编号查询钱包信息
	public CardAppDto SelectCardAppInfo(Integer carInfoId);
	//查询最大流水号
	int selectMaxTradeId(Integer cardid);
	//更新钱包信息
	void updateInfo(CardAppDto appDto,Integer carId);
	
	public CardAppDto selectMaxTradeIdByDto(int carid);
}
