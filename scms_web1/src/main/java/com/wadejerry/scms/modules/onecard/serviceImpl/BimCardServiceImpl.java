package com.wadejerry.scms.modules.onecard.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wadejerry.scms.frame.entity.SelectDataDto;
import com.wadejerry.scms.modules.onecard.dao.BimCardMapper;
import com.wadejerry.scms.modules.onecard.dto.BimCardDto;
import com.wadejerry.scms.modules.onecard.model.BimCard;
import com.wadejerry.scms.modules.onecard.service.BimCardService;
@Service("bimCardService")
public class BimCardServiceImpl implements BimCardService{
    @Autowired
    private BimCardMapper bimCardMapper;
	@Override
	public void insertCardInfo(BimCardDto bimCardDto) {
		bimCardMapper.insertCardInfo(bimCardDto);
		
	}
	@Override
	public List<BimCard> selectCardInfoByPage(Integer personId) {
		// TODO 根据personID查找bim_card信息
		return bimCardMapper.selectCardInfoByPage(personId);
	}
	@Override
	public void updateCardInfo(Integer bimCardId, String cardId) {
		// TODO Auto-generated method stub
		 bimCardMapper.updateCardInfo(bimCardId,cardId);
	}
	@Override
	public void updateStatusCardInfo(Integer bimCardId,Integer type) {
		// TODO Auto-generated method stub
		 bimCardMapper.updateStatusCardInfo(bimCardId,type);
	}
	@Override
	public void deleteCardInfo(Integer bimCardId) {
		// TODO Auto-generated method stub
		bimCardMapper.deleteCardInfo(bimCardId);
	}
	@Override
	public int selectFinger(Integer personId, String cardId) {
		// TODO Auto-generated method stub
		return bimCardMapper.selectFinger(personId,cardId);
	}
	@Override
	public void updateFingerSq(BimCardDto bimCardDto) {
		// TODO Auto-generated method stub
		bimCardMapper.updateFingerSq(bimCardDto);
	}
	@Override
	public List<BimCardDto> selectCardByCarNumber(Integer personId) {
		// TODO Auto-generated method stub
		return bimCardMapper.selectCardByCarNumber(personId);
	}
	@Override
	public List<SelectDataDto> selectCardInfoByCompanyId(Integer BimcompanyId) {
		// TODO Auto-generated method stub
		 List<BimCard>  list=bimCardMapper.selectCardInfoByCompanyId(BimcompanyId);
		 List<SelectDataDto> dataDto=new ArrayList<>();
		 if(list.size()>0){
			 for(BimCard temp:list){
					SelectDataDto selectDataDto=new SelectDataDto();
					selectDataDto.setId(temp.getBimCardId());
					selectDataDto.setText(temp.getCardId());
					dataDto.add(selectDataDto);
				}
		 }
		
		return dataDto;
	}

}
