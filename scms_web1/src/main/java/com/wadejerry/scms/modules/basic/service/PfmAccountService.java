package com.wadejerry.scms.modules.basic.service;

import java.util.Date;
import java.util.List;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.basic.dto.AccountDto;
import com.wadejerry.scms.modules.basic.dto.CardAppDto;
import com.wadejerry.scms.modules.basic.model.PfmAccount;
import com.wadejerry.scms.modules.pfm.dto.PfmFeeRecDto;

public interface PfmAccountService {

	List<AccountDto> selectAccountId(Integer card);

	List<AccountDto> selectRecording(Integer valueOf);

	int selectCardId(Integer cardid);

	List<AccountDto> RechargeMoney(String money);

	void RechargeSuccess(String money);

	void insertChargeInfo(AccountDto accountDto);

	int selectTradeId();

	Date selectLastUseTime(Integer integer);

	Date selectlastDepositTime(Integer valueOf);

	String selecttotalChargeMoney(Integer valueOf);

	void insertCardAppInfo(AccountDto accountDto);

	void updateCardAppInfo(AccountDto accountDto);

	void chargetxInfo(AccountDto accountDto);

	Date MaxDisableTime(Integer valueOf, int tradeId);

	String selecttotalReturnMoney(Integer valueOf);

	int selectMaxTradeId(Integer valueOf);

	int selectPhone(String phone);

	List<AccountDto> selecbyphone(String phone);

	PfmAccount selectCarInfo(PfmAccount pfmselect);

	int getRecordTotal(Integer typeid, Integer integer);

	//List<pfmAccount> getAccountListByPage(PageParamsDto paramsDto);

	List<PfmAccount> getAccountListByPage(PageParamsDto paramsDto);

	List<PfmAccount> getAccountListByPage(PageParamsDto paramsDto, Integer valueOf, Integer integer);

	int selectAllTradeId(Integer valueOf);

	List<PfmAccount> selectData(PfmAccount pfmAccount);

	List<PfmAccount> selectInfo(int i);

	int selectcarNumber(String selectval);

	int selectInfoCardId(String selectval);

	List<AccountDto> selecbyCCP(PfmAccount pfmAccount);

	void chongzhiInfo(CardAppDto appDto, PfmFeeRecDto pfmFeeRecDto, Integer integer);

	void tuikuanInfo(PfmFeeRecDto pfmFeeRecDto, CardAppDto appDto2);

	int selectAllByTypeAndCarid(int i);

	



}
