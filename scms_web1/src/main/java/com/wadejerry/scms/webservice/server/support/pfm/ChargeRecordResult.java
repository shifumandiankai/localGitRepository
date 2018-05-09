package com.wadejerry.scms.webservice.server.support.pfm;


import java.math.BigDecimal;

import com.wadejerry.scms.webservice.server.support.CommonPageResult;
import com.wadejerry.scms.webservice.server.support.pfm.dto.ChargeRecordDto;


public class ChargeRecordResult extends CommonPageResult {

	private ChargeRecordDto [] chargerRecordDtoArr;
	private BigDecimal totalMoney ;
	public ChargeRecordDto [] getChargerRecordDtoArr() {
		return chargerRecordDtoArr;
	}

	public void setChargerRecordDtoArr(ChargeRecordDto [] chargerRecordDtoArr) {
		this.chargerRecordDtoArr = chargerRecordDtoArr;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
}
