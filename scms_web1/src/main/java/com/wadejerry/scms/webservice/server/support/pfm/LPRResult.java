package com.wadejerry.scms.webservice.server.support.pfm;

import com.wadejerry.scms.webservice.server.support.CommonResult;
import com.wadejerry.scms.webservice.server.support.pfm.dto.LPRDto;

public class LPRResult extends CommonResult{
	private LPRDto[] lprDtoArr;

	public LPRDto[] getLprDtoArr() {
		return lprDtoArr;
	}

	public void setLprDtoArr(LPRDto[] lprDtoArr) {
		this.lprDtoArr = lprDtoArr;
	}
}
