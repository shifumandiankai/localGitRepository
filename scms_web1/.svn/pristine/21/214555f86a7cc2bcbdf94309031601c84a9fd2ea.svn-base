package com.wadejerry.scms.modules.basic.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wadejerry.scms.frame.entity.PageParamsDto;
import com.wadejerry.scms.modules.basic.dto.AccountDto;
import com.wadejerry.scms.modules.basic.model.PfmAccount;

public interface PfmAccountMapper {

public List<AccountDto> selectAccountId(String phone);

public List<AccountDto> selectRecording(Integer id);

public int selectCardId(Integer cardId);

public List<AccountDto> selectRecording(String money);

public void RechargeSuccess(String money);

public void insertChargeInfo(AccountDto accountDto);

public int selectTradeId();



public Date selectLastUseTime(Integer cardid);

public Date selectlastDepositTime(Integer cardid);

public String selecttotalChargeMoney(Integer cardid);

public void insertCardAppInfo(AccountDto accountDto);

public void updateCardAppInfo(AccountDto accountDto);

public Date MaxDisableTime(Integer cardid, Integer tradeId);

public String selecttotalReturnMoney(Integer cardid);

public int selectMaxTradeId(Integer cardid);

public int selectPhone(String phone);

public List<AccountDto> selecbyphone(String phone);

public List<AccountDto> selectAccountId(Integer card);

public PfmAccount selectCarInfo(PfmAccount pfmAccount);

public int getRecordTotal(Integer typeid, Integer integer);

public List<PfmAccount> getAccountListByPage(PageParamsDto paramsDto);

public List<PfmAccount> getAccountListByPage(@Param("paramsDto")PageParamsDto paramsDto, @Param("cardid")Integer cardid, @Param("typeid")Integer typeid);

public int selectAllTradeId(Integer cardid);

public List<PfmAccount> selectData(PfmAccount pfmAccount);

public List<PfmAccount> selectInfo(int i);

public int selectcarNumber(String selectval);

public int selectInfoCardId(String selectval);

public List<AccountDto> selecbyCCP(PfmAccount pfmAccount);

public int selectAllByTypeAndCarid(int typeid);



}
