package com.wadejerry.scms.modules.basic.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wadejerry.scms.modules.basic.dao.CardAppMapper;
import com.wadejerry.scms.modules.basic.dto.CardAppDto;
import com.wadejerry.scms.modules.basic.service.CardAppService;

@Service("CardAppService")
public class CardAppServiceImpl implements CardAppService {
	@Autowired
	private CardAppMapper cardAppMapper;

	public CardAppDto SelectCardAppInfo(Integer carInfoId) {
		return cardAppMapper.SelectCardAppInfo(carInfoId);

	}

	public int selectMaxTradeId(Integer cardid) {
		return cardAppMapper.selectMaxTradeId(cardid);

	}

	public void updateInfo(CardAppDto appDto, Integer carId) {
		cardAppMapper.updateInfo(appDto, carId);

	}

	@Override
	public CardAppDto selectMaxTradeIdByDto(int carid) {

		return cardAppMapper.selectMaxTradeIdByDto(carid);
	}

}
