package com.wadejerry.scms.modules.onecard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wadejerry.scms.frame.entity.SelectDataDto;
import com.wadejerry.scms.modules.onecard.dto.BimCardDto;
import com.wadejerry.scms.modules.onecard.model.BimCard;

public interface BimCardMapper {

	void insertCardInfo(BimCardDto bimCardDto);

	List<BimCard> selectCardInfoByPage(Integer personId);

	void updateCardInfo(Integer bimCardId, String cardId);

	void updateStatusCardInfo(Integer bimCardId, @Param("type")Integer type);

	void deleteCardInfo(Integer bimCardId);

	int selectFinger(Integer personId, String cardId);

	void updateFingerSq(BimCardDto bimCardDto);

	List<BimCardDto> selectCardByCarNumber(Integer personId);

	List<BimCard> selectCardInfoByCompanyId(Integer bimcompanyId);

}
