package com.wadejerry.scms.webservice.server.support.pfm;

import com.wadejerry.scms.webservice.server.support.CommonPageResult;
import com.wadejerry.scms.webservice.server.support.pfm.dto.AccountDto;

public class AccountResult extends CommonPageResult{
	private AccountDto[] AccountDtoArr;

	public AccountDto[] getAccountDtoArr() {
		return AccountDtoArr;
	}

	public void setAccountDtoArr(AccountDto[] accountDtoArr) {
		AccountDtoArr = accountDtoArr;
	}
	
}
