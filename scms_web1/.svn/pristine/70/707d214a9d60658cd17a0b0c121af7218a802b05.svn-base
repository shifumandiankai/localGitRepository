package com.wadejerry.scms.modules.onecard.service;

import java.util.List;

import com.wadejerry.scms.frame.entity.SelectDataDto;
import com.wadejerry.scms.modules.onecard.dto.BimCardDto;
import com.wadejerry.scms.modules.onecard.model.BimCard;

public interface BimCardService {

	void insertCardInfo(BimCardDto bimCardDto);

	List<BimCard> selectCardInfoByPage(Integer integer);

	void updateCardInfo(Integer valueOf, String cardId);

	void updateStatusCardInfo(Integer valueOf, Integer type);

	void deleteCardInfo(Integer bimCardId);

	int selectFinger(Integer personId, String cardId);

	void updateFingerSq(BimCardDto bimCardDto);

	List<BimCardDto> selectCardByCarNumber(Integer valueOf);

	
	List<SelectDataDto> selectCardInfoByCompanyId(Integer BimcompanyId);
	
	
}
