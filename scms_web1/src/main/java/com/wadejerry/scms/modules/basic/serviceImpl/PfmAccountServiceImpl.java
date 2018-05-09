package com.wadejerry.scms.modules.basic.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.basic.dao.CardAppMapper;
import com.wadejerry.scms.modules.basic.dao.PfmAccountMapper;
import com.wadejerry.scms.modules.basic.dto.AccountDto;
import com.wadejerry.scms.modules.basic.dto.CardAppDto;
import com.wadejerry.scms.modules.basic.model.PfmAccount;
import com.wadejerry.scms.modules.basic.service.PfmAccountService;
import com.wadejerry.scms.modules.pfm.dao.PfmFeeRecMapper;
import com.wadejerry.scms.modules.pfm.dto.PfmFeeRecDto;

@Service("pfmAccountservice")
public class PfmAccountServiceImpl implements PfmAccountService {
	@Autowired
	private PfmAccountMapper pfmAccountmapper;
	@Autowired
	private PfmFeeRecMapper feeRecMapper;
	@Autowired
	private CardAppMapper appMapper;

	public List<AccountDto> selectAccountId(Integer card) {

		List<AccountDto> listDto = pfmAccountmapper.selectAccountId(card);
		return listDto;
	}

	public List<AccountDto> selectRecording(Integer id) {
		List<AccountDto> listRecordDto = pfmAccountmapper.selectRecording(id);
		return listRecordDto;
	}

	public int selectCardId(Integer cardId) {
		int record = pfmAccountmapper.selectCardId(cardId);
		return record;
	}

	public List<AccountDto> RechargeMoney(String money) {
		List<AccountDto> listRecordDto = pfmAccountmapper.selectRecording(money);
		return listRecordDto;
	}

	public void RechargeSuccess(String money) {
		pfmAccountmapper.RechargeSuccess(money);

	}

	// @Transactional
	public void insertChargeInfo(AccountDto accountDto) {
		pfmAccountmapper.insertChargeInfo(accountDto);
		// int a=1/0;
	}

	// @Transactional
	public void updateCardAppInfo(AccountDto accountDto) {
		pfmAccountmapper.updateCardAppInfo(accountDto);

	}

	public int selectTradeId() {

		return pfmAccountmapper.selectTradeId();
	}

	public Date selectLastUseTime(Integer cardid) {
		return pfmAccountmapper.selectLastUseTime(cardid);

	}

	public Date selectlastDepositTime(Integer cardid) {

		return pfmAccountmapper.selectlastDepositTime(cardid);
	}

	public String selecttotalChargeMoney(Integer cardid) {
		return pfmAccountmapper.selecttotalChargeMoney(cardid);

	}

	public void insertCardAppInfo(AccountDto accountDto) {
		pfmAccountmapper.insertCardAppInfo(accountDto);

	}

	@Transactional(rollbackFor = Exception.class)
	public void chargetxInfo(AccountDto accountDto) {
		pfmAccountmapper.insertChargeInfo(accountDto);
		pfmAccountmapper.updateCardAppInfo(accountDto);

	}

	/*
	 * public Date MaxDisableTime(Integer cardid,Integer tradeId) { return
	 * pfmAccountmapper.MaxDisableTime(cardid,tradeId);
	 * 
	 * }
	 */

	public String selecttotalReturnMoney(Integer cardid) {
		return pfmAccountmapper.selecttotalReturnMoney(cardid);

	}

	public Date MaxDisableTime(Integer cardid, int tradeId) {
		return pfmAccountmapper.MaxDisableTime(cardid, tradeId);
	}

	public int selectMaxTradeId(Integer cardid) {

		return pfmAccountmapper.selectMaxTradeId(cardid);
	}

	public int selectPhone(String phone) {

		return pfmAccountmapper.selectPhone(phone);
	}

	public List<AccountDto> selecbyphone(String phone) {

		List<AccountDto> accountDtos = pfmAccountmapper.selecbyphone(phone);
		return accountDtos;
	}

	public PfmAccount selectCarInfo(PfmAccount pfmAccount) {
		return pfmAccountmapper.selectCarInfo(pfmAccount);

	}

	public int getRecordTotal(Integer typeid, Integer integer) {
		return pfmAccountmapper.getRecordTotal(typeid, integer);

	}

	/*
	 * public List<pfmAccount> getAccountListByPage(PageParamsDto
	 * paramsDto,Integer cardid) { List<pfmAccount> DtoList
	 * =pfmAccountmapper.getAccountListByPage(paramsDto,cardid); return DtoList;
	 * }
	 */

	public List<PfmAccount> getAccountListByPage(PageParamsDto paramsDto) {
		List<PfmAccount> DtoList = pfmAccountmapper.getAccountListByPage(paramsDto);
		return DtoList;
	}

	public List<PfmAccount> getAccountListByPage(PageParamsDto paramsDto, Integer cardid, Integer typeid) {
		List<PfmAccount> DtoList = pfmAccountmapper.getAccountListByPage(paramsDto, cardid, typeid);
		return DtoList;
	}

	public int selectAllTradeId(Integer cardid) {

		return pfmAccountmapper.selectAllTradeId(cardid);
	}

	public List<PfmAccount> selectData(PfmAccount pfmAccount) {

		return pfmAccountmapper.selectData(pfmAccount);
	}

	public List<PfmAccount> selectInfo(int i) {
		// TODO Auto-generated method stub
		return pfmAccountmapper.selectInfo(i);
	}

	public int selectcarNumber(String selectval) {

		return pfmAccountmapper.selectcarNumber(selectval);
	}

	public int selectInfoCardId(String selectval) {

		return pfmAccountmapper.selectInfoCardId(selectval);
	}

	public List<AccountDto> selecbyCCP(PfmAccount pfmAccount) {
		// TODO Auto-generated method stub
		return pfmAccountmapper.selecbyCCP(pfmAccount);
	}

	@Transactional
	public void chongzhiInfo(CardAppDto appDto, PfmFeeRecDto pfmFeeRecDto, Integer carId) {
		feeRecMapper.insertchongInfo(pfmFeeRecDto);
		appMapper.updateInfo(appDto, carId);

	}

	@Transactional
	public void tuikuanInfo(PfmFeeRecDto pfmFeeRecDto, CardAppDto appDto2) {
		feeRecMapper.insertchongInfo(pfmFeeRecDto);
		appMapper.updateTKInfo(appDto2, pfmFeeRecDto.getCarId());
	}

	@Override
	public int selectAllByTypeAndCarid(int carid) {
		// TODO Auto-generated method stub
		return pfmAccountmapper.selectAllByTypeAndCarid(carid);
	}

	/*
	 * public List<pfmAccount> getAccountListByPage(PageParamsDto paramsDto) {
	 * // TODO Auto-generated method stub return null; }
	 */

}
